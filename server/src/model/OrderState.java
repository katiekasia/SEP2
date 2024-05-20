package model;

public abstract class OrderState
{
public void expire(Order order){}
public abstract void cancel(Order order);
public abstract String status();
}
