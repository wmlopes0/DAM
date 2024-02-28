package ejercicio12;

import java.io.Serializable;
import java.util.Random;

public class Paciente implements Serializable {

    //Atributos
    private int temperatura;
    private int frecuenciaCardiaca;
    private int presionArterialSistolica;
    private int presionArterialDiastolica;

    //Constructor
    //Paciente malo a veces
    public Paciente(boolean buenoSiempre) {
        if (buenoSiempre) {
            this.temperatura = new Random().nextInt(37, 43); //37-42
            this.frecuenciaCardiaca = new Random().nextInt(60, 101);//60-100
            this.presionArterialSistolica = new Random().nextInt(120, 141);//120-140
            this.presionArterialDiastolica = new Random().nextInt(70, 91);//70-90
        } else {
            this.temperatura = new Random().nextInt(30, 50); //37-42
            this.frecuenciaCardiaca = new Random().nextInt(50, 111);//60-100
            this.presionArterialSistolica = new Random().nextInt(100, 160);//120-140
            this.presionArterialDiastolica = new Random().nextInt(50, 111);//70-90
        }
    }

    //Getter y Setter
    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getPresionArterialSistolica() {
        return presionArterialSistolica;
    }

    public void setPresionArterialSistolica(int presionArterialSistolica) {
        this.presionArterialSistolica = presionArterialSistolica;
    }

    public int getPresionArterialDiastolica() {
        return presionArterialDiastolica;
    }

    public void setPresionArterialDiastolica(int presionArterialDiastolica) {
        this.presionArterialDiastolica = presionArterialDiastolica;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "temperatura=" + temperatura +
                ", frecuenciaCardiaca=" + frecuenciaCardiaca +
                ", presionArterialSistolica=" + presionArterialSistolica +
                ", presionArterialDiastolica=" + presionArterialDiastolica +
                '}';
    }
}
