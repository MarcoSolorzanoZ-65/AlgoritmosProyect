package org.Huffman;

import java.util.PriorityQueue;

/**
 * @author : marco
 * @created : 12/6/2022, Tuesday
 **/

public class HuffmanCompresser {
    HuffmanNode root = null;

    public static void printCode(HuffmanNode root, String s) {
        if (root != null) {
            //se verifica si tiene hijos y si almacena una letra entonces se imprime, sino sigue
            if (root.left == null && root.right == null && Character.isLetter(root.c)) {
                System.out.println(root.c + ":" + s);
                return;
            }
            printCode(root.left, s + "0");
            printCode(root.right, s + "1");
        }
    }

    public void compress(String text) {
        //convierte el texto en un array de caracteres y en otro que se utiliza para contar la fecuencia
        int n = text.length();
        //Array para comprimir
        char[] charArray = text.toLowerCase().toCharArray();
        //Array utilizado para la fecuencia
        char[] charArrayVerify = text.toCharArray();
        //aqui se almacena la frecuencia de cada letra
        int[] charfreq = new int[n];

        //Cuenta la frecuencia de cada caracter
        for(int i = 0; i <text.length(); i++) {
            charfreq[i] = 1;
            for(int j = i+1; j <text.length(); j++) {
                if(charArrayVerify[i] == charArrayVerify[j]) {
                    charfreq[i]++;
                    charArrayVerify[j] = '0';
                }
            }
        }
        //Se crea la cola de prioridad
        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator());
        for (int i = 0; i < n; i++) {
            // se crea un nuevo un nodo y se agrega a la cola de prioridad
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.data = charfreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }
        while (q.size() > 1) {
            //se sacan de la cola de prioridad y se igualan al nodo
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();

            // se crea un nuevo nodo para ir haciendo las sumas de la frecuencia
            HuffmanNode f = new HuffmanNode();

            // se hacen las sumas de la frecuencia
            f.data = x.data + y.data;
            f.c = '-';

            // y se ponen los nodos que se sumaron como hijos del nodo sumado
            f.left = x;
            f.right = y;

            // se declara root como el nodo con las frecuencias sumadas
            root = f;

            // agrega el nuevo nodo sumado a la cola de prioridad
            q.add(f);
        }
        // imprime recorriendo recursivamente el arbol
        printCode(root, "");
    }
}
