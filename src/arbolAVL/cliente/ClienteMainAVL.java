/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolAVL.cliente;

import arbolAVL.modelo.Entero;
import arbolAVL.modelo.ArbolAVL;

/**
 *
 * @authors Andres Torres Ciceri y Edwin Orlando Restrepo Mosquera
 */
import arbolAVL.graficar.BTreePrinter;
import java.util.Scanner;

public class ClienteMainAVL {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        ArbolAVL abo = new ArbolAVL();
        //20, 10, 40, 50, 90, 30, 60 y 70.
        boolean imprimir;
        boolean salir = true;
        int num = 0;
        int numero = 0;
        try {
            abo.insertar(new Entero(20));
            abo.insertar(new Entero(10));
            abo.insertar(new Entero(40));
            abo.insertar(new Entero(50));
            abo.insertar(new Entero(90));
            abo.insertar(new Entero(30));
            abo.insertar(new Entero(60));
            abo.insertar(new Entero(70));

            //prueba con valores menores                                    50
//            abo.insertar(new Entero(50));                                /
//            abo.insertar(new Entero(40));                               40
//            abo.insertar(new Entero(30));                               /
//                                                                       30


            //prueba con valores combinados                               10
//            abo.insertar(new Entero(10));                                  \
//            abo.insertar(new Entero(50));                                   50
//            abo.insertar(new Entero(20));                                 /
//                                                                           20
            while (salir == true) {
                System.out.println("\n\nMenu:\n"
                        + "1. Insertar nodos \n"
                        + "2. Encontrar tio de un nodo dado\n"
                        + "3. Encontrar abuelo de un nodo dado\n"
                        + "4. Imprimir arbol RN\n"
                        + "5. Salir\n"
                        + "Opción:\n");
                Scanner leer = new Scanner(System.in);

                switch (leer.nextInt()) {
                    case 1:
                        System.out.println("\n\n¿Cuantos nodos desea insertar?");
                        numero = leer.nextInt();

                        for (int i = 0; i < numero; i++) {
                            System.out.println("\nInserte el valor del nodo #" + (i + 1));
                            num = leer.nextInt();
                            abo.insertar(new Entero(num));
                        }
                        break;
                    case 2:
                        System.out.println("\n\nInserte el valor del nodo del cual desea encontrar su nodo tio");
                        numero = leer.nextInt();
                        if (abo.imprimirTio(new Entero(numero), abo.getRaiz(), true) == true) {
                        } else {
                            System.out.println("\nNodo no encontrado");
                        }
                        break;
                    case 3:
                        System.out.println("\n\nInserte el valor del nodo del cual desea encontrar su nodo abuelo");
                        numero = leer.nextInt();
                        if (abo.imprimirAbuelo(new Entero(numero), abo.getRaiz(), true) == true) {
                        } else {
                            System.out.println("\nNodo no encontrado");
                        }
                        break;
                    case 4:
                        imprimir = true;
                        if (abo.getRaiz() != null) {
                            BTreePrinter.printNode(abo.getRaiz());
                        } else {
                            System.out.println("\n\nEl árbol está vacío.");
                        }
                        break;
                    case 5:
                        salir = false;
                }
            }
        } catch (Exception e) {
            System.out.println("\nError:" + e.getMessage());
        }
    }
}
