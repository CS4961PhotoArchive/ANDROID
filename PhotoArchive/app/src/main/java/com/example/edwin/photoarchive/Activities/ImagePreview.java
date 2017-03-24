package com.example.edwin.photoarchive.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
<<<<<<< HEAD:PhotoArchive/app/src/main/java/com/example/edwin/photoarchive/Activities/ImagePreview.java
import com.example.edwin.photoarchive.R;
=======
import android.graphics.Matrix;
import android.view.ScaleGestureDetector;
import android.view.MotionEvent;

>>>>>>> 873f1be01e0615d8176daea9d2d68c4710ac8992:PhotoArchive/app/src/main/java/com/example/edwin/photoarchive/ImagePreview.java

public class ImagePreview extends AppCompatActivity {
    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        img = (ImageView) findViewById(R.id.imageViewPreview);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String imagePath = extras.getString("imagePath");
            Glide.with(this).load(imagePath).into(img);

        }

    }

    //Gesture listener
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        scaleGestureDetector.onTouchEvent(ev);
        return true;
    }

    // Scaling Code
    private class ScaleListener extends ScaleGestureDetector.
            SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f));
            matrix.setScale(scaleFactor, scaleFactor);
            img.setImageMatrix(matrix);
            return true;
        }


    }
}
