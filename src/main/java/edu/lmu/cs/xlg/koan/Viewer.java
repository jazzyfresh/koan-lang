package edu.lmu.cs.xlg.roflkode;

import static javax.swing.KeyStroke.getKeyStroke;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import edu.lmu.cs.xlg.roflkode.entities.Entity;
import edu.lmu.cs.xlg.roflkode.entities.Script;
import edu.lmu.cs.xlg.roflkode.syntax.Parser;
import edu.lmu.cs.xlg.translators.RoflkodeToCTranslator;
import edu.lmu.cs.xlg.util.Log;

/**
 * A simple GUI application for viewing the different things the Roflkode compiler can do.
 *
 * The application has two panes.  The left is a simple text editor in which one can edit a Roflkode
 * script, load one from the file system, and save to the file system.  The right shows a view of
 * the script in response to a user selection action.  The user can choose to see
 * <ul>
 *   <li>The abstract syntax tree
 *   <li>The semantic graph
 *   <li>The translation into C
 * </ul>
 *
 * The following are planned for the future:
 * <ul>
 *   <li>A tuple or stack-based intermediate representation
 *   <li>The generated assembly language
 *   <li>A translation into Ruby or Java
 *   <li>The executable file
 * </ul>
 */
@SuppressWarnings("serial")
public class Viewer extends JFrame {

    private JTextArea source = new JTextArea(30, 60);
    private JTextArea view = new JTextArea(30, 80) {
        public void setText(String text) {
            super.setText(text);
            this.setCaretPosition(0);
        }
    };
    private JScrollPane sourcePane = new JScrollPane();
    private JScrollPane viewPane = new JScrollPane();
    private StringWriter errors = new StringWriter();
    private Log log = new Log("roflkode", new PrintWriter(errors));
    private File currentFile;
    private JFileChooser chooser = new JFileChooser(".");

    /**
     * Creates new Viewer.
     */
    public Viewer() {
        JSplitPane splitPane = new JSplitPane();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu();
        JMenu viewMenu = new JMenu();

        Action newAction = new AbstractAction("New") {
            {
                putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
                putValue(Action.ACCELERATOR_KEY, getKeyStroke("control N"));
            }
            public void actionPerformed(ActionEvent e) {newFile();}
        };

        Action openAction = new AbstractAction("Open") {
            {
                putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
                putValue(Action.ACCELERATOR_KEY, getKeyStroke("control O"));
            }
            public void actionPerformed(ActionEvent e) {openFile();}
        };

        Action saveAction = new AbstractAction("Save") {
            {
                putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
                putValue(Action.ACCELERATOR_KEY, getKeyStroke("control S"));
            }
            public void actionPerformed(ActionEvent e) {saveFile();}
        };

        Action saveAsAction = new AbstractAction("Save As") {
            public void actionPerformed(ActionEvent e) {saveAsFile();}
        };

        Action quitAction = new AbstractAction("Quit") {
            {
                putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);
                putValue(Action.ACCELERATOR_KEY, getKeyStroke("control Q"));
            }
            public void actionPerformed(ActionEvent e) {quit();}
        };

        Action syntaxAction = new AbstractAction("Syntax") {
            public void actionPerformed(ActionEvent e) {viewSyntaxTree();}
        };

        Action semanticsAction = new AbstractAction("Semantics") {
            public void actionPerformed(ActionEvent e) {viewSemanticGraph();}
        };

        Action makeTheCAction = new AbstractAction("-> C") {
            public void actionPerformed(ActionEvent e) {viewCTranslation();}
        };

        fileMenu.setText("File");
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
        fileMenu.add(quitAction);
        menuBar.add(fileMenu);

