package com.example.micaim.data;

public class Sensores {
    float sensor1, sensor2, sensor3;
    String sensorS1, sensorS2, sensorS3;

    public Sensores(float sensor1, float sensor2, float sensor3, String sensorS1, String sensorS2, String sensorS3) {
        this.sensor1 = sensor1;
        this.sensor2 = sensor2;
        this.sensor3 = sensor3;
        this.sensorS1 = sensorS1;
        this.sensorS2 = sensorS2;
        this.sensorS3 = sensorS3;
    }
    public Sensores() {
        this.sensor1 = 0.0f;
        this.sensor2 = 0.0f;
        this.sensor3 = 0.0f;
        this.sensorS1 = "0.0";
        this.sensorS2 = "0.0";
        this.sensorS3 = "0.0";
    }

    public double getSensor1(){
        return sensor1;
    }
    public String getSensorS1(){
        return sensorS1;
    }

    public double getSensor2(){
        return sensor2;
    }
    public String getSensorS2(){
        return sensorS2;
    }

    public double getSensor3(){
        return sensor3;
    }
    public String getSensorS3(){
        return sensorS3;
    }
}
