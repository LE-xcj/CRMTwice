/**
 * 
 */
package edu.zhku.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;

import edu.zhku.classCrawler.ClassCrawler;
import edu.zhku.util.HeaderUtil;
import edu.zhku.util.HttpClientPoolUtil;
import edu.zhku.util.ImgUtil;

/**  
* <p>Title: TestHttpClient.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月5日  
*/
public class TestHttpClient implements ActionListener {
	private JFrame frame = new JFrame();
	private JPanel mainPanle = new JPanel();

	private JButton button = new JButton();
	private JButton ok = new JButton("确认");
	private JTextField input = new JTextField();

	private String src = "";
	CloseableHttpClient hc = HttpClientPoolUtil.getHC();
	Header[] headers = HeaderUtil.getCookie(hc);
	public TestHttpClient() {
		
		//ImgUtil.saveImag(headers, hc);
		ImgUtil.saveImag(headers);
		button = new JButton(new ImageIcon("code/img.jpg"));

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

	public static void main(String[] args) {
		new TestHttpClient();

	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ok) {
			String str = input.getText();
			ClassCrawler.getData(hc, str, headers);

		} else if (button == e.getSource()) {
			
		}
	}
}
