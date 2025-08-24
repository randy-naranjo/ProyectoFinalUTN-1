package service;

import common.Exceptions.JugadorExisteException;
import dao.JugadorDAO;
import model.Jugador;

import java.util.List;

public class JugadorService {

    private static JugadorService instance;
    private final JugadorDAO dao = new JugadorDAO();

    private Jugador JugadorSeleccionado;

    private JugadorService() {}
    public static JugadorService getInstance() {
        if (instance == null) {
            instance = new JugadorService();
        }
        return instance;
    }

    public Jugador getJugadorSeleccionado() {
        return JugadorSeleccionado;
    }

    public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
        JugadorSeleccionado = jugadorSeleccionado;
    }

    public void crearJugador(Jugador jugador) throws Exception {
        validarJugadorExistente(jugador);

        dao.crear(jugador);
    }

    public void modificarJugador(Jugador jugador) throws Exception {
        validarJugadorExistente(jugador);

        dao.modificar(jugador);
    }
    public void borrarJugador(Jugador jugador){
        dao.borrar(jugador);
    }

    public List<Jugador> listarJugadores() {
        return dao.listarTodos();
    }

    private void validarJugadorExistente(Jugador jugador) throws Exception{
        Jugador jugadorExistente = dao.buscarPorNombre(jugador.getNombre());

        if(jugadorExistente != null){
            throw new JugadorExisteException("Nombre de Jugador ya existe");
        }
    }
}
