package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.*;
import org.iesalandalus.programacion.matriculacion.vista.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;

// Apartado 14.1.

// Apartado 14.1.xxii.
// Método Main
public class MainApp {

    public static void main(String[] args){

        Opcion opcionSeleccionada = null; // Variable para almacenar la opción elegida

        do {
            try {
                Consola.mostrarMenu();
                opcionSeleccionada = Consola.elegirOpcion();
                ejecutarOpcion(opcionSeleccionada);
            } catch (Exception e) {
                System.out.println("ERROR: Excepción capturada.\n" + e.getMessage());
            }
        } while (opcionSeleccionada != Opcion.SALIR);

    }

    // Apartado 14.1.ii.
    // Método para ejecutar una opción
    private static void ejecutarOpcion(Opcion opcion){
        try {
            switch (opcion) {
                case SALIR -> System.out.println("|| ¡Gracias por utilizar el Sistema de Matriculación! ||\n" +
                        "||-- ...Saliendo de la aplicación... --||");
                case INSERTAR_ALUMNO -> insertarAlumno();
                case BUSCAR_ALUMNO -> buscarAlumno();
                case BORRAR_ALUMNO -> borrarAlumno();
                case MOSTRAR_ALUMNOS -> mostrarAlumnos();
                case INSERTAR_CICLO_FORMATIVO -> insertarCicloFormativo();
                case BUSCAR_CICLO_FORMATIVO -> buscarCicloFormativo();
                case BORRAR_CICLO_FORMATIVO -> borrarCicloFormativo();
                case MOSTRAR_CICLOS_FORMATIVOS -> mostrarCiclosFormativos();
                case INSERTAR_ASIGNATURA -> insertarAsignatura();
                case BUSCAR_ASIGNATURA -> buscarAsignatura();
                case BORRAR_ASIGNATURA -> borrarAsignatura();
                case MOSTRAR_ASIGNATURAS -> mostrarAsignaturas();
                case INSERTAR_MATRICULA -> insertarMatricula();
                case BUSCAR_MATRICULA -> buscarMatricula();
                case ANULAR_MATRICULA -> anularMatricula();
                case MOSTRAR_MATRICULAS -> mostrarMatriculas();
                case MOSTRAR_MATRICULAS_ALUMNO -> mostrarMatriculasPorAlumno();
                case MOSTRAR_MATRICULAS_CICLO_FORMATIVO -> mostrarMatriculasPorCicloFormativo();
                case MOSTRAR_MATRICULAS_CURSO_ACADEMICO -> mostrarMatriculasPorCursoAcademico();
            }
        } catch (IllegalArgumentException | OperationNotSupportedException e){
            System.out.println("ERROR: excepción capturada. " + e.getMessage());
        }
    }





    // Apartado 14.1.v.
    // Método para eliminar un alumno


    // Apartado 14.1.vi.
    // Método para mostrar los alumnos almacenados
    private static void mostrarAlumnos(){
        if (alumnos.getTamano() > 0) {
            int contador = 1;
            for (Alumno a : alumnos.get()) {
                System.out.printf("%d.- %s" , contador,a);
                contador++;
            }
        } else {
            System.out.println("No hay alumnos registados.");
        }
    }




    // Apartado 14.1.x.
    // Método para mostrar las Asignaturas
    private static void mostrarAsignaturas(){
        if (asignaturas.getTamano() > 0) {
            int contador = 1;
            for (Asignatura a : asignaturas.get()) {
                System.out.printf("%d.- %s" , contador, a);
                contador++;
            }
        } else {
            System.out.println("No hay asignaturas registradas.");
        }
    }



    // Apartado 14.1.xiv.
    // Método para mostrar los Ciclos Formativos
    private static void mostrarCiclosFormativos(){
        if (ciclosFormativos.getTamano() > 0) {
            for (CicloFormativo cicloFormativo : ciclosFormativos.get()) {
                System.out.printf("%s", cicloFormativo);
            }
        } else {
            System.out.println("Ciclo Formativo no encontrado.");
        }
    }






    // Apartado 14.1.xvii.
    // Método para anular una Matrícula


    // Apartado 14.1.xviii.
    // Método para mostrar las Matrículas
    private static void mostrarMatriculas(){
        if (matriculas.getTamano() > 0) {
            for (Matricula matricula : matriculas.get()) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("Aún no hay matriículas registradas.");
        }
    }


    // Apartado 14.1.xvix.
    // Método para mostrar Matrículas por Alumno
    private static void mostrarMatriculasPorAlumno() {
        Alumno alumno = Consola.getAlumnoPorDni();
        Matricula[] resultado = matriculas.get(alumno);
        if (resultado.length > 0) {
            for (Matricula matricula : resultado) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("No hay matrículas registradas para este Alumno.");
        }
    }

    // Apartado 14.1.xx.
    // Método para mostrar Matrículas por Ciclo Formativo
    private static void mostrarMatriculasPorCicloFormativo(){
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        Matricula[] resultado = matriculas.get(cicloFormativo);
        if (resultado.length > 0) {
            for (Matricula matricula : resultado) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("No hay matrículas registradas para este Ciclo Formativo");
        }
    }

    // Apartado 14.1.xxi.
    // Método para mostrar Matrículas por Curso Académico
    private static void mostrarMatriculasPorCursoAcademico(){
        System.out.print("Introduce el curso académico (ej. 23-24): ");
        Curso curso = Consola.leerCurso();
        Matricula[] resultado = matriculas.get(curso.toString());
        if (resultado.length > 0) {
            for (Matricula matricula : resultado) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("No hay matrículas registrada para este Curso Académico.");
        }

    }
}
