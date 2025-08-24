package ui.juego.partida.components;

import javax.swing.*;
import java.awt.*;

public class CartaComponent extends JButton {

    private int numeroOculto;

    public CartaComponent() {
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 24));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    public void revelarNumero(){
        setText(String.valueOf(numeroOculto));
        setEnabled(false);
    };

    public void ocultarNumero(){
        setText("");
        setEnabled(true);
    };


    private int getNumeroOculto() {
        return numeroOculto;
    }

    public void setNumeroOculto(int numeroAsignado) {
        this.numeroOculto = numeroAsignado;
    }

    public boolean comparar(CartaComponent cartaOpuesta){
        return numeroOculto == cartaOpuesta.getNumeroOculto();
    }
}
