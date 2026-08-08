package labpro2p3_juegomemoria.control;

import labpro2p3_juegomemoria.modelo.Carta;
import labpro2p3_juegomemoria.modelo.Jugador;
import labpro2p3_juegomemoria.modelo.Tablero;

public class ControladorJuego implements LogicaJuego, GestorTurnos {

    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private int jugadorActual;

    public ControladorJuego(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        jugadorActual = 1;
    }

    public void iniciarJuego() {
        tablero = new Tablero();
        tablero.generarCartas();
        tablero.mezclar();
        jugadorActual = 1;
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

            return true;
        }

        return false;
    }


    public boolean juegoTerminado() {

        if (tablero == null) {
            return false;
        }

        return tablero.todasEmparejadas();
    }

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