package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import java.util.UUID;

public class AddProduct extends Command {
  private final String inventoryID;
  private final String name;
  private final Integer inInventory;
  private final Boolean enabled;
  private final Integer min;
  private final Integer max;

  public AddProduct(String inventoryID, String name, Integer inInventory, Boolean enabled, Integer min, Integer max) {
    this.inventoryID = inventoryID;
    this.name = name;
    this.inInventory = inInventory;
    this.enabled = enabled;
    this.min = min;
    this.max = max;
  }

  public String getInventoryID() {
    return inventoryID;
  }

  public String getName() {
    return name;
  }

  public Integer getInInventory() {
    return inInventory;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public Integer getMin() {
    return min;
  }

  public Integer getMax() {
    return max;
  }
}
