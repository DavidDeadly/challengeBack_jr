package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;

public class UpdateProductMax extends Command {
  private final String inventoryID;
  private final String productID;
  private final Integer max;

  public UpdateProductMax(String inventoryID, String productID, Integer max) {
    this.inventoryID = inventoryID;
    this.productID = productID;
    this.max = max;
  }

  public String getInventoryID() {
    return inventoryID;
  }

  public String getProductID() {
    return productID;
  }

  public Integer getMax() {
    return max;
  }
}
