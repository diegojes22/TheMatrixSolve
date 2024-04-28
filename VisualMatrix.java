package net.diego.sistemasdeecuaciones;

import javax.swing.*;
import java.awt.event.*;

/**
 * INSTITUTO TECNOLOGICO DE LA PIEDAD
 * 
 * 21 de Abril 2024
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
 *  - 
 */
public class VisualMatrix extends JFrame implements ActionListener{
    /* Atributos */
    int defineN;
    int defineM;
    
    Matriz valores;
    double[] constantes;
    
    boolean matrixExist = false;
    
    // Widgets
    JToolBar actionsArea;
    JButton configMatrix;
    JButton clearMatrix;
    
    JLabel labelForSolvers;
    
    JButton solveWithCramer;
    JButton solveWithGauss;
    JButton solveWithGaussJordan;
    
    JTextField[][] inputsNumbers;
    JLabel[] resultVals;
    
    /* Metodos */
    // Constructor
    public VisualMatrix() {
        // Propiedades basica de la ventana
        this.setLayout(null);
        this.setBounds(10,10,500,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // inicializar widgets
        actionsArea = new JToolBar();
        configMatrix = new JButton("Configurar matriz");
        clearMatrix = new JButton("Limpiar matriz");
        
        labelForSolvers = new JLabel("Resolver con:");
        
        solveWithCramer = new JButton("Cramer");
        solveWithGauss = new JButton("Gauss");
        solveWithGaussJordan = new JButton("Gauss-Jordan");
        
        // estilizar widgets
        configMatrix.setBounds(10, 10, 100, 20);
        clearMatrix.setBounds(10, 30, 100, 20);
        
        labelForSolvers.setBounds(10, 50, 150, 20);
        
        solveWithCramer.setBounds(10, 80, 100, 20);
        solveWithGauss.setBounds(10, 100, 100, 20);
        solveWithGaussJordan.setBounds(10, 120, 100, 20);
        
        // Agregar widgets
        this.add(configMatrix);
        this.add(clearMatrix);
        this.add(labelForSolvers);
        this.add(solveWithCramer);
        this.add(solveWithGauss);
        this.add(solveWithGaussJordan);
        
        // Eventos
        configMatrix.addActionListener(this);
        clearMatrix.addActionListener(this);
        
        solveWithCramer.addActionListener(this);
        solveWithGauss.addActionListener(this);
        solveWithGaussJordan.addActionListener(this);
    }
    
    // Eventos
    /**
     * Verificamos las dimensiones de la matriz
     * para evitar que sea muy grande o muy pequeña.
     * 
     * @return False si la matriz es muy grande o pequeña,
     *         True si la matriz esta en las dimensiones aceptables.
     */
    private boolean checkSize() {
        // Exeso de dimensiones
        if(defineN > 9 || defineM > 9) {
            JOptionPane.showMessageDialog(this, 
          "La matriz es muy grande", 
           "Advertencia", 
       JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        // dimensiones minusculas o negativas
        if(defineN < 2 || defineM < 2) {
            JOptionPane.showMessageDialog(this, 
          "La matriz es muy pequeña!", 
           "Advertencia", 
       JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        
        return true;
    }
    
    /**
     * Creamos e inicializamos varios inputs en forma
     * de matriz, que seran los datos de la matriz para 
     * resolver un sistema de ecuacones.
     */
    private void createMatrix() {
        // Variables
        int cordX = 150;
        int cordY = 10;
        
        // Verificamos si las dimensiones ingresadas por el usuario
        // son correctas
        if(!checkSize()) return;
        
        valores = new Matriz(defineN, defineM);  // matriz de cofactores
        
        inputsNumbers = new JTextField[defineN][defineM]; // inputs
        resultVals = new JLabel[defineM-1];
        
        // Dar propiedades a los inputs
        for(int i = 0; i < defineN; i++) {
            for(int j = 0; j < defineM; j++) {
                inputsNumbers[i][j] = new JTextField();
                inputsNumbers[i][j].setBounds(cordX, cordY, 50, 20);
                this.add(inputsNumbers[i][j]);
                
                this.repaint();
                
                cordX += 50;
            }
            
            cordX = 150;  // reajuste de coordenadas
            cordY += 20;
        }
        
        // Dar propiedades a las etiquetas de los resultados
        for(int i = 0; i < defineM-1; i++) {
            resultVals[i] = new JLabel("X"+i+" = ...");
            resultVals[i].setBounds(cordX, cordY, 50, 20);
            this.add(resultVals[i]);
            
            this.repaint();
            
            cordX += 50;
        }
        
        matrixExist = true;
    }
    
    /**
     * Eliminamos la matriz, las entradas y todo lo que tenga
     * que ver con esta para poder crear otra.
     */
    private void clearMatrix() {
        // remover matriz
        for(int i = 0; i < defineN; i++) {
            for(int j = 0; j < defineM; j++) {
                inputsNumbers[i][j].setVisible(false);
                this.remove(inputsNumbers[i][j]);
            }
        }
        
        // remover etiquetas de resultado
        for(int i = 0; i < defineM-1; i++) {
            this.remove(resultVals[i]);
        }
        
        // restablecer valores
        valores = null;
        inputsNumbers = null;
        resultVals = null;
        
        matrixExist = false;
        
        labelForSolvers.setText("Resolver con: ");
        this.repaint();
    }
    
    /**
     * Obtenemos los valores correspondientes de los inputs
     * y los introduciomos en nuestra matriz de cofactores.
     */
    private void getAllValues() {
        for(int i = 0; i < valores.getN(); i++) {
            for(int j = 0; j < valores.getM(); j++) {
                double val = Double.parseDouble(inputsNumbers[i][j].getText());
                valores.updateMatrizVal(i, j, val);
            }
            System.out.println("");
        }
    }
    
    /**
     * Mostramos los resultados en sus etiquetas correspondientes
     * @param results 
     */
    private void setResults(double[] results) {
        for(int i = 0; i < resultVals.length; i++) {
            resultVals[i].setText("X"+1+" = "+results[i]);
        }
    }
    
    /** 
     * Mostramos el metodo selecionado para resolver el
     * sistema de ecuaciones.
     * @param name 
     */
    private void showSelectedMethod(String name) {
        labelForSolvers.setText("Metodo: "+name);
    }
    
    // Escucha de eventos
    public void actionPerformed(ActionEvent e) {
        // Crear matriz
        if(e.getSource() == configMatrix && !matrixExist) {
            try {
                defineN = Integer.parseInt(JOptionPane.showInputDialog("Ingrese N: "));
                defineM = Integer.parseInt(JOptionPane.showInputDialog("Ingrese M: "));
                
                createMatrix();
            }
            catch(Exception errno) {
                JOptionPane.showMessageDialog(this, "Error: Ingrese un entero!");
            }
        }
        
        // Eliminar
        else if(e.getSource() == clearMatrix) {
            clearMatrix();
        }
        
        // Resolver con Gauss-Jordan
        else if(e.getSource() == solveWithGaussJordan && matrixExist) {
            getAllValues();
            System.out.println(valores.printMatriz());
            
            constantes = MetodoGaussJordan.solve(valores);
            setResults(constantes);
            showSelectedMethod("Gauss-Jordan");
        }
        
        // Resolver con Gauss
        else if(e.getSource() == solveWithGauss && matrixExist) {
            getAllValues();
            System.out.println(valores.printMatriz());
            
            Gauss.solve(valores);
            setResults(MetodoGaussJordan.solve(valores));
            showSelectedMethod("Gauss");
        }
        
        // Resolver con cramer
        else if(e.getSource() == solveWithCramer && matrixExist) {
            // Variables
            getAllValues(); // obtengo valores desde los inputs
            
            int n = valores.getN();
            int m = valores.getM()-1;
            
            Matriz temp = new Matriz(n, m);
            double[] independientes = new double[m];
            
            // obtener los terminos independientes
            for(int i = 0; i < n; i++) {
                independientes[i] = valores.getMatrizVal(i, m);
            }
            
            // Obtener matriz de coeficientes
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    temp.updateMatrizVal(i, j, valores.getMatrizVal(i, j));
                }
            }
            
            setResults(MetodoCramer.solve(temp, independientes));
            showSelectedMethod("Cramer");
        }
    }
    
    /* Zona de pruevas */
    public static void main(String[] args) {
        VisualMatrix win = new VisualMatrix();
        
        win.setVisible(true);
    }
}
