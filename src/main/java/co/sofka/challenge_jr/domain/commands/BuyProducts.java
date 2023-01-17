package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.application.repositories.models.ProductsBuyView;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class BuyProducts extends Command {
  private final String inventoryID;
  private final String buyID = UUID.randomUUID().toString();
  private final Set<ProductsBuyView> productsBuy;
  private final Date date = Date.from(Instant.now());
  private final String clientName;
  private final String idType;
  private final String idClient;

  public BuyProducts(String inventoryID, Set<ProductsBuyView> productsBuy, String clientName, String idType, String idClient) {
    this.inventoryID = inventoryID;
    this.productsBuy = productsBuy;
    this.clientName = clientName;
    this.idType = idType;
    this.idClient = idClient;
  }

  public String getInventoryID() {
    return inventoryID;
  }

  public Set<ProductsBuyView> getProductsBuy() {
    return productsBuy;
  }

  public Date getDate() {
    return date;
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

  public String getBuyID() {
    return buyID;
  }
}
