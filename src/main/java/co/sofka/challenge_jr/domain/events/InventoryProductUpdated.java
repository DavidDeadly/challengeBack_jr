package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.domain.values.InInventory;
import co.sofka.challenge_jr.domain.values.ProductID;

public class InventoryProductUpdated extends DomainEvent {
  private final ProductID productID;
  private final InInventory inInventory;

  public InventoryProductUpdated(ProductID productID, InInventory inInventory) {
    super("sofka.Inventory.InventoryProductUpdated");
    this.productID = productID;
    this.inInventory = inInventory;
  }

  public ProductID getProductID() {
    return productID;
  }

  public InInventory getInInventory() {
    return inInventory;
  }
}
