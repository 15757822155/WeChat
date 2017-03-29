package com.hdsh.wechat.avtivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hdsh.wechat.R;
import com.hdsh.wechat.fragment.CommunicatorFragment;
import com.hdsh.wechat.fragment.FindFragment;
import com.hdsh.wechat.fragment.MessageFragment;
import com.hdsh.wechat.fragment.UserFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.message_main)
    TextView messageMain;
    @BindView(R.id.communicator_list_main)
    TextView communicatorListMain;
    @BindView(R.id.find_main)
    TextView findMain;
    @BindView(R.id.user_main)
    TextView userMain;
    @BindView(R.id.viewpage_main)
    ViewPager viewpageMain;

    private boolean isExit = false;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        //设置Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("微信");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorTitle));
        setSupportActionBar(toolbar);
        initView();
        messageMain.setSelected(true);
        viewpageMain.addOnPageChangeListener(listener);
        viewpageMain.setOffscreenPageLimit(3);
        viewpageMain.setAdapter(adapter);
    }

    //ViewPager适配器
    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MessageFragment();
                case 1:
                    return new CommunicatorFragment();
                case 2:
                    return new FindFragment();
                case 3:
                    return new UserFragment();
                default:
                    throw new RuntimeException("未知错误");
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };
    //页面改变监听
    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            messageMain.setSelected(position == 0);
            communicatorListMain.setSelected(position == 1);
            findMain.setSelected(position == 2);
            userMain.setSelected(position == 3);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //初始化视图
    private void initView() {
        //——————————对底部导航栏图片进行缩放——————————————————
        Drawable drawable = getResources().getDrawable(R.drawable.select_message);
        Drawable drawable1 = getResources().getDrawable(R.drawable.select_communicator_list);
        Drawable drawable2 = getResources().getDrawable(R.drawable.select_find);
        Drawable drawable3 = getResources().getDrawable(R.drawable.select_user);
        drawable.setBounds(0, 0, 90, 80);
        drawable1.setBounds(0, 0, 90, 80);
        drawable2.setBounds(0, 0, 90, 80);
        drawable3.setBounds(0, 0, 90, 80);
        messageMain.setCompoundDrawables(null, drawable, null, null);
        communicatorListMain.setCompoundDrawables(null, drawable1, null, null);
        findMain.setCompoundDrawables(null, drawable2, null, null);
        userMain.setCompoundDrawables(null, drawable3, null, null);
        //——————————————end——————————————————————————

    }

    //填充标题按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    //设置标题菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_add:
                Toast.makeText(MainActivity.this, "添加", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //底部按钮的点击事件
    @OnClick({R.id.message_main, R.id.communicator_list_main, R.id.find_main, R.id.user_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_main:
                viewpageMain.setCurrentItem(0, false);
                break;
            case R.id.communicator_list_main:
                viewpageMain.setCurrentItem(1, false);
                break;
            case R.id.find_main:
                viewpageMain.setCurrentItem(2, false);
                break;
            case R.id.user_main:
                viewpageMain.setCurrentItem(3, false);
                break;
        }
    }


    //按两下退出程序
    @Override
    public void onBackPressed() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一下退出程序", Toast.LENGTH_SHORT).show();
            //设置定时器，若两秒内无操作，则状态重置
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
