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
 *  - MetodoCramer.java -> OK
 */

public class MetodoCramer {
    /**
     * 
     * @param coefficients Le pasamos la matriz de cofactores (los valores de las variables)
     * @param constants    Le pasamos el vector con terminos independientes (los resultados)
     * @return 
     */
    public static double[] solveUsingCramer(double[][] coefficients, double[] constants) {
        int n = constants.length;            // Numero de filas
        double[] solutions = new double[n];  // Vector de soluciones
        
        double determinant = calculateDeterminant(coefficients); // calculamos la determinante de la matriz de coeficientes
        
        if (determinant == 0) {
            // Si la determinante es 0, no se puede resolver con el metodo de Cramer
            System.out.println("El sistema tiene infinitas soluciones o ninguna solución única.");
            return null;
        }
        
        for (int i=0;i<n;i++) {
            // Resolver con Cramer
            
            double[][] tempCoefficients = new double[n][n]; // Matriz temporal
            
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (k == i) {
                        tempCoefficients[j][k] = constants[j];
                    } else {
                        tempCoefficients[j][k] = coefficients[j][k];
                    }
                }
            }
            
            double tempDeterminant = calculateDeterminant(tempCoefficients); // determinante de la matriz temporal
            solutions[i] = tempDeterminant/determinant; // Agregar la solucion al vector de soluciones
        }
        
        return solutions;
    }
    
    /**
     * Metodo para calcular la determinante de una matriz.
     * Solo aplica para matrices cuadradas.
     * 
     * @param matrix
     * @return 
     */
    public static double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;    // Numero de filas de la matriz
        double determinant = 0;   // Aqui se guarda el determinante
        
        // Condiciones para obtener el determinante
        if (n == 1) {
            // Si el numero de filas es 1, el valor de la determinante 
            // sera lo que este en la posicion (0, 0)
            determinant = matrix[0][0];
        } 
        
        else if (n == 2) {
            // Regla para calcular la determinante de una matriz 2x2:
            // Determinate = (a * d) - (b * c)
            determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } 
        
        else {
            // Intentar obtener la determinante si la matriz es mas grande
            // con el metodo de la matriz extendida (supongo)
            for (int i = 0; i < n; i++) {
                double[][] temp = new double[n - 1][n - 1];
                
                for (int j = 1; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (k < i) {
                            temp[j - 1][k] = matrix[j][k];
                        } else if (k > i) {
                            temp[j - 1][k - 1] = matrix[j][k];
                        }
                    }
                }
                determinant += matrix[0][i] * Math.pow(-1, i) * calculateDeterminant(temp);
            }
        }
        
        return determinant;
    }
}