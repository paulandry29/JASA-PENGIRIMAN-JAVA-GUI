/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.io.Serializable;

/**
 *
 * @author hp
 */
public class Barang implements Serializable{
    private String nama_barang, jenis_barang;
    private int berat,  sub_total;

    public Barang(String nama_barang, String jenis_barang, int berat, int sub_total) {
        this.nama_barang = nama_barang;
        this.jenis_barang = jenis_barang;
        this.berat = berat;
        this.sub_total = sub_total;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getUkuran() {
        return jenis_barang;
    }

    public void setUkuran(String ukuran) {
        this.jenis_barang = ukuran;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public int getSub_total() {
        return sub_total;
    }

    public void setSub_total(int sub_total) {
        this.sub_total = sub_total;
    }
    
    
    
}