        menuBar.add(new JButton(syntaxAction));
        menuBar.add(new JButton(semanticsAction));
        menuBar.add(new JButton(makeTheCAction));
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);

        source.setFont(new Font("Monospaced", 0, 16));
        sourcePane.setViewportView(source);
        splitPane.setLeftComponent(sourcePane);
        view.setEditable(false);
        view.setFont(new Font("Monospaced", 0, 16));
        viewPane.setViewportView(view);
        splitPane.setRightComponent(viewPane);
        splitPane.setDividerLocation(480);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setTitle("Roflkode Viewer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 712);
    }

    // Action implementations

    private void newFile() {
        currentFile = null;
        source.setText("");
    }

    private void openFile() {
        chooser.showOpenDialog(this);
        currentFile = chooser.getSelectedFile();
        if (currentFile == null) return;

        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader in = new BufferedReader(new FileReader(currentFile.getCanonicalPath()));
            String line;
            while ((line = in.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            in.close();
        } catch (IOException ignored) {
        }
        source.setText(buffer.toString());
    }

    private void saveFile() {
        if (currentFile == null) {
            view.setText("Nothing to save");
            return;
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(currentFile.getCanonicalPath()));
            out.write(source.getText());
            out.close();
            view.setText("File saved");
        } catch (IOException e) {
            view.setText("File not saved: " + e.getMessage());
        }
    }

    private void saveAsFile() {
        chooser.showSaveDialog(this);
        currentFile = chooser.getSelectedFile();
        if (currentFile != null) {
            saveFile();
        }
    }

    private void quit() {
        System.exit(0);
    }

    private void viewSyntaxTree() {
        viewPane.setViewportView(view);
        Script script = parse();
        if (log.getErrorCount() > 0) {
            view.setText(errors.toString());
        } else {
            try {
                viewPane.setViewportView(new JTree(makeTree(script, "")));
            } catch (IllegalStateException e) {
                view.setText("Internal error: Syntax tree has cycles\n" + e.getMessage());
            }
        }
    }

    private void viewSemanticGraph() {
        viewPane.setViewportView(view);
        Script script = analyze();
        if (log.getErrorCount() > 0) {
            view.setText(errors.toString());
        } else {
            StringWriter writer = new StringWriter();
            script.printEntityGraph(new PrintWriter(writer));
            view.setText(writer.toString());
        }
    }

    private void viewCTranslation() {
        viewPane.setViewportView(view);
        String c = translateToC();
        if (log.getErrorCount() > 0) {
            view.setText(errors.toString());
        } else {
            view.setText(c);
        }
    }

    // Wrappers for compiler operations

    private Script parse() {
        log.clearErrors();
        errors.getBuffer().setLength(0);
        Reader reader = new StringReader(source.getText());
        return new Parser(reader).parse(reader, log);
    }

    private Script analyze() {
        Script script = parse();
        if (log.getErrorCount() > 0) return null;
        script.analyze(log);
        return script;
    }

    private String translateToC() {
        Script script = analyze();
        if (log.getErrorCount() > 0) return null;
        StringWriter writer = new StringWriter();
        RoflkodeToCTranslator.translate(script, new PrintWriter(writer));
        return writer.toString();
    }

    /**
     * Returns a syntax tree for the hierarchy rooted at entity e.
     * Each node will have the name "prefix: classname" where prefix
     * is supplied as an argument to this method, and classname is
     * the name of this object's class.  The children of the node are
     * obtained from the non-static, non-null fields of this object.
     * Each of the fields which have type Entity will of course be
     * roots of subtrees; all other fields will generate leaf nodes.
     */
    public MutableTreeNode makeTree(Entity e, String prefix) {

        // Build a node for it with the prefix and the class name
        String className = e.getClass().getName();
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(
            prefix + ": " + className.substring(className.lastIndexOf('.') + 1)
        );

        // Add all the children
        for (Field field: Entity.relevantFields(e.getClass())) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(e);
                if (value == null) continue;
                if ((field.getModifiers() & Modifier.STATIC) != 0) continue;

                if (value instanceof Entity) {
                    node.add(makeTree((Entity)value, name));
                } else if (value instanceof Collection<?>) {
                    try {
                        for (Object child: (Collection<?>)value) {
                            node.add(makeTree((Entity)child, name));
                        }
                    } catch (ClassCastException cce) {
                        node.setUserObject(node.getUserObject()
                                + "  " + name + "=\"" + value + "\"");
                    }
                } else {
                    // Simple attribute, attach description to node name
                    node.setUserObject(node.getUserObject() + "  " + name + "=\"" + value + "\"");
                }
            } catch (IllegalAccessException cannotHappen) {
            }
        }
        return node;
    }

    /**
     * Runs the application.
     */
    public static void main(String args[]) {
        new Viewer().setVisible(true);
    }
}
