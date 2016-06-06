package com.edu.lzjtu.shopping.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.edu.lzjtu.shopping.model.Shop;
import com.edu.lzjtu.shopping.model.ShoppingCart;
import com.edu.lzjtu.shopping.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by houjie on 2016/4/21.
 */
public class ShopDB {
    public static final String DB_NAME = "shop_db";
    public static final int VRESION = 1;
    private static ShopDB shopDB;
    private SQLiteDatabase db;
    private ShopDB(Context context){
        ShopDatabaseHelper musicDatabaseHelper = new ShopDatabaseHelper(context,DB_NAME,null,VRESION);
        db = musicDatabaseHelper.getWritableDatabase();
    }
    public synchronized static ShopDB getInstance(Context context){
        if (shopDB ==null){
            shopDB = new ShopDB(context);
        }
        return shopDB;
    }

    public boolean findUser(User user) {
        boolean flag=false;
        if (user != null) {
            Cursor cursor = db.query("user", null, "user_name = ? ,user_password= ? ", new String[]{user.getName(), user.getPassword()}, null, null, null);
            flag = cursor.moveToFirst();
            if (flag) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                user.setId(id);
            }
        }
        return flag;
    }
    public void getUserShoppingCart(User user){
        if(user!=null){
            List<ShoppingCart> list = new ArrayList<>();
            Cursor cursor = db.query("shopping_cart", null, "user_id = ? ", new String[]{user.getId()+""}, null, null, null);
            if(cursor.moveToFirst()){
                do{
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    int shopId = cursor.getInt(cursor.getColumnIndex("shop_id"));
                    int shopCount = cursor.getInt(cursor.getColumnIndex("shop_count"));
                    ShoppingCart shoppingCart=new ShoppingCart();
                    list.add(shoppingCart);
                }while (cursor.moveToNext());
            }
            user.setList(list);
        }
    }



    public List<Shop> getAllShop(){
        List<Shop> list = new ArrayList<>();
        Cursor cursor = db.query("all_shop",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String shopName  =cursor.getString(cursor.getColumnIndex("shop_name"));
                String shopSay  =cursor.getString(cursor.getColumnIndex("shop_say"));
                int id  =cursor.getInt(cursor.getColumnIndex("_id"));
                int shopImage  =cursor.getInt(cursor.getColumnIndex("shop_image"));
                int shopPrice  = cursor.getInt(cursor.getColumnIndex("shop_price"));
                Shop shop =new Shop();
                shop.setId(id);
                shop.setImage(shopImage);
                shop.setName(shopName);
                shop.setPrice(shopPrice);
                shop.setSay(shopSay);
                list.add(shop);
            }while (cursor.moveToNext());
        }

        return list;
    }

    public Shop getShop(int id){
        Shop shop =new Shop();
        Cursor cursor = db.query("all_shop",null,"_id = ?",new String []{id+""},null,null,null);
        if(cursor.moveToFirst()){
                String shopName  =cursor.getString(cursor.getColumnIndex("shop_name"));
                String shopSay  =cursor.getString(cursor.getColumnIndex("shop_say"));
                int _id  =cursor.getInt(cursor.getColumnIndex("_id"));
                int shopImage  =cursor.getInt(cursor.getColumnIndex("shop_image"));
                int shopPrice  = cursor.getInt(cursor.getColumnIndex("shop_price"));
                shop.setId(_id);
                shop.setImage(shopImage);
                shop.setName(shopName);
                shop.setPrice(shopPrice);
                shop.setSay(shopSay);
        }
        return shop;
    }
    public void saveShop(Shop shop){
        ContentValues values =new ContentValues();
        values.put("shop_name",shop.getName());
        values.put("shop_image",shop.getImage());
        values.put("shop_say",shop.getSay());
        values.put("shop_price",shop.getPrice());
        db.insert("all_shop",null,values);
    }

    public void saveUser(User user){
        ContentValues values =new ContentValues();
        values.put("user_name",user.getName());
        values.put("user_password",user.getPassword());
        db.insert("user",null,values);
    }
    public static final String SHOPPING_CART = "create table shopping_cart("
            +"_id integer primary key autoincrement, "
            +"user_id integer, "
            +"shop_id integer, "
            +"shop_count integer)";
    public void saveShoppingCart(int userId,int shopId,int shopCount){
        ContentValues values =new ContentValues();
        values.put("user_id",userId);
        values.put("shop_id",shopId);
        db.insert("shopping_cart",null,values);
    }
}
