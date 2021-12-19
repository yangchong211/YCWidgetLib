package com.yc.ycexpandview.pager;

import android.os.Parcel;
import android.os.Parcelable;

public class PagerBean implements Parcelable{

    private String name;
    private int image;

    public PagerBean(String name, int image) {
        this.name = name;
        this.image = image;
    }

    protected PagerBean(Parcel in) {
        name = in.readString();
        image = in.readInt();
    }

    public static final Creator<PagerBean> CREATOR = new Creator<PagerBean>() {
        @Override
        public PagerBean createFromParcel(Parcel in) {
            return new PagerBean(in);
        }

        @Override
        public PagerBean[] newArray(int size) {
            return new PagerBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(image);
    }
}
