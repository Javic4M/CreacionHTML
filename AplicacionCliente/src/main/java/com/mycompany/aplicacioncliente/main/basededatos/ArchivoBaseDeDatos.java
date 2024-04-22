
package com.mycompany.aplicacioncliente.main.basededatos;

import com.mycompany.aplicacioncliente.main.analizadoresxml.LexicoXML;
import com.mycompany.aplicacioncliente.main.analizadoresxml.sym;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java_cup.runtime.Symbol;

public class ArchivoBaseDeDatos {
    
    private boolean nuevoSitioActivo = false, nuevaPaginaActiva = false, modificarPaginaActiva = false, eliminarSitioActivo = false, eliminarPaginaActiva = false, amComponenteActivo = false, eliminarComponenteActivo = false;
        
    public void analizarTokens(String contenido) {
        LexicoXML lexico = new LexicoXML(new StringReader(contenido));
        List<String> listaTokens = new ArrayList<>();
        
        try {
            while (!lexico.yyatEOF()) {
                Symbol symbol = lexico.next_token();

                if (sym.NUEVO_SITIO == symbol.sym) {
                    if (sym.NUEVO_SITIO == symbol.sym && !listaTokens.isEmpty()) {
                        crearArchivoBaseDeDatos(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    nuevoSitioActivo = true;
                } else if (sym.BORRAR_SITIO == symbol.sym) {
                    if (sym.BORRAR_SITIO == symbol.sym && !listaTokens.isEmpty()) {
                        crearArchivoBaseDeDatos(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    eliminarSitioActivo = true;
                } else if (sym.NUEVA_PAGINA == symbol.sym) {
                    if (sym.NUEVA_PAGINA == symbol.sym && !listaTokens.isEmpty()) {
                        crearArchivoBaseDeDatos(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    nuevaPaginaActiva = true;
                } else if (sym.MODIFICAR_PAGINA == symbol.sym) {
                    if (sym.MODIFICAR_PAGINA == symbol.sym && !listaTokens.isEmpty()) {
                        crearArchivoBaseDeDatos(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    modificarPaginaActiva = true;
                } else if (sym.BORRAR_PAGINA == symbol.sym) {
                    if (sym.BORRAR_PAGINA == symbol.sym && !listaTokens.isEmpty()) {
                        crearArchivoBaseDeDatos(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    eliminarPaginaActiva = true;
                } else if (sym.AGREGAR_COMPONENTE == symbol.sym || sym.MODIFICAR_COMPONENTE == symbol.sym) {
                    if ((sym.AGREGAR_COMPONENTE == symbol.sym || sym.MODIFICAR_COMPONENTE == symbol.sym) && !listaTokens.isEmpty()) {
                        crearArchivoBaseDeDatos(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    amComponenteActivo = true;
                } else if (sym.BORRAR_COMPONENTE == symbol.sym) {
                    if ((sym.BORRAR_COMPONENTE == symbol.sym) && !listaTokens.isEmpty()) {
                        crearArchivoBaseDeDatos(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    eliminarComponenteActivo = true;
                }
                
                if (nuevoSitioActivo || nuevaPaginaActiva) {
                    if (sym.PALABRA == symbol.sym || sym.CONTENIDO == symbol.sym || sym.VALOR == symbol.sym || sym.FECHA == symbol.sym || sym.FECHA_C == symbol.sym || sym.FECHA_M == symbol.sym || sym.USUARIO_M == symbol.sym) {
                        listaTokens.add(symbol.value.toString());
                    }
                } else if (modificarPaginaActiva || eliminarSitioActivo || eliminarPaginaActiva || amComponenteActivo || eliminarComponenteActivo) {
                    if (sym.PALABRA == symbol.sym || sym.CONTENIDO == symbol.sym || sym.VALOR == symbol.sym) {
                        listaTokens.add(symbol.value.toString());
                    }
                }
            }
            if (!listaTokens.isEmpty()) {
                crearArchivoBaseDeDatos(listaTokens);
            }
        } catch (IOException ex) {
            // Manejo de Error
        }
    } 
    
    private void marcarTodosFalse() {
        nuevoSitioActivo = false;
        eliminarSitioActivo = false;
        nuevaPaginaActiva = false; 
        modificarPaginaActiva = false;
        eliminarPaginaActiva = false;
        amComponenteActivo = false;
        eliminarComponenteActivo = false;
    }
     
    private void crearArchivoBaseDeDatos(List<String> listaTokens) {
        List<String> lineas = new ArrayList<>();
        boolean escribir = false;
        
        if (nuevoSitioActivo) {
            lineas.add("Sitio=" + listaTokens.get(0));
            String creacion = listaTokens.get(1) + ",";
            int contador = 2;
            
            if (contador < listaTokens.size()) {
                if ("FECHA_CREACION".equals(listaTokens.get(contador))) {
                    contador++;
                    creacion += listaTokens.get(contador);
                    contador++;
                } else {
                    creacion += obtenerFecha();
                }
            } else {
                creacion += obtenerFecha();
            }
            lineas.add("Creacion=" + creacion);
            String fechaModificacion = "";
            
            if (contador < listaTokens.size()) {
                if ("FECHA_MODIFICACION".equals(listaTokens.get(contador))) {
                    contador++;
                    fechaModificacion += listaTokens.get(contador);
                    contador++;
                } else {
                    fechaModificacion += obtenerFecha();
                }
            } else {
                fechaModificacion += obtenerFecha();
            }
            String usuarioModificacion = "";
            
            if (contador < listaTokens.size()) {
                if ("USUARIO_MODIFICACION".equals(listaTokens.get(contador))) {
                    contador++;
                    usuarioModificacion += listaTokens.get(contador);
                    contador++;
                } else {
                    usuarioModificacion += listaTokens.get(1);
                }
            } else {
                usuarioModificacion += listaTokens.get(1);
            }
            lineas.add("Modificacion=" + usuarioModificacion + "," + fechaModificacion);
            lineas.add("-------------------------------");
            lineas.add("");
            guardarArchivoBaseDeDatos(lineas, true);
        } else if (eliminarSitioActivo) {
            List<String> todasLasLineas = obtenerArchivoBaseDeDatos();
            int indice = 0;
            
            while (indice < todasLasLineas.size()) {
                String separar[] = todasLasLineas.get(indice).split("=");
                
                if ("Sitio".equals(separar[0]) && separar[1].equals(listaTokens.get(0))) {
                    
                    while (!"".equals(todasLasLineas.get(indice))) {
                        todasLasLineas.remove(indice);
                    }
                }
                indice++;
            }
            guardarArchivoBaseDeDatos(todasLasLineas, false);
        } else if (nuevaPaginaActiva) {
            List<String> todasLasLineas = obtenerArchivoBaseDeDatos();
            int indice = 0, contador = 5;
            
            lineas.add("Pagina=" + listaTokens.get(0));
            String creacion = listaTokens.get(4) + ",";
            
            if (contador < listaTokens.size()) {
                if ("FECHA_CREACION".equals(listaTokens.get(contador))) {
                    contador++;
                    creacion += listaTokens.get(contador);
                    contador++;
                } else {
                    creacion += obtenerFecha();
                }
            } else {
                creacion += obtenerFecha();
            }
            lineas.add("Creacion=" + creacion);
            String fechaModificacion = "";
            
            if (contador < listaTokens.size()) {
                if ("FECHA_MODIFICACION".equals(listaTokens.get(contador))) {
                    contador++;
                    fechaModificacion += listaTokens.get(contador);
                    contador++;
                } else {
                    fechaModificacion += obtenerFecha();
                }
            } else {
                fechaModificacion += obtenerFecha();
            }
            String usuarioModificacion = "";
            
            if (contador < listaTokens.size()) {
                if ("USUARIO_MODIFICACION".equals(listaTokens.get(contador))) {
                    contador++;
                    usuarioModificacion += listaTokens.get(contador);
                    contador++;
                } else {
                    usuarioModificacion += listaTokens.get(4);
                }
            } else {
                usuarioModificacion += listaTokens.get(4);
            }
            String modificacion = "Modificacion=" + usuarioModificacion + "," + fechaModificacion;
            lineas.add(modificacion);
            lineas.add("Vistas=0");
            
            while (contador < listaTokens.size()) {
                if ("valor".equals(listaTokens.get(contador))) {
                    contador++;
                    lineas.add("Etiqueta="+listaTokens.get(contador));
                }
                contador++;
            }
            lineas.add("-------------------------------");
            
            while (indice < todasLasLineas.size()) {
                String separar[] = todasLasLineas.get(indice).split("=");
                
                if ("Sitio".equals(separar[0]) && separar[1].equals(listaTokens.get(2))) {
                    agregarModificacionAlSitio(todasLasLineas, listaTokens.get(2), modificacion);
                    
                    while (!"-------------------------------".equals(todasLasLineas.get(indice))) {
                        indice++;
                    }
                    indice++;
                    
                    int limite = (lineas.size() - 1);
                    for (int i = limite; i >= 0; i--) {
                        todasLasLineas.add(indice, lineas.get(i));
                    }
                    break;
                }
                indice++;
            }
            guardarArchivoBaseDeDatos(todasLasLineas, false);
        } else if (modificarPaginaActiva) {
            List<String> todasLasLineas = obtenerArchivoBaseDeDatos();
            String sitioNombre = "", modificacion = "Modificacion=Anonimo," + obtenerFecha();
            int indice = 0;
            
            while (indice < todasLasLineas.size()) {
                String separar[] = todasLasLineas.get(indice).split("=");
                
                if ("Sitio".equals(separar[0])) {
                    sitioNombre = separar[1];
                    escribir = true;
                }
                if ("Pagina".equals(separar[0]) && separar[1].equals(listaTokens.get(0))) {
                    indice += 2;
                    todasLasLineas.remove(indice);
                    todasLasLineas.add(indice, modificacion);
                    indice += 2; //Quie
                    
                    int contador = 0;
                    boolean cambiar = false;
                    
                    while (contador < listaTokens.size()) {
                        if ("valor".equals(listaTokens.get(contador))) {
                            cambiar = true;
                            break;
                        }
                        contador++;
                    }
                          
                    if (cambiar) {
                        while (indice < todasLasLineas.size()) {
                            if (!"-------------------------------".equals(todasLasLineas.get(indice))) {
                                todasLasLineas.remove(indice);
                            } else {
                                int contador2 = contador;

                                while (contador2 < listaTokens.size()) {
                                    if ("valor".equals(listaTokens.get(contador2))) {
                                        contador2++;
                                        todasLasLineas.add(indice, "Etiqueta="+listaTokens.get(contador2));
                                    }
                                    contador2++;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                indice++;
            }
            if (escribir) {
                agregarModificacionAlSitio(todasLasLineas, sitioNombre, modificacion);
            }
            guardarArchivoBaseDeDatos(todasLasLineas, false);
        } else if (eliminarPaginaActiva) {
            List<String> todasLasLineas = obtenerArchivoBaseDeDatos();
            String sitioNombre = "", modificacion = "Modificacion=Anonimo," + obtenerFecha();
            int indice = 0;
            
            while (indice < todasLasLineas.size()) {
                String separar[] = todasLasLineas.get(indice).split("=");
                
                if ("Sitio".equals(separar[0])) {
                    sitioNombre = separar[1];
                    escribir = true;
                }
                if ("Pagina".equals(separar[0]) && separar[1].equals(listaTokens.get(0))) {
                    
                    while (!"-------------------------------".equals(todasLasLineas.get(indice))) {
                        todasLasLineas.remove(indice);
                    }
                    todasLasLineas.remove(indice);
                    break;
                }
                indice++;
            }
            if (escribir) {
                agregarModificacionAlSitio(todasLasLineas, sitioNombre, modificacion);
            }
            guardarArchivoBaseDeDatos(todasLasLineas, false);
        } else if (amComponenteActivo) {
            List<String> todasLasLineas = obtenerArchivoBaseDeDatos();
            String sitioNombre = "", modificacion = "Modificacion=Anonimo," + obtenerFecha();
            int indice = 0;

            while (indice < todasLasLineas.size()) {
                String separar[] = todasLasLineas.get(indice).split("=");
                
                if ("Sitio".equals(separar[0])) {
                    sitioNombre = separar[1];
                    escribir = true;
                }
                if ("Pagina".equals(separar[0]) && separar[1].equals(listaTokens.get(1))) {
                    indice += 2;
                    todasLasLineas.remove(indice);
                    todasLasLineas.add(indice, modificacion);
                    break;
                }
                indice++;
            }
            if (escribir) {
                agregarModificacionAlSitio(todasLasLineas, sitioNombre, modificacion);
            }
            guardarArchivoBaseDeDatos(todasLasLineas, false);
        } else if (eliminarComponenteActivo) {
            List<String> todasLasLineas = obtenerArchivoBaseDeDatos();
            String sitioNombre = "", modificacion = "Modificacion=Anonimo," + obtenerFecha();
            int indice = 0;

            while (indice < todasLasLineas.size()) {
                String separar[] = todasLasLineas.get(indice).split("=");
                
                if ("Sitio".equals(separar[0])) {
                    sitioNombre = separar[1];
                    escribir = true;
                }
                if ("Pagina".equals(separar[0]) && separar[1].equals(listaTokens.get(0))) {
                    indice += 2;
                    todasLasLineas.remove(indice);
                    todasLasLineas.add(indice, modificacion);
                    break;
                }
                indice++;
            }
            if (escribir) {
                agregarModificacionAlSitio(todasLasLineas, sitioNombre, modificacion);
            }
            guardarArchivoBaseDeDatos(todasLasLineas, false);
        }
    }
    
    private void agregarModificacionAlSitio(List<String> todasLasLineas, String nombreDelSitio, String modificacion) {
        int indice = 0;
        
        while (indice < todasLasLineas.size()) {
            String separar[] = todasLasLineas.get(indice).split("=");

            if ("Sitio".equals(separar[0]) && separar[1].equals(nombreDelSitio)) {
                indice += 2;
                todasLasLineas.remove(indice);
                todasLasLineas.add(indice, modificacion);
                break;
            }
            indice++;
        }
    }
    
    private String obtenerFecha() {
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        return formato.format(fecha);
    }
    
    private List<String> obtenerArchivoBaseDeDatos() {
        File file = new File("BaseDeDatos.txt");
        BufferedReader entrada = null;
        List<String> todasLasLineas = new ArrayList<>();
        String linea;
        
        if (file.exists()) {            
            try {
                entrada = new BufferedReader(new FileReader(file));
                
                while ((linea = entrada.readLine()) != null) {
                    todasLasLineas.add(linea);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return todasLasLineas;
    }
    
    private void guardarArchivoBaseDeDatos(List<String> lineas, boolean reescribir) {
        File file = new File("BaseDeDatos.txt");

        try {
            FileWriter writer = new FileWriter(file, reescribir);

            try (PrintWriter printer = new PrintWriter(writer)) {
                for (String linea : lineas) {
                    printer.println(linea);
                }
                printer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
