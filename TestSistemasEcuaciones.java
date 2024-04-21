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
                double[] solutions = cramer.solveUsingCramer(coeficientes.getMatriz(), constantes);

                // Imprimir la solucion
                System.out.println(Matriz.printVectorSolution(solutions));
                break;
                
            case 2:
                /* Resolver el sistema utilizando Gauss-Jordan */
                // Pendiente actualizar
                
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