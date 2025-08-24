package ui.juego.partida;

import model.Partida;
import service.JugadorService;
import service.PartidaService;
import ui.MainFrame;
import ui.juego.partida.components.CartaComponent;
import ui.juego.partida.components.InfoComponent;


import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class PartidaFrame extends JFrame {
    private PartidaService partidaService = PartidaService.getInstance();
    private JugadorService jugadorService = JugadorService.getInstance();

    private InfoComponent panelInfo;

    private JPanel panelCartas;


    private CartaComponent[] listaDeCartas;

    private CartaComponent primeraCarta = null;
    private CartaComponent segundaCarta = null;

    private boolean bloqueado = false;

    private Partida partida;

    public PartidaFrame() {
        setVisible(true);
        setTitle("Partida");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container padre = SwingUtilities.getAncestorOfClass(MainFrame.class, this);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());

        escucharCierreDelFrame();

        //Inicializar Modelo (Partida)
        partida = new Partida();
        partida.setJugador(jugadorService.getJugadorSeleccionado());
        partida.setFecha(LocalDate.now());
        partida.setDificultad(partidaService.getDificultadSeleccionada().toString());

        int tiempo = partidaService.getTiempo();

        // Panel de información
        panelInfo = new InfoComponent();
        add(panelInfo, BorderLayout.NORTH);

        //Cuadricula de Cartas
        crearCuadriculaDeCartas();

        panelInfo.iniciarTimer();
    }

    private void crearCuadriculaDeCartas(){
        int tamanhoCuadro = partidaService.getTamanhoCuadro();

        panelCartas = new JPanel(new GridLayout(tamanhoCuadro, tamanhoCuadro, 20, 20));
        panelCartas.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150)); //Margenes

        // Mezclamos las cartas
        int cantidadDeCartas = tamanhoCuadro * tamanhoCuadro;
        List<Integer> valoresCartas = generarValoresDeCartas(cantidadDeCartas);

        //Creamos los botones de las cartas
        listaDeCartas = new CartaComponent[cantidadDeCartas];
        for (int i = 0; i < cantidadDeCartas; i++) {
            CartaComponent carta = new CartaComponent();

            carta.setNumeroOculto(valoresCartas.get(i));//Asignar un valor oculto a la carta
            carta.addActionListener(e -> voltearCarta(carta));

            listaDeCartas[i] = carta;
            panelCartas.add(carta);
        }

        add(panelCartas, BorderLayout.CENTER);
    }

    private List<Integer> generarValoresDeCartas(int cantidadDeCartas){
        List<Integer> valoresCartas = new ArrayList<>();

        double cantidadDeNumeros = Math.ceil(((double) cantidadDeCartas / 2)); //Cantidad de numeros pares

        for (int num = 1; num <= cantidadDeNumeros; num++){
            valoresCartas.add(num);
            valoresCartas.add(num);
        }

        //Revolver cartas
        Collections.shuffle(valoresCartas);

        return valoresCartas;
    }

    private void voltearCarta(CartaComponent btn) {
        if (bloqueado) return; //Bloquear mientras se estan revolteando cartas

        if (primeraCarta == null) {
            btn.revelarNumero();
            primeraCarta = btn;
        } else if (segundaCarta == null) {
            btn.revelarNumero();
            segundaCarta = btn;

            panelInfo.sumarIntento();

            // Comprobamos si son iguales
            if (primeraCarta.comparar(segundaCarta)) {
                //Resetear Cartas Seleccionadas
                primeraCarta = null;
                segundaCarta = null;

                // Verificar si todas las cartas fueron encontradas
                if (todasEncontradas()) {
                    panelInfo.detenerTimer();
                    submitPartida(true);
                    JOptionPane.showMessageDialog(this, "¡Felicidades! Encontraste todos los pares");
                    CerrarJuego();
                }
            } else {
                //Delay para ocultar las cartas cuando el intento fue fallido
                bloqueado = true;
                Timer pausa = new Timer(700, e -> {
                    primeraCarta.ocultarNumero();
                    segundaCarta.ocultarNumero();
                    primeraCarta = null;
                    segundaCarta = null;
                    bloqueado = false;
                });
                pausa.setRepeats(false);
                pausa.start();
            }
        }
    }

    private boolean todasEncontradas() {
        int cartasRestantes = 0;
        for (CartaComponent carta : listaDeCartas) {
            if (carta.isEnabled()) {
                cartasRestantes++;
            };
        }

        if(cartasRestantes < 2){ //De esta forma terminamos el juego para cuando tenemos una cantidad de cartas impar
            return true;
        } else {
            return false;
        }
    }

    public void submitPartida(boolean isGanada){
        if(partida == null) return;
        partida.setIntentos(panelInfo.getIntentos());
        partida.setTiempo(panelInfo.getTiempoRestante());
        partida.setGanada(isGanada);

        partidaService.crear(partida);
    }

    private void escucharCierreDelFrame(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                panelInfo.detenerTimer();
            }
        });
    }

    public void CerrarJuego() {
        dispose();
    }

}
