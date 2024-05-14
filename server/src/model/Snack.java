package model;

public abstract class Snack
{
  private double price;
  private String type;
  private String size;
  public Snack(double price, String type, String size){
    this.price = price;
    this.size = size;
    this.type = type;
  }

//****************************************Getters and setters***************************************
  public double getPrice()
  {
    return price;
  }

  public String getType()
  {
    return type;
  }

  public String getSize()
  {
    return size;
  }

  public void setPrice(double price)
  {
    this.price = price;
  }

  public void setSize(String size)
  {
    this.size = size;
  }

  public void setType(String type)
  {
    this.type = type;
  }
}
