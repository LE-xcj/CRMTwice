package edu.zhku.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.zhku.classCrawler.Main;
import edu.zhku.util.HtmlUtil;

public class Test implements ActionListener {

	private JFrame frame = new JFrame();
	private JPanel mainPanle = new JPanel();

	private JButton button = new JButton();
	private JButton ok = new JButton("确认");
	private JTextField input = new JTextField();

	private String src = "";

	public Test() {

		String html = Main.getHtml();
		src = Main.getSrc(Main.getImgTag(html));
		Main.saveImag(src);

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
		new Test();

	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ok) {
			String str = input.getText();
			String data = Main.getData(str);
			System.out.println(data);
			try {
				HtmlUtil.saveToExcel(data);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (button == e.getSource()) {
			Main.saveImag(src);
			button = new JButton(new ImageIcon("img.jpg"));
			button.addActionListener(this);
		}
	}
}
