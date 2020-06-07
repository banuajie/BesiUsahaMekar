/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Penjualan;

import MasterData.MasterMaterial;
import MasterData.MasterKonsumen;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import besiusahamekar.koneksidb;
import static besiusahamekar.koneksidb.PathReport;

/**
 *
 * @author Banuajie
 */
public class PesananPenjualan extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;

String number,harga,stotal;
int hargajual;
String tampilan = "yyyy-MM-dd";
String namakonsumen, status="0",nopesandb="";
 int val = 0 ;
    /**
     * Creates new form PesananPenjualan
     */
    public PesananPenjualan() {
        initComponents();
        isidata();
    }
    
     private void setJTable() {
    String [] JudulKolom={"No","No_Pesanan","Tgl Pesanan","Konsumen","Nama","User","Kode Material","Nama Item","Jumlah","Harga","Total","Keterangan","Status"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false,false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTpesanpenjualan.setModel(tabModel);
    jTpesanpenjualan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTpesanpenjualan.getColumnModel().getColumn(0).setPreferredWidth(40);
    jTpesanpenjualan.getColumnModel().getColumn(1).setMinWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(1).setMaxWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(1).setWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(2).setMinWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(2).setMaxWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(2).setWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(3).setMinWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(3).setMaxWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(3).setWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(4).setMinWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(4).setMaxWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(4).setWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(5).setMinWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(5).setMaxWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(5).setWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(6).setPreferredWidth(180);
    jTpesanpenjualan.getColumnModel().getColumn(7).setPreferredWidth(453);
    jTpesanpenjualan.getColumnModel().getColumn(8).setPreferredWidth(100);
    jTpesanpenjualan.getColumnModel().getColumn(9).setPreferredWidth(150);
    jTpesanpenjualan.getColumnModel().getColumn(10).setPreferredWidth(150);
    jTpesanpenjualan.getColumnModel().getColumn(11).setMinWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(11).setMaxWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(11).setWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(12).setMinWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(12).setMaxWidth(0);
    jTpesanpenjualan.getColumnModel().getColumn(12).setWidth(0);
        
   //jTpesanpenjualan.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getPercentRenderer());
   //jTpesanpenjualan.getColumnModel().getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());
   //jTpesanpenjualan.getColumnModel().getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
       
    //getDatapesanan();
    }

       public void isidata(){
        notransaksi();
        hariini();
        //getDatasupplier();
        //getNamaSupplier();
        setJTable();
        
        jmldatatable();
        hitungsubtotal();
        hitungjum();
        jmldatatable();
   }
       
   public void hariini(){
         SimpleDateFormat format = new SimpleDateFormat(tampilan);
        
	   //get current date time with Date()
	   Date date = new Date();
           jDtanggal.setDate(new Date());
     }
       
   public void getDatatransaksi() {
        // import java.sql.connection
    String nopesan = jTnopesan.getText();
   
    SimpleDateFormat sdf = new SimpleDateFormat(tampilan);                 
    try {
            hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemppesananjual where No_Pesanan ='"+nopesan+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      String nopes,Konsumen,nama,user,kodeitem,namaitem,jumlah,harga,total,keterangan,status;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       nopes=rs.getString("No_Pesanan");
                       Date tglpesan=rs.getDate("Tgl_Pesan");
                       Konsumen=rs.getString("Konsumen");
                       nama=rs.getString("Nama");
                       user=rs.getString("User");
                       kodeitem=rs.getString("Kode_Item");
                       namaitem=rs.getString("Nama_Item");
                       jumlah=rs.getString("Jumlah");
                       harga=rs.getString("Harga");
                       total=rs.getString("Total");
                       keterangan=rs.getString("Keterangan");
                       status=rs.getString("status");
                      
                       SimpleDateFormat Format = new SimpleDateFormat("dd-MM-yyyy");
                       String d = Format.format(tglpesan);
                        
                       //jCidsupplier.setText(idsupplier);
         Object Data[]={no,nopes,d,Konsumen,nama,user,kodeitem,nama,jumlah,harga,total,keterangan,status};
         tabModel.addRow(Data);
        }
          // Tutup Koneksi
          st.close();
          rs.close();
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }    

    }
    
   public void tampilDataKeJTable(){
    String nopesan = jTnopesan.getText();
                   
    try {
            hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemppesananjual where No_Pesanan ='"+nopesan+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      
            String nopes,Konsumen,nama,user,kodeitem,namaitem,jumlah,harga,total,keterangan,status;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       nopes=rs.getString("No_Pesanan");
                       Date tglpesan=rs.getDate("Tgl_Pesan");
                       Konsumen=rs.getString("Konsumen");
                       nama=rs.getString("Nama");
                       user=rs.getString("User");
                       kodeitem=rs.getString("Kode_Item");
                       namaitem=rs.getString("Nama_Item");
                       jumlah=rs.getString("Jumlah");
                       harga=rs.getString("Harga");
                       total=rs.getString("Total");
                       keterangan=rs.getString("Keterangan");
                       status=rs.getString("status");
        
                        
                        SimpleDateFormat Format = new SimpleDateFormat("dd-MM-yyyy");
                        String tgl = Format.format(tglpesan);
                        
                        jDtanggal.setDate(tglpesan);
                        
                        jTuser.setText(user);
                        jTkonsumen.setText(Konsumen);
                        jTnama.setText(namakonsumen);
                        jTketerangan.setText(keterangan);
         
                       
         Object Data[]={no,nopes,tgl,Konsumen,nama,user,kodeitem,namaitem,jumlah,harga,total,keterangan,status};
         tabModel.addRow(Data);
        }
         
          // Tutup Koneksi
          st.close();
          rs.close();
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }    

    }
   
    private void hapusIsiJTable() {

         int row = tabModel.getRowCount();
         for (int i = 0; i < row; i++) {
         tabModel.removeRow(0);
         }
         
  }
    
    public void notransaksi(){
        
        Date sk = new Date();
        SimpleDateFormat format1=new SimpleDateFormat("ddMMyyyy");
        String time = format1.format(sk);
        try{
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sql= "select right(No_Pesanan,3) as kd from ttemppesananjual where No_Pesanan Like '%" +time+ "%' order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTnopesan.setText("NOPJ"+time+"000"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTnopesan.setText("NOPJ"+time+"00"+Integer.toString(kode));   
                else if(kode>=1000&&kode<=9999) 
                    jTnopesan.setText("NOPJ"+time+"0"+Integer.toString(kode));
                else
                    jTnopesan.setText("NOPJ"+time+"0000"+Integer.toString(kode));
            }else{

                int kode = 1;

                jTnopesan.setText("NOPJ"+time+"0000"+Integer.toString(kode));

                }

            rs.close();

        }  catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
           catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }    
    }
   
    public void ambildata(){
       String id = jTkodeitem.getText();
      
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tmaterial where Kode_Item ='"+id+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama_Item");
                       String stok =rs.getString("Stok");
                       String hjual =rs.getString("Harga_Jual");
                      
                       jTnamaitem.setText(nama);
                       jTstokitem.setText(stok);
                       hargajual = Integer.parseInt(hjual);
                       
        }
                      
          // Tutup Koneksi
          st.close();
          rs.close();
         
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }  
   }
    
    public void jmldatatable(){
         int rows = jTpesanpenjualan.getRowCount();
         String jmls = String.valueOf(rows);
         jmlData.setText(jmls);
         
             
   } 
    
