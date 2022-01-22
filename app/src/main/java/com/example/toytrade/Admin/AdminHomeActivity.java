package com.example.toytrade.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.toytrade.Buyers.HomeActivity;
import com.example.toytrade.Buyers.MainActivity;
import com.example.toytrade.R;
import com.example.toytrade.Sellers.SellerProductCategory1Activity;

public class AdminHomeActivity extends AppCompatActivity {

    private Button Check_order_btn,maintainProduct,checkApproveProductsbtn;
    private ImageButton Admin_logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Check_order_btn = (Button)findViewById(R.id.Check_order_btn);
        maintainProduct = (Button)findViewById(R.id.maintain_btn);
        Admin_logout_btn = (ImageButton)findViewById(R.id.Admin_logout_btn);
        checkApproveProductsbtn = (Button)findViewById(R.id.Approve_order_btn);

        maintainProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, HomeActivity.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);
                finish();
            }
        });

        Check_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminNewOrders.class);
                startActivity(intent);
            }
        });

        Admin_logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        checkApproveProductsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminCheckNewProductsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}