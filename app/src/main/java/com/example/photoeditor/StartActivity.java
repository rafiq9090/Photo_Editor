package com.example.photoeditor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.photoeditor.databinding.ActivityStartBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class StartActivity extends AppCompatActivity {

    ActivityStartBinding binding;

    public static Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.with(StartActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       try {
           mUri = data.getData();
           if (!mUri.equals("")){
               startActivity(new Intent(StartActivity.this,FinalActivity.class));
           }
       }catch (Exception e){
           e.getStackTrace();
       }
    }
}