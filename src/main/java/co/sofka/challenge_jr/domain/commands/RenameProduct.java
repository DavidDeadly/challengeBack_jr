package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;

public class RenameProduct extends Command {
  private final String inventoryID;
  private final String productID;
  private final String name;

  public RenameProduct(String inventoryID, String productID, String name) {
    this.inventoryID = inventoryID;
    this.productID = productID;
    this.name = name;
  }

  public String getInventoryID() {
    return inventoryID;
  }

  public String getProductID() {
    return productID;
  }

  public String getName() {
    return name;
  }
}
