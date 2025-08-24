package ui.juego.menu;

import common.Interfaces.UIPanel;
import ui.juego.menu.components.CrudJugadorComponent;
import ui.juego.menu.components.ListaJugadoresComponent;
import ui.juego.menu.components.SeleccionarDificultadComponent;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends UIPanel {

    private UIPanel listaJugadoresComponent, seleccionarDificultadComponent, crudJugadorComponent;

    private JPanel crearJugadorPanel, panelIzquierdo;

    public MenuPanel() {
        setLayout(new BorderLayout());
    }

    @Override
    public void inicializar() {
        inicializarPanelIzquierdo();
        inicializarPanelCrearJugador();

        //Layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdo, crearJugadorPanel);
        splitPane.setResizeWeight(0.5);
        splitPane.setEnabled(false);
        splitPane.setDividerSize(2);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(splitPane);
    }

    private void inicializarPanelIzquierdo(){
        listaJugadoresComponent = new ListaJugadoresComponent();
        listaJugadoresComponent.inicializar();

        seleccionarDificultadComponent = new SeleccionarDificultadComponent();
        seleccionarDificultadComponent.inicializar();

        panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.add(seleccionarDificultadComponent);
        panelIzquierdo.add(listaJugadoresComponent);
    }

    private void inicializarPanelCrearJugador(){
        crudJugadorComponent = new CrudJugadorComponent();
        crudJugadorComponent.inicializar();

        crearJugadorPanel = new JPanel(new BorderLayout());
        crearJugadorPanel.add(crudJugadorComponent, BorderLayout.CENTER);
    }
}
