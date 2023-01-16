package co.sofka.challenge_jr.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.domain.events.*;
import co.sofka.challenge_jr.domain.values.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Inventory extends AggregateEvent<InventoryID> {
  protected Name inventoryName;
  protected Set<Product> products;
  protected Set<Buy> buys;

  public Inventory(InventoryID entityId, Name inventoryName) {
    super(entityId);
    appendChange(new InventoryCreated(inventoryName));
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

  public void AddProduct(ProductID productID, Name name, InInventory inInventory, Enabled enabled, Min min, Max max) {
    appendChange(new ProductAdded(productID, name, inInventory, enabled, min, max));
  }

  public void DeleteProduct(ProductID productID) {
    appendChange(new ProductDeleted(productID));
  }

  public void RenameProduct(ProductID productID, Name name) {
    appendChange(new ProductRenamed(productID, name));
  }

  public void IncreaseProductInventory(ProductID productID, InInventory inInventory) {
    appendChange(new InventoryProductIncreased(productID, inInventory));
  }

  public void DecreaseProductInventory(ProductID productID, InInventory inInventory) {
    appendChange(new InventoryProductDecreased(productID, inInventory));
  }

  public void UpdateProductMax(ProductID productID, Max max) {
    appendChange(new ProductMaxUpdated(productID, max));
  }

  public void UpdateProductMin(ProductID productID, Min min) {
    appendChange(new ProductMinUpdated(productID, min));
  }

  public void BuyProduct(BuyID buyID, Set<ProductsBuy> productsBuys, DateBuy date, ClientName clientName, IDType idType, IDClient idClient) {
    appendChange(new ProductsBought(buyID, productsBuys, date, clientName, idType, idClient));
  }

  public Optional<Product> getProductById(ProductID productID) {
    return products
            .stream().filter(product -> product.identity().equals(productID))
            .findFirst();
  }

  public void removeProductById(ProductID productID) {
    products.removeIf(product -> product.identity().equals(productID));
  }
}
