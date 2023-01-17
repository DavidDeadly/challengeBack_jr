package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class ProductMinUpdated extends DomainEvent {
  private final String productID;
  private final Integer min;

  public ProductMinUpdated(String productID, Integer min) {
    super("sofka.Inventory.ProductMinUpdated");
    this.productID = productID;
    this.min = min;
  }

  public String getProductID() {
    return productID;
  }

  public Integer getMin() {
    return min;
  }
}
