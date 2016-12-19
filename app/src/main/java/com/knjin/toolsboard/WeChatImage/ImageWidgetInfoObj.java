package com.knjin.toolsboard.WeChatImage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jing on 16/12/19.
 */

public class ImageWidgetInfoObj implements Parcelable {
    private int x;
    private int y;
    private int width;
    private int height;

    public ImageWidgetInfoObj() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.x);
        dest.writeInt(this.y);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    protected ImageWidgetInfoObj(Parcel in) {
        this.x = in.readInt();
        this.y = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Parcelable.Creator<ImageWidgetInfoObj> CREATOR = new Parcelable.Creator<ImageWidgetInfoObj>() {
        @Override
        public ImageWidgetInfoObj createFromParcel(Parcel source) {
            return new ImageWidgetInfoObj(source);
        }

        @Override
        public ImageWidgetInfoObj[] newArray(int size) {
            return new ImageWidgetInfoObj[size];
        }
    };
}