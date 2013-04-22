The Syntax
==========

Here is a brief EBNF for the macrosyntax.  Here syntax categories and compound tokens are shown in all 
caps (NB: boolean tokens `T` and `F` are exceptions). Reserved tokens and symbols are always quoted, 
since our reserved tokens are symbols.  

Regex notation is used in the Microsyntax.

Macrosyntax
-----------

    SCRIPT        --> (BR)* (STMT BR)+ EOF
    STMT          -->  DEC 
                    |  ASSIGNMENT
                    |  PRINTSTMT
                    |  IFSTMT
                    |  BREAKSTMT
                    |  LOOP
                    |  FUNCALL
                    |  EXP
    DEC           -->  VARDEC | CONSTDEC
    VAR           -->  ID ( '.' ID  | '(' PARAMS ')' | '[' EXP ']' )*
    VARDEC        -->  TYPE ID ':=' EXP 
    CONSTDEC      -->  TYPE ID '!' ':=' EXP 
    TYPE          -->  NUMTYPE | STRTYPE | BOOLTYPE | FUNTYPE | NULLTYPE
    RETURNTYPE    -->  TYPE
    PARAMS        -->  (EXP (',' EXP)*)*
    ASSIGNMENT    -->  ID ':=' EXP | SWAP
    SWAP          -->  ID ':=:' ID
    PRINTSTMT     -->  'p:' EXP
    IFSTMT        -->  '??:' EXP '?' STMT (':' EXP '?' STMT)* (':' (STMT))? '??' 
    BREAKSTMT     -->  '!!' 
    LOOP          -->  FORLOOP | INFINITELOOP
    FORLOOP       -->  '8:' (RANGE)? (ANONFUN | (ID BLOCK))
    INFINITELOOP  -->  '8:' BLOCK
    FUNCALL       -->  (ID '('PARAMS')') | ANONFUN
    BLOCK         -->  '{' (STMT BR)* '}'
    BOOL          -->  'T' | 'F'
    ARRAY         -->  '[' EXP* (',' EXP)* ']'
    ARRAYREF      -->  ID '[' EXP ']'
    OBJECT        -->  ID '{' (ID ':' EXP)(ID ':' EXP ',')* '}'
    ANONFUN       -->  'f:' (PARAMS '->')? BLOCK
    EXP           -->  EXP1 ('||' EXP1)*
    EXP1          -->  EXP2 ('&&' EXP2)* 
    EXP2          -->  EXP3 (RELOP EXP3)?
    EXP3          -->  EXP4 (EXPNOP EXP4)*
    EXP4          -->  EXP5 (MULOP EXP5)*
    EXP5          -->  EXP6 (ADDOP EXP6)*
    EXP6          -->  EXP7 (('..'|'...') EXP7)?
    EXP7          -->  LIT | VAR | ARRAY | HASH | ANONFUN | OBJECT
    EXPNOP        -->  '**'
    MULOP         -->  '*' | '/' | '%' 
    ADDOP         -->  '+' | '-'
    RELOP         -->  '<' | '<=' | '==' | '!=' | '>=' | '>' 
    REGEX         -->  ID ':=' '/' ANY*  '/'
    TYPE          -->  '^' | '$' | '#' | 'f' | '~'
    LIT           -->  BOOL | STRING | NUMLIT

    
    
Microsyntax
-----------

    BR            -->  ';'
    COMMENT       -->  'c:'  ( )*   NEWLINE
    ID            -->  CHARLIT+ ([-_a-Z0-9])*
    NUMLIT        -->  [0-9]+ ('.' [0-9]*)?
    STRING        -->  (CHARLIT | NUMLIT) +
    CHARLIT       -->  [a-Z]
    ANY           -->  .

    NUMTYPE       --> '#'
    STRTYPE       --> '$'
    BOOLTYPE      --> '^'
    FUNTYPE       --> 'f'
    NULLTYPE      --> '~'

