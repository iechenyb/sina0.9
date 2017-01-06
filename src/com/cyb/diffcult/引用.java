package com.cyb.diffcult;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import com.cyb.collection.User;
//从JDK 1.2版本开始，把对象的引用分为4种级别，从而使程序能更加灵活地控制对象的生命周期。这4种级别由高到低依次为：强引用、软引用、弱引用和虚引用。
public class 引用 {
public static void main(String[] args) {
	User user = new User();
	WeakReference x = new WeakReference(user);
	//StrongReference y;
	SoftReference z = new SoftReference(user);
	ReferenceQueue  queue  =  new   ReferenceQueue();
	PhantomReference k = new PhantomReference(user, queue);
	System.out.println(queue.poll());
}
}
