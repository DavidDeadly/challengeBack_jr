package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.application.repositories.models.ProductsBuyView;

import java.util.ArrayList;
import java.util.List;

public class BuyProducts extends Command {
  private final String inventoryID;
  private final List<ProductsBuyView> productsBuy;
  private final String clientName;
  private final String idType;
  private final String idClient;

  public BuyProducts(String inventoryID, List<ProductsBuyView> productsBuy, String clientName, String idType, String idClient) {
    this.inventoryID = inventoryID;
    this.productsBuy = productsBuy;
    this.clientName = clientName;
    this.idType = idType;
    this.idClient = idClient;
  }

  public String getInventoryID() {
    return inventoryID;
  }

  public List<ProductsBuyView> getProductsBuy() {
    return productsBuy;
  }

  public String getClientName() {
    return clientName;
  }

  public String getIdType() {
    return idType;
  }

  public String getIdClient() {
    return idClient;
  }
}
