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
    public static double[] solve(Matriz coeficientes, double[] constantes) {
        // Variables
        int step = 1;
        String method = "Metodo de Cramer:\n";
        
        int n = constantes.length;
        double[] soluciones = new double[n];
        
        double determinante = calculateDeterminant(coeficientes.getMatriz()); // Calcular la determinante de la matriz

        // Si la determinante es 0, no se puede resolver con el metodo de Cramer
        if(determinante == 0) {
            System.out.println("El sistema tiene una infinidad de soluciones...");
            saveMethod("El sistema de ecuaciones tiene una infinidad de soluciones o no tiene solucion.");
            
            return null;
        }

        // Ciclo de resolucion
        for(int i = 0; i < n; i++) {
            Matriz temp = new Matriz(n,n,"Matriz temporal");

            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    if(k == i) {
                        // Remplazar la columna requerida por los terminos independientes
                        temp.updateMatrizVal(j, k, constantes[j]);
                    }
                    else {
                        // dejar las otras columnas igual, dependiendo del paso actual
                        temp.updateMatrizVal(j, k, coeficientes.getMatrizVal(j, k));
                    }
                }
                
                method += "Paso "+step+":\n"+temp.printMatriz()+"\n\n";
                step++;
            }

            double detTemp = calculateDeterminant(temp.getMatriz()); // determinante temporal del paso actual
            soluciones[i] = detTemp / determinante;
        }
        
        saveMethod(method);
        return soluciones;
    }
    
    /**
     * @deprecated
     * @param coefficients
     * @param constants
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
    
    /**
     * Guardamos el procedimiento
     * @param method 
     */
    private static void saveMethod(String method) {
        AppLog log = new AppLog();
        log.write(method);
        
        log = null;
    }
}