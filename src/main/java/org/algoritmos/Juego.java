package org.algoritmos;

import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;

import javax.swing.*;

//Clase juego donde se almacena la logica interna del juego.
public class Juego {
    ListaJugadores lj;
    private int winnerScore;
    public Juego(ListaJugadores lj) {
        this.lj = lj;
    }

    //Metodo para agregar a la lista de jugadores los bots que indique el usuario.
    public void botCreate(int botAmount) {
        for (int i = 1; i < botAmount+1; i++) {
            lj.insertarFinal(new Jugador("Bot #" + i, ""));
        }
    }

    public int getWinnerScore() {
        return winnerScore;
    }

    public void setLj(ListaJugadores lj) {
        this.lj = lj;
    }

    public ListaJugadores getLj() {
        return lj;
    }

    //The game.
    public boolean game() {
        //Variable utilizada para terminar el loop del juego.
        boolean jugar = true;
        do {
            //for para ir recorriendo la lista de jugadores para saber el turno de cada uno.
            for (int i = 0; i < lj.size(); i++) {
                Jugador jugador = lj.buscarJugador(i);
                if (!jugador.getUsuario().equals("Bot #" + i)) {
                    winnerScore = jugador.getScore();
                }
                //Aqui verificamos si en el turno anterior el jugador no quedo en la casilla 22 por lo que perderia el turno.
                if (jugador.isTurno()) {
                    //Para verificar si alguien gano el juego.
                    if (jugador.getScore() >= 84) {
                        jugar = false;
                        JOptionPane.showMessageDialog(null, "Felicidades! " + jugador.getUsuario() + " ha ganado!");
                        return true;
                        //Si nadie ha ganado se continua con el juego.
                    } else {
                        //Se declaran los valores de los dados en la ronda.
                        int dice1 = dice();
                        int dice2 = dice();
                        //Se suman ambos para saber cuantas casillas procede a avanzar.
                        int sumDice = sumDice(jugador, dice1, dice2);
                        //Se le despliega al jugador cuanto dieron ambos dados.
                        JOptionPane.showMessageDialog(null, "Jugador: " + jugador.getUsuario()
                                + "\nDado 1: " + dice1 + "\nDado 2: " + dice2
                                + "\nTotal: " + sumDice);
                        if (dice1 == 6 && dice2 == 6) {
                            JOptionPane.showMessageDialog(null, "Felicidades, ambos dados son un 6, repite turno!");
                            boolean dobles = true;
                            int doubleCount = 1;
                            updatePlayer(jugador, sumDice);
                            JOptionPane.showMessageDialog(null, "Resultado del turno:" + "\nJugador: "
                                    + jugador.getUsuario() + "\nRonda actual: "
                                    + jugador.getLaps() + "\nPosicion: "
                                    + jugador.getPosicion() + "\nPuntuacion: " + jugador.getScore());
                            if (jugador.getScore() >= 84) {
                                jugar = false;
                                JOptionPane.showMessageDialog(null, "Felicidades! " + jugador.getUsuario() + " ha ganado!");
                                return true;
                            }
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
                                    if (jugador.getScore() >= 84) {
                                        jugar = false;
                                        JOptionPane.showMessageDialog(null, "Felicidades! " + jugador.getUsuario() + " ha ganado!");
                                        return true;
                                    }
                                } else {
                                    updatePlayer(jugador, sumGDice);
                                    JOptionPane.showMessageDialog(null, "Resultado del turno:" + "\nJugador: "
                                            + jugador.getUsuario() + "\nRonda actual: "
                                            + jugador.getLaps() + "\nPosicion: "
                                            + jugador.getPosicion() + "\nPuntuacion: " + jugador.getScore());
                                    if (jugador.getScore() >= 84) {
                                        jugar = false;
                                        JOptionPane.showMessageDialog(null, "Felicidades! " + jugador.getUsuario() + " ha ganado!");
                                        return true;
                                    }
                                    dobles = false;
                                }
                            }
                        } else {
                            updatePlayer(jugador, sumDice);
                            JOptionPane.showMessageDialog(null, "Resultado del turno:" + "\nJugador: "
                                    + jugador.getUsuario() + "\nRonda actual: "
                                    + jugador.getLaps() + "\nPosicion: "
                                    + jugador.getPosicion() + "\nPuntuacion: " + jugador.getScore());
                            if (jugador.getScore() >= 84) {
                                jugar = false;
                                JOptionPane.showMessageDialog(null, "Felicidades! " + jugador.getUsuario() + " ha ganado!");
                                return true;
                            }
                        }
                    }
                    //Else en caso de que el jugador hubiera perdido el turno.
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

    private int sumDice(Jugador j, int dice1, int dice2) {
        int total = dice1 + dice2;
        if ((total) == 6) {
            JOptionPane.showMessageDialog(null, "Ambos dados suman 6, retrocede 1 posicion.");
            j.setPosicion(j.getPosicion() - 1);
            j.setScore(j.getScore() - 1);
        }
        return total;
    }

    public void checkPos(Jugador player, int pos) {
        switch (pos) {
            case 3 -> {
                player.setPosicion(player.getPosicion() - 2);
                player.setScore(player.getScore() - 2);
                JOptionPane.showMessageDialog(null, "Posicion 3: Retrocede 2 posiciones.");
            }
            case 8 -> {
                player.setPosicion(0);
                player.setScore(player.getScore() - 8);
                JOptionPane.showMessageDialog(null, "Posicion 8: Se devuelve al inicio del tablero.");
            }
            case 14 -> {
                player.setPosicion(player.getPosicion() - 5);
                player.setScore(player.getScore() - 5);
                JOptionPane.showMessageDialog(null, "Posicion 14: Retrocede 5 posiciones.");
            }
            case 22 -> {
                player.setTurno(false);
                JOptionPane.showMessageDialog(null, "Posicion 22: Pierde el siguiente turno.");
            }
            case 25 -> {
                player.setPosicion(player.getPosicion() + 3);
                player.setScore(player.getScore() + 3);
                JOptionPane.showMessageDialog(null, "Posicion 25: Avanza 3 posiciones.");
            }
        }
    }
}
