package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author José Antonio Padilla Ramallo
 */

// Apartado 13.1.
public class Consola {

    // Apartado 13.2.
    // Constructor privado
    private Consola()
            throws IllegalArgumentException
    {
        throw new IllegalArgumentException
                ("ERROR: no se puede instanciar esta clase.");
    }

    // Apartado 13.3.
    // Método para mostrar el menú
    public static void mostrarMenu()
    {
        System.out.println("\n|| MENÚ DE OPCIONES ||\n");
        for (Opcion o : Opcion.values()){
            System.out.println(o);
        }
    }

    // Apartado 13.4.
    // Método para elegir un opción del menú
    public static Opcion elegirOpcion()
            throws IllegalArgumentException
    {
        int opcion;
        do {
            System.out.println("Elige una opción (introduce un número entre 0 y 19): ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Opcion.values().length);

        return Opcion.values()[opcion];
    }

    // Apartado 13.5.
    // Método para leer un Alumno
    public static Alumno leerAlumno()
    {
        Alumno alumno = null;
        boolean lecturaCorrecta = false;
        do {
            try {
                System.out.println("Introduce el nombre del Alumno: ");
                String nombre = Entrada.cadena();

                System.out.println("Introduce el DNI del Alumno: ");
                String dni = Entrada.cadena();

                System.out.println("Introduce el e-mail del Alumno: ");
                String correo = Entrada.cadena();

                System.out.println("Introduce el teléfono del Alumno: ");
                String telefono = Entrada.cadena();

                LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento del Alumno ("
                        + Alumno.FORMATO_FECHA + "): ");

                alumno = new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
                lecturaCorrecta = true;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                System.out.println("Lectura del Alumno errónea. \nInténtelo de nuevo.");
            }
        } while (!lecturaCorrecta);
        return alumno;
    }

    // Apartado 13.6.
    // Método para leer un Alumno por su DNI
    public static Alumno getAlumnoPorDni()
    {
        System.out.println("Introduce el DNI del Alumno: ");
        String dniAlumno = Entrada.cadena();

        return new Alumno("Ficticio", dniAlumno, "emailFicticio@gmail.com",
                "123456789", LocalDate.now().minusYears(20));
    }

    // Apartado 13.7.
    // Método para leer una fecha
    public static LocalDate leerFecha(String mensaje)
    {
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            System.out.println(mensaje);
            String fechaString = Entrada.cadena();
            try {
                fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto, inténtalo de nuevo." + e.getMessage());
            }
        }
        return fecha;
    }

    // Apartado 13.8.
    // Método para leer el Grado
    public static Grado leerGrado()
    {
        System.out.println("Selecciona un Grado: ");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.ordinal() + ".- " + grado);
        }

        int opcion;

        do {
            System.out.println("Elige una opción de las mostradas: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Grado.values().length);

        return Grado.values()[opcion];
    }

    // Apartado 13.9.
    // Método para leer el ciclo formativo
    public static CicloFormativo leerCicloFormativo()
    {
        System.out.println("Introduce el código del Ciclo Formativo: ");
        int codigo = Entrada.entero();

        System.out.println("Introduce la familia profesional del Ciclo Formativo: ");
        String familiaProfesional = Entrada.cadena();

        System.out.println("Introduce el grado del Ciclo Formativo: ");
        Grado grado = leerGrado();

        System.out.println("Introduce el nombre del Ciclo Formativo: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduce las horas anuales del Ciclo Formativo: ");
        int horas = Entrada.entero();

        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    // Apartado 13.10.
    // Método para mostrar los Ciclos Formativos registrados en el sistema
    public static void mostrarCiclosFormativos(CicloFormativo[] ciclosFormativos)
    {
        if (ciclosFormativos.length > 0) {
            int contador = 1;
            for (CicloFormativo ciclo : ciclosFormativos) {
                System.out.printf("[%d.-] %s" , contador, ciclo);
                contador++;
            }
        } else {
            System.out.println("No hay Ciclos Formativos registrados.");
        }
    }

    // Apartado 13.11.
    // Método para obtener un Ciclo Formativo por código
    public static CicloFormativo getCicloFormativoPorCodigo()
            throws IllegalArgumentException
    {
        System.out.println("Introduce el código del Ciclo Formativo: ");
        int codigo = Entrada.entero();

        return new CicloFormativo(codigo, "Familia Ficticia", Grado.GDCFGS,
                "Nombre Ficticio", 2000);
    }

    // Apartado 13.12.
    // Método para leer un Curso
    public static Curso leerCurso()
    {
        System.out.println("Lista de Cursos existentes: \n");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.ordinal() + ".- " + curso);
        }

        int opcion;
        do {
            System.out.println("Elige una opción: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Curso.values().length);

        return Curso.values()[opcion];
    }

    // Apartado 13.13.
    // Método para leer la especialidad del profesorado
    public static EspecialidadProfesorado leerEspecialidadProfesorado()
    {
        System.out.println("Elige la especialidad del profesor: ");

        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad.ordinal() + ".- " + especialidad);
        }

        int opcion = Entrada.entero();

        return EspecialidadProfesorado.values()[opcion];
    }

