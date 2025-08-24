package ui.juego.menu.components;

import common.Enums.DificultadesEnum;
import common.Interfaces.UIPanel;
import service.PartidaService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SeleccionarDificultadComponent extends UIPanel {

    PartidaService partidaService = PartidaService.getInstance();

    JButton btnFacil, btnDificil, btnMedio;

    JPanel listaBotonesPanel;
    List<JButton> listaBotones;

    DificultadesEnum[] listaDificultades = { DificultadesEnum.FACIL, DificultadesEnum.MEDIO ,DificultadesEnum.DIFICIL };
    int numDificultad;


    @Override
    public void inicializar() {
        add(Box.createRigidArea(new Dimension(0, 30)));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(null, "Seleccionar Dificultad:", TitledBorder.CENTER, TitledBorder.TOP));
        setMaximumSize(new Dimension(1000, 300));

        crearBotonesDeDificultad();

        numDificultad = 0;
    }

    private void crearBotonesDeDificultad(){
        listaBotonesPanel = new JPanel();
        listaBotonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        btnFacil = new JButton("Facil");
        btnMedio = new JButton("Medio");
        btnDificil = new JButton("Dificil");

        listaBotones = new ArrayList<>();

        listaBotones.add(btnFacil);
        listaBotones.add(btnMedio);
        listaBotones.add(btnDificil);

        for(int i = 0; i < listaBotones.size(); i++){
            int numDificultad = i;
            JButton boton = listaBotones.get(numDificultad);

            boton.setBackground(Color.GRAY);
            boton.setForeground(Color.WHITE);

            boton.addActionListener(e -> {
                seleccionarDificultad(numDificultad);
            });

            listaBotonesPanel.add(boton);
        }
        add(listaBotonesPanel);
    }

    private void seleccionarDificultad(int numDificultad){
        for (JButton boton : listaBotones){
            boton.setBackground(Color.GRAY);
        }

        listaBotones.get(numDificultad).setBackground(new Color(28, 148, 100));

        partidaService.setDificultadSeleccionada(listaDificultades[numDificultad]);
    }
}
