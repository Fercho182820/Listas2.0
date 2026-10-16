package UNAM.ICO;

public class Nodo {
    private Object dato;
    private Nodo siguiente;

    public Nodo() {
    }

    public Nodo(Object dato) {
        this.dato = dato;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public void conectar(Nodo otroNodo) {
        this.siguiente = otroNodo;
    }

    public void desconectar() {
        this.siguiente = null;
    }

    public boolean tieneSiguiente() {
        return siguiente != null;
    }

    @Override
    public String toString() {
        return "Nodo{dato=" + dato + "}";
    }
}
