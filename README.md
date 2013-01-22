koan-lang |< ø@π
=================

"Relying on words to lead you to the truth is like relying on an incomplete formal system to lead you to the truth."

"Zen *koans* are a central part of Zen study, verbal though they are. Koans are supposed to be "triggers" which, though they do not contain enough information in themselves to impart enlightenment, may possibly be sufficient to unlock the mechanisms inside one's mind that lead to enlightenment. But in general, the Zen attitude is that words and truth are incompatible, or at least that no words can capture truth." 

– Douglas Hofstadter

--------------------------------

The general idea is that our language has no words, but still is a koan in itself. Let's start with some sample code.

    // Java                                ©: |<ø@π
    if (x%5 == 0) {                        ??: x%5 == 0 ? x += 2
        x += 2;                              : x%6 == 0 ? x -= 3;
    } else if (x%6 == 0) {                     ??: x == 0 ? y = 4 ??
        x -= 3;                              : x *= x ??
        if (x == 0) {
            y = 4;                         ©: |<ø@π is shorter, faster,
        }                                  ©: and sexier
    } else {
        x *= x;
    }

* The above if-statement begins with `??:` and ends with another `??`.  
* Each elseif-block begins with a colon and ends when another colon is found.  
* You can nest if-statements as if they were any other kind of statement.
* You can also have multiple statements within an if-else statement by separating them with semicolons.

|<ø@π has no traditional for-loops. Instead, blocks of code operate over range objects (`0...10`) and loops are denoted by an `∞` sign

    // Java                                ©: |<ø@π
    for (int i = 0; i < 10; i++) {         ∞: 0...10 {|i| a[i] = i}
        a[i] = i; 
    }

Traditional while-loops are represented the same way, but without ranges.  They have no condition either: you must use break statements, which are written as two bangs, `!!`.

    // Java                                ©: |<ø@π
    while (s != null) {                    ∞: {s = f.readLine();
        s = f.readLine();                      ??: s == ø ?!!??}
    }                                      ©: null is written as null set

You can print to standard out via ¶:

    // Java                                ©: |<ø@π
    System.out.println("Hey world");       ¶: "Hey world\n"

Hash-maps are denoted by `#:`.  Keys are mapped to values with `->`.

    ©: |<ø@π
    my-map = #:{a->3, b->4}

Functions are denoted by the `ƒ`: symbol (yes, that is the mathematical `ƒ` which can be accessed by hitting alt-f on your mac computer).

Functions are first-order.  Their declarations resemble the mathematical description of functions, i.e. _f: X ⟶ Y_, which maps from set X to set Y.  The evaluation of the last statement is what is returned from the function.

    // Java
    public static int gcd(int x, int y) {
        return x%y == 0 ? x : gcd(y, x%y); 
    }
    int z = gcd(37,73) // x == 1
    
    ©: |<ø@π
    gcd = ƒ: (x,y) -> {??: x%y == 0 ? x : gcd(y, x%y)??}
    z = gcd(37,73) �: z == 1

koan-lang also allows anonymous functions

    ©: |<ø@π
    ƒ: {__ += 4}              ©: a single parameter is __ (double underscore) 
    ƒ:{??:_1 == 2?_1**_2??}     ©:each ith parameter is _i (underscore, then int)
                              ©: __ resembles "fill in the blank", don't ya think?

Some general things:
* The language is dynamically and strongly typed.
* Variable declaration and initialization is the first assignment, like in Ruby or Python.
* No types are written (that requires words anyways).
* Line breaks implicitly indicate separate statements; semicolons do that explicitly.
* In general, colons are your friends.
* Regexes are just like Ruby's, but instead of `.match`, you only use `~=`.
* Arrays are pretty much the same as other languages.

Oh yeah, in case you haven't noticed, comments are indicated by the symbols ©:
