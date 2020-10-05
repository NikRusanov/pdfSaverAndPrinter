package PDFPrint_3.exceptions;

public class EmptyArgumentsException extends Exception{
    public EmptyArgumentsException() {
        super();
    }

    public EmptyArgumentsException(String message) {
        super(message);
    }

    public EmptyArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyArgumentsException(Throwable cause) {
        super(cause);
    }

    protected EmptyArgumentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
