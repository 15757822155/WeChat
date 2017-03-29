package com.hdsh.wechat.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hdsh.wechat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017-03-24 0024.
 */

public class FindFragment extends Fragment {

    @BindView(R.id.find_friend)
    TextView findFriend;
    @BindView(R.id.find_friend_image)
    ImageView findFriendImage;
    @BindView(R.id.find_richscan)
    TextView findRichscan;
    @BindView(R.id.find_shopping)
    TextView findShopping;
    @BindView(R.id.find_play)
    TextView findPlay;
    @BindView(R.id.find_play_game)
    TextView findPlayGame;
    @BindView(R.id.find_play_game_image)
    ImageView findPlayGameImage;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        //以下操作主要为了朋友圈那行的头像刷新页面
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Observable observable = Observable.just(MessageFragment.faceImageUrl);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Glide.with(FindFragment.this)
                                .load(MessageFragment.faceImageUrl)
                                .placeholder(R.mipmap.icon)
                                .error(R.drawable.ic_error)
                                .into(findFriendImage);
                    }
                });

    }

    private void initView() {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_friend);
        drawable.setBounds(0, 0, 110, 110);
        findFriend.setCompoundDrawables(drawable, null, null, null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.ic_richscan);
        drawable1.setBounds(0, 0, 110, 110);
        findRichscan.setCompoundDrawables(drawable1, null, null, null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.ic_shopping);
        drawable2.setBounds(0, 0, 110, 110);
        findShopping.setCompoundDrawables(drawable2, null, null, null);
        Drawable drawable3 = getResources().getDrawable(R.drawable.ic_play);
        drawable3.setBounds(0, 0, 110, 110);
        findPlay.setCompoundDrawables(drawable3, null, null, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
