package com.hdsh.wechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hdsh.wechat.R;
import com.hdsh.wechat.entity.CommunictorInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-03-21 0021.
 */

public class CommunicatorAdapter extends BaseAdapter {
    List<CommunictorInfo> list = new ArrayList<>();
    Context context;

    public CommunicatorAdapter(Context context) {
        this.context = context;
    }

    public List<CommunictorInfo> getList() {
        return list;
    }

    public void setList(List<CommunictorInfo> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return getList().size();
    }

    @Override
    public CommunictorInfo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_message_queue, null);
            holder.communicator_face = (ImageView) convertView.findViewById(R.id.communicator_face);
            holder.communicator_name = (TextView) convertView.findViewById(R.id.communicator_name);
            holder.communicator_message = (TextView) convertView.findViewById(R.id.communicator_message);
            holder.communicator_time = (TextView) convertView.findViewById(R.id.communicator_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CommunictorInfo info = getItem(position);
        Glide.with(context)
                .load(info.getCommunicator_face())
                .placeholder(R.mipmap.icon)
                .error(R.drawable.ic_error)
                .into(holder.communicator_face);
        holder.communicator_name.setText(info.getCommunicator_name());
        holder.communicator_message.setText(info.getCommunicator_message());
        holder.communicator_time.setText(info.getCommunicator_time());
        return convertView;
    }

    class ViewHolder {
        private ImageView communicator_face;
        private TextView communicator_name;
        private TextView communicator_message;
        private TextView communicator_time;
    }
}
