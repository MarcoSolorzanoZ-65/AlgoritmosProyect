package org.PlayerRelated;

public class Historial {
    private int numeroJuego, puntacionJuego;

    public int getNumeroJuego() {
        return numeroJuego;
    }

    public void setNumeroJuego(int numeroJuego) {
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
        return numeroJuego + "-" + puntacionJuego;
    }
}
