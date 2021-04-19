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
public class Transaksi implements Serializable{
    int id_transaksi, total_harga, pembayaran;
    String pengirim, penerima, alamat_tujuan, jenis_pengiriman, tanggal_transaksi, status_barang;

    public Transaksi(int id_transaksi, int total_harga, int pembayaran, String pengirim, String penerima, String alamat_tujuan, String jenis_pengiriman, String tanggal_transaksi, String status_barang) {
        this.id_transaksi = id_transaksi;
        this.total_harga = total_harga;
        this.pembayaran = pembayaran;
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.alamat_tujuan = alamat_tujuan;
        this.jenis_pengiriman = jenis_pengiriman;
        this.tanggal_transaksi = tanggal_transaksi;
        this.status_barang = status_barang;
    }

    

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }

    public int getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(int pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getAlamat_tujuan() {
        return alamat_tujuan;
    }

    public void setAlamat_tujuan(String alamat_tujuan) {
        this.alamat_tujuan = alamat_tujuan;
    }

    public String getJenis_pengiriman() {
        return jenis_pengiriman;
    }

    public void setJenis_pengiriman(String jenis_pengiriman) {
        this.jenis_pengiriman = jenis_pengiriman;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public String getStatus_barang() {
        return status_barang;
    }

    public void setStatus_barang(String status_barang) {
        this.status_barang = status_barang;
    }
    
    
}
