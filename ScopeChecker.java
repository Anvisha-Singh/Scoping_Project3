import org.antlr.v4.runtime.*;
import java.io.IOException;

public class ScopeChecker {
    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromFileName("test2.pas");
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
