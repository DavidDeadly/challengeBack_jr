package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.domain.values.InventoryID;
import co.sofka.challenge_jr.domain.values.ProductID;

public class DeleteProduct extends Command {
  private final InventoryID inventoryID;
  private final ProductID productID;

  public DeleteProduct(InventoryID inventoryID, ProductID productID) {
    this.inventoryID = inventoryID;
    this.productID = productID;
  }

  public InventoryID getInventoryID() {
    return inventoryID;
  }

  public ProductID getProductID() {
    return productID;
  }
}
