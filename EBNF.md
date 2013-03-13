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
                    |  IFSTMT
                    |  LOOP
                    |  PROCCALL
                    |  EXPSTMT
    DEC           -->  VARDEC | CONSTDEC | FUNDEC
    VARDEC        -->  TYPE ID ':=' EXP 
    CONSTDEC      -->  TYPE ID '!' (',' ID() '!')* ':=' EXP (',' EXP)*
    FUNDEC        -->  ID ':= f: (' PARAMS ') ->' ANONFUN
    PARAMS        -->  (ID (',' ID)*)*
    ASSIGNMENT    -->  DEC | ID ':=" EXP | (ID ':=:' ID)
    PRINTSTMT     -->  'p:' EXP
    EXPSTMT       -->  EXP
    IFSTMT        -->  '??:' EXP '?' STMT (':' EXP '?' STMT)* (':' (STMT))? '??'
    LOOP          -->  '8:' (RANGE)? STMT
    PROCCALL      -->  (ID '('ARGS')') | ANONFUN
    BOOL          -->  'T' | 'F'
    ARRAY         -->  '[' EXP* (',' EXP)* ']'
    ARRAYREF      -->  ID '[' EXP | RANGE ']'
    OBJECT        -->  '{' (ID ':' EXP ',')+ '}'
    HASH          -->  '#:{' (ID '=>' EXP) (',' ID '=>' EXP)* '}'
    ANONFUN       -->  ('f:' | PARAMS)? '{' (STMT BR)+  '}'
    RANGE         -->  ('..' | '...') EXP 
                       | EXP ('..' | '...')
                       | EXP ('..' | '...') EXP
    EXP           -->  EXP1 ('||' EXP1)*
    EXP1          -->  EXP2 ('&&' EXP2)* 
    EXP2          -->  EXP3 (RELOP EXP3)?
    EXP3          -->  EXP4 (EXPNOP EXP4)*
    EXP4          -->  EXP5 (MULOP EXP5)*
    EXP5          -->  EXP6 (ADDOP EXP6)*
    EXP6          -->  LIT | ID | ARRAY | OBJECT | PROCCALL | HASH | ARRAYREF       EXPNOP        -->  '**'
    MULOP         -->  '*' | '/' | '%' 
    ADDOP         -->  '+' | '-'
    RELOP         -->  '<' | '<=' | '==' | '!=' | '>=' | '>' 
    REGEX         -->  ID ':=' '/' .* '/'
    ARGS          -->  EXP5 (',' EXP5)*
    TYPE          -->  '^' | '$' | '#' 
    LIT           -->  BOOL | STRING | INTLIT

    
    
Microsyntax
-----------

    BR            -->  ';'
    COMMENT       -->  'c:'  ( )*   NEWLINE
    ID            -->  CHARLIT+ ([-_a-Z0-9])*
    NUMLIT        -->  [0-9]+ ('.' [0-9]*)?
    INTLIT        -->  [0-9]+
    STRING        -->  (CHARLIT | INTLIT) +
    CHARLIT       -->  [a-Z]

