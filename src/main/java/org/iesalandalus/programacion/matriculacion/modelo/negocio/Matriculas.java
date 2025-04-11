package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

// Punto 11.1.
public class Matriculas {

    private ArrayList<Matricula> coleccionMatriculas; // Array interno para gestionar Matrículas

    // Método GETTER para el tamaño de la colección
    public int getTamano() {
        return coleccionMatriculas.size();
    }

    // Punto 11.1.i.
    // Constructor con parámetros
    public Matriculas()
            throws IllegalArgumentException
    {
        this.coleccionMatriculas = new ArrayList<>();
    }

    // Punto 11.1.ii.
    // Método GET que devuelve una copia profunda
    public ArrayList<Matricula> get(){
        return copiaProfundaMatriculas();
    }

    // Método para realizar copias profundas
    private ArrayList<Matricula> copiaProfundaMatriculas()
    {
        ArrayList<Matricula> copiaMatriculas = new ArrayList<>();
        for (Matricula m : coleccionMatriculas) {
            copiaMatriculas.add(new Matricula(m));
        }
        return copiaMatriculas;
    }

    // Apartado 11.1.iii.
    // Método para insertar matrículas
    public void insertar(Matricula matricula)
            throws NullPointerException, OperationNotSupportedException
    {
        if (matricula == null) {
            throw new NullPointerException
                    ("ERROR: no se puede insertar una matrícula nula.");
        }

        if (buscar(matricula) != null) {
            throw new OperationNotSupportedException
                    ("ERROR: ya existe una matrícula con ese identificador.");
        }
        coleccionMatriculas.add(new Matricula(matricula)); // Copia profunda
    }


    // Apartado 11.1.iv.
    // Método para buscar una matrícula
    public Matricula buscar(Matricula matricula)
    {
        if (matricula == null) {
            System.out.println("La matrícula que está buscando es nula.");
            return null;
        }
        if (coleccionMatriculas.contains(matricula)) {
            System.out.println("\nMatrícula encontrada: ");
            return matricula;
        } else System.out.println("\nNo se ha encontrado la matrícula especificada.");
        return null;
    }

    // Apartado 11.1.v.
    // Método para borrar una matrícula
    public void borrar(Matricula matricula)
            throws IllegalArgumentException
    {
        if (matricula == null){
            throw new IllegalArgumentException
                    ("ERROR: no se puede borrar una matrícula nula.");
        }
        if (coleccionMatriculas.contains(matricula)){
            coleccionMatriculas.remove(matricula);
            System.out.println("\nMatrícula borrada correctamente.");
        } else System.out.println("\nNo se encuentra la matrícula que desea borrar.");
    }

    // Apartado 2.
    // Métodos GET sobrecargados

    // Método GET que devuelve las matrículas de un alumno pasado por parámetro
    public ArrayList<Matricula> get (Alumno alumno)
            throws IllegalArgumentException
    {
        if (alumno == null) {
            throw new IllegalArgumentException("ERROR: el Alumno es nulo.");
        }
        ArrayList<Matricula> resultado = new ArrayList<>();
        for (Matricula m : coleccionMatriculas){
            if (m.getAlumno().equals(alumno)){
                resultado.add(new Matricula(m)); // Añadimos usando el constructor copia
            }
        }
        return resultado;
    }

    // Método GET que devuelve las matrículas de un curso académico pasado por parámetro
    public ArrayList<Matricula> get (String cursoAcademico)
            throws IllegalArgumentException
    {
        if (cursoAcademico == null || cursoAcademico.isBlank()) {
            throw new IllegalArgumentException
                    ("ERROR: el Curso Académico es nulo o está vacío.");
        }
        ArrayList<Matricula> resultado = new ArrayList<>();
        for (Matricula m : coleccionMatriculas){
            if (m.getCursoAcademico().equals(cursoAcademico)){
                resultado.add(new Matricula(m)); // Añadimos usando el constructor copia
            }
        }
        return resultado;
    }

    // Método GET que devuelve las matrículas de un ciclo formativo pasado por parámetro
    public ArrayList<Matricula> get (CicloFormativo cicloFormativo)
    {
        ArrayList<Matricula> resultado = new ArrayList<>();
        for (Matricula m : coleccionMatriculas) {
            for (Asignatura a : m.getColeccionAsignaturas()) {
                if (a.getCicloFormativo().equals(cicloFormativo)) {
                    resultado.add(new Matricula(m)); // Copia profunda
                    break; // No hace falta seguir buscando en las asignaturas
                }
            }
        }
        return resultado;
    }
}
