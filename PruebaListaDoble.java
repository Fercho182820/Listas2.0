package UNAM.ICO;

public class PruebaListaDoble {

    public static void main(String[] args) {
        prueba1CreacionEInsercion();
        prueba2BusquedaYRecuperacion();
        prueba3Eliminacion();
        prueba4CasosEspeciales();
        prueba5ComportamientoParticular();
    }

    private static void imprimirTitulo(String titulo) {
        System.out.println();
        System.out.println("=== " + titulo + " ===");
    }

    // Lista de apoyo: Asta ⇄ Yuno ⇄ Noelle ⇄ Yami ⇄ Julius
    private static ListaDoble crearListaEjemplo() {
        ListaDoble lista = new ListaDoble();
        lista.insertarFinal("Asta");
        lista.insertarFinal("Yuno");
        lista.insertarFinal("Noelle");
        lista.insertarFinal("Yami");
        lista.insertarFinal("Julius");
        return lista;
    }

    private static void prueba1CreacionEInsercion() {
        imprimirTitulo("PRUEBA 1: Creación e inserción (lista doble)");
        ListaDoble lista = new ListaDoble();
        System.out.println("¿Está vacía? " + lista.estaVacia());

        lista.insertarFinal("Asta");
        lista.insertarFinal("Yuno");
        lista.insertarInicio("Yami");
        lista.insertarEn(1, "Noelle");   // posición intermedia
        lista.insertarFinal("Julius");
        lista.insertarEn(2, 5);          // un Integer: hojas del trébol de Asta
        lista.mostrar();
        lista.mostrarInverso();
        System.out.println("Tamaño: " + lista.tamano());
    }

    private static void prueba2BusquedaYRecuperacion() {
        imprimirTitulo("PRUEBA 2: Búsqueda y recuperación (lista doble)");
        ListaDoble lista = crearListaEjemplo();
        lista.mostrar();

        System.out.println("Posición de Noelle: " + lista.buscar("Noelle"));
        System.out.println("Posición de Luck (no existe): " + lista.buscar("Luck"));
        System.out.println("Dato en la posición 3: " + lista.obtener(3));
        try {
            lista.obtener(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }
    }

    private static void prueba3Eliminacion() {
        imprimirTitulo("PRUEBA 3: Eliminación (lista doble)");
        ListaDoble lista = crearListaEjemplo();
        lista.mostrar();

        System.out.println("Elimino el primero: " + lista.eliminarInicio());
        lista.mostrar();
        lista.mostrarInverso();
        System.out.println("Elimino el último: " + lista.eliminarFinal());
        lista.mostrar();
        lista.mostrarInverso();
        System.out.println("Elimino la posición 1: " + lista.eliminarEn(1));
        lista.mostrar();
        lista.mostrarInverso();
        System.out.println("¿Enlaces consistentes? " + lista.enlacesSonConsistentes());
    }

    private static void prueba4CasosEspeciales() {
        imprimirTitulo("PRUEBA 4: Casos especiales (lista doble)");
        ListaDoble lista = new ListaDoble();

        System.out.println("Lista vacía:");
        lista.mostrar();
        lista.mostrarInverso();
        System.out.println("¿Está vacía? " + lista.estaVacia() + " | Tamaño: " + lista.tamano());

        try {
            lista.eliminarFinal();
        } catch (IllegalStateException e) {
            System.out.println("Eliminar de lista vacía: " + e.getMessage());
        }

        System.out.println("Lista con un solo nodo:");
        lista.insertarInicio("Asta");
        lista.mostrar();
        lista.mostrarInverso();

        System.out.println("Elimino el último elemento disponible: " + lista.eliminarInicio());
        lista.mostrar();
        System.out.println("¿Enlaces consistentes? " + lista.enlacesSonConsistentes());

        try {
            lista.insertarEn(-1, "Yuno");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Posición fuera de límites: " + e.getMessage());
        }
        try {
            lista.eliminarEn(2);
        } catch (IllegalStateException e) {
            System.out.println("Eliminar en lista vacía: " + e.getMessage());
        }
    }

    private static void prueba5ComportamientoParticular() {
        imprimirTitulo("PRUEBA 5: Recorridos de la lista doble");
        ListaDoble lista = crearListaEjemplo();
        lista.mostrar();
        lista.mostrarInverso();
        System.out.println("¿Enlaces consistentes? " + lista.enlacesSonConsistentes());

        lista.insertarInicio("Nacht");
        lista.insertarEn(3, "Charmy");
        lista.eliminarEn(1);
        lista.eliminarFinal();
        System.out.println("Después de varias inserciones y eliminaciones:");
        lista.mostrar();
        lista.mostrarInverso();
        System.out.println("¿Enlaces consistentes? " + lista.enlacesSonConsistentes());
    }
}
