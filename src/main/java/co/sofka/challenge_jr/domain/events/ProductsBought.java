package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.application.repositories.models.ProductsBuyView;

import java.util.Date;
import java.util.Set;

public class ProductsBought extends DomainEvent {
  private final String buyID;
  private final Set<ProductsBuyView> productsBuy;
  private final Date date;
  private final String name;
  private final String idType;
  private final String idClient;

  public ProductsBought(String buyID, Set<ProductsBuyView> productsBuy, Date date, String name, String idType, String idClient) {
    super("sofka.Inventory.ProductBought");
    this.buyID = buyID;
    this.productsBuy = productsBuy;
    this.date = date;
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
