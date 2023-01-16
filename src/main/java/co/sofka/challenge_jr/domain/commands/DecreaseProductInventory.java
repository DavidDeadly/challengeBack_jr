package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.domain.values.InInventory;
import co.sofka.challenge_jr.domain.values.InventoryID;
import co.sofka.challenge_jr.domain.values.ProductID;

public class DecreaseProductInventory extends Command {
  private final InventoryID inventoryID;
  private final ProductID productID;
  private final InInventory inInventory;

  public DecreaseProductInventory(InventoryID inventoryID, ProductID productID, InInventory inInventory) {
    this.inventoryID = inventoryID;
    this.productID = productID;
    this.inInventory = inInventory;
  }

  public InventoryID getInventoryID() {
    return inventoryID;
  }

  public ProductID getProductID() {
    return productID;
  }

  public InInventory getInInventory() {
    return inInventory;
  }
}
