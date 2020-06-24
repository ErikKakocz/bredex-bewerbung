package de.bredex.bewerbung;
import org.approvaltests.Approvals;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GildedRoseTest {

	private static String getCurrentItemsAsOneBigString() {
		if (GildedRose.items == null)
			return "";

		String oneBigString = "";
		for (Item i: GildedRose.items) {
			oneBigString += i.getName()
					+ " " + i.getSellIn()
					+ " " + i.getQuality()
					+ "\n";
		}
		return oneBigString;
	}

	@Test
	public void testForRefactoring() {
		String itemsOverTime = "===> BEFORE MAIN\n";
		itemsOverTime += getCurrentItemsAsOneBigString();
		
		GildedRose.main(null);
		itemsOverTime += "===> AFTER MAIN\n";
		itemsOverTime += getCurrentItemsAsOneBigString();
		
		for (int i = 1; i <= 100; i++) {
			GildedRose.updateQuality();
			itemsOverTime += "===> AFTER UPDATEQUALITY (" + i + ")\n"
					+ getCurrentItemsAsOneBigString();
		}
		
		Approvals.verify(itemsOverTime);
	}
	
}
