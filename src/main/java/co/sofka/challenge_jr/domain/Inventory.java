package co.sofka.challenge_jr.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.application.repositories.models.BuyView;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import co.sofka.challenge_jr.application.repositories.models.ProductView;
import co.sofka.challenge_jr.application.repositories.models.ProductsBuyView;
import co.sofka.challenge_jr.domain.events.*;
import co.sofka.challenge_jr.domain.values.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Inventory extends AggregateEvent<InventoryID> {
  protected Name inventoryName;
  protected Set<Product> products;
  protected Set<Buy> buys;

  public Inventory(InventoryID entityId, Name inventoryName) {
    super(entityId);
    subscribe(new InventoryChange(this));
    appendChange(new InventoryCreated(inventoryName.value())).apply();
  }

  private Inventory(InventoryID inventoryID) {
    super((inventoryID));
    subscribe(new InventoryChange(this));
  }

  public static Inventory from (InventoryID inventoryID, List<DomainEvent> events) {
    Inventory inventory = new Inventory(inventoryID);
    events
      .forEach(inventory::applyEvent);
    return inventory;
  }

  public InventoryView toInventoryView() {
    InventoryView inventoryView = new InventoryView(
            entityId.value(),
            inventoryName.value()
    );

    Set<ProductView> productViews = products.stream().map(product ->
      new ProductView(
        product.identity().value(),
        product.Name().value(),
        product.InInventory().value(),
        product.Enabled().value(),
        product.Min().value(),
        product.Max().value()
      )
    ).collect(Collectors.toSet());

    inventoryView.setProducts(productViews);

    Set<BuyView> buysView = buys.stream().map(buy -> {
      Set<ProductsBuyView> productsBuyView = buy.Products().stream().map(productsBuy ->
        new ProductsBuyView(productsBuy.value().idProduct(), productsBuy.value().quantity())
      ).collect(Collectors.toSet());



      return new BuyView(
              buy.identity().value(),
              buy.Date().value(),
              buy.IdType().value().name(),
              buy.Id().value(),
              buy.ClientName().value(),
              productsBuyView
              );
    }).collect(Collectors.toSet());


    inventoryView.setBuys(buysView);
    return inventoryView;
  }

  public void addProduct(ProductID productID, Name name, InInventory inInventory, Enabled enabled, Min min, Max max) {
    appendChange(new ProductAdded(productID.value(), name.value(), inInventory.value(), enabled.value(), min.value(), max.value())).apply();
  }

  public void deleteProduct(ProductID productID) {
    appendChange(new ProductDeleted(productID.value())).apply();
  }

  public void renameProduct(ProductID productID, Name name) {
    appendChange(new ProductRenamed(productID.value(), name.value())).apply();
  }

  public void updateProductInventory(ProductID productID, InInventory inInventory) {
    appendChange(new InventoryProductUpdated(productID.value(), inInventory.value())).apply();
  }

  public void updateProductMax(ProductID productID, Max max) {
    appendChange(new ProductMaxUpdated(productID.value(), max.value())).apply();
  }

  public void updateProductMin(ProductID productID, Min min) {
    appendChange(new ProductMinUpdated(productID.value(), min.value())).apply();
  }

  public void buyProducts(BuyID buyID, Set<ProductsBuy> productsBuys, DateBuy date, ClientName clientName, IDType idType, IDClient idClient) {
    final var productsToBuy = productsBuys.stream().map(productsBuy ->
            new ProductsBuyView(productsBuy.value().idProduct(), productsBuy.value().quantity())
        ).collect(Collectors.toSet());
    appendChange(new ProductsBought(buyID.value(), productsToBuy, date.value(), clientName.value(), idType.value().name(), idClient.value())).apply();
  }

  public Optional<Product> getProductById(String productID) {
    return products
            .stream().filter(product -> product.identity().value().equals(productID))
            .findFirst();
  }

  protected void removeProductById(ProductID productID) {
    products.removeIf(product -> product.identity().equals(productID));
  }
}
