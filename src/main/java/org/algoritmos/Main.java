package org.algoritmos;

import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;
import org.ReadAndWrite_History.DaoH;
import org.ReadAndWrite_Players.Dao;
import org.apache.commons.codec.digest.DigestUtils;
import javax.swing.*;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        ListaJugadores gameList = new ListaJugadores();
        ListaJugadores users = new ListaJugadores();
        Dao dao = new Dao(users);
        Auth auth = new Auth(users, dao);
        Juego juego = new Juego(gameList);

        dao.cargarDatosPrueba();

        boolean seguir = true;
        while (seguir) {
            int opcion = 0;
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion a elegir: \n1) Login.\n2) Registrarse."));
            } catch (NumberFormatException e) {
                seguir = false;
            }
            switch (opcion) {
                case 0 -> JOptionPane.showMessageDialog(null, "Hasta luego!");
                case 1 -> {
                    Jugador player = auth.login();
                    if (player != null) {
                        boolean salir = false;
                        while (!salir) {
                            int op = Integer.parseInt(JOptionPane.showInputDialog("""
                                    Que desea hacer?\s
                                    1) Jugar.
                                    2) Perfil.
                                    3) Historial.
                                    4) Cerrar sesion."""));
                            switch (op) {
                                case 1 -> {
                                    int cantBots = 0;
                                    Random randomobj = new Random();
                                    DaoH daoH = new DaoH(player.getHistorial(), player);
                                    long seed = new Random().nextLong();
                                    randomobj.setSeed(seed);
                                    try {
                                        cantBots = Integer.parseInt(JOptionPane.showInputDialog("Ingrese contra cuantos bots desea jugar (maximo 3)."));
                                    } catch (NumberFormatException e) {
                                        System.err.println("Error de formato.");
                                        salir = true;
                                    }
                                    if (cantBots <= 3 && cantBots > 0) {
                                        gameList.insertarFinal(player);
                                        juego.botCreate(cantBots);
                                        juego.game();
                                        if (daoH.getLista().getCabeza() != null) {
                                            System.out.println("La lista ya esta cargada.");
                                        }else{
                                            daoH.cargarDatosPrueba();
                                        }
                                        daoH.getLista().ordenar(daoH.getLista().getCabeza());
                                        int finalseed = randomobj.nextInt();
                                        daoH.insertar(new Historial(finalseed, juego.getWinnerScore()));
                                        juego.setLj(new ListaJugadores());
                                        player.setScore(0);
                                        player.setPosicion(0);
                                        player.setLaps(0);
                                        player.setTurno(true);
                                        juego.getLj().insertarFinal(player);
                                    }
                                }
                                case 2 -> {
                                    String contraNueva = DigestUtils.md5Hex(JOptionPane.showInputDialog("Ingrese su nueva contraseña."));
                                    String contraConfirm = DigestUtils.md5Hex(JOptionPane.showInputDialog("Confirme su nueva contraseña."));

                                    if (contraConfirm.equals(contraNueva)) {
                                        dao.editar(player.getUsuario(), DigestUtils.md5Hex(JOptionPane.showInputDialog("Ingrese su vieja contraseña.")),
                                                contraConfirm);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "La contraseña nueva no coincide");
                                    }
                                }
                                case 3 -> {
                                    DaoH daoH = new DaoH(player.getHistorial(), player);
                                    if (daoH.getLista().getCabeza() != null) {
                                        System.out.println("La lista ya esta cargada.");
                                    }else{
                                        daoH.cargarDatosPrueba();
                                    }
                                    daoH.getLista().ordenar(daoH.getLista().getCabeza());
                                    JOptionPane.showMessageDialog(null, player.getHistorial().imprimir());
                                }
                                case 4 -> {
                                    salir = true;
                                }
                                default -> JOptionPane.showInputDialog("Opcion invalida!");
                            }
                        }
                    }
                }
                case 2 -> {
                    try {
                        auth.register();
                    } catch (NullPointerException e) {
                        System.err.println("Error: null.");
                    }
                }
                default -> JOptionPane.showMessageDialog(null, "Opcion invalida!");
            }
        }
    }
}