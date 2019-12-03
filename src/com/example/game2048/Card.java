package com.example.game2048;

import android.R.color;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;
 
public class Card extends FrameLayout {
 
	
	// ��¼��Ƭ����ɫ
	
	private int [] cardColor = new int[30]; 
	
	
	//��ʼ����Ƭ����ɫ
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
	
	
	//���췽��
	public Card(Context context) {
		super(context);
		
		setColor();
		lable = new TextView(getContext());
		lable.setTextSize(32);				//���ô�С
		lable.setBackgroundColor(0x33ffffff);   //������ɫ
		lable.setGravity(Gravity.CENTER);		//����Ϊ����
 
		LayoutParams lp = new LayoutParams(-1,-1);
		lp.setMargins(10, 10, 0, 0);		//�߾�Ϊ10
		addView(lable, lp);
		setNum(0);
		
	}
	private int num = 0;
	
	
	//��ȡ����
	public int getNum() {
		return num;
	}
 
	//��������
	public void setNum(int num) {
		this.num = num;
		if(num<=0){
			lable.setBackgroundColor(0x33ffffff);
			lable.setText("");
		}else {
			//ȡlog�õ�x������ɫ
			int x =(int) (Math.log((double)num));
			x%=11;
			lable.setBackgroundColor(cardColor[x]);
			lable.setText(num+"");
		}
		
	}
 
	//��Ƭ��lable���
	private TextView lable;
	
	
	//�ж��Ƿ����
	public boolean equals(Card o) {
		// TODO Auto-generated method stub
		return num==o.getNum();
	}
	
 
}
