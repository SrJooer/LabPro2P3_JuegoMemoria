
package labpro2p3_juegomemoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class Tablero {
    public static final int filas=6;
    public static final int columnas=6;
    public static final int imagenes=18;
    
    private static final String[] Nombres_pokemon ={
        "pikachu", "charmander", "bulbasaur", "litleo",
        "eevee", "squirtle", "snorlax", "jigglypuff",
        "dragonite", "garchomp", "mewtwo", "ditto",
        "mew", "pidgey", "magikarp", "rattata",
        "tentacruel", "oshawott"
    };
    private final Carta[][] cartas;
    
    public Tablero(){
    this.cartas = new Carta[filas][columnas];
    try{
         inicializarTablero();
    } catch(Exception e){
        throw new IllegalArgumentException("No se pudo hacer el tablero:"+ e.getMessage(), e );
    }
    
    }
    private void inicializarTablero() {
        List<Carta> mazo = generarMazo();
        Collections.shuffle(mazo);
 
        int indice = 0;
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                cartas[fila][columna] = mazo.get(indice++);
            }
        }
    }
    
    private List<Carta> generarMazo() {
        List<Carta> mazo = new ArrayList<>();
 
        for (int i = 0; i < imagenes; i++) {
            String idPareja = "pareja_" + i;
            String nombre = Nombres_pokemon[i];
            String rutaImagen = "/labpro2p3_juegomemoria/imagenes/" + nombre + ".png";
 
            if (i % 4 == 0) {
                mazo.add(new CartaEspecial(idPareja, rutaImagen, nombre, 1));
                mazo.add(new CartaEspecial(idPareja, rutaImagen, nombre, 1));
            } else {
                mazo.add(new CartaPokemon(idPareja, rutaImagen, nombre));
                mazo.add(new CartaPokemon(idPareja, rutaImagen, nombre));
            }
        }
        return mazo;
    }
    
    
    
    public Carta obtenerCarta(int fila, int columna) {
        try {
            return cartas[fila][columna];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(
                    "Posicion no esta en el tablero: " + fila + ", " + columna);
        }
    }
 
    public boolean verificarPareja(Carta primera, Carta segunda) {
        if (primera == null || segunda == null || primera == segunda) {
            return false;
        }
        return primera.esParejaDe(segunda);
    }
 
    public boolean juegoTerminado() {
        for (Carta[] filaCartas : cartas) {
            for (Carta carta : filaCartas) {
                if (carta.getEstado() != EstadoCarta.ENPAREJA) {
                    return false;
                }
            }
        }
        return true;
    }

}
