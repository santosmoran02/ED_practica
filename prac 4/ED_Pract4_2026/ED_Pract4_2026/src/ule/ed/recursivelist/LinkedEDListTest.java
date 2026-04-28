package ule.ed.recursivelist;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;


public class LinkedEDListTest {
	private LinkedEDList<String> lista;
	
	@Before
	public void test() {
		 lista= new LinkedEDList<String>();
	}

	@Test
	public void test_Vacia() {
		assertEquals(0,lista.size());
	}
	
	@Test
	public void test_AddLast() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void test_RemoveLastElem_Vacia() {
		lista.removeLastElem("A");
	}

	@Test(expected=NullPointerException.class)
	public void test_addLast_ElementoNulo() {
			lista.addLast(null);
	}
	
	@Test
	public void linkedtestAddPos_Varios() {
		lista.addPos("2",1);
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addPos("3",1);
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addPos("7",2);
		Assert.assertEquals("(3 7 2 )", lista.toString());
		lista.addPos("10",3);
		Assert.assertEquals("(3 7 10 2 )", lista.toString());
		
	}
	
	
	@Test(expected=NullPointerException.class)
	public void test_addPos_Null() {
		lista.addPos(null, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_addPos_0() {
		lista.addPos("2", 0);
	}
	
	@Test
	public void test_addPos_pos_mayor_que_size() {
		lista.addPos("3", 1000000000);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_getElemPos_pos_not_valid() {
		lista.getElemPos(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_getElemPos_pos_not_valid2() {
		lista.getElemPos(10000000);
	}
	
	@Test
	public void test_getElemPos_pos_valid() {
		lista.addLast("2");
		lista.addLast("5");
		lista.addLast("7");
		lista.getElemPos(1);
		lista.getElemPos(3);
	}
	
	@Test(expected=NullPointerException.class)
	public void test_getPosFirst_Null() {
		lista.getPosFirst(null);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void test_getPosFirst_not_valid_element() {
		lista.getPosFirst("7");
	}
	
	@Test
	public void test_getPosFirst() {
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("4");
		lista.getPosFirst("4");
	}
	
	
}
