package com.edu.lzjtu.shopping.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.lzjtu.shopping.R;
import com.edu.lzjtu.shopping.db.ShopDB;
import com.edu.lzjtu.shopping.model.Shop;

public class ShopDepict extends AppCompatActivity {

    private ImageView imageView;
    private TextView name;
    private TextView say;
    private TextView price;
    private Button add;
    private Button sub;
    private TextView count;
    private Button addToCart;
    private static int c =0;
    private Intent intent;
    private Shop shop;
    private ShopDB shopdb;
    private int shopid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_depict);
        intent  =getIntent();
        shopid =intent.getIntExtra("shop",0);
        shopdb = ShopDB.getInstance(this);
        shop =shopdb.getShop(shopid);
        init();
    }

    private void init() {
        count = (TextView) findViewById(R.id.shopdepictcount);
        count.setText(c+"");
        imageView = (ImageView) findViewById(R.id.shopdepictimageview);
        imageView.setImageResource(shop.getImage());
        name = (TextView) findViewById(R.id.shopdepictname);
        name.setText(shop.getName());
        say = (TextView) findViewById(R.id.shopdepictsay);
        say.setText(shop.getSay());
        price  = (TextView) findViewById(R.id.shopdepictprice);
        price.setText(shop.getPrice()+"");
        add  = (Button) findViewById(R.id.shopdepictadd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
                count.setText(c+"");

            }
        });
        sub = (Button) findViewById(R.id.shopdepictsub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c--;
                count.setText(c+"");
            }
        });

        addToCart = (Button) findViewById(R.id.shopdepictaddtocart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c==0){
                    Toast.makeText(ShopDepict.this, "选择的数量为0", Toast.LENGTH_SHORT).show();
                    return;
                }
                shopdb.saveShoppingCart(0,shopid,c);
                Toast.makeText(ShopDepict.this, "加入购物车成功", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
