public class PalabrasComando {
    private static final String[] comandosValidos = {"go", "quit", "help"};

    public PalabrasComando(){

    }

    public boolean esComando(String comando) {
        for(int i = 0; i < comandosValidos.length; i++) {
            if(comandosValidos[i].equals(comando))
                return true;
        }

        // El string no es comando reconocido
        return false;
    }
}
