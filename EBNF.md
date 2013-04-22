The Syntax
==========

Here is a brief EBNF for the macrosyntax.  Here syntax categories and compound tokens are shown in all 
caps (NB: boolean tokens `T` and `F` are exceptions). Reserved tokens and symbols are always quoted, 
since our reserved tokens are symbols.  

Regex notation is used in the Microsyntax.

Macrosyntax
-----------

    SCRIPT        --> (BR)* (STMT BR)+ EOF
    STMT          -->  VARDEC 
                    |  ASSIGNMENT
                    |  SWAP
                    |  PRINTSTMT
                    |  BREAKSTMT
                    |  IFSTMT
                    |  LOOP
                    |  FUNCALL
    
    VARDEC        -->  TYPE ID ('!')? ':=' EXP 
    TYPE          -->  NUMTYPE | STRTYPE | BOOLTYPE | FUNTYPE | NULLTYPE
    NUMTYPE       --> '#'
    STRTYPE       --> '$'
    BOOLTYPE      --> '^'
    FUNTYPE       --> 'f'
    NULLTYPE      --> '~'
    
    ASSIGNMENT    -->  ID ':=' EXP
    SWAP          -->  ID ':=:' ID
    PRINTSTMT     -->  'p:' EXP
    BREAKSTMT     -->  '!!' 
    IFSTMT        -->  '??:' EXP '?' STMT (':' EXP '?' STMT)* (':' (STMT))? '??' 
    LOOP          -->  FORLOOP | INFINITELOOP
    FORLOOP       -->  '8:' (RANGE)? (ANONFUN | ID BLOCK)
    INFINITELOOP  -->  '8:' BLOCK
    FUNCALL       -->  ID '(' PARAMS ')'
    PARAMS        -->  (EXP (',' EXP)*)*
    BLOCK         -->  '{' (STMT BR)* '}'
    
    EXP           -->  EXP1 ('||' EXP1)*
    EXP1          -->  EXP2 ('&&' EXP2)* 
    EXP2          -->  EXP3 (RELOP EXP3)?
    EXP3          -->  EXP4 (EXPNOP EXP4)*
    EXP4          -->  EXP5 (MULOP EXP5)*
    EXP5          -->  EXP6 (ADDOP EXP6)*
    EXP6          -->  EXP7 (('..'|'...') EXP7)?
    EXP7          -->  LIT | VAR | ARRAY | OBJECT | ANONFUN
    
    LIT           -->  'T' | 'F' | NUMLIT | STRINGLIT
    VAR           -->  ID ( '.' ID  | '(' PARAMS ')' | '[' EXP ']' )*
    ARRAY         -->  '[' EXP* (',' EXP)* ']'
    OBJECT        -->  ID '{' (ID ':' EXP) (',' ID ':' EXP)* '}'
    ANONFUN       -->  'f:' (PARAMS '->')? BLOCK
    
    EXPNOP        -->  '**'
    MULOP         -->  '*' | '/' | '%' 
    ADDOP         -->  '+' | '-'
    RELOP         -->  '<' | '<=' | '==' | '!=' | '>=' | '>' 
    TYPE          -->  '^' | '$' | '#' | 'f' | '~'
    
Microsyntax
-----------

    BR            -->  ';'
    COMMENT       -->  'c:'  ( )*   NEWLINE
    ID            -->  CHARLIT+ ([-_a-Z0-9])*
    NUMLIT        -->  [0-9]+ ('.' [0-9]*)?
    STRINGLIT     -->  (CHARLIT | NUMLIT) +
    CHARLIT       -->  [a-Z]
    REGEX         -->  '/' [^/]*  '/'
