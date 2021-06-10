package exceptions;

/**
 * 
 */
public class FileIOException extends Exception {
    public FileIOException(){
        super();
    }

    public FileIOException(String msg){
        super(msg);
    }

    public FileIOException(String msg, Throwable err) {
        super(msg, err);
    }
}
