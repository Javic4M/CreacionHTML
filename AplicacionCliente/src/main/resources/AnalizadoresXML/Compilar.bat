echo "STARTING JFLEX COMPILING"
java -jar "C:\jflex-1.9.1\lib\jflex-full-1.9.1.jar" AnalizadorLexico.flex

echo "STARTING CUP COMPILING"
java -jar "C:\java-cup-11b.jar" -parser SintacticoXML AnalizadorSintactico.cup

pause
move LexicoXML.java "..\..\java\com\mycompany\aplicacioncliente\main\analizadoresxml"
move SintacticoXML.java "..\..\java\com\mycompany\aplicacioncliente\main\analizadoresxml"
move sym.java "..\..\java\com\mycompany\aplicacioncliente\main\analizadoresxml"