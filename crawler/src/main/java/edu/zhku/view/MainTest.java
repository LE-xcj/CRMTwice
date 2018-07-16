/**
 * 
 */
package edu.zhku.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.http.impl.client.CloseableHttpClient;

import edu.zhku.classCrawler.Main;
import edu.zhku.crawler.Crawler;
import edu.zhku.util.HeaderUtil;
import edu.zhku.util.HtmlUtil;
import edu.zhku.util.HttpClientPoolUtil;
import edu.zhku.util.ImgUtil;

/**  
* <p>Title: MainTest.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月6日  
*/
public class MainTest implements ActionListener{
	private JFrame frame = new JFrame();
	private JPanel mainPanle = new JPanel();

	private JButton button = new JButton();
	private JButton ok = new JButton("确认");
	private JTextField input = new JTextField();

	private String cookie = null;
	private int floor = 1;
	public MainTest(String imgName, int floor) {
		init(imgName);
		this.floor = floor;
	}
	
	/**
	 * @param imgName
	 */
	private void init(String imgName) {
		
		// TODO Auto-generated method stub
		CloseableHttpClient hc = HttpClientPoolUtil.getHC();
		cookie = HeaderUtil.getCookieValue(hc);
		ImgUtil.saveImag(cookie, "code/" + imgName);
		
		
		button = new JButton(new ImageIcon("code/"+ imgName));

		mainPanle.setLayout(null);
		mainPanle.add(button);
		mainPanle.add(ok);
		mainPanle.add(input);

		button.setBounds(400, 0, 200, 100);
		ok.setBounds(400, 100, 100, 50);
		input.setBounds(200, 100, 100, 50);

		ok.addActionListener(this);
		button.addActionListener(this);

		frame.setBounds(200, 100, 875, 500);
		frame.setContentPane(mainPanle);
		frame.setVisible(true);
	}

	public MainTest() {
		init("code/img.jpg");
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ok) {
			String code = input.getText();
			
			if(floor == 1) {
				for(int i=3; i<=4; ++i) {
					String room = floor + "0" + i;
					if(i == 3) {
						room = "0" + floor + "0" + i;
					}
					String data = Crawler.getData(cookie, code, room);
					System.out.println(data);
					try {
						HtmlUtil.saveToExcel(data);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}else {
				for(int i=1; i<11; ++i) {
					
					if(i == 5) {
						continue;
					}
					String room = null;
					if (i < 10) {
						room = floor + "0" + i;
					} else {
						room = ("" + floor) + i;
					}
					String data = Crawler.getData(cookie, code, room);
					try {
						HtmlUtil.saveToExcel(data);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
		} 
	}
	
	public static void main(String[] args) {
		new MainTest();
	}
}
