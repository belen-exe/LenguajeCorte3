# Código a tres dimensiones (TAC) con EDTS

La gramática de C (mini-C para esta tarea) se implementará en un EDTS con código de tres direcciones para el árbol.
- Código de tres direcciones: Es una representación intermedia del código, que los compiladores generan antes de generar ensamblador. Para este caso, en vez de un AST, se usa esto para simplificar el arbol que se genera en instrucciones más simples.

## Gramática

<img width="500" height="750" alt="image" src="https://github.com/user-attachments/assets/587cec6b-eb97-40e5-9c31-042ec6d8a243" />
<img width="500" height="750" alt="image" src="https://github.com/user-attachments/assets/394d701d-508e-41fb-99c1-32c5299d18cb" />

Este mini-C incluye.
1. Las sentencias:
     - Declaraciones: int x;
     - Asignaciones: x = expr;
     - If/else
     - While (L0, L1, etc.)
     - Bloques {}
     - Expresiones solas
     - Sentencias vacías

2. Creación variables enteras y modificación.
3. Expresiones aritméticas:
     - Operadores lógicos: ||, &&, !
     - Comparadores: <, >, <=, >=, ==, !=
     - Operadores aritméticos: + - * /
     - Paréntesis
     - Identificadores
     - Números enteros
  
4. Tokens
     - ID permite nombres de variables estilo C.
     - INT captura enteros.
     - relop define los operadores relacionales.
     - WS ignora espacios y saltos de línea.


## EDTS, tabla de símbolos y TAC

Cada visita imprime un nodo del árbol con indentación, mostrando:
- tipo de operación
- resultado calculado
- estructura del programa

<br>

Imprime la tabla de símbolos:

<p align="center">
  <img width="400" height="100" alt="image" src="https://github.com/user-attachments/assets/8f2edc0a-cf94-4c5f-8a22-c014dc654d4a" />
</p>
<br>

La generación del código en tres dimensiones.

Cada regla semántica de la gramática tiene su traducción:
- Operaciones aritméticas: generan instrucciones t = x op y
- Relacionales y lógicas: producen 1 (true) o 0 (false)
- if/else: usan goto y etiquetas
- while: genera un ciclo con saltos

Para la expresión:
```
int x;
x = 3 + 4 * 5;
```
<p align="center">
  <img width="250" height="250" alt="image" src="https://github.com/user-attachments/assets/09fd6350-df7d-4da0-89e6-0612f85a288d" />
</p>

Toda la salida TAC se almacena en una lista y se imprime al final.

## Pruebas

t.expr:

<p align="center">
  <img width="808" height="303" alt="image" src="https://github.com/user-attachments/assets/7a4fd21b-9b45-440b-b6ba-61fc47d5fb9e" />
</p>

<br>
<br>

EDTS:

<p align="center">
  <img width="883" height="757" alt="image" src="https://github.com/user-attachments/assets/b72e9b6d-88e2-47c9-a406-0a45f2da7330" />
</p>


<br>
<br>

TAC:

<p align="center">
  <img width="468" height="770" alt="image" src="https://github.com/user-attachments/assets/5f01fff6-da63-42b7-8d13-2bab6c93dec8" />
</p>


:P
