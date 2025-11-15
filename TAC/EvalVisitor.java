import java.util.*;

public class EvalVisitor extends CalcExprBaseVisitor<Result> {

    // tabla simbolos
    Map<String, Double> memory = new HashMap<>();

    // TAC
    List<String> tac = new ArrayList<>();
    int tempCount = 0;
    int labelCount = 0;

    String newTemp() {
        return "t" + (tempCount++);
    }

    String newLabel() {
        return "L" + (labelCount++);
    }

    void emit(String s) {
        tac.add(s);
    }

    // ast
    private void printNode(String label, int indent) {
        System.out.println(" ".repeat(indent) + label);
    }

    @Override
    public Result visitDeclStat(CalcExprParser.DeclStatContext ctx) {
        String id = ctx.decl().ID().getText();
        memory.put(id, 0.0);
        printNode("Decl: int " + id, 2);
        return new Result(0.0, id);
    }

    @Override
    public Result visitAssignStat(CalcExprParser.AssignStatContext ctx) {
        String id = ctx.assign().ID().getText();
        Result r = visit(ctx.assign().expr());
        emit(id + " = " + r.place);
        memory.put(id, r.value);
        printNode("Assign: " + id + " = " + r.value, 2);
        return new Result(r.value, id);
    }

    @Override
    public Result visitExprStat(CalcExprParser.ExprStatContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Result visitInt(CalcExprParser.IntContext ctx) {
        double val = Double.valueOf(ctx.INT().getText());
        String t = newTemp();
        emit(t + " = " + val);
        printNode("Int: " + val, 4);
        return new Result(val, t);
    }

    @Override
    public Result visitId(CalcExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        double val = memory.getOrDefault(id, 0.0);
        printNode("Id: " + id + " (" + val + ")", 4);
        return new Result(val, id);
    }

    @Override
    public Result visitAddSub(CalcExprParser.AddSubContext ctx) {
        Result left = visit(ctx.expr(0));
        Result right = visit(ctx.expr(1));
        String t = newTemp();
        emit(t + " = " + left.place + " " + ctx.op.getText() + " " + right.place);
        double val = ctx.op.getText().equals("+") ? left.value + right.value : left.value - right.value;
        printNode("AddSub: " + val, 2);
        return new Result(val, t);
    }

    @Override
    public Result visitMulDiv(CalcExprParser.MulDivContext ctx) {
        Result left = visit(ctx.expr(0));
        Result right = visit(ctx.expr(1));
        String t = newTemp();
        emit(t + " = " + left.place + " " + ctx.op.getText() + " " + right.place);
        double val = ctx.op.getText().equals("*") ? left.value * right.value : left.value / right.value;
        printNode("MulDiv: " + val, 2);
        return new Result(val, t);
    }

    // operadores
    @Override
    public Result visitRelExpr(CalcExprParser.RelExprContext ctx) {
        Result l = visit(ctx.expr(0));
        Result r = visit(ctx.expr(1));
        String t = newTemp();
        String op = ctx.relop().getText();
        emit(t + " = " + l.place + " " + op + " " + r.place);

        boolean res = switch (op) {
            case "<" -> l.value < r.value;
            case ">" -> l.value > r.value;
            case "<=" -> l.value <= r.value;
            case ">=" -> l.value >= r.value;
            case "==" -> l.value.equals(r.value);
            case "!=" -> !l.value.equals(r.value);
            default -> false;
        };

        return new Result(res ? 1.0 : 0.0, t);
    }

    @Override
    public Result visitLogicAnd(CalcExprParser.LogicAndContext ctx) {
        Result l = visit(ctx.expr(0));
        Result r = visit(ctx.expr(1));
        String t = newTemp();
        emit(t + " = " + l.place + " && " + r.place);
        return new Result((l.value != 0 && r.value != 0) ? 1.0 : 0.0, t);
    }

    @Override
    public Result visitLogicOr(CalcExprParser.LogicOrContext ctx) {
        Result l = visit(ctx.expr(0));
        Result r = visit(ctx.expr(1));
        String t = newTemp();
        emit(t + " = " + l.place + " || " + r.place);
        return new Result((l.value != 0 || r.value != 0) ? 1.0 : 0.0, t);
    }

    @Override
    public Result visitLogicNot(CalcExprParser.LogicNotContext ctx) {
        Result e = visit(ctx.expr());
        String t = newTemp();
        emit(t + " = !" + e.place);
        return new Result(e.value == 0 ? 1.0 : 0.0, t);
    }

    // if/else
    @Override
    public Result visitIfStat(CalcExprParser.IfStatContext ctx) {
        String L_else = newLabel();
        String L_end = newLabel();

        Result cond = visit(ctx.ifStmt().expr());
        emit("if " + cond.place + " == 0 goto " + L_else);

        visit(ctx.ifStmt().stat(0));
        emit("goto " + L_end);

        emit(L_else + ":");
        if (ctx.ifStmt().stat().size() > 1)
            visit(ctx.ifStmt().stat(1));

        emit(L_end + ":");
        return new Result(0.0, null);
    }

    // while
    @Override
    public Result visitWhileStat(CalcExprParser.WhileStatContext ctx) {
        String L_start = newLabel();
        String L_end = newLabel();

        emit(L_start + ":");
        Result cond = visit(ctx.whileStmt().expr());
        emit("if " + cond.place + " == 0 goto " + L_end);

        visit(ctx.whileStmt().stat());
        emit("goto " + L_start);
        emit(L_end + ":");

        return new Result(0.0, null);
    }

    // bloque
    @Override
    public Result visitBlockStat(CalcExprParser.BlockStatContext ctx) {
        for (var s : ctx.block().stat())
            visit(s);
        return new Result(0.0, null);
    }

    public void printSymbolTable() {
        System.out.println("\n--- Tabla de Símbolos ---");
        memory.forEach((id, val) -> System.out.println(id + " = " + val));
        System.out.println("\n--- TAC ---");
        tac.forEach(System.out::println);
    }
}

