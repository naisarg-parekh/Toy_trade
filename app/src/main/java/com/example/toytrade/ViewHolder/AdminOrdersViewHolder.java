package com.example.toytrade.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toytrade.R;

public class AdminOrdersViewHolder extends RecyclerView.ViewHolder {
    public TextView userName, userPhoneNumber, userTotalAmount, userAddressCity, userDateTime;
    public Button ShowOrderBtn;

    public AdminOrdersViewHolder(@NonNull View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.orderusername);
        userPhoneNumber = itemView.findViewById(R.id.orderphonenumber);
        userTotalAmount = itemView.findViewById(R.id.ordertotalprice);
        userAddressCity = itemView.findViewById(R.id.orderaddresscity);
        userDateTime = itemView.findViewById(R.id.orderdatetime);
        ShowOrderBtn = itemView.findViewById(R.id.ordershowbtn);
    }
}
