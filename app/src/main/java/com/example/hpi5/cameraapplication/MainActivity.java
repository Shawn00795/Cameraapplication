package com.example.hpi5.cameraapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity implements View.OnClickListener{

    ImageView iv;
    ImageButton ib;
    Button b;
    Bitmap bmp;
    final static int cameradata = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize()
    {
        iv = (ImageView) findViewById(R.id.ImageView);
        ib = (ImageButton) findViewById(R.id.ImageButton);
        b = (Button) findViewById(R.id.Button);
        ib.setOnClickListener(this);
        b.setOnClickListener(this);
        InputStream is = getResources().openRawResource(R.drawable.image);
        bmp = BitmapFactory.decodeStream(is);
    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Button:
                try{
                    getApplicationContext().setWallpaper(bmp);
                }
                catch(IOException e){
                    e.printStackTrace();
            }
                break;
            case R.id.ImageButton:
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameradata);
                break;
        }
    }
        @Override
        protected void onActivityResult(int requestCode,int resultCode,Intent data)
        {
            super.onActivityResult(requestCode,resultCode,data);
            if(resultCode==RESULT_OK){
                Bundle extras = data.getExtras();
                bmp = (Bitmap) extras.get("data");
                iv.setImageBitmap(bmp);
            }
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
