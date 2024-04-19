package net.diego.sistemasdeecuaciones;

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
 *  - MetodoGaussJordan.java -> OK
 */

public class MetodoGaussJordan {
    public static double[] solveUsingGaussJordan(double[][] augmentedMatrix) {
        int numRows = augmentedMatrix.length;     // Filas    -> n
        int numCols = augmentedMatrix[0].length;  // Columnas -> m
        
        // Imprimir la matriz original de coeficientes
        // Nota: Simplificar esta parte
        System.out.println("\nMatriz de coeficientes");
        for(int f = 0; f < augmentedMatrix.length; f++){
            for(int c = 0; c < augmentedMatrix.length; c++){
                System.out.print((int)augmentedMatrix[c][f]+"\t");
            }
            System.out.println("");
        }
        System.out.println("");
        
        
        // Aplicar el método de Gauss-Jordan
        for (int pivot = 0; pivot < numRows; pivot++) {
            // Hacer el pivote igual a 1
            double pivotValue = augmentedMatrix[pivot][pivot];
            
            for (int j = 0; j < numCols; j++) {
                augmentedMatrix[pivot][j] /= pivotValue;
            }

            // Hacer ceros en las demás filas para la columna actual
            for (int i = 0; i < numRows; i++) {
                if (i != pivot) {
                    double factor = augmentedMatrix[i][pivot];
                    
                    for (int j = 0; j < numCols; j++) {
                        augmentedMatrix[i][j] -= factor * augmentedMatrix[pivot][j];
                    }
                }
            }
        }

        // Extraer las soluciones
        double[] solutions = new double[numRows];
        for (int i = 0; i < numRows; i++) {
            solutions[i] = augmentedMatrix[i][numCols - 1];
        }
        
        //Imprimir la matriz identidad mas los terminos independientes
        // Nota: La impresion debe ser simplificada
        System.out.println("");
        System.out.println("Identidad identidad + ahumentada");
        
        for(int f=0;f<augmentedMatrix.length;f++){
            for(int c=0;c<augmentedMatrix.length;c++){
                System.out.print((int)augmentedMatrix[c][f]+"\t");
            }
            System.out.print("["+solutions[f]+"]");
            System.out.println("");
        }
        System.out.println("");
        
        return solutions;
    }
}