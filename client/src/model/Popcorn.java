package model;

import java.io.Serializable;

public class Popcorn extends Snack implements Serializable
{
  public Popcorn(double price, String size)
  {
    super(price, "client_model.Popcorn", size);
  }
}
