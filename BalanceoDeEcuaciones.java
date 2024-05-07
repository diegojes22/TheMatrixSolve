package net.diego.sistemasdeecuaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BalanceoDeEcuaciones {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        // Solicitar al usuario que introduzca la ecuación química
        System.out.println("Introduce la ecuación química:");
        String ecuacion = entrada.nextLine();

        // Dividir los reactantes de los productos utilizando el "=" como split
        String[] splitReaPro = ecuacion.split("=");

        String reactantes = splitReaPro[0].trim(); // Reactantes
        String productos = splitReaPro[1].trim(); // Productos

        // Dividir los reactantes y productos en compuestos con el "+" como split
        String[] reactantesCompuestos = reactantes.split("\\+");
        String[] productosCompuestos = productos.split("\\+");

        // Obtener todos los elementos presentes en la ecuación
        List<String> elementos = obtenerElementos(reactantesCompuestos, productosCompuestos);

        // Crear matriz de coeficientes
        double[][] matrizCoeficientes = crearMatrizCoeficientes(reactantesCompuestos, productosCompuestos, elementos);

        // Imprimir la matriz de coeficientes
        imprimirMatriz(matrizCoeficientes);
        
        //eliminacionGaussiana(matrizCoeficientes);
        //System.out.println(MetodoGaussJordan.solveUsingGaussJordan(matrizCoeficientes));
        
    }

    // Método para obtener todos los elementos presentes en la ecuación
    public static List<String> obtenerElementos(String[] reactantesCompuestos, String[] productosCompuestos) {
        List<String> elementos = new ArrayList<>();
        
        // Procesar los reactantes
        for (String compuesto : reactantesCompuestos) {
            procesarCompuesto(compuesto, elementos);
        }
        
        // Procesar los productos
        for (String compuesto : productosCompuestos) {
            procesarCompuesto(compuesto, elementos);
        }
        
        return elementos;
    }

    // Método para procesar un compuesto y agregar sus elementos a la lista
    public static void procesarCompuesto(String compuesto, List<String> elementos) {
        // Patrón para extraer los elementos y sus subíndices
        Pattern pattern = Pattern.compile("([A-Z][a-z]*)(\\d*)");
        Matcher matcher = pattern.matcher(compuesto);
        
        // Iterar sobre los elementos del compuesto
        while (matcher.find()) {
            String elemento = matcher.group(1); // Símbolo del elemento
            if (!elementos.contains(elemento)) {
                elementos.add(elemento);
            }
        }
    }

    // Método para crear la matriz de coeficientes
    public static double[][] crearMatrizCoeficientes(String[] reactantesCompuestos, String[] productosCompuestos, List<String> elementos) {
        int numElementos = elementos.size();
        double[][] matrizCoeficientes = new double[numElementos][reactantesCompuestos.length + productosCompuestos.length];
        
        // Llenar la matriz con los coeficientes de los reactantes
        for (int i = 0; i < reactantesCompuestos.length; i++) {
            Map<String, Integer> coeficientesCompuesto = obtenerCoeficientesCompuesto(reactantesCompuestos[i]);
            for (int j = 0; j < numElementos; j++) {
                String elemento = elementos.get(j);
                matrizCoeficientes[j][i] = coeficientesCompuesto.getOrDefault(elemento, 0);
            }
        }
        
        // Llenar la matriz con los coeficientes de los productos
        for (int i = 0; i < productosCompuestos.length; i++) {
            Map<String, Integer> coeficientesCompuesto = obtenerCoeficientesCompuesto(productosCompuestos[i]);
            for (int j = 0; j < numElementos; j++) {
                String elemento = elementos.get(j);
                matrizCoeficientes[j][reactantesCompuestos.length + i] = -coeficientesCompuesto.getOrDefault(elemento, 0);
            }
        }
        
        return matrizCoeficientes;
    }

    // Método para obtener los coeficientes de un compuesto
    public static Map<String, Integer> obtenerCoeficientesCompuesto(String compuesto) {
        Map<String, Integer> coeficientesCompuesto = new HashMap<>();
        
        // Patrón para extraer los elementos y sus subíndices
        Pattern pattern = Pattern.compile("([A-Z][a-z]*)(\\d*)");
        Matcher matcher = pattern.matcher(compuesto);
        
        // Iterar sobre los elementos del compuesto
        while (matcher.find()) {
            String elemento = matcher.group(1); // Símbolo del elemento
            int coeficiente = obtenerCoeficiente(matcher.group(2)); // Coeficiente del elemento
            coeficientesCompuesto.put(elemento, coeficiente);
        }
        
        return coeficientesCompuesto;
    }

    // Método para obtener el coeficiente de un elemento
    public static int obtenerCoeficiente(String subindice) {
        if (subindice.isEmpty()) {
            return 1; // Si no hay subíndice, el coeficiente es 1
        } else {
            return Integer.parseInt(subindice); // Utilizamos el subíndice como coeficiente
        }
    }

    // Método para imprimir una matriz de coeficientes
    public static void imprimirMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //Metodo para resolver por medio de eliminacion Gaussiana
    public static void eliminacionGaussiana(double[][] matrizCoeficientes) {
        
    }

}