package labpro2p3_juegomemoria.vista;

import labpro2p3_juegomemoria.Jugador;

import javax.swing.*;

public class PJuego extends JPanel implements PanelConstructor {

    private String jugador1, jugador2;

    public PJuego(String jugador1, String jugador2) {
        Jugador jugador = new Jugador();
        init();
    }

    @Override
    public void init() {

    }
}
