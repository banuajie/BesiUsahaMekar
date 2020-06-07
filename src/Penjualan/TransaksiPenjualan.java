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
import java.text.DateFormat;
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
public class TransaksiPenjualan extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;
String number,harga,stotal;
int hargajual;
String tampilan = "yyyy-MM-dd";
String notra,kodebarang,namakonsumen,status,statustrans="0",statuspesan="0";
String idsupplier,nopes,no_transaksi,nopesandb="",DP="0";
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
    /**
     * Creates new form TransaksiPenjualan2
     */
    public TransaksiPenjualan() {
        initComponents();
           isidata();
    }
    
        public void hariini(){
         SimpleDateFormat format = new SimpleDateFormat(tampilan);
         DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	   //get current date time with Date()
	   Date date = new Date();
           jDtanggal.setDate(new Date());
     }
    
     private void setJTable() {
    String [] JudulKolom={"No","No_Transaksi","No Pesanan","Tgl Pesan","ID Konsumen","Nama","User","Kode Material","Nama Item","Jumlah","Harga","Total","Retur","Keterangan"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false,false,false,false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTtransjual.setModel(tabModel);
    jTtransjual.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTtransjual.getColumnModel().getColumn(0).setPreferredWidth(35);
    jTtransjual.getColumnModel().getColumn(1).setMinWidth(0);
    jTtransjual.getColumnModel().getColumn(1).setMaxWidth(0);
    jTtransjual.getColumnModel().getColumn(1).setWidth(0);
    jTtransjual.getColumnModel().getColumn(2).setMinWidth(0);
    jTtransjual.getColumnModel().getColumn(2).setMaxWidth(0);
    jTtransjual.getColumnModel().getColumn(2).setWidth(0);
    jTtransjual.getColumnModel().getColumn(3).setMinWidth(0);
    jTtransjual.getColumnModel().getColumn(3).setMaxWidth(0);
    jTtransjual.getColumnModel().getColumn(3).setWidth(0);
    jTtransjual.getColumnModel().getColumn(4).setMinWidth(0);
    jTtransjual.getColumnModel().getColumn(4).setMaxWidth(0);
    jTtransjual.getColumnModel().getColumn(4).setWidth(0);
    jTtransjual.getColumnModel().getColumn(5).setMinWidth(0);
    jTtransjual.getColumnModel().getColumn(5).setMaxWidth(0);
    jTtransjual.getColumnModel().getColumn(5).setWidth(0);
    jTtransjual.getColumnModel().getColumn(6).setMinWidth(0);
    jTtransjual.getColumnModel().getColumn(6).setMaxWidth(0);
    jTtransjual.getColumnModel().getColumn(6).setWidth(0);
    jTtransjual.getColumnModel().getColumn(7).setPreferredWidth(180);
    jTtransjual.getColumnModel().getColumn(8).setPreferredWidth(310);
    jTtransjual.getColumnModel().getColumn(9).setPreferredWidth(100);
    jTtransjual.getColumnModel().getColumn(10).setPreferredWidth(150);
    jTtransjual.getColumnModel().getColumn(11).setPreferredWidth(150);
    jTtransjual.getColumnModel().getColumn(12).setMinWidth(0);
    jTtransjual.getColumnModel().getColumn(12).setMaxWidth(0);
    jTtransjual.getColumnModel().getColumn(12).setWidth(0);
    jTtransjual.getColumnModel().getColumn(13).setMinWidth(0);
    jTtransjual.getColumnModel().getColumn(13).setMaxWidth(0);
    jTtransjual.getColumnModel().getColumn(13).setWidth(0);
     
   //jTtransaksipembelian.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getPercentRenderer());
   //jTtransaksipembelian.getColumnModel().getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());
   //jTtransaksipembelian.getColumnModel().getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
       
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
   }
    
   public void getDatatransaksi() {
        // import java.sql.connection
    String nopesan = jTnopesan.getText();
   String notranss = jTransaksi.getText();
               
    try {
            hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemppesananjual where No_Pesanan ='"+nopesan+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      String notrans,nopes,Konsumen,nama,user,kodeitem,namaitem,jumlah,harga,total,keterangan,status;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       
                       notrans=notranss;
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
                      
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     
                        String d= sdf.format(tglpesan);
                        jTtglpesan.setDate(tglpesan);
                        jTkonsumen.setText(Konsumen);
                        jTnama.setText(nama);
                        jTuser.setText(user);
                        jTketerangan.setText(keterangan);
                       
                       //jCidsupplier.setText(idsupplier);
         Object Data[]={no,notrans,nopes,d,Konsumen,nama,user,kodeitem,namaitem,jumlah,harga,total,keterangan,status};
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
       
     public void notransaksi(){
        
        Date sk = new Date();
        SimpleDateFormat format1=new SimpleDateFormat("ddMMyyyy");
        String time = format1.format(sk);
        try{
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sql= "select right(No_Transaksi,3) as kd from ttemptransaksijual where No_Transaksi Like '%" +time+ "%' order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTransaksi.setText("NTPJ"+time+"000"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTransaksi.setText("NTPJ"+time+"00"+Integer.toString(kode));   
                else if(kode>=1000&&kode<=9999) 
                    jTransaksi.setText("NTPJ"+time+"0"+Integer.toString(kode));
                else
                    jTransaksi.setText("NTPJ"+time+"0000"+Integer.toString(kode));
            }else{

                int kode = 1;

                jTransaksi.setText("NTPJ"+time+"0000"+Integer.toString(kode));

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
       
     public void jmldatatable(){
         int rows = jTtransjual.getRowCount();
         String jmls = String.valueOf(rows);
         jmlData.setText(jmls);
         
             
   } 
    
    public void hitungtotal(){
            String sjml = jTjml.getText();
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
    int jumlahBaris = jTtransjual.getRowCount();
    int totalBiaya = 0;
    int jumlah, harga;
    int total=0;
    
    TableModel tabelModel;
    tabelModel = jTtransjual.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 9).toString());
        harga = Integer.parseInt(tabelModel.getValueAt(i, 10).toString());
        
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
   
   public void hitungsisabayar(){
         jTsisabayar.setText("0");
         int total;
             int bayar;
        if(jTjmlbayar.getText().equals("")){
            bayar=0;
        }  else{
                    bayar = Integer.parseInt(jTjmlbayar.getText());
                    }
        int idp = Integer.parseInt(jTDP.getText());
        int y = Integer.parseInt(jTsubtotal.getText());
        int hasil = idp+bayar;
        if(hasil<y){
            JOptionPane.showMessageDialog(this, "Jumlah Bayar Kurang");
            jTjmlbayar.setText("0");
        }
         int sub = Integer.parseInt(jTsubtotal.getText());
         
         total = (bayar+idp)-sub;
         String stotal = String.valueOf(total);
         jTsisabayar.setText(stotal);
         
     }
   
    public void hitungjum(){
    int jumlahBaris = jTtransjual.getRowCount();
    
    int jumlah;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTtransjual.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 9).toString());
        
        total = total+jumlah;
    }
    
    jTjmltrans.setText(String.valueOf(total));
   }
     
    public void setenable(){
        jTransaksi.setEnabled(true);
        jTnopesan.setEnabled(true);
        jDtanggal.setEnabled(true);
        
        jTkonsumen.setEnabled(true);
       jTjmlbayar.setEnabled(true);
        jBnopesan.setEnabled(true);
                jTkonsumen.setEnabled(true);
                jBkonsumen.setEnabled(true);
                        jTsubtotal.setEnabled(true);
                        jTsisabayar.setEnabled(true);
                        jTkodeitem.setEnabled(true);
                                jBkodeitem.setEnabled(true);
                                jTjml.setEnabled(true);
                                       // jBcetak.setEnabled(true);
                                        jBjumlah.setEnabled(true);
                                        jBtambah.setEnabled(false);
                                        jBsimpan.setEnabled(true);
                                        jBbatal.setEnabled(true);
                                        jTketerangan.setEnabled(true);
                                        
     }
     
          public void setdisable(){
         notransaksi();
         jTransaksi.setEnabled(false);
        jTnopesan.setEnabled(false);
        jDtanggal.setEnabled(false);
        jTjmlbayar.setEnabled(false);
        jTkonsumen.setEnabled(false);
        
        jBnopesan.setEnabled(false);
                jTkonsumen.setEnabled(false);
                jBkonsumen.setEnabled(false);
                        jTsubtotal.setEnabled(false);
                        jTsisabayar.setEnabled(false);
                        jTkodeitem.setEnabled(false);
                                jBkodeitem.setEnabled(false);
                                jTjml.setEnabled(false);
                                        jBjumlah.setEnabled(false);
                                        jBtambah.setEnabled(true);
                                        jBsimpan.setEnabled(false);
                                        jBbatal.setEnabled(false);
                                        jTketerangan.setEnabled(false);
                                        
     }
          
          public void kosongheader(){
              jTkonsumen.setText("");
              jTnama.setText("");
              jTketerangan.setText("");
          }
    
    public void tampilDataKeJTable(){
    String notransaksi = jTransaksi.getText();
    SimpleDateFormat sdf = new SimpleDateFormat(tampilan);                 
    try {
            hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemptransaksijual where No_Transaksi ='"+notransaksi+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      
            String notrans,nopes,idkonsumen,nama,user,kodeitem,namaitem,jumlah,harga,total,retur,keterangan;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       notrans=rs.getString("No_Transaksi");
                       nopes=rs.getString("No_Pesanan");
                       Date tanggal=rs.getDate("Tgl_Pesan");
                       idkonsumen=rs.getString("ID_Konsumen");
                       nama=rs.getString("Nama");
                       user=rs.getString("User");
                       kodeitem=rs.getString("Kode_Item");
                       namaitem=rs.getString("Nama_Item");
                       jumlah=rs.getString("Jumlah");
                       harga=rs.getString("Harga");
                       total=rs.getString("Total");
                       retur=rs.getString("Retur");
                       keterangan=rs.getString("Keterangan");
                      
                        String d = sdf.format(tanggal);
                        jDtanggal.setDate(tanggal);
                        jTnopesan.setText(nopes);
                        
                        jTuser.setText(user);
                        jTkonsumen.setText(idkonsumen);
                        jTnama.setText(nama);
                        jTketerangan.setText(keterangan);
         
                       
         Object Data[]={no,notrans,nopes,d,idkonsumen,nama,user,kodeitem,namaitem,jumlah,harga,total,retur,keterangan};
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
    
    
   public void getNamakonsumen() {
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
    
   
   public void cekkode(){
        
    int jumlahBaris = jTtransjual.getRowCount();
    String kodeitem = jTkodeitem.getText();
   
    String kode=null;
    TableModel tabelModel;
    
    tabelModel = jTtransjual.getModel();
    for (int i=0; i<jumlahBaris; i++){
        kode = tabelModel.getValueAt(i, 7).toString();
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
       if(jTkonsumen.getText().trim().equals("")&&jTnama.getText().trim().equals("")){
           String idkonsumen = "KNSM-0000";
           String nama        = "KONSUMEN";     
           hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttemptransaksijual values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
             
                st.setString(1, jTransaksi.getText());
                st.setString(2, jTnopesan.getText());
                st.setString(3, (String)tanggal);
                st.setString(4, idkonsumen);
                st.setString(5, nama);
                st.setString(6, jTuser.getText());
                st.setString(7, jTkodeitem.getText());
                st.setString(8, jTnamaitem.getText());
                st.setString(9, jTjml.getText());
                st.setString(10, harga);
                st.setString(11, stotal);
                st.setString(12, "0");
                st.setString(13, jTketerangan.getText());
                
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
       }else{
           
         hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttemptransaksijual values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
             
                st.setString(1, jTransaksi.getText());
                st.setString(2, jTnopesan.getText());
                st.setString(3, (String)tanggal);
                st.setString(4, jTkonsumen.getText());
                st.setString(5, jTnama.getText());
                st.setString(6, jTuser.getText());
                st.setString(7, jTkodeitem.getText());
                st.setString(8, jTnamaitem.getText());
                st.setString(9, jTjml.getText());
                st.setString(10, harga);
                st.setString(11, stotal);
                st.setString(12, "0");
                st.setString(13, jTketerangan.getText());
                
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
       
        
  }
   
   private void cekstatustrans() {
              String notrans = jTransaksi.getText();
              
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String trans="Select Status,No_Transaksi from ttransaksijual where No_Transaksi ='"+notrans+"'";
            ResultSet rs=st.executeQuery(trans);
                
            while(rs.next()){
            String cekstatus = rs.getString(1);
            String notransaksi = rs.getString(2);
            if(cekstatus.equals(null)){
                statustrans="0";
            }
            else{
            statustrans = cekstatus;
            nopesandb = notransaksi;
            }
        }}catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
        }catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);}    

    }    
   private void cekstatuspesan() {
            String nopes = jTnopesan.getText();
             
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String pesan="Select DP,Status from tpesananjual where No_Pesanan ='"+nopes+"'";
            ResultSet rs=st.executeQuery(pesan);
                
            while(rs.next()){
            DP  = rs.getString(1);
            statuspesan  = rs.getString(2);
            
        }}catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
        }catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);}    

    }
   
   public void cekkodeganda(){
        
    int jumlahBaris = jTtransjual.getRowCount();
    String kodeitem = jTkodeitem.getText();
    int val = 0 ;
    String kode=null;
    TableModel tabelModel;
    
    tabelModel = jTtransjual.getModel();
    for (int i=0; i<jumlahBaris; i++){
        kode = tabelModel.getValueAt(i, 7).toString();
      if(kodeitem.equals(kode)){
        
            JOptionPane.showMessageDialog(this, "Item "+kode+" Sudah Ada ");
            
            val++;
        }
        
    }
    if(val==0){
        simpanketemptransaksi();
        updateketdata();
    }
    
    
   }
   
   public void updateketdata(){
    
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update tpesananjual set Status= ? WHERE No_Pesanan = ?";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
       try {
                     
           p.setString(1, "1");
           p.setString(2, jTnopesan.getText());
           
           
           p.executeUpdate();
           int rs= p.executeUpdate();
          
           if(rs>=0){ }

         // Memanggil Method   tampilDataKeJTable();
        
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
    }catch (SQLException se) {}
    
        
    
    }
   
   public void simpanketemptransaksi(){
//Connection conn;
      String notrans =jTransaksi.getText();
      String nopes = jTnopesan.getText();
      String idpel = jTkonsumen.getText();
      String nama = jTnama.getText();
      String ket = jTketerangan.getText();
     try{
            conn=koneksidb.getkoneksi();
            Statement state = conn.createStatement();
            int baris = jTtransjual.getRowCount();
           for (int a=0; a<baris; a++)
		{
                
                        
		String query = "INSERT INTO ttemptransaksijual (No_Transaksi,No_Pesanan,Tgl_Pesan,ID_Konsumen,Nama,User,Kode_Item,Nama_Item,Jumlah,Harga,Total,Retur,Keterangan) VALUES( '"+notrans+"','"+nopes+"','"+jTtransjual.getValueAt(a,3)+"','"+idpel+"','"+nama+"','"+jTtransjual.getValueAt(a,6)+"','"+jTtransjual.getValueAt(a,7)+"','"+jTtransjual.getValueAt(a,8)+"','"+jTtransjual.getValueAt(a,9)+"','"+jTtransjual.getValueAt(a,10)+"','"+jTtransjual.getValueAt(a,11)+"','"+jTtransjual.getValueAt(a,12)+"','"+ket+"')";
		state.executeUpdate(query);
			}
           state.close();
           //JOptionPane.showMessageDialog(null,"Data berhasil disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE);
            //hapusdatadb();
           getDatatransaksi();
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }         int col = tabModel.getColumnCount();     
  
  }
   
        public void hapus_Data() {
    // Konfirmasi sebelum melakukan penghapusan data
    int row = jTtransjual.getSelectedRow();
    kodebarang = tabModel.getValueAt(row, 7).toString();
    notra = tabModel.getValueAt(row, 1).toString();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus = '" + kodebarang + "' Transaksi = '" + notra + 
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttemptransaksijual WHERE Kode_Item = ? and No_Transaksi = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, kodebarang);
        st.setString(2, notra);
        int rs=st.executeUpdate();
        if(rs>0){
       // tampilDataKeJTable();
         getDatatransaksi();
        //setJTable();
        //JOptionPane.showMessageDialog(this,"Data Sudah dihapus");
        }
       
      } catch (Exception se) {  // Silahkan tambahkan catch Exception yang lain
         JOptionPane.showMessageDialog(this,"Gagal Hapus Data.. ");
       }
    }
  } 
   
     public void tampildatatransaksi(){
       
       jTransaksi.setText(no_transaksi);
      
   }    
     
   public void simpanketransaksijual(){
        hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        
       try{
           String idpel;
        String namapel;
        if(jTkonsumen.getText().trim().equals("")&&jTnama.getText().trim().equals("")){
           idpel = "-";
           namapel       = "-";  
        }else{
            idpel = jTkonsumen.getText();
             namapel=jTnama.getText(); 
           
        }
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttransaksijual values(?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement st=conn.prepareStatement(sql);
                    
                st.setString(1, jTransaksi.getText());
                st.setString(2, jTnopesan.getText());
                st.setString(3, (String)tanggal);
                st.setString(4, idpel);
                st.setString(5, namapel);
                st.setString(6, jTjmltrans.getText());
                st.setString(7, jTsubtotal.getText());
                st.setString(8, jTDP.getText());
                st.setString(9, jTjmlbayar.getText());
                st.setString(10, jTsisabayar.getText());
                st.setString(11, jTketerangan.getText());
                st.setString(12, "1");               
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
     
    
    public void updatestok(){
         //String kodeitem = jTkodeitem.getText();
         int baris = jTtransjual.getRowCount();
         for (int a=0; a<baris; a++)
	{String kodeitem = jTtransjual.getValueAt(a,7).toString();
         
         String jumlah = jTtransjual.getValueAt(a,9).toString();
         String notrans = jTransaksi.getText();
         SimpleDateFormat format = new SimpleDateFormat(tampilan);
         String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
         
         String stokmasuk = "0" ;
         try{
        conn=koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sqlstok = "SELECT Stok FROM tmaterial WHERE Kode_Item='" +kodeitem+"'"; 
        ResultSet rs=st.executeQuery(sqlstok); 
	while(rs.next()){
            String stokawal = rs.getString(1);
            String insert = "INSERT INTO tstokbarang (No_Transaksi,Tanggal,Kode_Item,Stok_Akhir,Stok_Awal,Stok_Masuk,Stok_Keluar) VALUES( '"+notrans+"','"+tanggal+"','"+kodeitem+"','0','"+stokawal+"','0','0')";
	    
            int stokakhir = Integer.parseInt(stokawal)-Integer.parseInt(jumlah) ;
            String tabelstok ="UPDATE tstokbarang SET Stok_Akhir='" +stokakhir+ "',Stok_Masuk='"+stokmasuk+ "',Stok_Keluar='"+jumlah+ "' WHERE Kode_Item='" + kodeitem + "'";
            String tabelitem ="UPDATE tmaterial SET Stok='" +stokakhir+ "' WHERE Kode_Item='" + kodeitem + "'";
            
            try{
                st= conn.createStatement();
                st.executeUpdate(insert);
                st.executeUpdate(tabelstok);                
                st.executeUpdate(tabelitem);
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
    
    public void cetaktransaksi(){
         String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("notrans", jTransaksi.getText());
        try {

            reportSource = PathReport + "BuktiPenjualan.jrxml";
            reportDest = PathReport + "BuktiPenjualan.html";

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
            String notransaksi = jTransaksi.getText();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Membatalkan Transaksi = '" + notransaksi +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttemptransaksijual WHERE No_Transaksi = ?";
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
        jBhapus = new javax.swing.JButton();
        jBsimpan = new javax.swing.JButton();
        jBkeluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTtransjual = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jBedit = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTransaksi = new javax.swing.JTextField();
        jTnama = new javax.swing.JTextField();
        jTkonsumen = new javax.swing.JTextField();
        jTuser = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBkodeitem = new javax.swing.JButton();
        jTnamaitem = new javax.swing.JTextField();
        jTjml = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jBjumlah = new javax.swing.JButton();
        jBkonsumen = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTnopesan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTsubtotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTsisabayar = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jDtanggal = new com.toedter.calendar.JDateChooser();
        jTkodeitem = new javax.swing.JTextField();
        jBnopesan = new javax.swing.JButton();
        jTtglpesan = new com.toedter.calendar.JDateChooser();
        jTstokitem = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTketerangan = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTjmlbayar = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jmlData = new javax.swing.JLabel();
        jBtambah = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jBbatal = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTjmltrans = new javax.swing.JTextField();
        jBcari = new javax.swing.JButton();
        jTDP = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Transaksi Penjualan Material");

        jBhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bin_Full.png"))); // NOI18N
        jBhapus.setText("Hapus");
        jBhapus.setEnabled(false);
        jBhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhapusActionPerformed(evt);
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

        jBkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        jBkeluar.setText("Keluar");
        jBkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkeluarActionPerformed(evt);
            }
        });

        jTtransjual.setAutoCreateRowSorter(true);
        jTtransjual.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTtransjual.setModel(new javax.swing.table.DefaultTableModel(
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
        jTtransjual.setFocusable(false);
        jTtransjual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTtransjualMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTtransjualMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTtransjual);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText(":");

        jBedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tools.png"))); // NOI18N
        jBedit.setText("Edit");
        jBedit.setEnabled(false);
        jBedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText(":");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Transaksi Penjualan Material", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("No Transaksi");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel7.setText("Konsumen");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText(":");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jTransaksi.setEnabled(false);
        jTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTransaksiKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTransaksiKeyTyped(evt);
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

        jBkodeitem.setText("jButton1");
        jBkodeitem.setEnabled(false);
        jBkodeitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkodeitemActionPerformed(evt);
            }
        });

        jTnamaitem.setEnabled(false);
        jTnamaitem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnamaitemKeyTyped(evt);
            }
        });

        jTjml.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTjml.setText("1");
        jTjml.setEnabled(false);
        jTjml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTjmlKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTjmlKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel16.setText("Jumlah Beli");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(":");

        jBjumlah.setText(">");
        jBjumlah.setEnabled(false);
        jBjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBjumlahActionPerformed(evt);
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

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("No Pesan");

        jTnopesan.setEnabled(false);
        jTnopesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnopesanKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnopesanKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText(":");

        jTsubtotal.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jTsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTsubtotal.setText("0");
        jTsubtotal.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("TOTAL");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText(":");

        jTsisabayar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTsisabayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTsisabayar.setText("0");
        jTsisabayar.setEnabled(false);

        jLabel27.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel27.setText("SISA");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setText(":");

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

        jBnopesan.setText("jButton1");
        jBnopesan.setEnabled(false);
        jBnopesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnopesanActionPerformed(evt);
            }
        });

        jTtglpesan.setDateFormatString("dd-MM-yyyy");
        jTtglpesan.setEnabled(false);

        jTstokitem.setEnabled(false);
        jTstokitem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTstokitemKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel4.setText("<< Stok Yang Tersedia");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jTkonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jTnopesan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBkonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBnopesan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTtglpesan, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTnama)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(jTransaksi))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addGap(44, 44, 44)
                            .addComponent(jLabel25)
                            .addGap(18, 18, 18)
                            .addComponent(jDtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addGap(23, 23, 23)
                                .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel26))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel27)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(49, 49, 49)
                                            .addComponent(jLabel28))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTsisabayar, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jTstokitem, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTjml, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel28)
                                        .addComponent(jLabel27))
                                    .addComponent(jTsisabayar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel15)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(jLabel24))
                            .addComponent(jDtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel14)
                                .addComponent(jTnopesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBnopesan))
                            .addComponent(jTtglpesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jTkonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBkonsumen)
                            .addComponent(jTnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(jBkodeitem)
                        .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTstokitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jTjml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBjumlah)
                        .addComponent(jLabel16))))
        );

        jTketerangan.setEnabled(false);

        jLabel33.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel33.setText("Jumlah Bayar");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel20.setText("Jumlah Pesan");

        jTjmlbayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTjmlbayar.setText("0");
        jTjmlbayar.setEnabled(false);
        jTjmlbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTjmlbayarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTjmlbayarKeyTyped(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel34.setText(":");

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jBtambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jBtambah.setText("Tambah");
        jBtambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtambahActionPerformed(evt);
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

        jLabel18.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel18.setText("Keterangan");

        jTjmltrans.setEnabled(false);

        jBcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.gif"))); // NOI18N
        jBcari.setText("Cari");
        jBcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcariActionPerformed(evt);
            }
        });

        jTDP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTDP.setText("0");
        jTDP.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel22.setText("DP");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setText(":");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBedit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBhapus)
                        .addGap(116, 116, 116)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jTjmltrans, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDP, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34)
                        .addGap(23, 23, 23)
                        .addComponent(jTjmlbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(jBsimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBbatal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBcari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBkeluar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jmlData)))))
                .addContainerGap())
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBedit)
                    .addComponent(jBhapus)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jTjmltrans, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jTDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jTjmlbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtambah)
                    .addComponent(jBsimpan)
                    .addComponent(jBbatal)
                    .addComponent(jBcari)
                    .addComponent(jBkeluar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
            jmldatatable();
            jBhapus.setEnabled(false);
            //cekkode();
            tampilDataKeJTable();

            hitungsubtotal();
            hitungjum();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
            tampilDataKeJTable();
            //tampilDataKeJTable();
        }
    }//GEN-LAST:event_jBhapusActionPerformed

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:

        String notrans = jTransaksi.getText();
        String tglpesan = jDtanggal.getDateFormatString();
        //String idsupp = jTkonsumen.getText();

        String jmlbayar = jTjmlbayar.getText();
        String subtotal = jTsubtotal.getText();
        String sisabayar = jTsisabayar.getText();
        int x = Integer.parseInt(jmlbayar);
        int y = Integer.parseInt(subtotal);
        int z = Integer.parseInt(jTDP.getText());
        int table = jTtransjual.getRowCount();
        int hasil = x+z;
        if(hasil<y||sisabayar.equalsIgnoreCase("")||notrans.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")||jmlbayar.equalsIgnoreCase("")||table<1||subtotal.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Data Belum Lengkap");

        }else{
            cekstatustrans();

            if(statustrans.equals("1")){
                JOptionPane.showMessageDialog(this,"Data Sudah Masuk Transaksi Pembelian");
                jBsimpan.setEnabled(false);

            }else{
                updatestok();
                simpanketransaksijual();
                jTDP.setText("0");
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

        }
    }//GEN-LAST:event_jBsimpanActionPerformed

    private void jBkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBkeluarActionPerformed

    private void jTtransjualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtransjualMouseClicked
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();

        if (evt.getClickCount() == 2) {
            Point pnt = evt.getPoint();
            int row = jTtransjual.rowAtPoint(pnt);
            //do something

            //fDB.fAB = this;
            int tabel = jTtransjual.getSelectedRow();

            fDB.no_transaksi = jTtransjual.getValueAt(tabel, 1).toString();
            fDB.kodeitem = jTtransjual.getValueAt(tabel, 7).toString();
            fDB.namaitem = jTtransjual.getValueAt(tabel, 8).toString();
            fDB.jumlah = jTtransjual.getValueAt(tabel, 9).toString();
            fDB.harga = jTtransjual.getValueAt(tabel, 10).toString();
            fDB.total= jTtransjual.getValueAt(tabel, 11).toString();

            fDB.jsimpan.setText("Edit Jual");
            fDB.tampildata();
            fDB.setVisible(true);

        }
    }//GEN-LAST:event_jTtransjualMouseClicked

    private void jTtransjualMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtransjualMouseEntered
        // TODO add your handling code here:

        if(jDtanggal.isEnabled()==false){
            tampilDataKeJTable();
            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jBedit.setEnabled(false);
            jBhapus.setEnabled(false);

        }else{
            getDatatransaksi();
            tampilDataKeJTable();
            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jBedit.setEnabled(false);
            jBhapus.setEnabled(false);
        }
    }//GEN-LAST:event_jTtransjualMouseEntered

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();

        //fDB.fAB = this;
        int tabel = jTtransjual.getSelectedRow();

        fDB.no_transaksi = jTtransjual.getValueAt(tabel, 1).toString();
        fDB.kodeitem = jTtransjual.getValueAt(tabel, 7).toString();
        fDB.namaitem = jTtransjual.getValueAt(tabel, 8).toString();
        fDB.jumlah = jTtransjual.getValueAt(tabel, 9).toString();
        fDB.harga = jTtransjual.getValueAt(tabel, 10).toString();
        fDB.total= jTtransjual.getValueAt(tabel, 11).toString();

        fDB.jsimpan.setText("Edit Jual");
        fDB.tampildata();
        fDB.setVisible(true);
    }//GEN-LAST:event_jBeditActionPerformed

    private void jTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTransaksiKeyPressed
        // TODO add your handling code here:
      
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
              
            cekstatustrans();
            if(nopesandb.equals(jTransaksi.getText())){
                jBsimpan.setEnabled(false);
                  jTDP.setText("0");
            }
            jTDP.setText("0");
            setJTable();
            tampilDataKeJTable();
            getNamakonsumen();
            hitungsubtotal();
            hitungjum();
            jmldatatable();
            jDtanggal.setEnabled(false);
            jTkonsumen.setEnabled(false);
            jTnopesan.setEnabled(false);
             jBsimpan.setEnabled(true);
             cekstatuspesan();
             jTDP.setText(DP);

        }
    }//GEN-LAST:event_jTransaksiKeyPressed

    private void jTransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTransaksiKeyTyped
        // TODO add your handling code here:
        String string = jTransaksi.getText();
        String upper = string.toUpperCase();
        jTransaksi.setText(upper);
    }//GEN-LAST:event_jTransaksiKeyTyped

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
    }//GEN-LAST:event_jTkonsumenKeyTyped

    private void jTuserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTuserKeyTyped
        // TODO add your handling code here:
        String string = jTuser.getText();
        String upper = string.toUpperCase();
        jTuser.setText(upper);
    }//GEN-LAST:event_jTuserKeyTyped

    private void jBkodeitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkodeitemActionPerformed
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
                            jTjml.setText("0");
                            hargajual = Integer.parseInt(hjual);
                            
                            cekkode();
                            tampilDataKeJTable();
                            //getDatatransaksi();
                            jmldatatable();
                            hitungsubtotal();
                            hitungjum();
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
    }//GEN-LAST:event_jBkodeitemActionPerformed

    private void jTnamaitemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnamaitemKeyTyped
        // TODO add your handling code here:
        String string = jTnamaitem.getText();
        String upper = string.toUpperCase();
        jTnamaitem.setText(upper);
    }//GEN-LAST:event_jTnamaitemKeyTyped

    private void jBjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBjumlahActionPerformed
        // TODO add your handling code here:

        String notrans = jTransaksi.getText();
        String tglpesan = jDtanggal.getDateFormatString();
        // String idsupp = jTkonsumen.getText();
        String kodeitem = jTkodeitem.getText();
        
        int x = Integer.valueOf(jTjml.getText());
        int y = Integer.valueOf(jTstokitem.getText());

        if(notrans.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong, Mohon Cek Kembali");
            // setJTable();
        }
        if(x > y){
            if(kodeitem.equalsIgnoreCase("MTRL-00000")){
                cekkode();
                tampilDataKeJTable();
                //getDatatransaksi();
                jmldatatable();
                hitungsubtotal();
                hitungjum();
            }
            else{
                JOptionPane.showMessageDialog(this,"Maaf Permintaan Barang Melebihi Stok Yang Tersedia");
                // setJTable();
            }
        }
        else{
            cekkode();
            tampilDataKeJTable();
            //getDatatransaksi();
            jmldatatable();
            hitungsubtotal();
            hitungjum();
        }
        jTkonsumen.setEnabled(false);
        jDtanggal.setEnabled(false);
        jTnopesan.setEnabled(false);
        jTransaksi.setEnabled(false);
    }//GEN-LAST:event_jBjumlahActionPerformed

    private void jBkonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkonsumenActionPerformed
        // TODO add your handling code here:
        MasterKonsumen dp=new MasterKonsumen();
        dp.setTitle("Data Konsumen");
        dp.jBedit.setVisible(false);
        dp.jBhapus.setVisible(false);
        dp.jBtambah.setVisible(false);
        dp.jButton5.setVisible(false);
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        
        panelPop.add(dp).setVisible(true);
        
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Konsumen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getDatas(dp.getTitle());
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

    private void jTnopesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnopesanKeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            jTkonsumen.setEnabled(false);
            String cek = jTnopesan.getText();
            if (cek.equals("")){

            }else{

                jTransaksi.setEnabled(false);
                jTnopesan.setEnabled(false);
                String string = jTnopesan.getText();
                String upper = string.toUpperCase();
                jTnopesan.setText(upper);
                cekstatuspesan();

                if(statuspesan.equals("1")||statuspesan.equals(null)){

                    JOptionPane.showMessageDialog(this,"Data Sudah Masuk Transaksi ");
                    jTnopesan.setText("");
                    jTnopesan.setEnabled(true);

                }else{

                    tampilDataKeJTable();
                    cekkodeganda();
                    getNamakonsumen();
                    simpanketemptransaksi();
                    hitungsubtotal();
                    hitungjum();
                    jmldatatable();

                }
            }
        }

    }//GEN-LAST:event_jTnopesanKeyPressed

    private void jTnopesanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnopesanKeyTyped
        // TODO add your handling code here:
        String string = jTnopesan.getText();
        String upper = string.toUpperCase();
        jTnopesan.setText(upper);
    }//GEN-LAST:event_jTnopesanKeyTyped

    private void jTkodeitemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkodeitemKeyPressed
        // TODO add your handling code here:
        jTnamaitem.setText("");
        jTstokitem.setText("");
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){

            ambildata();
            String string = jTkodeitem.getText();
            String upper = string.toUpperCase();
            jTkodeitem.setText(upper);
        }
    }//GEN-LAST:event_jTkodeitemKeyPressed

    private void jTkodeitemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkodeitemKeyTyped
        // TODO add your handling code here:
        String string = jTkodeitem.getText();
        String upper = string.toUpperCase();
        jTkodeitem.setText(upper);
    }//GEN-LAST:event_jTkodeitemKeyTyped

    private void jBnopesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnopesanActionPerformed
        // TODO add your handling code here:
        DaftarPesananPenjualan dp=new DaftarPesananPenjualan();
        dp.setTitle("");
        dp.jButton5.setVisible(false);
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        dp.s = "0";
        dp.setJTable();
        panelPop.add(dp).setVisible(true);
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Nomor Pesanan", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getDataitem(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }
     
    }                                         
