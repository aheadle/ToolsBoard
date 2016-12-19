package com.knjin.toolsboard.WeChatImage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jing on 16/12/19.
 */

public class ImageInfoObj implements Parcelable {
    private String imageUrl;
    private int imageWidth;
    private int imageHeight;

    public ImageInfoObj() {

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeInt(this.imageWidth);
        dest.writeInt(this.imageHeight);
    }

    protected ImageInfoObj(Parcel in) {
        this.imageUrl = in.readString();
        this.imageWidth = in.readInt();
        this.imageHeight = in.readInt();
    }

    public static final Parcelable.Creator<ImageInfoObj> CREATOR = new Parcelable.Creator<ImageInfoObj>() {
        @Override
        public ImageInfoObj createFromParcel(Parcel source) {
            return new ImageInfoObj(source);
        }

        @Override
        public ImageInfoObj[] newArray(int size) {
            return new ImageInfoObj[size];
        }
    };
}
