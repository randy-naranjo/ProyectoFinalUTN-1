package ui.juego.menu.components;

import common.Interfaces.UIPanel;
import model.Jugador;
import service.JugadorService;
import ui.juego.menu.MenuPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ListaJugadoresComponent extends UIPanel {
    private JugadorService jugadorService = JugadorService.getInstance();

    private JPanel listaJugadoresPanel;
    private JScrollPane scrollJugadores;
    private List<JButton> listaBotonesJugador;

    @Override
    public void inicializar() {
        removeAll();

        setLayout(new BorderLayout());

        inicializarListaBotones();

        scrollJugadores = new JScrollPane(listaJugadoresPanel);
        scrollJugadores.setBorder(BorderFactory.createTitledBorder(null, "Seleccione un Jugador:", TitledBorder.CENTER, TitledBorder.TOP));

        add(scrollJugadores, BorderLayout.CENTER);
    }

    private void inicializarListaBotones(){
        listaJugadoresPanel = new JPanel();
        listaJugadoresPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        listaJugadoresPanel.setLayout(new BoxLayout(listaJugadoresPanel, BoxLayout.Y_AXIS));

        List<Jugador> jugadores = jugadorService.listarJugadores();

        listaBotonesJugador = new ArrayList<>();
        for (Jugador jugador : jugadores){

            JButton btnJugador = new JButton(jugador.getNombre());

            btnJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnJugador.setBackground(Color.GRAY);
            btnJugador.setForeground(Color.WHITE);

            listaJugadoresPanel.add(btnJugador);
            listaJugadoresPanel.add(Box.createRigidArea(new Dimension(0, 5))); // separación vertical

            listaBotonesJugador.add(btnJugador);

            btnJugador.addActionListener(e -> {
                for (JButton btn : listaBotonesJugador){
                    btn.setBackground(Color.GRAY);
                    btn.setForeground(Color.WHITE);
                }
                btnJugador.setBackground(new Color(28, 148, 100));

                jugadorService.setJugadorSeleccionado(jugador);
                emitirJugadorSeleccionado();
            });

            if(jugador.equals(jugadorService.getJugadorSeleccionado())){
                btnJugador.setBackground(new Color(28, 148, 100));
            }
        }
    }

    private void emitirJugadorSeleccionado(){
        ActionEvent evento = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "jugadorSeleccionado");

        Container padre = SwingUtilities.getAncestorOfClass(MenuPanel.class, this);

        if (padre != null) {
            padre.dispatchEvent(evento);
        }
    }
}
