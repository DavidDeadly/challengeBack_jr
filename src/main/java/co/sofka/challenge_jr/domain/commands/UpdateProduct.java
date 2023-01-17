package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;

public class UpdateProduct extends Command {
  private final String inventoryID;
  private final String productID;
  private final Integer inInventory;
  private final String name;
  private final Integer min;
  private final Integer max;

  public UpdateProduct(String inventoryID, String productID, Integer inInventory, String name, Integer min, Integer max) {
    this.inventoryID = inventoryID;
    this.productID = productID;
    this.inInventory = inInventory;
    this.name = name;
    this.min = min;
    this.max = max;
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

  public Integer getInInventory() {
    return inInventory;
  }

  public Integer getMin() {
    return min;
  }

  public Integer getMax() {
    return max;
  }
}
