import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class SymbolTable {
    private final Map<String, Symbol> symbols = new HashMap<>();
    private final SymbolTable parent;

    public Collection<Symbol> getSymbols() {
        return symbols.values();
    }

    public SymbolTable getParent() {
        return parent;
    }

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
    }

    public boolean addSymbol(String name, String type) {
        String key = name.toLowerCase();
        if (symbols.containsKey(key)) return false;
        symbols.put(key, new Symbol(name, type));
        return true;
    }

    public Symbol lookup(String name) {
        Symbol symbol = symbols.get(name.toLowerCase());
        return symbol != null ? symbol : (parent != null ? parent.lookup(name) : null);
    }

}
