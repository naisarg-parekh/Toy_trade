package com.example.toytrade.Buyers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toytrade.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import Prevalent.Prevalent;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText name,phone,address,city;
    private Button confirm;

    private String totamount = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        totamount = getIntent().getStringExtra("Total Price");

        name = (EditText) findViewById(R.id.shippment_name);
        phone = (EditText) findViewById(R.id.shippment_phone);
        address = (EditText) findViewById(R.id.shippment_address);
        city = (EditText) findViewById(R.id.shippment_city);
        confirm = (Button)findViewById(R.id.shippment_confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }

    private void check()
    {
        if(TextUtils.isEmpty(name.getText().toString())){
            Toast.makeText(ConfirmFinalOrderActivity.this , "Please provide Your Full Name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone.getText().toString())){
            Toast.makeText(ConfirmFinalOrderActivity.this , "Please provide Your Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(address.getText().toString())){
            Toast.makeText(ConfirmFinalOrderActivity.this , "Please provide Your Address", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(name.getText().toString())){
            Toast.makeText(ConfirmFinalOrderActivity.this , "Please provide Your City", Toast.LENGTH_SHORT).show();
        }
        else{
            confirmorder();
        }
    }

    private void confirmorder()
    {
        final String saveCurrentTime, saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(Prevalent.currentOnlineUser.getPhone());

        HashMap<String, Object> ordersMap = new HashMap<>();
        ordersMap.put("TotalAmount", totamount);
        ordersMap.put("Name", name.getText().toString());
        ordersMap.put("Phone", phone.getText().toString());
        ordersMap.put("Address", address.getText().toString());
        ordersMap.put("City", city.getText().toString());
        ordersMap.put("date", saveCurrentDate);
        ordersMap.put("time", saveCurrentTime);
        ordersMap.put("State", "Not Shipped");

        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>(){

            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
               if(task.isSuccessful()){
                   FirebaseDatabase.getInstance().getReference().child("Cart List")
                           .child("User View")
                           .child(Prevalent.currentOnlineUser.getPhone())
                           .removeValue()
                           .addOnCompleteListener(new OnCompleteListener<Void>(){

                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){

                                       Toast.makeText(ConfirmFinalOrderActivity.this, "Your Final Order Has Been Placed Successfully", Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(ConfirmFinalOrderActivity.this, HomeActivity.class);
                                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                       startActivity(intent);
                                       finish();
                                   }
                               }
                           });
               }
            }
        });
    }


}