package com.chintansoni.android.firebasedatabase.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TodoMasterModel implements Parcelable {
    public static final Creator<TodoMasterModel> CREATOR = new Creator<TodoMasterModel>() {
        @Override
        public TodoMasterModel createFromParcel(Parcel in) {
            return new TodoMasterModel(in);
        }

        @Override
        public TodoMasterModel[] newArray(int size) {
            return new TodoMasterModel[size];
        }
    };
    private String mId;
    private TodoModel mTodoModel;

    public TodoMasterModel(String mId, TodoModel mTodoModel) {
        this.mId = mId;
        this.mTodoModel = mTodoModel;
    }

    protected TodoMasterModel(Parcel in) {
        mId = in.readString();
        mTodoModel = in.readParcelable(TodoModel.class.getClassLoader());
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public TodoModel getmTodoModel() {
        return mTodoModel;
    }

    public void setmTodoModel(TodoModel mTodoModel) {
        this.mTodoModel = mTodoModel;
    }

    @Override
    public String toString() {
        return "TodoMasterModel{" +
                "mId='" + mId + '\'' +
                ", mTodoModel=" + mTodoModel +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeParcelable(mTodoModel, i);
    }
}
