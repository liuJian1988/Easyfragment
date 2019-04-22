package com.lj.easyframent.finterface;

import android.app.Activity;
import android.app.Fragment;


import com.lj.util.RxBus;

import java.io.Serializable;

/**
 * 作者：liujian on 2018/9/11 14:07
 * 邮箱：15313727484@163.com
 * 用于连接Fragment层实现的操作
 */
public interface IFragmentAttachOperator extends Serializable {
    Fragment init(Activity a);

    boolean operator(RxBus.Event e, byte arg);
}
