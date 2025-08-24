package common;

public class Utils {

    public static String castearTiempo(int tiempo){
        int minutos = tiempo / 60;
        int segundos = tiempo % 60;
        return String.format("%d:%02d", minutos, segundos);
    }

}
