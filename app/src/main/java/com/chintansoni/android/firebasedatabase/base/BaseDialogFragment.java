package com.chintansoni.android.firebasedatabase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public abstract int getLayoutResource();

    public void showToast(String message) {
        ((BaseAppCompatActivity) getActivity()).showToast(message);
    }

    public void showAlertDialog(String message) {
        ((BaseAppCompatActivity) getActivity()).showAlertDialog(message);
    }

    public void showProgressDialog() {
        ((BaseAppCompatActivity) getActivity()).showProgressDialog();
    }

    public void hideProgressDialog() {
        ((BaseAppCompatActivity) getActivity()).hideProgressDialog();
    }

    public void launchActivity(Class activityClass) {
        launchActivity(activityClass, null);
    }

    public void launchActivity(Class activityClass, Bundle bundle) {
        ((BaseAppCompatActivity) getActivity()).launchActivity(activityClass, bundle);
    }
}
