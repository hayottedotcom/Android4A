package fr.esiea.android.projet.mobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class RecetteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);
        ImageView imgRecette = (ImageView) findViewById(R.id.imageViewRecette);
        TextView txtRecette=(TextView)findViewById(R.id.titrerecette);
        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
        imgRecette.setImageBitmap(bmp);
        txtRecette.setText(getIntent().getStringExtra("titre"));
    }

}
