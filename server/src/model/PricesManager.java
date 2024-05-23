package model;

public class PricesManager
{
  private double standardTicketPrice;
  private double vipTicketPrice;
  private double popcornPrice;
  private double nachosPrice;
  private double candiesPrice;
  private double oreoPrice;
  private double peanutsPrice;
  private double colaPrice;
  private double pepsiPrice;
  private double fantaPrice;
  private double redbullPrice;
  private double tuborgPrice;

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
  public double getPriceForSnack(String snack){
    switch (snack){
      case "candies":
        return getCandiesPrice();

      case "nachos":
        return getNachosPrice();

      case "oreo":
        return getOreoPrice();

      case "peanuts":
        return getPeanutsPrice();

      case "cola":
        return getColaPrice();

      case "pepsi":
        return getPepsiPrice();

      case "tuborg":
        return getTuborgPrice();

      case "redbull":
        return getRedbullPrice();

      case "fanta":
        return getFantaPrice();

      case "popcorn":
        return getPopcornPrice();

    }
    throw new IllegalArgumentException("No such snack found.");
  }
  public double getNachosPrice()
  {
    return nachosPrice;
  }

  public double getCandiesPrice()
  {
    return candiesPrice;
  }

  public double getColaPrice()
  {
    return colaPrice;
  }

  public double getFantaPrice()
  {
    return fantaPrice;
  }

  public double getOreoPrice()
  {
    return oreoPrice;
  }

  public double getPeanutsPrice()
  {
    return peanutsPrice;
  }

  public double getPepsiPrice()
  {
    return pepsiPrice;
  }

  public double getRedbullPrice()
  {
    return redbullPrice;
  }

  public double getTuborgPrice()
  {
    return tuborgPrice;
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

  public void setCandiesPrice(double candiesPrice)
  {
    if (nachosPrice >= 0){
      this.candiesPrice = candiesPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setColaPrice(double colaPrice)
  {
    if (nachosPrice >= 0){
      this.colaPrice = colaPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setFantaPrice(double fantaPrice)
  {
    if (nachosPrice >= 0){
this.fantaPrice = fantaPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setOreoPrice(double oreoPrice)
  {
    if (nachosPrice >= 0){
this.oreoPrice = oreoPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setPeanutsPrice(double peanutsPrice)
  {
    if (nachosPrice >= 0){
this.peanutsPrice = peanutsPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setPepsiPrice(double pepsiPrice)
  {
    if (nachosPrice >= 0){
this.pepsiPrice = pepsiPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setRedbullPrice(double redbullPrice)
  {
    if (nachosPrice >= 0){
      this.redbullPrice = redbullPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }

  public void setTuborgPrice(double tuborgPrice)
  {
    if (nachosPrice >= 0){
      this.tuborgPrice = tuborgPrice;
    }
    else throw new IllegalArgumentException("Price cannot be lower than 0.");
  }
}
