package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;

public class UpdateProductInventory extends Command {
  private final String inventoryID;
  private final String productID;
  private final Integer inInventory;

  public UpdateProductInventory(String inventoryID, String productID, Integer inInventory) {
    this.inventoryID = inventoryID;
    this.productID = productID;
    this.inInventory = inInventory;
  }

  public String getString() {
    return inventoryID;
  }

  public String getProductID() {
    return productID;
  }

  public Integer getInInventory() {
    return inInventory;
  }
}
