package labpro2p3_juegomemoria.control;

import labpro2p3_juegomemoria.Jugador;

public interface GestorTurnos {

    void CambiarTurno();

    int getJugadorActual();

    Jugador obtenerJugadorActual();
}
