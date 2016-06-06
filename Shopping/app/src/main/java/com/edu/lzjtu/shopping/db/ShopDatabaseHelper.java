package com.edu.lzjtu.shopping.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by houjie on 2016/4/20.
 */

public class ShopDatabaseHelper extends SQLiteOpenHelper{
    private Context mContent;
    public static final String CREATE_ALL_SHOP = "create table all_shop("
            +"_id integer primary key autoincrement, "
            +"shop_name text, "
            +"shop_image integer, "
            +"shop_say text, "
            +"shop_price integer)";
    public static final String CREATE_USER = "create table user("
            +"_id integer primary key autoincrement, "
            +"user_name text, "
            +"user_password text)";
    public static final String SHOPPING_CART = "create table shopping_cart("
            +"_id integer primary key autoincrement, "
            +"user_id integer, "
            +"shop_id integer, "
            +"shop_count integer)";
    public ShopDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContent = context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ALL_SHOP);
        db.execSQL(CREATE_USER);
        db.execSQL(SHOPPING_CART);
        Toast.makeText(mContent,"成功创建数据库",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
