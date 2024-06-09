package model;

import java.io.Serializable;
/**
 * represents a snack available for purchase in the cinema, with properties such as price, type, and size.
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class Snack implements Serializable
{
  private double price;
  private String type;
  private String size;
  /**
   *
   * 3 argument constructor
   * Constructor to initialize a Snack object.
   * @param price The price of the snack.
   * @param type The type of the snack.
   * @param size The size of the snack.
   */
  public Snack(double price, String type, String size){
    this.price = price;
    this.size = size;
    this.type = type;
  }

  //****************************************Getters and setters***************************************
  /**
   * Retrieves the price of the snack.
   * @return The price of the snack.
   */
  public double getPrice()
  {
    return price;
  }

  /**
   * Retrieves the type of the snack.
   * @return The type of the snack.
   */
  public String getType()
  {
    return type;
  }
  /**
   * Retrieves the size of the snack.
   * @return The size of the snack.
   */
  public String getSize()
  {
    return size;
  }
  /**
   * Sets the price of the snack.
   * @param price The price to set for the snack.
   */
  public void setPrice(double price)
  {
    this.price = price;
  }
  /**
   * Sets the size of the snack.
   * @param size The size to set for the snack.
   */
  public void setSize(String size)
  {
    this.size = size;
  }
  /**
   * Sets the type of the snack.
   * @param type The type to set for the snack.
   */
  public void setType(String type)
  {
    this.type = type;
  }
}
