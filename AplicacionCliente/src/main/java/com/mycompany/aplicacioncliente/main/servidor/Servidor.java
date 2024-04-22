
package com.mycompany.aplicacioncliente.main.servidor;

import com.mycompany.aplicacioncliente.main.analizadoresconsultas.LexicoConsultas;
import com.mycompany.aplicacioncliente.main.analizadoresconsultas.SintacticoConsultas;
import com.mycompany.aplicacioncliente.main.analizadoresxml.LexicoXML;
import com.mycompany.aplicacioncliente.main.analizadoresxml.SintacticoXML;
import com.mycompany.aplicacioncliente.main.basededatos.ArchivoBaseDeDatos;
import com.mycompany.aplicacioncliente.main.consultas.Consulta;
import com.mycompany.aplicacioncliente.main.fronted.FrameConsultas;
import com.mycompany.aplicacioncliente.main.html.ManejarDePaginasHTML;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java_cup.runtime.Symbol;

public class Servidor extends Thread {
    
    private final int PUERTO = 8080;
    
    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket socket = null;
        DataInputStream entrada;
        DataOutputStream salida;
        
        try {
            servidor = new ServerSocket(PUERTO);
            
            while(true) {
                socket = servidor.accept();
                entrada = new DataInputStream(socket.getInputStream());
                salida = new DataOutputStream(socket.getOutputStream());
                
                String contenidoDelPanel = entrada.readUTF();
                int operacion = Integer.parseInt(entrada.readUTF());
                String mensaje = null;
                
                if (operacion == 1) {
                    LexicoXML lexico = new LexicoXML(new StringReader(contenidoDelPanel));
                    SintacticoXML parser = new SintacticoXML(lexico);

                    try {
                        parser.parse();
                        mensaje = "Todo esta Correcto, realizando Acciones";
                        ManejarDePaginasHTML generador = new ManejarDePaginasHTML();
                        generador.analizarTokens(contenidoDelPanel);
                        ArchivoBaseDeDatos generador2 = new ArchivoBaseDeDatos();
                        generador2.analizarTokens(contenidoDelPanel);
                    } catch (Exception ex) {
                        Symbol sym = parser.getSymbol();

                        if (sym != null) {
                            switch (sym.sym) {
                                case 0 -> mensaje = "Error Sintáctico en la linea " + sym.left + ", columna " + sym.right + ": Se esperaba el Resto de la Instrucción";
                                default -> mensaje = "Error Sintáctico en la linea " + sym.left + ", columna " + sym.right + ": " + parser.getContenido();
                            } 
                        }
                    } catch (Error ex){ 
                        mensaje = ex.getMessage();
                    }
                } else if (operacion == 2){
                    LexicoConsultas lexico = new LexicoConsultas(new StringReader(contenidoDelPanel));
                    SintacticoConsultas parser = new SintacticoConsultas(lexico);
                    
                    try {
                        parser.parse();
                        mensaje = "Todo esta Correcto, realizando Consultas\n";
                        Consulta consulta = new Consulta();
                        consulta.analizarTokens(contenidoDelPanel);
                        List<String> respuestas = consulta.obtenerResultados();
                        FrameConsultas frame = new FrameConsultas(respuestas);
                        frame.setVisible(true);
                    } catch (Exception ex) {
                        Symbol sym = parser.getSymbol();

                        if (sym != null) {
                            switch (sym.sym) {
                                case 0 -> mensaje = "Error Sintáctico en la linea " + sym.left + ", columna " + sym.right + ": Se esperaba el Resto de la Instrucción";
                                default -> mensaje = "Error Sintáctico en la linea " + sym.left + ", columna " + sym.right + ": " + parser.getContenido();
                            } 
                        }
                    } catch (Error ex){ 
                        mensaje = ex.getMessage();
                    }
                }
                salida.writeUTF(mensaje);
                socket.close();
            }
        } catch(IOException ex) {
            System.out.println("Sucedio una Excepción de tipo: " + ex.getMessage());
        }
    }
}
