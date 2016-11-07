package com.chintansoni.android.firebasedatabase.interfaces;

import android.view.View;
import android.widget.CompoundButton;

public interface OnTodoActionListener {
    void onCheckBoxChange(CompoundButton compoundButton, boolean checked);
    void onTextViewClick(View view);
    void onImageButtonClick(View view);
}
