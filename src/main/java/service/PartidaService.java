package service;

import common.Enums.DificultadesEnum;
import dao.PartidaDAO;
import model.Jugador;
import model.Partida;

import java.util.List;

public class PartidaService {

    private static PartidaService instance;
    private final PartidaDAO dao = new PartidaDAO();

    private DificultadesEnum dificultadSeleccionada;

    private PartidaService() {}

    private int tiempo;
    private int tamanhoCuadro;

    public int getTiempo() {
        return tiempo;
    }

    public int getTamanhoCuadro() {
        return tamanhoCuadro;
    }

    public static PartidaService getInstance() {
        if (instance == null) {
            instance = new PartidaService();
        }
        return instance;
    }

    //GETTERS y SETTERS
    public DificultadesEnum getDificultadSeleccionada() {
        return dificultadSeleccionada;
    }

    public void setDificultadSeleccionada(DificultadesEnum dificultadSeleccionada) {
        this.dificultadSeleccionada = dificultadSeleccionada;

        switch (dificultadSeleccionada){
            case FACIL:
                tiempo = 60; // 1 minuto
                tamanhoCuadro = 2; // 2 x 2
                break;
            case MEDIO:
                tiempo = 3 * 60; // 3 minutos
                tamanhoCuadro = 4; // 4 x 4
                break;
            case DIFICIL:
                tiempo = 4 * 60; // 4 minutos
                tamanhoCuadro = 5; // 5 x 5
                break;
        }
    }

    //Methodos del servicio
    public void crear(Partida partida) {
        dao.crear(partida);
    }

    public List<Partida> listar(){
        return dao.listar();
    }
}
