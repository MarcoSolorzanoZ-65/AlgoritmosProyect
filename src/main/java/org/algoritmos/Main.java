package org.algoritmos;

import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;
import org.ReadAndWrite_Players.Dao;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ListaJugadores gameList = new ListaJugadores();
        ListaJugadores users = new ListaJugadores();
        Dao dao = new Dao(users);
        Auth auth = new Auth(users, dao);
        Juego juego = new Juego(gameList);

        dao.cargarDatosPrueba();

        boolean seguir = true;
        while(seguir) {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion a elegir: \n1)Login\n2)Registrarse"));
            switch (opcion) {
                case 1 -> {
                    Jugador player = auth.login();
                    if (player != null) {
                        boolean salir = false;
                        while (!salir) {
                            int cantBots = Integer.parseInt(JOptionPane.showInputDialog("Ingrese contra cuantos bots desea jugar (maximo 3)"));
                            if (cantBots <= 3) {
                                salir = true;
                                gameList.insertarFinal(player);
                                juego.botCreate(cantBots);
                                juego.game();
                            }
                        }
                    }
                }
                case 2 -> auth.register();

                default -> JOptionPane.showMessageDialog(null, "Opcion invalida!");
            }
        }
    }
}