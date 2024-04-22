package net.diego.sistemasdeecuaciones;

import DUtils.src.*;
import java.util.Scanner;

/**
 * INSTITUTO TECNOLOGICO DE LA PIEDAD
 * 
 * 18 de Abril 2024
 * 
 * PROYECTO: THE-MATRIX-SOLVER
 * 
 * Grupo: 2-C
 * Equipo:
 *  - El Adrian
 *  - Juan Carlos
 *  - Diego
 * 
 * Archivo:
 *  - TestSistemasEcuaciones -> sujeto a cambios pero posible candidato
 */

public class TestSistemasEcuaciones {
    public static void  main(String args[]){
        // Variables
        MetodoCramer cramer = new MetodoCramer();
        MetodoGaussJordan MetodoGaussJordan =new MetodoGaussJordan();
        
        Matriz matriz = null;
        
        Scanner input=new Scanner(System.in);
        int opcion;
        
        // Menu Inicio
        opcion = Menu.run(input, "..:: Seleccione el metodo a utilizar ::..", new String[]{
            "Salir", 
            "Metodo de Cramer",
            "Metodo de Gauss-Jordan",
            "Metodo de eliminacion Gaussiana (proximamente)"
        });
        
        switch(opcion){
            case 1:
                /* Metodo de Grammer */
                System.out.print("Ingrese el numero de ecuaciones: "); int n = input.nextInt();
                
                Matriz coeficientes = new Matriz(n, n, "Matriz de coeficientes");
                double[] constantes = new double[n];
                
                // LLenar matriz
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < n; j++) {
                        // Valor de las filas
                        System.out.print("Ingrese el valor "+i+", "+j+" : ");
                        coeficientes.updateMatrizVal(i, j, input.nextDouble());
                        System.out.println(coeficientes.printMatriz());
                    }
                    // valor del termino independiente
                    System.out.print("Ingrese el valor del termino independiente: ");
                    constantes[i] = input.nextDouble();
                }
                
                // Aplicar el metodo para la solucion
                double[] solutions = cramer.solve(coeficientes, constantes);

                // Imprimir la solucion
                System.out.println(Matriz.printVectorSolution(solutions));
                break;
                
            case 2:
                /* Resolver el sistema utilizando Gauss-Jordan */
                System.out.print("Ingrese el número de ecuaciones: ");
                int numRows = input.nextInt();
                int numCols = numRows + 1; // Número de columnas, incluyendo la columna de constantes o columna extendida

                //double[][] augmentedMatrix = new double[numRows][numCols]; // decarar matriz
                matriz = new Matriz(numRows, numCols, "Metodo Gauss-Jordan");

                // Solicitar los coeficientes y constantes
                System.out.println("Ingrese los coeficientes de las ecuaciones:");
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        System.out.print("Ingrese el elemento (" + (i + 1) + "," + (j + 1) + "): ");
                        matriz.updateMatrizVal(i, j, input.nextDouble());
                    }
                }

                // Resolver utilizando el método de Gauss-Jordan
                solutions = MetodoGaussJordan.solve(matriz);

                break;
                
            case 3:
                /* Metodo de eliminacion Gaussiana */
                System.out.print("Ingrese el numero de ecuaciones: ");
                int filas = input.nextInt();
                int columnas = filas + 1;
                
                matriz = new Matriz(filas, columnas, "Reduccion Gaussiana");
                
                // solicitar valores de la matriz
                System.out.println("Ingrese los coeficiente de las ecuaciones: ");
                for(int i = 0; i < filas; i++) {
                    for(int j = 0; j < columnas; j++) {
                        System.out.print("\tIngrese el elemento "+(i+1)+", "+(j+1)+": ");
                        matriz.updateMatrizVal(i, j, input.nextDouble());
                    }
                }
                
                Gauss.solve(matriz);
                
                break;
                
            default:
                System.out.println("Lo siento pero la opcion todavia no esta implementada o no existe");
                break;
        }
    }
}