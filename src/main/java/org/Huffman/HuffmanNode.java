package org.Huffman;

/**
 * @author : marco
 * @created : 12/6/2022, Tuesday
 **/

public class HuffmanNode {

    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }
}
