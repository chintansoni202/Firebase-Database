package com.chintansoni.android.firebasedatabase.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TodoModel implements Parcelable {
    public static final Creator<TodoModel> CREATOR = new Creator<TodoModel>() {
        @Override
        public TodoModel createFromParcel(Parcel in) {
            return new TodoModel(in);
        }

        @Override
        public TodoModel[] newArray(int size) {
            return new TodoModel[size];
        }
    };
    private String mUid;
    private String mTodoContent;
    private boolean mIsCompleted;

    public TodoModel() {
    }

    public TodoModel(String mUid, String mTodoContent, boolean mIsCompleted) {
        this.mUid = mUid;
        this.mTodoContent = mTodoContent;
        this.mIsCompleted = mIsCompleted;
    }

    private TodoModel(Parcel in) {
        mUid = in.readString();
        mTodoContent = in.readString();
        mIsCompleted = in.readByte() != 0;
    }

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public String getmTodoContent() {
        return mTodoContent;
    }

    public void setmTodoContent(String mTodoContent) {
        this.mTodoContent = mTodoContent;
    }

    public boolean ismIsCompleted() {
        return mIsCompleted;
    }

    public void setmIsCompleted(boolean mIsCompleted) {
        this.mIsCompleted = mIsCompleted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUid);
        parcel.writeString(mTodoContent);
        parcel.writeByte((byte) (mIsCompleted ? 1 : 0));
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "mUid='" + mUid + '\'' +
                ", mTodoContent='" + mTodoContent + '\'' +
                ", mIsCompleted=" + mIsCompleted +
                '}';
    }
}
