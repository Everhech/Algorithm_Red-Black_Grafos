package arbolAVL.modelo;

/**
 *
 * @authors Andres Torres Ciceri y Edwin Orlando Restrepo Mosquera
 */

public class ArbolAVL {

    private Nodo raiz;

    public ArbolAVL() {
        raiz = null;
    }
    
    //Obtiene raiz
    public Nodo getRaiz() {
        return raiz;
    }

    //Cambia la raiz
    public void setRaiz(Nodo nuevo) {
        raiz = nuevo;
    }

    /**
     * ROTACIONES
     */    
    /**
     * Rotacion simple: Izquierda
     * @return Nodo
     */
    public Nodo rotacionIzquierda(Nodo raiz) {
        Nodo aux = raiz.getIzquierdo();
        raiz.setRojo(true);
        aux.setPapa(raiz.getPapa());
        raiz.setIzquierdo(aux.getDerecho());
        aux.setDerecho(raiz);
        aux.getDerecho().setPapa(aux);
        return aux;
    }

    /**
     * Rotacion simple: Derecha
     * @return Nodo
     */
    public Nodo rotacionDerecha(Nodo raiz) {
        Nodo aux = raiz.getDerecho();
        aux.setPapa(raiz.getPapa());
        if (aux.getIzquierdo() != null) {
            aux.getIzquierdo().setPapa(raiz);
        }
        raiz.setDerecho(aux.getIzquierdo());
        raiz.setRojo(true);
        aux.setRojo(false);
        aux.setIzquierdo(raiz);
        aux.getIzquierdo().setPapa(aux);
        return aux;
    }
    
    /**
     * Rotacion Doble: Derecha - Izquierda
     * @return Nodo
     */
    public Nodo rotacionDerechaIzquierda(Nodo raiz) {
        Nodo aux;
        raiz.setIzquierdo(rotacionDerecha(raiz.getIzquierdo()));
        aux = rotacionIzquierda(raiz);
        return aux;
    }

    /**
     * Rotacion Doble: Izquierda - Derecha
     * @return Nodo
     */
    public Nodo rotacionIzquierdaDerecha(Nodo raiz) {
        Nodo aux;
        raiz.setDerecho(rotacionIzquierda(raiz.getDerecho()));
        aux = rotacionDerecha(raiz);
        return aux;
    }
    /**
     * Inserta un elemento en el arbol avl, llama al metodo recursivo
     * insertarAvl
     *
     * @param valor
     * @throws Exception
     */
    public void insertar(Object valor) throws Exception {
        Nodo nuevoNodo = new Nodo();
        Comparable dato;
        Comparable papa;
        dato = (Comparable) valor;
        papa = (Comparable) valor;        
        nuevoNodo.setValor(dato);
        if (raiz == null) {
            raiz = nuevoNodo;
        } else {
            raiz = insertarAVL(nuevoNodo, raiz, null);
            raiz.setRojo(false);
        }
    }

    /**
     * Metodo recursivo para insertar
     *
     * @param raiz, raiz
     * @param nuevoNodo, nodo a ingresar
     * @param padre, nodo padre del nuevo nodo
     * @return Nodo
     * @throws Exception
     */
    public Nodo insertarAVL(Nodo nuevoNodo, Nodo raiz, Nodo padre) {
        Nodo nuevoPadre = raiz, nodoAux = null;
        Nodo tio = null, abuelo = null;
        if (nuevoNodo.getValor().esMenor(raiz.getValor())) {
            if (raiz.getIzquierdo() == null) {
                nuevoNodo.setPapa(raiz);
                raiz.setIzquierdo(nuevoNodo);
            } else {
                nodoAux = insertarAVL(nuevoNodo, raiz.getIzquierdo(), raiz);
                raiz.setIzquierdo(nodoAux);
                tio = obtenerTio(nuevoNodo.getValor(), nuevoNodo, true);
                abuelo = obtenerAbuelo(nuevoNodo.getValor(), nuevoNodo, true);
            }
        } else if (nuevoNodo.getValor().esMayor(raiz.getValor())) {
            if (raiz.getDerecho() == null) {
                nuevoNodo.setPapa(raiz);
                raiz.setDerecho(nuevoNodo);
            } else {
                nodoAux = insertarAVL(nuevoNodo, raiz.getDerecho(), raiz);
                raiz.setDerecho(nodoAux);
                tio = obtenerTio(nuevoNodo.getValor(), nuevoNodo, true);
                abuelo = obtenerAbuelo(nuevoNodo.getValor(), nuevoNodo, true);
            }
        }
        if (raiz.getPapa() == null) {
            raiz.setRojo(false);
        }
        if (tio != null && abuelo != null) {
            if (nuevoNodo.getPapa().getColor() == true && tio.getColor() == true) {
                nuevoNodo.getPapa().setRojo(false);
                if (tio != raiz.getPapa()) {
                    tio.setRojo(false);
                }
                abuelo.setRojo(true);
            }
        }
        if (tio == null && abuelo != null && nuevoNodo.getColor() == true && nuevoNodo.getPapa().getColor() == true) {
            if (nuevoPadre.getIzquierdo() == null) {
                nuevoPadre = rotacionDerecha(abuelo);
                if (nuevoPadre.getIzquierdo() != null && nuevoPadre.getDerecho() != null) {
                    if (nuevoPadre.getColor() == false && nuevoPadre.getIzquierdo().getColor() == true && nuevoPadre.getDerecho().getColor() == true) {
                        return nuevoPadre;
                    }
                }
            } else if (nuevoPadre.getIzquierdo() == null && nuevoPadre.getDerecho().getDerecho() != null) {
                nuevoPadre = rotacionDerechaIzquierda(abuelo);
                return nuevoPadre;
            }
            if (nuevoPadre.getIzquierdo() != null && nuevoPadre.getIzquierdo().getIzquierdo() == null) {
                nuevoPadre = rotacionDerechaIzquierda(nuevoPadre);
                return nuevoPadre;
            }
            if (nuevoPadre.getIzquierdo() != null && nuevoPadre.getIzquierdo().getIzquierdo() != null) {
                nuevoPadre = rotacionIzquierda(abuelo);
            }
        }
        return nuevoPadre;
    }
    
