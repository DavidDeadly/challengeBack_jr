package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.domain.values.InventoryID;
import co.sofka.challenge_jr.domain.values.Name;

public class CreateInventory extends Command {
  private final InventoryID inventoryId;
  private final Name name;

  public CreateInventory(InventoryID inventoryID, Name name) {
    this.inventoryId = inventoryID;
    this.name = name;
  }

  public Name getName() {
    return name;
  }

  public InventoryID getInventoryId() {
    return inventoryId;
  }
}
