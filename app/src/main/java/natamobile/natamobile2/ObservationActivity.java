package natamobile.natamobile2;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import natamobile.natamobile2.Entities.Observation;
import natamobile.natamobile2.GoogleCloud.StorageUtil;
import natamobile.natamobile2.RestCommunication.OnLoopjCompleted;
import natamobile.natamobile2.RestCommunication.UseraccountRest;


public class ObservationActivity  extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    public static final int REQUEST_IMAGE_CAPTURE = 5;
    public static final int RESQUEST_VIDEO_CAPTURE = 10;
    private static final int PERMISSION_REQUEST_CODE = 15;
    private File photoFile;
    private Observation observation = new Observation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        observation = (Observation) intent.getSerializableExtra("Observation");
        if (observation == null) observation = new Observation();

        setContentView(R.layout.activity_observation);
        UseraccountRest.getImage(UseraccountRest.URL_BIRD,"pigeon.jpg",new OnLoopjCompleted() {
            @Override
            public void taskCompleted(String results) {
                loadBirdBtn(results);
            }
        });
    }

    public void takePicture(View view) {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                showCamAndTakePicture();
            } else {
                requestPermission(); // Code for permission
            }
        }
        else
        {

            showCamAndTakePicture();
        }


    }

    public void recordAudio(View view) {

    }

    public void recordVideo(View view) {

    }

    public void cancel(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    public void addObservation(View view) {
        Intent intent = new Intent();
        intent.putExtra("Observation", observation);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void selectBird(View view) {
    }
    private void loadBirdBtn(String url){
        ImageButton birdBtn = (ImageButton)findViewById(R.id.imageButtonChoosedBird);
        Picasso.with(this).load(url).into(birdBtn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout_medias);
            View view = LayoutInflater.from(this).inflate(R.layout.frame_layout_media, null);
            linearLayout.addView(view);
            TextView pseudoDate=(TextView)view.findViewById(R.id.txt_view_media_date);
            pseudoDate.setText(new Date().toString());
            TextView date=(TextView)view.findViewById(R.id.txt_view_content_type);
            date.setText("photo");
            ImageView imageView = (ImageView) findViewById(R.id.ImageViewBird);
            imageView.setAdjustViewBounds(true);
            Picasso.with(this).load(new File(photoFile.getAbsolutePath())).into(imageView);
            new Thread(new Runnable() {
                public void run() {
                    uploadFile("image/jgep");
                }
            }).start();
            Toast toast = Toast.makeText(getApplicationContext(), ""+photoFile.length() / (1024 * 1024)+" Mb", Toast.LENGTH_SHORT);
            toast.show();


        }
        if (requestCode == RESQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {

        }
    }

    private File createImageFile() throws IOException {

         //  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
          //  String imageFileName = "JPEG_" + timeStamp + "_";
           // File image = new File(Environment.getExternalStorageDirectory() + File.separator + imageFileName+".jpg");
           // image.createNewFile();

        // Create an image file name

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents

        return image;
    }
    public  void showCamAndTakePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
    private void uploadFile(String contentType){

        StorageUtil.uploadFile(this,"rogver-bird",photoFile.getPath(),contentType);
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showCamAndTakePicture();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Vous ne pouvez pas prendre de photo sans l'authorisation", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

}
