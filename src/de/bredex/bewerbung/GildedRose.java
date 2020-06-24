package de.bredex.bewerbung;
import java.util.ArrayList;
import java.util.List;



public class GildedRose {

    final static int QUALITYCHANGECONSTANT=1;
    final static String[] LEGENDARYITEMS= {"Sulfuras, Hand of Ragnaros"};
    
	public static List<Item> items = null;

	public static void main(String[] args) {
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 5, 20));

        updateQuality();
}

	
	static boolean isBackstagePass(Item item) {
	    String name=item.getName();
	    if(name != null &&
	       name.toLowerCase().startsWith("backstage passes"))
	        return true;
	    return false;
	}
	
	static boolean isLegendary(Item item) {
	    String name=item.getName();
	    for(String itemName:LEGENDARYITEMS) {
	        if (itemName.equalsIgnoreCase(name))
	            return true;
	    }
	    return false;
	}
	
	static boolean isConjured(Item item) {
	    String name=item.getName();
	    return ((name != null) && (name.toLowerCase().startsWith("conjured")));
	}
	
    public static void updateQuality()
    {
        for (Item item:items)
        {
            if ((!"Aged Brie".equals(item.getName())) && 
                 !isBackstagePass(item) && 
                 !isLegendary(item)) 
            {
                if (item.getQuality() > 0)
                {
                    
                    if(isConjured(item)) 
                    {
                        
                        int qualityChangeValue=Math.min(QUALITYCHANGECONSTANT*2, item.getQuality());
                        item.setQuality(item.getQuality() - qualityChangeValue);
                    }
                    else
                        item.setQuality(item.getQuality() - QUALITYCHANGECONSTANT);
                }
            }
            else
            {
                if (item.getQuality() < 50)
                {
                    item.setQuality(item.getQuality() + QUALITYCHANGECONSTANT);

                    if (isBackstagePass(item)) 
                    {
                         if(item.getSellIn()>0) 
                         {
                             if (item.getSellIn() < 11) 
                             {
                                 item.setQuality(item.getQuality() + (2 * QUALITYCHANGECONSTANT));
                                 if (item.getSellIn() < 6)
                                     item.setQuality(item.getQuality() + QUALITYCHANGECONSTANT);
                                 }
                         }
                     
                    }

                }
            }

            if (!isLegendary(item))
            {
                item.setSellIn(item.getSellIn() - 1);
            }

            if (item.getSellIn() < 0)
            {
                if (!"Aged Brie".equals(item.getName()))
                {
                    if (!isBackstagePass(item))
                    	
                    {
                        if (item.getQuality() > 0)
                        {
                            if (!isLegendary(item))
                            {
                                if(isConjured(item))
                                    item.setQuality(item.getQuality() - (2*QUALITYCHANGECONSTANT));
                                item.setQuality(item.getQuality() - QUALITYCHANGECONSTANT);
                            }
                        }
                    }
                    else
                    {
                        item.setQuality(item.getQuality() - item.getQuality());
                    }
                }
                else
                {
                    if (item.getQuality() < 50)
                    	
                    {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
            }
        }
    }

}