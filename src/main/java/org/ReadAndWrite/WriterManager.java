package org.ReadAndWrite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.algoritmos.Jugador;
import org.algoritmos.ListaJugadores;

/**
 * @author Marco Zumbado Solorzano carne C18736
 * @date 2021-08-16
 * @time 10:13:20
 */
public class WriterManager {

    private BufferedWriter writer;
    ListaJugadores lista;

    public WriterManager(ListaJugadores lista) {
        this.lista = lista;
    }

    public void open(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void write(Jugador p) throws IOException {
        writer.write(p.toString() + "\n");
    }

    public void writeAll() throws IOException {
        for (int i = 0; i < lista.size(); i++) {
            Jugador e = lista.buscarJugador(i);
            if (e != null) {
                write(e);
            }
        }
    }

    public void close() throws IOException {
        writer.close();
    }
}