package org.algoritmos;

public class Main {
    public static void main(String[] args) {
        ListaJugadores gameList = new ListaJugadores();
        ListaJugadores users = new ListaJugadores();
        Auth auth = new Auth(users);
        Juego juego = new Juego(gameList);


    }
}