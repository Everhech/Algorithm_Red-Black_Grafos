package arbolAVL.graficar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import arbolAVL.modelo.Nodo;

/**
 * Esta clase imprime por consola la estructura de un arbol binario
 *
 *
 * Adaptado de:
 * http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 *
 * Forma de usarlo: ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda(); ...
 * BTreePrinter.printNode(arbol.getRaiz());
 */
public class BTreePrinter {

    public static void printNode(Nodo root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);

    }

    private static void printNodeInternal(List<Nodo> nodes, int level,
            int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes)) {
            return;
        }

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Nodo> newNodes = new ArrayList<Nodo>();
        for (Nodo node : nodes) {
            if (node != null) {

                 
                if (node.getColor() == true) {
                    System.out.print("\033[31m" + node.getValor());
                }
                if (node.getColor() == false) {
                    System.out.print("\u001B[30m"+node.getValor());
                }

                newNodes.add(node.getIzquierdo());
                newNodes.add(node.getDerecho());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i
                            + 1);
                    continue;
                }

                if (nodes.get(j).getIzquierdo() != null) {
                    System.out.print("/");
                } else {
                    BTreePrinter.printWhitespaces(1);
                }

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getDerecho() != null) {
                    System.out.print("\\");
                } else {
                    BTreePrinter.printWhitespaces(1);
                }

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    static int maxLevel(Nodo node) {
        if (node == null) {
            return 0;
        }

        return Math.max(BTreePrinter.maxLevel(node.getIzquierdo()),
                BTreePrinter.maxLevel(node.getDerecho())) + 1;
    }

    static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null) {
                return false;
            }
        }

        return true;
    }

}
