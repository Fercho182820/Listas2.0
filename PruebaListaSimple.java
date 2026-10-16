package UNAM.ICO;

public class PruebaListaSimple {

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

    // Lista de apoyo: Asta → Yuno → Noelle → Yami → Julius
    private static ListaSimple crearListaEjemplo() {
        ListaSimple lista = new ListaSimple();
        lista.insertarFinal("Asta");
        lista.insertarFinal("Yuno");
        lista.insertarFinal("Noelle");
        lista.insertarFinal("Yami");
        lista.insertarFinal("Julius");
        return lista;
    }

    private static void prueba1CreacionEInsercion() {
        imprimirTitulo("PRUEBA 1: Creación e inserción (lista simple)");
        ListaSimple lista = new ListaSimple();
        System.out.println("¿Está vacía? " + lista.estaVacia());

        lista.insertarFinal("Asta");
        lista.insertarFinal("Yuno");
        lista.insertarInicio("Yami");
        lista.insertarEn(1, "Noelle");   // posición intermedia
        lista.insertarFinal("Julius");
        lista.insertarEn(2, 5);          // un Integer: hojas del trébol de Asta
        lista.mostrar();
        System.out.println("Tamaño: " + lista.tamano());
    }

    private static void prueba2BusquedaYRecuperacion() {
        imprimirTitulo("PRUEBA 2: Búsqueda y recuperación (lista simple)");
        ListaSimple lista = crearListaEjemplo();
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
        imprimirTitulo("PRUEBA 3: Eliminación (lista simple)");
        ListaSimple lista = crearListaEjemplo();
        lista.mostrar();

        System.out.println("Elimino el primero: " + lista.eliminarInicio());
        lista.mostrar();
        System.out.println("Elimino el último: " + lista.eliminarFinal());
        lista.mostrar();
        System.out.println("Elimino la posición 1: " + lista.eliminarEn(1));
        lista.mostrar();
        System.out.println("Tamaño: " + lista.tamano());
        System.out.println("¿El último apunta a null? " + lista.ultimoApuntaANulo());
    }

    private static void prueba4CasosEspeciales() {
        imprimirTitulo("PRUEBA 4: Casos especiales (lista simple)");
        ListaSimple lista = new ListaSimple();

        System.out.println("Lista vacía:");
        lista.mostrar();
        System.out.println("¿Está vacía? " + lista.estaVacia() + " | Tamaño: " + lista.tamano());
        System.out.println("Buscar en lista vacía: " + lista.buscar("Asta"));

        try {
            lista.eliminarInicio();
        } catch (IllegalStateException e) {
            System.out.println("Eliminar de lista vacía: " + e.getMessage());
        }

        System.out.println("Lista con un solo nodo:");
        lista.insertarInicio("Asta");
        lista.mostrar();
        System.out.println("Tamaño: " + lista.tamano());

        System.out.println("Elimino el último elemento disponible: " + lista.eliminarFinal());
        lista.mostrar();
        System.out.println("¿Está vacía? " + lista.estaVacia());

        try {
            lista.insertarEn(3, "Yuno");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Posición fuera de límites: " + e.getMessage());
        }
        try {
            lista.eliminarEn(0);
        } catch (IllegalStateException e) {
            System.out.println("Eliminar en lista vacía: " + e.getMessage());
        }
    }

    private static void prueba5ComportamientoParticular() {
        imprimirTitulo("PRUEBA 5: Referencias de la lista simple");
        ListaSimple lista = crearListaEjemplo();
        lista.mostrar();
        System.out.println("¿El último apunta a null? " + lista.ultimoApuntaANulo());

        lista.insertarFinal("Nacht");
        lista.eliminarInicio();
        lista.insertarEn(2, "Charmy");
        lista.mostrar();
        System.out.println("Después de modificar, ¿el último apunta a null? " + lista.ultimoApuntaANulo());
    }
}
