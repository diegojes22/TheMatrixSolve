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
    Matriz valores;
    double[] constantes = new double[3];
    
    // Widgets
    JLabel tagForX;           // Etiquetas
    JLabel tagForY;
    JLabel tagForZ;
    JLabel tagForConst;
    
    // Mucho de todo este codigo y variables
    // puede ser simplificado gracias a matrices
    // y arreglos.
    
    JTextField inputX1;       // Entradas de X
    JTextField inputX2;
    JTextField inputX3;
    
    JTextField inputY1;       // Entradas de Y
    JTextField inputY2;
    JTextField inputY3;
    
    JTextField inputZ1;       // Entradas de Z
    JTextField inputZ2;
    JTextField inputZ3;
    
    JTextField inputConst1;   // Entradas de los terminos independientes
    JTextField inputConst2;
    JTextField inputConst3;
    
    JButton solveWithGauss;   // Botones para invocar el procedimiento
    JButton solveWithCramer;
    
    JLabel valueOfX;          // Etiquetas para los resultados
    JLabel valueOfY;
    JLabel valueOfZ;
    
    /* Metodos */
    // Constructor
    public VisualMatrix() {
        // Propiedades basica de la ventana
        this.setLayout(null);
        this.setBounds(10,10,500,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // inicializar widgets
        tagForX = new JLabel("X");
        tagForY = new JLabel("Y");
        tagForZ = new JLabel("Z");
        tagForConst =new JLabel("Constantes");
        
        solveWithCramer = new JButton("Resolver con Cramer");
        
        inputX1 = new JTextField(); inputY1 = new JTextField(); inputZ1 = new JTextField(); inputConst1 = new JTextField(); // Entradas
        inputX2 = new JTextField(); inputY2 = new JTextField(); inputZ2 = new JTextField(); inputConst2 = new JTextField(); // de las
        inputX3 = new JTextField(); inputY3 = new JTextField(); inputZ3 = new JTextField(); inputConst3 = new JTextField(); // variables
        
        valueOfX = new JLabel("X = ");  // etiquetas para los resultados
        valueOfZ = new JLabel("Y = ");
        valueOfY = new JLabel("Z = ");
        
        // estilizar widgets
        tagForX.setBounds(0,10,83,20);
        tagForY.setBounds(84,10,83,20);
        tagForZ.setBounds(168, 10,83,20);
        tagForConst.setBounds(252, 10, 83, 20);
        
        inputX1.setBounds(0,30,83,20);
        inputX2.setBounds(84,30,83,20);
        inputX3.setBounds(168,30,83,20);
        inputConst1.setBounds(252, 30, 83, 20);
        
        inputY1.setBounds(0,50,83,20);
        inputY2.setBounds(84,50,83,20);
        inputY3.setBounds(168,50,83,20);
        inputConst2.setBounds(252, 50, 83, 20);
        
        inputZ1.setBounds(0,70,83,20);
        inputZ2.setBounds(84,70,83,20);
        inputZ3.setBounds(168,70,83,20);
        inputConst3.setBounds(252, 70, 83, 20);
        
        solveWithCramer.setBounds(10,110,100,20);
        
        valueOfX.setBounds(350, 10, 80, 20);
        valueOfY.setBounds(350, 30, 80, 20);
        valueOfZ.setBounds(350, 50, 80, 20);
        
        // Agregar widgets
        this.add(tagForX);           // etiquetas
        this.add(tagForY);
        this.add(tagForZ);
        
        this.add(inputX1);           // entradas de X
        this.add(inputX2);
        this.add(inputX3);
        
        this.add(inputY1);           // entradas de Y
        this.add(inputY2);
        this.add(inputY3);
        
        this.add(inputZ1);           // entradas de Z
        this.add(inputZ2);
        this.add(inputZ3);
        
        this.add(tagForConst);       // entradas de los terminos independientes
        this.add(inputConst1);
        this.add(inputConst2);
        this.add(inputConst3);
        
        this.add(solveWithCramer);  // boton del procedimiento
        
        this.add(valueOfX);         // etiquetas con los resultados
        this.add(valueOfY);
        this.add(valueOfZ);
        
        // implementar eventos a los botones
        solveWithCramer.addActionListener(this);
    }
    
    // eventos programados
    private void getAllValues() {
        valores = new Matriz(3, 3);
        
        valores.updateMatrizVal(0,0, Double.parseDouble(inputX1.getText()));  // obtener los valores de la matriz
        valores.updateMatrizVal(0,1, Double.parseDouble(inputX2.getText()));
        valores.updateMatrizVal(0,2, Double.parseDouble(inputX3.getText()));
        valores.updateMatrizVal(1,0, Double.parseDouble(inputY1.getText()));
        valores.updateMatrizVal(1,1, Double.parseDouble(inputY2.getText()));
        valores.updateMatrizVal(1,2, Double.parseDouble(inputY3.getText()));
        valores.updateMatrizVal(2,0, Double.parseDouble(inputZ1.getText()));
        valores.updateMatrizVal(2,1, Double.parseDouble(inputZ2.getText()));
        valores.updateMatrizVal(2,2, Double.parseDouble(inputZ3.getText()));
        
        constantes[0] = Double.parseDouble(inputConst1.getText());  // obtener terminos independientes
        constantes[1] = Double.parseDouble(inputConst2.getText());
        constantes[2] = Double.parseDouble(inputConst3.getText());
        
        double solucion[] = MetodoCramer.solve(valores, constantes); // resolver
        
        valueOfX.setText("X = "+solucion[0]);  // mostrar solucion
        valueOfY.setText("Y = "+solucion[1]);
        valueOfZ.setText("Z = "+solucion[2]);
    }
    
    // Escucha de eventos
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == solveWithCramer) {
            System.out.println("Resolver con el metodo de cramer");
            getAllValues();
        }
    }
    
    /* Zona de pruevas */
    public static void main(String[] args) {
        VisualMatrix win = new VisualMatrix();
        
        win.setVisible(true);
    }
}
