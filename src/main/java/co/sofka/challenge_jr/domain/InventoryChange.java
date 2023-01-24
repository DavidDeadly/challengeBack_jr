package co.sofka.challenge_jr.domain;

import co.com.sofka.domain.generic.EventChange;
import co.sofka.challenge_jr.domain.events.*;
import co.sofka.challenge_jr.domain.values.*;

import java.util.*;
import java.util.stream.Collectors;

public class InventoryChange extends EventChange {
  public InventoryChange(Inventory inventory) {
    apply((InventoryCreated event) -> {
      inventory.inventoryName = new Name(event.getInventoryName());
      inventory.buys = new ArrayList<>();
      inventory.products = new ArrayList<>();
    });

    apply((ProductAdded event) -> {
      Product product = new Product(
              new ProductID(event.getProductID()),
              new Name(event.getName()),
              new InInventory(event.getInInventory()),
              new Enabled(event.getEnabled()),
              new Min(event.getMin()),
              new Max(event.getMax())
      );

      inventory.products.add(product);
    });

    apply((ProductDeleted event) -> inventory.removeProductById(new ProductID(event.getProductID())));

    apply((InventoryProductUpdated event) -> {
      Optional<Product> productById = inventory.getProductById(event.getProductID());
      productById.ifPresent(product -> product.updateInInventory(new InInventory(event.getInInventory())));
    });

    apply((ProductMaxUpdated event) -> {
      Optional<Product> productById = inventory.getProductById(event.getProductID());
      productById.ifPresent(product -> product.updateMax(new Max(event.getMax())));
    });

    apply((ProductMinUpdated event) -> {
      Optional<Product> productById = inventory.getProductById(event.getProductID());
      productById.ifPresent(product -> product.updateMin(new Min(event.getMin())));
    });

    apply((ProductRenamed event) -> {
      Optional<Product> productById = inventory.getProductById(event.getProductID());
      productById.ifPresent(product -> product.rename(event.getName()));
    });

    apply((ProductsBought event) -> {
      event.getProductsBuy().forEach(productsBuy -> {
        Optional<Product> productById = inventory.getProductById(productsBuy.getProductId());
        productById.ifPresent(product -> product.buy(productsBuy.getQuantity()));
      });

      List<ProductsBuy> productsBuys = event.getProductsBuy().stream()
              .map(productsBuyView -> new ProductsBuy(productsBuyView.getProductId(), productsBuyView.getQuantity()))
              .collect(Collectors.toList());


      Buy buy = new Buy(
              new BuyID(event.getBuyID()),
              productsBuys,
              new DateBuy(event.getDate()),
              new IDType(IDTypeEnum.valueOf(event.getIdType())),
              new IDClient(event.getIdClient()),
              new ClientName(event.getName()));

      inventory.buys.add(buy);
    });
  }
}
