# EDTS

Implementar un EDTS (gramática + las acciones que dicen qué hacer al reconocer cada regla) para una GIC que suma, resta, multiplique y divida.

## Diseño de la gramática

```
E -> E AddSum T | T
T -> T MulDiv F | F
F -> (E) | num | id
```
Aplicada en Expr.g4:

<img width="731" height="665" alt="image" src="https://github.com/user-attachments/assets/b3f1cda9-b9ea-4ff9-bf42-06a39f5bd2c7" />


## Definir atributos, implementación y EDTS

Valores asociados a símbolos, qué información es (valor, tipo, etc.)

- **E → E1 + T:** E.valor = E1.valor + T.valor
- **E → E1 - T:**	E.valor = E1.valor - T.valor
- **E → T:**	E.valor = T.valor
- **T → T1 * F:**	T.valor = T1.valor * F.valor
- **T → T1 / F:**	T.valor = T1.valor / F.valor
- **T → F:**	T.valor = F.valor
- **F → NUM:**	F.valor = NUM.lexval (el valor literal)
- **F → ID:**	F.valor = tabla[ID.lexema] (variable)
- **F → (E):**	F.valor = E.valor

Implementación y EDTS.

<img width="1020" height="954" alt="image" src="https://github.com/user-attachments/assets/1db1f7a2-c8d7-4084-a558-18d1c74f227f" />


## Calcular los conjuntos: F,S,P

### Primeros
- Primeros(F) = {id, num, (}
- Primeros(T) = {id, num, (}
- Primeros(E) = {id, num, (}

### Siguientes
- Siguientes(E) = {$, addSum, )}
- Siguientes(T) = {MulDiv, id, num, (}
- Siguientes(F) = {id, num, (}

### Predicción
- Predicción(E -> E AddSum T) = {id, num, (}
- Predicción(E -> T) = {id, num, (}
- Predicción(T -> T MulDiv F) = {id, num, (}
- Predicción(T -> F) = {id, num, (}
- Predicción(F -> (E)) = {(}
- Predicción(F -> num) = {num}
- Predicción(F -> id) = {id}


## Generar el AST decorado

ANTLR ya genera el arbol.

<img width="802" height="527" alt="image" src="https://github.com/user-attachments/assets/6c9ba4a8-b762-4b66-a283-c8456c7f1585" />

Con:
- ParseTree tree = parser.prog();
- EvalVisitor eval = new EvalVisitor();
- eval.visit(tree);


## Generar la tabla de símbolos

Tabla con las variables asignadas.

<img width="695" height="149" alt="image" src="https://github.com/user-attachments/assets/e25f967e-b83c-4717-a242-649fd3d07c74" />

<img width="341" height="137" alt="image" src="https://github.com/user-attachments/assets/fe610aab-f6fa-4ec8-a5a5-9dce78e247c2" />


## Pruebas

Generar Visitor:

<img width="1171" height="61" alt="image" src="https://github.com/user-attachments/assets/58e949bc-b9c7-4451-816a-f2a0898807b5" />

```
antlr4 -no-listener -visitor CalcExpr.g4 
javac Calc.java CalcExpr*.java
```

<br>

Resultado:

<img width="1252" height="903" alt="image" src="https://github.com/user-attachments/assets/f128ddc3-c6db-4a8b-ba42-c5d0611771b8" />
