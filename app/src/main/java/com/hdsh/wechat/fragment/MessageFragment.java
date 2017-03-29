package com.hdsh.wechat.fragment;

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
import android.widget.Toast;

import com.hdsh.wechat.R;
import com.hdsh.wechat.adapter.CommunicatorAdapter;
import com.hdsh.wechat.avtivity.MessageActivity;
import com.hdsh.wechat.client.FaceImageApi;
import com.hdsh.wechat.client.WeChatClient;
import com.hdsh.wechat.entity.CommunictorInfo;
import com.hdsh.wechat.entity.FaceImageInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2017-03-22 0022.
 */

public class MessageFragment extends Fragment {
    private Context context;
    private static final String TAG = "MessageFragment";
    @BindView(R.id.lv_main)
    ListView lvMain;
    List<CommunictorInfo> list = new ArrayList<>();
    CommunicatorAdapter adapter;
    List<FaceImageInfo.ResultsBean> results = new ArrayList<>();
    public static String faceImageUrl = null;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        list.clear();
        adapter = new CommunicatorAdapter(getActivity());
        adapter.setList(list);
        lvMain.setAdapter(adapter);
        //加载网络数据(使用Retrofit+Rxjava)—————————————————————————————
        FaceImageApi faceImageApi = WeChatClient.getInstance().getFaceImageApi();
        faceImageApi.getFaceImage("10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FaceImageInfo>() {
                    @Override
                    public void onCompleted() {
                        faceImageUrl = list.get(0).getCommunicator_face();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "数据请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(FaceImageInfo faceImageInfo) {
                        results = faceImageInfo.getResults();
                        for (int i = 0; i < results.size(); i++) {
                            CommunictorInfo info = new CommunictorInfo();
                            info.setCommunicator_face(results.get(i).getUrl());
                            info.setCommunicator_time(results.get(i).getDesc());
                            info.setCommunicator_name(results.get(i).getWho());
                            info.setCommunicator_message(results.get(i).get_id());
                            list.add(info);
                            //更新数据必须要，而且必须写在这
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

        //———————————————————end———————————————————————
        lvMain.setOnItemClickListener(listener);
    }

    //消息页面的条目点击事件
    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(), MessageActivity.class);
            intent.putExtra("communicator_name", adapter.getItem(position).getCommunicator_name());
            startActivity(intent);
        }
    };
}
