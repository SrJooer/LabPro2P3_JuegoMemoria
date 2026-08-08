//Leonardo Romero 22611318

package labpro2p3_juegomemoria;

public abstract class Carta {

    protected final String idPareja;
    protected final String imagen;
    protected EstadoCarta estado;

    protected Carta(String idPareja, String rutaImagen) {
        if (idPareja == null || idPareja.isBlank()) {
            throw new IllegalArgumentException("El id de las parejas necesita un valor");
        }
        this.idPareja = idPareja;
        this.imagen = rutaImagen;
        this.estado = EstadoCarta.ABAJO;
    }

    public abstract void mostrar();

    public abstract void ocultar();

    public boolean estaDescubierta() {
        return estado == EstadoCarta.DESCUBIERTA || estado == EstadoCarta.ENPAREJA;
    }

    public void marcarEnPareja() {
        this.estado = EstadoCarta.ENPAREJA;
    }

    public boolean esParejaDe(Carta otra) {
        if (otra == null) {
            return false;
        }
        return this.idPareja.equals(otra.idPareja);
    }

    public String getIdPareja() {
        return idPareja;
    }

    public String getRutaImagen() {
        return imagen;
    }

    public EstadoCarta getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "idPareja= " + idPareja + "  estado=" + estado;
    }
}