package umlaut.android;


/**
 * Created by Gabriel Rojas on 19/7/2016.
 */
public class Usuario {

    private String nombre;
    private String mail;
    private String password;
    private int id_dni;
    private int tipo_usuario;
    private String old_password;
    private String new_password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_dni() {
        return id_dni;
    }

    public void setId_dni(int id_dni) {
        this.id_dni = id_dni;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }



}