package com.example.toytrade.Buyers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toytrade.Admin.AdminHomeActivity;
import com.example.toytrade.R;
import com.example.toytrade.Sellers.SellerProductCategory1Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import Model.Users;
import Prevalent.Prevalent;
import io.paperdb.Paper;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class LoginAcitvity extends AppCompatActivity {

    private EditText ph_no_input,pass_input;
    private NeumorphButton login_now;
    private ProgressDialog loadingBar;
    private String ParentDbname= "Users";
    private CheckBox chkBoxRememberMe;
    private TextView AdminLink,ForgetPasswordLink;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        ph_no_input = (EditText)findViewById(R.id.ph_no_input);
        pass_input = (EditText)findViewById(R.id.pass_input);
        login_now =  findViewById(R.id.login_now);
        loadingBar = new ProgressDialog(this);
        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chkb);
        AdminLink = (TextView) findViewById(R.id.admin_link);
        ForgetPasswordLink = (TextView)findViewById(R.id.forget_password_link);
        back = (ImageButton) findViewById(R.id.back);
        Paper.init(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAcitvity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });

        ForgetPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAcitvity.this, ResetPasswordActivity.class);
                intent.putExtra("check", "login");
                startActivity(intent);
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                login_now.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                ParentDbname = "Admins";
            }
        });


    }

    private void loginuser() {
        String phone = ph_no_input.getText().toString();
        String password = pass_input.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please Wait, While We Check Your Credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone,password);


        }
    }

    private void AllowAccessToAccount(String phone, String password) {

        if(chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(ParentDbname).child(phone).exists()){
                    Users userdata = dataSnapshot.child(ParentDbname).child(phone).getValue(Users.class);

                    if(userdata.getPhone().equals(phone)){

                        if(userdata.getPassword().equals(password)){

                            if (ParentDbname.equals("Admins"))
                            {
                                Toast.makeText(LoginAcitvity.this, "Welcome Admin, you are logged in Successfully..." , Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();


                                Intent intent = new Intent(LoginAcitvity.this, AdminHomeActivity.class);
                                startActivity(intent);
                            }
                            else if (ParentDbname.equals("Users"))
                            {

                                Toast.makeText(LoginAcitvity.this, "logged in Successfully..." , Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginAcitvity.this, HomeActivity.class);
                                Prevalent.currentOnlineUser = userdata;
                                startActivity(intent);
                            }

                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginAcitvity.this, "Password Incorrect" , Toast.LENGTH_SHORT).show();
                        }

                    }

                }
                else{
                    Toast.makeText(LoginAcitvity.this, "Account with this " + phone + " number does not exists." , Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(LoginAcitvity.this, "You Need To Create Account." , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}