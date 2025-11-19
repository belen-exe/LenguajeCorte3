# Parcial 3

## Punto 1

Gramática para consultas tipo SQL

Operaciones posibles:
- CREATE (new) - Insertar registros
- READ (get) - Consultar registros
- UPDATE (set) - Actualizar registros
- DELETE (drop) - Eliminar registros
- WHERE - Filtros condicionales en todas las operaciones
- Tipos de datos - Números, strings, booleanos, null

Estrutura general:
- programa → sentencias
- sentencias → sentencia | sentencias sentencia
- sentencia → create_op | read_op | update_op | delete_op

Insertar (CREATE):

```
new users { name: "Ana", age: 25, active: true }
new users { name: "Carlos", age: 30, active: true }
new users { name: "María", age: 22, active: false }
new users { name: "Pedro", age: 17, active: true }


new products { name: "Laptop", price: 1200000, stock: 15 }
new products { name: "Mouse", price: 25000, stock: 50 }
new products { name: "Teclado", price: 88000, stock: 0 }
```

**Resultados:**

Para correr:

<img width="986" height="116" alt="image" src="https://github.com/user-attachments/assets/b721a783-b358-4b96-a3b3-475ecad2e1d3" />

```
make NQL | bison -d NQL.y | flex NQL.l
cc -o nql NQL.tab.c lex.yy.c -lfl
./nql test.nql
```

<img width="1679" height="800" alt="image" src="https://github.com/user-attachments/assets/a628ef6a-35cf-4f98-a6c6-af431d339f9b" />


## Punto 2

Gramática de multiplicación de matrices:

![WhatsApp Image 2025-11-19 at 4 25 10 PM](https://github.com/user-attachments/assets/e4030f67-fee6-4f48-9542-823685d337f1)


## Punto 3

Aplicado en ANTLR:

<img width="769" height="857" alt="image" src="https://github.com/user-attachments/assets/ec57da05-8f27-44ca-9c68-5f7d5a132f5d" />

Multiplicación en EvalVisitor con comprobación de diferentes dimensiones:

<img width="653" height="564" alt="image" src="https://github.com/user-attachments/assets/7e5d2966-096e-4d59-a7eb-994ef0a65195" />

<br>
<br>
<br>

**Resultados:**

Ejemplo a mano:

![WhatsApp Image 2025-11-19 at 4 35 05 PM](https://github.com/user-attachments/assets/a9f8ae4b-40ef-45f1-a2e2-73c286376170)

<br>

Pasado a codigo:

<img width="1805" height="425" alt="image" src="https://github.com/user-attachments/assets/571762df-f8a3-44b2-a95c-e5cbefea3075" />
