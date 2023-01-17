package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class ProductMaxUpdated extends DomainEvent {
  private final String productID;
  private final Integer max;

  public ProductMaxUpdated(String productID, Integer max) {
    super("sofka.Inventory.ProductMaxUpdated");
    this.productID = productID;
    this.max = max;
  }

  public String getProductID() {
    return productID;
  }

  public Integer getMax() {
    return max;
  }
}
