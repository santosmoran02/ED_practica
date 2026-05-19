package ule.edi.tree;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class BinarySearchTreeTests {

   
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |	∅
	* |  |  |	∅
	* |  |	 ∅
	* |  20
	* |  |  15
	* |  |  |	∅
	* |  |  | 	∅
	* |  |  30
	* |  |  |  	∅
	* |  |  |  	∅
    */	
	private BinarySearchTree<Integer> ejemplo = null;
	
	
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  	∅
	* |  |  |  	∅
	* |  | 	 ∅
	* |  20
	* |  |  15
	* |  |  |  12
	* |  |  |  |  	∅
	* |  |  |  |  	∅
	* |  | 	 ∅
  */
	private BinarySearchTree<Integer> other=null;
	
	@Before
	public void setupBSTs() {
		
			
		ejemplo = new BinarySearchTree<Integer>();
		ejemplo.insert(10, 20, 5, 2, 15, 30);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
		
		
		other =new BinarySearchTree<Integer>();
		other.insert(10, 20, 5, 2, 15, 12);
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		
	    	}
	
	@Test
	public void testRemoveCountMayor1() {
		ejemplo.insert(20);
		ejemplo.insert(20);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(3), {15, ∅, ∅}, {30, ∅, ∅}}}");
		ejemplo.remove(20);
	    Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20(2), {15, ∅, ∅}, {30, ∅, ∅}}}");
	}
	
	@Test
	public void testRemoveCountMayor1HastaVaciar() {
		ejemplo.insert(20);
		ejemplo.insert(20);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20(3), {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
		ejemplo.remove(20);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20(2), {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
		ejemplo.remove(20);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
		ejemplo.remove(20);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {30, {15, ∅, ∅}, ∅}}",ejemplo.toString());
	}
	
	@Test
	public void testRemoveHoja() {
		ejemplo.remove(30);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove1Hijo() {
		ejemplo.remove(5);
		Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove2Hijos() {
		ejemplo.remove(10);
		Assert.assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
			
				
		@Test(expected = IllegalArgumentException.class)
		public void testInsertException() {
			Integer i = null;
			other.insert(i);	
		}
		
	
		@Test(expected = IllegalArgumentException.class)
		public void testContainsNull() {
			other.contains(null);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testRemoveNullElement() {
			Integer i = null;
			other.remove(i);
		}
		
		@Test(expected = NoSuchElementException.class)
		public void testRemoveNoSuchElement() {
			other.remove(11);
		}
		
	@Test
	public void testisEmpty() {
		Assert.assertFalse(other.isEmpty());
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		Assert.assertEquals("∅", other.toString());
		
		Assert.assertTrue(other.isEmpty());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsLeaf() {
		other.isLeaf(null);	
	}
	
	@Test
	public void testIsLeaf_NotNull() {
		Assert.assertTrue(ejemplo.isLeaf(ejemplo.getSubtreeWithPath("00")));
		Assert.assertFalse(ejemplo.isLeaf(ejemplo.getSubtreeWithPath("0")));
		Assert.assertFalse(ejemplo.isLeaf(ejemplo.getSubtreeWithPath("")));
	}
	
	@Test
	public void testContains_vacio() {
		Assert.assertFalse(ejemplo.contains(31));
		
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		
		Assert.assertFalse(other.contains(12));
	}
	
	@Test
	public void testIteratorWidth() {
	    BinarySearchTree<Integer> vacio = new BinarySearchTree<Integer>();
	    Assert.assertFalse(vacio.iteratorWidth().hasNext());

	    var it = ejemplo.iteratorWidth();
	    Assert.assertEquals(Integer.valueOf(10), it.next());
	    Assert.assertEquals(Integer.valueOf(5), it.next());
	    Assert.assertEquals(Integer.valueOf(20), it.next());
	    Assert.assertEquals(Integer.valueOf(2), it.next());
	    Assert.assertEquals(Integer.valueOf(15), it.next());
	    Assert.assertEquals(Integer.valueOf(30), it.next());
	    Assert.assertFalse(it.hasNext());
	}
	
	
	@Test
	public void testIteratorWidthInstances() {
	    // árbol vacío
	    BinarySearchTree<Integer> vacio = new BinarySearchTree<Integer>();
	    Assert.assertFalse(vacio.iteratorWidthInstances().hasNext());

	    var it = ejemplo.iteratorWidthInstances();
	    Assert.assertEquals(Integer.valueOf(10), it.next());
	    Assert.assertEquals(Integer.valueOf(5), it.next());
	    Assert.assertEquals(Integer.valueOf(20), it.next());
	    Assert.assertEquals(Integer.valueOf(2), it.next());
	    Assert.assertEquals(Integer.valueOf(15), it.next());
	    Assert.assertEquals(Integer.valueOf(30), it.next());
	    Assert.assertFalse(it.hasNext());

	    ejemplo.insert(20);
	    ejemplo.insert(20);
	    var it2 = ejemplo.iteratorWidthInstances();
	    Assert.assertEquals(Integer.valueOf(10), it2.next());
	    Assert.assertEquals(Integer.valueOf(5), it2.next());
	    Assert.assertEquals(Integer.valueOf(20), it2.next()); //
	    Assert.assertEquals(Integer.valueOf(20), it2.next()); 
	    Assert.assertEquals(Integer.valueOf(20), it2.next()); 
	    Assert.assertEquals(Integer.valueOf(2), it2.next());
	    Assert.assertEquals(Integer.valueOf(15), it2.next());
	    Assert.assertEquals(Integer.valueOf(30), it2.next());
	    Assert.assertFalse(it2.hasNext());
	}
	
	@Test
	public void testSize() {
		Assert.assertEquals(6, other.size());
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		Assert.assertEquals(0, other.size());
	}
	
	@Test
	public void testInstancesCount() {
		
		other.insert(20);
		other.insert(20);
		
		Assert.assertEquals(8, other.instancesCount());
		
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		other.removeAllMin();
		
		Assert.assertEquals(0, other.instancesCount());
		
		
	}
	
	@Test
	public void testPostOrden() {
		Assert.assertEquals(Integer.valueOf(30), ejemplo.postordenN(4));
		Assert.assertEquals(Integer.valueOf(2), ejemplo.postordenN(1));
		Assert.assertEquals(Integer.valueOf(5), ejemplo.postordenN(2));
	}
	
	@Test
	public void testTagPosDescend() {
	    ejemplo.tagPosDescend();
	    Assert.assertEquals("{10 [(descend, 4)], {5 [(descend, 5)], {2 [(descend, 6)], ∅, ∅}, ∅}, {20 [(descend, 2)], {15 [(descend, 3)], ∅, ∅}, {30 [(descend, 1)], ∅, ∅}}}", ejemplo.toString());
	}
	
	@Test
	public void testTagHeightLeaf() {
	    ejemplo.tagHeightLeaf();
	    Assert.assertEquals(
	        "{10, {5, {2 [(height, 3)], ∅, ∅}, ∅}, {20, {15 [(height, 3)], ∅, ∅}, {30 [(height, 3)], ∅, ∅}}}", ejemplo.toString());
	}
	
	@Test
	public void testTagHeight() {
	    ejemplo.tagHeight();
	    Assert.assertEquals("{10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅}, {20 [(height, 2)], {15 [(height, 3)], ∅, ∅}, {30 [(height, 3)], ∅, ∅}}}", ejemplo.toString());
	}
	
	@Test
	public void testTagOnlySonPreorder() {
	    Assert.assertEquals(1, ejemplo.tagOnlySonPreorder());
	    Assert.assertEquals("{10, {5, {2 [(onlySon, 3)], ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}", ejemplo.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveAllNullException() {
	    ejemplo.removeAll(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveAllNoSuchElementException() {
	    ejemplo.removeAll(99);
	}
	
	@Test
	public void testRemoveAll() {
	    
	    ejemplo.removeAll(2);
	    Assert.assertEquals("{10, {5, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}", ejemplo.toString());

	    ejemplo.insert(20);
	    ejemplo.insert(20);
	    Assert.assertEquals("{10, {5, ∅, ∅}, {20(3), {15, ∅, ∅}, {30, ∅, ∅}}}", ejemplo.toString());
	    ejemplo.removeAll(20);
	    Assert.assertEquals("{10, {5, ∅, ∅}, {30, {15, ∅, ∅}, ∅}}", ejemplo.toString());
	}
	
	@Test
	public void testRemoveRootOnlyLeftChild() {
	    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	    tree.insert(10, 5);
	    tree.remove(10);
	    Assert.assertEquals("{5, ∅, ∅}", tree.toString());
	}

	@Test
	public void testRemoveRootOnlyRightChild() {
	    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	    tree.insert(10, 15);
	    tree.remove(10);
	    Assert.assertEquals("{15, ∅, ∅}", tree.toString());
	}

	@Test
	public void testRemoveLeftChildOnlyRightChild() {
	    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	    tree.insert(10, 5, 20, 7);
	    tree.remove(5);
	    Assert.assertEquals("{10, {7, ∅, ∅}, {20, ∅, ∅}}", tree.toString());
	}

	@Test
	public void testRemoveLeftChildBothChildren() {
	    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	    tree.insert(10, 5, 20, 3, 7);
	    tree.remove(5);
	    Assert.assertEquals("{10, {7, {3, ∅, ∅}, ∅}, {20, ∅, ∅}}", tree.toString());
	}

	@Test
	public void testRemoveRightChildOnlyLeftChild() {
	    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	    tree.insert(10, 5, 20, 15);
	    tree.remove(20);
	    Assert.assertEquals("{10, {5, ∅, ∅}, {15, ∅, ∅}}", tree.toString());
	}

	@Test
	public void testRemoveRightChildBothChildrenWithReplacement() {
	    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	    tree.insert(10, 5, 20, 15, 30, 25);
	    tree.remove(20);
	    Assert.assertEquals("{10, {5, ∅, ∅}, {25, {15, ∅, ∅}, {30, ∅, ∅}}}", tree.toString());
	}

	@Test
	public void testRemoveRightChildLeaf() {
	    BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	    tree.insert(10, 5, 20);
	    tree.remove(20);
	    Assert.assertEquals("{10, {5, ∅, ∅}, ∅}", tree.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetContentWithPathIllegalArgument() {
	    ejemplo.getContentWithPath("2"); 
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetContentWithPathNoSuchElement() {
	    ejemplo.getContentWithPath("000"); 
	}

	@Test
	public void testGetContentWithPath() {
	    Assert.assertEquals(Integer.valueOf(10), ejemplo.getContentWithPath(""));   // raíz
	    Assert.assertEquals(Integer.valueOf(5),  ejemplo.getContentWithPath("0"));  // izquierda
	    Assert.assertEquals(Integer.valueOf(20), ejemplo.getContentWithPath("1"));  // derecha
	    Assert.assertEquals(Integer.valueOf(2),  ejemplo.getContentWithPath("00")); // hoja izquierda
	    Assert.assertEquals(Integer.valueOf(15), ejemplo.getContentWithPath("10")); // hoja izquierda de 20
	    Assert.assertEquals(Integer.valueOf(30), ejemplo.getContentWithPath("11")); // hoja derecha de 20
	}
	
	@Test
	public void testShowLeaves() {
	    BinarySearchTree<Integer> vacio = new BinarySearchTree<Integer>();
	    Assert.assertTrue(vacio.showLeaves().isEmpty());

	    var hojas = ejemplo.showLeaves();
	    Assert.assertEquals(3, hojas.size());
	    Assert.assertEquals("posicion: 1 - contenido: 2 - camino: LL", hojas.get(0));
	    Assert.assertEquals("posicion: 4 - contenido: 15 - camino: RL", hojas.get(1));
	    Assert.assertEquals("posicion: 6 - contenido: 30 - camino: RR", hojas.get(2));
	}
		
}


