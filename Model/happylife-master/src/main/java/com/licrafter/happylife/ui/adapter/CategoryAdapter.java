package com.licrafter.happylife.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.licrafter.happylife.R;
import com.licrafter.happylife.data.entity.BaseCategoryData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/26.
 */
public class CategoryAdapter extends BaseAdapter {

    private ArrayList<BaseCategoryData> categoryDatas;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public CategoryAdapter(Context context) {
        this.context = context;
        categoryDatas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return categoryDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
            viewHolder = new ViewHolder((ImageView) convertView.findViewById(R.id.categoryImageView)
                    , (TextView) convertView.findViewById(R.id.categoryTextView));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                    , parent.getWidth() / 3);
            convertView.setLayoutParams(new GridView.LayoutParams(params));
            convertView.setTag(viewHolder);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(categoryDatas.get(position).getObjectId(), categoryDatas.get(position).getName());
                }
            });
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(categoryDatas.get(position).getIconUrl())
                .fitCenter().into(viewHolder.categoryImageView);
        viewHolder.categoryTextView.setText(categoryDatas.get(position).getName());
        return convertView;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        public void onItemClick(String category, String title);
    }

    public void setData(ArrayList<BaseCategoryData> categoryDatas) {
        this.categoryDatas = categoryDatas;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public ImageView categoryImageView;
        public TextView categoryTextView;

        public ViewHolder(ImageView imageView, TextView textView) {
            this.categoryImageView = imageView;
            this.categoryTextView = textView;
        }
    }
}
