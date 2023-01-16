package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.domain.values.Name;
import co.sofka.challenge_jr.domain.values.ProductID;

public class ProductRenamed extends DomainEvent {
  private final ProductID productID;
  private final Name name;

  public ProductRenamed(ProductID productID, Name name) {
    super("sofka.Inventory.ProductRenamed");
    this.productID = productID;
    this.name = name;
  }

  public ProductID getProductID() {
    return productID;
  }

  public Name getName() {
    return name;
  }
}