private void getDataitem(String kode){
                jTnopesan.setText(kode);
                jTransaksi.setEnabled(false);
                jTnopesan.setEnabled(false);
                String string = jTnopesan.getText();
                String upper = string.toUpperCase();
                jTnopesan.setText(upper);
                cekstatuspesan();
                jTDP.setText(DP);
                if(statuspesan.equals("1")||statuspesan.equals(null)){

                    JOptionPane.showMessageDialog(this,"Data Sudah Masuk Transaksi ");
                    jTnopesan.setText("");
                    jTnopesan.setEnabled(true);

                }else{

  try {
           // hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemptransaksijual where No_Transaksi ='"+kode+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
            SimpleDateFormat sdf = new SimpleDateFormat(tampilan); 
            String notrans,nopes,idkonsumen,nama,user,kodeitem,namaitem,jumlah,harga,total,retur,keterangan;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       notrans=rs.getString("No_Transaksi");
                       nopes=rs.getString("No_Pesanan");
                       Date tglpesan=rs.getDate("Tgl_Pesan");
                       idkonsumen=rs.getString("ID_Konsumen");
                       nama=rs.getString("Nama");
                       user=rs.getString("User");
                       keterangan=rs.getString("Keterangan");
                           
                        String tgl = sdf.format(tglpesan);
                        jTtglpesan.setDate(tglpesan);
                        jTnopesan.setText(nopes);
                        
                        jTuser.setText(user);
                        jTkonsumen.setText(idkonsumen);
                        //jTnama.setText(nama);
                        jTketerangan.setText(keterangan);
         
                       
         Object Data[]={no,notrans,nopes,tgl,idkonsumen,nama,user,keterangan};
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


                    cekkodeganda();
                    getNamakonsumen();
                    simpanketemptransaksi();
                    hitungsubtotal();
                    hitungjum();
                    jmldatatable();

                }  


    }//GEN-LAST:event_jBnopesanActionPerformed

    private void jTjmlbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjmlbayarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            int x;
            if(jTjmlbayar.getText().equals("")){
                x=0;
        } else{
            x = Integer.parseInt(jTjmlbayar.getText());
        }
        
        int idp = Integer.parseInt(jTDP.getText());
        int jml = idp+x;
        int y = Integer.parseInt(jTsubtotal.getText());
        if(jml<y){
            hitungsisabayar();
            JOptionPane.showMessageDialog(this, "Jumlah Bayar Kurang");
            jTjmlbayar.setText("0");
        } else{    
            try{
                hitungsisabayar();
            } catch (Exception e) {

                JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Edit");
            }
        }
        }
    }//GEN-LAST:event_jTjmlbayarKeyPressed

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
        jTDP.setText("0");
    }//GEN-LAST:event_jBbatalActionPerformed

    private void jBcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcariActionPerformed
        // TODO add your handling code here:
        DaftarTransaksiPenjualan dtb = new DaftarTransaksiPenjualan();
        this.getParent().add(dtb);
        dtb.setVisible(true);
    }//GEN-LAST:event_jBcariActionPerformed

    private void jTjmlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjmlKeyTyped
        // TODO add your handling code here:
                       char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_jTjmlKeyTyped

    private void jTjmlbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjmlbayarKeyTyped
        // TODO add your handling code here:
                       char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
     // hitungsisabayar();
    }//GEN-LAST:event_jTjmlbayarKeyTyped

    private void jTjmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjmlKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        { 
            String notrans = jTransaksi.getText();
            String tglpesan = jDtanggal.getDateFormatString();
            // String idsupp = jTkonsumen.getText();
            String kodeitem = jTkodeitem.getText();

            int x = Integer.valueOf(jTjml.getText());
            int y = Integer.valueOf(jTstokitem.getText());
            
        if(notrans.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            // setJTable();
        }
        if(x > y){
            JOptionPane.showMessageDialog(this,"Maaf Permintaan Barang Melebihi Stok Yang Tersedia");
            // setJTable();
        }
        else{

            cekkode();
            tampilDataKeJTable();
            //getDatatransaksi();
            jmldatatable();
            hitungsubtotal();
            hitungjum();

        }
        jTkonsumen.setEnabled(false);
        jDtanggal.setEnabled(false);
        jTnopesan.setEnabled(false);
        jTransaksi.setEnabled(false);
        }
    }//GEN-LAST:event_jTjmlKeyPressed

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
    private javax.swing.JButton jBjumlah;
    private javax.swing.JButton jBkeluar;
    private javax.swing.JButton jBkodeitem;
    private javax.swing.JButton jBkonsumen;
    private javax.swing.JButton jBnopesan;
    private javax.swing.JButton jBsimpan;
    private javax.swing.JButton jBtambah;
    private com.toedter.calendar.JDateChooser jDtanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTDP;
    private javax.swing.JTextField jTjml;
    private javax.swing.JTextField jTjmlbayar;
    private javax.swing.JTextField jTjmltrans;
    private javax.swing.JTextField jTketerangan;
    private javax.swing.JTextField jTkodeitem;
    private javax.swing.JTextField jTkonsumen;
    private javax.swing.JTextField jTnama;
    private javax.swing.JTextField jTnamaitem;
    private javax.swing.JTextField jTnopesan;
    private javax.swing.JTextField jTransaksi;
    private javax.swing.JTextField jTsisabayar;
    private javax.swing.JTextField jTstokitem;
    private javax.swing.JTextField jTsubtotal;
    private com.toedter.calendar.JDateChooser jTtglpesan;
    private javax.swing.JTable jTtransjual;
    public javax.swing.JTextField jTuser;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
