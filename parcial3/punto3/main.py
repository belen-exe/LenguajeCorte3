import sys
from antlr4 import *
from MatrixLexer import MatrixLexer
from MatrixParser import MatrixParser
from EvalVisitor import EvalVisitor

def main(argv):
    if len(argv) < 2:
        print("Uso: python3 main.py archivo.expr")
        return

    input_file = argv[1]

    input_stream = FileStream(input_file, encoding='utf-8')

    lexer = MatrixLexer(input_stream)
    token_stream = CommonTokenStream(lexer)

    parser = MatrixParser(token_stream)
    tree = parser.program()

    visitor = EvalVisitor()
    visitor.visit(tree)

if __name__ == '__main__':
    main(sys.argv)
