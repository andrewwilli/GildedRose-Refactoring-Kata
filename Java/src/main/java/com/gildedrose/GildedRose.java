package com.gildedrose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (Item item : items){
            if(IsSpecialItem(item)){
                
            }
        } 
        

        for (int i = 0; i < items.length; i++) {
            // not aged brie / backstage passes / sulfuras ... + quality does not matter
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                // aged brie / backstage quality < 50 -> + 1 quality
                // backstage sellin < 11 -> + 1 quality
                // backstage sellin < 6 -> + 1 quiality
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }
            // sulfuras -> -1 sellin
            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }
            // sellin < 0 quality degrades twice as fast
            // not aged brie / backstage / sulfuras -> -1 quality
            // not aged brie but backstage -> degrade it twice
            // if aged brie -> +1 quality 
            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }

    private boolean IsSpecialItem(Item item) {
        final ArrayList<String> ITEM_TYPES_LIST = new ArrayList<>();
        final String SULFURAS = "Sulfuras, Hand of Ragnaros";
        final  String BRIE = "Aged Brie";
        final  String BACKSTAGE_PASSES_ITEM = "Backstage passes to a TAFKAL80ETC concert";
        final  String CONJURED_ITEM = "Conjured";
        ITEM_TYPES_LIST.add(SULFURAS);
        ITEM_TYPES_LIST.add(BRIE);
        ITEM_TYPES_LIST.add(BACKSTAGE_PASSES_ITEM);
        ITEM_TYPES_LIST.add(CONJURED_ITEM);

        return ITEM_TYPES_LIST.contains(item.name);
    }
}