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
        boolean lecturaCorrecta = false;
        CicloFormativo cicloFormativo = null;
        do {
            try {
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
                cicloFormativo = new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
                lecturaCorrecta = true;

            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                System.out.println("Lectura del Ciclo Formativo errónea. \nPor favor inténtelo de nuevo.");
            }
        } while (!lecturaCorrecta);

        return cicloFormativo;
    }

    // Apartado 13.10.
    // Método para mostrar los Ciclos Formativos registrados en el sistema
    public static void mostrarCiclosFormativos(ArrayList<CicloFormativo> ciclosFormativos)
            throws NullPointerException
    {
        if (ciclosFormativos.isEmpty()) {
            System.out.println("No hay Ciclos Formativos registrados.");
        } else if (ciclosFormativos == null) {
            throw new NullPointerException("ERROR: la colección de Ciclos Formativos es nula.");
        } else {
            System.out.println("Mostrando la lista actual de Ciclos Formativos: ");
            for (CicloFormativo cicloFormativo : ciclosFormativos) {
                System.out.println(cicloFormativo);
            }
        }
    }

    // Apartado 13.11.
    // Método para obtener un Ciclo Formativo por código
    public static CicloFormativo getCicloFormativoPorCodigo()
            throws IllegalArgumentException
    {
        System.out.println("Introduce el código del Ciclo Formativo: ");
        int codigo = Entrada.entero();

        return new CicloFormativo(codigo, "Familia Profesional Ficticia", Grado.GDCFGS,
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

        int opcion;
        do {
            System.out.println("Elige una opción: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Curso.values().length);

        return EspecialidadProfesorado.values()[opcion];
    }

    // Apartado 13.14.
    // Método para leer una asignatura
    public static Asignatura leerAsignatura(CicloFormativo cicloFormativo)
    {
        boolean entradaCorrecta = false;
        Asignatura asignatura = null;
        do {
            try {
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

                asignatura = new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble,
                        especialidadProfesorado, cicloFormativo);
                entradaCorrecta = true;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                System.out.println("Lectura de la Asignatura errónea. \nInténtelo de nuevo.");
            }
        } while (!entradaCorrecta);
        return asignatura;
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
        if (asignaturas.isEmpty()) {
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
    {
        boolean lecturaCorrecta = false;
        Matricula matricula = null;
        do {
            try {
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
                matricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion,
                        alumno, asignaturas);
                lecturaCorrecta = true;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                System.out.println("Lectura de la Matrícula errónea. \nInténtelo de nuevo.");
            }

        } while (!lecturaCorrecta);
        return matricula;
    }

    public static Matricula getMatriculaPorIdentificador()
            throws IllegalArgumentException
    {
        boolean lecturaCorrecta = false;
        Alumno alumno;
        Matricula matricula = null;
        do {
            try {
                System.out.println("Introduce el ID de la Matrícula: ");
                int idMatricula = Entrada.entero();
                alumno = new Alumno("Jose", "12345678Z", "correo@gmail.com",
                        "123456789", LocalDate.of(1990, 1, 1));

                ArrayList<Asignatura> coleccionAsignaturas = new ArrayList<>();
                coleccionAsignaturas.add(new Asignatura());
                coleccionAsignaturas.add(new Asignatura());

                matricula = new Matricula(idMatricula, "23-24", LocalDate.now().minusDays(13),
                        alumno, coleccionAsignaturas);
                lecturaCorrecta = true;
                System.out.println("\n|| --- Matrícula leída correctamente --- ||\n");
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                System.out.println("Lectura de la Matrícula errónea. \nInténtelo de nuevo.");
            }
        } while (!lecturaCorrecta);

        return matricula;
    }

    // Método para obtener el array de asignaturas que se asignarán en una matrícula.
    public static ArrayList<Asignatura> elegirAsignaturasMatricula(ArrayList<Asignatura> asignaturas) {
        Consola.mostrarAsignaturas(asignaturas);

        System.out.println("""
        \nPara añadir asignaturas:
        · Introduce el índice entre corchetes de la asignatura que deseas añadir -> [x]
        · Introduce 0 para finalizar la selección
        """);

        ArrayList<Asignatura> asignaturasElegidas = new ArrayList<>();

        while (asignaturasElegidas.size() < asignaturas.size()) {
            System.out.print("Introduce el número de la asignatura a añadir (" + (asignaturasElegidas.size() + 1) + "/" + asignaturas.size() + "): ");
            try {
                int opcion = Entrada.entero();

                if (opcion == 0) {
                    break; // Finaliza la selección voluntariamente
                }

                if (opcion < 1 || opcion > asignaturas.size()) {
                    System.out.println("ERROR: Índice fuera de rango. Debe estar entre 1 y " + asignaturas.size());
                    continue;
                }

                Asignatura seleccionada = asignaturas.get(opcion - 1);
                if (asignaturasElegidas.contains(seleccionada)) {
                    System.out.println("La asignatura ya ha sido seleccionada. Elige otra.");
                } else {
                    asignaturasElegidas.add(seleccionada);
                    System.out.println("Asignatura añadida: " + seleccionada.getNombre());
                }

            } catch (Exception e) {
                System.out.println("ERROR: Entrada no válida. Inténtalo de nuevo.");
            }
        }

        if (asignaturasElegidas.isEmpty()) {
            System.out.println("No se ha añadido ninguna asignatura.");
        } else {
            System.out.println("Asignaturas seleccionadas correctamente.");
        }

        return asignaturasElegidas;
    }

}
