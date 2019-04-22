package com.lj.easyframent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;


import com.lj.easyframent.factory.FragmentKey;
import com.lj.easyframent.finterface.IFragmentAttachOperator;
import com.lj.util.AppUtil;
import com.lj.util.RxBus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 作者：liujian on 2018/6/29 13:36
 * 邮箱：15313727484@163.com
 */
public abstract class BaseActivity extends AppCompatActivity implements Consumer<RxBus.Event>, Predicate<RxBus.Event> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //隐藏虚拟按键
        AppUtil.hideBottomUIMenu(this);
        compositeDisposable.add(RxBus.get().toFlowable(RxBus.Event.class).filter(this).observeOn(AndroidSchedulers.mainThread()).subscribe(this));
    }

    /**
     * 打开activity
     */
    public void openActivity(Class<?> cls) {
        openActivity(this, cls, null);
    }

    /**
     * 打开activity
     */
    public void openActivity(Class<?> cls, int key) {
        Map<String, Serializable> map = new HashMap<>();
        map.put(FragmentKey.FRAGMENT_KEY, key + "");
        openActivity(this, cls, map);
        overridePendingTransition(R.anim.create_zoomin, R.anim.create_zoomout);
    }

    public void openActivity(Class<?> cls, Map<String, Serializable> map) {
        Intent intent = new Intent(this, cls);
        if (map != null) {
            for (String k : map.keySet()) {
                intent.putExtra(k, map.get(k));
            }

        }
        startActivity(intent);
    }

    /**
     * 打开activity
     */
    public void openActivity(Context context, Class<?> cls, Map<String, Serializable> map) {
        Intent intent = new Intent(context, cls);
        if (map != null) {
            for (String k : map.keySet()) {
                intent.putExtra(k, map.get(k));
            }

        }
        context.startActivity(intent);
        overridePendingTransition(R.anim.create_zoomin, R.anim.create_zoomout);

    }

    @Override
    public void accept(RxBus.Event event) {
        rxReceiver(event);
    }

    @Override
    public boolean test(RxBus.Event event) {
        return rxFilter(event);
    }

    protected abstract boolean rxFilter(RxBus.Event event);

    protected abstract boolean rxReceiver(RxBus.Event event);

    protected abstract void setIFragmentAttachOperator(IFragmentAttachOperator fp);

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.finish_zoomin, R.anim.finish_zoomout);
        compositeDisposable.clear();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //do something.
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

}
