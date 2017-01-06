package com.cyb.frame;

import java.awt.Color;

import javax.swing.JFrame;

public class 面板背景色 extends JFrame  
{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args)  
    {  
        new 面板背景色();  
    }       
     
    public 面板背景色()  
    {  
        this.setSize(400,300);  
        this.setLocation(400,300);  
        this.setBackground(Color.blue);  
        this.getContentPane().setBackground(Color.red);  
        this.getContentPane().setVisible(false);//如果改为true那么就变成了红色。  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
}  