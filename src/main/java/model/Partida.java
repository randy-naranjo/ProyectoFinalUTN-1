package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "partidas")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "jugador_id", nullable = false)
    private Jugador jugador;

    @NotNull
    private String dificultad;

    @NotNull
    private int tiempo;

    @NotNull
    private int intentos;

    @NotNull
    private LocalDate fecha = LocalDate.now();

    private boolean ganada = false;

    public Partida() {}

    public boolean isGanada() {
        return ganada;
    }

    public void setGanada(boolean ganada) {
        this.ganada = ganada;
    }

    public Partida(String dificultad, int intentos, int tiempo, LocalDate fecha) {
        this.dificultad = dificultad;
        this.intentos = intentos;
        this.tiempo = tiempo;
        this.fecha = fecha;
    }

    public Long getId() { return id; }

    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }

    public String getDificultad() { return dificultad; }
    public void setDificultad(String dificultad) { this.dificultad = dificultad; }

    public int getIntentos() { return intentos; }
    public void setIntentos(int intentos) { this.intentos = intentos; }

    public int getTiempo() { return tiempo; }
    public void setTiempo(int tiempo) { this.tiempo = tiempo; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
