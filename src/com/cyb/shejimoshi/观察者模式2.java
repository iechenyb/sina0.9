package com.cyb.shejimoshi;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 　观察者模式结构中包括四种角色：
　　一、主题：主题是一个接口，该接口规定了具体主题需要实现的方法，比如添加、删除观察者以及通知观察者更新数据的方法。
　　二、观察者：观察者也是一个接口，该接口规定了具体观察者用来更新数据的方法。
　　三、具体主题：具体主题是一个实现主题接口的类，该类包含了会经常发生变化的数据。而且还有一个集合，该集合存放的是观察者的引用。
　　四：具体观察者：具体观察者是实现了观察者接口的一个类。具体观察者包含有可以存放具体主题引用的主题接口变量，
	以便具体观察者让具体主题将自己的引用添加到具体主题的集合中，
 让自己成为它的观察者，或者让这个具体主题将自己从具体主题的集合中删除，使自己不在时它的观察者。
 * @author DHUser
 *
 */
public class 观察者模式2 {
	public static void main(String[] args) {
	       // 创建一个被观察者对象
	       Product p = new Product("电视机", 176);
	       // 创建两个观察者对象
	       NameObserver no = new NameObserver();
	       PriceObserver po = new PriceObserver();
	       // 向被观察对象上注册两个观察者对象
	       p.registObserver(no);
	       p.registObserver(po);
	       // 程序调用setter方法来改变Product的name和price属性
	       p.setName("书桌");
	       p.setPrice(345f);
	    }
}
interface Observer9 {
    void update(Observable o, Object arg);
}
abstract class Observable {
    // 用一个List来保存该对象上所有绑定的事件监听器
    List<Observer9> observers = new ArrayList<Observer9>();
 
    // 定义一个方法，用于从该主题上注册观察者
    public void registObserver(Observer9 o) {
       observers.add(o);
    }
 
    // 定义一个方法，用于从该主题中删除观察者
    public void removeObserver(Observer9 o) {
       observers.add(o);
    }
 
    // 通知该主题上注册的所有观察者
    public void notifyObservers(Object value) {
       // 遍历注册到该被观察者上的所有观察者
       for (java.util.Iterator<Observer9> it = observers.iterator(); it.hasNext();) {
           Observer9 o = (Observer9) it.next();
           // 显式每个观察者的update方法
           o.update(this, value);
       }
    }
}
class Product extends Observable {
    // 定义两个属性
    private String name;
    private double price;
 
    // 无参数的构造器
    public Product() {
    }
 
    public Product(String name, double price) {
       this.name = name;
       this.price = price;
    }
 
    public String getName() {
       return name;
    }
 
    // 当程序调用name的setter方法来修改Product的name属性时
    // 程序自然触发该对象上注册的所有观察者
    public void setName(String name) {
       this.name = name;
       notifyObservers(name);
    }
 
    public double getPrice() {
       return price;
    }
 
    // 当程序调用price的setter方法来修改Product的price属性时
    // 程序自然触发该对象上注册的所有观察者
    public void setPrice(double price) {
       this.price = price;
       notifyObservers(price);
    }
}
class NameObserver implements Observer9 {
    // 实现观察者必须实现的update方法
    public void update(Observable o, Object arg) {
       if (arg instanceof String) {
           // 产品名称改变值在name中
           String name = (String) arg;
           // 启动一个JFrame窗口来显示被观察对象的状态改变
          /* JFrame f = new JFrame("观察者");
           JLabel l = new JLabel("名称改变为：" + name);
           f.add(l);
           f.pack();
           f.setVisible(true);*/
           System.out.println("名称观察者:" + o + "物品名称已经改变为: " + name);
       }
    }
}
class PriceObserver implements Observer9 {
    // 实现观察者必须实现的update方法
    public void update(Observable o, Object arg) {
       if (arg instanceof Double) {
           System.out.println("价格观察者:" + o + "物品价格已经改变为: " + arg);
       }
    }
}
