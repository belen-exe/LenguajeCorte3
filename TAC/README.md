# Código a tres dimensiones (TAC) con EDTS

La gramática de C (mini-C para esta tarea) se implementará en un EDTS con código de tres direcciones para el árbol.
- Código de tres direcciones: Es una representación intermedia del código, que los compiladores generan ANTES de generar ensamblador. Para este caso, en vez de una AST, se usa esto para simplificar el arbol que se genera en instrucciones más simples.

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




