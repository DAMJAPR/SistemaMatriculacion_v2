package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


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

    /*
     No sé si será un fallo del enunciado o un error de interpretación mío del mismo o del diagrama de clase, pero:
     · A la hora de usar el método setControlador() me he visto "forzado" a crear un método auxiliar
     que me permita acceder al mismo desde una instancia situada en la clase Controlador ya que este
     método tiene el modificador de acceso private, no puedo acceder a él de otra forma sin cambiar su modificador
     */
    public void iniciarControlador(Controlador controlador) {
        setControlador(controlador);
    }

    // Vista.5.
    // Método comenzar() que mostrará el menú, leerá una opción de consola y la ejecutará.
    public void comenzar()
    {
        Opcion opcionSeleccionada = null; // Variable para almacenar la opción elegida
        boolean entradaCorrecta = false;
        do {
            try {
                Consola.mostrarMenu();
                opcionSeleccionada = Consola.elegirOpcion();
                ejecutarOpcion(opcionSeleccionada);
                entradaCorrecta = true;
            } catch (Exception e) {
                System.out.println("ERROR: Excepción capturada.\n" + e.getMessage());
            }
        } while (opcionSeleccionada != Opcion.SALIR || !entradaCorrecta);
    }

    // Método terminar() que muestra un mensaje de despedida por consola.
    public void terminar() {
        System.out.println("\n|| ...CERRANDO LA APLICACIÓN... ||");
        System.out.println("\n|| !GRACIAS POR USAR EL SISTEMA DE MATRICULACIÓN¡ ||");
    }

    // Vista.3.
    // Método para ejecutar una opción
    private void ejecutarOpcion(Opcion opcion)
    {
        try {
            switch (opcion) {
                case SALIR -> controlador.terminar();
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
    private void insertarAlumno() throws OperationNotSupportedException
    {
        Alumno alumno = Consola.leerAlumno();
        if (alumno == null) {
            throw new OperationNotSupportedException("ERROR: el alumno no puede ser nulo.");
        }
        controlador.insertarAlumno(alumno);
    }

    // Método para buscar un alumno
    private void buscarAlumno() throws OperationNotSupportedException
    {
        Alumno alumno = Consola.getAlumnoPorDni();
        System.out.println(controlador.buscarAlumno(alumno));
    }

    // Método para borrar un alumno
    private void borrarAlumno() throws OperationNotSupportedException
    {
        Alumno alumno = Consola.getAlumnoPorDni();
        controlador.borrarAlumno(controlador.buscarAlumno(alumno));
    }

    // Método para mostrar todos los alumnos registrados
    public void mostrarAlumnos()
    {
        List<Alumno> alumnos = controlador.getAlumnos();

        if (alumnos.isEmpty()) {
            System.out.println("Aún no hay alumnos registrados para mostrar.");
        } else {
            List<Alumno> alumnosOrdenadosPorNombre = alumnos.stream()
                    .sorted(Comparator.comparing(Alumno::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());

            for (Alumno alumno : alumnosOrdenadosPorNombre) {
                System.out.println(alumno);
            }
        }
    }


    // Insertar Asignatura
    private void insertarAsignatura()
            throws OperationNotSupportedException
    {
        CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
        controlador.insertarCiclo(cicloFormativo);
        Asignatura asignatura = Consola.leerAsignatura(cicloFormativo);
        controlador.insertarAsignatura(asignatura);
    }

    // Buscar Asignatura
    private void buscarAsignatura()
            throws OperationNotSupportedException
    {
        Asignatura asignatura = Consola.getAsignaturaPorCodigo();
        System.out.println(controlador.buscarAsignatura(asignatura));
    }

    // Borrar Asignatura
    private void borrarAsignatura()
            throws OperationNotSupportedException
    {
        Asignatura asignatura = Consola.getAsignaturaPorCodigo();
        controlador.borrarAsignatura(controlador.buscarAsignatura(asignatura));
    }

    // Método para mostrar las Asignaturas
    private void mostrarAsignaturas()
    {
        ArrayList<Asignatura> asignaturas = controlador.getAsignaturas();

        if (asignaturas.isEmpty()) {
            System.out.println("Aún no hay alumnos registrados para mostrar.");
        } else {
            List<Asignatura> asignaturasOrdenadasPorNombre = asignaturas.stream()
                    .sorted(Comparator.comparing(Asignatura::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());

            for (Asignatura asignatura : asignaturasOrdenadasPorNombre) {
                System.out.println(asignatura);
            }
        }
    }

    // Insertar CicloFormativo
    private void insertarCicloFormativo()
            throws OperationNotSupportedException
    {
        CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
        controlador.insertarCiclo(cicloFormativo);
    }

    // Buscar CicloFormativo
    private void buscarCicloFormativo()
            throws OperationNotSupportedException
    {
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        System.out.println(controlador.buscarCiclo(cicloFormativo));
    }

    // Borrar CicloFormativo
    private void borrarCicloFormativo()
            throws OperationNotSupportedException
    {
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        controlador.borrarCiclo(controlador.buscarCiclo(cicloFormativo));
    }

    // Método para mostrar los Ciclos Formativos
    private void mostrarCiclosFormativos()
    {
        ArrayList<CicloFormativo> ciclosFormativos = controlador.getCiclos();
        if (ciclosFormativos.isEmpty()) {
            System.out.println("Aún no hay Ciclos Formativos registrados para mostrar.");
        } else {
            List<CicloFormativo> ciclosOrdenadosPorNombre = ciclosFormativos.stream()
                    .sorted(Comparator.comparing(CicloFormativo::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());

            for (CicloFormativo cicloFormativo : ciclosOrdenadosPorNombre) {
                System.out.println(cicloFormativo);
            }
        }
    }

    /// Método que permite insertar una matrícula en el sistema
    private void insertarMatricula()
            throws OperationNotSupportedException
    {
        // Se solicita al usuario el DNI del alumno desde la consola
        Alumno alumno = Consola.getAlumnoPorDni();

        /*
         Si se encuentra el Alumno, éste está registrado en el sistema y podemos seguir con la creación de la
         matrícula.
         */

        if(controlador.buscarAlumno(alumno) != null){

            // Se obtiene el listado actual de asignaturas desde el controlador.
            ArrayList<Asignatura> asignaturasDisponibles = controlador.getAsignaturas();
            if (asignaturasDisponibles.size() == 0) {
                System.out.println("Aún no hay asignaturas registradas.");
                System.out.println("Antes de insertar una matrícula, debe registrar alguna asignatura.");
                return;
            }

            // Se permite al usuario elegir cuáles asignaturas desea añadir a la matrícula
            ArrayList<Asignatura> asignaturasSeleccionadas = Consola.elegirAsignaturasMatricula(asignaturasDisponibles);
            if (asignaturasSeleccionadas.size() == 0) {
                System.out.println("No se ha seleccionado ninguna asignatura. Matrícula cancelada.");
                return;
            }

            // Se crea una matrícula con el alumno y las asignaturas seleccionadas.
            Matricula matricula = Consola.leerMatricula(alumno, asignaturasSeleccionadas);

            // Se inserta la matrícula en el sistema a través del controlador.
            controlador.insertarMatricula(matricula);

            /*
            Si no se encuentra el Alumno, se da la posibilidad de insertar un nuevo Alumno para continuar con
            el proceso de insertar una matrícula.
             */
        } else if (controlador.buscarAlumno(alumno) == null) {

            System.out.println("El alumno introducido no se encuentra registrado en el sistema.");
            System.out.println("¿Desea insertarlo como nuevo alumno? (Si/No)");
            String respuesta = Entrada.cadena();
            respuesta = respuesta.trim().toLowerCase();

            if (!respuesta.isBlank() && respuesta.equals("si")){
                // Se lee y se inserta el nuevo Alumno
                Alumno alumnoNuevo = Consola.leerAlumno();
                controlador.insertarAlumno(alumnoNuevo);
                // Se obtiene el listado actual de asignaturas desde el controlador
                ArrayList<Asignatura> asignaturasDisponibles = controlador.getAsignaturas();

                // Se permite al usuario elegir cuáles asignaturas desea añadir a la matrícula
                ArrayList<Asignatura> asignaturasSeleccionadas = Consola.elegirAsignaturasMatricula(asignaturasDisponibles);
                if (asignaturasSeleccionadas.size() == 0) {
                    System.out.println("No se ha seleccionado ninguna asignatura. Matrícula cancelada.");
                    return;
                }

                // Se crea una matrícula con el alumno y las asignaturas seleccionadas
                Matricula matricula = Consola.leerMatricula(alumnoNuevo, asignaturasSeleccionadas);

                // Se inserta la matrícula en el sistema a través del controlador
                controlador.insertarMatricula(matricula);
            }
        }
    }

    // Buscar CicloFormativo
    private void buscarMatricula()
            throws OperationNotSupportedException
    {
        Matricula matricula = Consola.getMatriculaPorIdentificador();
        System.out.println(controlador.buscarMatricula(matricula));
    }

    // Borrar CicloFormativo
    private void anularMatricula()
            throws OperationNotSupportedException
    {
        Matricula matricula = Consola.getMatriculaPorIdentificador();
        controlador.borrarMatricula(controlador.buscarMatricula(matricula));
    }


    // Método para mostrar las Matrículas
    private void mostrarMatriculas()
    {
        ArrayList<Matricula> matriculas = controlador.getMatriculas();

        if (matriculas.isEmpty()) {
            System.out.println("Aún no hay Matrículas registradas para mostrar.");
        } else {
            List<Matricula> matriculasOrdenadasPorFecha = matriculas.stream()
                    .sorted(Comparator.comparing(Matricula::getFechaMatriculacion)
                    .reversed(). // Orden descendente por fecha
                    thenComparing(m -> m.getAlumno().getNombre())) // Orden alfabético por nombre
                    .collect(Collectors.toList());

            for (Matricula matricula : matriculasOrdenadasPorFecha) {
                System.out.println(matricula);
            }
        }
    }

    // Método para mostrar Matrículas por Alumno
    private void mostrarMatriculasPorAlumno()
    {
        Alumno alumno = Consola.getAlumnoPorDni();
        ArrayList<Matricula> resultado = controlador.getMatriculas(alumno);

        if (resultado.isEmpty()) {
            System.out.println("Aún no hay Matrículas registradas para este Alumno.");
        }
        else {
            List<Matricula> matriculasOrdenadasPorFecha = resultado.stream()
                    .sorted(Comparator.comparing(Matricula::getFechaMatriculacion)
                            .reversed() // Orden descendente por fecha
                            .thenComparing(m -> m.getAlumno().getNombre())) // Orden alfabético por nombre
                    .collect(Collectors.toList());

            for (Matricula matricula : matriculasOrdenadasPorFecha) {
                System.out.println(matricula);
            }
        }
    }

    // Método para mostrar Matrículas por Ciclo Formativo
    private void mostrarMatriculasPorCicloFormativo(){
        CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
        ArrayList<Matricula> resultado = controlador.getMatriculas(cicloFormativo);
        if (resultado.isEmpty()) {
            System.out.println("Aún no hay Matrículas registradas para este Ciclo Formativo.");
        } else {
            List<Matricula> matriculasOrdenadasPorFecha = resultado.stream()
                    .sorted(Comparator.comparing(Matricula::getFechaMatriculacion)
                            .reversed() // Orden descendente por fecha
                            .thenComparing(m -> m.getAlumno().getNombre())) // Orden alfabético por nombre
                    .collect(Collectors.toList());

            for (Matricula matricula : matriculasOrdenadasPorFecha) {
                System.out.println(matricula);
            }
        }
    }

    // Método para mostrar Matrículas por Curso Académico
    private void mostrarMatriculasPorCursoAcademico(){
        System.out.print("Introduce el curso académico (P.ej.: 23-24): ");
        String cursoAcademico = Entrada.cadena().trim();
        ArrayList<Matricula> resultado = controlador.getMatriculas(cursoAcademico);
        if (resultado.isEmpty()) {
            System.out.println("Aún no hay Matrículas registradas para este Curso Académico.");
        } else {
            List<Matricula> matriculasOrdenadasPorFecha = resultado.stream()
                    .sorted(Comparator.comparing(Matricula::getFechaMatriculacion)
                            .reversed() // Orden descendente por fecha
                            .thenComparing(m -> m.getAlumno().getNombre())) // Orden alfabético por nombre
                    .collect(Collectors.toList());

            for (Matricula matricula : matriculasOrdenadasPorFecha) {
                System.out.println(matricula);
            }
        }

    }

}
