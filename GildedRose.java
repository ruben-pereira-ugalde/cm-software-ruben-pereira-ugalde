package com.gildedrose;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Malas prácticas detectadas:
 * 1. Lógica de negocio duplicada (violación de DRY): decremento/incremento de quality se repite.
 * 2. Alta complejidad ciclomática y anidamiento excesivo de ifs (violación de KISS).
 * 3. Clase GildedRose con múltiples responsabilidades: actualiza diferentes tipos de ítems.
 * 4. Dependencia rígida de nombres de items en el código (violación de Open/Closed).
 * 5. Bajo acoplamiento: falta de modularidad y reutilización de componentes.
 * 6. Calidad de código difícil de mantener y extender.
 */

public class GildedRose {
    private List<Item> items;

    // Singleton: asegura una única instancia de GildedRose
    private static GildedRose instance;

    private GildedRose(List<Item> items) {
        this.items = items;
    }

    /**
     * Obtiene la instancia única de GildedRose.
     * @param items Lista de ítems a gestionar (solo usada la primera vez)
     */
    public static synchronized GildedRose getInstance(List<Item> items) {
        if (instance == null) {
            instance = new GildedRose(items);
        }
        return instance;
    }

    /**
     * Actualiza sellIn y quality de todos los ítems usando la estrategia adecuada.
     */
    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = ItemUpdaterFactory.getUpdater(item.getName());
            updater.update(item);
        }
    }
}

/**
 * Factory para obtener la estrategia de actualización según el nombre del ítem.
 * Facilita cumplir con el principio Open/Closed.
 */
class ItemUpdaterFactory {
    private static final Map<String, ItemUpdater> registry = new HashMap<>();

    static {
        registry.put("Aged Brie", new AgedBrieUpdater());
        registry.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassesUpdater());
        registry.put("Sulfuras, Hand of Ragnaros", new SulfurasUpdater());
        // Se puede registrar más tipos aquí sin modificar la lógica de GildedRose
    }

    public static ItemUpdater getUpdater(String itemName) {
        return registry.getOrDefault(itemName, new DefaultItemUpdater());
    }
}

/**
 * Define la operación para actualizar un ítem.
 */
interface ItemUpdater {
    void update(Item item);
}

/**
 * Actualizador por defecto para ítems comunes.
 */
class DefaultItemUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        decrementQuality(item);
        item.decrementSellIn();
        if (item.getSellIn() < 0) {
            decrementQuality(item);
        }
    }

    protected void decrementQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }
}

/**
 * Actualizador para Aged Brie.
 */
class AgedBrieUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        incrementQuality(item);
        item.decrementSellIn();
        if (item.getSellIn() < 0) {
            incrementQuality(item);
        }
    }

    protected void incrementQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }
}

/**
 * Actualizador para Backstage passes.
 */
class BackstagePassesUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        incrementQuality(item);
        if (item.getSellIn() <= 10) incrementQuality(item);
        if (item.getSellIn() <= 5) incrementQuality(item);
        item.decrementSellIn();
        if (item.getSellIn() < 0) {
            item.setQuality(0);
        }
    }

    protected void incrementQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }
}

/**
 * Actualizador para Sulfuras. No cambia sellIn ni quality.
 */
class SulfurasUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // Sulfuras es legendario; no se modifica.
    }
}