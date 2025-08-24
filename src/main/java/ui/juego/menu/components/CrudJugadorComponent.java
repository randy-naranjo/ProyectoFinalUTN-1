package ui.juego.menu.components;

import common.Exceptions.JugadorExisteException;
import common.Interfaces.UIPanel;
import model.Jugador;
import service.JugadorService;
import ui.juego.menu.MenuPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CrudJugadorComponent extends UIPanel {
    private JugadorService jugadorService = JugadorService.getInstance();

    private JTextField txtNombreJugador;

    private JButton btnCrearJugador, btnBorrarJugador, btnModificarJugador;

    public CrudJugadorComponent(){
        //Layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(null, "Crear nuevo Jugador", TitledBorder.CENTER, TitledBorder.TOP));
    }

    public void setNombreJugadorSeleccionado(){
        var jugadorSeleccionado = jugadorService.getJugadorSeleccionado();
        txtNombreJugador.setText(jugadorSeleccionado.getNombre());
    }

    @Override
    public void inicializar() {

        txtNombreJugador = new JTextField();
        txtNombreJugador.setMaximumSize(new Dimension(200, 30));
        txtNombreJugador.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Botones
        btnCrearJugador = new JButton("Crear");
        btnCrearJugador.addActionListener(this::crearJugador);

        btnModificarJugador = new JButton("Modificar");
        btnModificarJugador.addActionListener(this::modificarJugador);

        btnBorrarJugador = new JButton("Borrar");
        btnBorrarJugador.addActionListener(this::borrarJugador);

        //Layout
        btnCrearJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificarJugador.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnBorrarJugador.setBackground(Color.RED);
        btnBorrarJugador.setForeground(Color.white);
        btnBorrarJugador.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));


        add(Box.createRigidArea(new Dimension(0, 20))); //espaciado
        add(txtNombreJugador);

        add(Box.createRigidArea(new Dimension(0, 10))); //espaciado
        panelBotones.add(btnCrearJugador);
        panelBotones.add(btnModificarJugador);
        panelBotones.add(btnBorrarJugador);

        add(panelBotones);
    }

    private void refrescarListaJugadores(){
        ActionEvent evento = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "refrescar");

        Container padre = SwingUtilities.getAncestorOfClass(MenuPanel.class, this);

        if (padre != null) {
            padre.dispatchEvent(evento);
        }
    }

    private void crearJugador(ActionEvent event){
        String nombre = txtNombreJugador.getText();

        if(nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre es requerido");
            return;
        };

        Jugador nuevoJugador = new Jugador();
        nuevoJugador.setNombre(nombre);

        try {
            jugadorService.crearJugador(nuevoJugador);
            JOptionPane.showMessageDialog(this, "Jugador Creado con Exito");
            refrescarListaJugadores();
        } catch (JugadorExisteException ex){
            JOptionPane.showMessageDialog(this,ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Error al Crear el Jugador");
        }
    }

    private void modificarJugador(ActionEvent event){
        Jugador jugadorSeleccionado = jugadorService.getJugadorSeleccionado();

        if(jugadorSeleccionado == null) return;

        String nombre = txtNombreJugador.getText();

        if(nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre es requerido");
            return;
        };

        jugadorSeleccionado.setNombre(nombre);

        try {
            jugadorService.modificarJugador(jugadorSeleccionado);
            JOptionPane.showMessageDialog(this, "Jugador Modificado con Exito");
            refrescarListaJugadores();
        } catch (JugadorExisteException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Error al Modificar el Jugador");
        }
    }

    private void borrarJugador(ActionEvent event){

        Jugador jugadorSeleccionado = jugadorService.getJugadorSeleccionado();

        if(jugadorSeleccionado == null) return;

        try {

            int resp=JOptionPane.showConfirmDialog(
                    this,
                    "Desea eliminar al Jugador: " + jugadorSeleccionado.getNombre(),
                    "confirmar",JOptionPane.YES_NO_OPTION
            );

            if (resp==JOptionPane.YES_OPTION){
                jugadorService.borrarJugador(jugadorSeleccionado);
                JOptionPane.showMessageDialog(this,"Se elimino el Jugador correctamente");

                jugadorService.setJugadorSeleccionado(null);
                txtNombreJugador.setText("");
                refrescarListaJugadores();
            }

        } catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Error al Eliminar el Jugador");
        }

    }
}
