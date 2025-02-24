package com.example.pandaeat;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;



public class ChefRegistration extends AppCompatActivity {
 //   String[] Maharashtra = {"Mumbai","Pune","Nashik"};
   // String[] Madhyapradesh = {"Bhopal","Indore","Ujjain"};
    String[] Barisal =    {"Barguna",  "Barisal",        "Bhola",    "Jhalokati",  "Patuakhali", "Pirojpur"};
    String[] Chittagong={"Bandarban","Brahmanbaria",   "Chandpur", "Chittagong", "Comilla",    "Cox's Bazar","Feni",     "Khagrachhari","Lakshmipur", "Noakhali", "Rangamati"};
    String[] Dhaka={"Dhaka",    "Faridpur",       "Gazipur",  "Gopalganj",  "Kishoreganj","Madaripur",  "Manikganj","Munshiganj",  "Narayanganj","Narsingdi","Rajbari","Shariatpur","Tangail"};
    String[] Khulna = {"Bagerhat", "Chuadanga",      "Jessore",  "Jhenaidah",  "Khulna",     "Kushtia",    "Magura",   "Meherpur",    "Narail",     "Satkhira"};
    String[] Mymensingh={"Jamalpur", "Mymensingh",     "Netrakona","Sherpur"};
    String[] Rajshahi=  {"Bogra",    "Chapainawabganj","Joypurhat","Naogaon",    "Natore",     "Pabna",      "Rajshahi", "Sirajganj"};
    String[] Rangpur={"Dinajpur", "Gaibandha",      "Kurigram", "Lalmonirhat","Nilphamari", "Panchagarh", "Rangpur",  "Thakurgaon"};
    String[] Sylhet={"Habiganj", "Moulvibazar",    "Sunamganj","Sylhet"};

    TextInputLayout Fname,Lname,Email,Pass,cpass,mobileno,houseno,area,pincode;
    Spinner Statespin,Cityspin;
    AppCompatButton signup, Emaill, Phone;
  //  CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname,lname,emailid,password,confpassword,mobile,house,Area,Pincode,statee,cityy;
    String role="Chef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_registration);

        Fname = (TextInputLayout)findViewById(R.id.Firstname);
        Lname = (TextInputLayout)findViewById(R.id.Lastname);
        Email = (TextInputLayout)findViewById(R.id.Email);
        Pass = (TextInputLayout)findViewById(R.id.Pwd);
        cpass = (TextInputLayout)findViewById(R.id.Cpass);
        mobileno = (TextInputLayout)findViewById(R.id.Mobileno);
        houseno = (TextInputLayout)findViewById(R.id.houseNo);
        pincode = (TextInputLayout)findViewById(R.id.Pincode);
        Statespin = (Spinner) findViewById(R.id.Statee);
        Cityspin = (Spinner) findViewById(R.id.Citys);
        area = (TextInputLayout)findViewById(R.id.Area);

        signup = (AppCompatButton) findViewById(R.id.Signup);
        Emaill = (AppCompatButton) findViewById(R.id.email);
        Phone = (AppCompatButton) findViewById(R.id.phone);

      //  Cpp = (CountryCodePicker)findViewById(R.id.CountryCode);

        Statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object value = parent.getItemAtPosition(position);
                statee = value.toString().trim();
                if(statee.equals("Barisal")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Barisal){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegistration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if(statee.equals("Chittagong")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Chittagong){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegistration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if(statee.equals("Dhaka")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Dhaka){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegistration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if(statee.equals("Khulna")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Khulna){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegistration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if(statee.equals("Mymensingh")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Mymensingh){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegistration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if(statee.equals("Rajshahi")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Rajshahi){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegistration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if(statee.equals("Rangpur")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Rangpur){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegistration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if(statee.equals("Sylhet")){
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Sylhet){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegistration.this,android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Cityspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                cityy = value.toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        databaseReference = firebaseDatabase.getInstance().getReference("Chef");
        FAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = Fname.getEditText().getText().toString().trim();
                lname = Lname.getEditText().getText().toString().trim();
                emailid = Email.getEditText().getText().toString().trim();
                mobile = mobileno.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();
                confpassword = cpass.getEditText().getText().toString().trim();
                Area = area.getEditText().getText().toString().trim();
                house = houseno.getEditText().getText().toString().trim();
                Pincode = pincode.getEditText().getText().toString().trim();

                if (isValid()){
                    final ProgressDialog mDialog = new ProgressDialog(ChefRegistration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait......");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String , String> hashMap = new HashMap<>();
                                hashMap.put("Role",role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        HashMap<String , String> hashMap1 = new HashMap<>();
                                        hashMap1.put("Mobile No",mobile);
                                        hashMap1.put("Fname",fname);
                                        hashMap1.put("Lname",lname);
                                        hashMap1.put("EmailId",emailid);
                                        hashMap1.put("City",cityy);
                                        hashMap1.put("Area",Area);
                                        hashMap1.put("Password",password);
                                        hashMap1.put("Pincode",Pincode);
                                        hashMap1.put("State",statee);
                                        hashMap1.put("Confirm Password",confpassword);
                                        hashMap1.put("House",house);

                                        firebaseDatabase.getInstance().getReference("Chef")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        mDialog.dismiss();

                                                        FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if(task.isSuccessful()){
                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(ChefRegistration.this);
                                                                    builder.setMessage("You Have Registered! Make Sure To Verify Your Email");
                                                                    builder.setCancelable(false);
                                                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                            dialog.dismiss();

                                                                            String phonenumber = "+88" + mobile;
                                                                            Intent b = new Intent(ChefRegistration.this,ChefVerifyPhone.class);
                                                                            b.putExtra("phonenumber",phonenumber);
                                                                            startActivity(b);

                                                                        }
                                                                    });
                                                                    AlertDialog Alert = builder.create();
                                                                    Alert.show();
                                                                }else{
                                                                    mDialog.dismiss();
                                                                    ReusableCodeForAll.ShowAlert(ChefRegistration.this,"Error",task.getException().getMessage());
                                                                }
                                                            }
                                                        });

                                                    }
                                                });

                                    }
                                });
                            }else {
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(ChefRegistration.this,"Error",task.getException().getMessage());
                            }
                        }
                    });
                }
//
            }
        });

        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(ChefRegistration.this, Cheflogin.class));
              finish();
            }
        });

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChefRegistration.this, MainMenu.class));
                finish();
            }
        });


    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid(){
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        mobileno.setErrorEnabled(false);
        mobileno.setError("");
        cpass.setErrorEnabled(false);
        cpass.setError("");
        area.setErrorEnabled(false);
        area.setError("");
        houseno.setErrorEnabled(false);
        houseno.setError("");
        pincode.setErrorEnabled(false);
        pincode.setError("");

        boolean isValid=false,isValidhouseno=false,isValidlname=false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false,isValidarea=false,isValidpincode=false;
        if(TextUtils.isEmpty(fname)){
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");
        }else{
            isValidname = true;
        }
        if(TextUtils.isEmpty(lname)){
            Lname.setErrorEnabled(true);
            Lname.setError("Enter Last Name");
        }else{
            isValidlname = true;
        }
        if(TextUtils.isEmpty(emailid)){
            Email.setErrorEnabled(true);
            Email.setError("Email Is Required");
        }else{
            if(emailid.matches(emailpattern)){
                isValidemail = true;
            }else{
                Email.setErrorEnabled(true);
                Email.setError("Enter a Valid Email Id");
            }
        }
        if(TextUtils.isEmpty(password)){
            Pass.setErrorEnabled(true);
            Pass.setError("Enter Password");
        }else{
            if(password.length()<8){
                Pass.setErrorEnabled(true);
                Pass.setError("Password is Weak");
            }else{
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(confpassword)){
            cpass.setErrorEnabled(true);
            cpass.setError("Enter Password Again");
        }else{
            if(!password.equals(confpassword)){
                cpass.setErrorEnabled(true);
                cpass.setError("Password Dosen't Match");
            }else{
                isValidconfpassword = true;
            }
        }
        if(TextUtils.isEmpty(mobile)){
            mobileno.setErrorEnabled(true);
            mobileno.setError("Mobile Number Is Required");
        }else{
            if(mobile.length()<10){
                mobileno.setErrorEnabled(true);
                mobileno.setError("Invalid Mobile Number");
            }else{
                isValidmobilenum = true;
            }
        }
        if(TextUtils.isEmpty(Area)){
            area.setErrorEnabled(true);
            area.setError("Area Is Required");
        }else{
            isValidarea = true;
        }
        if(TextUtils.isEmpty(Pincode)){
            pincode.setErrorEnabled(true);
            pincode.setError("Please Enter Pincode");
        }else{
            isValidpincode = true;
        }
        if(TextUtils.isEmpty(house)){
            houseno.setErrorEnabled(true);
            houseno.setError("Fields Can't Be Empty");
        }else{
            isValidhouseno = true;
        }

        isValid = (isValidarea && isValidconfpassword && isValidpassword && isValidpincode && isValidemail && isValidmobilenum && isValidname && isValidhouseno && isValidlname) ? true : false;
        return isValid;


    }
}