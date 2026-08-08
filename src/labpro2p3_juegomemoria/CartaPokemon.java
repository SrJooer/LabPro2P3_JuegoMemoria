//Leonardo Romero 22611318

package labpro2p3_juegomemoria;

public class CartaPokemon extends Carta{
    private final String nombrePokemon;
    
    public CartaPokemon(String idPareja, String Imagen, String nombrePokemon){
        super(idPareja, Imagen);
        if(nombrePokemon==null||nombrePokemon.isBlank()){
            throw new IllegalArgumentException("El nombre del Pokemon necesita un valor");
        }
        this.nombrePokemon = nombrePokemon;
    }
    
    @Override
    public void mostrar(){
    if (estado != EstadoCarta.ENPAREJA){
        estado=EstadoCarta.DESCUBIERTA;
    }
    }
    @Override
    public void ocultar(){
        if(estado != EstadoCarta.ENPAREJA){
            estado= EstadoCarta.ABAJO;
        }
    }
        public String getnombrePokemon(){
            return nombrePokemon;
        }
    }

