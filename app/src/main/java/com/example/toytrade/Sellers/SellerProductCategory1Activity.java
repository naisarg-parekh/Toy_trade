package com.example.toytrade.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.toytrade.Buyers.MainActivity;
import com.example.toytrade.R;

public class SellerProductCategory1Activity extends AppCompatActivity {

    private ImageView For0to18m,For18to36m,For3to5y;
    private ImageView For5to7y, For7to9y,For9to12y;
    private ImageView For12plusy;
    private ImageButton SellerLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_category1);

        For0to18m = (ImageView) findViewById(R.id.For0to18m);
        For18to36m = (ImageView) findViewById(R.id.For18to36m);
        For3to5y = (ImageView) findViewById(R.id.For3to5y);
        For5to7y = (ImageView) findViewById(R.id.For5to7y);
        For7to9y = (ImageView) findViewById(R.id.For7to9y);
        For9to12y = (ImageView) findViewById(R.id.For9to12y);
        For12plusy = (ImageView) findViewById(R.id.For12yplus);
        SellerLogout = (ImageButton) findViewById(R.id.Seller_logout_btn);


        For0to18m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategory1Activity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "For 0-18 Months");
                startActivity(intent);
            }
        });

        For18to36m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategory1Activity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "For 18-36 Months");
                startActivity(intent);
            }
        });

        For3to5y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategory1Activity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "For 3-5 Years");
                startActivity(intent);
            }
        });

        For5to7y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategory1Activity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "For 5-7 Years");
                startActivity(intent);
            }
        });

        For7to9y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategory1Activity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "For 7-9 Years");
                startActivity(intent);
            }
        });

        For9to12y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategory1Activity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "For 9-12 Years");
                startActivity(intent);
            }
        });

        For12plusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategory1Activity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "For 12+ Years");
                startActivity(intent);

            }
        });

        SellerLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategory1Activity.this, SellersHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}