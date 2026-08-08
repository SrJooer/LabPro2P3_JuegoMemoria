//Leonardo Romero 22611318
package labpro2p3_juegomemoria;

public class Jugador {
    private final String nombre;
    private int aciertos;
    
    public Jugador(String nombre){
    if(nombre == null|| nombre.isBlank()){
        throw new IllegalArgumentException("El nombre no puede estar vacio");
    }
    this.nombre=nombre.trim();
    this.aciertos=0;
    }


    public void nuevoAcierto(){
        this.aciertos++;
    }

    public void puntoExtra(int puntoExtra){
        if(puntoExtra>0){
            this.aciertos+= puntoExtra;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getAciertos() {
        return aciertos;
    }
    
    public void reiniciar(){
        this.aciertos=0;
    }
    @Override
    public String toString(){
        return nombre + " Aciertos:"+ aciertos;
    }
}