    //Método que muestra el tio
    public boolean imprimirTio(Object valor, Nodo raiz, boolean verificar) {
        Comparable dato = (Comparable) valor;
        if (raiz == null) {
            verificar = false;
            return verificar;
        } else if (raiz.getValor().esIgual(dato)) {
            verificar = true;
            if (raiz.getPapa().getPapa().getIzquierdo() != raiz.getPapa()) {
                if (raiz.getPapa().getPapa().getIzquierdo() != null) {
                    System.out.println("\n\nEl Tio de " + raiz.getValor() + " es " + raiz.getPapa().getPapa().getIzquierdo().getValor());
                } else {
                    System.out.println("\nEl nodo no tiene nodo tio ");
                }
            } else {
                if (raiz.getPapa().getPapa().getDerecho() != null) {
                    System.out.println("\n\nEl Tio de " + raiz.getValor() + " es " + raiz.getPapa().getPapa().getDerecho().getValor());
                } else {
                    System.out.println("\nEl nodo no tiene nodo tio");
                }
            }
            return verificar;
        } else if (raiz.getValor().esMenor(dato)) {
            imprimirTio(dato, raiz.getDerecho(), verificar);
        } else if (raiz.getValor().esMayor(dato)) {
            imprimirTio(dato, raiz.getIzquierdo(), verificar);
        }
        return verificar;
    }

    //Método que muestra el abuelo
    public boolean imprimirAbuelo(Object valor, Nodo raiz, boolean verificar) {
        Comparable dato = (Comparable) valor;
        if (raiz == null) {
            verificar = false;
            return verificar;
        } else if (raiz.getValor().esIgual(dato)) {
            verificar = true;
            if (raiz.getPapa().getPapa() != null) {
                System.out.println("\n\nEl Abuelo de " + raiz.getValor() + " es " + raiz.getPapa().getPapa().getValor());
            } else {
                System.out.println("\nEl nodo no tiene nodo abuelo");
            }
            return verificar;
        } else if (raiz.getValor().esMenor(dato)) {
            imprimirAbuelo(dato, raiz.getDerecho(), verificar);
        } else if (raiz.getValor().esMayor(dato)) {
            imprimirAbuelo(dato, raiz.getIzquierdo(), verificar);
        }
        return verificar;
    }

    //Método para obtener al tio
    public Nodo obtenerTio(Object valor, Nodo raiz, boolean verificar) {
        Comparable dato = (Comparable) valor;
        Nodo tio = null;
        if (raiz == null) {
            verificar = false;
            return tio;
        } else if (raiz.getValor().esIgual(dato)) {
            verificar = true;
            if (raiz.getPapa() != null) {
                if (raiz.getPapa().getPapa() != null) {
                    if (raiz.getPapa().getPapa().getIzquierdo() != raiz.getPapa()) {
                        if (raiz.getPapa().getPapa().getIzquierdo() != null) {
                            tio = raiz.getPapa().getPapa().getIzquierdo();
                            return tio;
                        } else {
                            verificar = false;
                            return tio;
                        }
                    } else {
                        if (raiz.getPapa().getPapa().getDerecho() != null) {
                            tio = raiz.getPapa().getPapa().getDerecho();
                            return tio;
                        } else {
                            verificar = false;
                            return tio;
                        }
                    }

                }
            } else {
                return tio;
            }
        }
        return tio;
    }
    
    //Método para obtener al abuelo
    public Nodo obtenerAbuelo(Object valor, Nodo raiz, boolean verificar) {
        Comparable dato = (Comparable) valor;
        Nodo abuelo = null;
        if (raiz == null) {
            verificar = false;
            return abuelo;
        } else if (raiz.getValor().esIgual(dato)) {
            verificar = true;
            if (raiz.getPapa() != null) {
                if (raiz.getPapa().getPapa() != null) {
                    verificar = true;
                    abuelo = raiz.getPapa().getPapa();
                    return abuelo;
                } else {
                    verificar = false;
                    return abuelo;
                }
            } else {
                verificar = false;
                return abuelo;
            }
        }
        return abuelo;
    }
}