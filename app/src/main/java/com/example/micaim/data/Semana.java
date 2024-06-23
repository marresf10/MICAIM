package com.example.micaim.data;

public class Semana {
    float lunes, martes, miercoles, jueves, viernes, sabado, domingo;

    public Semana(float lunes, Float martes, float miercoles, float jueves, float viernes, float sabado, float domingo){
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
    }

    @Override
    public String toString() {
        return "Semana{" +
                "Lunes=" + lunes +
                ", Martes=" + martes +
                ", Miercoles=" + miercoles +
                ", Jueves=" + jueves +
                ", Viernes=" + viernes +
                ", Sabado=" + sabado +
                ", Domingo=" + domingo +
                '}';
    }


    public float getLunes() { return lunes; }

    public float getMartes() { return martes; }

    public float getMiercoles() { return miercoles; }

    public float getJueves() { return jueves; }

    public float getViernes() { return viernes; }

    public float getSabado() { return sabado; }

    public float getDomingo() { return domingo; }

}
