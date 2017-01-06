package com.cyb.diffcult;

public class 异常捕获 {
	public static void main (String[] args) throws Exception {
		m3();
		System.out.println("------------------");
		m3();
		System.out.println("------------------");
		m2();
		System.out.println("------------------");
		m1();
		
	}
	public static void m5() throws Exception{
		try{
			try{
				throw new FatherEx();
			}catch(ChildEx f){
				System.out.println("catch Father exception !");
				throw f;
			}
		}catch(ChildEx e){
			System.out.println("catch Child exception!");
			return ;
		}finally{
			System.out.println("over!");//打印
		}
	}
	public static void m4() throws Exception{
		try{
			try{
				throw new FatherEx();
			}catch(FatherEx f){
				System.out.println("catch Father exception !");//print
				throw f;
			}
		}catch(ChildEx e){
			System.out.println("catch Child exception!");
			return ;
		}finally{
			System.out.println("over!");//print
		}
	}
	public static void m3() throws Exception{
		try{
			try{
				throw new ChildEx();
			}catch(FatherEx f){
				System.out.println("catch Father exception !");//print
				throw f;
			}
		}catch(ChildEx e){
			System.out.println("catch Child exception!");//print
			return ;
		}finally{
			System.out.println("over!");//print
		}
	}
	public static void m1(){
		try{
			int i=0;
			i++;
			System.out.println(i);
			int b = i/0;
			System.out.println(b);
			return ;
		}catch(Exception e){
			System.out.println("出现异常！"+e.getMessage());
			//System.exit(100);//-1 0 1 不执行finally
			return ;//依旧执行清理逻辑
		}finally{
			System.out.println("清理现场!");
		}
	}
	public static void m2(){
		try{
			int i=0;
			i++;
			System.out.println(i);
			return ;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			System.out.println("清理现场!");
		}
	}
	
}
class FatherEx extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
class ChildEx extends FatherEx{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
