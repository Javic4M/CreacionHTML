echo "STARTING JFLEX COMPILING"
java -jar "C:\jflex-1.9.1\lib\jflex-full-1.9.1.jar" AnalizadorLexicoConsultas.flex

echo "STARTING CUP COMPILING"
java -jar "C:\java-cup-11b.jar" -parser SintacticoConsultas AnalizadorSintacticoConsultas.cup

pause
move LexicoConsultas.java "..\..\java\com\mycompany\aplicacioncliente\main\analizadoresconsultas"
move SintacticoConsultas.java "..\..\java\com\mycompany\aplicacioncliente\main\analizadoresconsultas"
move sym.java "..\..\java\com\mycompany\aplicacioncliente\main\analizadoresconsultas"
