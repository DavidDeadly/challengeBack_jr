package co.sofka.challenge_jr.application.repositories.models;

import co.sofka.challenge_jr.domain.Inventory;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

public class InventoryView {
  @Id
  private String id;
  private String name;
  private Set<ProductView> products = new HashSet<>();
  private Set<BuyView> buys = new HashSet<>();

  public InventoryView(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public InventoryView() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<ProductView> getProducts() {
    return products;
  }

  public void setProducts(Set<ProductView> products) {
    this.products = products;
  }

  public Set<BuyView> getBuys() {
    return buys;
  }

  public void setBuys(Set<BuyView> buys) {
    this.buys = buys;
  }
}
