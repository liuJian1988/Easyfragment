package com.lj.easyframent.factory;

import android.app.Activity;
import android.app.Fragment;

import com.lj.easyframent.BaseActivity;


/**
 * fragment的替换操作
 * 参见博客：https://blog.csdn.net/liujian8654562/article/details/77327908
 * https://blog.csdn.net/liujian8654562/article/details/80348358
 */

public class FragmentOperator {
    /**
     * 1、replace并放回退栈中
     */
    public static void replaceAddToBackStack(Activity a, Fragment f, int id, String t1, String t2) {
        a.getFragmentManager().beginTransaction().addToBackStack(t1).replace(id, f, t2).commitAllowingStateLoss();
    }

    /**
     * 2、replace但不放回退栈中
     */
    public static void replaceNotToBackStack(Activity a, Fragment f, int id, String t2) {
        a.getFragmentManager().beginTransaction().replace(id, f, t2).commitAllowingStateLoss();
    }

    /**
     * 3、add并放回退栈中
     */
    public static void addAddToBackStack(Activity a, Fragment f, int id, String t1, String t2) {
        a.getFragmentManager().beginTransaction().addToBackStack(t1).add(id, f, t2).commitAllowingStateLoss();
        a.getFragmentManager().popBackStack();
    }

    /**
     * 4、add但不放回退栈中
     */
    public static void addNotAddToBackStack(Activity a, Fragment f, int id, String t2) {
        a.getFragmentManager().beginTransaction().add(id, f, t2).commitAllowingStateLoss();
    }

    /**
     * 5、hide当前的Fragment，并且add新的fragment
     * 可以保留当前Fragment的所有的状态，show即可还原
     */
    public static void hide(Activity a, Fragment f1, Fragment f2, int id, String t1, String t2) {
        a.getFragmentManager().beginTransaction().hide(f1).commitAllowingStateLoss();
        addAddToBackStack(a, f2, id, t1, t2);
    }

    /**
     * 6、show 之前被hide的Fragment
     */
    public static void show(Activity a, Fragment f1) {
        a.getFragmentManager().beginTransaction().show(f1).commitAllowingStateLoss();
    }

    /**
     * 7、在新的activity中打开fragment
     */
    public static void openInNewActivity(BaseActivity activity, Class<? extends Activity> c, int key) {
        activity.openActivity(c, key);
    }
}