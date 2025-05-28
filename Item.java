package com.gildedrose;

/**
 * Refactorización de la clase Item:
 * 
 * Malas prácticas originales:
 * 1. Atributos públicos sin encapsulación.
 * 2. Sin validación de límites para quality (podría quedar fuera de rango).
 * 3. Lógica de presentación y estado mezcladas.
 * 4. Sin métodos claros para alterar sellIn o quality.
 */
public class Item {
    private String name;
    private int sellIn;
    private int quality;

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    /**
     * Constructor de Item.
     * @param name Nombre descriptivo del producto.
     * @param sellIn Días restantes para la venta.
     * @param quality Calidad inicial (0-50).
     */
    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        setQuality(quality);
    }

    /**
     * @return Nombre del ítem.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Días restantes para vender.
     */
    public int getSellIn() {
        return sellIn;
    }

    /**
     * @return Calidad actual.
     */
    public int getQuality() {
        return quality;
    }

    /**
     * Disminuye sellIn en uno.
     */
    public void decrementSellIn() {
        sellIn--;
    }

    /**
     * Aumenta quality dentro del rango permitido.
     */
    public void increaseQuality() {
        if (quality < MAX_QUALITY) {
            quality++;
        }
    }

    /**
     * Disminuye quality dentro del rango permitido.
     */
    public void decreaseQuality() {
        if (quality > MIN_QUALITY) {
            quality--;
        }
    }

    /**
     * Establece quality asegurando que quede entre MIN_QUALITY y MAX_QUALITY.
     * @param quality Valor deseado de calidad.
     */
    public void setQuality(int quality) {
        this.quality = Math.max(MIN_QUALITY, Math.min(MAX_QUALITY, quality));
    }

    /**
     * Restaura quality a MIN_QUALITY.
     */
    public void resetQuality() {
        this.quality = MIN_QUALITY;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %d", name, sellIn, quality);
    }
}