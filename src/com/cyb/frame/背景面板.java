package com.cyb.frame;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class 背景面板 extends JFrame  
{  
	String picPath = System.getProperty("user.dir")+File.separator+"Chrysanthemum.jpg";
    //创建一个容器  
    Container ct;  
    //创建背景面板。  
    BackgroundPanel bgp;  
      
    //创建一个按钮，用来证明我们的确是创建了背景图片，而不是一张图片。  
    JButton jb;  
    public static void main(String[] args)  
    {  
        new 背景面板();  
    }  
    public 背景面板()  
    {  
        //不采用任何布局方式。  
        ct=this.getContentPane();  
        this.setLayout(null);  
          
        //在这里随便找一张400*300的照片既可以看到测试结果。  
        bgp=new BackgroundPanel((new ImageIcon(picPath)).getImage());  
        bgp.setBounds(0,0,400,300);  
        ct.add(bgp);  
          
        //创建按钮  
        jb=new JButton("测试按钮");  
        jb.setBounds(60,30,160,30);  
        ct.add(jb);  
          
        this.setSize(400,300);  
        this.setLocation(400,300);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
}  
class BackgroundPanel extends JPanel  
{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image im;  
    public BackgroundPanel(Image im)  
    {  
        this.im=im;  
        this.setOpaque(true);  
    }  
    //Draw the back ground.  
    public void paintComponent(Graphics g)  
    {  
        super.paintComponents(g);  
        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);  
          
    }  
} 
