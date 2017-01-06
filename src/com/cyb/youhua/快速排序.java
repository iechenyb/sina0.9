package com.cyb.youhua;

public class 快速排序 {
	public static void main(String[] args) {
		//int a[] = new int[]{6,2,7,3,8,9};
		//int a[] = new int[]{2,3,6,7,8,9};
		int a[] = new int[]{9,8,7,6,3,2};
		new 快速排序().sort(a,0,a.length-1);//优化方案，每次使用判断是否已经全部有序
		
	}
	public static void print(int a[]){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+",");
		}
		System.out.println();
	}
	public void sort(int arr[], int low, int high) {//每一轮povit不变
		print(arr);
		int l = low;
		int h = high;
		int povit = arr[low];
		while (l < h) {
			
			while (l < h && arr[h] >= povit){//大值存右侧
				h--;
			}
			
			if (l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				l++;
			}

			while (l < h && arr[l] <= povit){
				l++;
			}
			
			if (l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				h--;
			}
		}
		System.out.print("l=" + (l + 1) + ",h=" + (h + 1) + ",povit=" + povit
				+ "\n");		
		if (l > low)
			sort(arr, low, l - 1);
		if (h < high)
			sort(arr, l + 1, high);
	}
}
