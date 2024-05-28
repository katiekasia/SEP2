package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

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

  public double getPriceForSize(double price, String size) {
    double finalPrice;
    switch (size) {
      case "M":
        finalPrice = price * 1.3;
        break;
      case "L":
        finalPrice = price * 1.5;
        break;
      default:
        finalPrice = price;
    }
    BigDecimal bd = new BigDecimal(finalPrice).setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
  public double getPriceForTicket(String type){
    if (type.equals("standard")){
      return getStandardTicketPrice();
    }else if (type.equals("vip")){
      return getVipTicketPrice();
    }
    throw new IllegalArgumentException("No such ticket type");
  }
  public void changePrice(String item, double newPrice){
    switch (item){
      case "candies":
        setCandiesPrice(newPrice);
        break;
      case "nachos":
        setNachosPrice(newPrice);
        break;
      case "oreo":
        setOreoPrice(newPrice);
        break;
      case "peanuts":
        setPeanutsPrice(newPrice);
        break;
      case "cola":
        setColaPrice(newPrice);
        break;
      case "pepsi":
        setPepsiPrice(newPrice);
        break;
      case "tuborg":
        setTuborgPrice(newPrice);
        break;
      case "redbull":
        setRedbullPrice(newPrice);
        break;
      case "fanta":
        setFantaPrice(newPrice);
        break;
      case "popcorn":
        setPopcornPrice(newPrice);
        break;
      case "standard":
        setStandardTicketPrice(newPrice);
        break;
      case "vip":
        setVipTicketPrice(newPrice);
        break;
    }
  }

 public double getPriceForSizeOfSnack(String snackType, String size)
  {
    switch (snackType)
    {
      case "popcorn":
        return getPriceForSize(getPopcornPrice(),
            size);
      case "nachos":
        return getPriceForSize(getNachosPrice(),
            size);
      case "candies":
        return getPriceForSize(getCandiesPrice(),
            size);
      case "tuborg":
        return getPriceForSize(getTuborgPrice(),
            size);
      case "oreo":
        return getPriceForSize(getOreoPrice(),
            size);
      case "cola":
        return getPriceForSize(getColaPrice(),
            size);
      case "pepsi":
        return getPriceForSize(getPepsiPrice(),
            size);
      case "fanta":
        return getPriceForSize(getFantaPrice(),
            size);
      case "redbull":
        return getPriceForSize(getRedbullPrice(),
            size);
      case "peanuts":
        return getPriceForSize(getPeanutsPrice(),
            size);
    }
    return 0;
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

  public void setAllPrices(ArrayList<Double> newPrices){
    setStandardTicketPrice(newPrices.get(0));
    setVipTicketPrice(newPrices.get(1));
    setCandiesPrice(newPrices.get(2));
    setNachosPrice(newPrices.get(3));
    setOreoPrice(newPrices.get(4));
    setPopcornPrice(newPrices.get(5));
    setPeanutsPrice(newPrices.get(6));
    setColaPrice(newPrices.get(7));
    setPepsiPrice(newPrices.get(8));
    setFantaPrice(newPrices.get(9));
    setTuborgPrice(newPrices.get(10));
    setRedbullPrice(newPrices.get(11));
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
