package com.chintansoni.android.firebasedatabase.model;

public class UserModel {
    private String mUid;
    private String mDisplayName;
    private String mEmail;
    private String mPhotourl;

    public UserModel(String mUid, String mDisplayName, String mEmail, String mPhotourl) {
        this.mUid = mUid;
        this.mDisplayName = mDisplayName;
        this.mEmail = mEmail;
        this.mPhotourl = mPhotourl;
    }

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public String getmDisplayName() {
        return mDisplayName;
    }

    public void setmDisplayName(String mDisplayName) {
        this.mDisplayName = mDisplayName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhotourl() {
        return mPhotourl;
    }

    public void setmPhotourl(String mPhotourl) {
        this.mPhotourl = mPhotourl;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "mUid='" + mUid + '\'' +
                ", mDisplayName='" + mDisplayName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPhotourl='" + mPhotourl + '\'' +
                '}';
    }
}
