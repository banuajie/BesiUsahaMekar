/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Penjualan;

import Pembelian.ReturPembelian;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ReturPenjualan extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;

String formattedNumber;
String number,harga,stotal;
int hargajual;
String tampilan = "dd-MM-yyyy";
public String idsupplier,nopes,no_transaksi;
String notra,kodebarang,statustrans="0",statuspesan="0";
String kode,no_retur,jumlahitem;
 int val = 0 ;

 public String getidsupplier() {
        return idsupplier;
    }
    public String getnopes() {
        return nopes;
    }
    public String getnotrans() {
        return no_transaksi;
    }
    public String getnoretur() {
        return no_retur;
    }
    /**
     * Creates new form ReturPenjualan
     */
    public ReturPenjualan() {
        initComponents();
        isidata();
    }

    
     private void setJTable() {
    String [] JudulKolom={"No","No_Retur","No_Transaksi","Tanggal","Tgl Transaksi","User","ID Konsumen","Kode Material","Nama Item","Jumlah","Harga","Total","Keterangan","Status"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false,false, false, false, false, false, false, false, false, false, false,false,false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTreturjual.setModel(tabModel);
    jTreturjual.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTreturjual.getColumnModel().getColumn(0).setPreferredWidth(35);
    jTreturjual.getColumnModel().getColumn(1).setMinWidth(0);
    jTreturjual.getColumnModel().getColumn(1).setMaxWidth(0);
    jTreturjual.getColumnModel().getColumn(1).setWidth(0);
    jTreturjual.getColumnModel().getColumn(2).setMinWidth(0);
    jTreturjual.getColumnModel().getColumn(2).setMaxWidth(0);
    jTreturjual.getColumnModel().getColumn(2).setWidth(0);
    jTreturjual.getColumnModel().getColumn(3).setMinWidth(0);
    jTreturjual.getColumnModel().getColumn(3).setMaxWidth(0);
    jTreturjual.getColumnModel().getColumn(3).setWidth(0);
    jTreturjual.getColumnModel().getColumn(4).setMinWidth(0);
    jTreturjual.getColumnModel().getColumn(4).setMaxWidth(0);
    jTreturjual.getColumnModel().getColumn(4).setWidth(0);
    jTreturjual.getColumnModel().getColumn(5).setMinWidth(0);
    jTreturjual.getColumnModel().getColumn(5).setMaxWidth(0);
    jTreturjual.getColumnModel().getColumn(5).setWidth(0);
    jTreturjual.getColumnModel().getColumn(6).setMinWidth(0);
    jTreturjual.getColumnModel().getColumn(6).setMaxWidth(0);
    jTreturjual.getColumnModel().getColumn(6).setWidth(0);
    jTreturjual.getColumnModel().getColumn(7).setPreferredWidth(160);
    jTreturjual.getColumnModel().getColumn(8).setPreferredWidth(240);
    jTreturjual.getColumnModel().getColumn(9).setPreferredWidth(60);
    jTreturjual.getColumnModel().getColumn(10).setPreferredWidth(140);
    jTreturjual.getColumnModel().getColumn(1).setPreferredWidth(140);
    jTreturjual.getColumnModel().getColumn(12).setMinWidth(0);
    jTreturjual.getColumnModel().getColumn(12).setMaxWidth(0);
    jTreturjual.getColumnModel().getColumn(12).setWidth(0);
    jTreturjual.getColumnModel().getColumn(13).setMinWidth(0);
    jTreturjual.getColumnModel().getColumn(13).setMaxWidth(0);
    jTreturjual.getColumnModel().getColumn(13).setWidth(0);

   //jTtransaksipembelian.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getPercentRenderer());
   //jTtransaksipembelian.getColumnModel().getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());
   //jTtransaksipembelian.getColumnModel().getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
       
   
    }
    
    public void isidata(){
       setJTable();
       notransaksi();
       hariini();
   }
    
   public void getDatatransaksijual() {
        // import java.sql.connection
    
    String notransaksi = jTnotransaksijual.getText();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");                 
    try {
           
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemptransaksijual where No_Transaksi ='"+notransaksi+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      String notrans,user,idsupplier,kodeitem,nama,harga;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       notrans=rs.getString("No_Transaksi");
                       Date tglpesan=rs.getDate("Tgl_Pesan");
                       user=rs.getString("User");
                       idsupplier=rs.getString("ID_Konsumen");
                       kodeitem=rs.getString("Kode_Item");
                       nama=rs.getString("Nama");
                       harga=rs.getString("Harga");
                                             
                       String tgl = sdf.format(tglpesan);
                       jTuser.setText(user);
                       jTidkonsumen.setText(idsupplier);
                       jCkodeitem.addItem(kodeitem);
                       jTtanggaltransjual.setText(tgl);
        }
         
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
    
    public void getNamakonsumen() {
        // import java.sql.connection
       String id = jTidkonsumen.getText();
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tkonsumen where ID_Konsumen='"+id+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama");
                       jTnamakonsumen.setText(nama);
                       
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
    
    public void hariini(){
         DateFormat dateFormat = new SimpleDateFormat("d / MMM / yyyy");
	   //get current date time with Date()
	   Date date = new Date();
           jDtanggal.setDate(new Date());
     }
    
     public void setenable(){
         
        jTnoretur.setEnabled(true);
         jDtanggal.setEnabled(true);
         jTnotransaksijual.setEnabled(true);
                // jBtransjual.setEnabled(true);
                jCkodeitem.setEnabled(true);
                        
                        
                        jTjumlah.setEnabled(true);
                                jBjumlah.setEnabled(true);
                                jTketerangan.setEnabled(true);
                                        jBsimpan.setEnabled(true);
                                        jBbatal.setEnabled(true);
                                               // jBcetak.setEnabled(true);
        jBtambah.setEnabled(false);
                jBcari.setEnabled(false);
                jBtransjual.setEnabled(true);
                jTketerangan.setEnabled(true);
                                        
     }
     
          public void setdisable(){
         notransaksi();
           jTnoretur.setEnabled(false);
         jDtanggal.setEnabled(false);
         jTnotransaksijual.setEnabled(false);
                 jBtransjual.setEnabled(false);
                jCkodeitem.setEnabled(false);
                        
                        
                        jTjumlah.setEnabled(false);
                                jBjumlah.setEnabled(false);
                                jTketerangan.setEnabled(false);
                                        jBsimpan.setEnabled(false);
                                        jBbatal.setEnabled(false);
                                                
        jBtambah.setEnabled(true);
                jBcari.setEnabled(true);
                jBtransjual.setEnabled(false);
                                        jTketerangan.setEnabled(false);
     }
   
   
    public void notransaksi(){
        
        Date sk = new Date();
        SimpleDateFormat format1=new SimpleDateFormat("ddMMyyyy");
        String time = format1.format(sk);
        try{
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sql= "select right(No_Retur,3) as kd from ttempreturjual where No_Retur Like '%" +time+ "%' order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTnoretur.setText("NRPJ"+time+"000"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTnoretur.setText("NRPJ"+time+"00"+Integer.toString(kode));   
                else if(kode>=1000&&kode<=9999) 
                    jTnoretur.setText("NRPJ"+time+"0"+Integer.toString(kode));
                else
                    jTnoretur.setText("NRPJ"+time+"0000"+Integer.toString(kode));
            }else{

                int kode = 1;

                jTnoretur.setText("NRPJ"+time+"0000"+Integer.toString(kode));

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
    
    public void updatestatus(){
      String noretur =jTnoretur.getText();
    
      String status ="1";
       try{
           
            conn=koneksidb.getkoneksi();
            String updatestatus ="UPDATE ttempreturjual SET Status=? WHERE No_Retur = ?";
            PreparedStatement p=(PreparedStatement) conn.prepareStatement(updatestatus);
             
           p.setString(1, status);
           p.setString(2, noretur);
          
           p.executeUpdate();
            
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }
      
      
  }
    
    public void updatestok(){
         //String kodeitem = jTkodeitem.getText();
         int baris = jTreturjual.getRowCount();
         for (int a=0; a<baris; a++)
	{String kodeitem = jTreturjual.getValueAt(a,7).toString();
         
         String jumlah = jTreturjual.getValueAt(a,9).toString();
         String notrans = jTnotransaksijual.getText();
         String noretur = jTnoretur.getText();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
         
         String stokkeluar = "0" ;
         try{
        conn=koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sqlstok = "SELECT Stok FROM tmaterial WHERE Kode_Item='" +kodeitem+"'"; 
        ResultSet rs=st.executeQuery(sqlstok); 
	while(rs.next()){
            String stokawal = rs.getString(1);
            String insert = "INSERT INTO tstokbarang (No_Transaksi,Tanggal,Kode_Item,Stok_Akhir,Stok_Awal,Stok_Masuk,Stok_Keluar) VALUES( '"+noretur+"','"+tanggal+"','"+kodeitem+"','0','"+stokawal+"','0','0')";
	    
            int stokakhir = Integer.parseInt(stokawal) - Integer.parseInt(jumlah) ;
            String tabelstok ="UPDATE tstokbarang SET Stok_Akhir='" +stokakhir+ "',Stok_Masuk='"+jumlah+ "',Stok_Keluar='"+stokkeluar+ "' WHERE Kode_Item='" + kodeitem + "'";
            String tabelitem ="UPDATE tmaterial SET Stok='" +stokakhir+ "' WHERE Kode_Item='" + kodeitem + "'";
            String tabeltransbeli ="UPDATE ttemptransaksibeli SET Retur='" +jumlah+ "' WHERE No_Transaksi='" + notrans + "' and Kode_Item='" +kodeitem+"'";
            try{
                st= conn.createStatement();
                st.executeUpdate(insert);
                st.executeUpdate(tabelstok);                
                st.executeUpdate(tabelitem);
                st.executeUpdate(tabeltransbeli);
            }catch(Exception sqle){
             System.out.println("Input  Gagal = " + sqle.getMessage());}
        }
        
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
          }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }  
      
     }
     }
    
 public void tampildata(){
       //jTidkonsumen.setEnabled(true);
       jTidkonsumen.setText(idsupplier);
       
       
   }
  public void tampildatatransaksi(){
       
      jTnotransaksijual.setText(no_transaksi);
      
   }
    
  public void tampildataretur(){
       
       jTnoretur.setText(no_retur);
      
   }
  
   public void jmldatatable(){
         int rows = jTreturjual.getRowCount();
         String jmls = String.valueOf(rows);
         jmlData.setText(jmls);
         
             
   } 
    
             public void kosongheader(){
              jTnoretur.setText("");
              jTnotransaksijual.setText("");
              jTtanggaltransjual.setText("");
              jTidkonsumen.setText("");
                      jTnamakonsumen.setText("");
              jTketerangan.setText("");
              jTnamaitem.setText("");
          }
public void hitungtotal(){
            String sjml = jTjumlah.getText();
            int jml = Integer.parseInt(sjml);
            harga = String.valueOf(hargajual);
            int total = hargajual*jml;
            stotal = String.valueOf(total);
}
   public void hitungsubtotal(){
    int jumlahBaris = jTreturjual.getRowCount();
    int totalBiaya = 0;
    int jumlah, harga;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTreturjual.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 9).toString());
        harga = Integer.parseInt(tabelModel.getValueAt(i, 10).toString());
        total = jumlah*harga;
        totalBiaya = totalBiaya + total;
        
    }
    jTsubtotal.setText(String.valueOf(totalBiaya));
   }
   
    public void hitungjum(){
    int jumlahBaris = jTreturjual.getRowCount();
    
    int jumlah;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTreturjual.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 9).toString());
        
        total = total+jumlah;
    }
    
    jTjmlretur.setText(String.valueOf(total));
   }
    
    public void simpanketransaksijual(){
        hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        
       try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into treturjual values(?,?,?,?,?,?,?,?)";
            
            PreparedStatement st=conn.prepareStatement(sql);
                    
                st.setString(1, jTnoretur.getText());
                st.setString(2, jTnotransaksijual.getText());
                st.setString(3, (String)tanggal);
                st.setString(4, jTidkonsumen.getText());
                st.setString(5, jTjmlretur.getText());
                st.setString(6, jTsubtotal.getText());
                st.setString(7, jTketerangan.getText());
                st.setString(8, "1");           
                
            int rs=st.executeUpdate();

            if(rs>0){
            JOptionPane.showMessageDialog(this,"Retur Berhasil disimpan");
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
    
 private void hapusIsiJTable() {

         int row = tabModel.getRowCount();
         for (int i = 0; i < row; i++) {
         tabModel.removeRow(0);
         }
         
  }
    
    public void tampilDataKeJTable(){
    String noret = jTnoretur.getText();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
        
    try {
            hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttempreturjual WHERE No_Retur ='"+noret+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                    String noretur=null,notrans,user,idsupplier,kodeitem,nama,jumlah,harga,total,keterangan,status;
                int no=0;
                   while(rs.next()){
                       no=no+1;
                       
                       noret=rs.getString("No_Retur");
                       notrans=rs.getString("No_Transaksi");
                       Date tanggal=rs.getDate("Tanggal");
                       Date tgltransaksi=rs.getDate("Tgl_Transaksi");
                       user=rs.getString("User");
                       idsupplier=rs.getString("ID_Konsumen");
                       kodeitem=rs.getString("Kode_Item");
                       nama=rs.getString("Nama");
                       jumlah=rs.getString("Jumlah");
                       harga=rs.getString("Harga");
                       total=rs.getString("Total");
                       keterangan=rs.getString("Keterangan");
                       status=rs.getString("Status");
                       
                        
                        jDtanggal.setDate(tanggal);
                        jTnotransaksijual.setText(notrans);
                        jTuser.setText(user);
                        jTidkonsumen.setText(idsupplier);
                        jTketerangan.setText(keterangan);
                       
         Object Data[]={no,noretur,notrans,tanggal,tgltransaksi,user,idsupplier,kodeitem,nama,jumlah,harga,total,keterangan,status};
         tabModel.addRow(Data);
        }
       
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
    
    
    public void hapus() {
    // Konfirmasi sebelum melakukan penghapusan data
    int row = jTreturjual.getSelectedRow();
   String status = "0";
    String noretur = jTnoretur.getText();
      try {
          //int row = jTreturbeli.getSelectedRow();
          //tabModel.removeRow(row);
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttempreturjual WHERE No_Retur = ? and Status= ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, noretur);
        st.setString(2, status);
        int rs=st.executeUpdate();
        
      } catch (Exception se) {  // Silahkan tambahkan catch Exception yang lain
         JOptionPane.showMessageDialog(this,"Gagal Hapus Data.. ");
       }
    
  } 
    
    public void cekkode(){
        
    int jumlahBaris = jTreturjual.getRowCount();
    String kodeitem = jCkodeitem.getSelectedItem().toString();
    
  
    TableModel tabelModel;
    
    tabelModel = jTreturjual.getModel();
    for (int i=0; i<jumlahBaris; i++){
        String kode = tabelModel.getValueAt(i, 7).toString();
               
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        SimpleDateFormat ff = new SimpleDateFormat("dd-MM-yyyy");
        Date d=null;
    try {
        d = ff.parse(jTtanggaltransjual.getText());
    } catch (ParseException ex) {
        Logger.getLogger(ReturPembelian.class.getName()).log(Level.SEVERE, null, ex);
    }
        String tgl = format.format(d);
        
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttempreturjual values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
             
                st.setString(1, jTnoretur.getText());
                st.setString(2, jTnotransaksijual.getText());
                st.setString(3, (String)tanggal);
                st.setString(4, (String)tgl);
                st.setString(5, jTuser.getText());
                st.setString(6, jTidkonsumen.getText());
            
                st.setString(7, jCkodeitem.getSelectedItem().toString());
                st.setString(8, jTnamaitem.getText());
                st.setString(9, jTjumlah.getText());
                st.setString(10, harga);
                st.setString(11, stotal);
                st.setString(12, jTketerangan.getText());
                st.setString(13, "0");
                
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
   
     
     public void ambildata(){
       
       jTjumlah.setText("");
           try{
               String id = jCkodeitem.getSelectedItem().toString();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemptransaksijual where Kode_Item ='"+id+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama_Item");
                       jumlahitem= rs.getString("Jumlah");
                       String hjual =rs.getString("Harga");
                      
                       jTnamaitem.setText(nama);
                                           
                       jTjumlah.setText(jumlahitem);
                       
                       hargajual = Integer.parseInt(hjual);
                       
        }
                      
          // Tutup Koneksi
          st.close();
          rs.close();
         
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
          // System.out.println("Proses Query Gagal = " + sqle);
          // System.exit(0);
    }
    catch(Exception e){
           //System.out.println("Koneksi DB Gagal " +e.getMessage());
           //System.exit(0);
    }  
   }
     
     public void cetaktransaksi(){
         String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("noretur", jTnoretur.getText());
        try {

            reportSource = PathReport + "BuktiReturPenjualan.jrxml";
            reportDest = PathReport + "BuktiReturPenjualan.html";

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
            String notransaksi = jTnoretur.getText();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Membatalkan Transaksi = '" + notransaksi +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttempreturjual WHERE No_Retur = ?";
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
        jBsimpan = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jBbatal = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTnoretur = new javax.swing.JTextField();
        jTnotransaksijual = new javax.swing.JTextField();
        jTuser = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCkodeitem = new javax.swing.JComboBox();
        jTnamaitem = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jBjumlah = new javax.swing.JButton();
        jBtransjual = new javax.swing.JButton();
        jTidkonsumen = new javax.swing.JTextField();
        jTtanggaltransjual = new javax.swing.JTextField();
        jDtanggal = new com.toedter.calendar.JDateChooser();
        jTnamakonsumen = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTjumlah = new javax.swing.JTextField();
        jmlData = new javax.swing.JLabel();
        jTjmlretur = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreturjual = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jBhapus = new javax.swing.JButton();
        jTketerangan = new javax.swing.JTextField();
        jTsubtotal = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jBcari = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jBtambah = new javax.swing.JButton();
        jBedit = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Retur Penjualan Material");

        jBsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jBsimpan.setText("Simpan");
        jBsimpan.setEnabled(false);
        jBsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsimpanActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel37.setText("Jumlah Data : ");

        jBbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Undo.png"))); // NOI18N
        jBbatal.setText("Batal");
        jBbatal.setEnabled(false);
        jBbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbatalActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Retur Penjualan Material", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("No Retur");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("ID Konsumen");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText(":");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel7.setText("Tanggal");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText(":");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel8.setText("No Transaksi Jual");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText(":");

        jTnoretur.setEnabled(false);
        jTnoretur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTnoreturFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTnoreturFocusLost(evt);
            }
        });
        jTnoretur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnoreturKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnoreturKeyTyped(evt);
            }
        });

        jTnotransaksijual.setEnabled(false);
        jTnotransaksijual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnotransaksijualKeyPressed(evt);
            }
        });

        jTuser.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel12.setText("User");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText(":");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText(":");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel3.setText("Masukan Kode Material");

        jCkodeitem.setEnabled(false);
        jCkodeitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCkodeitemActionPerformed(evt);
            }
        });

        jTnamaitem.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel16.setText("Jumlah");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(":");

        jBjumlah.setText(">");
        jBjumlah.setEnabled(false);
        jBjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBjumlahActionPerformed(evt);
            }
        });

        jBtransjual.setText("jButton1");
        jBtransjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtransjualActionPerformed(evt);
            }
        });

        jTidkonsumen.setEnabled(false);

        jTtanggaltransjual.setEnabled(false);

        jDtanggal.setDateFormatString("dd-MM-yyyy");
        jDtanggal.setEnabled(false);

        jTnamakonsumen.setEnabled(false);

        jLabel34.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel34.setText("Nama Konsumen");

        jLabel35.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel35.setText(":");

        jTjumlah.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTjumlah.setEnabled(false);
        jTjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTjumlahKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTjumlahKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jCkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTnamaitem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTnoretur)
                                .addComponent(jDtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTnotransaksijual, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtransjual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTtanggaltransjual, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel35)))))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTnamakonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTidkonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jTidkonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jTnamakonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTnoretur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel7))
                            .addComponent(jDtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTnotransaksijual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtransjual)
                            .addComponent(jTtanggaltransjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBjumlah)
                        .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jCkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jTjmlretur.setEnabled(false);

        jTreturjual.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTreturjual.setModel(new javax.swing.table.DefaultTableModel(
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
        jTreturjual.setCellSelectionEnabled(true);
        jTreturjual.setFocusable(false);
        jTreturjual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreturjualMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTreturjualMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTreturjual);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText(":");

        jBhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bin_Full.png"))); // NOI18N
        jBhapus.setText("Hapus");
        jBhapus.setEnabled(false);
        jBhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhapusActionPerformed(evt);
            }
        });

        jTketerangan.setEnabled(false);

        jTsubtotal.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel18.setText("Keterangan");

        jBcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.gif"))); // NOI18N
        jBcari.setText("Cari");
        jBcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcariActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText(":");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel20.setText("Jumlah Retur");

        jBtambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jBtambah.setText("Tambah");
        jBtambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtambahActionPerformed(evt);
            }
        });

        jBedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tools.png"))); // NOI18N
        jBedit.setText("Edit");
        jBedit.setEnabled(false);
        jBedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        jButton5.setText("Keluar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel23.setText("Sub-Total");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText(":");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19))
                            .addComponent(jBedit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jTketerangan))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBhapus)
                                .addGap(126, 126, 126)
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTjmlretur, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                .addComponent(jButton5)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jmlData)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel23)
                        .addComponent(jTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBedit)
                        .addComponent(jBhapus)
                        .addComponent(jLabel21)
                        .addComponent(jLabel20)
                        .addComponent(jTjmlretur, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtambah)
                    .addComponent(jBsimpan)
                    .addComponent(jBcari)
                    .addComponent(jBbatal)
                    .addComponent(jButton5))
                .addGap(11, 11, 11))
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

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:

        String noretur = jTnoretur.getText();
        String tanggal = jDtanggal.getDateFormatString();
        String notrans = jTnotransaksijual.getText();
        String idkonsumen = jTidkonsumen.getText();
        String subtotal = jTsubtotal.getText();
        int table = jTreturjual.getRowCount();

        if(idkonsumen.equalsIgnoreCase("")||noretur.equalsIgnoreCase("")||tanggal.equalsIgnoreCase("")||table<1||subtotal.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Data Belum Lengkap");

        }else{

            updatestok();
            updatestatus();
            simpanketransaksijual();
            
                        int ok = JOptionPane.showConfirmDialog(this,
        "Cetak Pesanan Ini ?", "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
        
        cetaktransaksi();
    }
            setdisable();
            //kosongheader();
            isidata();

        }
    }//GEN-LAST:event_jBsimpanActionPerformed

    private void jBbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbatalActionPerformed
        // TODO add your handling code here:
        Transaksibatal();
        setdisable();
        kosongheader();
        isidata();
    }//GEN-LAST:event_jBbatalActionPerformed

    private void jTnoreturFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnoreturFocusGained
        // TODO add your handling code here:
        String string = jTnotransaksijual.getText();
        jTnotransaksijual.setText(string.toUpperCase());
    }//GEN-LAST:event_jTnoreturFocusGained

    private void jTnoreturFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnoreturFocusLost
        // TODO add your handling code here:
        String string = jTnoretur.getText();
        String upper = string.toUpperCase();
        jTnoretur.setText(upper);
    }//GEN-LAST:event_jTnoreturFocusLost

    private void jTnoreturKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnoreturKeyTyped
        // TODO add your handling code here:
        int cektabel = jTreturjual.getRowCount();
        hapusIsiJTable();
        setJTable();
        //        getDatatransaksi();
        getNamakonsumen();
        //tampilDataKeJTable();
        hitungsubtotal();
        hitungjum();

        if(cektabel==0){
            jTidkonsumen.setText("");

            jTidkonsumen.setText("");
        }else{
            jTidkonsumen.setEnabled(false);

        }
    }//GEN-LAST:event_jTnoreturKeyTyped

    private void jCkodeitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCkodeitemActionPerformed
        // TODO add your handling code here:
        ambildata();
        String string = jTnamaitem.getText();
        String upper = string.toUpperCase();
        jTnamaitem.setText(upper);
    }//GEN-LAST:event_jCkodeitemActionPerformed

    private void jBjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBjumlahActionPerformed
        // TODO add your handling code here:
        String notrans = jTnoretur.getText();
        String tglpesan = jDtanggal.getDateFormatString();
        String idsupp = jTidkonsumen.getText();
        String jumlah = jTjumlah.getText();
        int y = Integer.parseInt(jumlahitem);
        int x = Integer.parseInt(jumlah);
        if(idsupp.equalsIgnoreCase("")||notrans.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            // setJTable();

        }else{
            if(x>y){
                JOptionPane.showMessageDialog(this,"Jumlah Item Salah");
            }else{
            cekkode();
            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jTnotransaksijual.setEnabled(false);
            jTnoretur.setEnabled(false);

        }
        }
    }//GEN-LAST:event_jBjumlahActionPerformed

    private void jBtransjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtransjualActionPerformed
        // TODO add your handling code here:
        DaftarTransaksiPenjualan dp = new DaftarTransaksiPenjualan();
        jCkodeitem.removeAllItems();
        dp.setTitle("");
        dp.jButton5.setVisible(false);
        dp.setVisible(true);
        javax.swing.JPanel panelPop = new javax.swing.JPanel();

        panelPop.add(dp).setVisible(true);

        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih No Pesanan", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getData(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }

        }
        private void getData(String notransaksi){
       jTnotransaksijual.setText(notransaksi);
    try {
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemptransaksijual where No_Transaksi ='"+notransaksi+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      String notrans,user,idsupplier,kodeitem,nama,harga;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       notrans=rs.getString("No_Transaksi");
                       Date tglpesan=rs.getDate("Tgl_Pesan");
                       user=rs.getString("User");
                       idsupplier=rs.getString("ID_Konsumen");
                       kodeitem=rs.getString("Kode_Item");
                       nama=rs.getString("Nama");
                       harga=rs.getString("Harga");
                                             
                      String tgl = sdf.format(tglpesan);
                       jTuser.setText(user);
                       jTidkonsumen.setText(idsupplier);
                       jCkodeitem.addItem(kodeitem);
                       jTtanggaltransjual.setText(tgl);
        }
         
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }
                
    
    String id = jTidkonsumen.getText();
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tkonsumen where ID_Konsumen='"+id+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama");
                       jTnamakonsumen.setText(nama);
                       jTidkonsumen.setText(id);
                       
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
               
                jTnoretur.setEnabled(false);
                jCkodeitem.setEnabled(true);
    }//GEN-LAST:event_jBtransjualActionPerformed

    private void jTreturjualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreturjualMouseClicked
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();

        if (evt.getClickCount() == 2) {
            Point pnt = evt.getPoint();
            int row = jTreturjual.rowAtPoint(pnt);
            //do something

            //fDB.fAB = this;
            int tabel = jTreturjual.getSelectedRow();

            fDB.no_retur= jTnoretur.getText();
            fDB.kodeitem= jTreturjual.getValueAt(tabel, 7).toString();
            fDB.namaitem = jTreturjual.getValueAt(tabel, 8).toString();
            fDB.jumlah = jTreturjual.getValueAt(tabel, 9).toString();
            fDB.harga = jTreturjual.getValueAt(tabel, 10).toString();
            fDB.total= jTreturjual.getValueAt(tabel, 11).toString();

            fDB.jsimpan.setText("Edit Retur");
            fDB.jTeditkode.setEnabled(false);
            fDB.jTnama.setEnabled(false);
            fDB.jTharga.setEnabled(false);

            fDB.tampildata();
            fDB.setVisible(true);

        }
    }//GEN-LAST:event_jTreturjualMouseClicked

    private void jTreturjualMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreturjualMouseEntered
        // TODO add your handling code here:
        tampilDataKeJTable();
        //getDatatransaksibeli();
        //getDatareturbeli();
        hitungsubtotal();
        hitungjum();
        jBedit.setEnabled(false);
        jBhapus.setEnabled(false);
    }//GEN-LAST:event_jTreturjualMouseEntered

    private void jBcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcariActionPerformed
        // TODO add your handling code here:
        DaftarReturPenjualan dtb = new DaftarReturPenjualan();
        this.getParent().add(dtb);
        dtb.setVisible(true);
    }//GEN-LAST:event_jBcariActionPerformed

    private void jBtambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtambahActionPerformed
        // TODO add your handling code here:

        setenable();
        isidata();
        jTjumlah.setText("");
        jTnamaitem.setText("");
        jCkodeitem.removeAllItems();
    }//GEN-LAST:event_jBtambahActionPerformed

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:
        try{
            EditDataTabel fDB = new EditDataTabel();
            int tabel = jTreturjual.getSelectedRow();

            fDB.no_retur= jTnoretur.getText();
            fDB.kodeitem= jTreturjual.getValueAt(tabel, 7).toString();
            fDB.namaitem = jTreturjual.getValueAt(tabel, 8).toString();
            fDB.jumlah = jTreturjual.getValueAt(tabel, 9).toString();
            fDB.harga = jTreturjual.getValueAt(tabel, 10).toString();
            fDB.total= jTreturjual.getValueAt(tabel, 11).toString();

            fDB.jsimpan.setText("Edit Retur");
            fDB.jTeditkode.setEnabled(false);
            fDB.jTnama.setEnabled(false);
            fDB.jTharga.setEnabled(false);

            fDB.tampildata();
            fDB.setVisible(true);
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Edit");
            setJTable();
            jmldatatable();
            //tampilDataKeJTable();
        }
    }//GEN-LAST:event_jBeditActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        hapus();
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTnotransaksijualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnotransaksijualKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            String cek = jTnotransaksijual.getText();
            int table = jTreturjual.getRowCount();
            if (cek.equals("")){

            }else{

                String string = jTnotransaksijual.getText();
               jTnotransaksijual.setText(string.toUpperCase());

                getDatatransaksijual();
                getNamakonsumen();
               
                jTnoretur.setEnabled(false);
                jCkodeitem.setEnabled(true);
            }

        }

    }//GEN-LAST:event_jTnotransaksijualKeyPressed

    private void jTnoreturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnoreturKeyPressed
        // TODO add your handling code here:
             if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            setJTable();
        tampilDataKeJTable();
        }
    }//GEN-LAST:event_jTnoreturKeyPressed

    private void jTjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjumlahKeyTyped
        // TODO add your handling code here:
                           char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
      
    }//GEN-LAST:event_jTjumlahKeyTyped

    private void jTjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjumlahKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
                    String notrans = jTnoretur.getText();
        String tglpesan = jDtanggal.getDateFormatString();
        String idsupp = jTidkonsumen.getText();
        String jumlah = jTjumlah.getText();
        int y = Integer.parseInt(jumlahitem);
        int x = Integer.parseInt(jumlah);
        if(idsupp.equalsIgnoreCase("")||notrans.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            // setJTable();

        }else{
if(x>y){
                JOptionPane.showMessageDialog(this,"Jumlah Item Salah");
            }else{
            cekkode();
            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jTnotransaksijual.setEnabled(false);
            jTnoretur.setEnabled(false);

        }
        }

        }
    }//GEN-LAST:event_jTjumlahKeyPressed

    private void jBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusActionPerformed
        // TODO add your handling code here:
        try {
            hapus();
            jmldatatable();
            jBhapus.setEnabled(false);
            
            jmldatatable();

            hitungsubtotal();
            hitungjum();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
            jmldatatable();
            
        }
    }//GEN-LAST:event_jBhapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbatal;
    private javax.swing.JButton jBcari;
    private javax.swing.JButton jBedit;
    private javax.swing.JButton jBhapus;
    private javax.swing.JButton jBjumlah;
    private javax.swing.JButton jBsimpan;
    private javax.swing.JButton jBtambah;
    private javax.swing.JButton jBtransjual;
    private javax.swing.JButton jButton5;
    public javax.swing.JComboBox jCkodeitem;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTidkonsumen;
    private javax.swing.JTextField jTjmlretur;
    private javax.swing.JTextField jTjumlah;
    private javax.swing.JTextField jTketerangan;
    private javax.swing.JTextField jTnamaitem;
    private javax.swing.JTextField jTnamakonsumen;
    private javax.swing.JTextField jTnoretur;
    private javax.swing.JTextField jTnotransaksijual;
    private javax.swing.JTable jTreturjual;
    private javax.swing.JTextField jTsubtotal;
    private javax.swing.JTextField jTtanggaltransjual;
    public javax.swing.JTextField jTuser;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
