package co.sofka.challenge_jr.domain;

import co.com.sofka.domain.generic.Entity;
import co.sofka.challenge_jr.domain.values.*;

import java.util.Set;

public class Buy extends Entity<BuyID> {
  private DateBuy date;
  private IDType idType;
  private IDClient id;
  private ClientName clientName;
  private Set<ProductsBuy> products;

  public Buy(BuyID entityId, Set<ProductsBuy> products, DateBuy date, IDType idType, IDClient id, ClientName clientName) {
    super(entityId);
    this.date = date;
    this.products = products;
    this.id = id;
    this.idType = idType;
    this.clientName = clientName;
  }

  public DateBuy Date() {
    return date;
  }

  public IDType IdType() {
    return idType;
  }

  public IDClient Id() {
    return id;
  }

  public ClientName ClientName() {
    return clientName;
  }

  public Set<ProductsBuy> Products() {
    return products;
  }
}
