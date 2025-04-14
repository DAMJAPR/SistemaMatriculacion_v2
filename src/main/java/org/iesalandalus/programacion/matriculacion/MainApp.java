package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.util.Arrays;

// Método Main
public class MainApp {

    public static void main(String[] args){

        Vista miVista = new Vista();

        Modelo miModelo = new Modelo();

        boolean ejecucionCorrecta = true;
        do {
            try {
                Controlador miControlador = new Controlador(miModelo, miVista);

                miControlador.comenzar();
            } catch (Exception e) {
                ejecucionCorrecta = false;
                String respuesta;
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                System.out.println("""
                        ERROR: ha ocurrido un error fatal.
                        ¿Desea reiniciar el programa o cerrarlo? ->
                        [Sí/No]:\s""");
                respuesta = Entrada.cadena().trim().toLowerCase();
                if (!respuesta.equals("si")) {
                    Controlador miControlador = new Controlador(miModelo, miVista);
                    miControlador.terminar();
                } else {
                    System.out.println("*** || --- ...REINICIANDO EL SISTEMA... --- || ***");
                }
            }
        } while (!ejecucionCorrecta);


    }

}
