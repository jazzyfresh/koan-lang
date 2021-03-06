koan-lang
=================

"Relying on words to lead you to the truth is like relying on an incomplete formal system to lead you to the truth."

"Zen *koans* are a central part of Zen study, verbal though they are. Koans are supposed to be "triggers" which, though 
they do not contain enough information in themselves to impart enlightenment, may possibly be sufficient to unlock the 
mechanisms inside one's mind that lead to enlightenment. But in general, the Zen attitude is that words and truth are 
incompatible, or at least that no words can capture truth." 

– Douglas Hofstadter

--------------------------------

The general idea is that our language has **no words**, but still is a koan in itself. Let's start with some sample code.

    // Java                                c: koan
    if (x%5 == 0) {                        ??: x%5 == 0 ? x += 2
        x += 2;                              : x%6 == 0 ? { x -= 3;
    } else if (x%6 == 0) {                     ??: x == 0 ? y := 4 ??}
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

###Variables  
Variable declaration, like Python and Ruby, occurs concurrently with intialization
koan is statically typed. We have three types. `#` is for any number, `$` for any string, `^` for booleans.

     # x := 5
    
Because of koan's syntax for anonymous functions (you'll see later), variable names cannot begin with underscores (i.e.
this kind of thing wouldn't work: `_x := 5`).
    
**Constants** (compile time error if you try to update a constant) are variables followed by the bang `!`

    # x! := 5
    
**Assignment** occurs concurrently with initialization
    
**Swapping** is utilizes a symmmetric assignment `:=:`

    # x := 5
    # y := 3
    x :=: y
    c: x is now 3, y is now 5
    
###Strings 

Regexes are just like Ruby's, but instead of `.match`, you only use `~=`.

    danny-regex := /danny/
    $ danny-name := "dannyboy"
    danny-regex ~= danny-name      c: returns true

    
###Arithmetic and Boolean Expressions  

**Arithmetic Expression**

    y / (4 - x) * 2.5
    
**Boolean Expressions** use symbolic logic and the familiar symbols for logical operations 

    //Java                                        c: koan
    true                                          T
    false                                         F
    
    // or                                         c: or
    // the following evaluates as true            c: the following evaluates as true
    (true || false)                               (T || F)

    // and                                        c: and
    // the following evaluates as false           c: the following evaluates as false
    (true && false)                               (T && F)
    
* No "truthy" or "falsy", ie no other symbols have boolean value.

###Arrays
Are delimited by the familiar square brackets and use integers or Ranges as indices

    a := [1,2,3,4]
    a[0]       c: 1
    a[.]       c: 4  "." accesses the final element, as periods suggest finality
    a[1..3]    c: [1,2,3], last element of range inclusive
    a[1...3]   c: [1,2], last element of range exclusive
    a[1...]    c: [1,2,3,4], from index 1 until the end
    a[1..1]    c: [1]
    a[1...1]   c: Range syntax error
    
Arrays are dynamic, like in Python or Ruby, and you can append to an array using `:<`

    a := []
    a :< "hello"
    p: a[0]           c: prints "hello"
            

###Iteration

Koan has no traditional for-loops.
Instead, blocks of code iterate over range objects, `0...10`, and loops are denoted by an `8` sign 
(bc 8 is a sideways infinity).

    // Java                                c: koan
    for (int i = 0; i < 10; i++) {         8: 0...10 |i| a[i] := i
        a[i] = i; 
    }

**For-Each** essentially operates as a for-loop, but has a list where the range would go

    // Java                                c: koan
    int result = 0;                        # result := 0
    int [] a = {1,2,3,4,5};                a := [1,2,3,4,5]
    for (int x : a) {                      8: a |x| result += x
        result += x;
    }

The list will implicitly return an iterator that the block can iterate over.


**While-loops** are represented the same way, but without ranges or lists.  They have no condition either: 
you must use break statements, which are written as two bangs, `!!`.

    // Java                                c: koan
    while (s != null) {                    8: s := f.readLine();
        s = f.readLine();                      ??: s == {} ?!!??
    }                                      c: null is written with empty brackets, the empty set


You can **print** to standard out via 'p':

    // Java                                c: koan
    System.out.println("Hey world");       p: "Hey world\n"
    
**Hash-maps** are denoted by `#:`.  Keys are mapped to values with `=>`. You can access its elements like arrays.

    c: koan
    my-map := #:{a=>3, b=>4}
    p: my-map[b]     c: prints 4

**Functions** are denoted by the `f`: symbol.

Functions are first-order.  Their declarations resemble the mathematical description of functions, 
i.e. _f: X ⟶ Y_, which maps from set X to set Y. The evaluation of the last statement is what is 
returned from the function, unless that last line is the break symbol `!!`, which indicates that the
function is a procedure.

    // Java
    public static int gcd(int x, int y) {
        return x%y == 0 ? x : gcd(y, x%y); 
    }
    int z = gcd(37,73) // x == 1
    
    c: koan
    gcd := f: (x,y) -> {??: x%y == 0 ? x : gcd(y, x%y)??}
    # z = gcd(37,73) c: z == 1
    
    c: procedure example
    fourchange := f: (x) -> {x := 4; !!}
    

Koan also allows **anonymous functions**

    c: koan
    f: {__ += 4}                 c: a single parameter is __ (double underscore) 
    f: {??:_1 == 2?_1**_2??}     c: each ith parameter is _i (underscore, then int)
                                 c: __ resembles "fill in the blank", don't ya think?

###Objects

Objects are prototype-based, like JavaScript

    Point := { # x : 0,
               # y : 0,
               plot : f: (x,y) -> Graph.draw(x,y)  }
             

Some general things:
* The language is dynamically and strongly typed.
* Variable declaration and initialization is the first assignment, like in Ruby or Python.
* No types are written (that requires words anyways).
* Line breaks implicitly indicate separate statements; semicolons do that explicitly.
* In general, colons are your friends.
* Oh yeah, in case you haven't noticed, comments are indicated by the symbol 'c:'


