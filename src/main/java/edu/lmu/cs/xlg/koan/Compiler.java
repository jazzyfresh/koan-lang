
package edu.lmu.cs.xlg.koan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import edu.lmu.cs.xlg.koan.entities.Script;
import edu.lmu.cs.xlg.koan.syntax.Parser;
import edu.lmu.cs.xlg.util.Log;

/**
 * A Koan compiler. This class contains a static main method allowing you to run the compiler
 * from the command line, as well as a few methods to compile, or run phases of the compiler,
 * programmatically.
 */
public class Compiler {

    /**
     * A logger for logging messages (both regular and error messages). The base properties file is
     * called <code>roflkode.properties</code>.
     */
    private Log log = new Log("Koan", new PrintWriter(System.out, true));

    /**
     * Runs the compiler as an application.
     *
     * @param args
     *            the commandline arguments. For now, the first argument should be the name of a
     *            file to compile; if missing, the compiler will read from standard input.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("All I can do now is check syntax and semantics.");

        Compiler compiler = new Compiler();
        Reader reader = (args.length == 0) ? new BufferedReader(new InputStreamReader(System.in))
                : new FileReader(args[0]);
        Script script = compiler.checkSyntax(reader);
        script.printSyntaxTree("", "", new PrintWriter(System.out, true));
        script.analyze(compiler.log);

        // TODO: Obviously, this is a stub.
    }

    /**
     * Checks the syntax of a Koan script, given a reader object.
     *
     * @param reader
     *            the source
     * @return the abstract syntax tree if successful, or null if there were any syntax errors
     */
    public Script checkSyntax(Reader reader) throws IOException {
        log.clearErrors();

        Parser parser = new Parser(reader);
        try {
            log.message("syntax.checking");
            return parser.parse(reader, log);
        } finally {
            reader.close();
        }
    }

    /**
     * Checks the static semantics of a Koan script object, generally one already produced from
     * a parse.
     *
     * @param script
     *            Script object to analyze
     * @return the (checked) semantic graph if successful, or null if there were any syntax or
     *         static semantic errors
     */
    public Script checkSemantics(Script script) {
        log.message("semantics.checking");
        script.analyze(log);
        return script;
    }

    /**
     * Checks the syntax and static semantics of a Koan program from a file.
     *
     * @param reader
     *            the source
     * @return the (checked) semantic graph if successful, or null if there were any syntax or
     *         static semantic errors
     */
    public Script checkSemantics(Reader reader) throws IOException {
        Script script = checkSyntax(reader);
        if (log.getErrorCount() > 0) {
            return null;
        }
        return checkSemantics(script);
    }

    /**
     * Returns the number of errors logged so far.
     */
    public int getErrorCount() {
        return log.getErrorCount();
    }

    /**
     * Tells the compiler whether or not it should write log messages.
     */
    public void setQuiet(boolean quiet) {
        log.setQuiet(quiet);
    }
}