public void hitungtotal(){
            String sjml = jTjumlah.getText();
            int jml = Integer.parseInt(sjml);
            harga = String.valueOf(hargajual);
            int total;
            if(jml == 0){
                total = hargajual;
                stotal = String.valueOf(total);
            }
            else{
                total = hargajual*jml;
                stotal = String.valueOf(total);
            }
}
   public void hitungsubtotal(){
    int jumlahBaris = jTpesanpenjualan.getRowCount();
    int totalBiaya = 0;
    int jumlah, harga;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTpesanpenjualan.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 8).toString());
        harga = Integer.parseInt(tabelModel.getValueAt(i, 9).toString());
        
        if(jumlah == 0){
            total = harga;
            totalBiaya = totalBiaya + total;
        }
        else{
            total = jumlah*harga;
            totalBiaya = totalBiaya + total;
        }
    }
    jTsubtotal.setText(String.valueOf(totalBiaya));
   }
   
    public void hitungjum(){
    int jumlahBaris = jTpesanpenjualan.getRowCount();
    
    int jumlah;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTpesanpenjualan.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 8).toString());
        
        total = total+jumlah;
    }
    
    jTjmltrans.setText(String.valueOf(total));
   }
    
    
    public void setenable(){
        jTnopesan.setEnabled(true);
        jDtanggal.setEnabled(true);
        //jTuser.setEnabled(true);
                jTkonsumen.setEnabled(true);
                jBkonsumen.setEnabled(true);
                        //jTnama.setEnabled(true);
                //        jTkodeitem.setEnabled(true);
                  //              jBkode.setEnabled(true);
                    //            jTjumlah.setEnabled(true);
                                        //jBcetak.setEnabled(true);
                                        jBsimpanitem.setEnabled(true);
                                        jBtambah.setEnabled(false);
                                        jBsimpan.setEnabled(true);
                                        jBbatal.setEnabled(true);
                                        jTketerangan.setEnabled(true);
                                        jTDP.setEnabled(true);
                                        
     }
     
          public void setdisable(){
         notransaksi();
         jTnopesan.setEnabled(false);
        jDtanggal.setEnabled(false);
        jTuser.setEnabled(false);
                jTkonsumen.setEnabled(false);
                jBkonsumen.setEnabled(false);
                        jTnama.setEnabled(false);
                        jTkodeitem.setEnabled(false);
                                jBkode.setEnabled(false);
                                jTjumlah.setEnabled(false);
                                        jBsimpanitem.setEnabled(false);
                                        jBtambah.setEnabled(true);
                                        jBsimpan.setEnabled(false);
                                        jBbatal.setEnabled(false);
                                        jTketerangan.setEnabled(false);
                                         jTDP.setEnabled(false);
                                        
     }
          
          public void kosongheader(){
              jTkonsumen.setText("");
              jTnama.setText("");
              jTketerangan.setText("");
          }
    
        private void getNamakonsumen() {
        // import java.sql.connection
       String id = jTkonsumen.getText();
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tkonsumen where ID_Konsumen='"+id+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       namakonsumen =rs.getString("Nama");
                       jTnama.setText(namakonsumen);
                       
        }
          // Tutup Koneksi
          st.close();
          rs.close();
         
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }    

    }
        
          
    public void cekkode(){
    int jumlahBaris = jTpesanpenjualan.getRowCount();
    String kodeitem = jTkodeitem.getText();
    
    String kode=null;
    TableModel tabelModel;
    
    tabelModel = jTpesanpenjualan.getModel();
    for (int i=0; i<jumlahBaris; i++){
        kode = tabelModel.getValueAt(i, 6).toString();
        
      if(kodeitem.equals(kode)){
        
            JOptionPane.showMessageDialog(this, "Item "+kode+" Sudah Ada ");
            
            val++;
        }
        
    }
    if(val==0){
        simpanData();
    }
    val = 0 ;
    
   }    
        
    public void simpanData(){
//Connection conn;
        hitungtotal();
    try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttemppesananjual values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
             
             SimpleDateFormat format = new SimpleDateFormat(tampilan);
             String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
             
                st.setString(1, jTnopesan.getText());
                st.setString(2, (String)tanggal);
                st.setString(3, jTkonsumen.getText());
                st.setString(4, jTnama.getText());
                st.setString(5, jTuser.getText());
                st.setString(6, jTkodeitem.getText());
                st.setString(7, jTnamaitem.getText());
                st.setString(8, jTjumlah.getText());
                st.setString(9, harga);
                st.setString(10, stotal);
                st.setString(11, jTketerangan.getText());
                st.setString(12, jTDP.getText());
                
            int rs=st.executeUpdate();
            if(rs>0){
            //JOptionPane.showMessageDialog(this,"Input Berhasil");
      	    
                tampilDataKeJTable();
                       
            }
            st.close();
           
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }
  }
    
    public void hapus_Data() {
    // Konfirmasi sebelum melakukan penghapusan data
    int row = jTpesanpenjualan.getSelectedRow();
    String kode = tabModel.getValueAt(row, 6).toString();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus = '" + kode +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttemppesananjual WHERE Kode_Item = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, kode);
        int rs=st.executeUpdate();
        if(rs>0){
        tampilDataKeJTable();
        
        setJTable();
        //JOptionPane.showMessageDialog(this,"Data Sudah dihapus");
        }
       
      } catch (Exception se) {  // Silahkan tambahkan catch Exception yang lain
         JOptionPane.showMessageDialog(this,"Gagal Hapus Data.. ");
       }
    }
  } 
    
    public void simpankepesanjual(){
      hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into tpesananjual values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
                    
                st.setString(1, jTnopesan.getText());
                st.setString(2, (String)tanggal);
                st.setString(3, jTkonsumen.getText());
                st.setString(4, jTnama.getText());
                st.setString(5, jTjmltrans.getText());
                st.setString(6, jTsubtotal.getText());
                st.setString(7, jTketerangan.getText());
                st.setString(8, jTDP.getText());
                st.setString(9, "0");               
            int rs=st.executeUpdate();

            if(rs>0){
            JOptionPane.showMessageDialog(this,"Berhasil disimpan");
      	    tampilDataKeJTable();
                       
            }
            st.close();
           
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }
   }
    
    private void cekstatustrans() {
              String notrans = jTnopesan.getText();
              
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String trans="Select Status,No_Pesanan from tpesananjual where No_Pesanan ='"+notrans+"'";
            ResultSet rs=st.executeQuery(trans);
                
            while(rs.next()){
            status = rs.getString(1);
            String  nopes = rs.getString(2);
            nopesandb = nopes;
            }}catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
        }catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);}    

    } 

    public void cetaktransaksi(){
         String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("nopesan", jTnopesan.getText());
        try {

            reportSource = PathReport + "BuktiPesananPenjualan.jrxml";
            reportDest = PathReport + "BuktiPesananPenjualan.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }  
    
    public void Transaksibatal() {
    // Konfirmasi sebelum melakukan penghapusan data
            String notransaksi = jTnopesan.getText();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Membatalkan Transaksi = '" + notransaksi +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttemppesananjual WHERE No_Pesanan = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, notransaksi);
        int rs=st.executeUpdate();
       
      } catch (Exception se) {  // Silahkan tambahkan catch Exception yang lain
         JOptionPane.showMessageDialog(this,"Gagal Hapus Data.. ");
       }
    }
  }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBedit = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jBkeluar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTnopesan = new javax.swing.JTextField();
        jTnama = new javax.swing.JTextField();
        jTkonsumen = new javax.swing.JTextField();
        jTuser = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBkode = new javax.swing.JButton();
        jTnamaitem = new javax.swing.JTextField();
        jTjumlah = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jBsimpanitem = new javax.swing.JButton();
        jBkonsumen = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jDtanggal = new com.toedter.calendar.JDateChooser();
        jTkodeitem = new javax.swing.JTextField();
        jTstokitem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jBhapus = new javax.swing.JButton();
        jTjmltrans = new javax.swing.JTextField();
        jBcari = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jBtambah = new javax.swing.JButton();
        jTketerangan = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jmlData = new javax.swing.JLabel();
        jBbatal = new javax.swing.JButton();
        jBsimpan = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jTsubtotal = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTpesanpenjualan = new javax.swing.JTable();
        jTDP = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Pesanan Penjualan Material");

        jBedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tools.png"))); // NOI18N
        jBedit.setText("Edit");
        jBedit.setEnabled(false);
        jBedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel18.setText("Keterangan");

        jBkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        jBkeluar.setText("Keluar");
        jBkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkeluarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Pesanan Penjualan Material", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("No Pesan");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel7.setText("Konsumen");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText(":");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel8.setText("Nama");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText(":");

        jTnopesan.setEnabled(false);
        jTnopesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnopesanKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnopesanKeyTyped(evt);
            }
        });

        jTnama.setEnabled(false);
        jTnama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnamaKeyTyped(evt);
            }
        });

        jTkonsumen.setEnabled(false);
        jTkonsumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTkonsumenKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTkonsumenKeyTyped(evt);
            }
        });

        jTuser.setEnabled(false);
        jTuser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTuserKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel12.setText("User");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText(":");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText(":");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel3.setText("Masukan Kode Material");

        jBkode.setText("jButton1");
        jBkode.setEnabled(false);
        jBkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkodeActionPerformed(evt);
            }
        });

        jTnamaitem.setEnabled(false);
        jTnamaitem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnamaitemKeyTyped(evt);
            }
        });

        jTjumlah.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTjumlah.setText("1");
        jTjumlah.setEnabled(false);
        jTjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTjumlahKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTjumlahKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel16.setText("Jumlah Beli");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(":");

        jBsimpanitem.setText(">");
        jBsimpanitem.setEnabled(false);
        jBsimpanitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsimpanitemActionPerformed(evt);
            }
        });

        jBkonsumen.setText("jButton1");
        jBkonsumen.setEnabled(false);
        jBkonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkonsumenActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel24.setText("Tanggal");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText(":");

        jDtanggal.setDateFormatString("dd-MM-yyyy");
        jDtanggal.setEnabled(false);

        jTkodeitem.setEnabled(false);
        jTkodeitem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTkodeitemKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTkodeitemKeyTyped(evt);
            }
        });

        jTstokitem.setEnabled(false);
        jTstokitem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTstokitemKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("<< Stok Tersedia");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBkode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTstokitem, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(87, 87, 87)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTnopesan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(312, 312, 312)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBsimpanitem, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                            .addComponent(jTnama)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jTkonsumen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBkonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jTnopesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(jLabel25))
                            .addComponent(jDtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jTkonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBkonsumen))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(jBkode)
                        .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTstokitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBsimpanitem)))))
        );

        jBhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bin_Full.png"))); // NOI18N
        jBhapus.setText("Hapus");
        jBhapus.setEnabled(false);
        jBhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhapusActionPerformed(evt);
            }
        });

        jTjmltrans.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTjmltrans.setEnabled(false);

        jBcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.gif"))); // NOI18N
        jBcari.setText("Cari");
        jBcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcariActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText(":");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText(":");

        jBtambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jBtambah.setText("Tambah");
        jBtambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtambahActionPerformed(evt);
            }
        });

        jTketerangan.setEnabled(false);

        jLabel37.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel37.setText("Jumlah Data : ");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText(":");

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jBbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Undo.png"))); // NOI18N
        jBbatal.setText("Batal");
        jBbatal.setEnabled(false);
        jBbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbatalActionPerformed(evt);
            }
        });

        jBsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jBsimpan.setText("Simpan");
        jBsimpan.setEnabled(false);
        jBsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsimpanActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel20.setText("Jumlah Pesan");

        jTsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTsubtotal.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel23.setText("Sub-Total");

        jTpesanpenjualan.setAutoCreateRowSorter(true);
        jTpesanpenjualan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTpesanpenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode", "Nama Item", "Jumlah", "Harga", "Total"
            }
        ));
        jTpesanpenjualan.setFocusable(false);
        jTpesanpenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTpesanpenjualanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTpesanpenjualanMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTpesanpenjualan);

        jTDP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTDP.setText("0");
        jTDP.setEnabled(false);
        jTDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTDPKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTDPKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText(":");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel27.setText("DP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jBedit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBhapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jTjmltrans, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTsubtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTDP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addGap(23, 23, 23)
                        .addComponent(jTketerangan))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jmlData))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jBsimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBbatal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBcari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBkeluar)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jmlData)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBedit)
                            .addComponent(jBhapus)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jTjmltrans, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22)
                                .addComponent(jLabel23))
                            .addComponent(jTsubtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtambah)
                    .addComponent(jBbatal)
                    .addComponent(jBcari)
                    .addComponent(jBsimpan)
                    .addComponent(jBkeluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

            EditDataTabel fDB = new EditDataTabel();

            //fDB.fAB = this;
            int tabel = jTpesanpenjualan.getSelectedRow();

            fDB.kodeitem = jTpesanpenjualan.getValueAt(tabel, 6).toString();
            fDB.namaitem = jTpesanpenjualan.getValueAt(tabel, 7).toString();
            fDB.jumlah = jTpesanpenjualan.getValueAt(tabel, 8).toString();
            fDB.harga = jTpesanpenjualan.getValueAt(tabel, 9).toString();
            fDB.total= jTpesanpenjualan.getValueAt(tabel, 10).toString();

            fDB.jsimpan.setText("Edit Pesan");
            fDB.tampildata();
            fDB.setVisible(true);
    }//GEN-LAST:event_jBeditActionPerformed

    private void jBkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBkeluarActionPerformed

    private void jTnopesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnopesanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            cekstatustrans();
            if(nopesandb.equals(jTnopesan.getText())){
                jBsimpan.setEnabled(false);
            }
            setJTable();
            tampilDataKeJTable();
            getNamakonsumen();
            hitungsubtotal();
            hitungjum();
            jmldatatable();
            jDtanggal.setEnabled(false);
            jTkonsumen.setEnabled(false);
            
        }
    }//GEN-LAST:event_jTnopesanKeyPressed

    private void jTnopesanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnopesanKeyTyped
        // TODO add your handling code here:
        String string = jTnopesan.getText();
        String upper = string.toUpperCase();
        jTnopesan.setText(upper);
    }//GEN-LAST:event_jTnopesanKeyTyped

    private void jTnamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnamaKeyTyped
        // TODO add your handling code here:
        String string = jTnama.getText();
        String upper = string.toUpperCase();
        jTnama.setText(upper);
    }//GEN-LAST:event_jTnamaKeyTyped

    private void jTkonsumenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkonsumenKeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            getNamakonsumen();
            

        }

    }//GEN-LAST:event_jTkonsumenKeyPressed

    private void jTkonsumenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkonsumenKeyTyped
        // TODO add your handling code here:
        String string = jTkonsumen.getText();
        String upper = string.toUpperCase();
        jTkonsumen.setText(upper);
        getNamakonsumen();
        
    }//GEN-LAST:event_jTkonsumenKeyTyped

    private void jTuserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTuserKeyTyped
        // TODO add your handling code here:
        String string = jTuser.getText();
        String upper = string.toUpperCase();
        jTuser.setText(upper);
    }//GEN-LAST:event_jTuserKeyTyped

    private void jBkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkodeActionPerformed
        // TODO add your handling code here:
        MasterMaterial dp=new MasterMaterial();
        
        dp.jBedit.setVisible(false);
        dp.jBhapus.setVisible(false);
        dp.jButton2.setVisible(false);  
        dp.jButton5.setVisible(false);
        dp.setTitle("");
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        
        panelPop.add(dp).setVisible(true);
        
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Material", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))
                getData(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }
     
    }                                         
