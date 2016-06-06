package com.edu.lzjtu.shopping.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.lzjtu.shopping.R;

import java.util.List;

/**
 * Created by houjie on 2016/5/4.
 */
public class ShopAdapter extends BaseAdapter {
    private Context mContext;
    private List<Shop> mList;

    public ShopAdapter(Context mContext, List<Shop> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_all_shop_listview,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.allshopimage);
            viewHolder.name = (TextView) convertView.findViewById(R.id.allshopname);
            viewHolder.price = (TextView) convertView.findViewById(R.id.allshopprice);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageResource(mList.get(position).getImage());
        viewHolder.name.setText(mList.get(position).getName());
        viewHolder.price.setText(mList.get(position).getPrice()+"");
        return convertView;
    }


    class ViewHolder{
        ImageView imageView;
        TextView name;
        TextView price;
    }
}
