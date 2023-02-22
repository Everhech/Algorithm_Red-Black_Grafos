package arbolAVL.modelo;

/**
 * Representa un nodo que contiene tres campos: valor, izquierdo, derecho y el
 * factor de equilibrio
 *
 * @author PABLO RUIZ
 *
 */
public class Nodo {

    private Comparable valor;
    private Nodo izquierdo;
    private Nodo derecho;
    private boolean rojo = true;
    private Nodo papa;

    public Nodo getPapa() {
        return papa;
    }

    public void setPapa(Nodo papa) {
        this.papa = papa;
    }

    public Nodo() {
        valor = null;
        izquierdo = null;
        derecho = null;
    }

    public Nodo(Comparable valor) {
        this.valor = valor;
        izquierdo = null;
        derecho = null;
    }

    public boolean getColor() {
        return rojo;
    }

    public void setRojo(boolean rojo) {
        this.rojo = rojo;
    }

    public Comparable getValor() {
        return valor;
    }

    public void setValor(Comparable valor) {
        this.valor = valor;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}