private void getData(String kode){
            
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tmaterial where Kode_Item ='"+kode+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama_Item");
                       String stok =rs.getString("Stok");
                       String hjual =rs.getString("Harga_Jual");
                       
                       if(kode.equalsIgnoreCase("MTRL-00000")){
                            jTkodeitem.setText(kode);
                            jTnamaitem.setText(nama);
                            jTstokitem.setText(stok);
                            jTjumlah.setText("0");
                            hargajual = Integer.parseInt(hjual); 
                            cekkode();
                            
                            jmldatatable();
                            hitungsubtotal();
                            hitungjum();
                            jmldatatable();
                            jTkonsumen.setEnabled(false);
                            jTnopesan.setEnabled(false);
                            jDtanggal.setEnabled(false);
                       }
                       else{
                            jTkodeitem.setText(kode);
                            jTnamaitem.setText(nama);
                            jTstokitem.setText(stok);
                            hargajual = Integer.parseInt(hjual);
                       }
                       
        }
                      
          // Tutup Koneksi
          st.close();
          rs.close();
         
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }
    }//GEN-LAST:event_jBkodeActionPerformed

    private void jTnamaitemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnamaitemKeyTyped
        // TODO add your handling code here:
        String string = jTnamaitem.getText();
        String upper = string.toUpperCase();
        jTnamaitem.setText(upper);
    }//GEN-LAST:event_jTnamaitemKeyTyped

    private void jBsimpanitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanitemActionPerformed
        // TODO add your handling code here:

        String nopesan = jTnopesan.getText();
        String tglpesan = jDtanggal.getDateFormatString();
        String  konsumen = jTkonsumen.getText();
        String kodeitem = jTkodeitem.getText();
        String jml = jTjumlah.getText();

        int x = Integer.valueOf(jTjumlah.getText());
        int y = Integer.valueOf(jTstokitem.getText());
        
        if(konsumen.equalsIgnoreCase("")||nopesan.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||jml.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong, Mohon Cek Kembali");
            //setJTable();
        }
        else if(x > y){
            if(kodeitem.equalsIgnoreCase("MTRL-00000")){
            cekkode();

            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jmldatatable();
            jTkonsumen.setEnabled(false);
            jTnopesan.setEnabled(false);
            jDtanggal.setEnabled(false);
        }
            else{
                JOptionPane.showMessageDialog(this,"Maaf Permintaan Barang Melebihi Stok Yang Tersedia");
                // setJTable();
            }
        }
        else{
            cekkode();

            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jmldatatable();
            jTkonsumen.setEnabled(false);
            jTnopesan.setEnabled(false);
            jDtanggal.setEnabled(false);

        }
    }//GEN-LAST:event_jBsimpanitemActionPerformed

    private void jBkonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkonsumenActionPerformed
        // TODO add your handling code here:
        MasterKonsumen dp=new MasterKonsumen();
        dp.setTitle("dd");
        dp.jBedit.setVisible(false);
        dp.jBhapus.setVisible(false);
        dp.jBtambah.setVisible(false);
        dp.jButton5.setVisible(false);
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        
        panelPop.add(dp).setVisible(true);
        
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Konsumen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))
                getDatas(dp.getTitle());
                jTkodeitem.setEnabled(true);
                jBkode.setEnabled(true);
                jTjumlah.setEnabled(true);
        } else {
            System.out.println("Cancelled");
        }
     
    }                                         
                                        
