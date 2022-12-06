package org.ReadAndWrite_History;

import org.PlayerRelated.Historial;
import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaHistoriales;
import org.PlayerRelated.ListaJugadores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterManagerH {

    private BufferedWriter writer;
    ListaHistoriales lista;

    public WriterManagerH(ListaHistoriales lista) {
        this.lista = lista;
    }

    public void open(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void write(Historial h) throws IOException {
        writer.write(h.toString() + "\n");
    }

    public void writeAll() throws IOException {
        for (int i = 0; i < lista.size(); i++) {
            Historial e = lista.buscarHistorial(i);
            Historial temp = null;
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