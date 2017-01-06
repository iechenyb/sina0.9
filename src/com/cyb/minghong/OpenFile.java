package com.cyb.minghong;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OpenFile implements ActionListener {  
    JFrame frame = new JFrame("QQ小工具");// 框架布局  
    JTabbedPane tabPane = new JTabbedPane();// 选项卡布局  
    Container con = new Container();//  
    Container con1 = new Container();//  
    JLabel wj1 = new JLabel("QQ文件1");  
    JLabel wj2 = new JLabel("QQ文件2");  
    JLabel wj = new JLabel("QQ文件");  
    JLabel enlabel = new JLabel("QQ内容");  
    JLabel delabel = new JLabel("QQ内容");  
    JTextField wj1path = new JTextField();// TextField 目录的路径  
    JTextField wjpath = new JTextField();// 文件的路径  
    JTextField wj2path = new JTextField();// TextField 目录的路径  
    JTextArea encodestr = new JTextArea();
    JTextArea decodestr = new JTextArea();
    JButton wj1xz = new JButton("选择");// 选择  
    JButton wj2xz = new JButton("选择");// 选择  
    JButton wjxz = new JButton("选择");// 选择  
    JFileChooser jfc = new JFileChooser();// 文件选择器  
    JButton enbtn = new JButton("比较差异");//
    JButton debtn = new JButton("解析QQ");//     
    OpenFile() {  
        jfc.setCurrentDirectory(new File("d://"));// 文件选择器的初始目录定为d盘  
          
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
          
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
          
        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置  
        frame.setSize(580, 500);// 设定窗口大小  
        frame.setContentPane(tabPane);// 设置布局  
        wj1.setBounds(10, 10, 70, 20);  
        wj1path.setBounds(75, 10, 320, 20);  
        wj2.setBounds(10, 30, 70, 20);  
        wj2path.setBounds(75, 30, 320, 20); 
        wj1xz.setBounds(390, 10, 50, 20);  
        wj2xz.setBounds(390, 30, 50, 20);  
        wj.setBounds(10, 35, 70, 20);  
        wjpath.setBounds(75, 35, 320, 20);  
        wjxz.setBounds(390, 35, 50, 20);  
        
        enlabel.setBounds(10, 60, 120, 20);  
        encodestr.setBounds(75, 60, 450, 160);  //x,y,w,h
        delabel.setBounds(10, 60, 120, 20);  
        decodestr.setBounds(75, 60, 450, 160);  //x,y,w,h
       // decodestr.disable();
        decodestr.setText("！");
        decodestr.setBackground(Color.WHITE);
        enbtn.setBounds(180, 260, 120, 20);  
        debtn.setBounds(180, 260, 120, 20);  //x,y,w,h
        wj1xz.addActionListener(this); // 添加事件处理  
        wj2xz.addActionListener(this); // 添加事件处理  
        wjxz.addActionListener(this); // 添加事件处理  
        enbtn.addActionListener(this); // 添加事件处理  
        debtn.addActionListener(this); // 添加事件处理  
        
        con.add(wj1);
        con.add(wj1path);
        con.add(wj1xz);  
        con.add(wj2xz);  
        con.add(enbtn);  
        con.add(enlabel);
        con.add(encodestr);
        con.add(wj2);
        con.add(wj2path);
        
        con1.add(wj);  
        con1.add(wjpath);  
        con1.add(wjxz);  
        con1.add(debtn);         
        con1.add(delabel);
        con1.add(decodestr);
        frame.setVisible(true);// 窗口可见  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序  
        tabPane.add("解析QQ从网页上", con1);// 添加布局1
        tabPane.add("比较前后QQ变化", con);// 添加布局1
    }  
    /** 
     * 时间监听的方法 
     */  
    public void actionPerformed(ActionEvent e) {  
        if (e.getSource().equals(wj1xz)) {// 判断触发方法的按钮是哪个  
            jfc.setFileSelectionMode(0);// 设定只能选择到文件夹  
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
            if (state == 1) {  
                return;  
            } else {  
                File f = jfc.getSelectedFile();// f为选择到的目录  
                wj1path.setText(f.getAbsolutePath());  
                System.out.println(f.getAbsolutePath());  
                //JOptionPane.showMessageDialog(null, mlpath.getText(), "提示", 2);  
            }  
        }  
        if (e.getSource().equals(wj2xz)) {// 判断触发方法的按钮是哪个  
            jfc.setFileSelectionMode(0);// 设定只能选择到文件夹  
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
            if (state == 1) {  
                return;  
            } else {  
                File f = jfc.getSelectedFile();// f为选择到的目录  
                wj2path.setText(f.getAbsolutePath());  
                System.out.println(f.getAbsolutePath());  
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
        	if(wj1path.getText()!=null&&!"".equals(wj1path.getText())&&wj2path.getText()!=null&&!"".equals(wj2path.getText())){	        	
	            JOptionPane.showMessageDialog(null, wj1path.getText(), "提示", 2);  
	            String qqs = 解析扣扣号.initCompileQQ(wj1path.getText(), wj2path.getText());
	            encodestr.setText(qqs);
        	}else{
        		JOptionPane.showMessageDialog(null, "QQ文件不能为空！", "提示", 2);  
        	}
        }  
        if (e.getSource().equals(debtn)) {  
            // 弹出对话框可以改变里面的参数具体得靠大家自己去看，时间很短  
        	if(wjpath.getText()!=null&&!"".equals(wjpath.getText())){
        		System.out.println(wjpath.getText());
        		String savePath = 解析扣扣号.getQQFromFile(wjpath.getText());
        		decodestr.setText("解析后的文件存储的路径\n"+savePath);
        	}else{
        		JOptionPane.showMessageDialog(null, "QQ文件不能为空！", "提示", 2);  
            }
        }  
    }  
    public static void main(String[] args) {  
        new OpenFile();  
    }  
} 
