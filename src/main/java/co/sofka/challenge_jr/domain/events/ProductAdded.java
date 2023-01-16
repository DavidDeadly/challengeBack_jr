package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.domain.values.*;

public class ProductAdded extends DomainEvent {
  private final ProductID productID;
  private final Name name;
  private final InInventory inInventory;
  private final Enabled enabled;
  private final Min min;
  private final Max max;
  public ProductAdded(ProductID productID, Name name, InInventory inInventory, Enabled enabled, Min min, Max max) {
    super("sofka.Inventory.ProductAdded");
    this.productID = productID;
    this.name = name;
    this.inInventory = inInventory;
    this.enabled = enabled;
    this.min = min;
    this.max = max;
  }

  public ProductID getProductID() {
    return productID;
  }

  public Name getName() {
    return name;
  }

  public InInventory getInInventory() {
    return inInventory;
  }

  public Enabled getEnabled() {
    return enabled;
  }

  public Min getMin() {
    return min;
  }

  public Max getMax() {
    return max;
  }
}
