package com.aheadle.toolsboard.TedBottomPicker.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aheadle.toolsboard.R;
import com.aheadle.toolsboard.TedBottomPicker.TedBottomPicker;
import com.aheadle.toolsboard.TedBottomPicker.view.TedSquareFrameLayout;
import com.aheadle.toolsboard.TedBottomPicker.view.TedSquareImageView;
import com.bumptech.glide.Glide;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * Created by Jing on 16/9/10.
 */
public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.GalleryViewHolder> {


    ArrayList<PickerTile> pickerTiles;
    Context context;
    TedBottomPicker.Builder builder;
    OnItemClickListener onItemClickListener;

    public ImageGalleryAdapter(Context context, TedBottomPicker.Builder builder) {

        this.context = context;
        this.builder = builder;

        pickerTiles = new ArrayList<PickerTile>();

        if (builder.showCamera) {
            pickerTiles.add(new PickerTile(PickerTile.CAMERA));
        }

        if (builder.showGallery) {
            pickerTiles.add(new PickerTile(PickerTile.GALLERY));
        }

        Cursor imageCursor = null;
        try {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.ImageColumns.ORIENTATION};
            final String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";


            imageCursor = context.getApplicationContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
            //imageCursor = sContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);


            if (imageCursor != null) {

                int count = 0;
                while (imageCursor.moveToNext() && count < builder.maxCount) {
                    String imageLocation = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File imageFile = new File(imageLocation);
                    pickerTiles.add(new PickerTile(Uri.fromFile(imageFile)));
                    count++;

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (imageCursor != null && !imageCursor.isClosed()) {
                imageCursor.close();
            }
        }


    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.grid_item, null);
        final GalleryViewHolder holder = new GalleryViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(final GalleryViewHolder holder, final int position) {

        PickerTile pickerTile = getItem(position);


        if (pickerTile.isCameraTile()) {
            holder.iv_thumbnail.setBackgroundResource(builder.cameraTileBackgroundResId);
            holder.iv_thumbnail.setImageDrawable(builder.cameraTileDrawable);
        } else if (pickerTile.isGalleryTile()) {
            holder.iv_thumbnail.setBackgroundResource(builder.galleryTileBackgroundResId);
            holder.iv_thumbnail.setImageDrawable(builder.galleryTileDrawable);

        } else {
            Uri uri = pickerTile.getImageUri();
            if (builder.imageProvider == null) {
                Glide.with(context)
                        .load(uri)
                        .thumbnail(0.1f)
                        .dontAnimate()
                        .centerCrop()
                        .placeholder(R.drawable.ic_gallery)
                        .error(R.drawable.img_error)
                        .into(holder.iv_thumbnail);
            } else {
                builder.imageProvider.onProvideImage(holder.iv_thumbnail, uri);
            }


        }


        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pickerTiles.size();
    }

    public PickerTile getItem(int position) {
        return pickerTiles.get(position);
    }

    public void setOnItemClickListener(
            OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }


    public static class PickerTile {

        public static final int IMAGE = 1;
        public static final int CAMERA = 2;
        public static final int GALLERY = 3;
        protected final Uri imageUri;
        protected final
        @TileType
        int tileType;

        PickerTile(@SpecialTileType int tileType) {
            this(null, tileType);
        }

        PickerTile(@NonNull Uri imageUri) {
            this(imageUri, IMAGE);
        }

        protected PickerTile(@Nullable Uri imageUri, @TileType int tileType) {
            this.imageUri = imageUri;
            this.tileType = tileType;
        }

        @Nullable
        public Uri getImageUri() {
            return imageUri;
        }

        @TileType
        public int getTileType() {
            return tileType;
        }

        public boolean isImageTile() {
            return tileType == IMAGE;
        }

        public boolean isCameraTile() {
            return tileType == CAMERA;
        }

        public boolean isGalleryTile() {
            return tileType == GALLERY;
        }

        @Override
        public String toString() {
            if (isImageTile()) {
                return "ImageTile: " + imageUri;
            } else if (isCameraTile()) {
                return "CameraTile";
            } else if (isGalleryTile()) {
                return "PickerTile";
            } else {
                return "Invalid item";
            }
        }

        @IntDef({IMAGE, CAMERA, GALLERY})
        @Retention(RetentionPolicy.SOURCE)
        public @interface TileType {
        }

        @IntDef({CAMERA, GALLERY})
        @Retention(RetentionPolicy.SOURCE)
        public @interface SpecialTileType {
        }
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {

        TedSquareFrameLayout root;


        TedSquareImageView iv_thumbnail;

        public GalleryViewHolder(View view) {
            super(view);
            root = (TedSquareFrameLayout) view.findViewById(R.id.root);
            iv_thumbnail = (TedSquareImageView) view.findViewById(R.id.iv_thumbnail);

        }

    }


}
