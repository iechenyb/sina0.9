package com.cyb.youhua;

import java.util.Random;

public class 选择排序 {
	 static int[] arr = null;  
	 static int[] arr1 = null; 
	public static int[] constructDataArray(int length){  
        arr = new int[length];  
        arr1 = new int[length];  
        Random random = new Random();  
        for(int i=0;i<length;i++){  
            arr[i] = random.nextInt(length);
            arr1[i] = arr[i];  
        }  
        return arr;  
    }  
		public static void main(String[] args) {  
			constructDataArray(150000);
	        // TODO Auto-generated method stub  
	         Integer[] list={49,38,65,97,76,13,27,14,10};  
	         //快速排序  
	         /*QuicSort qs=new QuicSort();  
	         qs.quick(list);  */
	         //冒泡排序  
	        /* BubbleSort bs=new BubbleSort();  
	         bs.bubble(list);*/  
	         //选择排序  
	         _choiceSort(arr);  
	           
	         for(int i=0;i<list.length;i++){  
	             System.out.print(arr[i]+" ");  
	         }  
	         System.out.println();  
	    } 
	    public static void _choiceSort(int[] a) { 
	    	int count = 0;
	    	int count1 = 0;
	        if (a == null || a.length <= 0) {  
	            return;  
	        }  
	        for (int i = 0; i < a.length; i++) {  
	            int min = i; /* 将当前下标定义为最小值下标 */  
	  
	            for (int j = i + 1; j < a.length; j++) {  
	                if (a[min] > a[j]) { /* 如果有小于当前最小值的关键字 */  
	                    min = j; /* 将此关键字的下标赋值给min */  
	                    count ++;
	                }  
	            }  
	            if (i != min) {/* 若min不等于i，说明找到最小值，交换 */  
	                int tmp = a[min];  
	                a[min] = a[i];  
	                a[i] = tmp;  
	                count1++;
	            }  
	        }  
	        System.out.println("原始算法交换次数："+count);
	        System.out.println("优化算法交换次数："+count1);
	    }  
}
