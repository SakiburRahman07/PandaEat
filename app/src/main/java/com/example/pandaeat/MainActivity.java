package com.example.pandaeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {



    FirebaseAuth Fauth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView name, slogan;
    ImageView logo;
    View topView1, topView2, topView3, bottomView1, bottomView2,bottomView3;

    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                View.SYSTEM_UI_FLAG_FULLSCREEN|
                View.SYSTEM_UI_FLAG_IMMERSIVE);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        slogan=findViewById(R.id.slogan);

        logo=findViewById(R.id.logo);

        topView1=findViewById(R.id.topView1);
        topView2=findViewById(R.id.topView2);
        topView3=findViewById(R.id.topView3);

        bottomView1=findViewById(R.id.bottomView1);
        bottomView2=findViewById(R.id.bottomView2);
        bottomView3=findViewById(R.id.bottomView3);

        Animation logoAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomanimation);
        Animation nameAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomanimation);

        Animation topview1Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.top_views_animation);
        Animation topview2Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.top_views_animation);
        Animation topview3Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.top_views_animation);

        Animation bottomview1Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_views_animation);
        Animation bottomview2Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_views_animation);
        Animation bottomview3Animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_views_animation);

        topView1.setAnimation(topview1Animation);
        bottomView1.setAnimation(bottomview1Animation);

        topview1Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topView2.setVisibility(View.VISIBLE);
                bottomView2.setVisibility(View.VISIBLE);

                topView2.startAnimation(topview2Animation);
                bottomView2.startAnimation(bottomview2Animation);



            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topview2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topView3.setVisibility(View.VISIBLE);
                bottomView3.setVisibility(View.VISIBLE);

                topView3.startAnimation(topview3Animation);
                bottomView3.startAnimation(bottomview3Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        topview3Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            logo.setVisibility(View.VISIBLE);
            logo.startAnimation(logoAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                name.setVisibility(View.VISIBLE);
                name.startAnimation(nameAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        nameAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            slogan.setVisibility(View.VISIBLE);
            final String animetext = slogan.getText().toString();
            slogan.setText("");
            count=0;

            new CountDownTimer(animetext.length()*100, 100)
            {

                @Override
                public void onTick(long millisUntilFinished) {
                slogan.setText(slogan.getText().toString()+animetext.charAt(count));
                count++;
                }

                @Override
                public void onFinish() {

                }
            }.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

       /* imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textview);

        imageView.animate().alpha(0f).setDuration(0);
        textView.animate().alpha(0f).setDuration(0);

        imageView.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                textView.animate().alpha(1f).setDuration(800);

            }
        });*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Fauth = FirebaseAuth.getInstance();
                if(Fauth.getCurrentUser()!=null)
                {
                    if(Fauth.getCurrentUser().isEmailVerified()){
                        Fauth=FirebaseAuth.getInstance();

                        databaseReference=FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getUid()+"/Role");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String role = snapshot.getValue(String.class);
                                if(role.equals("Chef"))
                                {
                                    startActivity(new Intent(MainActivity.this, ChefFoodPanel_BottomNavigation.class));
                                    finish();
                                }
                                if(role.equals("Customer"))
                                {
                                    startActivity(new Intent(MainActivity.this, CustomerFoofPanel_BottomNavigation.class));
                                    finish();
                                }
                                if(role.equals("DeliveryPerson"))
                                {
                                    startActivity(new Intent(MainActivity.this, DeliveryFoodPanel_BottomNavigation.class));
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Check whether you have verified your details, Otherwise please verify");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                                startActivity(intent);
                                finish();

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        Fauth.signOut();
                    }
                }else {

                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                }
            }
        },5500);

    }
}