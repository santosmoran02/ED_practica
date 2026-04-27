package ule.ed.recursivelist;


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
		// TODO RECURSIVAMENTE
		return null;
	}



	

	@Override
	public T removeLastElem(T elem) {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public EDList<T> reverse() {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public int removeOddElements(){
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public int removeConsecDuplicates() {
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public String toSringExceptFromUntilReverse(int from, int until) {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public boolean lengthEqualsTo(int n) {
		// TODO RECURSIVAMENTE
		return false;
	}



	@Override
	public String toString() {
		// TODO RECURSIVAMENTE
	
		return null;
	}


	@Override
	public T removePenult() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
