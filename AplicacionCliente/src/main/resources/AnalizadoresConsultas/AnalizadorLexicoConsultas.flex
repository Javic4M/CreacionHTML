package com.mycompany.aplicacioncliente.main.analizadoresconsultas;

import java_cup.runtime.*;

%% // Separador de Area

%class LexicoConsultas
%public 
%cup
%line
%column
%eofval{
    return symbol(sym.EOF);
%eofval}

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}

Consultar = [cC][oO][nN][sS][uU][lL][tT][aA][rR]
Sitio = [vV][iI][sS][iI][tT][aA][sS][_][sS][iI][tT][iI][oO]
Pagina = [vV][iI][sS][iI][tT][aA][sS][_][pP][aA][gG][iI][nN][aA]
Populares = [pP][aA][gG][iI][nN][aA][sS][_][pP][oO][pP][uU][lL][aA][rR][eE][sS]
Componente = [cC][oO][mM][pP][oO][nN][eE][nN][tT][eE]
Todos = [tT][oO][dD][oO][sS]
Titulo = [tT][iI][tT][uU][lL][oO]
Parrafo = [pP][aA][rR][rR][aA][fF][oO]
Imagen = [iI][mM][aA][gG][eE][nN]
Video = [vV][iI][dD][eE][oO]
Menu = [mM][eE][nN][uU]
Palabra = [[_]|[-]|[$]][[a-zA-Z]|[0-9]][[a-zA-Z][0-9]]*
Saltos = \r|\n|\r\n
Espacios = {Saltos} | [ \t\f]

%% // Separador de Area

<YYINITIAL> {

    "\""                  { return symbol(sym.COMILLA, yytext()); }

    ","                   { return symbol(sym.COMA, yytext()); }

    "."                   { return symbol(sym.PUNTO, yytext()); }
    
    ";"                   { return symbol(sym.PUNTO_Y_COMA, yytext()); }

    {Consultar}           { return symbol(sym.CONSULTAR, yytext()); }

    {Sitio}               { return symbol(sym.VISITAS_SITIO, yytext()); }
    
    {Pagina}              { return symbol(sym.VISITAS_PAGINA, yytext()); }

    {Populares}           { return symbol(sym.PAGINAS_POPULARES, yytext()); }
     
    {Componente}          { return symbol(sym.COMPONENTE, yytext()); }

    {Todos}               { return symbol(sym.TODOS, yytext()); }
    
    {Titulo}              { return symbol(sym.TITULO, yytext()); }

    {Parrafo}             { return symbol(sym.PARRAFO, yytext()); }

    {Imagen}              { return symbol(sym.IMAGEN, yytext()); }

    {Video}               { return symbol(sym.VIDEO, yytext()); }

    {Menu}                { return symbol(sym.MENU, yytext()); }

    {Palabra}	          { return symbol(sym.PALABRA, yytext()); }

    {Espacios}            {/* ignoramos */}
}

[^]                       { throw new Error("Error Léxico caracter Invalido en la linea " + (yyline+1) + ", columna " + (yycolumn+1) + ": " + yytext()); } 