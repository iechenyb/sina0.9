package com.cyb.shejimoshi;

import java.util.ArrayList;
import java.util.List;
/**
 * 监听设计模式要包含几个重要的部分:
	1、事件
	2、事件源
	3、响应事件的监听者
 * @author DHUser
 *
 */
public class 监听模式 {
	/**
	 * @description 测试
	 */
	public static void main(String[] args) {
		Programmer pro = new Programmer();// 生成一个事件源对象，将要做事情
		pro.addListener(new PM());// 增加监听者
		pro.addListener(new Director());// 增加监听者
		pro.addListener(new Chief());// 增加监听者
		pro.updateCode();// 程序猿去修改代码
	}
}

/**
 * @description 编程事件
 * 
 */
class Code extends ActionEvent {
	// 这个事件的属性是敲打代码、修改代码、处理时间等等...
	private boolean beat = true;// 默认敲代码
	private boolean update = true;// 默认修改代码
	private long time;

	public boolean isBeat() {
		return beat;
	}

	public void setBeat(boolean beat) {
		this.beat = beat;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}

/**
 * 
 * @description 程序员要根据编程事件的属性进行操作
 * 
 */
class Programmer {
	// 程序猿被n个人监听着
	private List<ActionEventListener> list = new ArrayList<ActionEventListener>();
	public void addListener(ActionEventListener ael) {// 程序猿的监听者们，要同意别人监听才行
		list.add(ael);
	}
	ActionEvent e = new Code();//定义一个编程事件
	// 需要修改时，程序猿进行修改操作
	public void updateCode() {
		if (((Code) e).isUpdate()) {// 如果是真，则需要进行修改
			System.out.println("程序猿修改代码");
			// 监听者们的做法
			for (int i = 0; i < list.size(); ++i) {
				ActionEventListener a = list.get(i);
				a.actionPerformed(e);
			}
		}
	}
}
abstract class ActionEvent {}
interface ActionEventListener {
	public void actionPerformed(ActionEvent e);
}

class PM implements ActionEventListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("修改完代码后，项目经理说，那里还不行，缺个分号；");
	}

}

class Director implements ActionEventListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("修改完代码后，运营总监说，你没做出我想要的感觉");
	}
}

class Chief implements ActionEventListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("修改完代码后，总经理说，再做几个方案！给我选");
	}
}
