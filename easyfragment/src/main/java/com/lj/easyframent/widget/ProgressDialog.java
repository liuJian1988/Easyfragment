package com.lj.easyframent.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lj.easyframent.R;


public class ProgressDialog extends Dialog {
	private Context context;
	private String msg;
	private ImageView spaceshipImage;
	private Animation hyperspaceJumpAnimation;
	private TextView tipTextView;

	public ProgressDialog(Context context) {
		super(context, R.style.loading_dialog);
		// TODO Auto-generated constructor stub
		this.context = context;
		createLoadingDialog();
	}

	/**
	 * 得到自定义的progressDialog
	 * @return
	 */
	private void createLoadingDialog() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// main.xml中的ImageView
		spaceshipImage = (ImageView) v.findViewById(R.id.img);
		tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
		// 加载动画
		hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context,
				R.anim.loading_animation);

		this.setCancelable(false);// 不可以用“返回键”取消
		this.setContentView(layout);

	}

	public void setContent(String msg) {
		tipTextView.setText(msg);// 设置加载信息
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		// 使用ImageView显示动画
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		super.show();
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		spaceshipImage.clearAnimation();
		super.dismiss();
	}
}
