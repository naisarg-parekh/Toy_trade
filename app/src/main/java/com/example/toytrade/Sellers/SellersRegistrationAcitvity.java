package com.example.toytrade.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toytrade.Buyers.LoginAcitvity;
import com.example.toytrade.Buyers.MainActivity;
import com.example.toytrade.R;
import com.firebase.ui.database.paging.FirebaseDataSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SellersRegistrationAcitvity extends AppCompatActivity {
    private TextView SellerAlreadyHaveAccount;
    private Button registerButton;
    private EditText nameInput,phoneInput, emailInput, passwordInput, addressInput;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellers_registration_acitvity);
        SellerAlreadyHaveAccount = (TextView)findViewById(R.id.seller_already_have_account_btn);
        registerButton = (Button)findViewById(R.id.seller_register_btn);
        nameInput = (EditText)findViewById(R.id.seller_name);
        phoneInput = (EditText)findViewById(R.id.seller_phone);
        emailInput= (EditText)findViewById(R.id.seller_email);
        passwordInput = (EditText)findViewById(R.id.seller_password);
        addressInput = (EditText)findViewById(R.id.seller_address);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellersRegistrationAcitvity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        SellerAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellersRegistrationAcitvity.this, SellerLoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerSeller();

            }
        });
    }

    private void registerSeller()
    {
        String name = nameInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String address = addressInput.getText().toString();

        if(!name.equals("") && !phone.equals("")&& !email.equals("") && !password.equals("") && !address.equals(""))
        {
            loadingBar.setTitle("Creating Sellers Account");
            loadingBar.setMessage("Please Wait, While We Check Your Credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                final DatabaseReference rootRef;
                                rootRef  = FirebaseDatabase.getInstance().getReference();

                                String sid = mAuth.getCurrentUser().getUid();

                                HashMap<String, Object> sellerMap = new HashMap<>();
                                sellerMap.put("sid",sid);
                                sellerMap.put("phone",phone);
                                sellerMap.put("email",email);
                                sellerMap.put("address",address);
                                sellerMap.put("name",name);
                                sellerMap.put("password",password);

                                rootRef.child("Sellers").child(sid).updateChildren(sellerMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>()
                                        {

                                            @Override
                                            public void onComplete(@NonNull Task<Void> task)
                                            {
                                                loadingBar.dismiss();
                                                Toast.makeText(SellersRegistrationAcitvity.this, "You are Registered Successfully", Toast.LENGTH_SHORT).show();

                                                Intent intent= new Intent(SellersRegistrationAcitvity.this, SellersHomeActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });

                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(SellersRegistrationAcitvity.this, "Please Fill Out all Details", Toast.LENGTH_SHORT).show();
        }

    }
}