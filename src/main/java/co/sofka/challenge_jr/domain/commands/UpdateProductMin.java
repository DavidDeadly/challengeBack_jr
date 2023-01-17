package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;

public class UpdateProductMin extends Command {
  private final String inventoryID;
  private final String productID;
  private final Integer min;

  public UpdateProductMin(String inventoryID, String productID, Integer min) {
    this.inventoryID = inventoryID;
    this.productID = productID;
    this.min = min;
  }

  public String getInventoryID() {
    return inventoryID;
  }

  public String getProductID() {
    return productID;
  }

  public Integer getMin() {
    return min;
  }
}
