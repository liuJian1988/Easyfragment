package com.lj.easyframent.factory;

import android.os.Bundle;

import com.lj.easyframent.BaseFragment;

import java.io.Serializable;

/**
 * 作者：liujian on 2018/9/11 11:33
 * 邮箱：15313727484@163.com
 */
public interface IFragmentFactory extends Serializable {
    public static final String STR_KEY = "str_key";
    public static final String OBJ_KEY = "obj_key";
    public static final String INT_KEY = "int_key";

    BaseFragment getFragment(int key, boolean useCache, Bundle data);
}
