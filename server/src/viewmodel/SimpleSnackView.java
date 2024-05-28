package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Snack;

public class SimpleSnackView
{
  private StringProperty price;
  private StringProperty type;
  private StringProperty size;

  public SimpleSnackView(Snack snack){

    price = new SimpleStringProperty(snack.getPrice() + "DKK");
    type = new SimpleStringProperty(snack.getType());
    size = new SimpleStringProperty(snack.getSize());
  }

  public void setType(String type)
  {
    this.type.set(type);
  }

  public void setSize(String size)
  {
    this.size.set(size);
  }

  public void setPrice(String price)
  {
    this.price.set(price);
  }
  public StringProperty typeProperty()
  {
    return type;
  }

  public StringProperty sizeProperty()
  {
    return size;
  }

  public StringProperty priceProperty()
  {
    return price;
  }

}
