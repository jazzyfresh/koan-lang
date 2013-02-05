koan-lang
=================

"Relying on words to lead you to the truth is like relying on an incomplete formal system to lead you to the truth."

"Zen *koans* are a central part of Zen study, verbal though they are. Koans are supposed to be "triggers" which, though they do not contain enough information in themselves to impart enlightenment, may possibly be sufficient to unlock the mechanisms inside one's mind that lead to enlightenment. But in general, the Zen attitude is that words and truth are incompatible, or at least that no words can capture truth." 

– Douglas Hofstadter

--------------------------------

The general idea is that our language has no words, but still is a koan in itself. Let's start with some sample code.

    // Java                                c: koan
    if (x%5 == 0) {                        ??: x%5 == 0 ? x += 2
        x += 2;                              : x%6 == 0 ? x -= 3;
    } else if (x%6 == 0) {                     ??: x == 0 ? y = 4 ??
        x -= 3;                              : x *= x ??
        if (x == 0) {
            y = 4;                         c: koan is shorter, faster,
        }                                  c: and sexier... but this is cruel to say because we compare to Java
    } else {
        x *= x;
    }

* The above if-statement begins with `??:` and ends with another `??`.  
* Each elseif-block begins with a colon and ends when another colon is found.  
* You can nest if-statements as if they were any other kind of statement.
* You can also have multiple statements within an if-else statement by separating them with semicolons.

**Variable Declaration**, like Python and Ruby, occurs concurrently with intialization

    x := 5
    
* Because of koan's syntax for anonymous function, variable names cannot begin with underscores `_`

    _x := 5
    c: error

**Parallel Declaration** is allowed, seperating the components of both sides of the declaration by commas

    x, y := 5, 3
    
**Constants** (compile time error if updated), are variable followed by the bang `!`

    x! := 5
    
**Assignment** is intialization... Ask Dr. Toal    
    
**Swapping** is utilizes a symmmetric assignment `:=:`

    x := 5
    y := 3
    x :=: y
    c: x is now 3, y is now 5
    
**Arithmetic Expression**

    y / (4 - x) * 2.5
    
**Boolean Expressions** use symbolic logic, and the familiar symbols for logical operations 

    //Java                                 c: koan
    true                                   T
    false                                  F
    
    //or                                   c: or
    true || false == true                  T || F == T
    //and                                  c: and
    true && false == false                 T && F == F
    
* No "truthy" or "falsy", ie no other symbols have boolean value.

**Arrays** delimited by the familiar square brackets 

    a := [1,2,3,4]
    a[0]       c: 1
    a[.]       c: 4  "." accesses the final element, as periods suggest finality
    a[1..3]    c: [1,2,3]
    a[1...3]   c: [1,2]
    a[1...]    c: [1,2,3,4]
    a[1..1]    c: [1]
    
* Arrays are dynamic, like in Python or Ruby

**Iteration**

koan has no traditional for-loops. Instead, blocks of code operate over range objects (`0...10`) and loops are denoted by an `8` sign.

    // Java                                c: koan
    for (int i = 0; i < 10; i++) {         8: 0...10 {|i| a[i] = i}
        a[i] = i; 
    }

Traditional while-loops are represented the same way, but without ranges.  They have no condition either: you must use break statements, which are written as two bangs, `!!`.

    // Java                                c: koan
    while (s != null) {                    8: {s = f.readLine();
        s = f.readLine();                      ??: s == 0 ?!!??}
    }                                      c: null is written as null set
    
**List Comprehension**

* Will arrays return arrays?
* Will ranges return arrays?
* Will any iteration return arrays?


You can **print** to standard out via 'p':

    // Java                                c: koan
    System.out.println("Hey world");       p: "Hey world\n"
    
**Hash-maps** are denoted by `#:`.  Keys are mapped to values with `->`.

    c: koan
    my-map = #:{a->3, b->4}
    p: my-map[b]     c: prints 4

**Functions** are denoted by the `f`: symbol.

Functions are first-order.  Their declarations resemble the mathematical description of functions, i.e. _f: X ⟶ Y_, which maps from set X to set Y. 
The evaluation of the last statement is what is returned from the function, unless that last line is the break symbol `!!`, which indicates a procedure.

    // Java
    public static int gcd(int x, int y) {
        return x%y == 0 ? x : gcd(y, x%y); 
    }
    int z = gcd(37,73) // x == 1
    
    c: koan
    gcd := f: (x,y) -> {??: x%y == 0 ? x : gcd(y, x%y)??}
    z = gcd(37,73) c: z == 1
    
    c: procedure example
    fourchange := f: (x) -> {x := 4; !!}
    

