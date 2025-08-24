package ui.juego;

import common.Interfaces.UIPanel
import ui.juego.menu.MenuPanel;
import javax.swing.*;
import java.awt.*;

public class InicioPanel extends UIPanel {

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


        });
    }


}
