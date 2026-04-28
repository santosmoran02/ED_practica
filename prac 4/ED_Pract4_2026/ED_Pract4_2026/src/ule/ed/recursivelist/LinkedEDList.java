package ule.ed.recursivelist;

import java.util.NoSuchElementException;


public class LinkedEDList<T> implements EDList<T> {

	//	referencia al primer  de la lista
	private Node<T> front;

	

	private class Node<T> {

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		T elem;

		Node<T> next;
	}

	@Override
	public boolean isEmpty() {
		return front == null;
	}


	@Override
	public int size() {
		return sizeRec(front);
	}
	
	private int sizeRec(Node<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + sizeRec(node.next);
		}
	}


	@Override
	public void addLast(T elem) {
		
		if (elem == null) {
			throw new NullPointerException("Error, el elemento no puede ser nulo\n");
		}
		
		Node<T> nuevo = new Node<>(elem);
		
		if (front == null) {
			front = nuevo;
		} else {
			addLastRec(front, elem);
		}
		
	}
	
	private void addLastRec(Node<T> node, T elem) {
		
		Node<T> nuevo = new Node<>(elem);
		
		if (node.next == null) {
			node.next = nuevo;
		} else {
			addLastRec(node.next, elem);
		}
	}

	
	@Override
	public void addPos(T elem, int position) {
		if (elem == null) {
			throw new NullPointerException("Error, el elemento no puede ser nulo\n");
		}
		
		if (position <= 0) {
			throw new IllegalArgumentException("Error, la posición no puede se menor o igual a 0\n");
		}
		
		Node<T> nuevo = new Node<>(elem);
		
		if (position == 1) {
			nuevo.next = front;
			front = nuevo;
		} else if (position > size()) {
			addLast(elem);
		} else {
			addPosRec(elem, front, position - 1);
		}
		
	}
	
	private void addPosRec(T elem, Node<T> prev, int position) {
		
		Node<T> nuevo = new Node<>(elem);
		
		if (position == 1) {
			nuevo.next = prev.next;
			prev.next = nuevo;
		} else {
			addPosRec(elem, prev.next, position - 1);
		}
	}


	@Override
	public T getElemPos(int position) {
		if (position < 1 || position > size()) {
			throw new IllegalArgumentException("Error, la posición tiene que estar entre 1 y el tamaño\n");
		}
		
		return getElemPosRec(front, position);
	}
	
	private T getElemPosRec(Node<T> node, int position) {
		if (position == 1) {
			return node.elem;
		} else {
			return getElemPosRec(node.next, position - 1);
		}
	}



	@Override
	public int getPosFirst(T elem) {
		if (elem == null) {
			throw new NullPointerException("Error, el elemento no puede ser nulo\n");
		} else {
			return getPosFirstRec(elem, front, 1);
		}
	}
	
	private int getPosFirstRec(T elem, Node<T> node, int position) {
		
		if (node == null) {
			throw new NoSuchElementException("Error, el elemento no está en la lista\n");
		} else if (node.elem.equals(elem)) {
			return position;
		} else {
			return getPosFirstRec(elem, node.next, position + 1);
		}
	}
	
	@Override
	public int getPosLast(T elem) {
	    if (elem == null) {
	        throw new NullPointerException("Error, el elemento no puede ser nulo\n");
	    }
	    int result = getPosLastRec(elem, front, 1);
	    if (result == -1) {
	        throw new NoSuchElementException("Error, el elemento no está en la lista\n");
	    }
	    return result;
	}
	
	private int getPosLastRec(T elem, Node<T> node, int position) {
	    if (node == null) {
	        return -1;
	    }
	    
	    int resto = getPosLastRec(elem, node.next, position + 1);
	    
	    if (resto != -1) {
	        return resto;
	    } else if (node.elem.equals(elem)) {
	        return position;
	    } else {
	        return -1;
	    }
	}



	@Override
	public T removelast() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("Error, la lista no puede estar vacía\n");
		}
		
		T[] result = (T[]) new Object[1];
		front = removeLastRec(front, result);
		return result[0];
	}
	
	private Node<T> removeLastRec(Node<T> node, T[] result) {
		if (node.next == null) {
			result[0] = node.elem;
			return null;	
		} else {
			node.next = removeLastRec(node.next, result);
			return node;
		}
		
	}
	
	

	@Override
	public T removeLastElem(T elem) {
	    if (elem == null) {
	        throw new NullPointerException("Error, el elemento no puede ser nulo\n");
	    }
	    
	    if (isEmpty()) {
	        throw new NoSuchElementException("Error, la lista está vacía\n");
	    }
	    
	   
	    T eliminar = removeLastElemRec(front, front.next, elem);
	    
	    if (eliminar != null) {
	        return eliminar;
	    }
	    
	    if (front.elem.equals(elem)) {
	        T dev = front.elem;
	        front = front.next;
	        return dev;
	    }
	    
	    throw new NoSuchElementException("Error, el elemento no está en la lista\n");
	}

	private T removeLastElemRec(Node<T> prev, Node<T> current, T elem) {
	    if (current == null) {
	        return null;
	    }
	    
	    T eliminar = removeLastElemRec(current, current.next, elem);
	    
	    if (eliminar != null) {
	        return eliminar;
	    }
	    
	    if (current.elem.equals(elem)) {
	        prev.next = current.next;
	        return current.elem; 
	    }
	    
	    return null;
	}



	@Override
	public EDList<T> reverse() {
		EDList<T> lista_devolver = new LinkedEDList<>();
		reverseRec(front, lista_devolver);
		return lista_devolver;
	}
	
	private void reverseRec(Node<T> actual, EDList<T> listaNueva){
		
		if (actual == null) {
			return;
		}
		
		reverseRec(actual.next, listaNueva);
		
		listaNueva.addLast(actual.elem);
	}



	@Override
	public int removeOddElements(){
		if (isEmpty()) {
			return 0;
		}
		
		front = front.next;
		
		if (front == null) {
			return 1;
		}
		
		return 1 + removeOddElementsRec(front);
	}
	
	private int removeOddElementsRec(Node<T> node) {
		if (node == null || node.next == null) {
			return 0;
		} else {
			node.next = node.next.next;
			return 1 + removeOddElementsRec(node.next);
		}
	}



	@Override
	public int removeConsecDuplicates() {
		if (isEmpty()) {
			return 0;
		}
		return removeConsecDuplicatesRec(front, front.next);
	}
	
	private int removeConsecDuplicatesRec(Node<T> anterior, Node<T> actual) {
		if (actual == null) {
			return 0;
		}
		
		int borrados = removeConsecDuplicatesRec(actual, actual.next);
		
		if (anterior.elem.equals(actual.elem)) {
			anterior.next = actual.next;
			return 1 + borrados;
		}
		return borrados;
	}



	@Override
	public String toSringExceptFromUntilReverse(int from, int until) {
		
		if (from <= 0 || until <= 0 || from < until) {
			throw new IllegalArgumentException("Error, introduce valores válidos para from y until\n");
		}
		
		return "(" + toStringExceptFromUntilReverse(front, from, until, 1) + ")";
	}
	
	private String toStringExceptFromUntilReverse(Node<T> node, int from, int until, int posicion) {
		
		if (node == null) {
			return "";
		}
		
		String cadena = toStringExceptFromUntilReverse(node.next, from, until, posicion + 1);
		
		if (posicion > from || posicion < until) {
			return cadena + node.elem + " ";
		}
		
		return cadena;		
		
	}



	@Override
	public boolean lengthEqualsTo(int n) {
		return lengthEqualsToRec(front) == n;
	}
	
	private int lengthEqualsToRec(Node<T> node) {
		
		if (node == null) {
			return 0;
		}
		
		return 1 + lengthEqualsToRec(node.next);
	}



	@Override
	public String toString() {
		if (isEmpty()) {
			return "()";
		}
		
		return "(" + toStringRec(front) + ")";
	}
	
	private String toStringRec(Node<T> node) {
		if (node == null) {
			return "";
		}
		
		return node.elem + " " + toStringRec(node.next);
	}
	



	@Override
	public T removePenult() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("Error, la lista no puede estar vacía\n");
		}
		
		if (front.next == null) {
			throw new NoSuchElementException("Error, la lista no puede tener solo un elemento\n");
		}
		
		T eliminado;
		
		if (front.next.next == null) {
			eliminado = front.elem;
			front = front.next;
			return eliminado;
		}
		
		return removePenultRec(front);
	}
	
	private T removePenultRec(Node<T> node) {
		
		if (node.next.next.next == null) {
			T result = node.next.elem;
			node.next = node.next.next;
			return result;
		} 
		
		return removePenultRec(node.next);
	}

	
	
}