private void getDatas(String kode){
    
   
    try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tkonsumen where ID_Konsumen='"+kode+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       namakonsumen =rs.getString("Nama");
                       jTkonsumen.setText(kode);
                       jTnama.setText(namakonsumen);
                       
        }
          // Tutup Koneksi
          st.close();
          rs.close();
         
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }    

      
    }//GEN-LAST:event_jBkonsumenActionPerformed

    private void jTkodeitemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkodeitemKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            jTnamaitem.setText("");
            jTstokitem.setText("");
            ambildata();

        }

    }//GEN-LAST:event_jTkodeitemKeyPressed

    private void jTkodeitemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkodeitemKeyTyped
        // TODO add your handling code here:
        String string = jTkodeitem.getText();
        String upper = string.toUpperCase();
        jTkodeitem.setText(upper);

        ambildata();
    }//GEN-LAST:event_jTkodeitemKeyTyped

    private void jBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
            tampilDataKeJTable();
            hitungsubtotal();
            hitungjum();
            jmldatatable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
            tampilDataKeJTable();
            hitungsubtotal();
            hitungjum();
        }
        jBhapus.setEnabled(false);
    }//GEN-LAST:event_jBhapusActionPerformed

    private void jBcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcariActionPerformed
        // TODO add your handling code here:
        DaftarPesananPenjualan dpj = new DaftarPesananPenjualan();
        this.getParent().add(dpj);
        dpj.s = "1";
        dpj.setJTable();
        dpj.setVisible(true);
    }//GEN-LAST:event_jBcariActionPerformed

    private void jBtambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtambahActionPerformed
        // TODO add your handling code here:
        setenable();
        hariini();
        isidata();
    }//GEN-LAST:event_jBtambahActionPerformed

    private void jBbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbatalActionPerformed
        // TODO add your handling code here:
        Transaksibatal();
        setdisable();
        kosongheader();
        isidata();
    }//GEN-LAST:event_jBbatalActionPerformed

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:
        String nopesan = jTnopesan.getText();
        String tglpesan = jDtanggal.getDateFormatString();
        //String konsumen = jTkonsumen.getText();
        //String nama = jTnama.getText();       
        
        int x = Integer.valueOf(jTDP.getText());
        int y = Integer.valueOf(jTsubtotal.getText());
        int hasil = y/4;
        if(nopesan.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")||x<hasil){
            
            JOptionPane.showMessageDialog(this,"Maaf Uang Muka Kurang Dari 25 %");
            tampilDataKeJTable();
        }else{

            simpankepesanjual();
                             int ok = JOptionPane.showConfirmDialog(this,
        "Cetak Pesanan Ini ?", "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
        
        cetaktransaksi();
    }
            setdisable();
            kosongheader();
            isidata();

        }
    }//GEN-LAST:event_jBsimpanActionPerformed

    private void jTpesanpenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTpesanpenjualanMouseClicked
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();

        if (evt.getClickCount() == 2) {
            Point pnt = evt.getPoint();
            int row = jTpesanpenjualan.rowAtPoint(pnt);
            //do something

            //fDB.fAB = this;
            int tabel = jTpesanpenjualan.getSelectedRow();

            fDB.kodeitem = jTpesanpenjualan.getValueAt(tabel, 6).toString();
            fDB.namaitem = jTpesanpenjualan.getValueAt(tabel, 7).toString();
            fDB.jumlah = jTpesanpenjualan.getValueAt(tabel, 8).toString();
            fDB.harga = jTpesanpenjualan.getValueAt(tabel, 9).toString();
            fDB.total= jTpesanpenjualan.getValueAt(tabel, 10).toString();

            fDB.jsimpan.setText("Edit Pesan");
            fDB.tampildata();
            fDB.setVisible(true);

        }
    }//GEN-LAST:event_jTpesanpenjualanMouseClicked

    private void jTpesanpenjualanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTpesanpenjualanMouseEntered
        // TODO add your handling code here:

        tampilDataKeJTable();
        hitungsubtotal();
        hitungjum();
        jmldatatable();
        jBedit.setEnabled(false);
        jBhapus.setEnabled(false);

    }//GEN-LAST:event_jTpesanpenjualanMouseEntered

    private void jTjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjumlahKeyTyped
        // TODO add your handling code here:
                       char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_jTjumlahKeyTyped

    private void jTDPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTDPKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
      
    }//GEN-LAST:event_jTDPKeyTyped

    private void jTDPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTDPKeyPressed
        // TODO add your handling code here:
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           int x=0;
           if(jTDP.getText().equals("")){
               x=0;
           }
               else{
                  x = Integer.valueOf(jTDP.getText());     
                       }
        int y = Integer.valueOf(jTsubtotal.getText());
        int hasil = y/4;
        if(x<hasil){
           jTDP.setText("0");
            JOptionPane.showMessageDialog(this, "Maaf Uang Muka Kurang Dari 25%");
        }
       }
    }//GEN-LAST:event_jTDPKeyPressed

    private void jTjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjumlahKeyPressed
        // TODO add your handling code here:
     if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {     
        String nopesan = jTnopesan.getText();
        String tglpesan = jDtanggal.getDateFormatString();
        String  konsumen = jTkonsumen.getText();
        String kodeitem = jTkodeitem.getText();
        String jml = jTjumlah.getText();
        
        int x = Integer.valueOf(jTjumlah.getText());
        int y = Integer.valueOf(jTstokitem.getText());

        if(konsumen.equalsIgnoreCase("")||nopesan.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||jml.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            setJTable();
        }
        else if(x > y){
            JOptionPane.showMessageDialog(this,"Maaf Permintaan Barang Melebihi Stok Yang Tersedia");
            setJTable();
        }else{
            cekkode();

            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jmldatatable();
            jTkonsumen.setEnabled(false);
            jTnopesan.setEnabled(false);
            jDtanggal.setEnabled(false);

        }
    }
        
    }//GEN-LAST:event_jTjumlahKeyPressed

    private void jTstokitemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTstokitemKeyTyped
        // TODO add your handling code here:
        String string = jTstokitem.getText();
        String upper = string.toUpperCase();
        jTstokitem.setText(upper);
    }//GEN-LAST:event_jTstokitemKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbatal;
    private javax.swing.JButton jBcari;
    private javax.swing.JButton jBedit;
    private javax.swing.JButton jBhapus;
    private javax.swing.JButton jBkeluar;
    private javax.swing.JButton jBkode;
    private javax.swing.JButton jBkonsumen;
    private javax.swing.JButton jBsimpan;
    private javax.swing.JButton jBsimpanitem;
    private javax.swing.JButton jBtambah;
    private com.toedter.calendar.JDateChooser jDtanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTDP;
    private javax.swing.JTextField jTjmltrans;
    private javax.swing.JTextField jTjumlah;
    private javax.swing.JTextField jTketerangan;
    private javax.swing.JTextField jTkodeitem;
    private javax.swing.JTextField jTkonsumen;
    private javax.swing.JTextField jTnama;
    private javax.swing.JTextField jTnamaitem;
    private javax.swing.JTextField jTnopesan;
    private javax.swing.JTable jTpesanpenjualan;
    private javax.swing.JTextField jTstokitem;
    private javax.swing.JTextField jTsubtotal;
    public javax.swing.JTextField jTuser;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
