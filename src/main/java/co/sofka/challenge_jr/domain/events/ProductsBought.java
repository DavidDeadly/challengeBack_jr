package co.sofka.challenge_jr.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.domain.values.*;

import java.util.Set;

public class ProductsBought extends DomainEvent {
  private final BuyID buyID;
  private final Set<ProductsBuy> productsBuy;
  private final DateBuy date;
  private final ClientName name;
  private final IDType idType;
  private final IDClient idClient;

  public ProductsBought(BuyID buyID, Set<ProductsBuy> productsBuy, DateBuy date, ClientName name, IDType idType, IDClient idClient) {
    super("sofka.Inventory.ProductBought");
    this.buyID = buyID;
    this.productsBuy = productsBuy;
    this.date = date;
    this.name = name;
    this.idType = idType;
    this.idClient = idClient;
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
