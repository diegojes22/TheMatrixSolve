package net.diego.sistemasdeecuaciones;

import java.util.Random;

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
 *  - firstWindow -> Archivo de prueva para la 
 * implementacion de una interfaz grafica.
 */
public class firstWindow extends JFrame implements ActionListener {
    /* Atributos */
    JLabel hw;
    JButton btn;
    
    /* Metodos */
    // Constructor
    public firstWindow() {
        this.setLayout(null);
        this.setTitle("Hello, World!");
        
        // Inicializar widgets
        hw = new JLabel("Hola, Mundo!");
        btn = new JButton("Click");
        
        // estilizar
        hw.setBounds(10,10,200,50);
        btn.setBounds(10, 80, 100, 50);
        
        // Eventos
        btn.addActionListener(this);
        
        // Agregar elementos
        this.add(hw);
        this.add(btn);
    }
    
    // Eventos
    private void changeText() {
        Random r = new Random();
        String[] msgs = {
            "Hola",
            "Adios",
            "Prefiero Python",
            "I hate Java",
            "N is alone",
            "a",
            "El poder del conocimiento",
            "No supe que poner en este texto",
            "BGY"
        };
        hw.setText(
            msgs[r.nextInt(msgs.length)]
        );
    }
    
    // Escucha de eventos
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn) {
            changeText();
        }
    }
    
    /* Zona de pruevas */
    public static void main(String[] args) {
        firstWindow win = new firstWindow();
        
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setBounds(0,0,200,300);
        win.setVisible(true);
    }
}
