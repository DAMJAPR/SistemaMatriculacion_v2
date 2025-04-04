package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
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
    public void comenzar()
    {
        Modelo.comenzar();
        vista.comenzar();
    }

    public void terminar()
    {
        Modelo.terminar();
        vista.terminar();
    }

    // Controlador.5.
    /*
    Métodos que realizan operaciones de insertar, buscar, borrar y listar.
    Hacen una llamada al correspondiente método del Modelo.
     */

    // Métodos para realizar operaciones con Alumnos:
    public void insertarAlumno(Alumno alumno)
    {
        Modelo.insertarAlumno(alumno);
    }

    public Alumno buscarAlumno (Alumno alumno)
    {
        return Modelo.buscarAlumno(alumno);
    }

    public void borrarAlumno (Alumno alumno)
            throws OperationNotSupportedException
    {
        Modelo.borrarAlumno(alumno);
    }

    public Alumno[] getAlumnos()
    {
        return Modelo.getAlumnos();
    }

    // Métodos para hacer operaciones con Asignaturas:
    public void insertarAsignatura(Asignatura asignatura)
    {
        Modelo.insertarAsignatura(asignatura);
    }

    public Asignatura buscarAsignatura (Asignatura asignatura)
    {
        return Modelo.buscarAsignatura(asignatura);
    }

    public void borrarAsignatura (Asignatura asignatura)
            throws OperationNotSupportedException
    {
        Modelo.borrarAsignatura(asignatura);
    }

    public Asignatura[] getAsignaturas()
    {
        return Modelo.getAsignaturas();
    }

    // Métodos para hacer operaciones con Ciclos Formativos:
    public void insertarCiclo(CicloFormativo cicloFormativo)
    {
        Modelo.insertarCicloFormativo(cicloFormativo);
    }

    public CicloFormativo buscarCiclo (CicloFormativo cicloFormativo)
    {
        return Modelo.buscarCicloFormativo(cicloFormativo);
    }

    public void borrarCiclo (CicloFormativo cicloFormativo)
            throws OperationNotSupportedException
    {
        Modelo.borrarCicloFormativo(cicloFormativo);
    }

    public CicloFormativo[] getCiclos()
    {
        return Modelo.getCiclos();
    }

    // Métodos para hacer operaciones con Matriculas:
    public void insertarMatricula(Matricula matricula)
    {
        Modelo.insertarMatricula(matricula);
    }

    public Matricula buscarMatricula (Matricula matricula)
    {
        return Modelo.buscarMatricula(matricula);
    }

    public void borrarMatricula (Matricula matricula)
            throws OperationNotSupportedException
    {
        Modelo.borrarMatricula(matricula);
    }

    public Matricula[] getMatriculas()
    {
        return Modelo.getMatriculas();
    }

    public Matricula[] getMatriculas (Alumno alumno)
    {
        return Modelo.getMatriculas(alumno);
    }

    public Matricula[] getMatriculas (CicloFormativo cicloFormativo)
    {
        return Modelo.getMatriculas(cicloFormativo);
    }

    public Matricula[] getMatriculas (String cursoAcademico)
    {
        return Modelo.getMatriculas(cursoAcademico);
    }

}
