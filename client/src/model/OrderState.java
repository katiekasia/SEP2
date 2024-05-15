package model;

public abstract class OrderState
{
public abstract void expire(Order order);
public void cancel(Order order){}
public String status(){return getClass().getSimpleName();}
}
