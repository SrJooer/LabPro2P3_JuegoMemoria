
package labpro2p3_juegomemoria;

public abstract class Carta {

    // Identificador compartido por las dos cartas que forman una pareja.
    protected final String idPareja;
    protected final String imagen;
    protected EstadoCarta estado;

    protected Carta(String idPareja, String rutaImagen) {
        if (idPareja == null || idPareja.isBlank()) {
            throw new IllegalArgumentException("El identificador de pareja no puede estar vacío.");
        }
        this.idPareja = idPareja;
        this.imagen = rutaImagen;
        this.estado = EstadoCarta.BOCA_ABAJO;
    }

    public abstract void mostrar();

    public abstract void ocultar();

    public boolean estaDescubierta() {
        return estado == EstadoCarta.DESCUBIERTA || estado == EstadoCarta.EMPAREJADA;
    }

    public void marcarEnPareja() {
        this.estado = EstadoCarta.EMPAREJADA;
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
        return getClass().getSimpleName() + "{idPareja='" + idPareja + "', estado=" + estado + "}";
    }
}