package org.PlayerRelated;

//Clase para almacenar tanto el numero de juego como la puntuacion respectiva que tuvo el jugador.
public class Historial {
    private long numeroJuego;
    private int puntacionJuego;

    public Historial(int numeroJuego, int puntacionJuego){
        this.numeroJuego = numeroJuego;
        this.puntacionJuego = puntacionJuego;
    }
    public long getNumeroJuego() {
        return numeroJuego;
    }

    public void setNumeroJuego(long numeroJuego) {
        this.numeroJuego = numeroJuego;
    }

    public int getPuntacionJuego() {
        return puntacionJuego;
    }

    public void setPuntacionJuego(int puntacionJuego) {
        this.puntacionJuego = puntacionJuego;
    }

    @Override
    public String toString() {
        return numeroJuego + "_" + puntacionJuego;
    }
}
