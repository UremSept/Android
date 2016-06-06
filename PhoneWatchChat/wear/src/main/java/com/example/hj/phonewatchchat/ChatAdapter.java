package com.example.hj.phonewatchchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by houjie on 2016/4/8.
 */
public class ChatAdapter extends BaseAdapter {
    private Context mContent;

    public ChatAdapter(Context mContent, List<Type> mList) {
        this.mContent = mContent;
        this.mList = mList;
    }

    private List<Type> mList;
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
        ViewHolderTime viewHolderTime = null;
        ViewHolderLeft viewHolderLeft =null;
        ViewHolderRight viewHolderRight = null;
        int type =mList.get(position).getType();
        if(convertView == null){
            switch (type){
                case Type.timeType:
                    viewHolderTime = new ViewHolderTime();
                    convertView = LayoutInflater.from(mContent).inflate(R.layout.layout_time,null);
                    viewHolderTime.textView = (TextView) convertView.findViewById(R.id.chat_time);
                    convertView.setTag(viewHolderTime);
                    break;
                case Type.wearType:
                    viewHolderLeft = new ViewHolderLeft();
                    convertView = LayoutInflater.from(mContent).inflate(R.layout.layout_wear,null);
                    viewHolderLeft.imageView= (ImageView) convertView.findViewById(R.id.chat_wear_image);
                    viewHolderLeft.imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContent,"点击了左边的图片",Toast.LENGTH_SHORT).show();
                        }
                    });
                    viewHolderLeft.textView = (TextView) convertView.findViewById(R.id.chat_wear_text);
                    convertView.setTag(viewHolderLeft);
                    break;
                case Type.phoneType:
                    viewHolderRight = new ViewHolderRight();
                    convertView = LayoutInflater.from(mContent).inflate(R.layout.layout_phone,null);
                    viewHolderRight.imageView= (ImageView) convertView.findViewById(R.id.chat_phone_image);
                    viewHolderRight.imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContent,"点击了右边的图片",Toast.LENGTH_SHORT).show();
                        }
                    });

                    viewHolderRight.textView = (TextView) convertView.findViewById(R.id.chat_phone_text);
                    convertView.setTag(viewHolderRight);
                    break;
                default:break;

            }
        }else {
            switch (type){
                case Type.timeType:
                        viewHolderTime= (ViewHolderTime) convertView.getTag();

                    break;
                case Type.wearType:
                    viewHolderLeft= (ViewHolderLeft) convertView.getTag();
                    break;
                case Type.phoneType:
                    viewHolderRight= (ViewHolderRight) convertView.getTag();

                    break;
                default:break;

            }
        }
        switch (type){
            case Type.timeType:
                TimeType timeType= (TimeType) mList.get(position);
                viewHolderTime.textView.setText(timeType.getTime());
                break;
            case Type.wearType:
                WearType wearType = (WearType) mList.get(position);
                viewHolderLeft.imageView.setImageResource(wearType.getImage());
                viewHolderLeft.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContent,"点击了左边的图片",Toast.LENGTH_SHORT).show();
                    }
                });
                viewHolderLeft.textView.setText(wearType.getWearText());
                break;
            case Type.phoneType:
                PhoneType phoneType = (PhoneType) mList.get(position);
                viewHolderRight.imageView.setImageResource(phoneType.getImage());
                viewHolderRight.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContent,"点击了右边的图片",Toast.LENGTH_SHORT).show();
                    }
                });
                viewHolderRight.textView.setText(phoneType.getPhoneText());
                break;
            default:break;

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    class ViewHolderLeft{
        private ImageView imageView;
        private TextView textView;
    }
    class ViewHolderRight{
        private ImageView imageView;
        private TextView textView;
    }
    class ViewHolderTime{
        private TextView textView;
    }
}
