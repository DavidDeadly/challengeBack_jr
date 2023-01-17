package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
public class ProductRenamed extends DomainEvent {
  private final String productID;
  private final String name;

  public ProductRenamed(String productID, String name) {
    super("sofka.Inventory.ProductRenamed");
    this.productID = productID;
    this.name = name;
  }

  public String getProductID() {
    return productID;
  }

  public String getName() {
    return name;
  }
}
