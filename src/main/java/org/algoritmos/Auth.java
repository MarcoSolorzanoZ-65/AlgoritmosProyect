package org.algoritmos;

import org.PlayerRelated.Jugador;
import org.PlayerRelated.ListaJugadores;
import org.PlayerRelated.Nodo;
import org.ReadAndWrite_Players.Dao;

import javax.swing.*;

/**
 * @author : marco
 * @created : 12/5/2022, Monday
 **/

public class Auth {
    ListaJugadores lj;
    Dao filemanager;

    public Auth(ListaJugadores lj, Dao fileManager) {
        this.lj = lj;
        this.filemanager = fileManager;
    }

    public Jugador login() {
        String user = JOptionPane.showInputDialog("Ingrese su nombre de usuario");
        String pass = JOptionPane.showInputDialog("Ingrese su contraseña");

        Nodo temp = lj.getCabeza();
        while (temp != null) {
            if (temp.getDato().getUsuario().equals(user) && temp.getDato().getPassword().equals(pass)) {
                return temp.getDato();
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    public boolean register() {
        String user = JOptionPane.showInputDialog("Ingrese su nombre de usuario");
        String pass = JOptionPane.showInputDialog("Ingrese su contraseña");

        String pass2 = JOptionPane.showInputDialog("Confirme su contraseña");

        Nodo temp = lj.getCabeza();
        while (temp != null) {
            if (user.equals(temp.getDato().getUsuario())) {
                JOptionPane.showMessageDialog(null, "Este nombre de usuario ya existe!");
                return false;
            }
            temp = temp.getSiguiente();
        }
        if (pass.equals(pass2)) {
            Jugador j = new Jugador(user, pass);
            filemanager.insertar(j);
        }else{
            JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales");
        }
        return true;
    }

}
