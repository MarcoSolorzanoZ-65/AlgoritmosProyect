package org.algoritmos;

import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;

import javax.swing.*;

/**
 * @author : marco
 * @created : 12/4/2022, Sunday
 **/

public class Juego {
    ListaJugadores lj;

    public Juego(ListaJugadores lj) {
        this.lj = lj;
    }

    public void botCreate(int botAmount) {
        for (int i = 0; i < botAmount; i++) {
            lj.insertarFinal(new Jugador("Bot #" + i, ""));
        }
    }

    public void setLj(ListaJugadores lj) {
        this.lj = lj;
    }

    public ListaJugadores getLj() {
        return lj;
    }

    public boolean game() {
        boolean jugar = true;
        while (jugar) {
            for (int i = 0; i < lj.size(); i++) {
                Jugador jugador = lj.buscarJugador(i);
                if (jugador.isTurno()) {
                    if (jugador.getScore() >= 84) {
                        jugar = false;
                        JOptionPane.showMessageDialog(null, "Felicidades " + jugador.getUsuario() + " has ganado!");
                        return true;
                    } else {
                        int dice1 = dice();
                        int dice2 = dice();
                        int sumDice = sumDice(jugador, dice1, dice2);
                        if (jugador.getScore() >= 84) {
                            jugar = false;
                            JOptionPane.showMessageDialog(null, "Felicidades " + jugador.getUsuario() + " has ganado!");
                            return true;
                        }
                        JOptionPane.showMessageDialog(null, "Jugador: " + jugador.getUsuario()
                                + "\nDado 1: " + dice1 + "\nDado 2: " + dice2
                                + "\nTotal: " + sumDice);
                        if (sumDice == 6){
                            JOptionPane.showMessageDialog(null, "Ambos dados suman 6, retrocede 1 posicion.");
                        }
                        if (dice1 == 6 && dice2 == 6) {
                            JOptionPane.showMessageDialog(null, "Felicidades, ambos dados son un 6, repite turno!");
                            boolean dobles = true;
                            int doubleCount = 1;
                            updatePlayer(jugador, sumDice);
                            JOptionPane.showMessageDialog(null, "Resultado del turno:" + "\nJugador: "
                                    + jugador.getUsuario() + "\nRonda actual: "
                                    + jugador.getLaps() + "\nPosicion: "
                                    + jugador.getPosicion() + "\nPuntuacion: " + jugador.getScore());
                            while (dobles && doubleCount != 2) {
                                int goldenDice1 = dice();
                                int goldenDice2 = dice();
                                int sumGDice = sumDice(jugador, goldenDice1, goldenDice2);
                                JOptionPane.showMessageDialog(null, "Jugador: " + jugador.getUsuario()
                                        + "\nDado 1: " + goldenDice1 + "\nDado 2: " + goldenDice2
                                        + "\nTotal: " + sumGDice);
                                if (sumGDice == 6){
                                    JOptionPane.showMessageDialog(null, "Ambos dados suman 6, retrocede 1 posicion.");
                                }
                                if (goldenDice1 == 6 && goldenDice2 == 6) {
                                    JOptionPane.showMessageDialog(null, "Felicidades, ambos dados son un 6, repite turno nuevamente!");
                                    updatePlayer(jugador, sumGDice);
                                    JOptionPane.showMessageDialog(null, "Resultado del turno:" + "\nJugador: "
                                            + jugador.getUsuario() + "\nRonda actual: "
                                            + jugador.getLaps() + "\nPosicion: "
                                            + jugador.getPosicion() + "\nPuntuacion: " + jugador.getScore());
                                    doubleCount++;
                                } else {
                                    updatePlayer(jugador, sumGDice);
                                    JOptionPane.showMessageDialog(null, "Resultado del turno:" + "\nJugador: "
                                            + jugador.getUsuario() + "\nRonda actual: "
                                            + jugador.getLaps() + "\nPosicion: "
                                            + jugador.getPosicion() + "\nPuntuacion: " + jugador.getScore());
                                    dobles = false;
                                }
                            }
                        } else {
                            updatePlayer(jugador, sumDice);
                            JOptionPane.showMessageDialog(null, "Resultado del turno:" + "\nJugador: "
                                    + jugador.getUsuario() + "\nRonda actual: "
                                    + jugador.getLaps() + "\nPosicion: "
                                    + jugador.getPosicion() + "\nPuntuacion: " + jugador.getScore());
                        }
                    }
                } else {
                    jugador.setTurno(true);
                    JOptionPane.showMessageDialog(null, "El jugador " + jugador.getUsuario() + " perdio el turno la ronda pasada.");
                }
                ListaJugadores ljScores = new ListaJugadores();
                ljScores = lj;
                ljScores.ordenar(ljScores.getCabeza());
                String ScorePlayers = "Progreso actual de los jugadores:\n";
                for (int x = 0; x < ljScores.size(); x++) {
                    ScorePlayers += "#" + (x + 1) + ": " + ljScores.buscarJugador(x).getUsuario() + ", puntuacion: " + ljScores.buscarJugador(x).getScore() + ".\n";
                }
                JOptionPane.showMessageDialog(null, ScorePlayers);
            }
        } while (jugar);
        return false;
    }

    private void updatePlayer(Jugador jugador, int sumDice) {
        jugador.setPosicion(jugador.getPosicion() + (sumDice));
        checkPos(jugador, jugador.getPosicion());
        checkLaps(jugador);
        jugador.setScore(jugador.getScore() + (sumDice));
    }

    public void checkLaps(Jugador jugador) {
        if (jugador.getPosicion() == 28) {
            jugador.setPosicion(0);
            jugador.setLaps(jugador.getLaps() + 1);
        } else if (jugador.getPosicion() > 28) {
            int posicionesSobrantes = jugador.getPosicion() - 28;
            jugador.setPosicion(posicionesSobrantes);
            jugador.setLaps(jugador.getLaps() + 1);
        }
    }

    private int dice() {
        return (int) ((Math.random() * (7 - 1)) + 1);
    }

    private void sumDice(Jugador j, int dice1, int dice2) {
        if ((dice1 + dice2) == 6) {
            j.setPosicion(j.getPosicion() - 1);
            j.setScore(j.getScore() - 1);
            JOptionPane.showMessageDialog(null, "Ambos dados suman 6, retrocede 1 posicion.");
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
