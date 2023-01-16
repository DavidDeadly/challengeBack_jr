package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.domain.values.Min;
import co.sofka.challenge_jr.domain.values.ProductID;

public class ProductMinUpdated extends DomainEvent {
  private final ProductID productID;
  private final Min min;

  public ProductMinUpdated(ProductID productID, Min min) {
    super("sofka.Inventory.ProductMinUpdated");
    this.productID = productID;
    this.min = min;
  }

  public ProductID getProductID() {
    return productID;
  }

  public Min getMin() {
    return min;
  }
}
