package com.hdsh.wechat.avtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hdsh.wechat.R;
import com.hdsh.wechat.adapter.CommunicatorMessageAdapter;
import com.hdsh.wechat.client.RobotApi;
import com.hdsh.wechat.client.WeChatClient;
import com.hdsh.wechat.entity.RobotInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageActivity extends AppCompatActivity implements View.OnKeyListener {
    private static final String TAG = "MessageActivity";
    @BindView(R.id.back_message)
    ImageView backMessage;
    @BindView(R.id.communicator_name)
    TextView communicatorName;
    @BindView(R.id.communicator_info)
    ImageView communicatorInfo;
    @BindView(R.id.voice_communicator)
    ImageView voiceCommunicator;
    @BindView(R.id.edittext_communicator)
    EditText edittextCommunicator;
    @BindView(R.id.smilies_communicator)
    ImageView smiliesCommunicator;
    @BindView(R.id.add_list_communicator)
    ImageView addListCommunicator;
    @BindView(R.id.lv_message)
    ListView mLvMessage;
    //    @BindView(R.id.iv_message)
//    ImageView ivMessage;
    private Unbinder unbinder;
    private String msg;
    List<RobotInfo> mList = new ArrayList<>();
    CommunicatorMessageAdapter mAdapter;
    RobotApi robotApi;
    private String mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    //初始化视图相关
    private void initView() {
        //设置标题
        String communicator_name = getIntent().getStringExtra("communicator_name");
//        String communicator_image = getIntent().getStringExtra("communicator_image");
        communicatorName.setText(communicator_name);
//        Glide.with(this)
//                .load(communicator_image)
//                .into(ivMessage);

        mAdapter = new CommunicatorMessageAdapter(this);
        mLvMessage.setAdapter(mAdapter);
        edittextCommunicator.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //key有down跟up两种事件，会执行两次内容，所以规定了只有key的状态为action_down才执行以下操作
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            mList.clear();
            msg = edittextCommunicator.getText().toString();
            mList.add(new RobotInfo(msg, false));
            mAdapter.setList(mList);
            mAdapter.notifyDataSetChanged();
            for (int i = 0; i < mList.size(); i++) {
                Log.i(TAG, "onKey: " + mList.get(i).getText());
            }
            request(msg);
            return true;
        } else {
            return false;
        }
    }

    private void request(final String msg) {
        robotApi = WeChatClient.getInstance().getRobotApi();
        robotApi.getResult("8d1b0f0beb1847f7b2a86589f74dd5d3", msg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RobotInfo>() {
                    @Override
                    public void onCompleted() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MessageActivity.this, "数据接收失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(RobotInfo robotInfo) {
                        mText = robotInfo.getText();
                        mList.add(new RobotInfo(mText, true));
                        for (int i = 0; i < mList.size(); i++) {
                            Log.i(TAG, "onNext: " + mList.get(i).getText());
                        }
                    }
                });
    }

    @OnClick({R.id.back_message, R.id.communicator_info, R.id.voice_communicator, R.id.smilies_communicator, R.id.add_list_communicator})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_message:
                finish();
                break;
            case R.id.communicator_info:
                // TODO: 2017-03-23 0023 交流者信息界面
                break;
            case R.id.voice_communicator:
                break;
            case R.id.smilies_communicator:
                break;
            case R.id.add_list_communicator:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
