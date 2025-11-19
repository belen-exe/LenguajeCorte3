grammar Matrix;

program
    : statement* EOF
    ;

statement
    : decl ';'
    | assign ';'
    | printStmt ';'
    ;

decl
    : 'mat' ID '=' matrix
    ;

assign
    : ID '=' expr
    ;

printStmt
    : 'print' '(' expr ')'
    ;

expr
    : expr '@' expr        # MatMulExpr
    | matrix               # MatrixLiteralExpr
    | ID                   # VarReference
    | '(' expr ')'         # ParenExpr
    ;

matrix
    : '[' rowList ']'      # MatrixValue
    ;

rowList
    : row (',' row)*
    ;

row
    : '[' numberList ']'   # RowValue
    ;

numberList
    : NUMBER (',' NUMBER)*
    ;

NUMBER
    : '-'? DIGIT+ ('.' DIGIT+ )?
    ;

ID
    : LETTER (LETTER | DIGIT | '_')*
    ;

WS
    : [ \t\r\n]+ -> skip
    ;

fragment DIGIT : [0-9];
fragment LETTER: [a-zA-Z];

