package org.algoritmos;

import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;

import javax.swing.*;

/**
 * @author : marco
 * @created : 12/5/2022, Monday
 **/

public class Auth {
    ListaJugadores lj;

    public Auth(ListaJugadores lj) {
        this.lj = lj;
    }

    public void login() {
        String user = JOptionPane.showInputDialog("Ingrese su nombre de usuario");
        String pass = JOptionPane.showInputDialog("Ingrese su contraseña");


    }

    public void register() {
        String user = JOptionPane.showInputDialog("Ingrese su nombre de usuario");
        String pass = JOptionPane.showInputDialog("Ingrese su contraseña");

        String pass2 = JOptionPane.showInputDialog("Confirme su contraseña");

        if (pass.equals(pass2)) {
            lj.insertarInicio(new Jugador(user, pass));
        }
    }

}
