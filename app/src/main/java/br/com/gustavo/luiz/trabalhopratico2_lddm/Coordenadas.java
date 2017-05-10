package br.com.gustavo.luiz.trabalhopratico2_lddm;

/**
 * Created by luiz on 09/05/17.
 */

public class Coordenadas
{
    private int id;
    private String latitude;
    private String longitude;
    private byte[] foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
