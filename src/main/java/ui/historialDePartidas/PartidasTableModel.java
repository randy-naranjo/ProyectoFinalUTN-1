package ui.historialDePartidas;
import common.Utils;
import model.Partida;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PartidasTableModel extends AbstractTableModel {
    private String[] columns = { "ID", "Jugador", "Dificultad", "Tiempo", "Intentos", "Estado" };
    private List<Partida> _partidaList;

    public PartidasTableModel(List<Partida> partidaList) {
        this._partidaList = partidaList;
    }

    public List<Partida> getPartidasList() {
        return _partidaList;
    }

    public void setPartidasList(List<Partida> pacienteList) {
        this._partidaList = pacienteList;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return _partidaList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Partida partida = _partidaList.get(rowIndex);

        return switch(columnIndex){
            case 0 -> partida.getId();
            case 1 -> partida.getJugador().getNombre();
            case 2 -> partida.getDificultad();
            case 3 -> Utils.castearTiempo(partida.getTiempo());
            case 4 -> partida.getIntentos();
            case 5 -> partida.isGanada() ? "Ganada" : "Perdida";
            default -> null;
        };
    }
}
