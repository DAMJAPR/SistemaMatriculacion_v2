package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import java.util.ArrayList;

/**
 *
 * @author José Antonio Padilla Ramallo
 * Esta clase es la encargada de hacer de intermediario entre la vista y el modelo.
 */
public class Controlador {

    // Atributos
    private final Modelo modelo;
    private final Vista vista;

    // Controlador.3.
    // Constructor con parámetros
    public Controlador (Modelo modelo, Vista vista)
            throws IllegalArgumentException
    {
        if (modelo == null || vista == null) {
            throw new IllegalArgumentException
                    ("ERROR: el modelo o la vista no pueden ser nulos.\n");
        }

        // Controlador.2.
        // Atributos
        this.modelo = modelo;
        this.vista = vista;
        vista.iniciarControlador(this);
    }

    // Controlador.4.
    /*
     Métodos comenzar() y terminar()
     Que llaman a los correspondientes métodos en las clases Modelo y Vista
     */
    public void comenzar()
    {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar()
    {
        modelo.terminar();
        vista.terminar();
    }

    // Controlador.5.
    /*
    Métodos que realizan operaciones de insertar, buscar, borrar y listar.
    Hacen una llamada al correspondiente método del Modelo.
     */

    // Métodos para realizar operaciones con Alumnos:
    public void insertarAlumno(Alumno alumno)
            throws IllegalArgumentException
    {
        if (alumno == null) {
            throw new IllegalArgumentException
                    ("ERROR: el Alumno que intenta insertar es nulo.");
        }
        modelo.insertarAlumno(alumno);
    }

    public Alumno buscarAlumno (Alumno alumno)
            throws IllegalArgumentException
    {
        if (alumno == null) {
            throw new IllegalArgumentException
                    ("ERROR: el Alumno que intenta insertar es nulo.");
        }
        return modelo.buscarAlumno(alumno);
    }

    public void borrarAlumno (Alumno alumno)
            throws IllegalArgumentException
    {
        if (alumno == null) {
            throw new IllegalArgumentException
                    ("ERROR: el Alumno que intenta insertar es nulo.");
        }
        modelo.borrarAlumno(alumno);
    }

    public ArrayList<Alumno> getAlumnos()
    {
        return modelo.getAlumnos();
    }

    // Métodos para hacer operaciones con Asignaturas:
    public void insertarAsignatura(Asignatura asignatura)
            throws IllegalArgumentException
    {
        if (asignatura == null) {
            throw new IllegalArgumentException
                    ("ERROR: la Asignatura que intenta insertar es nula.");
        }
        ArrayList<Asignatura> asignaturas = getAsignaturas();
        if (!Consola.asignaturaYaMatriculada(asignaturas, asignatura)) {
            modelo.insertarAsignatura(asignatura);
        } else {
            System.out.println("La asignatura que intenta insertar ya existe.");
        }
    }

    public Asignatura buscarAsignatura (Asignatura asignatura)
            throws IllegalArgumentException
    {
        if (asignatura == null) {
            throw new IllegalArgumentException
                    ("ERROR: la Asignatura que intenta buscar es nula.");
        }
        return modelo.buscarAsignatura(asignatura);
    }

    public void borrarAsignatura (Asignatura asignatura)
            throws IllegalArgumentException
    {
        if (asignatura == null) {
            throw new IllegalArgumentException
                    ("ERROR: la Asignatura que intenta borrar es nula.");
        }
        modelo.borrarAsignatura(asignatura);
    }

    public ArrayList<Asignatura> getAsignaturas()
    {
        return modelo.getAsignaturas();
    }

    // Métodos para hacer operaciones con Ciclos Formativos:
    public void insertarCiclo(CicloFormativo cicloFormativo)
            throws IllegalArgumentException
    {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException
                    ("ERROR: el Ciclo Formativo que intenta insertar es nulo.");
        }
        modelo.insertarCicloFormativo(cicloFormativo);
    }

    public CicloFormativo buscarCiclo (CicloFormativo cicloFormativo)
            throws IllegalArgumentException
    {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException
                    ("ERROR: el Ciclo Formativo que intenta buscar es nulo.");
        }
        return modelo.buscarCicloFormativo(cicloFormativo);
    }

    public void borrarCiclo (CicloFormativo cicloFormativo)
            throws IllegalArgumentException
    {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException
                    ("ERROR: el Ciclo Formativo que intenta borrar es nulo.");
        }
        modelo.borrarCicloFormativo(cicloFormativo);
    }

    public ArrayList<CicloFormativo> getCiclos()
    {
        return modelo.getCiclos();
    }

    // Métodos para hacer operaciones con Matriculas:
    public void insertarMatricula(Matricula matricula)
            throws IllegalArgumentException
    {
        if (matricula == null) {
            throw new IllegalArgumentException
                    ("ERROR: la Matrícula que intenta insertar es nula.");
        }
        modelo.insertarMatricula(matricula);
    }

    public Matricula buscarMatricula (Matricula matricula)
            throws IllegalArgumentException
    {
        if (matricula == null) {
            throw new IllegalArgumentException
                    ("ERROR: la Matrícula que intenta buscar es nula.");
        }
        return modelo.buscarMatricula(matricula);
    }

    public void borrarMatricula (Matricula matricula)
            throws IllegalArgumentException
    {
        if (matricula == null) {
            throw new IllegalArgumentException
                    ("ERROR: la Matrícula que intenta borrar es nula.");
        }
        modelo.borrarMatricula(matricula);
    }

    public ArrayList<Matricula> getMatriculas()
    {
        return modelo.getMatriculas();
    }

    public ArrayList<Matricula> getMatriculas (Alumno alumno)
            throws IllegalArgumentException
    {
        if (alumno == null) {
            throw new IllegalArgumentException
                    ("ERROR: no se pueden obtener las matrículas de un Alumno nulo.");
        }
        return modelo.getMatriculas(alumno);
    }

    public ArrayList<Matricula> getMatriculas (CicloFormativo cicloFormativo)
            throws IllegalArgumentException
    {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException
                    ("ERROR: no se pueden obtener las matrículas de un Ciclo Formativo nulo.");
        }
        return modelo.getMatriculas(cicloFormativo);
    }

    public ArrayList<Matricula> getMatriculas (String cursoAcademico) throws IllegalArgumentException
    {
        if (cursoAcademico == null) {
            throw new IllegalArgumentException
                    ("ERROR: no se pueden obtener las matrículas de un Curso Académico nulo.");
        }
        return modelo.getMatriculas(cursoAcademico);
    }

}
