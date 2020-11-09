package parser.exceptions;

public class UnitParseException extends IllegalArgumentException {
    public UnitParseException(String message) {
        super(message);
    }

    public UnitParseException(Exception e) {
        super(e);
    }
}
