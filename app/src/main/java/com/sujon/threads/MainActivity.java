package com.sujon.threads;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button cameraButton;
    ImageView myImageView;

    public static final int CAMERA_REQUEST_CODE = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraButton = findViewById(R.id.button);
        myImageView = findViewById(R.id.imageView);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasSystemFeature()) {
                    cameraButton.setEnabled(false);
                } else {
                    Intent myIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    if (myIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(myIntent, CAMERA_REQUEST_CODE);

                    }

                }

            }
        });
    }

    public boolean hasSystemFeature() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap imageBitmap = (Bitmap) bundle.get("data");
            myImageView.setImageBitmap(imageBitmap);
        }
    }
}