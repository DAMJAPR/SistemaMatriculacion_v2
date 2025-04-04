package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;

import javax.naming.OperationNotSupportedException;

/**
 *
 * @author José Antonio Padilla Ramallo
 */
public class Vista {

    // Vista.2.
    // Atributos
    private Controlador controlador;

    // Vista.4.
    // Método que asignará el controlador pasado al atributo si éste no es nulo.
    private void setControlador(Controlador controlador)
            throws NullPointerException
    {
        if (controlador == null) {
            throw new NullPointerException("ERROR: el controlador es nulo.");
        }
        this.controlador = controlador;
    }

    // Vista.5.
    // Método comenzar() que mostrará el menú, leerá una opción de consola y la ejecutará.
    public void comenzar(){
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

    // Método terminar() que muestra un mensaje de despedida por consola.
    public void terminar(){
        System.out.println("\n|| ...CERRANDO LA APLICACIÓN... ||");
        System.out.println("\n|| !GRACIAS POR USAR EL SISTEMA DE MATRICULACIÓN¡ ||");
    }

    // Vista.3.
    // Método para ejecutar una opción
    private void ejecutarOpcion(Opcion opcion){
        try {
            switch (opcion) {
                case SALIR -> System.out.println("|| ¡Gracias por utilizar el Sistema de Matriculación! ||\n" +
                        "||-- ...Saliendo de la aplicación... --||");
                case INSERTAR_ALUMNO -> insertarAlumno();
                case BUSCAR_ALUMNO -> buscarAlumno();
                case BORRAR_ALUMNO -> borrarAlumno();
                case MOSTRAR_ALUMNOS -> mostrarAlumnos();
                case INSERTAR_ASIGNATURA -> insertarAsignatura();
                case BUSCAR_ASIGNATURA -> buscarAsignatura();
                case BORRAR_ASIGNATURA -> borrarAsignatura();
                case MOSTRAR_ASIGNATURAS -> mostrarAsignaturas();
                case INSERTAR_CICLO_FORMATIVO -> insertarCicloFormativo();
                case BUSCAR_CICLO_FORMATIVO -> buscarCicloFormativo();
                case BORRAR_CICLO_FORMATIVO -> borrarCicloFormativo();
                case MOSTRAR_CICLOS_FORMATIVOS -> mostrarCiclosFormativos();
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

    // Método para insertar un alumno
    private void insertarAlumno() throws OperationNotSupportedException {
        Alumno alumno = Consola.leerAlumno();
        if (alumno == null) {
            throw new OperationNotSupportedException("ERROR: el alumno no puede ser nulo.");
        }
        controlador.insertarAlumno(alumno);
    }

    // Método para buscar un alumno
    private void buscarAlumno() throws OperationNotSupportedException {
        Alumno alumno = Consola.getAlumnoPorDni();
        controlador.buscarAlumno(alumno);
    }

    // Método para borrar un alumno
    private void borrarAlumno() throws OperationNotSupportedException {
        Alumno alumno = Consola.getAlumnoPorDni();
        controlador.borrarAlumno(controlador.buscarAlumno(alumno));
    }

    // Método para mostrar todos los alumnos registrados
    private void mostrarAlumnos(){
        Alumno[] alumnos = controlador.getAlumnos();;
        if (alumnos.length > 0) {
            int contador = 1;
            for (Alumno a : alumnos) {
                System.out.printf("%d.- %s" , contador, a);
                contador++;
            }
        } else {
            System.out.println("No hay alumnos registados.");
        }
    }

    // Insertar Asignatura
    private void insertarAsignatura() throws OperationNotSupportedException {
        CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
        Asignatura asignatura = Consola.leerAsignatura(cicloFormativo);
        controlador.insertarAsignatura(asignatura);
    }

    // Buscar Asignatura
    private void buscarAsignatura() throws OperationNotSupportedException {
        Asignatura asignatura = Consola.getAsignaturaPorCodigo();
        controlador.buscarAsignatura(asignatura);
    }

    // Borrar Asignatura
    private void borrarAsignatura() throws OperationNotSupportedException {
        Asignatura asignatura = Consola.getAsignaturaPorCodigo();
        controlador.borrarAsignatura(controlador.buscarAsignatura(asignatura));
    }

    // Método para mostrar las Asignaturas
    private void mostrarAsignaturas(){
        Asignatura[] asignaturas = controlador.getAsignaturas();
        if (asignaturas.length > 0) {
            int contador = 1;
            for (Asignatura a : asignaturas) {
                System.out.printf("%d.- %s" , contador, a);
                contador++;
            }
        } else {
            System.out.println("No hay asignaturas registradas.");
        }
    }

    // Insertar CicloFormativo
    private void insertarCicloFormativo() throws OperationNotSupportedException {
        CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
        controlador.insertarCiclo(cicloFormativo);
    }

    // Buscar CicloFormativo
    private void buscarCicloFormativo() throws OperationNotSupportedException {
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        controlador.buscarCiclo(cicloFormativo);
    }

    // Borrar CicloFormativo
    private void borrarCicloFormativo() throws OperationNotSupportedException {
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        controlador.borrarCiclo(controlador.buscarCiclo(cicloFormativo));
    }

    // Método para mostrar los Ciclos Formativos
    private void mostrarCiclosFormativos() {
        CicloFormativo[] ciclosFormativos = controlador.getCiclos();
        if (ciclosFormativos.length > 0) {
            for (CicloFormativo cicloFormativo : ciclosFormativos) {
                System.out.printf("%s", cicloFormativo);
            }
        } else {
            System.out.println("Ciclo Formativo no encontrado.");
        }
    }

    /// Método que permite insertar una matrícula en el sistema
    private void insertarMatricula() throws OperationNotSupportedException {
        // Se solicita al usuario los datos del alumno desde la consola
        Alumno alumno = Consola.leerAlumno();

        // Se obtiene el listado actual de asignaturas desde el controlador
        Asignatura[] asignaturasDisponibles = controlador.getAsignaturas();

        // Se permite al usuario elegir cuáles asignaturas desea añadir a la matrícula
        Asignatura[] asignaturasSeleccionadas = Consola.elegirAsignaturasMatricula(asignaturasDisponibles);
        if (asignaturasSeleccionadas.length == 0) {
            System.out.println("No se ha seleccionado ninguna asignatura. Matrícula cancelada.");
            return;
        }

        // Se crea una matrícula con el alumno y las asignaturas seleccionadas
        Matricula matricula = Consola.leerMatricula(alumno, asignaturasSeleccionadas);

        // Se inserta la matrícula en el sistema a través del controlador
        controlador.insertarMatricula(matricula);
    }


    // Buscar CicloFormativo
    private void buscarMatricula() throws OperationNotSupportedException {
        Matricula matricula = Consola.getMatriculaPorIdentificador();
        controlador.buscarMatricula(matricula);
    }

    // Borrar CicloFormativo
    private void anularMatricula() throws OperationNotSupportedException {
        Matricula matricula = Consola.getMatriculaPorIdentificador();
        controlador.borrarMatricula(controlador.buscarMatricula(matricula));
    }


    // Método para mostrar las Matrículas
    private void mostrarMatriculas() {
        Matricula[] matriculas = controlador.getMatriculas();
        if (matriculas.length > 0) {
            for (Matricula matricula : matriculas) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("Aún no hay matrículas registradas.");
        }
    }

    // Método para mostrar Matrículas por Alumno
    private void mostrarMatriculasPorAlumno() {
        Alumno alumno = Consola.getAlumnoPorDni();
        Matricula[] resultado = controlador.getMatriculas(alumno);
        if (resultado.length > 0) {
            for (Matricula matricula : resultado) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("No hay matrículas registradas para este Alumno.");
        }
    }

    // Método para mostrar Matrículas por Ciclo Formativo
    private void mostrarMatriculasPorCicloFormativo(){
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        Matricula[] resultado = controlador.getMatriculas(cicloFormativo);
        if (resultado.length > 0) {
            for (Matricula matricula : resultado) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("No hay matrículas registradas para este Ciclo Formativo");
        }
    }

    // Método para mostrar Matrículas por Curso Académico
    private void mostrarMatriculasPorCursoAcademico(){
        System.out.print("Introduce el curso académico (P.ej.: 23-24): ");
        Curso curso = Consola.leerCurso();
        Matricula[] resultado = controlador.getMatriculas(curso.toString());
        if (resultado.length > 0) {
            for (Matricula matricula : resultado) {
                System.out.printf("%s", matricula);
            }
        } else {
            System.out.println("No hay matrículas registrada para este Curso Académico.");
        }

    }


}
