package com.cyb.youhua;

import java.util.Random;
/**
 * 优化主要是对局部或者全局有序的概率大的进行优化
 * * @author DHUser
 *
 */
public class 冒泡排序 {
	 static int[] arr = null;  
	 static int[] arr1 = null;  
	 public static void main(String[] args) {           
	        //构造数据  
		 	//constructDataArray(30000);
		 	constructOrderedDataArray(50000);
	        System.out.println("---------排序前1-----------");  
	        //printArrayData(arr);  
	        //冒泡排序  
	        bubbleSort4(arr);  
	        System.out.println("---------排序后1-----------");  
	        //printArrayData(arr); 
	        
	       // int[] arr1 = constructDataArray(1500);  
	        System.out.println("---------排序前2-----------");  
	        //printArrayData(arr1);  
	        commonsort(arr1);
	        System.out.println("---------排序后2-----------");  
	        //printArrayData(arr1); 
	    }  
	    public static long jiecheng(long num){
	    	long total = 1;
	    	for(int i=1;i<=num;i++){
	    		total = total*i;
	    	}
	    	return total;
	    }  
	    //构造数据  
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
	    //构造数据  
	    public static int[] constructOrderedDataArray(int length){  
	        arr = new int[length];  
	        arr1 = new int[length];  
	        for(int i=0;i<length;i++){  
	            arr[i] = i;
	            arr1[i] = arr[i];  
	        }  
	        return arr;  
	    }  
	      
	      
	    /** 
	     * 引入标志位，默认为true 
	     * 如果前后数据进行了交换，则为true，否则为false。如果没有数据交换，则排序完成。 
	     * @param arr 
	     */  
	    public static int[] bubbleSort4(int[] arr){  
	    	long s = System.currentTimeMillis();
	        boolean flag = true;  
	        int n = arr.length;  
	        int total = 0;
	        while(flag){  
	            flag = false;  
	            for(int j=0;j<n-1;j++){  
	                if(arr[j] >arr[j+1]){  
	                    //数据交换  
	                    int temp = arr[j];  
	                    arr[j] = arr[j+1];  
	                    arr[j+1] = temp;  
	                    //设置标志位  
	                    flag = true;  
	                    total = total +1;
	                }  
	            }  
	            n--;  
	        }  
	        long e = System.currentTimeMillis();
	        System.out.println(e-s+",实际比较次数="+total);
	        return arr;  
	    }  
	    public static void commonsort(int[] values){
	    	long s = System.currentTimeMillis();
	        int temp;
	        long total = 0;
	        for(int i=0;i<values.length;i++){//趟数
	          for(int j=0;j<values.length-i-1;j++){//比较次数
	            if(values[j]>values[j+1]){
	              total = total +1;
	              temp=values[j];
	              values[j]=values[j+1];
	              values[j+1]=temp;
	            }
	          }
	        } 
	        long e = System.currentTimeMillis();
	        System.out.println((e-s)+",共比较次数,"+total);	        
	      }
	   
	    //打印数据  
	    public static void printArrayData(int[] arr){  
	        for(int d :arr){  
	            System.out.print(d + ",");  
	        }  
	        System.out.println();  
	    }  
}
