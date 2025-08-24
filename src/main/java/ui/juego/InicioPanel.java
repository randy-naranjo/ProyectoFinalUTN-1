package ui.juego;

import common.Enums.DificultadesEnum;
import common.Interfaces.UIPanel;
import model.Jugador;
import service.JugadorService;
import service.PartidaService;
import ui.juego.menu.MenuPanel;
import ui.juego.partida.PartidaFrame;

import javax.swing.*;
import java.awt.*;

public class InicioPanel extends UIPanel {
    private PartidaService partidaService = PartidaService.getInstance();
    private JugadorService jugadorService = JugadorService.getInstance();

    private JButton btnIniciarJuego;
    private MenuPanel menuPanel;
    private JPanel panelBotones;

    public InicioPanel(){
        super();
        setLayout(new BorderLayout());
    }

    @Override
    public void inicializar(){
        menuPanel = new MenuPanel();
        menuPanel.inicializar();

        inicializarBotonIniciarJuego();

        add(menuPanel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void inicializarBotonIniciarJuego(){
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        btnIniciarJuego = new JButton("Comenzar Juego");

        panelBotones.add(btnIniciarJuego);

        btnIniciarJuego.addActionListener(e -> {

            Jugador jugadorSeleccionado = jugadorService.getJugadorSeleccionado();
            DificultadesEnum dificultadSeleccionada = partidaService.getDificultadSeleccionada();

            if(jugadorSeleccionado == null || dificultadSeleccionada == null){
                JOptionPane.showMessageDialog(this, "Debe Seleccionar un Jugador y una Dificultad");
                return;
            }

            new PartidaFrame();
        });
    }


}
