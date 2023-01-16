package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.domain.values.ProductID;

public class ProductDeleted extends DomainEvent {
  private final ProductID productID;

  public ProductDeleted(ProductID productID) {
    super("sofka.Inventory.ProductDeleted");
    this.productID = productID;
  }

  public ProductID getProductID() {
    return productID;
  }
}
