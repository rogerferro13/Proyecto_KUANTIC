package kuantic.modelo;

public class Usuario {
    private String nombreusuario;
    private String contrasena;

    public Usuario(String nombreusuario, String contrasena) {
        this.nombreusuario = nombreusuario;
        this.contrasena = contrasena;
    }

    public boolean validar(String u, String p) {
        return this.nombreusuario.equals(u) && this.contrasena.equals(p);
    }
}
