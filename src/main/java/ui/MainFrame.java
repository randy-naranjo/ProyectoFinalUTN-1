package ui;

import common.Interfaces.UIPanel;
import ui.historialDePartidas.HistorialPanel;
import ui.juego.InicioPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    private JButton btnLinkHistorial;
    private JButton btnLinkJuego;

    private UIPanel inicioPanel;
    private UIPanel historialPanel;

    public MainFrame() {
        setTitle("Juego de Memoria");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Layout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        //Paneles
        inicioPanel = new InicioPanel();
        historialPanel = new HistorialPanel();

        contentPanel.add(inicioPanel, "INICIO");
        contentPanel.add(historialPanel, "HISTORIAL");

        //Navbar
        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navbar.setBackground(Color.LIGHT_GRAY);

        //Botones de Navegacion
        btnLinkJuego = crearBotonNav("Juego", "INICIO");
        btnLinkHistorial = crearBotonNav("Historial", "HISTORIAL");

        //Agregar botones al nav
        navbar.add(btnLinkJuego);
        navbar.add(btnLinkHistorial);

        //Agregar navbar y panel de contenidos
        add(navbar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        //Mostrar por defecto el panel de JUEGO
        mostrarPanel("INICIO");
    }

    // Crea un botón de navegación con lógica de cambio de panel
    private JButton crearBotonNav(String texto, String panelName) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setBackground(Color.GRAY);
        btn.setForeground(Color.WHITE);

        btn.addActionListener(e -> {
            mostrarPanel(panelName);
        });

        return btn;
    }

    // Cambia el panel visible y actualiza estilos de botones
    private void mostrarPanel(String panelName) {
        cardLayout.show(contentPanel, panelName);

        // Reiniciar el estilo
        btnLinkHistorial.setBackground(Color.GRAY);
        btnLinkJuego.setBackground(Color.GRAY);

        // Marcar el activo
        switch (panelName){
            case "HISTORIAL":
                btnLinkHistorial.setBackground(Color.BLUE);
                historialPanel.inicializar();
                break;
            case "INICIO":
                btnLinkJuego.setBackground(Color.BLUE);
                inicioPanel.inicializar();
                break;
        }
    }
}
