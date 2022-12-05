package org.algoritmos;

import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;

public class Main {
    public static void main(String[] args) {
        ListaJugadores gameList = new ListaJugadores();
        ListaJugadores users = new ListaJugadores();
        Auth auth = new Auth(users);
        Juego juego = new Juego(gameList);

        gameList.insertarFinal(new Jugador("marco", "penesucio69"));
        juego.botCreate(3);
        juego.game();
    }
}