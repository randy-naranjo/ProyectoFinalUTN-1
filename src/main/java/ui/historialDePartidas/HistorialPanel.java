package ui.historialDePartidas;

import common.Interfaces.UIPanel;
import service.PartidaService;

import javax.swing.*;
import java.awt.*;

public class HistorialPanel extends UIPanel {
    private PartidaService _partidasService = PartidaService.getInstance();

    @Override
    public void inicializar() {
        removeAll();
        setLayout(new BorderLayout());

        PartidasTableModel partidasTableModel = new PartidasTableModel(_partidasService.listar());
        JTable partidasTable = new JTable(partidasTableModel);
        add(new JScrollPane(partidasTable), BorderLayout.CENTER);
    }
}
