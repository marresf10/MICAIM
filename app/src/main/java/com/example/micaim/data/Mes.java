package com.example.micaim.data;

public class Mes {
    float semana1, semana2, semana3, semana4, semana5;

    public Mes(float semana1, float semana2, float semana3, float semana4,float semana5){
        this.semana1 = semana1;
        this.semana2 = semana2;
        this.semana3 = semana3;
        this.semana4 = semana4;
        this.semana5 = semana5;
    }
    @Override
    public String toString() {
        return "Mes{" +
                "semana1=" + semana1 +
                ", semana2=" + semana2 +
                ", semana3=" + semana3 +
                ", semana4=" + semana4 +
                ", semana5=" + semana5 +
                '}';
    }

    public float getSemana1() { return semana1; }

    public float getSemana2() { return semana2; }

    public float getSemana3() { return semana3; }

    public float getSemana4() { return semana4; }

    public float getSemana5() { return semana5; }
}
