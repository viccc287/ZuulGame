public class Comandos {
    private String palabraComando;
    private String segundaPalabra;

    public Comandos(String accion, String direccion) {
        palabraComando = accion;
        this.segundaPalabra = direccion;
    }

    public String getPalabraComando(){
        return palabraComando;
    }

    public String getSegundaPalabra() {
        return segundaPalabra;
    }

    public boolean comandoDesconocido() {
        return (palabraComando == null);
    }

    public boolean tieneSegundaPalabra() {
        return (segundaPalabra != null);
    }
}
