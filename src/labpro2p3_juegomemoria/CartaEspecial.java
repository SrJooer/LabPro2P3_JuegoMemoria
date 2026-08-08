//Leonardo Romero 22611318

package labpro2p3_juegomemoria;

public class CartaEspecial extends Carta{
    private final String nombrePokemon;
    private final int puntoExtra;
    private boolean puntosDados;
    
    public CartaEspecial(String idPareja, String Imagen, String nombrePokemon, int puntoExtra){
        super(idPareja, Imagen);
        if(nombrePokemon==null||nombrePokemon.isBlank()){
           throw new IllegalArgumentException("El nombre del Pokemon necesita un valor");
        }
        if(puntoExtra<0){
            throw new IllegalArgumentException("Puntos extra necesitan ser positivos");
        }
        this.nombrePokemon=nombrePokemon;
        this.puntoExtra=puntoExtra;
        this.puntosDados=false;
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
    
    public int darPuntos() {
        if (!puntosDados && estado == EstadoCarta.ENPAREJA) {
            puntosDados = true;
            return puntoExtra;
        }
        return 0;
    }

    public String getNombrePokemon() {
        return nombrePokemon;
    }

    public int getPuntoExtra() {
        return puntoExtra;
    }
    
}
