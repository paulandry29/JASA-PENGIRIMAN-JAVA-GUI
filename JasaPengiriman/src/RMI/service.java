/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public interface service extends Remote{
    
    public String input_pelanggan(String nama, String alamat, String no_hp) throws RemoteException;
    public String input_barang(String nama_barang, String jenis_barang, int berat, int sub_total) throws RemoteException;
    public ArrayList<Barang> dataBarang() throws RemoteException;
    public String update_table(String table) throws RemoteException;
    public String sum_subTotal() throws RemoteException;
    public void dalete_barang(String id) throws RemoteException;
    public ArrayList<Keranjang> dataKeranjang() throws RemoteException;
    public String input_keranjang(String nama_barang, int id_barang, String jenis_barang, int berat, int sub_total) throws RemoteException;
    public String lastInput_barang()throws RemoteException;
    public void dalete_keranjang(String id) throws RemoteException;
    public void input_transaksi(String pengirim, String penerima, String alamat_tujuan, String jenis_pengiriman, int total_harga, int pembayaran, String status_barang) throws RemoteException;
    public String lastInput_transaksi()throws RemoteException;
    public void input_detailTransaksi(int id_transaksi, int id_barang, String nama_barang, String jenis_barang, int berat, int sub_total)throws RemoteException;
    public void truncate_keranjang()throws RemoteException;
    public ArrayList<Transaksi> dataTransaksi() throws RemoteException;
    public ArrayList<DetailTransaksi> dataDetailTransaksi(String id) throws RemoteException;
    public boolean getLogin(String user, String pass) throws RemoteException;
    public ArrayList<Transaksi> operator_tracking(String id)throws RemoteException;
    public void updateStatus(String id, String status)throws RemoteException;
}
