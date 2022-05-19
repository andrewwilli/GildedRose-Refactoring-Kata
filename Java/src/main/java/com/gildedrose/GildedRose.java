package com.gildedrose;

class GildedRose {
    final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    final String BRIE = "Aged Brie";
    final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    final String CONJURED = "Conjured";

    public enum SpecialItem {
        SULFURAS,
        BRIE,
        BACKSTAGE,
        CONJURED
    }

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;

    }

    public void updateQuality() {

        for (Item item : items) {
            switch (item.name) {
                case SULFURAS:
                    updateSufuras(item);
                    break;
                case BRIE:
                    updateBrie(item);
                    break;
                case BACKSTAGE:
                    updateBackstage(item);
                    break;
                case CONJURED:
                    updateConjure(item);
                    break;
                default:
                    updateNormal(item);
            }
        }
    }

    private void updateNormal(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
        item.sellIn = item.sellIn - 1;
        checkSellInLimitNegative(item);
        checkQualityLimit(item);
    }

    private void updateConjure(Item item) {
        item.quality = item.quality - 2;
        checkSellInLimitNegative(item);
        checkQualityLimit(item);
    }

    private void updateBackstage(Item item) {

        item.sellIn = item.sellIn - 1;
        if (item.quality < 50) {
            item.quality = item.quality + 1;
            if (item.sellIn <= 10) {
                item.quality = item.quality + 1;

            }
            if (item.sellIn <= 5) {
                item.quality = item.quality + 1;
            }
        }

        checkSellInLimitPositive(item);
        checkQualityLimit(item);
        if (item.sellIn <= 0) {
            item.quality = 0;
        }
        System.out.println(item.quality);
    }

    private void updateBrie(Item item) {
        item.quality = item.quality + 1;

        item.sellIn = item.sellIn - 1;

        checkSellInLimitPositive(item);
        checkQualityLimit(item);
    }

    private void updateSufuras(Item item) {
        checkQualityLimit(item);
    }

    private void checkQualityLimit(Item item) {
        if (item.quality > 51) {
            item.quality = 50;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
    }

    private void checkSellInLimitNegative(Item item) {
        if (item.sellIn < 1) {
            item.quality = item.quality - 1;
        }
    }

    private void checkSellInLimitPositive(Item item) {
        if (item.sellIn < 1) {
            item.quality = item.quality + 1;
        }
    }

}