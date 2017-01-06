package com.cyb.youhua;
/**
 *  当前树层级为n，则当前层级节点个数最多为2的n-1次方；
 *  每个节点编号为m，m>=1,则每个节点的父节点索引为（m-1）/2；
 *  每个父节点的左孩子索引为2n-1，右孩子为2n+1（数组二叉树的索引从0开始编号）
 * @author DHUser
 * 
 */
public class 堆使用二叉树原理遍历数组 {
	private static int[] sort = new int[] { 1, 0, 10, 20, 3, 5, 6, 4, 9, 8, 12,
		17, 34, 11 };
	public static void main(String[] args) {
	   int parent= getParentIndex(5);//从索引0开始，构建完全二叉树
	   System.out.println(sort[parent]);
	   int parent1= getParentIndex(6);//从索引0开始，构建完全二叉树
	   System.out.println(sort[parent1]);
	   int left= getChildLeftIndex(5);//从索引0开始，构建完全二叉树
	   System.out.println(sort[left]);
	   int right= getChildRightIndex(5);//从索引0开始，构建完全二叉树
	   System.out.println(sort[right]);
	   //System.out.println(7>>1);
	}
	/**
	 * 父节点位置
	 * 
	 * @paramcurrent
	 * @return
	 */
	private static int getParentIndex(int current) {
		return (current - 1) >> 1;
	}

	/**
	 * 左子节点position注意括号，加法优先级更高
	 * 
	 * @paramcurrent
	 * @return
	 */
	private static int getChildLeftIndex(int current) {
		return (current << 1) + 1;
	}

	/**
	 * 右子节点position
	 * 
	 * @paramcurrent
	 * @return
	 */
	private static int getChildRightIndex(int current) {
		return (current << 1) + 2;
	}
}
