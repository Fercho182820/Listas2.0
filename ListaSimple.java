package UNAM.ICO;

import java.util.Objects;

public class ListaSimple {
    private Nodo cabeza;
    private int tamano;

    public ListaSimple() {
        this.cabeza = null;
        this.tamano = 0;
    }

    // ---------- Consultas básicas ----------

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int tamano() {
        return tamano;
    }

    // ---------- Inserciones ----------

    public void insertarInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(cabeza);   // 1) el nuevo apunta a la antigua cabeza
        cabeza = nuevo;               // 2) la cabeza ahora es el nuevo
        tamano++;
    }

    public void insertarFinal(Object dato) {
        if (estaVacia()) {
            insertarInicio(dato);
            return;
        }
        Nodo nuevo = new Nodo(dato);
        Nodo ultimo = obtenerUltimoNodo();
        ultimo.setSiguiente(nuevo);
        tamano++;
    }

    public void insertarEn(int posicion, Object dato) {
        if (posicion < 0 || posicion > tamano) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion
                    + " (tamaño de la lista: " + tamano + ")");
        }
        if (posicion == 0) {
            insertarInicio(dato);
            return;
        }
        Nodo anterior = obtenerNodo(posicion - 1);
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(anterior.getSiguiente());  // 1) primero enlazo el nuevo con lo que sigue
        anterior.setSiguiente(nuevo);                 // 2) luego el anterior apunta al nuevo
        tamano++;
    }

    // ---------- Eliminaciones ----------

    public Object eliminarInicio() {
        validarQueNoEsteVacia();
        Nodo eliminado = cabeza;
        cabeza = cabeza.getSiguiente();
        eliminado.desconectar();
        tamano--;
        return eliminado.getDato();
    }

    public Object eliminarFinal() {
        validarQueNoEsteVacia();
        if (tamano == 1) {
            return eliminarInicio();
        }
        Nodo penultimo = obtenerNodo(tamano - 2);
        Nodo eliminado = penultimo.getSiguiente();
        penultimo.desconectar();      // el penúltimo ahora apunta a null
        tamano--;
        return eliminado.getDato();
    }

    public Object eliminarEn(int posicion) {
        validarQueNoEsteVacia();
        validarPosicion(posicion);
        if (posicion == 0) {
            return eliminarInicio();
        }
        Nodo anterior = obtenerNodo(posicion - 1);
        Nodo eliminado = anterior.getSiguiente();
        anterior.setSiguiente(eliminado.getSiguiente());  // el anterior se salta al eliminado
        eliminado.desconectar();
        tamano--;
        return eliminado.getDato();
    }

    // ---------- Búsqueda y recuperación ----------

    // Devuelve la posición del dato, o -1 si no existe
    public int buscar(Object dato) {
        Nodo actual = cabeza;
        int posicion = 0;
        while (actual != null) {
            if (Objects.equals(actual.getDato(), dato)) {
                return posicion;
            }
            actual = actual.getSiguiente();
            posicion++;
        }
        return -1;
    }

    public Object obtener(int posicion) {
        validarPosicion(posicion);
        return obtenerNodo(posicion).getDato();
    }

    // ---------- Mostrar ----------

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista está vacía");
            return;
        }
        String resultado = "";
        Nodo actual = cabeza;
        while (actual != null) {
            resultado += actual.getDato() + " → ";
            actual = actual.getSiguiente();
        }
        System.out.println(resultado + "null");
    }

    // ---------- Verificación (para las pruebas) ----------

    // Comprueba que el último nodo tenga siguiente = null
    public boolean ultimoApuntaANulo() {
        if (estaVacia()) {
            return true;
        }
        return obtenerUltimoNodo().getSiguiente() == null;
    }

    // ---------- Métodos privados de apoyo ----------

    private Nodo obtenerUltimoNodo() {
        Nodo actual = cabeza;
        while (actual.tieneSiguiente()) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    private Nodo obtenerNodo(int posicion) {
        Nodo actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    private void validarPosicion(int posicion) {
        if (posicion < 0 || posicion >= tamano) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion
                    + " (tamaño de la lista: " + tamano + ")");
        }
    }

    private void validarQueNoEsteVacia() {
        if (estaVacia()) {
            throw new IllegalStateException("La lista está vacía");
        }
    }
}
