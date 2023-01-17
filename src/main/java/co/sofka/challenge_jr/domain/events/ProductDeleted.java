package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class ProductDeleted extends DomainEvent {
  private final String productID;

  public ProductDeleted(String productID) {
    super("sofka.Inventory.ProductDeleted");
    this.productID = productID;
  }

  public String getProductID() {
    return productID;
  }
}
