package com.hdsh.wechat.avtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hdsh.wechat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MessageActivity extends AppCompatActivity {

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
    private Unbinder unbinder;

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
        communicatorName.setText(communicator_name);

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
