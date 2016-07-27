package umlaut.android;

/**
 * Created by Gabriel Rojas on 19/7/2016.
 */
public class ServerRequest {

    private String operation;
    private Usuario user;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}


