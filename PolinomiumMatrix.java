package net.diego.sistemasdeecuaciones;

import javax.swing.*;
import java.awt.event.*;

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
 *
 */
public class PolinomiumMatrix extends JFrame implements ActionListener, ItemListener{
    /* Atributos */
    private int grade;
    private boolean pointsExists = false;
    private double[][] points;
    private boolean inProcess = false;
    
    private Matriz matriz;
    private String fx;
    
    private int minPoints = 2;
    private int maxPoints = 10;
    
    // Widgets
    JLabel tagForXInput;
    JLabel tagForYInput;
    JTextField inputs[][];
    JLabel tagForPolinomium;
    
    JButton configPoints;
    JButton clearPoints;
    JButton graphics;
    JButton viewMethod;
    
    JComboBox methods;
    
    JLabel[][] outSystemMatriz;
    
    /* Metodos */
    // Constructor
    public PolinomiumMatrix() {
        configWin();
        
        initWidgets();
        configWidgets();
        buttonEvents();
        addWidgets();
    }
    
    /**
     * Configurar aspectos basicos de la ventana, como
     * el tamaño, titulo, etc.
     */
    private void configWin() {
        this.setLayout(null);
        this.setBounds(10, 10, 500, 450);
        this.setVisible(true);
        this.setResizable(false);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.repaint();
    }
    
    /**
     * Inicializar todos los widgets
     */
    private void initWidgets() {
        tagForXInput = new JLabel("X");
        tagForYInput = new JLabel("Y");
        
        configPoints = new JButton("Nuevo");
        clearPoints = new JButton("Eliminar");
        
        methods = new JComboBox();
        
        graphics = new JButton("Graficar");
        viewMethod = new JButton("Procedimiento");
        tagForPolinomium = new JLabel("f(x) = ");
    }
    
    /**
     * Ajustar y estilizar los widgets
     */
    private void configWidgets() {
        tagForXInput.setBounds(10, 0, 90, 20);
        tagForYInput.setBounds(110, 0, 90, 20);
        
        configPoints.setBounds(10, 270, 90, 20);
        clearPoints.setBounds(100, 270, 90, 20);
        
        methods.setBounds(10, 300, 190, 20);
        
        graphics.setBounds(200, 270, 180, 20);
        viewMethod.setBounds(200, 300, 180, 20);
        tagForPolinomium.setBounds(200, 350, 200, 20);
    }
    
    /**
     * Agregar funcionalidad a los botones
     */
    private void buttonEvents() {
        configPoints.addActionListener(this);
        clearPoints.addActionListener(this);
        
        graphics.addActionListener(this);
        viewMethod.addActionListener(this);
        
        methods.addItem("seleccionar...");
        methods.addItem("Gauss");
        methods.addItem("Gauss-Jordan");
        methods.addItem("Cramer");
        methods.addItemListener(this);
    }
    
    /**
     * Agregar widgets en la ventana
     */
    private void addWidgets() {
        add(tagForXInput);
        add(tagForYInput);
        
        add(configPoints);
        add(clearPoints);
        
        add(methods);
        
        add(graphics);
        add(viewMethod);
        add(tagForPolinomium);
    }
    
    /**
     * Crear inputs dinamicamente
     */
    private void createInputs() {
        int coordX = 10;
        int coordY = 30;
        
        // solicitar cuantos inputs
        if(getPolinomiumGrade()) {
            inputs = new JTextField[grade][2];
            points = new double[grade][2];
            
            for(int x = 0; x < 2; x++) {
                for(int y = 0; y < grade; y++) {
                    inputs[y][x] = new JTextField();
                    inputs[y][x].setBounds(coordX, coordY, 90, 20);
                    add(inputs[y][x]);
                    
                    coordY += 20;
                }
                
                coordY = 30;
                coordX += 100;
            }
        }
        
        pointsExists = true;
        this.repaint();
    }
    
    /**
     * Eliminamos los inputs y restablecemos los valores
     * relacionados para evitar conflictos.
     */
    private void deleteInputs() {
        for(int x = 0; x < 2; x++) {
            for(int y = 0; y < grade; y++) {
                this.remove(inputs[y][x]);
            }
        }
        
        inputs = null;
        grade = 0;
        matriz = null;
        points = null;
        pointsExists = false;
        
        tagForPolinomium.setText("f(X) = ...");
        
        deleteOutSystem();
        this.repaint();
    }
    
    /**
     * Generar la matriz en base los puntos
     */
    private void buildMatrizSystem() {
        // obtener valores
        for(int x = 0; x < 2; x++) {
            for(int y = 0; y < grade; y++) {
                points[y][x] = Double.parseDouble(inputs[y][x].getText());
            }
        }
        
        matriz = CreatePolinomium.getSystem(points);
    }
    
    /**
     * Obtener el grado del polinomio para poder definir cuantos
     * inputs se nesecitan.
     * 
     * @return 
     */
    private boolean getPolinomiumGrade() {
        try {
            grade = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de puntos"));
            if(grade < minPoints || grade > maxPoints) {
                errorMessage("Advertencia", "Son muchos puntos o son muy pocos!");
                return false;
            }
            return true;
        }
        catch(Exception errno) {
            errorMessage("Error al leer datos", "Error: "+errno.getMessage());
        }
        return false;
    }
    
