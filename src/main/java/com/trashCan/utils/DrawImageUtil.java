package com.trashCan.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * 生成随机图片，用来作为验证码
 */
public class DrawImageUtil {
	public static final int WIDTH = 120;// 生成的图片的宽度
	public static final int HEIGHT = 30;// 生成的图片的高度

	/**
	 * 设置图片的背景色
	 * 
	 * @param g
	 */
	public static void setBackGround(Graphics g) {
		// 设置颜色
		g.setColor(Color.WHITE);
		// 填充区域
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	/**
	 * 设置图片的边框
	 * 
	 * @param g
	 */
	public static void setBorder(Graphics g) {
		// 设置边框颜色
		g.setColor(Color.BLUE);
		// 边框区域
		g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}

	/**
	 * 在图片上画随机线条
	 * 
	 * @param g
	 */
	public static void drawRandomLine(Graphics g) {
		// 设置颜色
		g.setColor(Color.GREEN);
		// 设置线条个数并画线
		for (int i = 0; i < 9; i++) {
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 画随机字符
	 * 
	 * @param g
	 * @param createTypeFlag
	 * @return String... createTypeFlag是可变参数，
	 *         Java1.5增加了新特性：可变参数：适用于参数个数不确定，类型确定的情况
	 *         ，java把可变参数当做数组处理。注意：可变参数必须位于最后一项
	 */
	public static String drawRandomNum(Graphics2D g) {
		// 设置颜色
		g.setColor(Color.BLUE);
		// 设置字体
		g.setFont(new Font("宋体", Font.BOLD, 25));
		// 数字和字母的组合
		String baseNumLetter = "0123456789";
		return createRandomChar(g, baseNumLetter);
	}

	/**
	 * 创建随机字符
	 * 
	 * @param g
	 * @param baseChar
	 * @return 随机字符
	 */
	private static String createRandomChar(Graphics2D g, String baseChar) {
		StringBuffer sb = new StringBuffer();
		int x = 5;
		String ch = "";
		// 控制字数
		for (int i = 0; i < 4; i++) {
			// 设置字体旋转角度
			int degree = new Random().nextInt() % 30;
			ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
			sb.append(ch);
			// 正向角度
			g.rotate(degree * Math.PI / 180, x, 20);
			g.drawString(ch, x, 20);
			// 反向角度
			g.rotate(-degree * Math.PI / 180, x, 20);
			x += 30;
		}
		return sb.toString();
	}
}