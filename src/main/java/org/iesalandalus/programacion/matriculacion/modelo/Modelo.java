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
import java.util.ArrayList;

/**
 *
 * @author José Antonio Padilla Ramallo
 * Clase que gestiona el modelo de datos de esta aplicación.
 * Será la encargada de comunicarse con las cuatro clases que hacen referencia
 * a las colecciones de datos (alumnos, asignaturas, ciclos formativos y matrículas).
 */

public class Modelo {

    // Declaramos las colecciones sin inicializarlas aún
    private ArrayList<Alumno> coleccionAlumnos;
    private ArrayList<Asignatura> coleccionAsignaturas;
    private ArrayList<CicloFormativo> coleccionCiclos;
    private ArrayList<Matricula> coleccionMatriculas;

    // Modelo.2.
    /*
    Método comenzar que creará la instancia de las clases de negocio.
     */
    public void comenzar(){
        // Creamos las instancias dentro
        coleccionAlumnos = new ArrayList<>();
        coleccionAsignaturas = new ArrayList<>();
        coleccionCiclos = new ArrayList<>();
        coleccionMatriculas = new ArrayList<>();
    }

    // Modelo.3.
    /*
    Método terminar que muestra un mensaje informativo indicando que el modelo ha terminado.
     */
    public void terminar(){

        System.out.println("""

                ||--- El Modelo ha finalizado ---||
                """);

    }

    // Modelo.4.
    /*
    Métodos insertar(para Alumno, Asignatura, Ciclo Formativo y Matricula).
     */

