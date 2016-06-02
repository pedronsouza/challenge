package br.pedronsouza.desafio.bankfacil.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String name;
    private String thumbImgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbImgUrl() {
        return thumbImgUrl;
    }

    public void setThumbImgUrl(String thumbImgUrl) {
        this.thumbImgUrl = thumbImgUrl;
    }

    public User() {

    }

    protected User(Parcel in) {
        name = in.readString();
        thumbImgUrl = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(thumbImgUrl);
    }
}
