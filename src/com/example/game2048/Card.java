package com.example.game2048;

import android.R.color;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;
 
public class Card extends FrameLayout {
 
	
	// 记录卡片的颜色
	
	private int [] cardColor = new int[30]; 
	
	
	//初始化卡片的颜色
	public void setColor(){
		cardColor[0] =Color.BLUE;
		cardColor[1] = Color.CYAN;
		cardColor[8] = Color.DKGRAY;
		cardColor[5] = Color.GRAY;
		cardColor[4] = Color.GREEN;
		cardColor[3] = Color.RED;
		cardColor[6] = Color.LTGRAY;
		cardColor[7] = Color.MAGENTA;
		cardColor[2]= Color.YELLOW;
		cardColor[9] = Color.WHITE;
		cardColor[10] = Color.TRANSPARENT;
	}
	
	
	//构造方法
	public Card(Context context) {
		super(context);
		
		setColor();
		lable = new TextView(getContext());
		lable.setTextSize(32);				//设置大小
		lable.setBackgroundColor(0x33ffffff);   //背景颜色
		lable.setGravity(Gravity.CENTER);		//布局为居中
 
		LayoutParams lp = new LayoutParams(-1,-1);
		lp.setMargins(10, 10, 0, 0);		//边距为10
		addView(lable, lp);
		setNum(0);
		
	}
	private int num = 0;
	
	
	//获取数字
	public int getNum() {
		return num;
	}
 
	//设置数字
	public void setNum(int num) {
		this.num = num;
		if(num<=0){
			lable.setBackgroundColor(0x33ffffff);
			lable.setText("");
		}else {
			//取log得到x设置颜色
			int x =(int) (Math.log((double)num));
			x%=11;
			lable.setBackgroundColor(cardColor[x]);
			lable.setText(num+"");
		}
		
	}
 
	//卡片的lable组件
	private TextView lable;
	
	
	//判断是否相等
	public boolean equals(Card o) {
		// TODO Auto-generated method stub
		return num==o.getNum();
	}
	
 
}
