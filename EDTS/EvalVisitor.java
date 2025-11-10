import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends CalcExprBaseVisitor<Double> {
    Map<String, Double> memory = new HashMap<>();

    // Mostrar el AST decorado (indentación)
    private void printNode(String label, int indent) {
        System.out.println(" ".repeat(indent) + label);
    }

    @Override
    public Double visitAssign(CalcExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        double value = visit(ctx.expr());
        memory.put(id, value);
        printNode("Assign: " + id + " = " + value, 2);
        return value;
    }

    @Override
    public Double visitPrintExpr(CalcExprParser.PrintExprContext ctx) {
        Double value = visit(ctx.expr());
        printNode("Result: " + value, 0);
        return value;
    }

    @Override
    public Double visitInt(CalcExprParser.IntContext ctx) {
        double val = Double.valueOf(ctx.INT().getText());
        printNode("Int: " + val, 4);
        return val;
    }

    @Override
    public Double visitId(CalcExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        double val = memory.getOrDefault(id, 0.0);
        printNode("Id: " + id + " (" + val + ")", 4);
        return val;
    }

    @Override
    public Double visitMulDiv(CalcExprParser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        double val = ctx.op.getType() == CalcExprParser.MUL ? left * right : left / right;
        printNode("MulDiv: " + val, 2);
        return val;
    }

    @Override
    public Double visitAddSub(CalcExprParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        double val = ctx.op.getType() == CalcExprParser.ADD ? left + right : left - right;
        printNode("AddSub: " + val, 2);
        return val;
    }

    @Override
    public Double visitParens(CalcExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    public void printSymbolTable() {
        System.out.println("\n--- Tabla de Símbolos ---");
        memory.forEach((id, val) -> System.out.println(id + " = " + val));
    }
}

