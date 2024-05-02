package net.diego.sistemasdeecuaciones;

import javax.swing.*;

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
public class WatchProcedure extends JFrame{
    /* Atributos */
    JTextArea text;
    JScrollPane scroll;
    
    /* Metodos */
    // Constructor
    public WatchProcedure(){
        initWidgets();
        addWidgets();
        
        AppLog log = new AppLog();
        text.setText(log.read());
        
        configWin();
    }
    
    private void initWidgets() {
        text = new JTextArea();
        scroll = new JScrollPane(text);
        
        scroll.setBounds(0, 0, 350, 600);
    }
    
    private void addWidgets() {
        this.add(scroll);
    }
    
    private void configWin() {
        this.setLayout(null);
        this.setBounds(200, 150, 370, 600);
        this.setVisible(true);
        this.setResizable(false);
        
        this.setTitle("Procedimiento");
        
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.repaint();
    }
    
    public static void run() {
        WatchProcedure win = new WatchProcedure();
    }
}
