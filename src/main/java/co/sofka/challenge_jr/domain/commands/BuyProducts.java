package co.sofka.challenge_jr.domain.commands;

import co.com.sofka.domain.generic.Command;
import co.sofka.challenge_jr.domain.values.*;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class BuyProducts extends Command {
  private final InventoryID inventoryID;
  private final BuyID buyID = new BuyID(UUID.randomUUID().toString());
  private final Set<ProductsBuy> productsBuy;
  private final DateBuy date = new DateBuy(Date.from(Instant.now()));
  private final ClientName name;
  private final IDType idType;
  private final IDClient idClient;

  public BuyProducts(InventoryID inventoryID, Set<ProductsBuy> productsBuy, ClientName name, IDType idType, IDClient idClient) {
    this.inventoryID = inventoryID;
    this.productsBuy = productsBuy;
    this.name = name;
    this.idType = idType;
    this.idClient = idClient;
  }

  public InventoryID getInventoryID() {
    return inventoryID;
  }

  public Set<ProductsBuy> getProductsBuy() {
    return productsBuy;
  }

  public DateBuy getDate() {
    return date;
  }

  public ClientName getName() {
    return name;
  }

  public IDType getIdType() {
    return idType;
  }

  public IDClient getIdClient() {
    return idClient;
  }

  public BuyID getBuyID() {
    return buyID;
  }
}
