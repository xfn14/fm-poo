package exceptions;

/**
 * Player's substitution couldn't occur
 */
public class InvalidPlayerSubException extends Exception {
    public InvalidPlayerSubException(){
        super("Invalid player substitution");
    }

    public InvalidPlayerSubException(String msg){
        super(msg);
    }
}
