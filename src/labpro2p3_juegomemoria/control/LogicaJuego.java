package labpro2p3_juegomemoria.control;

import labpro2p3_juegomemoria.Carta;
import labpro2p3_juegomemoria.Tablero;

public interface LogicaJuego {

    void iniciarJuego();

    boolean verificarPareja(Carta carta1, Carta carta2);

    boolean juegoTerminado();

    String finalizarPartida();

    Tablero getTablero();
}
