package org.PlayerRelated;

import javax.swing.*;

public class ListaJugadores {

    private Nodo cabeza, cola;
    private int cantidadNodos;

    public ListaJugadores() {
    }

    private boolean estaVacia() {
        return cabeza == null;
    }

    public void insertarInicio(Jugador jugador) {
        Nodo nuevo = new Nodo(jugador);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        cantidadNodos++;
    }

    public void insertarFinal(Jugador jugador) {
        Nodo nuevo = new Nodo(jugador);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
        cantidadNodos++;
    }

    public Jugador buscarJugador(int posicion) {
        if (!estaVacia()) {
            Nodo temp = cabeza;
            int pos = 0;
            while (temp != null) {
                if (posicion ==
                        +pos) {
                    return temp.getDato();
                }
                temp = temp.getSiguiente();
                pos++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "El jugador solicitado no existe");
        }
        return null;
    }

    public Jugador edit(String user, String pass, String newUser, String newPass) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.getDato().getUsuario().equals(user)) {
                temp.getDato().setUsuario(newUser);
                temp.getDato().setPassword(newPass);
                return temp.getDato();
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    public void ordenar() {
        if (!estaVacia()) {
            boolean ordenado = false;
            boolean primerRecorrido = true;
            Nodo ultimoOrdenado = cola;
            Nodo actual;
            while (!ordenado) {
                ordenado = true;
                for (actual = cabeza; actual.getSiguiente() != (primerRecorrido == true ? null : ultimoOrdenado); actual = actual.getSiguiente()) {
                    if (actual.getDato().getScore() > actual.getSiguiente().getDato().getScore()) {
                        Jugador temp = actual.getDato();
                        actual.setDato(actual.getSiguiente().getDato());
                        actual.getSiguiente().setDato(temp);
                        ordenado = true;
                    }
                }
            }
        }
    }

    public int size() {
        return cantidadNodos;
    }
}
