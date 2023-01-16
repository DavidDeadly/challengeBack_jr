package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.domain.values.InventoryID;
import co.sofka.challenge_jr.domain.values.Max;
import co.sofka.challenge_jr.domain.values.ProductID;

public class UpdateProductMax extends Command {
  private final InventoryID inventoryID;
  private final ProductID productID;
  private final Max max;

  public UpdateProductMax(InventoryID inventoryID, ProductID productID, Max max) {
    this.inventoryID = inventoryID;
    this.productID = productID;
    this.max = max;
  }

  public InventoryID getInventoryID() {
    return inventoryID;
  }

  public ProductID getProductID() {
    return productID;
  }

  public Max getMax() {
    return max;
  }
}
