package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;

public class CreateInventory extends Command {
  private final String inventoryId;
  private final String name;

  public CreateInventory(String inventoryID, String name) {
    this.inventoryId = inventoryID;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getInventoryId() {
    return inventoryId;
  }
}
