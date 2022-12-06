package org.PlayerRelated;

import javax.swing.*;

public class ListaHistoriales {
    private NodoH cabeza, cola;
    private int cantidadNodos;

    public ListaHistoriales() {
    }

    public NodoH getCabeza() {
        return cabeza;
    }

    private boolean estaVacia() {
        return cabeza == null;
    }

    public void insertarInicio(Historial historial) {
        NodoH nuevo = new NodoH(historial);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        cantidadNodos++;
    }

    public void insertarFinal(Historial historial) {
        NodoH nuevo = new NodoH(historial);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
        cantidadNodos++;
    }

    public Historial buscarHistorial(int posicion) {
        if (!estaVacia()) {
            NodoH temp = cabeza;
            int pos = 0;
            while (temp != null) {
                if (posicion == pos) {
                    return temp.getDato();
                }
                temp = temp.getSiguiente();
                pos++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "El historial solicitado no existe");
        }
        return null;
    }

    public void ordenar(NodoH start) {
        int swapped, i;
        NodoH ptr1;
        NodoH lptr = null;
        if (!estaVacia()) {
            do {
                swapped = 0;
                ptr1 = start;
                while (ptr1.getSiguiente() != lptr) {
                    if (ptr1.getDato().getPuntacionJuego() < ptr1.getSiguiente().getDato().getPuntacionJuego()) {
                        Historial t = ptr1.getDato();
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

