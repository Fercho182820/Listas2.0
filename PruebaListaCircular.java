package UNAM.ICO;

public class PruebaListaCircular {

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

    // Lista de apoyo: Asta → Yuno → Noelle → Yami → Julius → (vuelve a Asta)
    private static ListaCircular crearListaEjemplo() {
        ListaCircular lista = new ListaCircular();
        lista.insertarFinal("Asta");
        lista.insertarFinal("Yuno");
        lista.insertarFinal("Noelle");
        lista.insertarFinal("Yami");
        lista.insertarFinal("Julius");
        return lista;
    }

    private static void prueba1CreacionEInsercion() {
        imprimirTitulo("PRUEBA 1: Creación e inserción (lista circular)");
        ListaCircular lista = new ListaCircular();
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
        imprimirTitulo("PRUEBA 2: Búsqueda y recuperación (lista circular)");
        ListaCircular lista = crearListaEjemplo();
        lista.mostrar();

        System.out.println("Posición de Noelle: " + lista.buscar("Noelle"));
        System.out.println("Posición de Luck (no existe, y no se cicla): " + lista.buscar("Luck"));
        System.out.println("Dato en la posición 3: " + lista.obtener(3));
        try {
            lista.obtener(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }
    }

    private static void prueba3Eliminacion() {
        imprimirTitulo("PRUEBA 3: Eliminación (lista circular)");
        ListaCircular lista = crearListaEjemplo();
        lista.mostrar();

        System.out.println("Elimino el primero: " + lista.eliminarInicio());
        lista.mostrar();
        System.out.println("Elimino el último: " + lista.eliminarFinal());
        lista.mostrar();
        System.out.println("Elimino la posición 1: " + lista.eliminarEn(1));
        lista.mostrar();
        System.out.println("Tamaño: " + lista.tamano());
        System.out.println("¿El último apunta al primero? " + lista.ultimoApuntaAlPrimero());
    }

    private static void prueba4CasosEspeciales() {
        imprimirTitulo("PRUEBA 4: Casos especiales (lista circular)");
        ListaCircular lista = new ListaCircular();

        System.out.println("Lista vacía:");
        lista.mostrar();
        System.out.println("¿Está vacía? " + lista.estaVacia() + " | Tamaño: " + lista.tamano());
        System.out.println("Buscar en lista vacía: " + lista.buscar("Asta"));

        try {
            lista.eliminarFinal();
        } catch (IllegalStateException e) {
            System.out.println("Eliminar de lista vacía: " + e.getMessage());
        }

        System.out.println("Lista con un solo nodo (se apunta a sí mismo):");
        lista.insertarInicio("Asta");
        lista.mostrar();
        System.out.println("¿El último apunta al primero? " + lista.ultimoApuntaAlPrimero());

        System.out.println("Elimino el último elemento disponible: " + lista.eliminarInicio());
        lista.mostrar();

        try {
            lista.insertarEn(4, "Yuno");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Posición fuera de límites: " + e.getMessage());
        }
        try {
            lista.eliminarEn(1);
        } catch (IllegalStateException e) {
            System.out.println("Eliminar en lista vacía: " + e.getMessage());
        }
    }

    private static void prueba5ComportamientoParticular() {
        imprimirTitulo("PRUEBA 5: Ciclo de la lista circular");
        ListaCircular lista = crearListaEjemplo();
        lista.mostrar();
        System.out.println("¿El último apunta al primero? " + lista.ultimoApuntaAlPrimero());

        lista.insertarInicio("Nacht");
        lista.insertarFinal("Charmy");
        lista.insertarEn(3, "Luck");
        lista.eliminarInicio();
        lista.eliminarFinal();
        System.out.println("Después de modificar la estructura:");
        lista.mostrar();
        System.out.println("¿El último apunta al primero? " + lista.ultimoApuntaAlPrimero());
        System.out.println("El recorrido terminó correctamente (mostrar() hizo una sola vuelta)");
    }
}
