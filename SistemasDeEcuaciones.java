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
 *  - TestMatriz.java -> Archivo de pruevas
 */

// El siguiente programa solo es un ejemplo de
// como es que funciona la clase Matriz y 
// experimentar con dicha clase, encontrar posibles
// fallos, carencias, mejoras, etc...

// Este archivo pueda servir como una plantilla
// para lo que sera el programa en algun futuro,
// pero la idea es que solo sea una prueva
public class SistemasDeEcuaciones {

    public static void main(String[] args) {
        // Variables
        Matriz matriz = null;
        
        int opc = 0;
        int matrixType;
        Scanner in = new Scanner(System.in);
        
        // Parametros de la matriz
        String nombreMatriz;
        int n = 0;
        int m = 0;
        double value;
        
        // MainLoop
        do {
            // Menu de inicio
            opc = Menu.run(in, "..:: Inicio ::..", new String[]{
                "Salir",
                "Crear matriz",
                "Ver matriz",
                "Resolver con metodo de Gauss",
                "Resolver con metodo de Gauss-Jordan",
                "Resolver con metodo de Grammer"
            });
            
            // Opciones
            switch(opc) {
                // Crear Matriz
                case 1:
                    do {
                        matrixType = Menu.run(in, "..:: Crear matriz ::..", new String[]{
                            "Volver",
                            "Matriz 2x2",
                            "Matriz 3x3",
                            "Otro"
                        });
                        
                        switch(matrixType) {
                            // Matriz de 2x2
                            case 1:
                                n = 2;
                                m = 2;
                                break;
                                
                            // Matriz de 3x3
                            case 2:
                                n = 3;
                                m = 3;
                                break;
                                
                            // Otro tipo de matriz
                            case 3:
                                System.out.print("Ingrese el numero de filas (n): ");
                                n = in.nextInt();
                                System.out.print("Ingrese el numero de columnas (m): ");
                                m = in.nextInt();
                                
                                break;
                        }
                        
                        // Opciones de acuerdo al menu
                        if(matrixType == 0) {
                            System.out.println("Saliendo...");
                            break;
                        }
                        
                        // El if de esta seccion es para evitar errores en caso
                        // de que el usuario alla elegido otra opcion como 5, 8,
                        // o -1, si es asi, no declaramos la matriz ni pedimos
                        // datos que luego no coincidan, lo que generaria un error.
                        if(matrixType > 0 && matrixType < 4) {
                            // Pedir el nombre
                            System.out.print("Ingrese el nombre de la matriz: ");
                            nombreMatriz = in.next();
                            
                            // Inicializamos la matriz de acuerdo a
                            // como lo eligio el ususario
                            matriz = new Matriz(n, m, nombreMatriz);

                            // LLenar matriz
                            for(int i = 0; i < n; i++) {
                                for(int j = 0; j < m; j++) {
                                    System.out.print("Ingrese el valor "+i+", "+j+" : ");
                                    value = in.nextDouble();

                                    if(!matriz.updateMatrizVal(i, j, value)) {  // Esto esta en revision
                                        System.out.println("Error");
                                    }

                                    System.out.println(matriz.printMatriz());
                                }
                            }
                        }
                        
                    } while(matrixType != 0);
                    
                    break;
                    
                // Ver matriz
                case 2:
                    System.out.println("..:: Ver Matriz ::..");
                    
                    if(matriz == null) {
                        System.out.println("Error: No hay ninguna matriz inicializada!");
                        break;
                    }
                    System.out.println(matriz.printMatriz());
                    break;
                    
                // Default para opciones no implementadas o que no existen
                default:
                    System.out.println("Lo siento pero la opcion no existe o aun no ha sido implementada :(");
                    break;
            }
        }while(opc != 0);
        
        in.close();
    }
}
