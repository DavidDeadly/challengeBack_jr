package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.domain.values.Max;
import co.sofka.challenge_jr.domain.values.ProductID;

public class ProductMaxUpdated extends DomainEvent {
  private final ProductID productID;
  private final Max max;

  public ProductMaxUpdated(ProductID productID, Max max) {
    super("sofka.Inventory.ProductMaxUpdated");
    this.productID = productID;
    this.max = max;
  }

  public ProductID getProductID() {
    return productID;
  }

  public Max getMax() {
    return max;
  }
}
