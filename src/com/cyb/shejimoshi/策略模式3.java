package com.cyb.shejimoshi;

public class 策略模式3 {
	public static void main(String[] args) {
		//客户端没有选择打折策略类
	    DiscountContext dc = new DiscountContext(new OldDiscount());
	    
	    double price1 = 79;
	    dc.setDiscount(new OldDiscount());
	    //使用默认的打折策略
	    System.out.println("79元的书默认打折后的价格是："+ dc.getDiscountPrice(price1));
	    //客户端选择合适的VIP打折策略
	    dc.setDiscount(new VipDiscount());
	    double price2 = 89;
	    //使用VIP打折得到打折价格
	    System.out.println("89元的书对VIP用户的价格是："+ dc.getDiscountPrice(price2));
	}
}
interface DiscountStrategy
{
    //定义一个用于计算打折价的方法
    double getDiscount(double originPrice);
}
class OldDiscount implements DiscountStrategy {
    // 重写getDiscount()方法，提供旧书打折算法
    public double getDiscount(double originPrice) {
       System.out.println("使用旧书折扣...");
       return originPrice * 0.7;
    }
}

//实现DiscountStrategy接口，实现对VIP打折的算法
class VipDiscount implements DiscountStrategy {
    // 重写getDiscount()方法，提供VIP打折算法
    public double getDiscount(double originPrice) {
       System.out.println("使用VIP折扣...");
       return originPrice * 0.5;
    }
}
//策略定义
class DiscountContext
{
    //组合一个DiscountStrategy对象
    private DiscountStrategy strategy;
    //构造器，传入一个DiscountStrategy对象
    public DiscountContext(DiscountStrategy strategy)
    {
       this.strategy  = strategy;
    }
    //根据实际所使用的DiscountStrategy对象得到折扣价
    public double getDiscountPrice(double price)
    {
       //如果strategy为null，系统自动选择OldDiscount类
       if (strategy == null)
       {
           strategy = new OldDiscount();
       }
       return this.strategy.getDiscount(price);
    }
    //提供切换算法的方法
    public void setDiscount(DiscountStrategy strategy)
    {
       this.strategy = strategy;
    }
}