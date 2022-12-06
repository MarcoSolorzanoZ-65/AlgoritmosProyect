package org.ReadAndWrite_History;

import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;

import java.io.IOException;

public class DaoH {

    ListaJugadores lista;
    WriterManagerH writer;
    ReaderManagerH reader;
    public static final String FILE_NAME = "usuarios.txt";

    public DaoH(ListaJugadores lista) {
        this.lista = lista;
        this.writer = new WriterManagerH(lista);
        this.reader = new ReaderManagerH(lista);
        FILE_NAME = jugador.getUsuario() + ".txt";
    }

    public boolean insertar(Jugador p) {
        lista.insertarInicio(p);
        guardarLista();
        return true;
    }

    public void cargarDatosPrueba() throws IOException { // se agregan datos de prueba
        try {
            reader.open(FILE_NAME);
            reader.readAll();
            reader.close(); //importante cerrar el archivo
            System.out.println("Lectura exitosa");
        } catch (IOException ex) {
            try {
                writer.open(FILE_NAME);
                writer.writeAll();
                writer.close();
                System.out.println("Archivo creado");
            } catch (IOException exe) {
                System.err.println("error de archivo");
                System.err.println(ex.getMessage());
            }
        }
    }

    private void guardarLista() {
        try {
            writer.open(FILE_NAME);
            writer.writeAll();
            writer.close();
            System.out.println("Escritura exitosa");
        } catch (IOException ex) {
            System.err.println("error de archivo");
            System.err.println(ex.getMessage());
        }
    }

    public boolean editar(String user, String pass, String newPass){
        Jugador s = lista.edit(user, pass, newPass);
        if (s != null) {
            guardarLista();
        }
        return true;
    }

    public ListaJugadores getLista() {
        return lista;
    }
}