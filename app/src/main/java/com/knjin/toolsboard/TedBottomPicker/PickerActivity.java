package com.knjin.toolsboard.TedBottomPicker;

import android.Manifest;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.aheadle.toolsboard.R;
import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class PickerActivity extends AppCompatActivity {

    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        iv_image = (ImageView) findViewById(R.id.iv_image);

        Button btn_show = (Button) findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        TedBottomPicker bottomSheetDialogFragment = new TedBottomPicker.Builder(PickerActivity.this)
                                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                                    @Override
                                    public void onImageSelected(Uri uri) {

                                        Glide.with(PickerActivity.this)
                                                //.load(uri.toString())
                                                .load(uri)
                                                .into(iv_image);

                                    }
                                })
                                .setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                                .create();

                        bottomSheetDialogFragment.show(getSupportFragmentManager());

                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(PickerActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }
                };


                new TedPermission(PickerActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();

            }
        });

    }

}
