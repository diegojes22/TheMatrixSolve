package net.diego.sistemasdeecuaciones;

/**
 * INSTITUTO TECNOLOGICO DE LA PIEDAD
 * 
 * 28 de Abril 2024
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
 *  - CreatePolinomium
 */
public class CreatePolinomium {
    /* Atributos */
    private int n, m;
    private int grado = 0;
    private double[][] points;
    private Matriz matriz;
    
    /* Metodos */
    public CreatePolinomium(double points[][]) {
        setPoints(points);
    }
    
    public void setPoints(double points[][]) {
        this.points = points;
        
        this.n = points.length;
        this.m = points.length+1;
        
        grado = n-1;
        
        matriz = new Matriz(n, m);
    }
    
    /**
     * Establesemos la matriz en base a los puntos
     * dados anteriormente.
     */
    public void setMatriz() {
        // Rellenar matriz de cofactores
        for(int j = 0; j < m-1; j++) {
            for(int i = 0; i < n; i++) {
                matriz.updateMatrizVal(i, j, Math.pow(points[i][0], j));
            }
        }
        
        // Insertar terminos independientes
        for(int i = 0; i < n; i++) {
            matriz.updateMatrizVal(i, m-1, points[i][1]);
        }
    }
    
    /**
     * Obtenemos la matriz resultante de la operacion
     * anteriormente realizada.
     * @return 
     */
    public Matriz getMatrizSystem() {
        return matriz;
    }
    
    /**
     * Metodo estatico para generar una matriz en base a 
     * n puntos.
     * 
     * @param points
     * @return 
     */
    public static Matriz getSystem(double[][] points) {
        CreatePolinomium p = new CreatePolinomium(points);
        p.setMatriz();
        return p.getMatrizSystem();
    }
    
    /**
     * Crear el polinomio en base a los resultados
     * de las variables.
     * 
     * @param vals
     * @return 
     */
    public static String getPolinomium(double[] vals) {
        String px = "";
        
        int i = 0;
        for(i = 0; i < vals.length; i++) {
            if(i == 0) {                  // potencia igual a 0
                px += vals[i]+" + ";
            }
            else if(i == 1) {             // potencia igual a 1
                px += vals[i]+"*x "+" + ";
            }
            else if(i == vals.length-1) { // ultimo valor del polinomio
                px += vals[i]+"*x**"+i;
            }
            else {                        // otro caso
                px += vals[i]+"*x**"+i+" + ";
            }
        }
        
        if(i == 2) {
            px = px.substring(0, px.length() - 1);
            px = px.substring(0, px.length() - 1);
        }
        
        return px;
    }
    
    /**
     * Graficamos un polinomio con un script externo
     * a partir de un STring generado por los
     * procedimientos de la clase.
     * 
     * @param function 
     */
    public static void grapichs(String function) {
        try {
            // Invocar script para graficar
            String command = "python \"C:\\Users\\Diego Muñoz\\Documents\\NetBeansProjects\\SistemasDeEcuaciones\\src\\main\\java\\net\\diego\\sistemasdeecuaciones\\more\\Graficadora.py\" \""+function+"\"";
            Process child = Runtime.getRuntime().exec(command);
        }
        catch (Exception errno) {
            System.out.println("Error desconocido: "+errno.getMessage());
        }
    }
    
    /* TestZone */
    /*public static void main(String[] args) {
        // puntos
        double[][] puntos = {
            {-2, 12},
            {0, 0},
            {2, 12}
        };
        
        // obtener sistema de ecuaciones
        Matriz m = CreatePolinomium.getSystem(puntos);
        
        // resolver y obtener polinomio
        double[] r = MetodoGaussJordan.solve(m);
        String px = CreatePolinomium.getPolinomium(r);
        
        System.out.println(m.printMatriz());
        System.out.println(px);
        
        CreatePolinomium.grapichs(px);
    }*/
}
