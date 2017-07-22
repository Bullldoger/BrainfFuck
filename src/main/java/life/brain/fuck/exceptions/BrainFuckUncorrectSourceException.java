package life.brain.fuck.exceptions;

/**
 * Created by roman on 20.07.17.
 */
public class BrainFuckUncorrectSourceException extends Exception {
    private String message = "uncorrect source code";
    private byte[] interpreterCast;

    public BrainFuckUncorrectSourceException(String message) {
        this.message = message;
    }

    public BrainFuckUncorrectSourceException() {

    }

    public BrainFuckUncorrectSourceException(byte[] interpreterCast) {
        this.interpreterCast = interpreterCast;
    }

    public String getMessage() {
        return this.message;
    }
}
