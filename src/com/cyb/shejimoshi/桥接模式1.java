package com.cyb.shejimoshi;

public class 桥接模式1 {
	public static void main(String[] args)
    {
       //下面将得到“辣味”的牛肉面
       AbstractNoodle noodle1 = new BeefMoodle(
           new PepperySytle());
       noodle1.eat();
       //下面将得到“不辣”的牛肉面
       AbstractNoodle noodle2 = new BeefMoodle(
           new PlainStyle());
       noodle2.eat();
       //下面将得到“辣味”的猪肉面
       AbstractNoodle noodle3 = new PorkyNoodle(
           new PepperySytle());
       noodle3.eat();
       //下面将得到“不辣”的猪肉面
       AbstractNoodle noodle4 = new PorkyNoodle(
           new PlainStyle());
       noodle4.eat();
    }
}
interface Peppery
{
    String style();
}
class PepperySytle implements Peppery
{
    //实现"辣味"风格的方法
    public String style()
    {
       return"辣味很重，很过瘾...";
    }
}
class PlainStyle implements Peppery
{
    //实现"不辣"风格的方法
    public String style()
    {
       return"味道清淡，很养胃...";
    }
}
//口味的桥梁
abstract class AbstractNoodle
{
    //组合一个Peppery变量，用于将该维度的变化独立出来
    protected Peppery style;
    //每份Noodle必须组合一个Peppery对象
    public AbstractNoodle(Peppery style)
    {
       this.style = style;
    }
    public abstract void eat();
}
class PorkyNoodle extends AbstractNoodle
{
    public PorkyNoodle(Peppery style)
    {
       super(style);
    }
    //实现eat()抽象方法
    public void eat()
    {
       System.out.println("这是一碗稍嫌油腻的猪肉面条。"
           + super.style.style());
    }
}
class BeefMoodle extends AbstractNoodle
{
    public BeefMoodle(Peppery style)
    {
       super(style);
    }
    //实现eat()抽象方法
    public void eat()
    {
       System.out.println("这是一碗美味的牛肉面条。"
           + super.style.style());
    }
}