package net.diego.sistemasdeecuaciones;

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
        
        Scanner input=new Scanner(System.in);
        int opcion;
        
        // Menu Inicio
        System.out.println("Selecciona el metodo a utilizar...");
        System.out.println("[1] Mertodo de Cramer");
        System.out.println("[2] Metodo de Gauss-Jordan");
        System.out.println("[3] Metodo de eliminacoion Gaussiana");
        opcion=input.nextInt();
        
        switch(opcion){
            case 1:
                /* Resolver por metodo de Cramer (Solo puede ser matriz cuadrada)*/
                
                System.out.print("Ingrese el número de ecuaciones: ");
                int n = input.nextInt();

                // Declarar matrices nesesarias para la operacion
                double[][] coefficients = new double[n][n];  // Matriz de coeficientes
                double[] constants = new double[n];          // Vector de resultados

                // Solicitar los coeficientes y constantes
                System.out.println("Ingrese los coeficientes de las ecuaciones:");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print("Ingrese el coeficiente de X" + (j + 1) + " en la ecuación " + (i + 1) + ": ");
                        coefficients[i][j] = input.nextDouble();
                    }
                    System.out.print("Ingrese la constante en la ecuación (termino independiente) " + (i + 1) + ": ");
                    constants[i] = input.nextDouble();
                }
                
                // Aplicar el metodo
                double[] solutions = cramer.solveUsingCramer(coefficients, constants);

                // Imprimir la solucion
                System.out.println("Soluciones:");
                for (int i = 0; i < n; i++) {
                    System.out.println("x" + (i + 1) + " = " + solutions[i]);
                }
                break;
                
            case 2:
                /* Resolver el sistema utilizando Gauss-Jordan */
                
                System.out.print("Ingrese el número de ecuaciones: ");
                int numRows = input.nextInt();
                int numCols = numRows + 1; // Número de columnas, incluyendo la columna de constantes o columna extendida

                double[][] augmentedMatrix = new double[numRows][numCols]; // decarar matriz

                // Solicitar los coeficientes y constantes
                System.out.println("Ingrese los coeficientes de las ecuaciones:");
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        System.out.print("Ingrese el elemento (" + (i + 1) + "," + (j + 1) + "): ");
                        augmentedMatrix[i][j] = input.nextDouble();
                    }
                }

                // Resolver utilizando el método de Gauss-Jordan
                solutions = MetodoGaussJordan.solveUsingGaussJordan(augmentedMatrix);

                // Mostrar soluciones
                System.out.println("Soluciones:");
                for (int i = 0; i < numRows; i++) {
                    System.out.println("x" + (i + 1) + " = " + solutions[i]);
                }
                break;
                
            case 3:
                /* Metodo de eliminacion Gaussiana */
                System.out.println("Proximamente ...");
                break;
                
            default:
                System.out.println("Lo siento pero la opcion todavia no esta implementada o no existe");
                break;
        }
    }
}