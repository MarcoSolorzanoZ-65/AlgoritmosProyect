package org.algoritmos;

/**
 * @author : marco
 * @created : 12/4/2022, Sunday
 **/

public class Juego {
    ListaJugadores lj = new ListaJugadores();

    public void botCreate(int botAmount) {
        for (int i = 0; i < botAmount; i++) {
            lj.insertarFinal(new Jugador("Bot #" + i, ""));
        }
    }

    public void game() {
        boolean jugar = true;
        while (jugar) {
            for (int i = 0; i < lj.size(); i++) {
                Jugador jugador = lj.buscarJugador(i);
                int dice1 = dice();
                int dice2 = dice();
                sumDice(jugador, dice1, dice2);
                if (dice1 == 6 && dice2 == 6) {
                    boolean dobles = true;
                    int doubleCount = 0;
                    jugador.setPosicion(jugador.getPosicion() + (dice1 + dice2));
                    jugador.setScore(jugador.getScore() + (dice1 + dice2));
                    while (dobles || doubleCount != 2) {
                        int goldenDice1 = dice();
                        int goldenDice2 = dice();
                        if (goldenDice1 == 6 && goldenDice2 == 6) {
                            jugador.setPosicion(jugador.getPosicion() + (goldenDice1 + goldenDice2));
                            jugador.setScore(jugador.getScore() + (goldenDice1 + goldenDice2));
                            doubleCount++;
                        } else {
                            sumDice(jugador, goldenDice1, goldenDice2);
                            jugador.setPosicion(jugador.getPosicion() + (goldenDice1 + goldenDice2));
                            jugador.setScore(jugador.getScore() + (goldenDice1 + goldenDice2));
                            dobles = false;
                        }
                    }
                } else {
                    jugador.setPosicion(jugador.getPosicion() + (dice1 + dice2));
                    jugador.setScore(jugador.getScore() + (dice1 + dice2));
                }
            }
        }
    }

    private int dice() {
        return (int) ((Math.random() * (7 - 1)) + 1);
    }

    private void sumDice(Jugador j, int dice1, int dice2) {
        if ((dice1 + dice2) == 6) {
            j.setPosicion(j.getPosicion() - 1);
        }
    }

    public void checkPos(Jugador player, int pos) {
        switch (pos) {
            case 3 -> player.setPosicion(player.getPosicion() - 2);
            case 8 -> player.setPosicion(0);
            case 14 -> player.setPosicion(player.getPosicion() - 5);
            case 22 -> player.setTurno(false);
            case 25 -> player.setPosicion(player.getPosicion() + 3);
        }
    }
}
