package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    @Test
    void lowerSellInAndQualityAfterTwoDayNormalItem() {
        Item[] items = new Item[] { new Item("foo", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    // Important twice means it is just -2 degrading instead of -1
    void sellDatePassedQualityDegradesTwiceAsFast() {
        Item[] items = new Item[] { new Item("foo", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(40, app.items[0].quality);
    }

    @Test
    void agedBIncreasesQualitySellInMoreThanZero() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
       
    }

    @Test
    void agedBIncreasesTwiceQualitySellInLessEqualZero() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(10, app.items[0].quality);
    }

    @Test
    void qualityNeverOver50WhenUpdating() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void sulfurasNotDecreasing() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 15, 35) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(35, app.items[0].quality);
        assertEquals(15, app.items[0].sellIn);
    }

    // Backstage passes
    @Test
    void backstageIncreaseQuality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(45, app.items[0].quality);
        assertEquals(10, app.items[0].sellIn);
    }
    
    @Test
    void backstageIncreaseQualityBy2With5Update() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(40, app.items[0].quality);
        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void backstageIncreaseQualityBy2With3Update() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(38, app.items[0].quality);
        assertEquals(7, app.items[0].sellIn);
    }

    @Test
    void backstageIncreaseQualityBy3With5Days() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(45, app.items[0].quality);
   
    }

    @Test
    void backstageIncreaseQualityBy3With5DaysSellInIsZero() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
   
    }

}
