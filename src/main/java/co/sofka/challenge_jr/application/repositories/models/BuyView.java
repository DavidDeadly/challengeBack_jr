package co.sofka.challenge_jr.application.repositories.models;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class BuyView {
  @Id
  private String buyId;
  private Date date;
  private String idType;
  private String clientId;
  private String clientName;
  private List<ProductsBuyView> products;

  public BuyView(String buyId, Date date, String idType, String clientId, String clientName, List<ProductsBuyView> products) {
    this.buyId = buyId;
    this.date = date;
    this.idType = idType;
    this.clientId = clientId;
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

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public List<ProductsBuyView> getProducts() {
    return products;
  }

  public void setProducts(List<ProductsBuyView> products) {
    this.products = products;
  }
}
