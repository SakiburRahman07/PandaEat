package com.example.pandaeat;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Delivery_Login extends AppCompatActivity {

    TextInputLayout email,pass;
    AppCompatButton Signin,Signinphone;
    TextView Forgotpassword , signup;
    FirebaseAuth Fauth;
    String emailid,pwd;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().hide();
        Forgotpassword = (TextView)findViewById(R.id.Dforgotpass);





        try{

            email = (TextInputLayout)findViewById(R.id.Demail);
            pass = (TextInputLayout)findViewById(R.id.Dpassword);
            Signin = (AppCompatButton) findViewById(R.id.Loginbtn);
            signup = (TextView) findViewById(R.id.donot);

            Signinphone = (AppCompatButton) findViewById(R.id.Dbtnphone);



            Fauth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    emailid = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if(isValid()){

                        final ProgressDialog mDialog = new ProgressDialog(Delivery_Login.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Sign In Please Wait.......");
                        mDialog.show();

                        Fauth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    mDialog.dismiss();


                                    String user11 = Fauth.getUid().toString();

                                    databaseReference.child("User").child(user11).child("Role").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            String sakib = snapshot.getValue(String.class).toString();


                                            if(sakib.equals("DeliveryPerson"))
                                            {
                                                                                    if(Fauth.getCurrentUser().isEmailVerified()){
                                        mDialog.dismiss();
                                        Toast.makeText(Delivery_Login.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                        Intent Z = new Intent(Delivery_Login.this,DeliveryFoodPanel_BottomNavigation.class);
                                        startActivity(Z);
                                        finish();

                                    }else{
                                        ReusableCodeForAll.ShowAlert(Delivery_Login.this,"Verification Failed","You Have Not Verified Your Email");

                                    }
                                            }
                                            else
                                            {
                                                FirebaseAuth.getInstance().signOut();
                                                Intent intent = new Intent(Delivery_Login.this, MainMenu.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                              //  ReusableCodeForAll.ShowAlert(MainMenu.,"Error","This email is already registered with customer or delivery man.");
                                                Toast.makeText(Delivery_Login.this, "This email is already registered with customer or chef.", Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });



//                                    if(Fauth.getCurrentUser().isEmailVerified()){
//                                        mDialog.dismiss();
//                                        Toast.makeText(Delivery_Login.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
//                                        Intent Z = new Intent(Delivery_Login.this,DeliveryFoodPanel_BottomNavigation.class);
//                                        startActivity(Z);
//                                        finish();
//
//                                    }else{
//                                        ReusableCodeForAll.ShowAlert(Delivery_Login.this,"Verification Failed","You Have Not Verified Your Email");
//
//                                    }
                                }else{
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(Delivery_Login.this,"Error",task.getException().getMessage());
                                }
                            }
                        });
                    }
                }
            });
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Delivery_Login.this,Delivery_Registration.class));
                    finish();
                }
            });
            Forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Delivery_Login.this,ForgotPassword.class));
                    finish();
                }
            });
            Signinphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Delivery_Login.this,MainMenu.class));
                    finish();
                }
            });
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    String emailpattern  = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid(){

        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalid=false,isvalidemail=false,isvalidpassword=false;
        if(TextUtils.isEmpty(emailid)){
            email.setErrorEnabled(true);
            email.setError("Email is required");
        }else{
            if(emailid.matches(emailpattern)){
                isvalidemail=true;
            }else{
                email.setErrorEnabled(true);
                email.setError("Invalid Email Address");
            }
        }
        if(TextUtils.isEmpty(pwd)){

            pass.setErrorEnabled(true);
            pass.setError("Password is Required");
        }else{
            isvalidpassword=true;
        }
        isvalid=(isvalidemail && isvalidpassword)?true:false;
        return isvalid;
    }
}