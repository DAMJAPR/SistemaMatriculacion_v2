package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

// Apartado 9.1.
public class CiclosFormativos {

    private final ArrayList<CicloFormativo> coleccionCiclosFormativos; // Array interno para gestionar los ciclos formativos

    // Apartado 9.1.i.
    // Constructor con parámetros
    public CiclosFormativos()
            throws IllegalArgumentException
    {
        this.coleccionCiclosFormativos = new ArrayList<>(); // inicializa el array
    }

    // Apartado 9.1.ii.
    // Método GET para devolver una copia profunda del array
    public ArrayList<CicloFormativo> get() {
        return copiaProfundaCiclosFormativos();
    }

    // método para la Copia Profunda de Alumnos
    private ArrayList<CicloFormativo> copiaProfundaCiclosFormativos()
    {
        // Creamos un nuevo array con la misma longitud que el original
        ArrayList<CicloFormativo> copiaCiclos = new ArrayList<>();

        // Recorremos el array original y copiamos cada objeto
        for (CicloFormativo c : coleccionCiclosFormativos) {
            copiaCiclos.add(new CicloFormativo(c));
        }
        return copiaCiclos;
    }

    // Métodos GETTER para el tamaño y la capacidad
    public int getTamano() {
        return coleccionCiclosFormativos.size();
    }

    // Apartado 9.1.iii.
    // Método para insertar ciclos formativos
    public void insertarCiclo (CicloFormativo cicloFormativo)
            throws IllegalArgumentException, OperationNotSupportedException {
        if (cicloFormativo == null){
            throw new IllegalArgumentException
                    ("ERROR: no se pueden insertar ciclos formativos nulos.");
        }

        if (buscar(cicloFormativo) != null){
            throw new OperationNotSupportedException
                    ("ERROR: ya existe un ciclo formativo con ese código.");
        }
        coleccionCiclosFormativos.add(new CicloFormativo(cicloFormativo)); // Inserta al final y copia profunda
    }

    // Apartado 9.1.iv.
    // Método para buscar un ciclo formativo con un índice
    public CicloFormativo buscar(CicloFormativo cicloFormativo)
    {
        if (cicloFormativo == null) {
            System.out.println("El Ciclo Formativo que está buscando es nulo.");
            return null;
        }
        if (coleccionCiclosFormativos.contains(cicloFormativo)) {
            System.out.println("\nCiclo Formativo encontrado: ");
            return cicloFormativo;
        } else System.out.println("\nNo se ha encontrado el Ciclo Formativo especificado.");
        return null;
    }

    //  Apartado 9.1.v.
    // Método para borrar un ciclo formativo
    public void borrar(CicloFormativo cicloFormativo)
            throws IllegalArgumentException
    {
        if (cicloFormativo == null){
            throw new IllegalArgumentException
                    ("ERROR: no se puede borrar un Ciclo Formativo nulo.");
        }
        if (coleccionCiclosFormativos.contains(cicloFormativo)){
            coleccionCiclosFormativos.remove(cicloFormativo);
                System.out.println("\nCiclo Formativo borrado correctamente.");
        } else System.out.println("\nNo se encuentra el Ciclo Formativo que desea borrar.");
    }
}
