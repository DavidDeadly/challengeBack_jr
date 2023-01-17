package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;

public class DeleteProduct extends Command {
  private final String inventoryID;
  private final String productID;

  public DeleteProduct(String inventoryID, String productID) {
    this.inventoryID = inventoryID;
    this.productID = productID;
  }

  public String getInventoryID() {
    return inventoryID;
  }

  public String getProductID() {
    return productID;
  }
}
