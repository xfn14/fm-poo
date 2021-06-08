package exceptions;

public class InvalidPlayerSubException extends Exception {
    public InvalidPlayerSubException(){
        super("Invalid player substitution");
    }

    public InvalidPlayerSubException(String msg){
        super(msg);
    }
}
