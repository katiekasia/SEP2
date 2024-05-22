package model;

public class PricesManager
{
  private double standardTicketPrice;
  private double vipTicketPrice;
  private double popcornPrice;
  private double nachosPrice;

  public PricesManager(){}

  public double getPriceForSize(double price, String size){
    switch (size){
      case "M":
      return   price * 1.3;
      case "L":
        return price * 1.5;
      default:
        return price;
    }
  }
  public double getNachosPrice()
  {
    return nachosPrice;
  }

  public double getPopcornPrice()
  {
    return popcornPrice;
  }

  public double getStandardTicketPrice()
  {
    return standardTicketPrice;
  }

  public double getVipTicketPrice()
  {
    return vipTicketPrice;
  }

  public void setNachosPrice(double nachosPrice)
  {
    if (nachosPrice >= 0){
      this.nachosPrice = nachosPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setPopcornPrice(double popcornPrice)
  {
    if (popcornPrice >= 0){
      this.popcornPrice = popcornPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setStandardTicketPrice(double standardTicketPrice)
  {
    if (standardTicketPrice >= 0){
      this.standardTicketPrice = standardTicketPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setVipTicketPrice(double vipTicketPrice)
  {
    if (vipTicketPrice >= 0){
      this.vipTicketPrice = vipTicketPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }
//  if (nachosPrice >= 0){
//
//}
//    else throw new IllegalArgumentException("Price cannot be lower than 0.");
}
