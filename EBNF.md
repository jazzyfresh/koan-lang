The Syntax
==========

Here is a brief EBNF for the macrosyntax.  Here syntax categories and compound tokens are shown in all 
caps (NB: boolean tokens `T` and `F` are exceptions). Reserved tokens and symbols are always quoted, 
since our reserved tokens are symbols.  



Macrosyntax
-----------

    SCRIPT        -->  (STMT BR)+
    STMT          -->  DEC 
                    |  PRINTSTMT
                    |  CONDITIONAL
                    |  LOOP
                    |  PROCCALL
    DEC           -->  VARDEC | CONSTDEC | PROCDEC | FUNDEC
    VARDEC        -->  TYPE ID ':=' EXP 
    CONSTDEC      -->  TYPE ID '!' ':=' EXP
    PROCDEC       -->  ID ':= f: (' PARAMS ') ->' BLOCK
    FUNDEC        -->  ID ':= f: (' PARAMS ') ->' BLOCK
    PARAMS        -->  ID (',' ID)*
    ASSIGNMENT    -->  ID ':=" EXP | (ID ':=:' ID)
    PRINTSTMT     -->  'p:' EXP
    CONDITIONAL   -->  '??:' BOOLEXP '?' (STMT | ANONFUN()) (':' BOOLEXP '?' (STMT | ANONFUN()))* (':' (STMT() | ANONFUN()))? '??'
    LOOP          -->  '8:' (RANGE)? STMT
    PROCCALL      -->  (ID '('ARGS')') | ANONFUN
    BOOL          -->  'T' | 'F'
    ARRAY         -->  '[' EXP* (',' EXP)* ']'
    ARRAYREF      -->  ID '[' '.' | [0-9]+ | RANGE ']'
    OBJECT        -->  '{' (ID ':' EXP ',')+ '}'
    HASH          -->  '#:{' (ID '=>' EXP) (',' ID '=>' EXP)* '}'
    ANONFUN       -->  'f:{' (EXP ('_'('_' | [0-9]+) EXP)*)* '}'
    RANGE         -->  [0-9]+ ('..' | '...') [0-9]+
    BLOCK         -->  '{' STMT ('!!')? '}'
                    |  '{' (STMT BR)+ ('!!')? '}'
    EXP           -->  EXP1 ('||' EXP1)*
    EXP1          -->  EXP2 ('&&' EXP2)* 
    EXP2          -->  EXP3 (RELOP EXP3)?
    EXP3          -->  EXP4 (EXPNLOP EXP4)*
    EXP4          -->  EXP5 (MULOP EXP5)*
    EXP5          -->  EXP6 (ADDOP EXP6)*
    EXP6          -->  LIT | ID | ARRAY | OBJECT | ANONFUN | PROCCALL | HASH | ARRAYREF | 'p:' EXP
    EXPNOP        --> '**'
    MULOP         -->  '*' | '/' | '%' 
    ADDOP         -->  '+' | '-'
    RELOP         -->  '<' | '<=' | '==' | '!=' | '>=' | '>' 
    REGEX         -->  ID ':=' '/' .* '/'
    ARGS          -->  EXP (',' EXP)*
    TYPE          -->  '^' | '$' | '#' 
    LIT           -->  BOOL | CHARLIT | STRING | INTLIT | NUMLIT

    
    
Microsyntax
-----------

    BR            -->  NEWLINE | ';'
    COMMENT       -->  'c:'  ( )*   NEWLINE
    ID            -->  [a-Z]+ ([-_a-Z0-9])*
    NUMLIT        -->  [0-9]+ ('.' [0-9]*)?
    INTLIT        -->  [0-9]+
    STRING        -->  '"'  ( [a-Z0-9])+  '"'
    CHARLIT       -->  [a-Z]

