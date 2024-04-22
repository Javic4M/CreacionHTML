package com.mycompany.aplicacioncliente.main.consultas;

public class Populares {
    
    private String pagina;
    private int vistas;

    public Populares(String pagina, int vistas) {
        this.pagina = pagina;
        this.vistas = vistas;
    }

    public String getPagina() {
        return pagina;
    }

    public int getVistas() {
        return vistas;
    }
}
