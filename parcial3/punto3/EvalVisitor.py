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

    def visitVarReference(self, ctx):
        name = ctx.ID().getText()
        if name not in self.memory:
            raise Exception(f"Variable '{name}' no existe")
        return self.memory[name]

    def visitAssign(self, ctx):
        name = ctx.ID().getText()
        value = self.visit(ctx.expr())
        self.memory[name] = value
        return value

    # declaration
    def visitDecl(self, ctx):
        name = ctx.ID().getText()
        value = self.visit(ctx.matrix())
        self.memory[name] = value
        return value

    # matriz multiplication A @ B
    def visitMatMulExpr(self, ctx):
        A = self.visit(ctx.expr(0))
        B = self.visit(ctx.expr(1))

        if A is None or B is None:
            raise Exception("Error: alguna matriz es None")

        rowsA = len(A)
        colsA = len(A[0])
        rowsB = len(B)
        colsB = len(B[0])

        if colsA != rowsB:
            raise Exception(f"Incompatibles: {colsA} != {rowsB}")

        result = [[0 for _ in range(colsB)] for _ in range(rowsA)]

        for i in range(rowsA):
            for j in range(colsB):
                for k in range(colsA):
                    result[i][j] += A[i][k] * B[k][j]

        return result

    # print
    def visitPrintStmt(self, ctx):
        value = self.visit(ctx.expr())
        print(value)
        return value
