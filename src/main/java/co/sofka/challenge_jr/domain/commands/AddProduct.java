package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.domain.values.*;

import java.util.Random;
import java.util.UUID;

public class AddProduct extends Command {
  private final InventoryID inventoryID;
  private final ProductID productID = new ProductID(UUID.randomUUID().toString());
  private final Name name;
  private final InInventory inInventory;
  private final Enabled enabled;
  private final Min min;
  private final Max max;

  public AddProduct(InventoryID inventoryID, Name name, InInventory inInventory, Enabled enabled, Min min, Max max) {
    this.inventoryID = inventoryID;
    this.name = name;
    this.inInventory = inInventory;
    this.enabled = enabled;
    this.min = min;
    this.max = max;
  }

  public InventoryID getInventoryID() {
    return inventoryID;
  }

  public ProductID getProductID() {
    return productID;
  }

  public Name getName() {
    return name;
  }

  public InInventory getInInventory() {
    return inInventory;
  }

  public Enabled getEnabled() {
    return enabled;
  }

  public Min getMin() {
    return min;
  }

  public Max getMax() {
    return max;
  }
}
