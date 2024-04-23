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
 *  - Formularios -> Pruevas con los inputs y eventos 
 * para implementar un GUI en la app.
 */
public class Formularios extends JFrame implements ActionListener{
    /* Atributos */
    // Widgets
    JLabel tagForXOne;
    JTextField inputXOne;
    
    JButton submitResults;
    
    /* Metodos */
    // Constructor
    public Formularios() {
        this.setLayout(null);
        
        // Inicializar widgets
        tagForXOne= new JLabel("X1");
        inputXOne = new JTextField();
        
        submitResults = new JButton("Aplicar");
        
        // Estilizar widgtes
        tagForXOne.setBounds(10, 10, 100, 20);
        inputXOne.setBounds(10,40,100,20);
        
        submitResults.setBounds(10, 80, 100, 20);
        
        // Eventos
        submitResults.addActionListener(this);
        
        // Agregar widgets
        this.add(tagForXOne);
        this.add(inputXOne);
        this.add(submitResults);
    }
    
    // Eventos
    private void getNumbers() {
        double valOf_xone = 0;
        
        try {
            valOf_xone = Double.parseDouble(inputXOne.getText());
        }catch(Exception errno) {
            JOptionPane.showMessageDialog(this, "Error, Ingrese un numero por favor.");
        }
        System.out.println("Valor de X1 = "+valOf_xone);
    }
    
    // Escucha de eventos
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitResults){
            getNumbers();
        }
    }
    
    /* Area de pruevas */
    public static void main(String[] args) {
        Formularios win = new Formularios();
        
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setBounds(0,0,250,400);
        win.setVisible(true);
    }
}
