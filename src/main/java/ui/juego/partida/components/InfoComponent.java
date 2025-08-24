package ui.juego.partida.components;

import common.Utils;
import service.PartidaService;
import ui.MainFrame;
import ui.juego.partida.PartidaFrame;

import javax.swing.*;
import java.awt.*;

public class InfoComponent extends JPanel {
    private PartidaService partidaService = PartidaService.getInstance();

    private Timer timer;

    private int tiempoRestante; // 1 minuto

    private int intentos = 0;

    private JLabel lblTiempo;
    private JLabel lblIntentos;

    public InfoComponent(){
        tiempoRestante = partidaService.getTiempo();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        lblTiempo = new JLabel("", SwingConstants.CENTER);
        updateLabelTiempo();

        lblIntentos = new JLabel("", SwingConstants.CENTER);
        updateLabelIntentos();

        add(lblTiempo);
        add(lblIntentos);
    }

    private void updateLabelTiempo(){
        lblTiempo.setText("Tiempo Restante: " + Utils.castearTiempo(tiempoRestante));
    }

    public void iniciarTimer() {
        if(timer != null) return; //Evitar iniciar otro
        timer = new Timer(1000, e -> {
            tiempoRestante--;

            updateLabelTiempo();

            if (tiempoRestante <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "¡Tiempo agotado! Perdiste");

                PartidaFrame partidaFrame = (PartidaFrame) SwingUtilities.getAncestorOfClass(PartidaFrame.class, this);
                partidaFrame.submitPartida(false);
                partidaFrame.CerrarJuego();
            }
        });
        timer.start();
    }

    public void detenerTimer(){
        if(timer == null) return;
        timer.stop();
    }

    public void updateLabelIntentos(){
        lblIntentos.setText("Cantidad de Intentos: " + intentos);
    }

    public int getIntentos() {
        return intentos;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void sumarIntento() {
        intentos++; 
        updateLabelIntentos();
    }

}
