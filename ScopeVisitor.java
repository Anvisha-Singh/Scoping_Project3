import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class ScopeVisitor extends pascalBaseVisitor<Void> {
    private final Stack<SymbolTable> scopes = new Stack<>();
    public final List<String> errors = new ArrayList<>();
    private int scopeLevel = 0;
    private int loopDepth = 0;

    private void enterScope(String scopeType) {
        scopes.push(new SymbolTable(scopes.isEmpty() ? null : scopes.peek()));
        scopeLevel++;
    }

    private void exitScope(String scopeType) {
        System.out.println("\n↗ Exiting " + scopeType + " scope (Level " + scopeLevel + ")");
        printSymbols();
        scopes.pop();
        scopeLevel--;
    }

    private void printSymbols() {
        if (!scopes.peek().getSymbols().isEmpty()) {
            System.out.println("Current symbols:");
        }
        scopes.peek().getSymbols().forEach(symbol ->
                System.out.println("  " + symbol.name + " : " + symbol.type)
        );
    }

    private void enterLoop() {
        loopDepth++;
        enterScope("loop");
    }

    private void exitLoop() {
        exitScope("loop");
        loopDepth--;
    }

    @Override
    public Void visitProgram(pascalParser.ProgramContext ctx) {
        enterScope("global");
        String programName = ctx.programHeading().identifier().IDENT().getText().toLowerCase();
        scopes.peek().addSymbol(programName, "PROGRAM");
        visitChildren(ctx);
        exitScope("global");
        return null;
    }

    @Override
    public Void visitVariableDeclarationPart(pascalParser.VariableDeclarationPartContext ctx) {
        ctx.variableDeclaration().forEach(this::visitVariableDeclaration);
        return null;
    }

    @Override
    public Void visitVariableDeclaration(pascalParser.VariableDeclarationContext ctx) {
        String type = ctx.type_().getText().toLowerCase();
        if (!type.equals("integer")) {
            errors.add("Unsupported type: " + type + " - only INTEGER allowed");
            return null;
        }

        for (pascalParser.IdentifierContext idCtx : ctx.identifierList().identifier()) {
            String varName = idCtx.IDENT().getText().toLowerCase();
            if (!scopes.peek().addSymbol(varName, "VARIABLE")) {
                errors.add("Duplicate variable: " + varName);
            }
        }
        return null;
    }

    @Override
    public Void visitProcedureDeclaration(pascalParser.ProcedureDeclarationContext ctx) {
        String procName = ctx.identifier().IDENT().getText();
        if (!scopes.peek().addSymbol(procName, "PROCEDURE")) {
            errors.add("Duplicate procedure: " + procName);
        }

        enterScope("procedure '" + procName + "'");

        if (ctx.formalParameterList() != null) {
            for (pascalParser.FormalParameterSectionContext section :
                    ctx.formalParameterList().formalParameterSection()) {
                if (section.parameterGroup() != null) {
                    pascalParser.IdentifierListContext idList =
                            section.parameterGroup().identifierList();
                    for (pascalParser.IdentifierContext idCtx : idList.identifier()) {
                        String paramName = idCtx.IDENT().getText();
                        if (!scopes.peek().addSymbol(paramName, "PARAMETER")) {
                            errors.add("Duplicate parameter: " + paramName);
                        }
                    }
                }
            }
        }

        visit(ctx.block());
        exitScope("procedure '" + procName + "'");
        return null;
    }

    @Override
    public Void visitWhileStatement(pascalParser.WhileStatementContext ctx) {
        enterLoop();
        visitChildren(ctx);
        exitLoop();
        return null;
    }

    @Override
    public Void visitForStatement(pascalParser.ForStatementContext ctx) {
        enterLoop();
        String controlVar = ctx.identifier().IDENT().getText();
        if (!scopes.peek().addSymbol(controlVar, "CONTROL_VARIABLE")) {
            errors.add("Duplicate control variable in for loop: " + controlVar);
        }
        visitChildren(ctx);
        exitLoop();
        return null;
    }

    @Override
    public Void visitBreakStatement(pascalParser.BreakStatementContext ctx) {
        if (loopDepth == 0) {
            errors.add("'BREAK' used outside of a loop at line " + ctx.getStart().getLine());
        }
        return null;
    }

    @Override
    public Void visitContinueStatement(pascalParser.ContinueStatementContext ctx) {
        if (loopDepth == 0) {
            errors.add("'CONTINUE' used outside of a loop at line " + ctx.getStart().getLine());
        }
        return null;
    }

    @Override
    public Void visitCompoundStatement(pascalParser.CompoundStatementContext ctx) {
        enterScope("begin-end block");
        visitChildren(ctx);
        exitScope("begin-end block");
        return null;
    }

    @Override
    public Void visitIdentifier(pascalParser.IdentifierContext ctx) {
        if (!isInDeclarationContext(ctx)) {
            String name = ctx.IDENT().getText();
            Symbol symbol = findSymbolInScopeHierarchy(name);
            if (symbol == null) {
                errors.add("Undeclared identifier: " + name + " at line " + ctx.getStart().getLine());
            }
        }
        return null;
    }

    private boolean isInDeclarationContext(pascalParser.IdentifierContext ctx) {
        return ctx.parent instanceof pascalParser.VariableDeclarationContext ||
                ctx.parent instanceof pascalParser.ProcedureDeclarationContext ||
                ctx.parent instanceof pascalParser.ParameterGroupContext ||
                ctx.parent instanceof pascalParser.ProgramHeadingContext;
    }

    private Symbol findSymbolInScopeHierarchy(String name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Symbol symbol = scopes.get(i).lookup(name);
            if (symbol != null) return symbol;
        }
        return null;
    }
}
