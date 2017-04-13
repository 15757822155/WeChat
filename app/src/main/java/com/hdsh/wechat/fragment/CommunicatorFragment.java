package com.hdsh.wechat.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hdsh.wechat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-03-24 0024.
 */

public class CommunicatorFragment extends Fragment {
    @BindView(R.id.communicator_new_friend)
    TextView mCommunicatorNewFriend;
    @BindView(R.id.communicator_group_chat)
    TextView mCommunicatorGroupChat;
    @BindView(R.id.communicator_official)
    TextView mCommunicatorOfficial;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_communicator, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_new_friend);
        Drawable drawable1 = getResources().getDrawable(R.drawable.ic_group_chat);
        Drawable drawable2 = getResources().getDrawable(R.drawable.ic_lable);
        Drawable drawable3 = getResources().getDrawable(R.drawable.ic_official);
        drawable.setBounds(0, 0, 120, 120);
        drawable1.setBounds(0, 0, 120, 120);
        drawable2.setBounds(0, 0, 120, 120);
        drawable3.setBounds(0, 0, 120, 120);
        mCommunicatorNewFriend.setCompoundDrawables(drawable, null, null, null);
        mCommunicatorGroupChat.setCompoundDrawables(drawable1, null, null, null);
//        findMain.setCompoundDrawables(null, drawable2, null, null);
        mCommunicatorOfficial.setCompoundDrawables(drawable3, null, null, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
