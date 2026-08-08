/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labpro2p3_juegomemoria.control;//Jhoveth Moncada

import labpro2p3_juegomemoria.Carta;

public interface LogicaJuego {

    void iniciarJuego();

    boolean verificarPareja(Carta carta1, Carta carta2);

    boolean juegoTerminado();
}
