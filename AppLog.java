package net.diego.sistemasdeecuaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * INSTITUTO TECNOLOGICO DE LA PIEDAD
 * 
 * 1 de Mayo 2024
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
 *  - AppLog.java
 * Esta clase nos permite leer y escribir un archivo,
 * pensado para poder guardar los procedimientos del
 * los programas anteriores.
 */


public class AppLog {
    /* Atributos */
    private String filename;
    
    private FileReader read;
    private BufferedReader readBuffer;
    private FileWriter writer;
    private BufferedWriter writeBuffer;
    
    /* Metodos */
    // Constructor
    public AppLog() {
        this.filename = "C:\\Users\\Diego Muñoz\\Documents\\NetBeansProjects\\SistemasDeEcuaciones\\src\\main\\java\\net\\diego\\sistemasdeecuaciones\\log\\procedimiento.txt";
    }
    
    /**
     * Nos permite sobrescribir el contenido del archivo.
     * 
     * @param newText Texto para sobrescribir
     * @return True si el archivo si se pudo sobrescribir.
     *         False si ocurrio un error y no se sobrescribio.
     */
    public boolean overwrite(String newText) {
        try {
            writer = new FileWriter(filename);
            writeBuffer = new BufferedWriter(writer);
            
            writeBuffer.write("\n"+newText);
            
            writeBuffer.close();
            
            writeBuffer = null;
            writer = null;
            return true;
        }
        catch (IOException errno){
            System.err.println("Error: "+errno);
        }
        
        return false;
    }
    
    /**
     * Nos permite agregar contenido al archivo.
     * 
     * @param text Texto a agregar en el archivo.
     * @return True si se logro agregar el contenido del archivo.
     *         False si ocurrio un error.
     */
    public boolean write(String text) {
        try {
            writer = new FileWriter(filename);
            writeBuffer = new BufferedWriter(writer);
            
            writeBuffer.append("\n"+text);
            
            writeBuffer.close();
            
            writeBuffer = null;
            writer = null;
            return true;
        }
        catch (IOException errno){
            System.err.println("Error: "+errno);
        }
        
        return false;
    }
    
    /**
     * Nos permite leer el contenido del archivo.
     * 
     * @return Contenido del archivo. 
     */
    public String read() {
        String text = "";
        
        try {
            read = new FileReader(filename);
            readBuffer = new BufferedReader(read);
            
            String line = "";
            while ((line = readBuffer.readLine()) != null) {
                text += line + "\n";
            }
            
            readBuffer.close();
            read = null;
            readBuffer = null;
        } 
        catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return text;
    }
    
    /**
     * Nos permite limpiar el contenido del archivo
     * para que quede vacio.
     */
    public void clear() {
        this.overwrite("");
    }
}
