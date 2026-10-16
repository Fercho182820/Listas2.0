package UNAM.ICO;

import java.util.Objects;

public class ListaDoble {
    private NodoDoble cabeza;
    private NodoDoble cola;
    private int tamano;

    public ListaDoble() {
        this.cabeza = null;
        this.cola = null;
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
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);   // el nuevo apunta hacia adelante a la antigua cabeza
            cabeza.setAnterior(nuevo);    // la antigua cabeza apunta hacia atrás al nuevo
            cabeza = nuevo;
        }
        tamano++;
    }

    public void insertarFinal(Object dato) {
        if (estaVacia()) {
            insertarInicio(dato);
            return;
        }
        NodoDoble nuevo = new NodoDoble(dato);
        nuevo.setAnterior(cola);      // el nuevo apunta hacia atrás a la antigua cola
        cola.setSiguiente(nuevo);     // la antigua cola apunta hacia adelante al nuevo
        cola = nuevo;
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
        if (posicion == tamano) {
            insertarFinal(dato);
            return;
        }
        // Caso intermedio: el nodo que hoy está en "posicion" quedará después del nuevo
        NodoDoble actual = obtenerNodo(posicion);
        NodoDoble anterior = actual.getAnterior();
        NodoDoble nuevo = new NodoDoble(dato);

        nuevo.setAnterior(anterior);
        nuevo.setSiguiente(actual);
        anterior.setSiguiente(nuevo);
        actual.setAnterior(nuevo);
        tamano++;
    }

    // ---------- Eliminaciones ----------

    public Object eliminarInicio() {
        validarQueNoEsteVacia();
        NodoDoble eliminado = cabeza;
        if (tamano == 1) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);   // la nueva cabeza ya no tiene anterior
        }
        eliminado.desconectar();
        tamano--;
        return eliminado.getDato();
    }

    public Object eliminarFinal() {
        validarQueNoEsteVacia();
        NodoDoble eliminado = cola;
        if (tamano == 1) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);    // la nueva cola ya no tiene siguiente
        }
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
        if (posicion == tamano - 1) {
            return eliminarFinal();
        }
        NodoDoble eliminado = obtenerNodo(posicion);
        NodoDoble anterior = eliminado.getAnterior();
        NodoDoble siguiente = eliminado.getSiguiente();

        anterior.setSiguiente(siguiente);   // el anterior se salta al eliminado
        siguiente.setAnterior(anterior);    // el siguiente se salta al eliminado
        eliminado.desconectar();
        tamano--;
        return eliminado.getDato();
    }

    // ---------- Búsqueda y recuperación ----------

    // Devuelve la posición del dato, o -1 si no existe
    public int buscar(Object dato) {
        NodoDoble actual = cabeza;
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
        String resultado = "null ← ";
        NodoDoble actual = cabeza;
        while (actual != null) {
            resultado += actual.getDato();
            if (actual.tieneSiguiente()) {
                resultado += " ⇄ ";
            }
            actual = actual.getSiguiente();
        }
        System.out.println(resultado + " → null");
    }

    public void mostrarInverso() {
        if (estaVacia()) {
            System.out.println("La lista está vacía");
            return;
        }
        String resultado = "null ← ";
        NodoDoble actual = cola;
        while (actual != null) {
            resultado += actual.getDato();
            if (actual.tieneAnterior()) {
                resultado += " ⇄ ";
            }
            actual = actual.getAnterior();
        }
        System.out.println(resultado + " → null");
    }

    // ---------- Verificación (para las pruebas) ----------

    // Comprueba que todos los enlaces siguiente/anterior sean coherentes entre sí
    public boolean enlacesSonConsistentes() {
        if (estaVacia()) {
            return cola == null && tamano == 0;
        }
        if (cabeza.getAnterior() != null || cola.getSiguiente() != null) {
            return false;
        }
        int contadorAdelante = 0;
        NodoDoble actual = cabeza;
        while (actual != null) {
            if (actual.tieneSiguiente() && actual.getSiguiente().getAnterior() != actual) {
                return false;
            }
            contadorAdelante++;
            actual = actual.getSiguiente();
        }
        int contadorAtras = 0;
        actual = cola;
        while (actual != null) {
            contadorAtras++;
            actual = actual.getAnterior();
        }
        return contadorAdelante == tamano && contadorAtras == tamano;
    }

    // ---------- Métodos privados de apoyo ----------

    // Si la posición está en la primera mitad empieza desde la cabeza, si no, desde la cola
    private NodoDoble obtenerNodo(int posicion) {
        NodoDoble actual;
        if (posicion < tamano / 2) {
            actual = cabeza;
            for (int i = 0; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
        } else {
            actual = cola;
            for (int i = tamano - 1; i > posicion; i--) {
                actual = actual.getAnterior();
            }
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
