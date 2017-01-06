package com.cyb.diffcult;

import java.util.Random;
/**
 *  Java语言中goto是保留关键字，没有goto语句，也没有任何使用goto关键字的地方。
	Java中也可在特定情况下，通过特定的手段，来实现goto的功能。显然Java不愿意开发者随意跳转程序。下面解释两个特定：
	特定情况：只有在循环体内，比如for、while语句（含do...while语句）中。
	特定手段：语句标签和循环控制关键字break、continue，语法格式是：break/continue 语句标签。
 * @author DHUser
 *
 */
public class Goto {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		outer: 
			for (int i = 0; i < 10; i++) {
			       System.out.println("\nouter_loop:" + i);
			inner: 
				for (int k = 0; i < 10; k++) {
				System.out.print(k + " ");
				int x = new Random().nextInt(10);
				if (x > 7) {
					System.out.print(" >>x == " + x
							+ "，结束inner循环，继续迭代执行outer循环了！");
					continue outer;
				}
				if (x == 1) {
					System.out.print(" >>x == 1，跳出并结束整个outer和inner循环！");
					break outer;
				}
			}//end inner
		}
		System.out.println("------>>>所有循环执行完毕！");
	}
}
