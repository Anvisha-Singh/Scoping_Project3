import org.antlr.v4.runtime.*;
import java.io.IOException;

public class ScopeChecker {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java ScopeChecker <input-file.pas>");
            System.exit(1);
        }

        CharStream input = CharStreams.fromFileName(args[0]);
        pascalLexer lexer = new pascalLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        pascalParser parser = new pascalParser(tokens);

        ScopeVisitor visitor = new ScopeVisitor();
        visitor.visit(parser.program());

        if (visitor.errors.isEmpty()) {
            System.out.println("Scope check passed!");
        } else {
            System.out.println("Scope errors found:");
            visitor.errors.forEach(System.out::println);
        }
    }
}
