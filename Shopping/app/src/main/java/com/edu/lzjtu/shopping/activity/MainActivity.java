package com.edu.lzjtu.shopping.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.edu.lzjtu.shopping.R;
import com.edu.lzjtu.shopping.db.ShopDB;
import com.edu.lzjtu.shopping.model.AllShopFragment;
import com.edu.lzjtu.shopping.model.Shop;
import com.edu.lzjtu.shopping.model.ShoppingCartFragment;
import com.edu.lzjtu.shopping.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FragmentManager manager;
    private Button btnAllShop;
    private Button btnMyShop;
    private ShopDB shopDB;
    private List<Shop> shops;
    public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shopDB = ShopDB.getInstance(MainActivity.this);
        shops = new ArrayList<>();
        manager = getSupportFragmentManager();
        SharedPreferences sharedPreferences = getSharedPreferences("flag",MODE_PRIVATE);
        boolean first = sharedPreferences.getBoolean("first",true);
        if(first){
            Shop shop1 = new Shop("入提拉米苏蛋糕",R.mipmap.pict1,"【天猫超市】马来西亚进口 福多FUDO 24入提拉米苏蛋糕 432g 糕点 ",20);
            shopDB.saveShop(shop1);
            Shop shop2 = new Shop("皇冠丹麦曲奇饼干",R.mipmap.pict2,"【天猫超市】印尼进口 皇冠丹麦曲奇饼干 908g/盒 赠品随机 ",85);
            shopDB.saveShop(shop2);
            Shop shop3 = new Shop("纽西兰曲奇饼干",R.mipmap.pict3,"【天猫超市】马来西亚进口纽西兰曲奇饼干608g蓝罐零食送礼 ",40);
            shopDB.saveShop(shop3);
            Shop shop4 = new Shop("三养拉面",R.mipmap.pict4,"【天猫超市】韩国进口三养拉面火鸡面超辣鸡肉味拌面140g*5方便面 ",38);
            shopDB.saveShop(shop4);
            Shop shop5 = new Shop("gilim蜂蜜黄油扁桃仁",R.mipmap.pict5,"【天猫超市】韩国进口零食品gilim蜂蜜黄油扁桃仁250g 杏仁味坚果",37);
            shopDB.saveShop(shop5);
            Shop shop6 = new Shop("嘉娜宝Kracie",R.mipmap.pict6,"【天猫超市】日本进口零食品 嘉娜宝Kracie玫瑰香体水果软糖果32g",20);
            shopDB.saveShop(shop6);
            Shop shop7 = new Shop("福小老板脆紫菜",R.mipmap.pict7,"【天猫超市】泰国进口海苔 小老板脆紫菜（经典味）36g/包  ",13);
            shopDB.saveShop(shop7);
            Shop shop8 = new Shop("橄榄油",R.mipmap.pict8,"【天猫超市】西班牙进口 融氏特级初榨橄榄油1L 食用油",58);
            shopDB.saveShop(shop8);
            Shop shop9 = new Shop("品利葡萄籽油",R.mipmap.pict9,"【天猫超市】西班牙进口 品利葡萄籽油1L 食用油",59);
            shopDB.saveShop(shop9);
            Shop shop10 = new Shop("丸天酱油",R.mipmap.pict10,"【天猫超市】日本 进口 丸天酱油刺身酱油200ml/瓶生鱼片鱼生寿司",25);
            shopDB.saveShop(shop10);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("first",false);
            editor.commit();

        }
        shops = shopDB.getAllShop();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameshop,new AllShopFragment(new ArrayList<Shop>(),this));
        transaction.commit();
        btnAllShop = (Button) findViewById(R.id.btnallshop);
        btnAllShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameshop,new AllShopFragment(shops,MainActivity.this));
                transaction.commit();
            }
        });
        btnMyShop  = (Button) findViewById(R.id.btnmyshop);
        btnMyShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user==null){
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivityForResult(intent,1);
                }else{
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frameshop,new ShoppingCartFragment());
                    transaction.commit();
                }
            }
        });
    }
}
