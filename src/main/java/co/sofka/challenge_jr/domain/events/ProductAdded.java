package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

import java.util.UUID;

public class ProductAdded extends DomainEvent {
  private final String productID = UUID.randomUUID().toString();
  private final String name;
  private final Integer inInventory;
  private final Boolean enabled;
  private final Integer min;
  private final Integer max;
  public ProductAdded(String name, Integer inInventory, Boolean enabled, Integer min, Integer max) {
    super("sofka.Inventory.ProductAdded");
    this.name = name;
    this.inInventory = inInventory;
    this.enabled = enabled;
    this.min = min;
    this.max = max;
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
