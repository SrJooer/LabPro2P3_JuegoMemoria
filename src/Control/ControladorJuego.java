package labpro2p3_juegomemoria.control;

// Jhoveth Moncada

import labpro2p3_juegomemoria.Carta;
import labpro2p3_juegomemoria.Jugador;
import labpro2p3_juegomemoria.Tablero;

public class ControladorJuego implements LogicaJuego, GestorTurnos {

    private Tablero tablero;
    private final Jugador jugador1;
    private final Jugador jugador2;
    private int jugadorActual;

    public ControladorJuego(Jugador jugador1, Jugador jugador2) {

        if (jugador1 == null || jugador2 == null) {
            throw new IllegalArgumentException(
                    "Los jugadores no pueden ser nulos"
            );
        }

        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = 1;
    }

    @Override
    public void iniciarJuego() {
        try {
            tablero = new Tablero();

            jugador1.reiniciar();
            jugador2.reiniciar();

            jugadorActual = 1;

        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "No se pudo iniciar el juego: " + e.getMessage(), e
            );
        }
    }

    @Override
    public boolean verificarPareja(Carta carta1, Carta carta2) {

        if (carta1 == null || carta2 == null) {
            return false;
        }

        if (carta1 == carta2) {
            return false;
        }

        if (carta1.esParejaDe(carta2)) {

            carta1.marcarEnPareja();
            carta2.marcarEnPareja();

            registrarAcierto();

            return true;
        }

        return false;
    }

    private void registrarAcierto() {

        if (jugadorActual == 1) {
            jugador1.nuevoAcierto();
        } else {
            jugador2.nuevoAcierto();
        }
    }

    @Override
    public boolean juegoTerminado() {

        if (tablero == null) {
            return false;
        }

        return tablero.juegoTerminado();
    }

    @Override
    public void CambiarTurno() {

        if (jugadorActual == 1) {
            jugadorActual = 2;
        } else {
            jugadorActual = 1;
        }
    }

    @Override
    public int getJugadorActual() {
        return jugadorActual;
    }

    public Jugador obtenerJugadorActual() {

        if (jugadorActual == 1) {
            return jugador1;
        }

        return jugador2;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
