package com.cyb.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.commons.lang.RandomStringUtils;

import com.cyb.erweima.CAPTCHALabel;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = -4655235896173916415L;
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JTextField validateTextField;
	private String randomText;

	public static void main(String args[]) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public LoginFrame() {
		setTitle("系统登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		//setIcon(new ImageIcon("Chrysanthemum.jpg"));//貌似这样可以。
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

		JPanel usernamePanel = new JPanel();
		contentPane.add(usernamePanel);

		JLabel usernameLable = new JLabel("\u7528\u6237\u540D\uFF1A");
		usernameLable.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		usernamePanel.add(usernameLable);

		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		usernamePanel.add(usernameTextField);
		usernameTextField.setColumns(10);

		JPanel passwordPanel = new JPanel();
		contentPane.add(passwordPanel);
		JLabel passwordLabel = new JLabel("\u5BC6 \u7801\uFF1A");
		passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		passwordPanel.add(passwordLabel);
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		passwordPanel.add(passwordField);
		JPanel validatePanel = new JPanel();
		contentPane.add(validatePanel);
		JLabel validateLabel = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
		validateLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		validatePanel.add(validateLabel);
		validateTextField = new JTextField();
		validateTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		validatePanel.add(validateTextField);
		validateTextField.setColumns(5);
		randomText = RandomStringUtils.randomAlphanumeric(4);

		CAPTCHALabel label = new CAPTCHALabel(randomText);// 随机验证码
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		validatePanel.add(label);

		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel);

		JButton submitButton = new JButton("登录");
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("@");
			}
		});
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		buttonPanel.add(submitButton);

		JButton cancelButton = new JButton("退出");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("2");
			}
		});
		cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		buttonPanel.add(cancelButton);

		pack();// 自动调整窗体大小
		// setLocation(com.lixiyu.util.SwingUtil.centreContainer(getSize()));//
		// 让窗体居中显示
	}
}
