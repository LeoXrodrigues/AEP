package com.facu.aep.dto;

public class WeatherDTO {

    private String cidade;
    private double temperatura;
    private String descricao;
    private double latitude;
    private double longitude;
    private String rua;
    private String bairro;

    public WeatherDTO() {}

    public WeatherDTO(String cidade, double temperatura, String descricao, double latitude, double longitude, String rua, String bairro) {
        this.cidade = cidade;
        this.temperatura = temperatura;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rua = rua;
        this.bairro = bairro;
    }

    // Getters e Setters para todos os campos...

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
}
