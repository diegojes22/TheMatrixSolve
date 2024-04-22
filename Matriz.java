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
 *  - Matriz.java
 */

// Posiblemente algunos metodos sean renombrados
public class Matriz {
    /* Atributos */
    private double matriz[][];
    private int n;  // numero de filas
    private int m;  // numero de columnas
    private String nombre = "M";
    
    /* Metodos */
    // Constructores
    
    /**
     * Solo declaramos el tamaño de la matriz
     * 
     * @param n Numero de filas
     * @param m Numero de columas
     */
    public Matriz(int n, int m) {
        this.n = n;
        this.m = m;
        
        matriz = new double[n][m];
    }
    
    /**
     * Podemos inicializar el objeto solo
     * con la matriz que queremos copiar.
     * 
     * @param matriz Matriz a clonar
     */
    public Matriz(double[][] matriz) {
        this.matriz = matriz;
        
        this.n = matriz.length;
        this.m = matriz[0].length;
    }
    
    /**
     * Declaramos el nombre de la matriz y su
     * tamaño.
     * 
     * @param n      Numero de filas
     * @param m      Numero de columnas
     * @param nombre Nombre de la matriz
     */
    public Matriz(int n, int m, String nombre) {
        this.n = n;
        this.m = m;
        
        this.nombre = nombre;
        
        matriz = new double[n][m];
    }
    
    /**
     * Declaramos una matriz con su tamaño, nombre,
     * y le pasamos los valores de otra matriz a 
     * esta.
     * 
     * Nota: Si el tamaño de la matriz que queremos
     * copiar no coincide con el tamaño (los atributos
     * n y m, se mostrara una advertencia y se
     * inicializara otra.
     * 
     * @param n      Numero de filas
     * @param m      Numero de columnas
     * @param nombre Nombre de la matriz
     * @param matriz Matriz "nativa" que queremos clonar
     */
    public Matriz(int n, int m, String nombre, double[][] matriz) {
        this.n = n; 
        this.m = m;
        
        this.nombre = nombre;
        
        if(n != matriz.length) {  // verificar tamaños de la matriz que queremos clonar
            System.err.println("Las filas de la matrices no coinciden con el argumento N");
            System.out.println("RE-inicializando la matriz");
            
            matriz = new double[n][m];
            return;
        }
        
        this.matriz = matriz;
    }
    
    
    // Getters
    /**
     * Devolvemos la matriz en un formato "nativo"
     * de java
     * 
     * @return Matriz
     */
    public double[][] getMatriz() { 
        return matriz; 
    }
    /**
     * Obtenemos el numero de filas.
     * Posiblemente renombremos el metodo
     * 
     * @return 
     */
    public int getN() { 
        return n;
    
    /**
     * Obtenemos el numero de columnas.
     * Posiblemente renombremos el metodo tambien.
     * 
     * @return 
     */}
    public int getM() { return m; }
    
    /**
     * Obtenemos un valor espesifico de la posicion
     * (n, m)
     * 
     * @param n
     * @param m
     * @return 
     */
    public double getMatrizVal(int n, int m) {
        if(n > this.n || m > this.m) {
            return 0;
        }
        
        return matriz[n][m];
    }
    
    // Setters
    /**
     * Establecemos el valor de la matriz mediante
     * una matrz "nativa" de java.
     * 
     * Si se utilizo algun consructor que no inicializa
     * la matriz, se debe utilizar este metodo o el de
     * "actualizarMatriz" si o si.
     * 
     * @param matriz 
     */
    public void setMatriz(double[][] matriz) { this.matriz = matriz; }
    
    /**
     * Convertimos la matriz a un String para de esa
     * forma poderla imprimir en la consola.
     * 
     * @return Devolvemos la matriz en un String 
     */
    public String printMatriz() {
        String str = "";
        
        for(int i = 0; i < n; i++) {
            str += this.printVector(matriz[i])+"\n";
        }
         
        return str;
    }
    
    /**
     * Podemos imprimir cualquier vector de una forma agradable.
     * 
     * @param v  Vector a imprimir
     * @return 
     */
    public static String printVector(double v[]) {
        String str = "[ ";
        
        for(int i = 0; i < v.length; i++) {
            str += v[i] + " ";
        }
        str += " ]";
        
        return str;
    }
    
    /**
     * Tambien imprimimos un vector, solo que este metodo
     * esta diseñado para poder ser impreso de forma que
     * se vea como una respuesta de un sistema de ecuaciones.
     * 
     * @param v  Vector a imprimir
     * @return 
     */
    public static String printVectorSolution(double v[]) {
        String str = "";
        
        for(int i = 0; i < v.length; i++) {
            str += "x" + (i+1) + " = " + v[i] + "\n";
        }
        
        return str;
    }
    
    /**
     * Nos permite cambiar un valor en especifico
     * de la matriz.
     * 
     * Podemos utilizar este metodo con algun ciclo
     * para poder inicializar la matriz o actualizarla
     * toda.
     * 
     * @param n    En que fila esta el elemento a cambiar
     * @param m    En que columna esta el elemento a cambiar
     * @param val  Nuevo valor
     * 
     * @return 
     */
    public boolean updateMatrizVal(int n, int m, double val) {
        try {
            this.matriz[n][m] = val;
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Obtenemos la dimension de la matriz 
     * en formato String para imprimirlo en
     * consola.
     * 
     * No es muy util.
     * 
     * @return Devolvemos la dimension en String 
     */
    public String getMatrixSize() {
        String str = "Filas = " + n + ", Columnas = " + m + "\n";
        return str;
    }
    
    /**
     * Este metodo no se porque esta aqui :)
     * pero igualmente lo pongo.
     * 
     * Pueden quitarlo si quieren.
     * @return 
     */
    @Override 
    public String toString() {
        return "Datos de la matriz = {\n"+getMatrixSize()+"\n"+printMatriz()+"}";
    }
    
}
