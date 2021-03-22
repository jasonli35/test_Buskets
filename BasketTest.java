
import java.util.Collection;
import java.util.Arrays;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BasketTest {

	public static Collection<Object[]> BAGNUMS =
			Arrays.asList(new Object[][] { {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12} });
	private int bagType;

	public BasketTest(int bagType) {
		super();
		this.bagType = bagType;
	}

	@Parameterized.Parameters(name = "Basket{index}")
	public static Collection<Object[]> bags() {
		return BAGNUMS;
	}
	
	private Basket makeBasket() {
		switch(this.bagType) {
			case 0: return new Basket0();
			case 1: return new Basket1();
			case 2: return new Basket2();
			case 3: return new Basket3();
			case 4: return new Basket4();
			case 5: return new Basket5();
			case 6: return new Basket6();
			case 7: return new Basket7();
			case 8: return new Basket8();
			case 9: return new Basket9();
			case 10: return new Basket10();
			case 11: return new Basket11();
			case 12: return new Basket12();
		}
		return null;
	}
	

	
	@Test
	public void addedHasCount1() {
		Basket basketToTest = makeBasket();

		Item i = new Item("Shampoo", 5);
		basketToTest.addToBasket(i);
		assertEquals(1, basketToTest.count());
	}
	
	//This method check if addToBasket and count method work properly when added 20000 duplicated items
	@Test
	public void testCountAndAdd() {
		Basket basketA = makeBasket();
		
		Item water = new Item("water", 2);
		Item apple = new Item("apple", 3);
		for(int i = 0; i < 1000; i++) {
			basketA.addToBasket(water);
			basketA.addToBasket(apple);
		}
		
		assertEquals(2000, basketA.count());
	}
	
	//This method test if add method can add null item to basket; if yes, the test fail; vice verse
	@Test
	public void testAddNull() {
		Basket basket = makeBasket();
		Item water = new Item("water", 2);
		basket.addToBasket(water);
		basket.addToBasket(null);
		assertEquals(1, basket.count());
	}

	//This method check if removeAllFromBasket can remove item that is not exit
	@Test
	public void testRemove() {
		Basket basket = makeBasket();
		Item water = new Item("water", 2);
		Item apple = new Item("apple", 3);
		Item phone = new Item("phone", 100);
		assertEquals(true, basket.removeFromBasket(phone));
	}
	
	
	//This method check if the basket remove all items in the basket
	@Test
	public void testIfRemoveAllItem() {
		Basket basketA = makeBasket();
		
		Item water = new Item("water", 2);
		Item apple = new Item("apple", 3);
		
	
		for(int i = 0; i < 4; i++) {
			basketA.addToBasket(water);
			basketA.addToBasket(apple);
		}
		basketA.removeAllFromBasket(water);
		
		
		assertEquals(4, basketA.count());
	}
	
	@Test
	public void testRemoveAll3() {
		Item water = new Item("water", 2);
		Item i = new Item("water", 2);
		
		Basket basket = makeBasket();
		basket.addToBasket(water);
		basket.addToBasket(i);
		
		
		assertEquals(true, basket.removeAllFromBasket(water));
	}
	@Test
	public void testRemove3() {
		Basket basket = makeBasket();
		basket.addToBasket(null);
		assertEquals(false, basket.removeFromBasket(null));
	}
	//This will test whether countItem will work properly
	@Test
	public void testCountItem() {
		Basket basket = makeBasket();
		Item apple = new Item("apple", 3);
		Item water = new Item("water", 2);
		for(int i = 0; i < 10; i++) {
			basket.addToBasket(water);
			basket.addToBasket(apple);
		}
		assertEquals(10, basket.countItem(water));
	}
	//This method test the countItem can count different item with same name and price
	@Test
	public void testCountItem1() {
		Basket basket = makeBasket();
		Item apple = new Item("apple", 3);
		Item apple1 = new Item("apple", 3);
		basket.addToBasket(apple);
		basket.addToBasket(apple1);
		assertEquals(2, basket.countItem(apple));
	}
	//This method check if the return value of removeAllFromBasket is corret
	@Test
	public void testRemoveAllreturnVal() {
		Basket basket = makeBasket();
		Item apple = new Item("apple", 3);
		basket.addToBasket(apple);
		assertEquals(true, basket.removeAllFromBasket(apple));
		
	}
	@Test
	public void testRemoveAllreturnVal1() {
		Basket basket = makeBasket();
		Item apple = new Item("apple", 3);
		Item pineApple = new Item("pineApple", 6);
		basket.addToBasket(apple);
		assertEquals(false, basket.removeAllFromBasket(pineApple));
		
	}
	//This method test if it can take very large price of money
	@Test
	public void testCost() {
		Basket basket = makeBasket();
		Item airplane = new Item("airplane", 200000000);
		Item appleshare = new Item("apple share", 300000000);
		Item saleForceshare = new Item("apple share", 1500000000);
		basket.addToBasket(airplane);
		basket.addToBasket(appleshare);
		basket.addToBasket(saleForceshare);
		assertEquals(2000000000, basket.totalCost());
		
		
		
	}
	@Test
	public void testRemoveAllFromBasket() {
		Basket basket = makeBasket();
		Item airplane = new Item("airplane", 200000000);
		Item appleshare = new Item("apple share", 300000000);
		Item saleForceshare = new Item("apple share", 300000000);
		basket.addToBasket(airplane);
		basket.addToBasket(appleshare);
		basket.addToBasket(saleForceshare);
		basket.addToBasket(null);
		basket.removeAllFromBasket(null);
		assertEquals(3, basket.count());
	}
	@Test
	public void testCallRemoveTwice() {
		Basket basket = makeBasket();
		Item airplane = new Item("airplane", 200000000);
		basket.removeFromBasket(airplane);
		
		basket.addToBasket(airplane);
		basket.addToBasket(airplane);
		basket.removeFromBasket(airplane);
		basket.addToBasket(airplane);
		basket.addToBasket(airplane);
		
		assertEquals(2, basket.count());
	}
	
	@Test
	public void testRemoveAndCount() {
		Basket basket = makeBasket();
		Item airplane = new Item("airplane", 200000000);
		basket.addToBasket(airplane);
		basket.removeFromBasket(airplane);
		basket.removeFromBasket(airplane);
		basket.removeFromBasket(airplane);
		assertEquals(0, basket.count());
	}
	@Test
	public void testRemoveAndCount2() {
		Basket basket = makeBasket();
		Item airplane = new Item("airplane", 200000000);
		basket.addToBasket(airplane);
		basket.removeFromBasket(airplane);
		basket.removeFromBasket(airplane);
		
		basket.addToBasket(airplane);
		assertEquals(1, basket.count());
	}
	@Test
	public void testRemoveAndCount3() {
		Basket basket = makeBasket();
		Item airplane = new Item("airplane", 200000000);
		Item water = new Item("water", 200000000);
		Item coke = new Item("coke", 200000000);
		
		basket.addToBasket(airplane);
		basket.addToBasket(water);
		basket.addToBasket(coke);
		basket.addToBasket(coke);
		basket.removeFromBasket(coke);
		
		

//		assertEquals(1, basket.countItem(coke));
//		assertEquals(1, basket.countItem(water));
		assertEquals(3, basket.count());
	}
}
