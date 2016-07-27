package umlaut.android;

/**
 * Created by Gabriel Rojas on 19/7/2016.
 */
public class ServerResponse {

    private String result;
    private String message;
    private Usuario user;

    public String getResultado() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Usuario getUser() {
        return user;
    }
}