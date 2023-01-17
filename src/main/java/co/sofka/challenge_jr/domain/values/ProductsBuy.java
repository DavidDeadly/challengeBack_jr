package co.sofka.challenge_jr.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class ProductsBuy implements ValueObject<ProductsBuy.Values> {
  private final String productID;
  private final Integer quantity;

  public ProductsBuy(String productID, Integer quantity) {
    this.productID = productID;
    this.quantity = quantity;
  }


  @Override
  public Values value() {
    return new Values() {
      @Override
      public String idProduct() {
        return productID;
      }

      @Override
      public Integer quantity() {
        return quantity;
      }
    };
  }

  public interface Values {
    String idProduct();
    Integer quantity();
  }
}
