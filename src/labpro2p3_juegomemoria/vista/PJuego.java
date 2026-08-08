package labpro2p3_juegomemoria.vista;

import labpro2p3_juegomemoria.Carta;
import labpro2p3_juegomemoria.Jugador;
import labpro2p3_juegomemoria.control.ControladorJuego;
import labpro2p3_juegomemoria.control.GestorTurnos;
import labpro2p3_juegomemoria.control.LogicaJuego;

import javax.swing.*;
import java.awt.*;

public class PJuego extends JPanel implements PanelConstructor {

    private static final int FILAS = 6;
    private static final int COLUMNAS = 6;
    private static final int TAMANO_IMAGEN = 80;
    private static final int PAUSA = 900;

    private final Color COLOR_REVERSO = new Color(60, 40, 90);
    private final Color COLOR_TURNO = new Color(255, 203, 5);

    private final Jugador jugador1;
    private final Jugador jugador2;
    private final LogicaJuego logica;
    private final GestorTurnos turnos;

    private JPanel marcador;
    private JPanel tablero;
    private JLabel textoJugador1;
    private JLabel textoJugador2;
    private JLabel textoTurno;

    private final JButton[][] botones = new JButton[FILAS][COLUMNAS];

    private Carta primeraCarta;
    private Carta segundaCarta;
    private int filaPrimera;
    private int columnaPrimera;
    private int filaSegunda;
    private int columnaSegunda;

    public PJuego(String nombre1, String nombre2) {
        jugador1 = new Jugador(nombre1);
        jugador2 = new Jugador(nombre2);

        ControladorJuego controlador = new ControladorJuego(jugador1, jugador2);
        logica = controlador;
        turnos = controlador;

        logica.iniciarJuego();

        init();
    }

    @Override
    public void init() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        initMarcador();
        initTablero();

        add(marcador, BorderLayout.NORTH);
        add(tablero, BorderLayout.CENTER);

        actualizarMarcador();
    }

    private void initMarcador() {
        marcador = new JPanel();
        marcador.setLayout(new GridLayout(1, 3, 10, 0));
        marcador.setBackground(Color.BLACK);
        marcador.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        textoJugador1 = getTexto("");
        textoTurno = getTexto("");
        textoJugador2 = getTexto("");

        textoJugador2.setHorizontalAlignment(JLabel.RIGHT);
        textoTurno.setHorizontalAlignment(JLabel.CENTER);

        marcador.add(textoJugador1);
        marcador.add(textoTurno);
        marcador.add(textoJugador2);
    }

    private void actualizarMarcador() {
        textoJugador1.setText(jugador1.getNombre() + ": " + jugador1.getAciertos());
        textoJugador2.setText(jugador2.getNombre() + ": " + jugador2.getAciertos());

        if (turnos.getJugadorActual() == 1) {
            textoTurno.setText("Turno de " + jugador1.getNombre());
            textoJugador1.setForeground(COLOR_TURNO);
            textoJugador2.setForeground(Color.WHITE);
        } else {
            textoTurno.setText("Turno de " + jugador2.getNombre());
            textoJugador1.setForeground(Color.WHITE);
            textoJugador2.setForeground(COLOR_TURNO);
        }

        textoTurno.setForeground(COLOR_TURNO);
    }

    private void initTablero() {
        tablero = new JPanel();
        tablero.setLayout(new GridLayout(FILAS, COLUMNAS, 5, 5));
        tablero.setBackground(Color.BLACK);
        tablero.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {

                final int f = fila;
                final int c = columna;

                JButton boton = new JButton();
                boton.setBackground(COLOR_REVERSO);
                boton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
                boton.setFocusPainted(false);
                boton.addActionListener(e -> clicEnCarta(f, c));

                botones[fila][columna] = boton;
                tablero.add(boton);
            }
        }
    }

    private void clicEnCarta(int fila, int columna) {
        try {
            if (primeraCarta != null && segundaCarta != null) {
                return;
            }

            Carta carta = logica.getTablero().obtenerCarta(fila, columna);

            if (carta.estaDescubierta()) {
                return;
            }

            carta.mostrar();
            mostrarImagen(fila, columna, carta);

            if (primeraCarta == null) {
                primeraCarta = carta;
                filaPrimera = fila;
                columnaPrimera = columna;
            } else {
                segundaCarta = carta;
                filaSegunda = fila;
                columnaSegunda = columna;
                verificarSeleccion();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrio un error al seleccionar la carta: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarSeleccion() {

        if (logica.verificarPareja(primeraCarta, segundaCarta)) {

            marcarPareja(filaPrimera, columnaPrimera);
            marcarPareja(filaSegunda, columnaSegunda);

            actualizarMarcador();
            limpiarSeleccion();

            if (logica.juegoTerminado()) {
                mostrarGanador();
            }

        } else {

            Timer timer = new Timer(PAUSA, e -> {
                primeraCarta.ocultar();
                segundaCarta.ocultar();

                ocultarImagen(filaPrimera, columnaPrimera);
                ocultarImagen(filaSegunda, columnaSegunda);

                turnos.CambiarTurno();
                actualizarMarcador();
                limpiarSeleccion();
            });

            timer.setRepeats(false);
            timer.start();
        }
    }

    private void limpiarSeleccion() {
        primeraCarta = null;
        segundaCarta = null;
    }

    private void mostrarImagen(int fila, int columna, Carta carta) {
        JButton boton = botones[fila][columna];
        ImageIcon imagen = getImagen(carta.getRutaImagen(), TAMANO_IMAGEN);

        boton.setBackground(Color.WHITE);

        if (imagen != null) {
            boton.setIcon(imagen);
        } else {
            boton.setForeground(Color.BLACK);
            boton.setText(carta.getIdPareja());
        }
    }

    private void ocultarImagen(int fila, int columna) {
        JButton boton = botones[fila][columna];
        boton.setIcon(null);
        boton.setText("");
        boton.setBackground(COLOR_REVERSO);
    }

    private void marcarPareja(int fila, int columna) {
        JButton boton = botones[fila][columna];
        boton.setBorder(BorderFactory.createLineBorder(new Color(80, 220, 100), 3));
    }

    private void mostrarGanador() {

        JOptionPane.showMessageDialog(this,
                logica.finalizarPartida(),
                "Fin de la partida",
                JOptionPane.INFORMATION_MESSAGE);

        VisualManager.cambiarPanel(new PInicio());
    }
}
