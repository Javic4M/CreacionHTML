
package com.mycompany.aplicacioncliente.main.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextPane;

public class ClienteXML {
    
    private final String HOST = "127.0.0.1";
    private final int PUERTO = 8080;
    
    public void conectar(String contenidoDelPanel, JTextPane areaDeConsola) {
        DataInputStream entrada;
        DataOutputStream salida;
        
        try {
            Socket socket = new Socket(HOST,PUERTO);       
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            
            salida.writeUTF(contenidoDelPanel);
            salida.writeUTF("1");
            
            String mensaje = entrada.readUTF();
            areaDeConsola.setText(mensaje);
            socket.close();
        } catch(IOException ex) {
            System.out.println("Sucedio una Excepción de tipo: " + ex.getMessage());
        } 
    }
}
