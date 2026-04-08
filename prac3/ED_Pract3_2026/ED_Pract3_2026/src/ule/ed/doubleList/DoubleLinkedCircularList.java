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
			
			if (!hasNext()) {
			    throw new NoSuchElementException();
			}
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
			
			if (!hasNext()) {
			    throw new NoSuchElementException("Error, no hay siguiente\n");
			}
			
			T elemento = actual.elem;
			actual = actual.prev;
			return elemento;
		}
	}
	
	@SuppressWarnings("hiding")
	private class iteratorInstance<T> implements Iterator<T> {
		// añadir atributos
		DoubleNode<T> actual;
		int contador = 0;
		
		
		public iteratorInstance(DoubleNode<T> nodo) {
			actual = nodo.next;
		}
			

		@Override
		public boolean hasNext() {
			return actual != cab;
		}

		@Override
		public T next() {
			
			if (!hasNext()) {
			    throw new NoSuchElementException("Error, no hay siguiente\n");
			}
			
			T elemento = actual.elem;
			contador++;
			
			if (contador == actual.count) {
				actual = actual.next;
				contador = 0;
			}
			
			return elemento;
		}
	}
	
	@SuppressWarnings("hiding")
	private class reverseIteratorInstance<T> implements Iterator<T> {
		// añadir atributos
		DoubleNode<T> actual;
		int contador = 0;
	
		public reverseIteratorInstance(DoubleNode<T> nodo) {
			actual = nodo.prev;
		}

		@Override
		public boolean hasNext() {
			return actual != cab;
		}

		@Override
		public T next() {
			if (!hasNext()) {
			    throw new NoSuchElementException("Error, no hay siguiente\n");
			}
			T elemento = actual.elem;
			contador++;
			
			if (contador == actual.count) {
				actual = actual.prev;
				contador = 0;
			}
			
			return elemento;
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
		if (num <= 0) {
			throw new IllegalArgumentException("Error, el número no puede ser menor que o igual que 0\n");
		}
		
		if (isEmpty()) {
			throw new EmptyCollectionException("Error, la lista está vacía\n");
		}
		
		DoubleNode<T> aux = cab.next;
		int eliminados = 0;
		
		if (num >= aux.count) {
			eliminados = aux.count;
			cab.next = aux.next;
			aux.next.prev = cab;
		} else {
			
			aux.count = aux.count - num;
			eliminados = num;
		}
		
		return eliminados;
	}

	@Override
	public T removePos(int pos, int num) throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("Error, la lista no puede estar vacía\n");
		}
		
		if (pos < 1 || pos > size()) {
			throw new IllegalArgumentException("Error, la posición tiene que estar entre 1 y size\n");
		}
		
		if (num < 0) {
			throw new IllegalArgumentException("Error, el número tiene que ser mayor o igual que 1\n");
		}
		
		
		
		int posicion = 1;
		DoubleNode<T> aux = cab.next;
		T elemDevolver;
		
		while (posicion < pos) {
			aux = aux.next;
			posicion++;
		}
		
		elemDevolver = aux.elem;
		
		if (num >= aux.count) {
			
			aux.prev.next = aux.next;
			aux.next.prev = aux.prev;
			aux.next = null;
			aux.prev = null;
			
		} else {
			aux.count = aux.count - num;
		}
		
		return elemDevolver;
	}

	@Override
	public int remove(T elem, int num) throws EmptyCollectionException {
		if (elem == null) {
			throw new NullPointerException("Error, el elemento no puede ser nulo\n");
		}
		
		if (num < 1) {
			throw new IllegalArgumentException("Error, el número no puede ser menor que 1\n");
		}
		
		if (isEmpty()) {
			throw new EmptyCollectionException("Error, la lista no puede estar vacía\n");
		}
		
		if (!contains(elem)) {
			throw new NoSuchElementException("Error, la lista no puede estar vacía\n");
		}
		
		DoubleNode<T> aux = cab.next;
		int eliminados = 0;
		
		while (!aux.elem.equals(elem)) {
			aux = aux.next;
		}
		
		if (num >= aux.count) {
			aux.prev.next = aux.next;
			aux.next.prev = aux.prev;
			aux.next = null;
			aux.prev = null;
			eliminados = aux.count;
		} else {
			eliminados = num;
			aux.count = aux.count - num;
		}
		
		return eliminados;
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
		StringBuilder sb = new StringBuilder();
		DoubleNode<T> aux = cab.prev;
		
		sb.append("[");
		
		while (aux != cab) {
			sb.append(aux.elem + "(" + aux.count + ") ");
			aux = aux.prev;
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		DoubleNode<T> aux = cab.next;
		
		sb.append("[");
		
		while (aux != cab) {
			sb.append(aux.elem + "(" + aux.count + ") ");
			aux = aux.next;
		}
		
		sb.append("]");
		
		return sb.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new DobleLinkedListIterator<T>(cab);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new ReverseIterator<T>(cab);
	}

	@Override
	public Iterator<T> iteratorInstance() {
		return new iteratorInstance<T>(cab);
	}

	@Override
	public Iterator<T> reverseIteratorInstance() {
		return new reverseIteratorInstance<T>(cab);
	}

	@Override
	public T removeAllLast() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("Error, la lista está vacía\n");
		}
		
		T eliminado = cab.prev.elem;
		
		DoubleNode<T> aux = cab.prev;
		
		cab.prev = aux.prev;
		aux.prev.next = cab;
		
		return eliminado;
	}

	@Override
	public int getPosElem(T elem) throws EmptyCollectionException {
		if (elem == null) {
			throw new NullPointerException("Error, el elemento no puede ser nulo\n");
		}
		
		if (isEmpty()) {
			throw new EmptyCollectionException("Error, la lista está vacía\n");
		}
		
		if (!contains(elem)) {
			throw new NoSuchElementException("Error, el elemento no está en la lista\n");
		}
		
		DoubleNode<T> aux = cab.next;
		int contador = 1;
		
		while (!aux.elem.equals(elem)) {
			aux = aux.next;
			contador++;
		}
		
		return contador;
	}

	@Override
	public ArrayList<T> intersection(IDoubleList<T> other) {
		
		if (other == null) {
		    throw new NullPointerException();
		}
		
		ArrayList<T> lista_devolver = new ArrayList<T>();
		DoubleNode<T> aux = cab.next;
		
		while (aux != cab) {
			if (other.contains(aux.elem)) {
				lista_devolver.add(aux.elem);
			}
			aux = aux.next;
		}
		
		return lista_devolver;
	}

	

	

	
}


