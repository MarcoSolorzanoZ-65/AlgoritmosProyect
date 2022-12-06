package org.ReadAndWrite_History;

import org.PlayerRelated.Historial;
import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaHistoriales;
import org.PlayerRelated.ListaJugadores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderManagerH {

    private BufferedReader reader;
    ListaHistoriales lista;

    public ReaderManagerH(ListaHistoriales lista) {
        this.lista = lista;
    }

    public void open(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public Historial read() throws IOException {
        Historial h = null;
        String line = null; //retorna null cuando no hay más registros
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] datos;
        if (line != null) {
            h = new Historial(0,0);
            datos = line.split("_"); // separa el String en un array
            h.setNumeroJuego(Long.parseLong(datos[0]));
            h.setPuntacionJuego(Integer.parseInt(datos[1]));
        }
        return h;
    }

    public void readAll() throws IOException {
        Historial h;
        while ((h = read()) != null) {
            lista.insertarFinal(h);
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}