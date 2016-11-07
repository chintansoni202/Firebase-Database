package com.chintansoni.android.firebasedatabase.dialogfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.chintansoni.android.firebasedatabase.R;
import com.chintansoni.android.firebasedatabase.base.BaseDialogFragment;
import com.chintansoni.android.firebasedatabase.model.TodoMasterModel;

import butterknife.BindView;

public class AddEditDialogFragment extends BaseDialogFragment implements TextView.OnEditorActionListener {

    public static final String ACTION_ADD = "Add";
    public static final String ACTION_EDIT = "Edit";
    private static final String ARG_TODO_MASTER_MODEL = "TodoMasterModel";
    private static final String ARG_ACTION = "Action";

    @BindView(R.id.actv_dialog_fragment_add_edit_item)
    AppCompatTextView mAppCompatTextView;
    @BindView(R.id.acet_dialog_fragment_add_edit_item)
    AppCompatEditText mAppCompatEditText;

    private TodoMasterModel mTodoMasterModel;
    private String mAction;

    public static DialogFragment getInstance(TodoMasterModel todoMasterModel, String action) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TODO_MASTER_MODEL, todoMasterModel);
        bundle.putString(ARG_ACTION, action);
        AddEditDialogFragment addEditDialogFragment = new AddEditDialogFragment();
        addEditDialogFragment.setArguments(bundle);
        return addEditDialogFragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.dialog_fragment_add_edit_item;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTodoMasterModel = bundle.getParcelable(ARG_TODO_MASTER_MODEL);
            mAction = bundle.getString(ARG_ACTION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAppCompatTextView.setText(mAction);

        if (mAction.equals(ACTION_EDIT)) {
            mAppCompatEditText.setText(mTodoMasterModel.getmTodoModel().getmTodoContent());
            mAppCompatEditText.setSelection(mAppCompatEditText.getText().length());
        }

        // Show soft keyboard automatically
        mAppCompatEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mAppCompatEditText.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            mAppCompatEditText.setError(null);
            if (TextUtils.isEmpty(mAppCompatEditText.getText())) {
                mAppCompatEditText.setError("Please enter some text");
                return true;
            }

            mTodoMasterModel.getmTodoModel().setmTodoContent(mAppCompatEditText.getText().toString());
            OnDoneClickListener mOnDoneClickListener = (OnDoneClickListener) getActivity();
            mOnDoneClickListener.onDoneClick(mTodoMasterModel, mAction);
            dismiss();
            return true;
        }
        return false;
    }

    public interface OnDoneClickListener {
        void onDoneClick(TodoMasterModel todoMasterModel, String action);
    }
}
