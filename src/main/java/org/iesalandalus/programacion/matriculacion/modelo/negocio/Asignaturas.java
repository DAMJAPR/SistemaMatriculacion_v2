package org.iesalandalus.programacion.matriculacion.modelo.negocio;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

// Apartado 10.1.
public class Asignaturas {

    private final ArrayList<Asignatura> coleccionAsignaturas; // Array interno para gestionar las asignaturas

    // Métodos GET para el tamaño de la colección
    public int getTamano() {
        return coleccionAsignaturas.size();
    }

    // Apartado 10.1.i.
    // Constructor con parámetros
    public Asignaturas ()
            throws IllegalArgumentException
    {
        this.coleccionAsignaturas = new ArrayList<>();
    }

    // Método para hacer una copia profunda del array de asignaturas
    private ArrayList<Asignatura> copiaProfundaAsignaturas(){
        // Creamos un nuevo array con la misma longitud que el original
        ArrayList<Asignatura> copiaAsignaturas = new ArrayList<>();

        // Recorremos el array original y copiamos cada objeto
        for (Asignatura a : coleccionAsignaturas){
            copiaAsignaturas.add(new Asignatura(a)); // Usamos el constructor copia
        }
        return copiaAsignaturas;
    }

    // Apartado 10.1.ii.
    // Método GET que devuelve una copia profunda del array
    public ArrayList<Asignatura> get(){
        return copiaProfundaAsignaturas();
    }

    // Apartado 10.1.iii.
    // Método para insertar asignaturas
    public void insertar(Asignatura asignatura)
            throws NullPointerException, OperationNotSupportedException {
        if (asignatura == null){
            throw new NullPointerException
                    ("ERROR: la asignatura que quiere introducir es nula.");
        }

        if (buscar(asignatura) != null){
            throw new OperationNotSupportedException
                    ("ERROR: ya existe una asignatura con ese código.");
        }
        coleccionAsignaturas.add(new Asignatura(asignatura)); // Insertamos con copia profunda
    }

    // Apartado 10.1.iv.
    // Método para buscar asignaturas
    public Asignatura buscar(Asignatura asignatura){
        if (asignatura == null) {
            System.out.println("La asignatura que está buscando es nula.");
            return null;
        }
        if (coleccionAsignaturas.contains(asignatura)) {
            System.out.println("\nAsignatura encontrada: ");
            return asignatura;
        } else System.out.println("\nNo se ha encontrado la asignatura especificada.");
        return null;
    }

    // Apartado 10.1.v.
    // Método para borrar asignaturas
    public void borrar(Asignatura asignatura)
            throws IllegalArgumentException
    {
        if (asignatura == null){
            throw new IllegalArgumentException
                    ("ERROR: no se puede borrar una asignatura nula.");
        }
        if (coleccionAsignaturas.contains(asignatura)){
            coleccionAsignaturas.remove(asignatura);
            System.out.println("\nMatrícula borrada correctamente.");
        } else System.out.println("\nNo se encuentra la matrícula que desea borrar.");
    }
}
