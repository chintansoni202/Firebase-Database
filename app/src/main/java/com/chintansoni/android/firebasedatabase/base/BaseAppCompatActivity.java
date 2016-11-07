package com.chintansoni.android.firebasedatabase.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.chintansoni.android.firebasedatabase.R;

import butterknife.ButterKnife;

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    /**
     * To pass from activity to getMenuResource() when no menu is to be shown
     */
    public static final int NO_MENU = 0;
    public Toolbar mToolbar;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutResource();

    protected abstract int getMenuResource();

    public void setToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuResource() == NO_MENU) {
            return false;
        } else {
            getMenuInflater().inflate(getMenuResource(), menu);
            return true;
        }
    }

    public void showProgressDialog() {
        showProgressDialog("Loading");
    }

    public void showProgressDialog(String title) {
        showProgressDialog(title, "Please wait...");
    }

    public void showProgressDialog(String title, String message) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.setMessage(message);
        else
            mProgressDialog = ProgressDialog.show(this, title, message, true, false);
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.setMessage(message);
        alertDialog.show();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void launchActivity(Class activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (requestCode == -1) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, requestCode);
        }
    }

    public void launchActivity(Class activity, Bundle bundle) {
        launchActivity(activity, bundle, -1);
    }

    public void launchActivity(Class activity) {
        launchActivity(activity, null);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeyboard();
    }
}