/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class server extends UnicastRemoteObject implements service {

    private static final long serialVersionUID = 1L;

    public server() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(5098);
        registry.bind("db", new server());
        System.out.println("Server is running");
    }

    @Override
    public String input_pelanggan(String nama, String alamat, String no_hp) throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "insert into pelanggan(nama_pelanggan, alamat, no_hp) values('" + nama + "', '" + alamat + "', '" + no_hp + "')";
            st.executeUpdate(sql);
            System.out.println("RMI.server.input_pelanggan() Sukses");
            return "Insert Sukses";
        } catch (Exception e) {
            System.out.println("RMI.server.input_pelanggan() " + e.toString());
            return e.toString();
        }
    }

    @Override
    public String input_barang(String nama_barang, String jenis_barang, int berat, int sub_total) throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "insert into barang(nama_barang, jenis_barang, berat, sub_total) values('" + nama_barang + "', '" + jenis_barang + "', '" + berat + "', '" + sub_total + "')";
            st.executeUpdate(sql);
            System.out.println("RMI.server.input_barang() Sukses");
            return "Insert barang Sukses";
        } catch (Exception e) {
            System.out.println("RMI.server.input_barang() " + e.toString());
            return (e.toString());
        }
    }

    @Override
    public ArrayList<Barang> dataBarang() throws RemoteException {
        ArrayList<Barang> dataBarang = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "select * from barang";
            ResultSet rs = st.executeQuery(sql);
            Barang barang;
            while (rs.next()) {
                barang = new Barang(rs.getString("nama_barang"), rs.getString("jenis_barang"), rs.getInt("berat"), rs.getInt("sub_total"));
                dataBarang.add(barang);
            }
            System.out.println("RMI.server.dataBarang() sukses");

//            if (dataBarang.isEmpty()) {
//                System.out.println("RMI.server.dataBarang() Empty");
//            } else {
//                System.out.println("RMI.server.dataBarang() not Empty");
//            }

        } catch (Exception e) {
            System.out.println("RMI.server.dataBarang() " + e.toString());
        }

        return dataBarang;
    }

    @Override
    public String update_table(String table) throws RemoteException {
        String data="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "select * from "+table;
            ResultSet rs = st.executeQuery(sql);
            data = rs.toString();
        } catch (Exception ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public String sum_subTotal() throws RemoteException {
        String sum ="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "select sum(sub_total) from keranjang as total";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            sum = rs.getString(1);
            if(sum == null){
                sum="0";
            }
        } catch (Exception ex) {
            System.out.println("RMI.server.sum_subTotal() "+ex);
        }
        return sum;
    }

    @Override
    public void dalete_barang(String id) throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            //Statement st = (Statement) con.createStatement();
            //String sql = "delete from barang where nama_barang=?";
            PreparedStatement ps = con.prepareStatement("delete from barang where id_barang=?");
            //st.executeUpdate(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("RMI.server.dalete_barang() Sukses");
        } catch (Exception ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //SELECT * FROM tb_siswa ORDER BY id_siswa DESC LIMIT 1

    @Override
    public ArrayList<Keranjang> dataKeranjang() throws RemoteException {
        ArrayList<Keranjang> dataKeranjang = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "select * from keranjang";
            ResultSet rs = st.executeQuery(sql);
            Keranjang keranjang;
            while (rs.next()) {
                keranjang = new Keranjang(rs.getString("nama_barang"), rs.getString("jenis_barang"), rs.getInt("berat"), rs.getInt("sub_total"), rs.getInt("id_barang"));
                dataKeranjang.add(keranjang);
            }
            System.out.println("RMI.server.dataKeranjang() sukses");

        } catch (Exception e) {
            System.out.println("RMI.server.dataKeranjang() " + e.toString());
        }

        return dataKeranjang;
    }

    @Override
    public String input_keranjang(String nama_barang, int id_barang, String jenis_barang, int berat, int sub_total) throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "insert into keranjang(id_barang, nama_barang, jenis_barang, berat, sub_total) values('" + id_barang + "', '" + nama_barang + "', '" + jenis_barang + "', '" + berat + "', '" + sub_total + "')";
            st.executeUpdate(sql);
            System.out.println("RMI.server.input_Keranjang() Sukses");
            return "Insert barang Sukses";
        } catch (Exception e) {
            System.out.println("RMI.server.input_Keranjang() " + e.toString());
            return (e.toString());
        }
    }

    @Override
    public String lastInput_barang() throws RemoteException {
        String id="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT * FROM barang ORDER BY id_barang DESC LIMIT 1";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            id = rs.getString(1);
            System.out.println("RMI.server.lastInput_barang() sukses");
            
        } catch (Exception e) {
            System.out.println("RMI.server.lastInput_barang() "+ e.toString());
        }
        return id;
    }

    @Override
    public void dalete_keranjang(String id) throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            PreparedStatement ps = con.prepareStatement("delete from keranjang where id_barang=?");
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("RMI.server.dalete_keranjang() Sukses");
        } catch (Exception ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void input_transaksi(String pengirim, String penerima, String alamat_tujuan, String jenis_pengiriman, int total_harga, int pembayaran, String status_barang) throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "insert into transaksi(pengirim, penerima, alamat_tujuan,jenis_pengiriman, total_harga, pembayaran, status_barang) "
                    + "values('" + pengirim + "', '" + penerima + "', '" + alamat_tujuan + "', '" + jenis_pengiriman + "', '" + total_harga + "', '" + pembayaran + "', '" + status_barang + "')";
            st.executeUpdate(sql);
            System.out.println("RMI.server.input_transaksi() Sukses");
        } catch (Exception e) {
            System.out.println("RMI.server.input_transaksi() " + e.toString());
        }
    }

    @Override
    public String lastInput_transaksi() throws RemoteException {
        String id="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT * FROM transaksi ORDER BY id_transaksi DESC LIMIT 1";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            id = rs.getString(1);
            System.out.println("RMI.server.lastInput_transaksi() sukses");
            
        } catch (Exception e) {
            System.out.println("RMI.server.lastInput_transaksi() "+ e.toString());
        }
        return id;
    }

    @Override
    public void input_detailTransaksi(int id_transaksi, int id_barang, String nama_barang, String jenis_barang, int berat, int sub_total) throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "insert into detail_transaksi(id_transaksi, id_barang, nama_barang, jenis_barang, berat, sub_total) "
                    + "values('" + id_transaksi + "', '" + id_barang + "', '" + nama_barang + "', '" + jenis_barang + "', '" + berat + "', '" + sub_total + "')";
            st.executeUpdate(sql);
            System.out.println("RMI.server.input_transaksi() Sukses");
        } catch (Exception e) {
            System.out.println("RMI.server.input_transaksi() " + e.toString());
        }
    }

    @Override
    public ArrayList<Transaksi> dataTransaksi() throws RemoteException {
        ArrayList<Transaksi> dataTransaksi = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            String sql = "select * from transaksi";
            ResultSet rs = st.executeQuery(sql);
            Transaksi transaksi;
            while (rs.next()) {
                transaksi = new Transaksi(rs.getInt("id_transaksi"), rs.getInt("total_harga"), rs.getInt("pembayaran"), rs.getString("pengirim"), rs.getString("penerima"), rs.getString("alamat_tujuan"), rs.getString("jenis_pengiriman"), rs.getString("tanggal_transaksi"), rs.getString("status_barang"));
                dataTransaksi.add(transaksi);
            }
            System.out.println("RMI.server.dataKeranjang() sukses");

        } catch (Exception e) {
            System.out.println("RMI.server.dataKeranjang() " + e.toString());
        }

        return dataTransaksi;
    }

    @Override
    public void truncate_keranjang() throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            PreparedStatement ps = con.prepareStatement("truncate table keranjang");
            ps.executeUpdate();
            System.out.println("RMI.server.truncate_keranjang() Sukses");
        } catch (Exception ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<DetailTransaksi> dataDetailTransaksi(String id) throws RemoteException {
        ArrayList<DetailTransaksi> dataDetailTransaksi = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            Statement st = (Statement) con.createStatement();
            PreparedStatement ps = con.prepareStatement("select * from detail_transaksi where id_transaksi=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            DetailTransaksi detailtransaksi;
            while (rs.next()) {
                detailtransaksi = new DetailTransaksi(rs.getInt("id_transaksi"), rs.getInt("id_barang"), rs.getString("nama_barang"), rs.getString("jenis_barang"), rs.getInt("berat"), rs.getInt("sub_total"));
                dataDetailTransaksi.add(detailtransaksi);
            }
            System.out.println("RMI.server.dataDetailTransaksi() sukses");

        } catch (Exception e) {
            System.out.println("RMI.server.dataDetailTransaksi() " + e.toString());
        }

        return dataDetailTransaksi;
    }

    @Override
    public ArrayList<Transaksi> operator_tracking(String id) throws RemoteException {
        ArrayList<Transaksi> dataTransaksi = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");//  Statement st = (Statement) con.createStatement();
            PreparedStatement ps = con.prepareStatement("select * from transaksi where id_transaksi=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            Transaksi transaksi;
            while (rs.next()) {
                transaksi = new Transaksi(rs.getInt("id_transaksi"), rs.getInt("total_harga"), rs.getInt("pembayaran"), rs.getString("pengirim"), rs.getString("penerima"), rs.getString("alamat_tujuan"), rs.getString("jenis_pengiriman"), rs.getString("tanggal_transaksi"), rs.getString("status_barang"));
                dataTransaksi.add(transaksi);
            }
        } catch (Exception e) {
            System.out.println("RMI.server.operator_tracking()"+e);
        }
        return dataTransaksi;
    }

    @Override
    public void updateStatus(String id, String status) throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
            PreparedStatement ps = con.prepareStatement("update transaksi set status_barang=? where id_transaksi=?");
            ps.setString(1, status);
            ps.setString(2, id);
            ps.executeUpdate();
            System.out.println("RMI.server.updateStatus() Sukses");
        } catch (Exception ex) {
            System.out.println("RMI.server.updateStatus()"+ex);
        }
    }

    @Override
    public boolean getLogin(String user, String pass) throws RemoteException {
        ArrayList<admin> dataAdmin=new ArrayList<>();
        boolean found=false;
        String id="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jasa_pengiriman2", "root", "");
          //  Statement st = (Statement) con.createStatement();
            PreparedStatement ps = con.prepareStatement("select * from admin where username=? and password=?");
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
           // id=rs.getString(1);
            admin Admin = null;
            while(rs.next()){
                Admin = new admin(rs.getInt("id_admin"), rs.getString("username"), rs.getString("password"));
                dataAdmin.add(Admin);
            }
            if (Admin==null){
            
                return found=false;
            }else
            {
                return found=true;
            }
        }
        catch(Exception ex){
        ex.printStackTrace();
            System.out.println("RMI.server.getLogin() gagal"+ex);
        }
        return found;
    }
    
    

}
