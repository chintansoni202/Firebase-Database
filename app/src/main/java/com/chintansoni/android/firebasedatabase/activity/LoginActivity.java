package com.chintansoni.android.firebasedatabase.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.chintansoni.android.firebasedatabase.R;
import com.chintansoni.android.firebasedatabase.base.BaseSocialLoginActivity;
import com.chintansoni.android.firebasedatabase.model.UserModel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseSocialLoginActivity {

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBar();
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mFirebaseAuth.getCurrentUser() != null) {
            launchActivity(HomeActivity.class);
            finish();
        }
        mFirebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAuth != null) {
            mFirebaseAuth.removeAuthStateListener(this);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected int getMenuResource() {
        return NO_MENU;
    }

    @Override
    protected void onGoogleSignInResult(GoogleSignInResult result) {
        super.onGoogleSignInResult(result);
        if (result.isSuccess()) {
            firebaseAuthWithGoogle(result.getSignInAccount());
        } else {
            showToast(result.getStatus().getStatusCode() + "");
            showToast("Google Sign In failed.");
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            launchActivity(HomeActivity.class);
                        } else {
                            showToast("Firebase Authentication failed.");
                        }
                    }
                });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() != null) {
            FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReferenceUser = mFirebaseDatabase.getReference("user");
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            Map<String, Object> map = new HashMap<>();
            map.put(databaseReferenceUser.push().getKey(), new UserModel(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getPhotoUrl() == null ? "" : firebaseUser.getPhotoUrl().toString()));
            databaseReferenceUser.setValue(map);
        }
    }
}
