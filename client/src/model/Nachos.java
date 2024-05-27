package model;

import java.io.Serializable;

public class Nachos extends Snack implements Serializable
{
  public Nachos(double price, String size)
  {
    super(price, "client_model.Nachos", size);
  }
}
