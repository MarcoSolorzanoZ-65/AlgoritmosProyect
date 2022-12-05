package org.ReadAndWrite;

import org.algoritmos.Jugador;
import org.algoritmos.ListaJugadores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Marco Zumbado Solorzano carne C18736
 * @date 2021-08-16
 * @time 10:13:20
 */
public class ReaderManager {

    private BufferedReader reader;
    ListaJugadores lista;

    public ReaderManager(ListaJugadores lista) {
        this.lista = lista;
    }

    public void open(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public Jugador read() throws IOException {
        Jugador p = null;
        String line = reader.readLine(); //retorna null cuando no hay más registros
        String datos[];
        if (line != null) {
            p = new Jugador("", "");
            datos = line.split("-"); // separa el String en un array
            p.setUsuario(datos[0]);
            p.setPassword(datos[1]);

        }
        return p;
    }

    public void readAll() throws IOException {
        Jugador p;
        while ((p = read()) != null) {
            lista.insertarInicio(p);
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}