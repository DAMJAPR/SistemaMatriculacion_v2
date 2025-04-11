package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

// Apartado 8.1.
public class Alumnos {

    private final ArrayList<Alumno> coleccionAlumnos; // ArrayList interno para gestionar los alumnos

    // Apartado 8.1.i.
    // Constructor con parámetros
    public Alumnos()
            throws IllegalArgumentException
    {
        coleccionAlumnos = new ArrayList<>();
    }

    // Apartado 8.1.ii.
    // método GET para el array de tipo Alumno
    public ArrayList<Alumno> get(){
        return copiaProfundaAlumnos();
    }

    // método para la Copia Profunda de Alumnos
    private ArrayList<Alumno> copiaProfundaAlumnos(){
        // Creamos un nuevo ArrayList con el mismo contenido que el original
        ArrayList<Alumno> copiaAlumnos = new ArrayList<>();

        // Recorremos el ArrayList original
        for (Alumno a : coleccionAlumnos) {
            copiaAlumnos.add(new Alumno(a)); // Por cada alumno, hacemos copia profunda con el constructor copia
        }

        return copiaAlumnos;
    }

    // Métodos GETTER para el tamaño y la capacidad
    public int getTamano() {
        return coleccionAlumnos.size();
    }


    // Apartado 8.1.iii.
    // Método para insertar alumnos
    public void insertarAlumno (Alumno alumno)
            throws NullPointerException, OperationNotSupportedException {
        if (alumno == null){
            throw new NullPointerException
                    ("ERROR: no se pueden insertar alumnos nulos.");
        }

        if (buscar(alumno) != null){
            throw new OperationNotSupportedException
                    ("ERROR: ya existe un Alumno con ese dni.");
        }
        coleccionAlumnos.add(new Alumno(alumno)); // Añade el Alumno al final y copia profunda
       }

    // Apartado 8.1.iv.
    // Método para buscar un alumno
    public Alumno buscar(Alumno alumno){
        if (coleccionAlumnos.contains(alumno)) {
            return alumno;
        } return null;
    }


    // Apartado 8.1.v.
    // Método para borrar alumnos
    public void borrar(Alumno alumno)
            throws NullPointerException
    {
        if (alumno == null) {
            throw new NullPointerException
                    ("ERROR: el alumno es nulo.");
        } else if (coleccionAlumnos.contains(alumno)){
            coleccionAlumnos.remove(alumno);
            System.out.println("Alumno borrado correctamente.");
        } else
            System.out.println("No se encuentra el alumno que desea borrar.");
    }

}

