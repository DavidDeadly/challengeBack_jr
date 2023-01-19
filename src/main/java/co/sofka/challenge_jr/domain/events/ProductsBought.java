package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.application.repositories.models.ProductsBuyView;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class ProductsBought extends DomainEvent {
  private final String buyID = UUID.randomUUID().toString();
  private final Set<ProductsBuyView> productsBuy;
  private final Date date = Date.from(Instant.now());
  private final String name;
  private final String idType;
  private final String idClient;

  public ProductsBought(Set<ProductsBuyView> productsBuy, String name, String idType, String idClient) {
    super("sofka.Inventory.ProductBought");
    this.productsBuy = productsBuy;
    this.name = name;
    this.idType = idType;
    this.idClient = idClient;
  }

  public Set<ProductsBuyView> getProductsBuy() {
    return productsBuy;
  }

  public Date getDate() {
    return date;
  }

  public String getName() {
    return name;
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
