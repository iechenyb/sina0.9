package com.cyb.shejimoshi;

public class 策略模式2 {
	public static void main(String[] args) {
		  Context context;
	      context = new Context(new ConcreteStrategyA());

	      context.contextInterface();

	      context = new Context(new ConcreteStrategyB());

	      context.contextInterface();

	      context = new Context(new ConcreteStrategyC());

	      context.contextInterface();
	}
}
abstract class Strategy {
   // 算法方法
   public abstract void algorithmInterface();
}
class ConcreteStrategyA extends Strategy {
   @Override
   public void algorithmInterface() {
      System.out.println("算法A实现");
   }
}
class ConcreteStrategyB extends Strategy {
   @Override
   public void algorithmInterface() {
      System.out.println("算法B实现");
  }
}
class ConcreteStrategyC extends Strategy {
	   @Override
	   public void algorithmInterface() {
	      System.out.println("算法C实现");
	  }
	}
class Context {
   private Strategy strategy;
   public Context(Strategy strategy) {
      this.strategy = strategy;

   }
   public void contextInterface() {
      strategy.algorithmInterface();
   }
}