package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class InventoryCreated extends DomainEvent {
  private final String inventoryName;
  public InventoryCreated(String inventoryName) {
    super("sofka.inventory.InventoryCreated");
    this.inventoryName = inventoryName;
  }

  public String getInventoryName() {
    return inventoryName;
  }
}
