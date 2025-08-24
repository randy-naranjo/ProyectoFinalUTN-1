package service;

import common.Enums.DificultadesEnum;
import dao.PartidaDAO;
import model.Jugador;
import model.Partida;

import java.util.List;

public class PartidaService {

    private static PartidaService instance;
    private final PartidaDAO dao = new PartidaDAO();

    private PartidaService() {}

    public static PartidaService getInstance() {
        if (instance == null) {
            instance = new PartidaService();
        }
        return instance;
    }

    public void crear(Partida partida) {
        dao.crear(partida);
    }

    public List<Partida> listar(){
        return dao.listar();
    }
}
