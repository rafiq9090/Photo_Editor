package com.example.photoeditor;

import static com.example.photoeditor.StartActivity.mUri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.example.photoeditor.databinding.ActivityFinalBinding;

public class FinalActivity extends AppCompatActivity {

    ActivityFinalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        binding = ActivityFinalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (!binding.imgaeView.equals("")){
            startActivity(new Intent(FinalActivity.this,StartActivity.class));
            finish();
        }



        Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);

        dsPhotoEditorIntent.setData(mUri);
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "your_photo");
        int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};

        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);


        //startActivityForResult(dsPhotoEditorIntent, 200);
        startActivityIfNeeded(dsPhotoEditorIntent,200);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case 200:

                    Uri outputUri = data.getData();

                    // handle the result uri as you want, such as display it in an imageView;

                    // imageView.setImageURI(outputUri);
                    binding.imgaeView.setImageURI(outputUri);

                    break;

            }

        }

    }
}