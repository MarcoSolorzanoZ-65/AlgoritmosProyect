package org.PlayerRelated;

import javax.swing.*;

public class ListaJugadores {

    private Nodo cabeza, cola;
    private int cantidadNodos;

    public ListaJugadores() {
    }

    public Nodo getCabeza() {
        return cabeza;
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
                if (posicion == pos) {
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

    public Jugador edit(String user, String pass, String newPass) {
        Nodo temp = cabeza;
        boolean salir = true;
        while (temp != null && salir == true) {
            if (temp.getDato().getUsuario().equals(user) && temp.getDato().getPassword().equals(pass)) {
                temp.getDato().setPassword(newPass);
                return temp.getDato();
            }else{
                JOptionPane.showMessageDialog(null, "Contraseña antigua invalida!");
                salir = false;
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    public void ordenar(Nodo start)
    {
        int swapped, i;
        Nodo ptr1;
        Nodo lptr = null;
        if (!estaVacia()) {
            do {
                swapped = 0;
                ptr1 = start;
                while (ptr1.getSiguiente() != lptr) {
                    if (ptr1.getDato().getScore() < ptr1.getSiguiente().getDato().getScore()) {
                        Jugador t = ptr1.getDato();
                        ptr1.setDato(ptr1.getSiguiente().getDato());
                        ptr1.getSiguiente().setDato(t);
                        swapped = 1;
                    }
                    ptr1 = ptr1.getSiguiente();
                }
                lptr = ptr1;
            }
            while (swapped != 0);
        }
    }

    public int size() {
        return cantidadNodos;
    }
}
