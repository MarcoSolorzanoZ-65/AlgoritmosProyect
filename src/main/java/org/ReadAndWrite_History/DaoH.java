package org.ReadAndWrite_History;

import org.PlayerRelated.Historial;
import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaHistoriales;
import org.PlayerRelated.ListaJugadores;

import java.io.IOException;

public class DaoH {

    ListaHistoriales lista;
    WriterManagerH writer;
    ReaderManagerH reader;
    public static String FILE_NAME;

    public DaoH(ListaHistoriales lista, Jugador jugador) {
        this.lista = lista;
        this.writer = new WriterManagerH(lista);
        this.reader = new ReaderManagerH(lista);
        FILE_NAME = jugador.getUsuario() + ".txt";
    }

    public boolean insertar(Historial h) {
        lista.insertarInicio(h);
        guardarLista();
        return true;
    }

    public void cargarDatosPrueba() throws IOException { // se agregan datos de prueba
        try {
            reader.open(FILE_NAME);
            reader.readAll();
            reader.close(); //importante cerrar el archivo
            System.out.println("Lectura exitosa.");
        } catch (IOException ex) {
            try {
                writer.open(FILE_NAME);
                writer.writeAll();
                writer.close();
                System.out.println("Archivo creado.");
            } catch (IOException exe) {
                System.err.println("Error de archivo.");
                System.err.println(ex.getMessage());
            }
        }
    }

    private void guardarLista() {
        try {
            writer.open(FILE_NAME);
            writer.writeAll();
            writer.close();
            System.out.println("Escritura exitosa.");
        } catch (IOException ex) {
            System.err.println("Error de archivo.");
            System.err.println(ex.getMessage());
        }
    }

    public ListaHistoriales getLista() {
        return lista;
    }
}