    public void insertarAlumno(Alumno alumno){
        boolean insercionCorrecta = false;
        while (!insercionCorrecta) {
            try {
                coleccionAlumnos.add(alumno);
                insercionCorrecta = true;
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n" + e.getStackTrace());
                System.out.println("""
                        
                        ERROR: No ha sido posible insertar el alumno.
                        Inténtelo de nuevo o escriba 'salir' para cancelar la inserción.%n
                        """);

                // Opción para salir si el usuario no quiere seguir intentando
                String respuesta = "";
                while (respuesta == null || respuesta.isBlank()) {
                    System.out.print("¿Desea volver a intentarlo? (Sí/No): ");
                    respuesta = Entrada.cadena().trim().toLowerCase();
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

    public void insertarAsignatura(Asignatura asignatura){
        try {
            coleccionAsignaturas.add(asignatura);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
        System.out.println("\nAsignatura insertada correctamente.");
    }

    public void insertarCicloFormativo(CicloFormativo cicloFormativo) {
        try {
            coleccionCiclos.add(cicloFormativo);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
        System.out.println("\nCiclo Formativo insertado correctamente.");
    }

    public void insertarMatricula(Matricula matricula)
    {
        try {
            coleccionMatriculas.add(matricula);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
        System.out.println("\nMatrícula insertada correctamente.");
    }

    // Modelo.5.
    /*
    Métodos buscar, cada uno devuelve una nueva instancia del elemento encontrado si éste existe.
     */
    public Alumno buscarAlumno(Alumno alumno) {
        try {
            if (coleccionAlumnos.contains(alumno)) {
                System.out.println("Alumno encontrado: ");
                return alumno;
            } else {
                System.out.println("Alumno no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
        return null;
    }

    public Asignatura buscarAsignatura(Asignatura asignatura){
        try {
            if (coleccionAsignaturas.contains(asignatura)) {
                System.out.println("Asignatura encontrada: ");
                System.out.println(asignatura);
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: Excepción capturada.\n" + e.getMessage());
        }
        return null;
    }

    public CicloFormativo buscarCicloFormativo(CicloFormativo cicloFormativo){
        try {
            if (coleccionCiclos.contains(cicloFormativo)) {
                System.out.println("Ciclo Formativo encontrado:\n");
                return cicloFormativo;
            } else {
                System.out.println("Ciclo formativo no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
        return null;
    }

    public Matricula buscarMatricula(Matricula matricula) {
        try {
            if (coleccionMatriculas.contains(matricula)) {
                System.out.println("Matrícula encontrada:\n");
                return matricula;
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
    public void borrarAlumno(Alumno alumno)
            throws IllegalArgumentException
    {
        try {
            if (alumno == null) {
                throw new IllegalArgumentException
                        ("ERROR: no se puede borrar un Alumno nulo.");
            }
            if (!coleccionAlumnos.contains(alumno)) {
                throw new OperationNotSupportedException
                        ("ERROR: el Alumno que intenta borrar no se encuentra registrado en el sistema.");
            } else coleccionAlumnos.remove(alumno);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }

    }

    public void borrarAsignatura (Asignatura asignatura)
            throws IllegalArgumentException
    {
        try {
            if (asignatura == null) {
                throw new IllegalArgumentException
                        ("ERROR: no se puede borrar una Asignatura nula.");
            }
            if (!coleccionAsignaturas.contains(asignatura)) {
                throw new OperationNotSupportedException
                        ("ERROR: la Asignatura que intenta borrar no se encuentra registrada en el sistema.");
            } else coleccionAsignaturas.remove(asignatura);
        } catch (Exception e) {
            System.out.println("ERROR: se ha capturado una excepción.\n" + e.getMessage());
        }
    }

    public void borrarCicloFormativo (CicloFormativo cicloFormativo)
            throws IllegalArgumentException
    {
        try {
            if (cicloFormativo == null) {
                throw new IllegalArgumentException
                        ("ERROR: no se puede borrar un CicloFormativo nulo.");
            }
            if (!coleccionCiclos.contains(cicloFormativo)) {
                throw new OperationNotSupportedException
                        ("ERROR: el CicloFormativo que intenta borrar no se encuentra registrado en el sistema.");
            } else coleccionCiclos.remove(cicloFormativo);
        } catch (Exception e) {
            System.out.println("ERROR: excepción capturada.\n" + e.getMessage());
        }
    }

    public void borrarMatricula(Matricula matricula)
            throws IllegalArgumentException
    {
        try {
            if (matricula == null) {
                throw new IllegalArgumentException
                        ("ERROR: no se puede borrar un CicloFormativo nulo.");
            }
            if (!coleccionMatriculas.contains(matricula)) {
                throw new OperationNotSupportedException
                        ("ERROR: la Matrícula que intenta borrar no se encuentra registrada en el sistema.");
            } else {
                matricula.setFechaAnulacion
                        (Consola.leerFecha("Introduce la fecha de anulación (dd/MM/yyyy): "));
                coleccionMatriculas.remove(matricula);
            }
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
    public ArrayList<Alumno> getAlumnos()
    {
        ArrayList<Alumno> copiaAlumnos = new ArrayList<>();
        for (Alumno a : coleccionAlumnos) {
            copiaAlumnos.add(new Alumno(a));
        }
        if (copiaAlumnos.size() == 0) {
            System.out.println("Aún no hay Alumnos registrados");
        }
        return copiaAlumnos;
    }

    // Métogo GET para las Asignaturas
    public ArrayList<Asignatura> getAsignaturas()
    {
        ArrayList<Asignatura> copiaAsignaturas = new ArrayList<>();
        for (Asignatura a : coleccionAsignaturas) {
            copiaAsignaturas.add(new Asignatura(a));
        }
        if (copiaAsignaturas.size() == 0) {
            System.out.println("Aún no hay Asignaturas registradas");
        }
        return copiaAsignaturas;
    }

    // Métogo GET para los CiclosFormativos
    public ArrayList <CicloFormativo> getCiclos()
    {
        ArrayList<CicloFormativo> copiaCiclos = new ArrayList<>();
        for (CicloFormativo c : coleccionCiclos) {
            copiaCiclos.add(new CicloFormativo(c));
        }
        if (copiaCiclos.size() == 0) {
            System.out.println("Aún no hay CiclosFormativos registrados");
        }
        return copiaCiclos;
    }

    // Métodos GET para las matrículas
    public ArrayList<Matricula> getMatriculas ()
    {
        ArrayList<Matricula> copiaMatriculas = new ArrayList<>();
        for (Matricula m : coleccionMatriculas) {
            copiaMatriculas.add(new Matricula(m));
        }
        if (copiaMatriculas.size() == 0) {
            System.out.println("Aún no hay Matrículas registradas");
        }
        return copiaMatriculas;
    }

    public ArrayList<Matricula> getMatriculas (Alumno alumno)
    {
        Matriculas copiaMatriculasAlumno = new Matriculas();
        return copiaMatriculasAlumno.get(alumno);
    }

    public ArrayList<Matricula> getMatriculas (CicloFormativo cicloFormativo)
    {
        Matriculas copiaMatriculasCiclo = new Matriculas();
        return copiaMatriculasCiclo.get(cicloFormativo);
    }

    public ArrayList<Matricula> getMatriculas(String cursoAcademico)
    {
        Matriculas copiaMatriculasCurso = new Matriculas();
        return copiaMatriculasCurso.get(cursoAcademico);
    }

}
