package ule.edi.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * arbol binario de busqueda (binary search tree, BST).
 * 
 * El codigo fuente esta en UTF-8, y la constante EMPTY_TREE_MARK definida en
 * AbstractTreeADT del proyecto API deberia ser el simbolo de conjunto vacio:
 * ∅
 * 
 * Si aparecen caracteres "raros", es porque el proyecto no esta bien
 * configurado en Eclipse para usar esa codificacion de caracteres.
 *
 * En el toString() se usa el formato:
 * 
 * Un arbol vaci­o se representa como "∅". Un Ã¡rbol no vacio como
 * "{(informacion rai­z), sub-arbol 1, sub-arbol 2, ...}".
 * 
 * Por ejemplo, {A, {B, ∅, ∅}, ∅} es un arbol binario con rai­z "A" y un
 * unico sub-arbol, a su izquierda, con rai­z "B".
 * 
 * El metodo render() tambien representa un arbol, pero con otro formato; por
 * ejemplo, un arbol {M, {E, ∅, ∅}, {S, ∅, ∅}} se muestra como:
 * 
 * M 
 * | E
 * | | ∅
 * | | ∅ 
 * | S 
 * | | ∅
 * | | ∅
 * 
 * Cualquier nodo puede llevar asociados etiquetas: pares (clave,valor) para adjuntar
 * informacion extra. Si es el caso, tanto toString() como render() mostraran
 * los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor) y con
 * {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en los
 * elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> seria muy restrictivo; en su lugar se
 * permiten tipos que sean comparables no solo con exactamente T sino tambien
 * con tipos por encima de T en la herencia.
 * 
 * @param <T> tipo de la informacion en cada nodo, comparable.
 */
public class BinarySearchTree<T extends Comparable<? super T>>  {

	// solo tiene un atributo: root
	
	public static final String NULL_MARK ="∅";
	public BinaryTreeNode<T> root;
	
	
	/**
	 * CONSTRUCTOR: arbol BST vaci­o
	 */
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * Indica si el árbol es vacío 
	 * 
	 * @return true si el árbol es vacio: root es null
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 *  devuelve la cadena formada por el contenido del árbol teniendo en cuenta que 
	 *  si un nodo tiene su atributo count>1 pone entre paréntesis su valor justo detrás del atributo elem
	 *  También debe mostrar las etiquetas que tenga el nodo (si las tiene)
	 * 
	 * Por ejemplo: {M, {E(2), ∅, ∅}, {K(5), ∅, ∅}}
	 * 
	 * @return cadena con el contenido del árbol incluyendo su atributo count entre paréntesis si elemento tiene más de 1 instancia
	 */
	
	@Override
	public String toString() {
		 if (root==null)
			 return NULL_MARK;
		 else
			return root.toString();
	}
	
	/**
	 * Indica si el árbol es hoja (todos sus hijos vacíos)
	 * 
	 * @return cierto si todos los hijos son vacíos
	 * @throws EmptyCollectionException 
	 */
	public boolean isLeaf(BinaryTreeNode<T> node) {
		if (node == null) {
			throw new IllegalArgumentException("Error, el árbol no puede estar vacío\n");
		}
		
		return (node.left == null && node.right == null);
	}
	

	/**
	 * Inserta los elementos que no sean null, de un array en el arbol. 
	 * (si alguno es 'null', no lo inserta), devolviendo el nº de elems insertados en nodo nuevo
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 * @return  	numero de elementos insertados en el arbol en un nodo nuevo
	 */
	public int insert(T... elements) {
		int contador = 0;
	    for (T element : elements) {
	        if (element != null) {
	            if (insert(element)) {
	                contador++;
	            }
	        }
	    }
	    return contador;
	}

	/**
	 * Inserta (como hoja) un nuevo elemento en el arbol de busqueda.
	 * 
	 * Debe asignarse valor a su atributo father (referencia a su nodo padre o null
	 * si es la rai­z)
	 * 
	 * No se permiten elementos null. Si element es null dispara excepcion:IllegalArgumentException 
	 * Si el elemento ya existe en el arbol
	 *  no inserta un nodo nuevo, sino que incrementa el atributo count del nodo que tiene igual contenido.
	 * 
	 * @param element valor a insertar.
	 * @return true si se insertó en un nuevo nodo (no existia ese elemento en el arbol),
	 *         false en caso contrario
	 * @throws IllegalArgumentException si element es null
	 */
	public boolean insert(T element) {
		if (element == null) {
			throw new IllegalArgumentException("Error, el elemento a añadir no puede ser nulo\n");
		}
		
		if (root == null) {
	        root = new BinaryTreeNode<T>(element);
	        return true;
	    }
	    return insertRec(root, element);
	}
	
	private boolean insertRec(BinaryTreeNode<T> node, T element) {
		
		int diferencia = element.compareTo(node.elem);
		
		if (diferencia < 0) {
			if (node.left == null) {
				node.left = new BinaryTreeNode<T> (element);
				node.left.father = node;
				return true;
			} else {
				return insertRec(node.left, element);
			}	
		} else if (diferencia > 0) {
			if (node.right == null) {
				node.right = new BinaryTreeNode<> (element);
				node.right.father = node;
				return true;
			} else {
				return insertRec(node.right, element);
			}	
		} else {
			node.count++;
			return false;
		}
	}

	/**
	 * Busca el elemento en el arbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param element valor a buscar.
	 * @return true si el elemento esta en el arbol, false en caso contrario
	 * @throws IllegalArgumentException si element es null
	 *
	 */
	public boolean contains(T element) {
		if (element == null) {
			throw new IllegalArgumentException("Error, el elemento buscado no está en el árbol\n");
		}
		
		if (root == null) {
			return false;
		}
		return containsRec(root, element);
	}
	
	private boolean containsRec(BinaryTreeNode<T> node, T element) {
		int dif = element.compareTo(node.elem);
		
		if (dif > 0) {
			if (node.right == null) {
				return false;
			}
			return containsRec(node.right, element);
		} else if (dif < 0) {
			if (node.left == null) {
				return false;
			}
			return containsRec(node.left, element);
		} else {
			return true;
		}
		
	}
	
	
	
	/**
	 * Devuelve un iterador que recorre los elementos (sin tener en cuenta el número de instancias)del arbol por niveles segun
	 * el recorrido en anchura
	 * 
	 * Por ejemplo, con el arbol
	 * 
	 * {50, {30(2), {10, ∅, ∅}, {40, ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
	 * 
	 * y devolvera el iterador que recorrera los nodos en el orden: 50, 30, 80, 10, 40, 60
	 * 
	 * 
	 * 
	 * @return iterador para el recorrido en anchura
	 */
    public Iterator<T> iteratorWidth() {
	 	LinkedList<T> lista = new LinkedList<T>();
	 	if (root == null) {
	 		return lista.iterator();
	 	}
	 	LinkedList<BinaryTreeNode<T>> cola = new LinkedList<>();
	 	
	 	cola.add(root);
	 	
	 	while (!cola.isEmpty()) {
	 		BinaryTreeNode<T> nodo = cola.poll();
	 		lista.add(nodo.elem);
	 		if (nodo.left != null) {
	 			cola.add(nodo.left);
	 		}
	 		if (nodo.right != null) {
	 			cola.add(nodo.right);
	 		}
	 	}
	 	return lista.iterator();
	}

	/**
	 * Devuelve un iterador que recorre los elementos (teniendo en cuenta el número de instancias)del arbol por niveles segun
	 * el recorrido en anchura
	 * 
	 * Por ejemplo, con el arbol
	 * 
	 * {50, {30(2), {10, ∅, ∅}, {40, ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
	 * 
	 * y devolvera el iterador que recorrera los nodos en el orden: 50, 30, 30, 80, 80, 10, 40, 60
	 *  
	 * @return iterador para el recorrido en anchura
	 */
     public Iterator<T> iteratorWidthInstances() {
		LinkedList<T> lista = new LinkedList<>();
		if (root == null) {
			return lista.iterator();
		}
		
		LinkedList<BinaryTreeNode<T>> cola = new LinkedList<>();
		cola.add(root);
		
		while (!cola.isEmpty()) {
			BinaryTreeNode<T> nodo = cola.poll();
			int contador = nodo.count;
			while (contador > 0) {
				lista.add(nodo.elem);
				contador--;
			}
			if (nodo.left != null) {
				cola.add(nodo.left);
			}
			if (nodo.right != null) {
				cola.add(nodo.right);
			}
		}
		return lista.iterator();
	 }
	
		
	/**
	 * Cuenta el número de elementos diferentes del arbol (no tiene en cuenta las instancias)
	 * 
	 * Por ejemplo, con el arbol
	 * 
	 * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
	 * 
	 * la llamada a ejemplo.instancesCount() devolvera 6
	 * 
	 * @return el numero de elementos diferentes del arbol 
	 */
    public int size() {
		// TODO implementar este metodo
			return sizeRec(root);
		}
    
    private int sizeRec(BinaryTreeNode<T> node) {
		if (node==null)
			return 0;
		else 
			return 1+ sizeRec(node.left)+sizeRec(node.right);
	}
	
	
    /**
	 * Cuenta el número de instancias de elementos diferentes del arbol 
	 * 
	 * Por ejemplo, con el arbol ejemplo=
	 * 
	 * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
	 * 
	 * la llamada a ejemplo.instancesCount() devolvera 11
	 * 
	 * @return el número de instancias de elementos del arbol 
	 */
	public int instancesCount() {
		if (root == null) {
			return 0;
		}
		
		return instancesCountRec(root);
	}
	
	private int instancesCountRec(BinaryTreeNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return node.count + instancesCountRec(node.left) + instancesCountRec(node.right);
		}
	}
	

	/**
	 * Elimina un elemento del arbol. Si el atributo count del nodo que contiene el elemento es >1, simplemente se decrementará este valor en una unidad
	 * 
	 * Si hay que eliminar el nodo, y tiene dos hijos, se tomara el criterio de sustituir el
	 * elemento por el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no esta en el arbol
	 * @throws IllegalArgumentException si element es null
     *
	 */
	public void remove(T element) {
		
		// TODO Implementar el metodo
		remove(element, 1);
	}
	
	/**
	 * Decrementa el número de instancias del elemento en num unidades.
	 * Si count queda en cero o negativo, se elimina el elemento del arbol. 
	 * Devuelve el número de instancias que pudo eliminar
	 * 
	 * 
	 * Si hay que eliminar el nodo, y tiene dos hijos, se tomara el criterio de sustituir el
	 * elemento por el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no esta en el arbol	
	 * @throws IllegalArgumentException si element es null
	 * @return numero de instancias eliminadas
	 * 
	 */
	public int remove(T element, int num) {
		if (element == null) {
			throw new IllegalArgumentException("Error, el elemento no puede ser nulo\n");
		}
		
		if (root == null || !contains(element)) {
			throw new NoSuchElementException("Error, el elemento no está contenido en el árbol\n");
		}
		
		return removeRec(root, element, num);
	}
	
	private int removeRec(BinaryTreeNode<T> node, T element, int num) {
	    int dif = element.compareTo(node.elem);

	    if (dif < 0) {
	        if (node.left == null) {
	            return 0;
	        }
	        return removeRec(node.left, element, num);
	    } else if (dif > 0) {
	        if (node.right == null) {
	            return 0;
	        }
	        return removeRec(node.right, element, num);
	    } else {
	        int elementosDevolver = 0;
	        if (num >= node.count) {
	            elementosDevolver = node.count;
	            if (node.father == null) {
	                if (isLeaf(node)) {
	                    root = null;
	                } else if (node.left != null && node.right == null) {
	                    root = node.left;
	                    root.father = null;
	                } else if (node.right != null && node.left == null) {
	                    root = node.right;
	                    root.father = null;
	                } else {
	                    BinaryTreeNode<T> nodoPeque = findMinRec(node.right);
	                    node.elem = nodoPeque.elem;
	                    node.count = nodoPeque.count;
	                    if (nodoPeque.right != null) {
	                        nodoPeque.father.left = nodoPeque.right;
	                        nodoPeque.right.father = nodoPeque.father;
	                    } else {
	                        nodoPeque.father.left = null;
	                    }
	                }
	            } else if (node.father.left == node) {
	                if (node.left != null && node.right == null) {
	                    node.father.left = node.left;
	                    node.left.father = node.father;
	                } else if (node.right != null && node.left == null) {
	                    node.father.left = node.right;
	                    node.right.father = node.father;
	                } else if (node.left != null && node.right != null) {
	                    BinaryTreeNode<T> nodoPeque = findMinRec(node.right);
	                    node.elem = nodoPeque.elem;
	                    node.count = nodoPeque.count;
	                    if (nodoPeque.right != null) {
	                        nodoPeque.father.left = nodoPeque.right;
	                        nodoPeque.right.father = nodoPeque.father;
	                    } else {
	                        nodoPeque.father.left = null;
	                    }
	                } else {
	                    node.father.left = null;
	                }
	            } else if (node.father.right == node) {
	                if (node.left != null && node.right == null) {
	                    node.father.right = node.left;
	                    node.left.father = node.father;
	                } else if (node.right != null && node.left == null) {
	                    node.father.right = node.right;
	                    node.right.father = node.father;
	                } else if (node.left != null && node.right != null) {
	                    BinaryTreeNode<T> nodoPeque = findMinRec(node.right);
	                    node.elem = nodoPeque.elem;
	                    node.count = nodoPeque.count;
	                    if (nodoPeque.right != null) {
	                        nodoPeque.father.left = nodoPeque.right;
	                        nodoPeque.right.father = nodoPeque.father;
	                    } else {
	                        nodoPeque.father.left = null;
	                    }
	                } else {
	                    node.father.right = null;
	                }
	            }
	        } else {
	            elementosDevolver = num;
	            node.count -= num;
	        }
	        return elementosDevolver;
	    }
	}
	
	private BinaryTreeNode<T> findMinRec(BinaryTreeNode<T> nodo) {
		if (nodo.left == null) {
			return nodo;
		}
		return findMinRec(nodo.left);
	}

	
	
	/**
	 * Elimina todas las instancias del elemento en el árbol 
	 * eliminando del arbol el nodo que contiene el elemento .
	 * 
	 * 
	 * Se tomara el criterio de sustituir el elemento por el menor de sus mayores 
	 * y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no esta en el arbol	
	 * @throws IllegalArgumentException si element es null
	 */
	public int removeAll(T element) {
		if (element == null) {
			throw new IllegalArgumentException("Error, el elemento no puede ser nulo\n");
		}
		
		if (root == null || !contains(element)){
			throw new NoSuchElementException("Error, el elemento no está contenido en el árbol\n");
		}
		
		 int count = findCount(root, element);
		 return remove(element, count);
	}
	
	private int findCount(BinaryTreeNode<T> node, T element) {
	    int dif = element.compareTo(node.elem);

	    if (dif < 0) {
	        return findCount(node.left, element);
	    } else if (dif > 0) {
	        return findCount(node.right, element);
	    } else {
	        return node.count;
	    }
	}

	/**
	* Devuelve el sub-árbol indicado. (para tests)
	* path será el camino para obtener el sub-arbol. Está formado por 0 y 1.
	* Si se codifica "bajar por la izquierda" como "0" y
	* "bajar por la derecha" como "1", el camino desde un 
	* nodo N hasta un nodo M (en uno de sus sub-árboles) será          * la cadena de 0s y 1s que indica cómo llegar desde N hasta M.
	*
	* Se define también el camino vacío desde un nodo N hasta
	* él mismo, como cadena vacía.
	* 
	* Si el subarbol no existe lanzará la excepción NoSuchElementException.
	* 
	* @param path
	* @return el nodo no vacío que se alcanza con ese camino
	* @throws NoSuchElementException si el camino no alcanza un nodo no vacío en el árbol
	* @throws IllegalArgumentException si el camino no contiene sólamente 0s y 1s
*/
	public BinaryTreeNode<T> getSubtreeWithPath(String path) {
		return getSubTreeWithPathRec(root, path);
	}
	
	private BinaryTreeNode<T> getSubTreeWithPathRec(BinaryTreeNode<T> node, String path) {

	    BinaryTreeNode<T> nodoDevolver = null;

	    if (path.isEmpty()) {
	        nodoDevolver = node;
	        return nodoDevolver;
	    }

	    char c = path.charAt(0);

	    if (c != '0' && c != '1') {
	        throw new IllegalArgumentException("Error, algún número en la cadena no es un 0 o un 1\n");
	    }

	    if (c == '0') {
	        if (node.left != null) {
	            return getSubTreeWithPathRec(node.left, path.substring(1));
	        } else {
	            throw new NoSuchElementException("Error, no existe ese elemento en el árbol\n");
	        }
	    } else if (c == '1') {
	        if (node.right != null) {
	            return getSubTreeWithPathRec(node.right, path.substring(1));
	        } else {
	            throw new NoSuchElementException("Error, no existe ese elemento en el árbol\n");
	        }
	    }

	    return nodoDevolver;
	}



/**
 * Devuelve el contenido del nodo alcanzado desde la raíz
 * de éste árbol, con el camino dado.
 * 
 * Por ejemplo, sea un árbol "A" {10, {5, ∅, ∅}, {20, ∅, {30, ∅, ∅}}}:
 * 
 * 10
 * |  5
 * |  |  ∅
 * |  |  ∅
 * |  20
 * |  |  ∅ 
 * |  |  30
 * |  |  |  ∅
 * |  |  |  ∅
 * 
 * Entonces se tiene que A.getContentWithPath("1") es 20 y 
 * que A.getContentWithPath("") es 10.
 * 
 * @param path camino a seguir desde la raíz.
 * @return contenido del nodo alcanzado.
 * @throws NoSuchElementException si el camino no alcanza un nodo no vacío en el árbol
 * @throws IllegalArgumentException si el camino no contiene sólamente 0s y 1s
*/
	public T getContentWithPath(String path) {
		BinaryTreeNode<T> nodo = getSubtreeWithPath(path);
		return nodo.elem;
	}
	
	
	

	/**
	 * Importante: Solamente se puede recorrer el arbol una vez
	 * 
	 * Calcula y devuelve el numero de nodos que son hijos unicos y etiqueta cada
	 * nodo que sea hijo unico (no tenga hermano hijo del mismo padre) con la
	 * etiqueta "onlySon" y el valor correspondiente a su posicion segun el
	 * recorrido preorden en este arbol.
	 * 
	 * La rai­z no se considera hijo unico.
	 * 
	 * Por ejemplo, sea un arbol ejemplo, que tiene 3 hijos unicos, 
	 * la llamada a ejemplo.tagOnlySonPreorder() devuelve 3 y los va etiquetando
	 * segun su recorrido en preorden.
	 * 
	 * {30, {10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}, ∅}
	 * 
	 *
	 * el arbol quedari­a etiquetado:
	 * 
	 * {30, {10 [(onlySon, 2)], {5, {2 [(onlySon, 4)], ∅, ∅}, ∅}, {20, {15 [(onlySon, 6)], {12
	 * [(onlySon, 7)], ∅, ∅}, ∅}, ∅}, ∅}
	 * 
	 */
	public int tagOnlySonPreorder() {
	    return tagOnlySonPreorderRec(root, new int[]{0});
	}

	private int tagOnlySonPreorderRec(BinaryTreeNode<T> node, int[] pos) {
	    if (node == null) {
	        return 0;
	    }
	    
	    pos[0]++;
	    int contador = 0;
	    
	    if (node.father != null) {
	        if (node.father.left == null || node.father.right == null) {
	            node.setTag("onlySon", pos[0]);
	            contador = 1;
	        }
	    }
	    
	    contador += tagOnlySonPreorderRec(node.left, pos);
	    contador += tagOnlySonPreorderRec(node.right, pos);
	    
	    return contador;
	}

	
	
	/**
	 * Elimina el nodo que contiene el elemento menor del árbol. 
	 * Este método busca el nodo con el menor valor en el árbol, lo elimina y 
	 * regresa la cantidad total de instancias que tenía el nodo (count). 
	 * Si el nodo mínimo tiene un hijo derecho, este hijo asciende para reemplazarlo.
	 * Si no hay hijos, simplemente se elimina el nodo. 
	 * 
	 * Si el árbol está vacío devuelve 0.
	 * 
	 * Para el árbol {30, {10, {5, ∅, {6, ∅, ∅}}, {20, {15, ∅, ∅}, ∅}},∅},
	 * se eliminaría el 5, y el árbol quedaría: 
	 * {30, {10, {6, ∅, ∅}, {20, {15, ∅, ∅}, ∅}},∅}
	 * 
	 * @return int La cantidad de instancias del nodo con el mínimo valor
	 */	
	 public int removeAllMin() {
		 if (root == null) {
			 return 0;
		 }
		 
		 return removeAllMinRec(root);
	 }
	 
	 private int removeAllMinRec(BinaryTreeNode<T> nodo) {
		 
		 if(nodo.left == null) {
			 int contador = 0;
			 contador = nodo.count;
			 if (nodo.father == null) {
				 root = nodo.right;
				 if (root != null) {
					 root.father = null;
				 }
			 } else if (nodo.right != null) {
				 nodo.father.left = nodo.right;
				 nodo.right.father = nodo.father;
			 } else {
				 nodo.father.left = null;
			 }
			 return contador;
		 }
		 
		 return removeAllMinRec(nodo.left);
	 }

 /**
	 *  Importante: Solamente se puede recorrer el arbol una vez
	 *  
	 * Devuelve una lista con información de todos los nodos hoja del árbol en inorden
	 * 
	 * Para cada hoja se almacena una cadena con:
	 * - su posición en el recorrido inorden,
	 * - el contenido del nodo,
	 * - el camino desde la raíz hasta la hoja.
	 * 
	 * El camino se representa mediante:
	 * - 'L' para indicar hijo izquierdo,
	 * - 'R' para indicar hijo derecho.
	 * 
	 * Por ejemplo, para el árbol:
	 *        10
	 *       /  \
	 *      5    20
	 *     /    /  \
	 *    2    15  30
	 * 
	 * el método devolvería la lista de strings:
	 * 
	 * ["posicion: 1 - contenido: 2 - camino: LL",
	 *  "posicion: 4 - contenido: 15 - camino: RL",
	 *  "posicion: 6 - contenido: 30 - camino: RR"]
	 * 
	 * Si el árbol está vacío, devuelve una lista vacía.
	 * 
	 * @return lista con un string para cada elemento del arbol
	 */
	 public LinkedList<String> showLeaves(){
		 // TODO EL MÉTODO
		 LinkedList<String> lista= new LinkedList<String>();
		 if (root == null) {
			 return lista;
		 } 
		 showLeavesRec(root, lista, new int[]{0}, "");
		 return lista;
	 }
	 
	 private void showLeavesRec(BinaryTreeNode<T> node, LinkedList<String> lista, int[] pos, String camino){
		 
		 if (node == null) {
			 return;
		 }
		 
		 showLeavesRec(node.left, lista, pos, camino + "L");
		 
		 pos[0]++;
		 if (isLeaf(node)) {
			 lista.add("posicion: " + pos[0] + " - contenido: " + node.elem + " - camino: " + camino);
		 }
		 
		 showLeavesRec(node.right, lista, pos, camino + "R");
	 }
	
	 /**
	 * Importante: Solamente se puede recorrer el arbol una vez
	 * 
	 * Etiqueta cada nodo con la etiqueta "height" y el valor correspondiente a la
	 * altura del nodo.
	 * 
	 * Por ejemplo, sea un arbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
	 * 
	
	 * el arbol quedara etiquetado:
	 * 
	 * {10 [(height, 1)], 
	 * {5 [(height, 2)], {2 [(height, 3)],∅, ∅}, ∅}, 
	 * {20[(height, 2)], {15 [(height, 3)], {12 [(height, 4)], ∅, ∅}, ∅}, ∅}}
	 * 
	 */
	public void tagHeight() {
		tagHeightRec(root, 1);
	}
	
	private void tagHeightRec(BinaryTreeNode<T> node, int altura) {
		if (node == null) {
			return;
		}
		
		node.setTag("height", altura);
		
		tagHeightRec(node.left, altura + 1);
		
		tagHeightRec(node.right, altura + 1);
	}
	
	
	/**
	 * Importante: Solamente se puede recorrer el arbol una vez
	 * 
	 * Etiqueta cada nodo hoja con la etiqueta "height" y el valor correspondiente a la
	 * altura del nodo.
	 * 
	 * Por ejemplo, sea un arbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
	 * 
	 * el arbol quedara etiquetado:
	 * 
	 * {10, 
	 * {5, {2 [(height, 3)],∅, ∅}, ∅}, 
	 * {20,{15, {12 [(height, 4)], ∅, ∅}, ∅}, ∅}
	 * }
	 * 
	 */
	public void tagHeightLeaf() {
		tagHeightLeafRec(root, 1);
	}
	
	private void tagHeightLeafRec(BinaryTreeNode<T> nodo, int altura) {
		
		if (nodo == null) {
			return;
		}
		
		if (isLeaf(nodo)) {
			nodo.setTag("height", altura);
		}
		
		tagHeightLeafRec(nodo.left, altura + 1);
		
		tagHeightLeafRec(nodo.right, altura + 1);
	}

	
	/**
	 * Importante: Solamente se puede recorrer el arbol una vez
	 * 
	 * Recorre en orden descendente el arbol etiquetando todos sus nodos con la etiqueta "descend" y
	 * el valor correspondiente a la posición en dicho recorrido.
	 * 
	 * 
	 * Por ejemplo, sea el arbol ejemplo
	 * 
	 * {50, {30(2), {10, ∅, ∅}, {40(4), ∅, ∅}}, {80(2), {60, ∅, ∅}, ∅}}
	 * 
	 * la llamada a ejemplo.tagPosDescend() el arbol quedaria etiquetado:
	 * 
     *  {50 [(descend, 3)], {30(2) [(descend, 5)], {10 [(descend, 6)], ∅, ∅}, 
     *   {40(4) [(descend, 4)], ∅, ∅}}, {80(2) [(descend, 1)], {60 [(descend, 2)], ∅, ∅}, ∅}}
	 * 
	 */
	public void tagPosDescend() {
		 tagPosDescendRec(root, new int[]{0});
	}
	
	private void tagPosDescendRec(BinaryTreeNode<T> node, int[] pos) {
		if (node == null) {
			return;
		}
		
		tagPosDescendRec(node.right, pos);
		
		pos[0]++;
		node.setTag("descend", pos[0]);
		
		tagPosDescendRec(node.left, pos);
		
		
	}


/**
 * Importante: Solamente se puede recorrer el arbol una vez
 * 
 * Devuelve el elemento que está en la posición n en postorden
 * 
 * Por ejemplo, sea un arbol "A":
 * 
 * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
 *  
 *  la llamada con n=4, devolverá 30
 * 
 */
	
	public T postordenN(int n) {
	    return postordenNRec(root, n, new int[]{0});
	}

	private T postordenNRec(BinaryTreeNode<T> node, int n, int[] pos) {
	    if (node == null) {
	        return null;
	    }
	    
	    T resultado = postordenNRec(node.left, n, pos);
	    if (resultado != null) {
	        return resultado;
	    }
	    
	    resultado = postordenNRec(node.right, n, pos);
	    if (resultado != null) {
	        return resultado;
	    }
	    
	    pos[0]++;
	    if (pos[0] == n) {
	        return node.elem;
	    }
	    
	    return null;
	}


}

	