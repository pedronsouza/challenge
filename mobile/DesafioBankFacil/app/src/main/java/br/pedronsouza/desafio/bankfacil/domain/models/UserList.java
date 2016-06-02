package br.pedronsouza.desafio.bankfacil.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class UserList implements Parcelable {
    List<User> users;

    public UserList() {

    }

    protected UserList(Parcel in) {
        users = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<UserList> CREATOR = new Creator<UserList>() {
        @Override
        public UserList createFromParcel(Parcel in) {
            return new UserList(in);
        }

        @Override
        public UserList[] newArray(int size) {
            return new UserList[size];
        }
    };

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(users);
    }
}
