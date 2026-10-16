package UNAM.ICO;

import java.util.Objects;

public class ListaCircular {
    private Nodo cabeza;
    private int tamano;

    public ListaCircular() {
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
        if (estaVacia()) {
            nuevo.setSiguiente(nuevo);    // con un solo nodo, se apunta a sí mismo
            cabeza = nuevo;
        } else {
            Nodo ultimo = obtenerUltimoNodo();
            nuevo.setSiguiente(cabeza);   // el nuevo apunta a la antigua cabeza
            ultimo.setSiguiente(nuevo);   // el último cierra el ciclo con el nuevo
            cabeza = nuevo;
        }
        tamano++;
    }

    public void insertarFinal(Object dato) {
        if (estaVacia()) {
            insertarInicio(dato);
            return;
        }
        Nodo nuevo = new Nodo(dato);
        Nodo ultimo = obtenerUltimoNodo();
        nuevo.setSiguiente(cabeza);       // el nuevo cierra el ciclo con la cabeza
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
        // Si posicion == tamano, "anterior" es el último y su siguiente es la cabeza,
        // por lo que el ciclo se conserva sin tratamiento especial
        Nodo anterior = obtenerNodo(posicion - 1);
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevo);
        tamano++;
    }

    // ---------- Eliminaciones ----------

    public Object eliminarInicio() {
        validarQueNoEsteVacia();
        Nodo eliminado = cabeza;
        if (tamano == 1) {
            cabeza = null;
        } else {
            Nodo ultimo = obtenerUltimoNodo();
            cabeza = cabeza.getSiguiente();
            ultimo.setSiguiente(cabeza);  // el último ahora apunta a la nueva cabeza
        }
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
        penultimo.setSiguiente(cabeza);   // el penúltimo cierra el ciclo con la cabeza
        eliminado.desconectar();
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
        anterior.setSiguiente(eliminado.getSiguiente());
        eliminado.desconectar();
        tamano--;
        return eliminado.getDato();
    }

    // ---------- Búsqueda y recuperación ----------

    // Devuelve la posición del dato, o -1 si no existe
    public int buscar(Object dato) {
        Nodo actual = cabeza;
        for (int posicion = 0; posicion < tamano; posicion++) {   // máximo una vuelta
            if (Objects.equals(actual.getDato(), dato)) {
                return posicion;
            }
            actual = actual.getSiguiente();
        }
        return -1;
    }

    public Object obtener(int posicion) {
        validarPosicion(posicion);
        return obtenerNodo(posicion).getDato();
    }

    // ---------- Mostrar ----------

    // Hace exactamente una vuelta completa
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista está vacía");
            return;
        }
        String resultado = "";
        Nodo actual = cabeza;
        for (int i = 0; i < tamano; i++) {
            resultado += actual.getDato() + " → ";
            actual = actual.getSiguiente();
        }
        System.out.println(resultado + "(vuelve a " + cabeza.getDato() + ")");
    }

    // ---------- Verificación (para las pruebas) ----------

    // Comprueba que el ciclo se cierra justo después de "tamano" nodos
    public boolean ultimoApuntaAlPrimero() {
        if (estaVacia()) {
            return true;
        }
        Nodo actual = cabeza;
        int pasos = 0;
        while (actual.getSiguiente() != cabeza && pasos < tamano) {
            actual = actual.getSiguiente();
            pasos++;
        }
        return pasos == tamano - 1;
    }

    // ---------- Métodos privados de apoyo ----------

    private Nodo obtenerUltimoNodo() {
        return obtenerNodo(tamano - 1);
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
