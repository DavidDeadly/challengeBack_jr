package co.sofka.challenge_jr.domain;

import co.com.sofka.domain.generic.Entity;
import co.sofka.challenge_jr.domain.values.*;

public class Product extends Entity<ProductID> {
  private Name name;
  private InInventory inInventory;
  private Enabled enabled;
  private Min min;
  private Max max;

  public Product(ProductID entityId, Name name, InInventory inInventory, Enabled enabled, Min min, Max max) {
    super(entityId);
    this.name = name;
    this.inInventory = inInventory;
    this.enabled = enabled;
    this.min = min;
    this.max = max;
  }

  public Name Name() {
    return name;
  }

  public InInventory InInventory() {
    return inInventory;
  }

  public Enabled Enabled() {
    return enabled;
  }

  public Min Min() {
    return min;
  }

  public Max Max() {
    return max;
  }

  public void updateMax(Max max) {
    this.max = max;
  }

  public void updateMin(Min min) {
    this.min = min;
  }

  public void rename(String name) {
    this.name = new Name(name);
  }

  public void buy(Integer quantity) {
    final Integer minBuy = this.min.value();
    final Integer maxBuy = this.max.value();
    final int newInInventory = this.inInventory.value() - quantity;

    if(quantity < minBuy) {
      throw new IllegalArgumentException("The minimum quantity to buy this product is " + minBuy);
    }

    if(quantity > maxBuy) {
      throw new IllegalArgumentException("The maximum quantity to buy this product is " + maxBuy);
    }

    if(newInInventory < 0) {
      throw new IllegalArgumentException("There's no enough stock of this product");
    }

    this.inInventory = new InInventory(newInInventory);
  }

  public void updateInInventory(InInventory inInventory) {
    this.inInventory = inInventory;
  }
}
