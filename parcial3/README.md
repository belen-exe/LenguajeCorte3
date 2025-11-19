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



