package co.sofka.challenge_jr.application.repositories.models;

public class ProductsBuyView {
  private final String productId;
  private final Integer quantity;

  public ProductsBuyView(String productId, Integer quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public String getProductId() {
    return productId;
  }

  public Integer getQuantity() {
    return quantity;
  }
}
