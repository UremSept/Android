package com.edu.lzjtu.shopping.model;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.edu.lzjtu.shopping.R;
import com.edu.lzjtu.shopping.activity.ShopDepict;

import java.util.List;

/**
 * Created by houjie on 2016/5/4.
 */
public class ShoppingCartFragment extends Fragment {
    private List<Shop> mList;
    private ListView mListView;
    private Context context;
    public ShoppingCartFragment(){

    }
    public ShoppingCartFragment(List<Shop> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.layout_all_shop,null);
        ShopAdapter shopAdapter = new ShopAdapter(context,mList);
        mListView = (ListView) view.findViewById(R.id.all_shop_listview);
        mListView.setAdapter(shopAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ShopDepict.class);
                intent.putExtra("shop",mList.get(position).getId());
                startActivity(intent);
            }
        });
        return view;
    }
}
