package com.mycompany.aplicacioncliente.main.analizadoresxml;

import java_cup.runtime.*;

%% // Separador de Area

%class LexicoXML
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

Saltos = \r|\n|\r\n
Espacios = {Saltos} | [ \t\f]
Atributos = [aA][tT][rR][iI][bB][uU][tT][oO][sS]
Etiquetas = [eE][tT][iI][qQ][uU][eE][tT][aA][sS]
Palabra = [[_]|[-]|[$]][[a-zA-Z]|[0-9]][[a-zA-Z][0-9]]*
Contenido = [a-zA-Z][[a-zA-Z]|[0-9]]*
Color = [#][[a-zA-Z]|[0-9]]*
Url = [h][t][t][p][s][[a-zA-Z]|[0-9]|[-]|[_]|[ ]|[.]|[/]|[?]|[=]|[:]|[@]]*
Fecha = [0-9][0-9][0-9][0-9][/][0-9][0-9][/][0-9][0-9]
Numero = [0-9][0-9]*

%% // Separador de Area

<YYINITIAL> {
	
    NUEVO_SITIO_WEB       { return symbol(sym.NUEVO_SITIO, yytext()); }

    NUEVA_PAGINA          { return symbol(sym.NUEVA_PAGINA, yytext()); }
    
    BORRAR_SITIO_WEB      { return symbol(sym.BORRAR_SITIO, yytext()); }

    BORRAR_PAGINA         { return symbol(sym.BORRAR_PAGINA, yytext()); }

    MODIFICAR_PAGINA      { return symbol(sym.MODIFICAR_PAGINA, yytext()); }
    
    AGREGAR_COMPONENTE    { return symbol(sym.AGREGAR_COMPONENTE, yytext()); }
    
    BORRAR_COMPONENTE     { return symbol(sym.BORRAR_COMPONENTE, yytext()); }

    MODIFICAR_COMPONENTE  { return symbol(sym.MODIFICAR_COMPONENTE, yytext()); }

    ID                    { return symbol(sym.ID, yytext()); }
    
    TITULO                { return symbol(sym.TITULO, yytext()); }

    SITIO                 { return symbol(sym.SITIO, yytext()); }

    PADRE                 { return symbol(sym.PADRE, yytext()); }

    PARRAFO               { return symbol(sym.PARRAFO, yytext()); }

    IMAGEN                { return symbol(sym.IMAGEN, yytext()); }

    VIDEO                 { return symbol(sym.VIDEO, yytext()); }

    MENU                  { return symbol(sym.MENU, yytext()); }

    USUARIO_CREACION      { return symbol(sym.USUARIO_C, yytext()); }

    FECHA_CREACION        { return symbol(sym.FECHA_C, yytext()); }

    FECHA_MODIFICACION    { return symbol(sym.FECHA_M, yytext()); }

    USUARIO_MODIFICACION  { return symbol(sym.USUARIO_M, yytext()); }

    PAGINA                { return symbol(sym.PAGINA, yytext()); }
    
    CLASE                 { return symbol(sym.CLASE, yytext()); }

    TEXTO                 { return symbol(sym.TEXTO, yytext()); }

    ALINEACION            { return symbol(sym.ALINEACION, yytext()); }

    IZQUIERDA             { return symbol(sym.IZQUIERDA, yytext()); }

    CENTRAR               { return symbol(sym.CENTRAR, yytext()); }

    DERECHA               { return symbol(sym.DERECHA, yytext()); }

    COLOR                 { return symbol(sym.COLOR, yytext()); }

    ALTURA                { return symbol(sym.ALTURA, yytext()); }

    ANCHO                 { return symbol(sym.ANCHO, yytext()); }

    COLOR                 { return symbol(sym.COLOR, yytext()); }

    ORIGEN                { return symbol(sym.ORIGEN, yytext()); }

    acciones              { return symbol(sym.ACCIONES, yytext()); }

    accion                { return symbol(sym.ACCION, yytext()); }

    parametros            { return symbol(sym.PARAMETROS, yytext()); }

    parametro             { return symbol(sym.PARAMETRO, yytext()); }

    atributos             { return symbol(sym.ATRIBUTOS, yytext()); }

    atributo              { return symbol(sym.ATRIBUTO, yytext()); }

    etiqueta              { return symbol(sym.ETIQUETA, yytext()); }

    nombre                { return symbol(sym.NOMBRE, yytext()); }

    valor                { return symbol(sym.VALOR, yytext()); }

    "\""                  { return symbol(sym.COMILLA, yytext()); }

    "<"	                  { return symbol(sym.MENOR, yytext()); }

    "</" 	          { return symbol(sym.MENOR_DIAGONAL, yytext()); }

    ">"	                  { return symbol(sym.MAYOR, yytext()); }

    "/>"	          { return symbol(sym.MAYOR_DIAGONAL, yytext()); }

    "["                   { return symbol(sym.CORCHETE_I, yytext()); }

    "]"                   { return symbol(sym.CORCHETE_D, yytext()); }

    "="                   { return symbol(sym.IGUAL, yytext()); }

    {Atributos}           { return symbol(sym.ATRIBUTOS, yytext()); }
    
    {Etiquetas}           { return symbol(sym.ETIQUETAS, yytext()); }

    {Palabra}	          { return symbol(sym.PALABRA, yytext()); }

    {Contenido}	          { return symbol(sym.CONTENIDO, yytext()); }

    {Color}	          { return symbol(sym.NUMERO_COLOR, yytext()); }

    {Url}	          { return symbol(sym.URL, yytext()); }

    {Fecha}	          { return symbol(sym.FECHA, yytext()); }

    {Numero}	          { return symbol(sym.NUMERO, yytext()); }

    {Espacios}            {/* ignoramos */}
}

[^]                       { throw new Error("Error Léxico caracter Invalido en la linea " + (yyline+1) + ", columna " + (yycolumn+1) + ": " + yytext()); } 