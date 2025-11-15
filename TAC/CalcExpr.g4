grammar CalcExpr;

prog: stat+ ;

stat
    : decl                     # declStat
    | assign                   # assignStat
    | ifStmt                   # ifStat
    | whileStmt                # whileStat
    | block                    # blockStat
    | expr ';'                 # exprStat
    | ';'                      # emptyStat
    ;

decl
    : 'int' ID ';'
    ;

assign
    : ID '=' expr ';'
    ;

ifStmt
    : 'if' '(' expr ')' stat ('else' stat)?
    ;

whileStmt
    : 'while' '(' expr ')' stat
    ;

block
    : '{' stat* '}'
    ;

expr
    : expr '||' expr           # logicOr
    | expr '&&' expr           # logicAnd
    | '!' expr                 # logicNot
    | expr relop expr          # relExpr
    | expr op=('*'|'/') expr   # MulDiv
    | expr op=('+'|'-') expr   # AddSub
    | '(' expr ')'             # Parens
    | ID                       # Id
    | INT                      # Int
    ;

relop
    : '<' | '>' | '<=' | '>=' | '==' | '!='
    ;

ID  : [a-zA-Z_][a-zA-Z0-9_]* ;
INT : [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;

