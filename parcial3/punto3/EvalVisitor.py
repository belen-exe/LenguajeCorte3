from MatrixVisitor import MatrixVisitor

class EvalVisitor(MatrixVisitor):
    def __init__(self):
        self.memory = {}

    def visitMatrixValue(self, ctx):
        rows = []
        for rowCtx in ctx.rowList().row():
            nums = [float(n.getText()) for n in rowCtx.numberList().NUMBER()]
            rows.append(nums)
        return rows

    def visitMatrixLiteralExpr(self, ctx):
        return self.visit(ctx.matrix())

    # variable
    def visitVarReference(self, ctx):
        name = ctx.ID().getText()
        if name not in self.memory:
            print(f"Error: variable '{name}' no existe")
            return None
        return self.memory[name]

    # asignación
    def visitAssign(self, ctx):
        name = ctx.ID().getText()
        value = self.visit(ctx.expr())
        self.memory[name] = value
        return value

    # declaración: mat A = [[...]];
    def visitDecl(self, ctx):
        name = ctx.ID().getText()
        value = self.visit(ctx.matrix())
        self.memory[name] = value
        return value

    # A @ B
    def visitMatMulExpr(self, ctx):
        try:
            A = self.visit(ctx.expr(0))
            B = self.visit(ctx.expr(1))

            if A is None or B is None:
                print("Error: una de las matrices es None")
                return None

            rowsA = len(A)
            colsA = len(A[0])
            rowsB = len(B)
            colsB = len(B[0])

            if colsA != rowsB:
                print(f"Incompatibles: {colsA} != {rowsB}")
                return None

            # producto
            result = [[0 for _ in range(colsB)] for _ in range(rowsA)]
            for i in range(rowsA):
                for j in range(colsB):
                    for k in range(colsA):
                        result[i][j] += A[i][k] * B[k][j]

            return result

        except Exception as e:
            print(f"Error al multiplicar matrices: {e}")
            return None

    # print
    def visitPrintStmt(self, ctx):
        try:
            value = self.visit(ctx.expr())
            print(value)
            return value
        except Exception as e:
            print(f"Error en print: {e}")
            return None
