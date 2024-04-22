package com.mycompany.aplicacioncliente.main.consultas;

import com.mycompany.aplicacioncliente.main.analizadoresconsultas.LexicoConsultas;
import com.mycompany.aplicacioncliente.main.analizadoresconsultas.sym;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;

public class Consulta {
    
    private boolean visitaSitioActiva = false, visitaPaginaActiva = false, paginasPolularActiva = false, componenteActivo = false;
    private List<String> listaTokens = new ArrayList<>();
    private List<Populares> populares = new ArrayList<>();
    private List<String> respuesta = new ArrayList<>();
    private String tipo = "";
    
    public void analizarTokens(String contenido) {
        LexicoConsultas lexico = new LexicoConsultas(new StringReader(contenido));
        
        try {
            while (!lexico.yyatEOF()) {
                Symbol symbol = lexico.next_token();
                
                if (sym.VISITAS_SITIO == symbol.sym || visitaSitioActiva) {
                    visitaSitioActiva = true;
                    
                    if (sym.PALABRA == symbol.sym) {
                        listaTokens.add(symbol.value.toString());
                    }
                } else if (sym.VISITAS_PAGINA == symbol.sym || visitaPaginaActiva) {
                    visitaPaginaActiva = true;
                    
                    if (sym.PALABRA == symbol.sym) {
                        listaTokens.add(symbol.value.toString());
                    }
                } else if (sym.PAGINAS_POPULARES == symbol.sym || paginasPolularActiva) {
                    paginasPolularActiva = true;
                    
                    if (sym.PALABRA == symbol.sym) {
                        listaTokens.add(symbol.value.toString());
                    }
                } else if (sym.COMPONENTE == symbol.sym || componenteActivo) {
                    componenteActivo = true;
                    
                    if (sym.PALABRA == symbol.sym || sym.TODOS == symbol.sym || sym.TITULO == symbol.sym || sym.PARRAFO == symbol.sym || sym.IMAGEN == symbol.sym || sym.VIDEO == symbol.sym) {
                        listaTokens.add(symbol.value.toString());
                    }
                }
            }
            realizarConsultas();
        } catch (IOException ex) {
            // Manejo de Error
        }
    }