    /**
     * Algunas verificaciones para la matriz.
     * @return 
     */
    private boolean checkMatriz() {
        if(matriz == null) {
            errorMessage("Error de matriz", "La matriz esta vacia");
            return false;
        }
        
        return true;
    }   
    
    /**
     * Crear etiquetas de salida para el sistema de ecuaciones
     */
    private void createOutSystem() {
        int coordX = 210;
        int coordY = 10;
        
        try { deleteOutSystem(); }catch(Exception e) {}
        
        outSystemMatriz = new JLabel[matriz.getN()][matriz.getM()];
        
        for(int n = 0; n < matriz.getN(); n++) {
            for(int m = 0; m < matriz.getM(); m++) {
                outSystemMatriz[n][m] = new JLabel(matriz.getMatrizVal(n, m)+"");
                outSystemMatriz[n][m].setBounds(coordX, coordY, 50, 20);
                this.add(outSystemMatriz[n][m]);
                
                coordY += 20;
            }
            
            coordY = 10;
            coordX += 50;
        }
        
        this.repaint();
    }
    
    /**
     * eliminar etiquetas de salida.
     */
    private void deleteOutSystem() {
        for(int n = 0; n < outSystemMatriz.length; n++) {
            for(int m = 0; m < outSystemMatriz[0].length; m++) {
                this.remove(outSystemMatriz[n][m]);
            }
        }
        
        outSystemMatriz = null;
    }
    
    /**
     * Resolver de acuerdo con el metodo seleccionado.
     */
    private void solve() {
        String selection = methods.getSelectedItem().toString();
        buildMatrizSystem();
        
        if(!checkMatriz()) return;
            
        // Resolver con Gauss
        if(selection.equalsIgnoreCase("Gauss")) {
            fx = CreatePolinomium.getPolinomium(MetodoGaussJordan.solve(matriz));
            Gauss.solve(matriz);
        }
        // Resolver con Gauss-Jordan
        else if(selection.equalsIgnoreCase("Gauss-Jordan")) {
            fx = CreatePolinomium.getPolinomium(MetodoGaussJordan.solve(matriz));
        }
        // Preguntar Cramer
        else if(selection.equalsIgnoreCase("Cramer")) {
            // dimensiones de la matriz
            int n = matriz.getN();
            int m = matriz.getM()-1;
            
            // obtener matriz de cofactores
            Matriz temp = new Matriz(n, m);
            double[] independientes = new double[m];
            
            // obtener los terminos independientes
            for(int i = 0; i < n; i++) {
                independientes[i] = matriz.getMatrizVal(i, m);
            }
            
            // rellenar coeficientes
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    temp.updateMatrizVal(i, j, matriz.getMatrizVal(i, j));
                }
            }
            
            if(MetodoCramer.solve(temp, independientes) == null) {
                errorMessage("Advertencia", "El metodo de Cramer no se puede aplicar en este caso, intente con otro metodo");
                return;
            }
            
            fx = CreatePolinomium.getPolinomium(MetodoCramer.solve(temp, independientes));
            tagForPolinomium.setText("f(x) = "+fx);
        }
        
        
        createOutSystem();
        //simpleMessage("Graficando", "Pulse OK para continuar..."); // freno para evitar mas subprocesos
        //CreatePolinomium.grapichs(fx); // graficar polinomio
    }
    
    /**
     * Simplificar el metodo para mostrar un dialogo de error.
     * Muy util para las exepciones.
     * 
     * @param title
     * @param text 
     */
    private void errorMessage(String title, String text) {
        JOptionPane.showMessageDialog(this, text, title, JOptionPane.ERROR_MESSAGE);
    }
    
    private void simpleMessage(String title, String text) {
        JOptionPane.showMessageDialog(this, text, title, JOptionPane.NO_OPTION);
    }
    
    /**
     * Invocamos un subproceso para poder visualizar el metodo
     * guardado en un archivo de texto.
     */
    private void viewMethod() {
        WatchProcedure.run();
    }
    
    // Eventos
    
    // Escuchar eventos
    @Override
    public void actionPerformed(ActionEvent e) {
        // Evento de nuevo
        if(e.getSource() == this.configPoints && !pointsExists) {
            createInputs();
        }
        // Evento de limpiar
        else if(e.getSource() == this.clearPoints && pointsExists) {
            deleteInputs();
        }
        // Evento para ver el procedimiento
        else if(e.getSource() == this.viewMethod && pointsExists) {
            viewMethod();
        }
        // Evento para graficar
        else if(e.getSource() == this.graphics && pointsExists) {
            CreatePolinomium.grapichs(fx);
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == methods && pointsExists) {
            if(!inProcess) {
                solve();
                inProcess = true;
                return;
            }
            
            inProcess = false;
        }
    }
    
    /* Test zone */
    public static void main(String[] args) {
        PolinomiumMatrix win = new PolinomiumMatrix();
    }
}
