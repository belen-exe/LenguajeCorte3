# Parcial 3

## Punto 1

Gramática para consultas tipo SQL

Operaciones posibles:
- CREATE (new) - Insertar registros
- READ (get) - Consultar registros
- UPDATE (set) - Actualizar registros
- DELETE (drop) - Eliminar registros
- WHERE - Filtros condicionales en todas las operaciones
- Múltiples tablas - Hasta 10 tablas simultáneas
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

