package co.sofka.challenge_jr.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class ProductsBuy implements ValueObject<ProductsBuy.Values> {
  private final ProductID productID;
  private final Integer quantity;

  public ProductsBuy(ProductID productID, Integer quantity) {
    this.productID = productID;
    this.quantity = quantity;
  }


  @Override
  public Values value() {
    return new Values() {
      @Override
      public ProductID idProduct() {
        return productID;
      }

      @Override
      public Integer quantity() {
        return quantity;
      }
    };
  }

  public interface Values {
    ProductID idProduct();
    Integer quantity();
  }
}
