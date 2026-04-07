package ule.ed.doubleList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedCircularList<T> implements IDoubleList<T> {

	/* Representa un anillo que almacena datos en una estructura doblemente enlazada circular.
	 * 
	 * Siempre tiene un nodo fijo de 'referencia' (nodo cabecera)
	 * que tiene a <code>null</code> su contenido. Un anillo vacío será un
	 * nodo de referencia (R) que se apunta a sí mismo como 'next' y
	 * como 'previous', para cerrar el anillo.
	 * 
	*/
	
	//	referencia al nodo cabecera de la lista
	private DoubleNode<T> cab;

	// Clase de los nodos de la lista doble

	@SuppressWarnings("hiding")
	protected class DoubleNode<T> {

		DoubleNode(T element, int count) {
			this.elem = element;
			this.count= count;
			this.next = null;
			this.prev = null;
		}

		T elem;
        int count; 
		DoubleNode<T> next;
		DoubleNode<T> prev;
	   
		public String toString(){
			//TODO 
			return this.elem.toString()+"("+this.count+")";
			
		}
	}


	///// ITERADOR //////////


	
	private class DobleLinkedListIterator<T> implements Iterator<T> {
		DoubleNode<T> actual;


		public DobleLinkedListIterator(DoubleNode<T> nodo) {
			// TODO
			actual=nodo.next;
		}

		@Override
		public boolean hasNext() {
			// TODO
			boolean check=false;
			
			if(actual!=cab) {
				check=true;
			}
			return check;
		}

		@Override
		public T next() {
			// TODO
			T aux = null;

			if(! hasNext()) {
				throw new NoSuchElementException();
			}
			aux = actual.elem;
			actual = actual.next;

			return aux;
		}
	}

	////// ITERATOR REVERSE

	@SuppressWarnings("hiding")
	private class ReverseIterator<T> implements Iterator<T> {
		// añadir atributos
		DoubleNode<T> actual;


		public ReverseIterator(DoubleNode<T> nodo) {
			actual = nodo.prev;
		}

		@Override
		public boolean hasNext() {
			return actual != cab;
		}

		@Override
		public T next() {
			T elemento = actual.elem;
			actual = actual.prev;
			return elemento;
		}
	}
	
	@SuppressWarnings("hiding")
	private class iteratorInstance<T> implements Iterator<T> {
		// añadir atributos
	
		public iteratorInstance(DoubleNode<T> nodo) {
	 //TODO	
			}
			

		@Override
		public boolean hasNext() {
			// TODO
		return false;
		}

		@Override
		public T next() {
			// TODO
			return null;
			
		}
	}
	
	@SuppressWarnings("hiding")
	private class reverseIteratorInstance<T> implements Iterator<T> {
		// añadir atributos
	
		public reverseIteratorInstance(DoubleNode<T> nodo) {
			//TODO
		}

		@Override
		public boolean hasNext() {
			// TODO
			return false;
					}

		@Override
		public T next() {
			// TODO
			return null;
		}
	}

	
	public DoubleLinkedCircularList() {
		// TODO crear el nodo cabecera, dejar next y prev apuntando a la misma cabecera
		cab = new DoubleNode<T>(null, 0);
		cab.next = cab;
		cab.prev = cab;
	
	}

	@SafeVarargs
	public DoubleLinkedCircularList(T...v) {
		// TODO Auto-generated constructor stub
		// constructor que añade elementos
		cab=new DoubleNode<T>(null,0);
		cab.next=cab;
		cab.prev=cab;
		for (T elem:v) {
			this.addLast(elem,1);
		}
	}

	@Override
	public boolean isEmpty() {
		return (cab.next == cab);
	}

	@Override
	public void clear() {
		cab.next = cab;
		cab.prev = cab;
	}

	@Override
	public void addFirst(T elem, int n) {
		if (elem == null) {
			throw new NullPointerException("Error, el elemento no puede ser nulo\n");
		}
		
		if (n <= 0) {
			throw new IllegalArgumentException("Error, el número no puede ser 0 o negativo\n");
		}
		
		DoubleNode<T> aux = cab.next;
		DoubleNode<T> nodoNuevo = new DoubleNode<>(elem, n);
		
		if (contains(elem)) {
			while (!aux.elem.equals(elem)) {
				aux = aux.next;
			}
			aux.count = aux.count + n;
		} else {
			nodoNuevo.prev = cab;
			nodoNuevo.next = cab.next;
			cab.next.prev = nodoNuevo;
			cab.next = nodoNuevo;	
		}
	}

	@Override
	public void addLast(T elem, int n) {
		if (elem == null) {
			throw new NullPointerException("Error, el elemento no puede ser nulo\n");
		}
		
		if (n <= 0) {
			throw new IllegalArgumentException("Error, el número no puede ser 0 o menor que 0\n");
		}
		
		DoubleNode<T> aux = cab.next;
		DoubleNode<T> nodoNuevo = new DoubleNode<>(elem, n);
		
		if (contains(elem)) {
			while (!aux.elem.equals(elem)) {
				aux = aux.next;
			}
			aux.count = aux.count + n;
		} else {
			nodoNuevo.prev = cab.prev;
			cab.prev.next = nodoNuevo;
			nodoNuevo.next = cab;
			cab.prev = nodoNuevo;
		}
	}

	@Override
	public int removeFirst(int num) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T removePos(int pos, int num) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remove(T elem, int num) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(T elem) {
		if (elem == null) {
			throw new NullPointerException("Error, el elemento no puede ser nulo\n");
		}
		
		DoubleNode<T> aux = cab.next;
		while (aux != cab) {
			if (aux.elem.equals(elem)) {
				return true;
			}
			aux = aux.next;
		}
		return false;
	}

	@Override
	public int size() {
		int total = 0;
		
		DoubleNode<T> aux = cab.next;
		
		while (aux != cab) {
			aux = aux.next;
			total++;
		}
		return total;
	}

	@Override
	public int countInstances() {
		int total = 0;
		
		DoubleNode<T> aux = cab.next;
		
		while (aux != cab) {
			total += aux.count;
			aux = aux.next;
		}
		return total;
	}

	@Override
	public String toStringReverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return new DobleLinkedListIterator<T>(cab);
	}

	@Override
	public Iterator<T> reverseIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iteratorInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> reverseIteratorInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeAllLast() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPosElem(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<T> intersection(IDoubleList<T> other) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	
}


