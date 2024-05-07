package net.diego.sistemasdeecuaciones;

/**
 * INSTITUTO TECNOLOGICO DE LA PIEDAD
 * 
 * 20 de Abril 2024
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
 *  - MetodoGauss.java -> OK
 */
public class Gauss {
    public static void solve(Matriz matriz) {
        // variables
        double pivote;               // Manejo del pivote
        int pivotePos;
        
        int filas = matriz.getN();   // dimension de la matriz
        int columnas = matriz.getM();
        
        int step = 1; // Observar en que paso vamos
        String method = "Metodo de reduccion Gaussiana\n"; // aqui se guarda el procedimiento
        
        // Imprimir la matriz original
        System.out.println("Matriz original: \n"+matriz.printMatriz());
        method += "Matriz original = \n"+matriz.printMatriz()+"\n\n";
        
        for(pivotePos = 0; pivotePos < columnas - 1; pivotePos++) {
            // Verifivar si el pivote correspondiente es 1
            pivote = matriz.getMatrizVal(pivotePos, pivotePos);
            
            if(pivote != 1) {
                // dividimos los elementos de esa fila entre el pivote
                for(int c = 0; c < columnas; c++) {
                    double valorActual = matriz.getMatrizVal(pivotePos, c);
                    
                    if(valorActual != 0) matriz.updateMatrizVal(pivotePos, c, (valorActual / pivote) );
                }
                
                // imprimir paso
                System.out.println("Paso "+step+" : \n"+matriz.printMatriz());
                method += "Paso "+step+":\n"+matriz.printMatriz()+"\n\n";
                step++;
            }
            
            // convertir los elementos inferiores en 0
            for(int f = pivotePos + 1; f < filas; f++) {
                double factor = matriz.getMatrizVal(f, pivotePos);
                
                for(int c = 0; c < columnas; c++) {
                    double valorActual = matriz.getMatrizVal(f, c);
                    double valorDeArriba = matriz.getMatrizVal(pivotePos, c);
                    double resta = valorActual - factor * valorDeArriba;

                    matriz.updateMatrizVal(f, c, resta);
                }
            }
            
            // imprimir paso
            System.out.println("Paso "+step+" : \n"+matriz.printMatriz());
            method += "Paso "+step+":\n"+matriz.printMatriz()+"\n\n";
            step++;
        }
        
        saveMethod(method);
    }
    
    /* Este metodo esta incompleto */
    public static void extractSolutions(Matriz matriz) {
        int filas = matriz.getN()-1;
        int columnas = matriz.getM()-1;
        
        double val = 0;
        
        for(; filas > 0; filas--) {
            for(; columnas > 0; columnas--) {
                val = matriz.getMatrizVal(filas, columnas);
                
                for(int f = 0; f <= filas; f++) {
                    matriz.updateMatrizVal(f, columnas-1, 
                        val * matriz.getMatrizVal(f, columnas-1)
                    );
                }
            }
        }
        
        System.out.println(val);      
        
        System.out.println("Solucion : \n"+matriz.printMatriz());
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