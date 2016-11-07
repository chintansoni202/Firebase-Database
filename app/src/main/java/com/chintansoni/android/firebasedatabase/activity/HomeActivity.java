package com.chintansoni.android.firebasedatabase.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.chintansoni.android.firebasedatabase.R;
import com.chintansoni.android.firebasedatabase.base.BaseNavigationDrawerActivity;
import com.chintansoni.android.firebasedatabase.dialogfragment.AddEditDialogFragment;
import com.chintansoni.android.firebasedatabase.interfaces.OnTodoActionListener;
import com.chintansoni.android.firebasedatabase.model.TodoMasterModel;
import com.chintansoni.android.firebasedatabase.model.TodoModel;
import com.chintansoni.android.firebasedatabase.viewholder.TodoViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseNavigationDrawerActivity implements AddEditDialogFragment.OnDoneClickListener {

    @BindView(R.id.rv_home)
    RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReferenceTodo;
    private OnTodoActionListener mOnTodoActionListener;

    @OnClick(R.id.fab_home)
    void onFabHomeClick(View view) {
        showAddEditDialog(
                new TodoMasterModel("", new TodoModel(FirebaseAuth.getInstance().getCurrentUser().getUid(), "", false))
                , AddEditDialogFragment.ACTION_ADD);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabaseReferenceTodo = FirebaseDatabase.getInstance().getReference("todos");
        mOnTodoActionListener = new OnTodoActionListener() {
            @Override
            public void onCheckBoxChange(CompoundButton compoundButton, boolean checked) {
                TodoMasterModel todoMasterModel = (TodoMasterModel) compoundButton.getTag();
                todoMasterModel.getmTodoModel().setmIsCompleted(checked);
                onDoneClick(todoMasterModel, AddEditDialogFragment.ACTION_EDIT);
                Log.i("Checkbox", todoMasterModel.toString());
            }

            @Override
            public void onTextViewClick(View view) {
                TodoMasterModel todoMasterModel = (TodoMasterModel) view.getTag();
                showAddEditDialog(todoMasterModel, AddEditDialogFragment.ACTION_EDIT);
                Log.i("TextView", todoMasterModel.toString());
            }

            @Override
            public void onImageButtonClick(View view) {
                TodoMasterModel todoMasterModel = (TodoMasterModel) view.getTag();
                mDatabaseReferenceTodo.child(todoMasterModel.getmId()).setValue(null);
                Log.i("Delete", todoMasterModel.toString());
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerAdapter mHomeRecyclerAdapter = new FirebaseRecyclerAdapter<TodoModel, TodoViewHolder>(TodoModel.class, R.layout.list_item_home, TodoViewHolder.class, mDatabaseReferenceTodo) {
            @Override
            public void populateViewHolder(TodoViewHolder todoViewHolder, TodoModel todoModel, int position) {

                todoViewHolder.setListener(mOnTodoActionListener);

                TodoMasterModel todoMasterModel = new TodoMasterModel(getRef(position).getKey(), todoModel);

                todoViewHolder.getmAppCompatCheckBox().setTag(todoMasterModel);
                todoViewHolder.getmAppCompatTextView().setTag(todoMasterModel);
                todoViewHolder.getmAppCompatImageButton().setTag(todoMasterModel);

                todoViewHolder.getmAppCompatCheckBox().setChecked(todoModel.ismIsCompleted());
                todoViewHolder.getmAppCompatTextView().setText(todoModel.getmTodoContent());

            }
        };
        mRecyclerView.setAdapter(mHomeRecyclerAdapter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected int getMenuResource() {
        return NO_MENU;
    }

    @Override
    public void onDoneClick(TodoMasterModel todoMasterModel, String action) {
        Log.i("Todo", todoMasterModel.toString());
        String key;
        if (action.equals(AddEditDialogFragment.ACTION_ADD)) {
            key = mDatabaseReferenceTodo.push().getKey();
        } else {
            key = todoMasterModel.getmId();
        }
        mDatabaseReferenceTodo.child(key).setValue(todoMasterModel.getmTodoModel());
    }

    private void showAddEditDialog(TodoMasterModel todoMasterModel, String action) {
        DialogFragment dialogFragment = AddEditDialogFragment.getInstance(todoMasterModel, action);
        dialogFragment.show(getSupportFragmentManager(), AddEditDialogFragment.class.getSimpleName());
    }
}
