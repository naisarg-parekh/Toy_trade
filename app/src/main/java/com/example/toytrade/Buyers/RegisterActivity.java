package com.example.toytrade.Buyers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toytrade.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.santalu.maskedittext.MaskEditText;

import java.util.HashMap;

import soup.neumorphism.NeumorphCardView;

public class RegisterActivity extends AppCompatActivity {

    private MaskEditText rpn;
    NeumorphCardView CreateAccount;
    private EditText  rp,rn;
    private ProgressDialog loadingBar;
    private ImageButton back;
    private TextView loginnow,google;
    GoogleSignInClient  mGoogleSignInClient;
    public static final int RC_SIGN_IN=123;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccount = findViewById(R.id.CreateAccount);
        rpn = findViewById(R.id.register_ph_no_input);
        rp = (EditText) findViewById(R.id.register_pass_input);
        rn = (EditText) findViewById(R.id.register_username_input);
        loadingBar = new ProgressDialog(this);
        back = (ImageButton) findViewById(R.id.back);
        loginnow = (TextView)findViewById(R.id.login_now);

//        google = (TextView)findViewById(R.id.google_login);
//        mAuth = FirebaseAuth.getInstance();

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginAcitvity.class);
                startActivity(intent);
            }
        });

//        Googlesigninoption();




//        google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.google_login:
//                        signIn();
//                        break;
//                }
//            }
//        });
    }

//    private void Googlesigninoption()
//    {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//    }

//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account.getIdToken());
//
//                String userName = account.getDisplayName();
//                String userPhoto = account.getPhotoUrl().toString();
//                userPhoto = userPhoto+"?type=large";
//
//                SharedPreferences.Editor editor = getApplicationContext()
//                        .getSharedPreferences("MyPrefs",MODE_PRIVATE)
//                        .edit();
//                editor.putString("username", userName);
//                editor.putString("userPhoto", userPhoto);
//                editor.apply();
//            } catch (ApiException e) {
//
//                Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    private void firebaseAuthWithGoogle(String idToken) {
//
//        loadingBar.setTitle("Create Account");
//        loadingBar.setMessage("Please Wait, While We Check Your Credentials");
//        loadingBar.setCanceledOnTouchOutside(false);
//        loadingBar.show();
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            loadingBar.dismiss();
//
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
//                            startActivity(intent);
//
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user!=null){
//            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//            startActivity(intent);
//        }
//
//
//    }

    private void CreateAccount() {
        String name = rn.getText().toString();
        String phone = rpn.getText().toString();
        String password = rp.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }

        else{
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please Wait, While We Check Your Credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validatephonenumber(name, phone,password);
        }
    }

    private void validatephonenumber(final String name, final String phone, final String password) {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);

                    Rootref.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {

                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Congratulations, Your Account is Created!", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(RegisterActivity.this, LoginAcitvity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Network Error: Please Try Again.", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }
                else
                    {
                        Toast.makeText(RegisterActivity.this, "The number " + phone + " Already Exists!", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Please try again with another phone number.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}