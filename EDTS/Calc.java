import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Calc {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];
        InputStream is = inputFile != null ? new FileInputStream(inputFile) : System.in;

        CharStream input = CharStreams.fromStream(is);
        CalcExprLexer lexer = new CalcExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcExprParser parser = new CalcExprParser(tokens);
        ParseTree tree = parser.prog();

        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
        eval.printSymbolTable();
    }
}

