
package com.mycompany.aplicacioncliente.main.html;

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
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;

public class ManejarDePaginasHTML {
    
    private boolean sitioActivo = false, borrarSitio = false, paginaActiva = false, modificarPaginaActiva = false, borrarPaginaActiva = false, agregarComponente = false, modificarComponente = false, borrarComponente = false;
    
    public void analizarTokens(String contenido) {
        LexicoXML lexico = new LexicoXML(new StringReader(contenido));
        List<String> listaTokens = new ArrayList<>();
                
        try {
            while (!lexico.yyatEOF()) {
                Symbol symbol = lexico.next_token();

                if (sym.NUEVO_SITIO == symbol.sym) {            
                    if (sym.NUEVO_SITIO == symbol.sym && !listaTokens.isEmpty()) {
                        realizarAccion(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    sitioActivo = true;
                } else if (sym.BORRAR_SITIO == symbol.sym) {
                    if (sym.BORRAR_SITIO == symbol.sym && !listaTokens.isEmpty()) {
                        realizarAccion(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse(); 
                    }
                    borrarSitio = true;
                } else if (sym.NUEVA_PAGINA == symbol.sym) {
                    if (sym.NUEVA_PAGINA == symbol.sym && !listaTokens.isEmpty()) {
                        realizarAccion(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse(); 
                    }
                    paginaActiva = true;
                } else if (sym.MODIFICAR_PAGINA == symbol.sym) {
                    if (sym.MODIFICAR_PAGINA == symbol.sym && !listaTokens.isEmpty()) {
                        realizarAccion(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    modificarPaginaActiva = true;
                } else if (sym.BORRAR_PAGINA == symbol.sym) {
                    if (sym.BORRAR_PAGINA == symbol.sym && !listaTokens.isEmpty()) {
                        realizarAccion(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse(); 
                    }
                    borrarPaginaActiva = true;
                } else if (sym.AGREGAR_COMPONENTE == symbol.sym) {
                    if (sym.AGREGAR_COMPONENTE == symbol.sym && !listaTokens.isEmpty()) { 
                        realizarAccion(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    agregarComponente = true;
                } else if (sym.MODIFICAR_COMPONENTE == symbol.sym) {
                    if (sym.MODIFICAR_COMPONENTE == symbol.sym && !listaTokens.isEmpty()) {
                        realizarAccion(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    modificarComponente = true;
                } else if (sym.BORRAR_COMPONENTE == symbol.sym) {
                    if (sym.BORRAR_COMPONENTE == symbol.sym && !listaTokens.isEmpty()) {
                        realizarAccion(listaTokens);
                        listaTokens = new ArrayList<>();
                        marcarTodosFalse();
                    }
                    borrarComponente = true;
                }
            
                if (sitioActivo || borrarSitio || paginaActiva || modificarPaginaActiva || borrarPaginaActiva || borrarComponente) {
                    if (sym.PALABRA == symbol.sym || sym.CONTENIDO == symbol.sym || sym.VALOR == symbol.sym) {
                        listaTokens.add(symbol.value.toString());
                    }
                } else if (agregarComponente) {
                    if (sym.TITULO == symbol.sym || sym.PARRAFO == symbol.sym || sym.IMAGEN == symbol.sym || sym.VIDEO == symbol.sym || sym.MENU == symbol.sym || sym.TEXTO == symbol.sym || sym.ORIGEN == symbol.sym || sym.ALINEACION == symbol.sym || sym.IZQUIERDA == symbol.sym || sym.CENTRAR == symbol.sym || sym.DERECHA == symbol.sym || sym.COLOR == symbol.sym || sym.ALTURA == symbol.sym || sym.ANCHO == symbol.sym || sym.PALABRA == symbol.sym || sym.CONTENIDO == symbol.sym || sym.NUMERO == symbol.sym || sym.NUMERO_COLOR == symbol.sym || sym.URL == symbol.sym) {
                        listaTokens.add(symbol.value.toString());
                    }
                } else if (modificarComponente) {
                    if (sym.TITULO == symbol.sym || sym.PARRAFO == symbol.sym || sym.IMAGEN == symbol.sym || sym.VIDEO == symbol.sym || sym.MENU == symbol.sym || sym.TEXTO == symbol.sym || sym.ORIGEN == symbol.sym || sym.ALINEACION == symbol.sym || sym.IZQUIERDA == symbol.sym || sym.CENTRAR == symbol.sym || sym.DERECHA == symbol.sym || sym.COLOR == symbol.sym || sym.ALTURA == symbol.sym || sym.ANCHO == symbol.sym || sym.PALABRA == symbol.sym || sym.CONTENIDO == symbol.sym || sym.NUMERO == symbol.sym || sym.NUMERO_COLOR == symbol.sym || sym.URL == symbol.sym) {
                        listaTokens.add(symbol.value.toString());
                    }
                }
            }
            if (!listaTokens.isEmpty()) {
                realizarAccion(listaTokens);
            }
        } catch (IOException ex) {
            // Manejo de Error
        }
    }
    
    private void realizarAccion(List<String> listaTokens) {
        if (sitioActivo) {
            crearSitio(listaTokens);
        } else if (borrarSitio) {  
            eliminarSitio(listaTokens.get(0));
        } else if (paginaActiva) {
            crearPaginaHTML(listaTokens);
        } else if (modificarPaginaActiva) {
            modificarPaginaHTML(listaTokens);
        } else if (borrarPaginaActiva) {
            eliminarPaginaHTML(listaTokens.get(0));
        } else if (agregarComponente) {
            agregarComponente(listaTokens);
        } else if (modificarComponente) {
            modificarComponente(listaTokens);
        } else if (borrarComponente) {
            borrarComponente(listaTokens);
        }
    }
    
    public void marcarTodosFalse() {
        sitioActivo = false;
        borrarSitio = false; 
        paginaActiva = false;
        modificarPaginaActiva = false;
        borrarPaginaActiva = false;
        agregarComponente = false;
        modificarComponente = false;
        borrarComponente = false;
    }
    
    // Metodos para crear, manipular el HTML y crear Directorios
    private void crearSitio(List<String> listaTokens) {
        File directorio = new File("Sitios\\" + listaTokens.get(0));
        directorio.mkdir();
    }
    
    private void eliminarSitio(String path) {
        File directorio = new File("Sitios\\" + path);
        File[] archivos = directorio.listFiles();
        
        if (directorio.exists()) {
            if (archivos != null) {
                for (File archivo : archivos) {
                    archivo.delete();
                }
            }
            directorio.delete();
        }
    }
    
    private void crearPaginaHTML(List<String> listaTokens) {
        List<String> codigo = new ArrayList<>();
        
        codigo.add("<!DOCTYPE html>");
        codigo.add("<html lang=\"es\">");
        codigo.add("\t<head>");
        codigo.add("\t\t<meta charset=\"UTF-8\">");
        codigo.add("\t\t<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
        codigo.add("\t\t<title>" + listaTokens.get(1) + "</title>");
        codigo.add("\t<style>");
        codigo.add("\t</style>");
        codigo.add("\t</head>");
        codigo.add("\t<body>");
        codigo.add("\t</body>");
        codigo.add("<html>"); 
        guardarNuevaPagina(listaTokens.get(0), listaTokens.get(2), codigo);
    }
    
    private void modificarPaginaHTML(List<String> listaTokens) {
        List<String> contenidoTotal = obtenerHTML(listaTokens.get(0));
        String contenidoLinea = "", etiqueta = "";
        
        if (!contenidoTotal.isEmpty()) {
            boolean abierto = false;
            
            for (int i = 0; i < contenidoTotal.size(); i++) {
                String caracteres[] = contenidoTotal.get(i).split("");
                contenidoLinea = "";
                int indice = 0;
                
                while (indice < caracteres.length) {
                    if ("<".equals(caracteres[indice])) {
                        etiqueta += caracteres[indice]; abierto = true;
                    } else if (">".equals(caracteres[indice])) {
                        etiqueta += caracteres[indice]; abierto = false;

                        if ("<title>".equals(etiqueta)) {
                            contenidoLinea = contenidoLinea + caracteres[indice] + listaTokens.get(1);
                            
                            while (!"<".equals(caracteres[indice])) {
                                indice++;
                            }
                        } else {
                            etiqueta = "";
                        }
                    } else if (abierto) {
                        etiqueta += caracteres[indice];
                    }
                    contenidoLinea += caracteres[indice];
                    indice++;
                }
                contenidoTotal.remove(i);
                contenidoTotal.add(i, contenidoLinea);
            }
            String path = buscarUbicacionDelHTML(new File("Sitios"), listaTokens.get(0) + ".html");
            guardarModificacones(path, contenidoTotal);
        }
    }
    
    private void eliminarPaginaHTML(String nombreDeLaPagina) {
        String path = buscarUbicacionDelHTML(new File("Sitios"), nombreDeLaPagina + ".html");
        System.out.println("Eliminar Pagina: " + path);
        File archivo = new File(path);
        archivo.delete();
    }
        
    private void agregarComponente(List<String> listaTokens) {
        List<String> contenidoTotal = obtenerHTML(listaTokens.get(1));
        String etiquetaAInsertar = establecerEtiqueta(listaTokens);
        String cssAInsertar = establecerCSS(listaTokens);
        String contenidoLinea = "", etiqueta = "";
        boolean bodyEncontrado = false, styleEncontrado = false;
        
        if (!contenidoTotal.isEmpty()) {
            for (int i = 0; i < contenidoTotal.size(); i++) {
                String caracteres[] = contenidoTotal.get(i).split("");
                boolean insertar = true;
                contenidoLinea = "";
                int indice = 0;
                
                while (indice < caracteres.length) {               
                    if ("<".equals(caracteres[indice])) {  
                        for (int j = indice; j < caracteres.length; j++) {
                            etiqueta += caracteres[j];
                            
                            if (">".equals(caracteres[j])) {
                                break;
                            }
                        }
                        
                        if ("</body>".equals(etiqueta) && !bodyEncontrado) {
                            contenidoTotal.add(i, etiquetaAInsertar);
                            bodyEncontrado = true;
                            insertar = false; 
                        } else if ("</style>".equals(etiqueta) && !styleEncontrado) {
                            contenidoTotal.add(i, cssAInsertar);
                            styleEncontrado = true;
                            insertar = false;
                        }
                    }
                    contenidoLinea += caracteres[indice];
                    etiqueta = "";
                    indice++;
                }
                if (insertar) {
                    contenidoTotal.remove(i);
                    contenidoTotal.add(i, contenidoLinea);
                }
            }
            String path = buscarUbicacionDelHTML(new File("Sitios"), listaTokens.get(1) + ".html");
            guardarModificacones(path, contenidoTotal);
        }
    }
    
    private void modificarComponente(List<String> listaTokens) {
        List<String> contenidoTotal = obtenerHTML(listaTokens.get(1));
        String etiquetaAInsertar = establecerEtiqueta(listaTokens);
        String cssAInsertar = establecerCSS(listaTokens);
        String identificador1 = "." + listaTokens.get(0), identificador2 = "\"" + listaTokens.get(0) + "\"";
        String contenidoLinea = "", etiqueta = "", css = "";
        
        if (!contenidoTotal.isEmpty()) {
            for (int i = 0; i < contenidoTotal.size(); i++) {
                String caracteres[] = contenidoTotal.get(i).split("");
                contenidoLinea = "";
                int indice = 0;
                
                while (indice < caracteres.length) {
                    if ("\"".equals(caracteres[indice])) {
                        etiqueta += caracteres[indice];
                        
                        for (int k = (indice+1); k < caracteres.length; k++) {
                            etiqueta += caracteres[k];
                            
                            if ("\"".equals(caracteres[k])) {
                                break;
                            }
                        }
                        
                        if (identificador2.equals(etiqueta)) {
                            contenidoLinea = etiquetaAInsertar;
                            break;
                        }
                    } else if (".".equals(caracteres[indice])) {
                        for (int k = indice; k < caracteres.length; k++) {
                            if (" ".equals(caracteres[k])) {
                                break;
                            } else {
                                css += caracteres[k];
                            }
                        }
                        
                        if (identificador1.equals(css)) {
                            contenidoLinea = cssAInsertar;
                            break;
                        }
                    }
                    contenidoLinea += caracteres[indice];
                    etiqueta = ""; css = "";
                    indice++;
                }
                contenidoTotal.remove(i);
                contenidoTotal.add(i, contenidoLinea);
            }
            String path = buscarUbicacionDelHTML(new File("Sitios"), listaTokens.get(1) + ".html");
            guardarModificacones(path, contenidoTotal);
        }
    }
    
    private void borrarComponente(List<String> listaTokens){
        List<String> contenidoTotal = obtenerHTML(listaTokens.get(1));
        String identificador1 = "." + listaTokens.get(0), identificador2 = "\"" + listaTokens.get(0) + "\"";
        String contenidoLinea = "", etiqueta = "", css = "";
        
        if (!contenidoTotal.isEmpty()) {
            for (int i = 0; i < contenidoTotal.size(); i++) {
                String caracteres[] = contenidoTotal.get(i).split("");
                contenidoLinea = "";
                int indice = 0;
                
                while (indice < caracteres.length) {
    
                    if ("\"".equals(caracteres[indice])) {
                        etiqueta += caracteres[indice];
                        
                        for (int k = (indice + 1); k < caracteres.length; k++) {
                            etiqueta += caracteres[k];
                            
                            if ("\"".equals(caracteres[k])) {
                                break;
                            }
                        }
                        
                        if (identificador2.equals(etiqueta)) {
                            contenidoLinea = "";
                            break;
                        }
                    } else if (".".equals(caracteres[indice])) {
                        for (int k = indice; k < caracteres.length; k++) {
                            if (" ".equals(caracteres[k])) {
                                break;
                            } else {
                                css += caracteres[k];
                            }
                        }
                        
                        if (identificador1.equals(css)) {
                            contenidoLinea = "";
                            break;
                        }
                    }
                    contenidoLinea += caracteres[indice];
                    etiqueta = ""; css = "";
                    indice++;
                }
                contenidoTotal.remove(i);
                contenidoTotal.add(i, contenidoLinea);
            }
            String path = buscarUbicacionDelHTML(new File("Sitios"), listaTokens.get(1) + ".html");
            guardarModificacones(path, contenidoTotal);
        }
    }
    
    
    // Metodo para contruir el Componente en base a las Instrucciones
    private String establecerEtiqueta(List<String> listaTokens) {
        List<String> partes = new ArrayList<>();
        String codigoInsertar = "";
        
        if ("TITULO".equals(listaTokens.get(2))) {
            partes.add("\t\t<h1 class=\""  + listaTokens.get(0) + "\">");
            if ("TEXTO".equals(listaTokens.get(3))) {
                partes.add(listaTokens.get(4));
            }
            partes.add("</h1>"); 
            
        } else if ("PARRAFO".equals(listaTokens.get(2))) {
            partes.add("\t\t<p class=\""  + listaTokens.get(0) + "\">");
            if ("TEXTO".equals(listaTokens.get(3))) {
                partes.add(listaTokens.get(4));
            }
            partes.add("</p>");
        } else if ("IMAGEN".equals(listaTokens.get(2))) {
            partes.add("\t\t");
            partes.add("<img class=\""  + listaTokens.get(0) + "\" ");
            if ("ORIGEN".equals(listaTokens.get(3))) {
                partes.add("src=\"" + listaTokens.get(4) + "\"");
            }
            partes.add("></img>");
            if (5 < listaTokens.size()) {
                if ("ALINEACION".equals(listaTokens.get(5))) {
                    partes.add(1, "<div style=\"text-align:" + obtenerAlineacion(listaTokens.get(6)) + "\">");
                    partes.add("</div>");
                }
            }
        } else if ("VIDEO".equals(listaTokens.get(2))) {
            partes.add("\t\t");
            partes.add("<iframe class=\""  + listaTokens.get(0) + "\" ");
            if ("ORIGEN".equals(listaTokens.get(3))) {
                partes.add("src=\"" + listaTokens.get(4) + "\"");
            }
            partes.add("></iframe>");
        } else if ("MENU".equals(listaTokens.get(2))) {
            
        }
        
        for (String parte : partes) {
            codigoInsertar += parte;
        }
        return codigoInsertar;
    }
    
    private String establecerCSS(List<String> listaTokens) {
        List<String> partes = new ArrayList<>();
        String cssAIngresar = "";
        boolean paso = true;
        int contador = 5;
        
        partes.add("\t\t." + listaTokens.get(0) + " {");
        partes.add(" }");
        
        if ("ORIGEN".equals(listaTokens.get(3))) {
            paso = false;
        }
        
        if (contador < listaTokens.size()) {
            if ("ALINEACION".equals(listaTokens.get(contador))) {
                if (paso) {
                    contador++;
                    partes.add(1, " text-align:" + obtenerAlineacion(listaTokens.get(contador)));
                    contador++;
                } else {
                    contador += 2;
                }
            }
        }

        if (contador < listaTokens.size()) {
            if ("COLOR".equals(listaTokens.get(contador))) {
                contador++;
                partes.add(1, " color: " + listaTokens.get(contador) + ";");
                contador++;
            }
        }
        
        if (contador < listaTokens.size()) {
            if ("ALTURA".equals(listaTokens.get(contador))) {
                contador++;
                partes.add(1, " height: " + listaTokens.get(contador) + "px;");
                contador++;
            }
        }
        
        if (contador < listaTokens.size()) {
            if ("ANCHO".equals(listaTokens.get(contador))) {
                contador++;
                partes.add(1, " width: " + listaTokens.get(contador) + "px;");
                contador++;
            }
        }
        
        for (String parte : partes) {
            cssAIngresar += parte;
        }
        return cssAIngresar;
    }
    
    private String obtenerAlineacion(String comando) {
        String codigoInsertar = "";
        
        if ("CENTRAR".equals(comando)) {
            codigoInsertar = " center;";
        } else if ("IZQUIERDA".equals(comando)) {
            codigoInsertar = " left;";
        } else if ("DERECHA".equals(comando)) {
            codigoInsertar = " right;";
        } else if ("JUSTIFICAR".equals(comando)) {
            codigoInsertar = " justify;";
        }
        return codigoInsertar;
    }
    
    
     // Metodos para Escribir, Leer y Buscar los Archivos HTML
    private List<String> obtenerHTML(String direccion) {
        String path = buscarUbicacionDelHTML(new File("Sitios"), direccion + ".html");
        File file = new File(path);
        BufferedReader entrada = null;
        List<String> contenido = new ArrayList<>();
        String linea;
        
        if (file.exists()) {            
            try {
                entrada = new BufferedReader(new FileReader(file));
                
                while ((linea = entrada.readLine()) != null) {
                    contenido.add(linea);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return contenido;
    }
    
    private String buscarUbicacionDelHTML(File file, String archivoABuscar) {
        File[] archivos = file.listFiles();
        String path = "";
        
        if (archivos != null) {
            
            for (File archivo : archivos) {
                
                if (archivo.getName().equals(archivoABuscar)) {
                    return archivo.getPath();
                }
                
                if (archivo.isDirectory()) {
                    path = buscarUbicacionDelHTML(archivo, archivoABuscar);
                    
                    if (!path.isEmpty()) {
                        return path;
                    }
                }
            }
        } else {
            System.out.println("No tiene Hijos");
        }
        return path;
    }
    
    private void guardarNuevaPagina(String direccion, String sitio, List<String> contenidoTotal) {
        File file = new File("Sitios\\" + sitio + "\\" + direccion + ".html");
        guardarHTML(file, contenidoTotal);
    }
    
    private void guardarModificacones(String path, List<String> contenidoTotal) {
        File file = new File(path);
        guardarHTML(file, contenidoTotal);
    }
    
    public void guardarHTML(File file, List<String> contenidoTotal) {
        try {
            FileWriter writer = new FileWriter(file, false);

            try (PrintWriter printer = new PrintWriter(writer)) {
                for (String contenidoLinea : contenidoTotal) {
                    if (!contenidoLinea.isEmpty()) {
                        printer.println(contenidoLinea);
                    }
                }
                printer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
