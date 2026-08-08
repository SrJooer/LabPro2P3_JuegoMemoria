package labpro2p3_juegomemoria.vista;

import javax.swing.*;
import java.awt.*;

public class VisualManager {

    private static JFrame ventana;
    private Dimension dimension = new Dimension(800, 800);

    public VisualManager() {
        init();
        cambiarPanel(new PInicio());
    }

    private void init() {
        ventana = new JFrame("Juego de Memoria");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(dimension);
        ventana.setLocationRelativeTo(null);
        ventana.setBackground(Color.BLACK);
        ventana.setVisible(true);
    }
    
    public static void cambiarPanel(JPanel panel) {
        ventana.setContentPane(panel);
        ventana.revalidate();
        ventana.repaint();
    }
}
