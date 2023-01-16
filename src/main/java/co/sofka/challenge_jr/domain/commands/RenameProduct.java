package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.domain.values.InventoryID;
import co.sofka.challenge_jr.domain.values.Name;
import co.sofka.challenge_jr.domain.values.ProductID;

public class RenameProduct extends Command {
  private final InventoryID inventoryID;
  private final ProductID productID;
  private final Name name;

  public RenameProduct(InventoryID inventoryID, ProductID productID, Name name) {
    this.inventoryID = inventoryID;
    this.productID = productID;
    this.name = name;
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
}
