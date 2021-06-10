package exceptions;

/**
 * Excpetion threw when a specific line can't be parsed
 */
public class FileInvalidLineException extends Exception {
    public FileInvalidLineException(){
        super("Line couldn't be parsed");
    }

    public FileInvalidLineException(String msg){
        super(msg);
    }

    public FileInvalidLineException(String msg, Throwable err) {
        super(msg, err);
    }
}