    private void realizarConsultas() {
        List<String> contenidoTotal = null;
        
        if (visitaSitioActiva) {
            List<String> todasLasLineas = obtenerArchivoBaseDeDatos();
            int indice = 0, numeroDeVistas = 0, indiceSitio = 0;
            respuesta.add("Número de Visitas Totales del Sitio");
            
            while (indice < todasLasLineas.size()) {
                String separar[] = todasLasLineas.get(indice).split("=");
                
                if (indiceSitio < listaTokens.size()) {
                    if ("Sitio".equals(separar[0]) && separar[1].equals(listaTokens.get(indiceSitio))) {
                        indice++;

                        while (!"".equals(todasLasLineas.get(indice))) {
                            String separar2[] = todasLasLineas.get(indice).split("=");

                            if ("Pagina".equals(separar2[0])) {
                                indice += 3;
                                String separar3[] = todasLasLineas.get(indice).split("=");
                                numeroDeVistas += Integer.parseInt(separar3[1]);
                            }
                            indice++;
                        }
                        respuesta.add(separar[1] + ":  " + numeroDeVistas + "  visitas");
                        numeroDeVistas = 0;
                        indiceSitio++;
                    }
                    indice++;
                } else {
                    break;
                }
            }  
        } else if (visitaPaginaActiva) {
            List<String> todasLasLineas = obtenerArchivoBaseDeDatos();
            int indice = 0, numeroDeVistas = 0;
            respuesta.add("Número de Visitas de la Pagina");
            int indiceAEliminar = 0;
            
            while (indiceAEliminar < listaTokens.size()) {
                listaTokens.remove(indiceAEliminar);
                indiceAEliminar++;
            }
            
            while (!listaTokens.isEmpty()) {
                String separar[] = todasLasLineas.get(indice).split("=");

                if ("Pagina".equals(separar[0]) && separar[1].equals(listaTokens.get(0))) {

                    while (!"-------------------------------".equals(todasLasLineas.get(indice))) {
                        String separar2[] = todasLasLineas.get(indice).split("=");

                        if ("Vistas".equals(separar2[0])) {
                            numeroDeVistas = Integer.parseInt(separar2[1]);
                        }
                        indice++;
                    }
                    respuesta.add(separar[1] + ":  " + numeroDeVistas + "  visitas");
                    listaTokens.remove(0);
                    indice = 0;
                }
                indice++;
            }
        } else if (paginasPolularActiva) {
            List<String> todasLasLineas = obtenerArchivoBaseDeDatos();
            int indice = 0;
            respuesta.add("Pagina");
            
            while (indice < todasLasLineas.size()) {
                String separar[] = todasLasLineas.get(indice).split("=");
                
                if ("Sitio".equals(separar[0]) && separar[1].equals(listaTokens.get(0))) {
                    indice++;
                    
                    while (!"".equals(todasLasLineas.get(indice))) {
                        String separar2[] = todasLasLineas.get(indice).split("=");
                        
                        if ("Pagina".equals(separar2[0])) {
                            indice += 3;
                            String separar3[] = todasLasLineas.get(indice).split("=");
                            populares.add(new Populares(separar2[1], Integer.parseInt(separar3[1])));
                        }
                        indice++;
                    }
                    break;
                }
                indice++;
            } 
            ordenar();
        } else if (componenteActivo) {
            contenidoTotal = obtenerHTML(listaTokens.get(2));
            String componenteABuscar = "";
            respuesta.add("Componentes");
            
            if ("TODOS".equals(listaTokens.get(0))) {
                componenteABuscar = "</h1>";
                tipo = "TITULOS";
                ubicarEtiquetasNormales(contenidoTotal, componenteABuscar, ">");
                componenteABuscar = "</p>";
                tipo = "PARRAFOS";
                ubicarEtiquetasNormales(contenidoTotal, componenteABuscar, ">");
                componenteABuscar = "</img>";
                tipo = "IMAGENES";
                ubicarEtiquetasNormales(contenidoTotal, componenteABuscar, ">");
                componenteABuscar = "</iframe>";
                tipo = "VIDEOS";
                ubicarEtiquetasNormales(contenidoTotal, componenteABuscar, ">");
            } else if ("TITULO".equals(listaTokens.get(0))) {
                componenteABuscar = "</h1>";
                tipo = "TITULOS";
                ubicarEtiquetasNormales(contenidoTotal, componenteABuscar, ">");
            } else if ("PARRAFO".equals(listaTokens.get(0))) {
                componenteABuscar = "</p>";
                tipo = "PARRAFOS";
                ubicarEtiquetasNormales(contenidoTotal, componenteABuscar, ">");
            } else if ("IMAGEN".equals(listaTokens.get(0))) {
                componenteABuscar = "</img>";
                tipo = "IMAGENES";
                ubicarEtiquetasNormales(contenidoTotal, componenteABuscar, ">");
            } else if ("VIDEO".equals(listaTokens.get(0))) {
                componenteABuscar = "</iframe>";
                tipo = "VIDEOS";
                ubicarEtiquetasNormales(contenidoTotal, componenteABuscar, ">");
            }
        }
    }
    
    private void ubicarEtiquetasNormales(List<String> contenidoTotal, String componenteABuscar, String cierre) {
        int contadorComponentes = 0;
        
        for (int i = 0; i < contenidoTotal.size(); i++) {
            String caracteres[] = contenidoTotal.get(i).split("");
            boolean abierto = false;
            String etiqueta = "";
            int indice = 0;

            while (indice < caracteres.length) {
                if ("<".equals(caracteres[indice])) {
                    etiqueta += caracteres[indice]; abierto = true;
                } else if (cierre.equals(caracteres[indice])) {
                    etiqueta += caracteres[indice]; abierto = false;

                    if (componenteABuscar.equals(etiqueta)) {
                        contadorComponentes++;
                    }
                    etiqueta = "";
                } else if (abierto) {
                    etiqueta += caracteres[indice];
                }
                indice++;
            }
        }
        respuesta.add("Número de  " + tipo + ":  " + contadorComponentes);
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
      
    public void ordenar() {
        int n = populares.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (populares.get(j).getVistas() > populares.get(j + 1).getVistas()) {
                    Populares temp = populares.get(j);
                    populares.set(j, populares.get(j + 1));
                    populares.set(j + 1, temp);
                }
            }
        }
        
        int contador = 0;
        for (int i = (n-1); i >= 0; i--) {
            if (contador != 10) {
                respuesta.add(populares.get(i).getPagina() + ", tiene " + populares.get(i).getVistas() + " visitas"); 
                contador++;
            } else {
                break;
            }
        }
    }
    
    public List<String> obtenerResultados() {
        return respuesta;
    }
}
