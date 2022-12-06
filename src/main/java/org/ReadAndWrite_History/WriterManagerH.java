package org.ReadAndWrite_History;

import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterManagerH {

    private BufferedWriter writer;
    ListaJugadores lista;

    public WriterManagerH(ListaJugadores lista) {
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
            Jugador temp = null;
            if (e != null && e != temp) {
                write(e);
            }
            temp = e;
        }
    }

    public void close() throws IOException {
        writer.close();
    }
}