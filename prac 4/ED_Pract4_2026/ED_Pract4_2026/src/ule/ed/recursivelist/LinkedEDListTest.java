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
	
	@Test(expected=NullPointerException.class)
	public void test_getPosLast_null() {
		lista.getPosLast(null);
	}
	
	
	@Test
	public void test_getPosLast() {
		lista.addLast("1");	
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("3");
		lista.getPosLast("3");
	}
	
	@Test(expected = NoSuchElementException.class)
	public void test_getPosLast_noExiste() {
	    lista.addLast("1");
	    lista.getPosLast("7");
	}
	
	@Test(expected=EmptyCollectionException.class)
	public void test_removeLast_vacio() throws EmptyCollectionException{
		lista.removelast();
	}
	
	@Test
	public void test_removeLast() throws EmptyCollectionException{
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		lista.removelast();
	}
	
	@Test(expected=NullPointerException.class)
	public void test_removeLastElem_vacio() {
		lista.removeLastElem(null);
	}
	
	@Test
	public void test_removeLastElem() {
		lista.addLast("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("3");
		lista.removeLastElem("1");
		lista.removeLastElem("3");
	}
	
	@Test(expected=NoSuchElementException.class)
	public void test_removeLastElem_no_elemento() {
		lista.addLast("1");
		lista.removeLastElem("2");
	}
	
	@Test
	public void test_reverse() {
		lista.addLast("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.reverse();
	}
	
	@Test
	public void test_removeOddElements() {
		lista.removeOddElements();
		lista.addLast("1");
		lista.removeOddElements();
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.removeOddElements();
	}
	
	@Test
	public void test_removeDuplicates() {
		lista.removeConsecDuplicates();
		lista.addLast("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("4");
		lista.removeConsecDuplicates();
	}
	
	@Test(expected=EmptyCollectionException.class)
	public void test_removePenult_vacio() throws EmptyCollectionException{
		lista.removePenult();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void test_removePenult_unElemento() throws EmptyCollectionException{
		lista.addLast("1");
		lista.removePenult();
	}
	
	@Test
	public void test_removePenult_dosElementos() throws EmptyCollectionException{
		lista.addLast("1");
		lista.addLast("2");
		lista.removePenult();
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		lista.removePenult();
	}
	
	@Test
	public void test_toString_vacio() {
		lista.toString();
	}
	
	@Test
	public void test_lengthEqualsTo() {
		lista.addLast("1");
		lista.addLast("2");
		lista.lengthEqualsTo(2);
		lista.lengthEqualsTo(1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_toStringExceptFromUntilReverse_invalid() {
		lista.toSringExceptFromUntilReverse(0, 0);
	}
	
	@Test
	public void test_toStringExceptFromUntilReverse() {
		lista.addLast("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		lista.addLast("6");
		assertEquals("(6 5 1 )", lista.toSringExceptFromUntilReverse(4, 2));
	}
	
	
}
