# EDTS

Implementar un EDTS para una GIC que suma, resta, multiplique y divida.

## Diseño de la gramática

```
E -> E AddSum T | T
T -> T MulDiv F | F
F -> (E) | num | id
```
Aplicada en Expr.g4:

<img width="731" height="665" alt="image" src="https://github.com/user-attachments/assets/b3f1cda9-b9ea-4ff9-bf42-06a39f5bd2c7" />


## Definir atributos

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

## Calcular los conjuntos: F,S,P,