    // Apartado 13.14.
    // Método para leer una asignatura
    public static Asignatura leerAsignatura(CicloFormativo cicloFormativo)
    {
        System.out.println("Introduce el código de la Asignatura: ");
        String codigo = Entrada.cadena();

        System.out.println("Introduce el nombre de la Asignatura: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduce las horas anuales de la Asignatura: ");
        int horasAnuales = Entrada.entero();

        System.out.println("Introduce el Curso de la Asignatura: ");
        Curso curso = leerCurso();

        System.out.println("Introduce las horas de desdoble de la Asignatura: ");
        int horasDesdoble = Entrada.entero();

        System.out.println("Introduce la especialidad del profesorado de la Asignatura: ");
        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();

        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble,
                especialidadProfesorado, cicloFormativo);
    }

    // Apartado 13.15.
    // Método para obtener una asignatura por código
    public static Asignatura getAsignaturaPorCodigo()
    {
        CicloFormativo cicloFormativo = new CicloFormativo(1234, "Informática",
                Grado.GDCFGS, "Desarrollo de Aplicaciones Multiplataforma", 2000);

        System.out.println("Introduce el código de la asignatura: ");
        String codigo = Entrada.cadena();

        return new Asignatura(codigo, "Nombre Ficticio", 300, Curso.PRIMERO, 6,
                EspecialidadProfesorado.INFORMATICA, cicloFormativo);
    }

    // Apartado 13.16.
    // Método para mostrar las asignaturas
    private static void mostrarAsignaturas(ArrayList<Asignatura> asignaturas)
    {
        if (asignaturas.size() == 0) {
            System.out.println("Aún no hay asignaturas registradas.");
        } else {
            System.out.println("Mostrando la lista actual de asignaturas: ");
            int posicion = 1;
            for (Asignatura asignatura : asignaturas) {
                System.out.println("[" + posicion + "]" + asignatura + "\n");
                posicion++;
            }
        }
    }

    // Apartado 13.17.
    // Método para mostrar si existe la asignatura
    public static boolean asignaturaYaMatriculada(ArrayList<Asignatura> asignaturasMatricula, Asignatura asignatura)
    {
        for (Asignatura a : asignaturasMatricula){
            if (a != null && a.equals(asignatura)){
                return true;
            }
        }
        return false;
    }

    // Apartado 13.18
    // Método para leer una matrícula
    public static Matricula leerMatricula(Alumno alumno, ArrayList<Asignatura> asignaturas)
            throws OperationNotSupportedException
    {
        System.out.println("Introduce el ID de la Matrícula: ");
        int idMatricula = Entrada.entero();

        System.out.println("Introduce el Curso Académico de la Matrícula: ");
        String cursoAcademico = Entrada.cadena();

        System.out.println("Introduce la fecha de matriculación: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA);
        String fechaMatriculacionStr = Entrada.cadena().trim();
        LocalDate fechaMatriculacion = LocalDate.parse(fechaMatriculacionStr, formatter);

        if (alumno == null) {
            throw new OperationNotSupportedException
                    ("ERROR: el alumno especificado es nulo.");
        }

        // Comprobamos que no haya asignaturas repetidas
        if (asignaturas.size() > 1) {
            for (int i = 0; i < asignaturas.size() - 1; i++) {
                for (int j = i + 1; j < asignaturas.size(); j++) {
                    if (asignaturas.get(i).equals(asignaturas.get(j))) {
                        throw new IllegalArgumentException(
                                "ERROR: No puede haber dos asignaturas iguales en la misma matrícula.");
                    }
                }
            }
        }

        return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion,
                alumno, asignaturas);
    }

    public static Matricula getMatriculaPorIdentificador()
    {
        System.out.println("Introduce el ID de la Matrícula: ");
        int idMatricula = Entrada.entero();

        Alumno alumno = new Alumno("Jose", "12345678Z", "correo@gmail.com",
                "123456789", LocalDate.of(1990, 1, 1));

        int numAsignaturas = 2;
        Asignatura[] coleccionAsignaturas = new Asignatura[numAsignaturas];
        coleccionAsignaturas[0] = new Asignatura();
        coleccionAsignaturas[1] = new Asignatura();

        return new Matricula(idMatricula, "23-24", LocalDate.now().minusDays(13),
                alumno, coleccionAsignaturas);
    }

    // Método para obtener el array de asignaturas que se asignarán en una matrícula.
    public static ArrayList<Asignatura> elegirAsignaturasMatricula(ArrayList<Asignatura> asignaturas) {
    Consola.mostrarAsignaturas(asignaturas);

    System.out.println("Para añadir una asignatura, " +
            "introduce el número correspondiente de la lista entre corchetes -> [x].");
    System.out.println("Introduce 0 para salir.");

    System.out.print("¿Cuántas asignaturas deseas elegir?: ");
    int cantidad;
    do {
        cantidad = Entrada.entero();
        if (cantidad < 0 || cantidad > asignaturas.length) {
            System.out.println("ERROR: Introduce un número válido (entre 0 y " + asignaturas.length + "): ");
        }
    } while (cantidad < 0 || cantidad > asignaturas.length);

    Asignatura[] asignaturasElegidas = new Asignatura[cantidad];
    int posicion = 0;

    while (posicion < cantidad) {
        System.out.print("Introduce el número de la asignatura a añadir: ");
        int opcion = Entrada.entero();

        if (opcion == 0) {
            break; // Permite salir antes si el usuario lo desea.
        }

        if (opcion > 0 && opcion <= asignaturas.length) {
            asignaturasElegidas[posicion] = asignaturas[opcion - 1];
            posicion++;
            System.out.println("Asignatura añadida.");
        } else {
            System.out.println("ERROR: Opción no válida. Introduce un número entre 1 y " + asignaturas.length);
        }
    }

    return asignaturasElegidas;
}

}
