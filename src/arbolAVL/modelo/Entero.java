package arbolAVL.modelo;

public class Entero implements Comparable{
	private int dato;
	public Entero(int dato){
		this.dato = dato;
	}
	public Entero(){
		this(0);
	}
	
	public int getDato() {
		return dato;
	}
	public void setDato(int dato) {
		this.dato = dato;
	}
	public boolean esIgual(Object q){
		Entero obj = (Entero)q;
		return (dato == obj.getDato());
	}
	
	public boolean esMenor(Object q){
		Entero obj = (Entero)q;
		return (dato < obj.getDato());
	}
	
	public boolean esMayor(Object q){
		Entero obj = (Entero)q;
		return (dato > obj.getDato());
	}
	public String toString(){
		return "" + dato;
	}

}
