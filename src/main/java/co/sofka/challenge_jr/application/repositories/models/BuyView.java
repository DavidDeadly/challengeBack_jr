package co.sofka.challenge_jr.application.repositories.models;

import java.util.Date;
import java.util.Set;

public class BuyView {
  private String buyId;
  private Date date;
  private String idType;
  private String id;
  private String clientName;
  private Set<ProductsBuyView> products;

  public BuyView(String buyId, Date date, String idType, String id, String clientName, Set<ProductsBuyView> products) {
    this.buyId = buyId;
    this.date = date;
    this.idType = idType;
    this.id = id;
    this.clientName = clientName;
    this.products = products;
  }

  public BuyView() {
  }

  public String getBuyId() {
    return buyId;
  }

  public void setBuyId(String buyId) {
    this.buyId = buyId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getIdType() {
    return idType;
  }

  public void setIdType(String idType) {
    this.idType = idType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public Set<ProductsBuyView> getProducts() {
    return products;
  }

  public void setProducts(Set<ProductsBuyView> products) {
    this.products = products;
  }
}