koan-lang also allows **anonymous functions**

    c: koan
    f: {__ += 4}              c: a single parameter is __ (double underscore) 
    f: {??:_1 == 2?_1**_2??}     c: each ith parameter is _i (underscore, then int)
                              c: __ resembles "fill in the blank", don't ya think?

Some general things:
* The language is dynamically and strongly typed.
* Variable declaration and initialization is the first assignment, like in Ruby or Python.
* No types are written (that requires words anyways).
* Line breaks implicitly indicate separate statements; semicolons do that explicitly.
* In general, colons are your friends.
* Regexes are just like Ruby's, but instead of `.match`, you only use `~=`.
* Arrays are pretty much the same as other languages.
* Oh yeah, in case you haven't noticed, comments are indicated by the symbol 'c:'


### SYNTAX  STILL IN PROGRESS

Here is a brief EBNF for the macrosyntax.  Here syntax categories and compound tokens are shown in all caps (NB: boolean tokens `T` and `F` are exceptions). Reserved tokens and symbols are always quoted, since our reserved tokens are symbols.  The meta symbols are the usual ones: `|` for alternatives, `*` for zero or more, `+` for one or more, `?` for zero or one, `'[a-z]` for one letter from the range of lowercase letters, and parentheses for grouping.

The tokens `NUMLIT`, `STRLIT`, `ID`, and `BR` are defined in the microsyntax below the EBNF.

    SCRIPT        →  (STMT BR)+
    STMT          →  DEC 
                  |  PRINTSTMT
                  |  CONDITIONAL
                  |  LOOP
                  |  PROCCALL
    DEC           →  VARDEC | CONSTDEC | PROCDEC | FUNDEC
    VARDEC        →  ID (',' ID)* ':=' EXP (',' EXP)*
                  |  OBJECT
    CONSTDEC      →  ID '!' (',' ID'!')* ':=' EXP (',' EXP)*
    PROCDEC       →  ID ':= f: (' PARAMS ') ->' BLOCK
    FUNDEC        →  ID ':= f: (' PARAMS ') ->' BLOCK
    PARAMS        →  () | ( )*
                  |  ID (',' ID)*
    ASSIGNMENT    →  DEC | (ID ':=:' ID)              
    PRINTSTMT     →  'p:' EXP
    CONDITIONAL   →  '??:' EXP '?' STMT BR (CONDITIONAL)* (':' EXP '?' STMT BR (CONDITIONAL)*)*  BR (':' STMT )? '??'
    LOOP          →  '8:' (RANGE)? BLOCK
    PROCCALL      →  (ID '('ARGS')') | ANONFUN
    BOOL          →  'T' | 'F'   
    ARRAY         →  '[' EXP* (',' EXP)* ']'
    ARRREF        →  ID '[' '.' | [0-9]+ (('..' | '...') [0-9]+)? ']'
    OBJECT        →  
    HASH          → '#:{' (ID '->' EXP) (',' ID '->' EXP)* '}'
    ANONFUN       → 'f:{' (EXP ('_'('_' | [0-9]+) EXP)*)* '}'
    BLOCK         →  '{' STMT ('!!')? '}'
                  |  '{' (STMT BR)+ ('!!')? '}'
    EXP           →  EXP1 (MULOP EXP1)*
    EXP1          →  EXP2 (ADDOP EXP2)*
    EXP2          →  EXP3 (RELOP EXP3)?
    EXP3          →  EXP8 (LOGOP EXP8)*
    EXP8          →  LIT | ID | ARRAY | OBJECT | ANONFUN | PROCCALL | HASH | BOOL | ARREF
    MULOP         →  '*' | '/' | '%' | '**'
    ADDOP         →  '+' | '-'
    RELOP         →  '<' | '<=' | '==' | '!=' | '>=' | '>' 
    LOGOP         →  '||' | '&&' 
    REGEX         → ...leaving this for the jazzy one
    
Our Microsyntax:

    BR            →  NEWLINE | ';'
    COMMENT       →  'c:'  ( )*   NEWLINE
    ID            →  [a-Z]+ ([-_a-Z0-9])*
    NUMLIT        →  [0-9]+ ('.' [0-9]*)?
    STRLIT        →  '"'  ( NUMLIT | [a-Z])+  '"'
    ARGS          →  EXP8 (',' EXP8)*
    
    
Open Questions
* How does spacing matter in EBNF?
* Objects
* Regexes
* Generators
* Array Returns
