package com.mrrobot.harunuyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    ImageView imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    ImageView[] imageArray;
    int skor;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageArray=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        skor=0;
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {

                textView.setText("Time: "+l/1000);
            }

            @Override
            public void onFinish() {
                textView.setText("Time Off");
                handler.removeCallbacks(runnable);
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);//Bütün resimleri görünmez yapacaktır.
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Yeniden?");
                alert.setMessage("Yeniden oynamak istediğine emin misin?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Oyun Bitti!",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();
        resimleriSakla();
    }
    public void tıkla(View view){
        skor++;
        textView2.setText("Skor: "+skor);
    }

    public void resimleriSakla(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);//Bütün resimleri görünmez yapacaktır.
                }
                Random rnd=new Random();
                int i =rnd.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);

    }
}