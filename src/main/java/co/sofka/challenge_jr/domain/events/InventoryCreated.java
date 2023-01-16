package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.domain.values.Name;

public class InventoryCreated extends DomainEvent {
  private final Name inventoryName;
  public InventoryCreated(Name inventoryName) {
    super("sofka.inventory.InventoryCreated");
    this.inventoryName = inventoryName;
  }

  public Name getInventoryName() {
    return inventoryName;
  }
}
