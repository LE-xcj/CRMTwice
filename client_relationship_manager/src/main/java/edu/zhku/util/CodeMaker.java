/**
 * 
 */
package edu.zhku.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * Title: CodeUtil.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年6月12日
 */
public class CodeMaker {
	
	public static Map<String, BufferedImage> getCode() {

		// 在内存中创建一幅图片
		BufferedImage img = new BufferedImage(110, 30, BufferedImage.TYPE_INT_BGR);

		// 向图片上写数据
		Graphics g = img.getGraphics();

		// 设置背景色
		g.setColor(Color.white);
		g.fillRect(0, 0, 110, 30);

		String checkcode = "";

		Map<String, BufferedImage> code = new HashMap<>(1);
		// 画5个验证码字符
		for (int i = 0; i < 5; i++) {
			g.setColor(generateColor());
			g.setFont(generateFont());
			String str = generateStr();
			checkcode += str;
			g.drawString(str, 20 * i, 25);
		}

		// 画干扰点
		for (int i = 0; i < 100; i++) {
			Random rand = new Random();
			int x = rand.nextInt(110);
			int y = rand.nextInt(30);
			g.setColor(generateColor());
			g.fillOval(x, y, 2, 2);
		}

		// 画干扰线
		for (int i = 0; i < 5; i++) {
			Random rand = new Random();
			int x1 = rand.nextInt(110);
			int y1 = rand.nextInt(30);
			int x2 = rand.nextInt(110);
			int y2 = rand.nextInt(30);
			g.setColor(generateColor());
			g.drawLine(x1, y1, x2, y2);
		}

		code.put(checkcode, img);
		return code;
	}
	
	/**
	 * 产生随机字符
	 * 
	 * @return
	 */
	private static String generateStr() {
		String[] nums = new String[62];

		// 添加0-9
		for (int i = 0; i < 10; i++)
			nums[i] = String.valueOf(i);

		// 添加A-Z
		for (int i = 65; i < 91; i++)
			nums[i - 55] = Character.toString((char) i);

		// 添加a-z
		for (int i = 97; i < 123; i++)
			nums[i - 61] = Character.toString((char) i);

		// 产生随机数
		Random rand = new Random();
		int index = rand.nextInt(62);
		return nums[index];

	}

	private static Font generateFont() {
		// TODO Auto-generated method stub
		String[] fontNames = new String[] { "Broadway", "方正姚体", "Footlight MT Light", "Sitka Text", "方正舒体", "幼圆",
				"Colonna MT" };
		int[] fontStyles = new int[] { Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC };

		Random rand = new Random();
		int nameIndex = rand.nextInt(fontNames.length);
		int styleIndex = rand.nextInt(fontStyles.length);

		return new Font(fontNames[nameIndex], fontStyles[styleIndex], 28);
	}

	private static Color generateColor() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
}
