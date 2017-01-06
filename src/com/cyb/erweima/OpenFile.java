package com.cyb.erweima;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.cyb.date.DateUtil;

public class OpenFile implements ActionListener {  
    JFrame frame = new JFrame("二维码小工具");// 框架布局  
    JTabbedPane tabPane = new JTabbedPane();// 选项卡布局  
    Container con = new Container();//  
    Container con1 = new Container();//  
    JLabel ml = new JLabel("二维码目录");  
    JLabel wj = new JLabel("二维码文件");  
    JLabel enlabel = new JLabel("二维码内容");  
    JLabel delabel = new JLabel("二维码内容");  
    JTextField mlpath = new JTextField();// TextField 目录的路径  
    JTextField wjpath = new JTextField();// 文件的路径  
    JTextArea encodestr = new JTextArea();
    JTextArea decodestr = new JTextArea();
    JButton mlxz = new JButton("选择");// 选择  
    JButton wjxz = new JButton("选择");// 选择  
    JFileChooser jfc = new JFileChooser();// 文件选择器  
    JButton enbtn = new JButton("生成二维码");//
    JButton debtn = new JButton("解析二维码");//
      
    OpenFile() {  
        jfc.setCurrentDirectory(new File("d://"));// 文件选择器的初始目录定为d盘  
          
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
          
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
          
        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置  
        frame.setSize(580, 500);// 设定窗口大小  
        frame.setContentPane(tabPane);// 设置布局  
        ml.setBounds(10, 10, 70, 20);  
        mlpath.setBounds(75, 10, 320, 20);  
        mlxz.setBounds(390, 10, 50, 20);  
        wj.setBounds(10, 35, 70, 20);  
        wjpath.setBounds(75, 35, 320, 20);  
        wjxz.setBounds(390, 35, 50, 20);  
        
        enlabel.setBounds(10, 60, 120, 20);  
        encodestr.setBounds(75, 60, 350, 160);  //x,y,w,h
        delabel.setBounds(10, 60, 120, 20);  
        decodestr.setBounds(75, 60, 350, 160);  //x,y,w,h
        decodestr.disable();
        decodestr.setText("不可以编辑，此处显示解码内容！");
        decodestr.setBackground(Color.DARK_GRAY);
        enbtn.setBounds(180, 260, 120, 20);  
        debtn.setBounds(180, 260, 120, 20);  //x,y,w,h
        mlxz.addActionListener(this); // 添加事件处理  
        wjxz.addActionListener(this); // 添加事件处理  
        enbtn.addActionListener(this); // 添加事件处理  
        debtn.addActionListener(this); // 添加事件处理  
        
        con.add(ml);
        con.add(mlpath);
        con.add(mlxz);  
        con.add(enbtn);  
        con.add(enlabel);
        con.add(encodestr);
        
        con1.add(wj);  
        con1.add(wjpath);  
        con1.add(wjxz);  
        con1.add(debtn);         
        con1.add(delabel);
        con1.add(decodestr);
        frame.setVisible(true);// 窗口可见  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序  
        tabPane.add("加密", con);// 添加布局1
        tabPane.add("解析", con1);// 添加布局1
    }  
    /** 
     * 时间监听的方法 
     */  
    public void actionPerformed(ActionEvent e) {  
        if (e.getSource().equals(mlxz)) {// 判断触发方法的按钮是哪个  
            jfc.setFileSelectionMode(1);// 设定只能选择到文件夹  
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
            if (state == 1) {  
                return;  
            } else {  
                File f = jfc.getSelectedFile();// f为选择到的目录  
                mlpath.setText(f.getAbsolutePath());  
                //JOptionPane.showMessageDialog(null, mlpath.getText(), "提示", 2);  
            }  
        }  
        // 绑定到选择文件，先择文件事件  
        if (e.getSource().equals(wjxz)) {  
            jfc.setFileSelectionMode(0);// 设定只能选择到文件  
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
            if (state == 1) {  
                return;// 撤销则返回  
            } else {  
                File f = jfc.getSelectedFile();// f为选择到的文件  
                wjpath.setText(f.getAbsolutePath());  
                //JOptionPane.showMessageDialog(null, wjpath.getText(), "提示", 2);  
            }  
        }  
        if (e.getSource().equals(enbtn)) { 
        	
        	String path = mlpath.getText()+"/"+DateUtil.date2long14(new Date())+".png"; 
        	String content = encodestr.getText();
        	if(mlpath.getText()!=null&&!"".equals(mlpath.getText())){
	        	if(content!=null&&!"".equals(encodestr.getText())){
	        		QRCodeEncoderHandler.encoderQRCode(content,path );
	        		JOptionPane.showMessageDialog(null, mlpath.getText(), "提示", 2);  
	        	}else{
	        		 // 弹出对话框可以改变里面的参数具体得靠大家自己去看，时间很短  
	        		JOptionPane.showMessageDialog(null, "二维码内容不能为空", "提示", 2);  
	        	}
        	}else{
        		JOptionPane.showMessageDialog(null, "二维码目录不能为空！", "提示", 2);  
        	}
        }  
        if (e.getSource().equals(debtn)) {  
            // 弹出对话框可以改变里面的参数具体得靠大家自己去看，时间很短  
        	if(wjpath.getText()!=null&&!"".equals(wjpath.getText())){
        		String code = ZxingHandler.decode(wjpath.getText());
        		decodestr.setText(code);
        	}else{
        		JOptionPane.showMessageDialog(null, "二维码文件不能为空！", "提示", 2);  
            }
        }  
    }  
    public static void main(String[] args) {  
        new OpenFile();  
    }  
} 
