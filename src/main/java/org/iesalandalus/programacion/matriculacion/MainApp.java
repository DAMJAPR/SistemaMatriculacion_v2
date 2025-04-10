package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import java.util.Arrays;

// Método Main
public class MainApp {

    public static void main(String[] args){

        Vista miVista = new Vista();

        Modelo miModelo = new Modelo();

        try {
            Controlador miControlador = new Controlador(miModelo, miVista);

            miControlador.comenzar();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

    }

}
