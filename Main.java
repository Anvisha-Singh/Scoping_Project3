import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Absolute path to the file
        String filePath = "case1.pas";

        // Read the file contents into a FileInputStream
        FileInputStream fileInputStream = new FileInputStream(filePath);

        // Create an input stream from the file
        ANTLRInputStream inputStream = new ANTLRInputStream(fileInputStream);

        // Create a lexer
        pascalLexer lexer = new pascalLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser
        pascalParser parser = new pascalParser(tokens);

        // Parse the input starting from the 'program' rule
        ParseTree tree = parser.program();

        // Pretty print the tree
        printParseTree(tree, "");

        // Close the file input stream
        fileInputStream.close();
    }

    // Custom method to print the parse tree in a cleaner format
    private static void printParseTree(ParseTree tree, String indent) {
        // If it's a leaf node, print the text of the token
        if (tree.getChildCount() == 0) {
            System.out.println(indent + tree.getText());
        } else {
            // If it's an internal node, print its name and recursively print the children
            System.out.println(indent + tree.getClass().getSimpleName() + ": " + tree.getText());
            for (int i = 0; i < tree.getChildCount(); i++) {
                printParseTree(tree.getChild(i), indent + "  ");
            }
        }
    }

}

