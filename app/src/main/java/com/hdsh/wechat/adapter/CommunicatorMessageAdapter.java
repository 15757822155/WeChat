package com.hdsh.wechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdsh.wechat.R;
import com.hdsh.wechat.entity.RobotInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-04-11 0011.
 */

public class CommunicatorMessageAdapter extends BaseAdapter {
    List<RobotInfo> mList = new ArrayList<>();
    Context mContext;
    private static final String TAG = "CommunicatorMessageAdap";

    public CommunicatorMessageAdapter(Context context) {
        mContext = context;
    }

    public List<RobotInfo> getList() {
        return mList;
    }

    public void setList(List<RobotInfo> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public RobotInfo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_communicator_message, null);
            viewHolder.communicatorMessage = (TextView) convertView.findViewById(R.id.tv_communicator_message);
            viewHolder.userMessage = (TextView) convertView.findViewById(R.id.tv_user_message);
            viewHolder.llCommunicatorMessage = (LinearLayout) convertView.findViewById(R.id.ll_communicator_message);
            viewHolder.llUserMessage = (LinearLayout) convertView.findViewById(R.id.ll_user_message);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        RobotInfo robotInfo = getItem(position);
        if (robotInfo.isType()) {
            viewHolder.communicatorMessage.setText(robotInfo.getText());
            viewHolder.llUserMessage.setVisibility(View.GONE);
        } else {
            viewHolder.userMessage.setText(robotInfo.getText());
            viewHolder.llCommunicatorMessage.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView communicatorMessage;
        TextView userMessage;
        LinearLayout llCommunicatorMessage;
        LinearLayout llUserMessage;
    }
}
