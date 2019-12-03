package com.example.game2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.GridLayout;

public class GameView extends GridLayout {

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initGameView();
	}
	//主逻辑
	private void initGameView() {

		setColumnCount(4); 
		setBackgroundColor(0xffbbada0); 

		// 监听触摸事件
		setOnTouchListener(new OnTouchListener() {

			private float startX, startY, offsetX, offsetY; 

			@Override
			public boolean onTouch(View arg0, MotionEvent even) {
				// TODO Auto-generated method stub
				switch (even.getAction()) {
				case MotionEvent.ACTION_DOWN: 
					startX = even.getX();
					startY = even.getY();
					break;
				case MotionEvent.ACTION_UP: 
					offsetX = even.getX() - startX;
					offsetY = even.getY() - startY;

					if (Math.abs(offsetX) > Math.abs(offsetY)) { 
						if (offsetX < -5) {
							swipeLeft(); 
						} else {
							swipeRight(); 
						}
					} else {
						if (offsetY < -5) {
							swipeUp(); 
						} else {
							swipeDown(); 
						}
					}

					break;

				}

				return true;
			}
		});
	}

	//动态调整布局
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		int cardWidth = (Math.min(w, h) - 10) / 4;
		addCard(cardWidth, cardWidth);
		startGame();
	}
	//添加卡片
	private void addCard(int cardWidth, int cardHeight) {
		Card c;

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				c = new Card(getContext());
				c.setNum(0);
				addView(c, cardWidth, cardHeight);
				cardMop[x][y] = c;
			}
		}
	}
	//随机数
	private void addRandomNum() {
		emptyPoints.clear();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardMop[x][y].getNum() <= 0) {
					emptyPoints.add(new Point(x, y)); // 添加所有空卡片
				}
			}
		}
		Point p = emptyPoints
				.remove((int) (Math.random() * emptyPoints.size())); 
		cardMop[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}
	//开始游戏
	private void startGame() {
		MainActivity.getMainActivity().clearScore();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cardMop[x][y].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
	}

	private void swipeLeft() {
		boolean flog = false;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				for (int x1 = x + 1; x1 < 4; x1++) {
					if (cardMop[x1][y].getNum() > 0) {
						if (cardMop[x][y].getNum() <= 0) {
							cardMop[x][y].setNum(cardMop[x1][y].getNum());
							cardMop[x1][y].setNum(0);
							x--;
							flog = true;

						} else if (cardMop[x][y].equals(cardMop[x1][y])) {
							cardMop[x][y].setNum(cardMop[x][y].getNum() * 2);
							cardMop[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMop[x][y].getNum());
							flog = true;

						}
						break;
					}
				}
			}
		}
		if(flog)
		{
			addRandomNum();
			checkCompleta();
		}
	}

	private void swipeRight() {
		boolean flog = false;
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >= 0; x--) {
				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cardMop[x1][y].getNum() > 0) {
						if (cardMop[x][y].getNum() <= 0) {
							cardMop[x][y].setNum(cardMop[x1][y].getNum());
							cardMop[x1][y].setNum(0);
							x++;
							flog = true;

						} else if (cardMop[x][y].equals(cardMop[x1][y])) {
							cardMop[x][y].setNum(cardMop[x][y].getNum() * 2);
							cardMop[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMop[x][y].getNum());
							flog = true;

						}
						break;
					}
				}
			}
		}
		if(flog)
		{
			addRandomNum();
			checkCompleta();
		}
	}

	private void swipeUp() {
		boolean flog = false;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				for (int y1 = y + 1; y1 < 4; y1++) {
					if (cardMop[x][y1].getNum() > 0) {
						if (cardMop[x][y].getNum() <= 0) {
							cardMop[x][y].setNum(cardMop[x][y1].getNum());
							cardMop[x][y1].setNum(0);
							y--;
							flog = true;

						} else if (cardMop[x][y].equals(cardMop[x][y1])) {
							cardMop[x][y].setNum(cardMop[x][y].getNum() * 2);
							cardMop[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMop[x][y].getNum());
							flog = true;

						}
						break;
					}
				}
			}
		}
		if(flog)
		{
			addRandomNum();
			checkCompleta();
		}
	}

	private void swipeDown() {
		boolean flog = false;
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >= 0; y--) {
				for (int y1 = y-1; y1 >= 0; y1--) {
					if (cardMop[x][y1].getNum() > 0) {
						if (cardMop[x][y].getNum() <= 0) {
							cardMop[x][y].setNum(cardMop[x][y1].getNum());
							cardMop[x][y1].setNum(0);
							y++;
							flog = true;

						} else if (cardMop[x][y].equals(cardMop[x][y1])) {
							cardMop[x][y].setNum(cardMop[x][y].getNum() * 2);
							cardMop[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMop[x][y].getNum());
							flog = true;

						}
						break;
					}
				}
			}
		}
		if(flog)
		{
			addRandomNum();
			checkCompleta();
		}
	}
	//检查
	public void checkCompleta()
	{
		boolean complate = true;
		ALL:
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardMop[x][y].getNum()==0||
						(x>0&&cardMop[x][y].equals(cardMop[x-1][y]))||
						(x<3&&cardMop[x][y].equals(cardMop[x+1][y]))||
						(y>0&&cardMop[x][y].equals(cardMop[x][y-1]))||
						(y<3&&cardMop[x][y].equals(cardMop[x][y+1]))) {
					complate =false;
					break ALL;
				}
			}
		}
		if(complate){
			new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束!").setPositiveButton("重来", new DialogInterface.OnClickListener() {
 
				
				//重新开始游戏
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					startGame();
				}
			}).show();
		}
	}

	private Card[][] cardMop = new Card[4][4];
	private List<Point> emptyPoints = new ArrayList<Point>();
}
