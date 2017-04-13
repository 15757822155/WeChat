package com.hdsh.wechat.fragment;

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
import butterknife.OnClick;

/**
 * Created by Administrator on 2017-03-24 0024.
 */

public class UserFragment extends Fragment {

    @BindView(R.id.tv_user)
    TextView mTvUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_user)
    public void onClick() {
        String a = "&lt;p&gt;购买vip描述&lt;/p&gt;&lt;p&gt;购买vip描述&lt;/p&gt;&lt;p&gt;购买vip描述&lt;/p&gt;&lt;p&gt;购买vip描述&lt;/p&gt;";
        String b[] = a.split("(&lt;p&gt;)|(&lt;/p&gt;)");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            stringBuffer.append(b[i]);
            if (b[i] != null && b[i].length() > 0 && i != b.length - 1) {
                stringBuffer.append("\n");
            }
        }
        mTvUser.setText(stringBuffer);
    }
}
