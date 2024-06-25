package com.example.micaim.data;

public class Semana {
    float lunes, martes, miércoles, jueves, viernes, sábado, domingo;

    public Semana(float lunes, Float martes, float miércoles, float jueves, float viernes, float sábado, float domingo){
        this.lunes = lunes;
        this.martes = martes;
        this.miércoles = miércoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sábado = sábado;
        this.domingo = domingo;
    }

    @Override
    public String toString() {
        return "Semana{" +
                "lunes=" + lunes +
                ", martes=" + martes +
                ", miércoles=" + miércoles +
                ", jueves=" + jueves +
                ", viernes=" + viernes +
                ", sábado=" + sábado +
                ", domingo=" + domingo +
                '}';
    }


    public float getLunes() { return lunes; }

    public float getMartes() { return martes; }

    public float getMiercoles() { return miércoles; }

    public float getJueves() { return jueves; }

    public float getViernes() { return viernes; }

    public float getSabado() { return sábado; }

    public float getDomingo() { return domingo; }

}
