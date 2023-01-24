package co.sofka.challenge_jr.application.repositories.models;

import co.sofka.challenge_jr.domain.Inventory;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InventoryView {
  @Id
  private String id;
  private String name;
  private List<ProductView> products = new ArrayList<>();
  private List<BuyView> buys = new ArrayList<>();

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

  public List<ProductView> getProducts() {
    return products;
  }

  public void setProducts(List<ProductView> products) {
    this.products = products;
  }

  public List<BuyView> getBuys() {
    return buys;
  }

  public void setBuys(List<BuyView> buys) {
    this.buys = buys;
  }
}
