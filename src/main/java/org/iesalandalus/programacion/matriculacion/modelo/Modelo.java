package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;

/**
 *
 * @author José Antonio Padilla Ramallo
 * Clase que gestiona el modelo de datos de esta aplicación.
 * Será la encargada de comunicarse con las cuatro clases que hacen referencia
 * a las colecciones de datos (alumnos, asignaturas, ciclos formativos y matrículas).
 */

public class Modelo {

    public static final int CAPACIDAD = 3;

    // Declaramos las colecciones como atributos estáticos sin inicializarlas aún
    private static Alumnos coleccionAlumnos;
    private static Asignaturas coleccionAsignaturas;
    private static CiclosFormativos coleccionCiclos;
    private static Matriculas coleccionMatriculas;

    // Modelo.2.
    /*
    Método comenzar que creará la instancia de las clases de negocio.
     */
    public static void comenzar(){
        // Creamos las instancias dentro
        coleccionAlumnos = new Alumnos(CAPACIDAD);
        coleccionAsignaturas = new Asignaturas(CAPACIDAD);
        coleccionCiclos = new CiclosFormativos(CAPACIDAD);
        coleccionMatriculas = new Matriculas(CAPACIDAD);
    }

    // Modelo.3.
    /*
    Método terminar que muestra un mensaje informativo indicando que el modelo ha terminado.
     */
    public static void terminar(){

        System.out.println("\n||--- El Modelo ha finalizado ---||" +
                "\n||--- ¡Gracias por usar el Sistema de Matriculación! ---||");

    }


    // Modelo.4.
    /*
    Métodos insertar(para Alumno, Asignatura, Ciclo Formativo y Matricula).
     */

    public static void insertarAlumno(Alumno alumno){
        boolean insercionCorrecta = false;
        while (!insercionCorrecta) {
            try {
                coleccionAlumnos.insertarAlumno(alumno);
                insercionCorrecta = true;
            } catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
                System.out.println("ERROR: No ha sido posible insertar el alumno.\n");
                System.out.println(e.getMessage());
                System.out.println("Inténtelo de nuevo o escriba 'salir' para cancelar.");

                // Opción para salir si el usuario no quiere seguir intentando
                System.out.print("¿Desea volver a intentarlo? (Sí/No): ");
                String respuesta = "";
                while (respuesta == null || respuesta.isBlank()) {
                    System.out.print("¿Desea volver a intentarlo? (Sí/No): ");
                    respuesta = Entrada.cadena();
                }
                if (respuesta.equals("no") || respuesta.equals("salir")) {
                    System.out.println("Inserción cancelada.");
                    return; // Sale del método sin volver a intentar
                }
            } catch (Exception e) {
                System.out.println("ERROR: se ha capturado una excepción.\n" + e.getMessage());
                return;
            }
            System.out.println("\nAlumno insertado correctamente.");
        }
    }

    public static void insertarAsignatura(Asignatura asignatura){
        try {
            // Asignatura asignatura = Consola.leerAsignatura(coleccionCiclos);
            coleccionAsignaturas.insertar(asignatura);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    public static void insertarCicloFormativo(CicloFormativo cicloFormativo) {
        try {
            // CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
            coleccionCiclos.insertarCiclo(cicloFormativo);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    public static void insertarMatricula(Matricula matricula)

    {
        try {
            // Matricula matricula = Consola.leerMatricula(coleccionAlumnos, coleccionAsignaturas);
            coleccionMatriculas.insertar(matricula);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    // Modelo.5.
    /*
    Métodos buscar, cada uno devuelve una nueva instancia del elemento encontrado si éste existe.
     */
    public static Alumno buscarAlumno(Alumno alumno) {
        try {
            Alumno buscado = coleccionAlumnos.buscar(alumno);
            if (buscado != null) {
                System.out.println("Alumno encontrado: ");
                return buscado;
            } else {
                System.out.println("Alumno no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
        return null;
    }

    public static Asignatura buscarAsignatura(Asignatura asignatura){
        try {
            // Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignatura buscada = coleccionAsignaturas.buscar(asignatura);
            if (buscada != null) {
                System.out.println("Asignatura encontrada: ");
                return buscada;
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: Excepción capturada.\n" + e.getMessage());
        }
        return null;
    }

    public static CicloFormativo buscarCicloFormativo(CicloFormativo cicloFormativo){
        try {
            // CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            CicloFormativo buscado = coleccionCiclos.buscar(cicloFormativo);
            if (buscado != null) {
                System.out.println("Ciclo Formativo encontrado:\n");
                return buscado;
            } else {
                System.out.println("Ciclo formativo no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
        return null;
    }

    public static Matricula buscarMatricula(Matricula matricula) {
        try {
            // Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula buscada = coleccionMatriculas.buscar(matricula);
            if (buscada != null) {
                System.out.println("Matrícula encontrada:\n");
                return buscada;
            } else {
                System.out.println("Matrícula no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
        return null;
    }


    // Modelo.6.
    /*
    Métodos borrar (para Alumno, Asignatura, Ciclo Formativo y Matricula).
     */
    public static void borrarAlumno(Alumno alumno) throws OperationNotSupportedException {
        try {
            coleccionAlumnos.borrar(alumno);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }

    }

    public static void borrarAsignatura (Asignatura asignatura) {
        try {
            // Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            coleccionAsignaturas.borrar(asignatura);
        } catch (Exception e) {
            System.out.println("ERROR: se ha capturado una excepción.\n" + e.getMessage());
        }
    }

    public static void borrarCicloFormativo(CicloFormativo cicloFormativo) {
        try {
            // CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            coleccionCiclos.borrar(cicloFormativo);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    public static void borrarMatricula(Matricula matricula)
    {
        try {
            /*
            System.out.println("Mostrando la lista de matrículas: ");
            mostrarMatriculas();
            System.out.println("Por favor, introduce el identificador de la matrícula que desea anular: ");
            Matricula matriculaAAnular = Consola.getMatriculaPorIdentificador();
             */
            matricula.setFechaAnulacion (Consola.leerFecha("Introduce la fecha de anulación (dd/MM/yyyy): "));
            coleccionMatriculas.borrar(matricula);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    // Modelo.7.
    /*
    Métodos get definidos en el diagrama de clases, que devuelven una lista
    de los diferentes elementos de la aplicación (Alumnos, Asignaturas, Ciclos Formativos y Matrículas).
     */

    // Métogo GET para los Alumnos
    public static Alumno[] getAlumnos(){
        return coleccionAlumnos.get();
    }

    // Métogo GET para las Asignaturas
    public static Asignatura[] getAsignaturas(){
        return coleccionAsignaturas.get();
    }

    // Métogo GET para los CiclosFormativos
    public static CicloFormativo[] getCiclos(){
        return coleccionCiclos.get();
    }

    // Métodos GET para las matrículas
    public static Matricula[] getMatriculas (){
        return coleccionMatriculas.get();
    }

    public static Matricula[] getMatriculas (Alumno alumno){
        return coleccionMatriculas.get(alumno);
    }

    public static Matricula[] getMatriculas (CicloFormativo cicloFormativo){
        return coleccionMatriculas.get(cicloFormativo);
    }

    public static Matricula[] getMatriculas(String cursoAcademico){
        return coleccionMatriculas.get(cursoAcademico);
    }

}
