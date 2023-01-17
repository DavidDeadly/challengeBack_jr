package co.sofka.challenge_jr.domain;

import co.com.sofka.domain.generic.EventChange;
import co.sofka.challenge_jr.domain.events.*;
import co.sofka.challenge_jr.domain.values.ProductID;

import java.util.HashSet;
import java.util.Optional;

public class InventoryChange extends EventChange {
  public InventoryChange(Inventory inventory) {
    apply((InventoryCreated event) -> {
      inventory.inventoryName = event.getInventoryName();
      inventory.buys = new HashSet<>();
      inventory.products = new HashSet<>();
    });

    apply((ProductAdded event) -> {
      Product product = new Product(
              event.getProductID(),
              event.getName(),
              event.getInInventory(),
              event.getEnabled(),
              event.getMin(),
              event.getMax()
      );

      inventory.products.add(product);
    });

    apply((ProductDeleted event) -> inventory.removeProductById(event.getProductID()));

    apply((InventoryProductUpdated event) -> {
      Optional<Product> productById = inventory.getProductById(event.getProductID());
      productById.ifPresent(product -> product.updateInInventory(event.getInInventory()));
    });

    apply((ProductMaxUpdated event) -> {
      Optional<Product> productById = inventory.getProductById(event.getProductID());
      productById.ifPresent(product -> product.updateMax(event.getMax()));
    });

    apply((ProductMinUpdated event) -> {
      Optional<Product> productById = inventory.getProductById(event.getProductID());
      productById.ifPresent(product -> product.updateMin(event.getMin()));
    });

    apply((ProductRenamed event) -> {
      Optional<Product> productById = inventory.getProductById(event.getProductID());
      productById.ifPresent(product -> product.rename(event.getName()));
    });

    apply((ProductsBought event) -> {
      event.getProductsBuy().forEach(productsBuy -> {
        Optional<Product> productById = inventory.getProductById(new ProductID(productsBuy.value().idProduct()));
        productById.ifPresent(product -> product.buy(productsBuy.value().quantity()));
      });

      Buy buy = new Buy(event.getBuyID(), event.getProductsBuy(), event.getDate(), event.getIdType(), event.getIdClient(), event.getName());

      inventory.buys.add(buy);
    });
  }
}
