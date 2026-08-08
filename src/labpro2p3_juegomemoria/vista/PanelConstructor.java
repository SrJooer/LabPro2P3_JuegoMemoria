package labpro2p3_juegomemoria.vista;

import javax.swing.*;
import java.awt.*;

public interface PanelConstructor {

    public void init();

    default JButton getBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK);
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return boton;
    }

    default JButton getBoton(String texto, Runnable accion) {
        JButton boton = getBoton(texto);
        boton.addActionListener(e -> accion.run());
        return boton;
    }

    default JTextField getCampo() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("SansSerif", Font.PLAIN, 24));
        campo.setForeground(Color.WHITE);
        campo.setBackground(Color.BLACK);
        campo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        campo.setCaretColor(Color.WHITE);
        return campo;
    }

    default JLabel getTexto(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 24));
        label.setForeground(Color.WHITE);
        return label;
    }

    default JLabel getTitulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 36));
        label.setForeground(Color.WHITE);
        return label;
    }

    default ImageIcon getImagen(String ruta, int tamano) {
        try {
            java.net.URL url = getClass().getResource(ruta);

            if (url == null) {
                throw new Exception("No se encontro la imagen: " + ruta);
            }

            ImageIcon original = new ImageIcon(url);
            Image escalada = original.getImage().getScaledInstance(tamano, tamano, Image.SCALE_SMOOTH);

            return new ImageIcon(escalada);

        } catch (Exception e) {
            System.out.println("Error al cargar imagen: " + e.getMessage());
            return null;
        }
    }

}
