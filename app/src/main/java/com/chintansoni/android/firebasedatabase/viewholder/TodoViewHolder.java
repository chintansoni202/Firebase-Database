package com.chintansoni.android.firebasedatabase.viewholder;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;

import com.chintansoni.android.firebasedatabase.R;
import com.chintansoni.android.firebasedatabase.interfaces.OnTodoActionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class TodoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.accbListItemHome)
    AppCompatCheckBox mAppCompatCheckBox;
    @BindView(R.id.actvListItemHome)
    AppCompatTextView mAppCompatTextView;
    @BindView(R.id.acibListItemHome)
    AppCompatImageButton mAppCompatImageButton;
    private OnTodoActionListener mOnTodoActionListener;

    public TodoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setListener(OnTodoActionListener onTodoActionListener) {
        mOnTodoActionListener = onTodoActionListener;
    }

    public AppCompatCheckBox getmAppCompatCheckBox() {
        return mAppCompatCheckBox;
    }

    public AppCompatTextView getmAppCompatTextView() {
        return mAppCompatTextView;
    }

    public AppCompatImageButton getmAppCompatImageButton() {
        return mAppCompatImageButton;
    }

    @OnCheckedChanged(R.id.accbListItemHome)
    void onCheckBoxChange(CompoundButton compoundButton, boolean checked) {
        mOnTodoActionListener.onCheckBoxChange(compoundButton, checked);
    }

    @OnClick(R.id.actvListItemHome)
    void onTextViewClick(View view) {
        mOnTodoActionListener.onTextViewClick(view);
    }

    @OnClick(R.id.acibListItemHome)
    void onImageButtonClick(View view) {
        mOnTodoActionListener.onImageButtonClick(view);
    }
}
