package co.sofka.challenge_jr.domain;

import co.com.sofka.domain.generic.Entity;
import co.sofka.challenge_jr.domain.values.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Buy extends Entity<BuyID> {
  private DateBuy date;
  private IDType idType;
  private IDClient id;
  private ClientName clientName;
  private List<ProductsBuy> products;

  public Buy(BuyID entityId, List<ProductsBuy> products, DateBuy date, IDType idType, IDClient id,
      ClientName clientName) {
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

  public List<ProductsBuy> Products() {
    return products;
  }
}
