package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class InventoryProductUpdated extends DomainEvent {
  private final String productID;
  private final Integer inInventory;

  public InventoryProductUpdated(String productID, Integer inInventory) {
    super("sofka.Inventory.InventoryProductUpdated");
    this.productID = productID;
    this.inInventory = inInventory;
  }

  public String getProductID() {
    return productID;
  }

  public Integer getInInventory() {
    return inInventory;
  }
}
