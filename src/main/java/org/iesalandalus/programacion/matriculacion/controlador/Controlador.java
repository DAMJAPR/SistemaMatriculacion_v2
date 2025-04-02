package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;

/**
 *
 * @author José Antonio Padilla Ramallo
 * Esta clase es la encargada de hacer de intermediario entre la vista y el modelo.
 */
public class Controlador {

    // Controlador.2.
    // Atributos
    private Modelo modelo;
    private Vista vista;

    // Controlador.3.
    // Constructor con parámetros
    public Controlador (Modelo modelo, Vista vista)
            throws NullPointerException
    {
        if (modelo == null || vista == null) {
            throw new NullPointerException
                    ("ERROR: el modelo o la vista no pueden ser nulos.\n");
        }
        this.modelo = modelo;
        this.vista = vista;
        // Recuerda usar el método setControlador de la clase Vista cuando lo hayas codificado
    }

    // Controlador.4.
    /*
     Métodos comenzar() y terminar()
     Que llaman a los correspondientes métodos en las clases Modelo y Vista
     */
    public static void comenzar()
    {
        Modelo.comenzar();
        Vista.comenzar();
    }

    public static void terminar()
    {
        Modelo.terminar();
        Vista.terminar();
    }

    // Controlador.5.
    /*
    Métodos que realizan operaciones de insertar, buscar, borrar y listar.
    Hacen una llamada al correspondiente método del Modelo.
     */

    // Métodos para realizar operaciones con Alumnos:
    public static void insertarAlumno(Alumno alumno)
    {
        Modelo.insertarAlumno(alumno);
    }

    public static Alumno buscarAlumno (Alumno alumno)
    {
        return Modelo.buscarAlumno(alumno);
    }

    public static void borrarAlumno (Alumno alumno)
            throws OperationNotSupportedException
    {
        Modelo.borrarAlumno(alumno);
    }

    public static Alumno[] getAlumnos()
    {
        return Modelo.getAlumnos();
    }

    // Métodos para hacer operaciones con Asignaturas:
    public static void insertarAsignatura(Asignatura asignatura)
    {
        Modelo.insertarAsignatura(asignatura);
    }

    public static Asignatura buscarAsignatura (Asignatura asignatura)
    {
        return Modelo.buscarAsignatura(asignatura);
    }

    public static void borrarAsignatura (Asignatura asignatura)
            throws OperationNotSupportedException
    {
        Modelo.borrarAsignatura(asignatura);
    }

    public static Asignatura[] getAsignaturas()
    {
        return Modelo.getAsignaturas();
    }

    // Métodos para hacer operaciones con Ciclos Formativos:
    public static void insertarCiclo(CicloFormativo cicloFormativo)
    {
        Modelo.insertarCicloFormativo(cicloFormativo);
    }

    public static CicloFormativo buscarCiclo (CicloFormativo cicloFormativo)
    {
        return Modelo.buscarCicloFormativo(cicloFormativo);
    }

    public static void borrarCiclo (CicloFormativo cicloFormativo)
            throws OperationNotSupportedException
    {
        Modelo.borrarCicloFormativo(cicloFormativo);
    }

    public static CicloFormativo[] getCiclos()
    {
        return Modelo.getCiclos();
    }

    // Métodos para hacer operaciones con Matriculas:
    public static void insertarMatricula(Matricula matricula)
    {
        Modelo.insertarMatricula(matricula);
    }

    public static Matricula buscarMatricula (Matricula matricula)
    {
        return Modelo.buscarMatricula(matricula);
    }

    public static void borrarMatricula (Matricula matricula)
            throws OperationNotSupportedException
    {
        Modelo.borrarMatricula(matricula);
    }

    public static Matricula[] getMatriculas()
    {
        return Modelo.getMatriculas();
    }

    public static Matricula[] getMatriculas (Alumno alumno)
    {
        return Modelo.getMatriculas(alumno);
    }

    public static Matricula[] getMatriculas (CicloFormativo cicloFormativo)
    {
        return Modelo.getMatriculas(cicloFormativo);
    }

    public static Matricula[] getMatriculas (String cursoAcademico)
    {
        return Modelo.getMatriculas(cursoAcademico);
    }

}
