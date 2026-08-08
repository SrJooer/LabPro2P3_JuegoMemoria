package labpro2p3_juegomemoria.vista;

import javax.swing.*;
import java.awt.*;

public class PInicio extends JPanel implements PanelConstructor {

    private JPanel contenido;
    private JPanel formulario;
    private JTextField jugador1 = getCampo();
    private JTextField jugador2 = getCampo();

    public PInicio() {
        init();
    }

    @Override
    public void init() {
        setLayout(new GridBagLayout());
        setOpaque(false);

        initFormulario();
        initContenido();
        contenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(contenido);
    }

    private void initFormulario() {
        formulario = new JPanel();
        formulario.setLayout(new GridLayout(2, 2, 0, 10));
        formulario.setAlignmentX(Component.CENTER_ALIGNMENT);
        formulario.setOpaque(false);

        formulario.add(getTexto("Jugador 1: "));
        formulario.add(jugador1);
        formulario.add(getTexto("Jugador 2: "));
        formulario.add(jugador2);
    }

    private void initContenido() {
        contenido = new JPanel();
        contenido.setLayout(new GridLayout(0, 1, 0, 10));
        contenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.setOpaque(false);

        contenido.add(getTexto("Ingrese los nombres de los jugadores:"));
        contenido.add(formulario);
        contenido.add(Box.createRigidArea(new Dimension(0, 5)));
        contenido.add(getBoton("Iniciar", this::validarCampos));
        contenido.add(getBoton("Salir", () -> System.exit(0)));
    }

    private void validarCampos() {
        try {
            String nombre1 = jugador1.getText().trim();
            String nombre2 = jugador2.getText().trim();

            if (nombre1.isEmpty()) {
                throw new IllegalArgumentException("Falta ingresar el nombre del Jugador 1.");
            }

            if (nombre2.isEmpty()) {
                throw new IllegalArgumentException("Falta ingresar el nombre del Jugador 2.");
            }

            VisualManager.cambiarPanel(new PJuego(nombre1, nombre2));

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Campos vacíos", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo iniciar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
