package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.domain.values.InventoryID;
import co.sofka.challenge_jr.domain.values.Min;
import co.sofka.challenge_jr.domain.values.ProductID;

public class UpdateProductMin extends Command {
  private final InventoryID inventoryID;
  private final ProductID productID;
  private final Min min;

  public UpdateProductMin(InventoryID inventoryID, ProductID productID, Min min) {
    this.inventoryID = inventoryID;
    this.productID = productID;
    this.min = min;
  }

  public InventoryID getInventoryID() {
    return inventoryID;
  }

  public ProductID getProductID() {
    return productID;
  }

  public Min getMin() {
    return min;
  }
}
