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
 *  - Matriz.java -> Sujeto a cambios
 */
public class Matriz {
    /* Atributos */
    private double matriz[][];
    private int n;  // numero de filas
    private int m;  // numero de columnas
    private String nombre = "M";
    
    // La matriz "Resultado" esta pensada para poder
    // ser reuelta por alguno de los metodos, ya sea
    // de grammer, gauss o gauss-jordan.
    private Matriz resultado;
    
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
        String str = "[\n";
        
        // Busqueda de los elementos
        for(int i = 0; i < n; i++) {
            str += "\t";
            for(int j = 0; j < m; j++) {
                str += matriz[i][j] + " ";
            }
            
            str += "\n";
        }
        
        str += ']'; 
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
     * Nos permite actualizar la matriz "resultado"
     * con el fin de poder observar el procedimiento
     * de resolucion con algun metodo.
     * 
     * @param newMatrix 
     */
    public void actualizarResultado(Matriz newMatrix) {
        this.resultado = newMatrix;
    }
    
    /**
     * Mostramos los valores que tiene la matriz
     * "resultado". Util cuando se resuelva la
     * matriz original con algun metodo como
     * de Grammer o Gaussiano.
     * 
     * @return 
     */
    public String imprimirResultado() {
        String str = "[\n";
        
        // Busqueda de los elementos
        for(int i = 0; i < resultado.n; i++) {
            str += "\t";
            
            for(int j = 0; j < resultado.m; j++) {
                str += matriz[i][j] + " ";
            }
            
            str += "\n";
        }
        
        str += ']'; 
        return str;
    }
    
    /**
     * Obtenemos la dimension de la matriz 
     * en formato String para imprimirlo en
     * consola
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
