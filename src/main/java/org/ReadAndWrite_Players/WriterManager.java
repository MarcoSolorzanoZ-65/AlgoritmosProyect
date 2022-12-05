package org.ReadAndWrite_Players;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;

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