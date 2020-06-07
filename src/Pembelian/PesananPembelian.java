/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pembelian;


import MasterData.MasterMaterial;
import MasterData.MasterSupplier;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import besiusahamekar.MenuUtama;
import besiusahamekar.koneksidb;
import static besiusahamekar.koneksidb.PathReport;

/**
 *
 * @author Banuajie
 */
public class PesananPembelian extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;

String formattedNumber;
String number,harga,stotal;
int hargapokok;
String tampilan = "yyyy-MM-dd";
public String idsupplier,nopes,aktif="",namauser;
String kodebarang ,nopesandb="";
 int val = 0 ;
MenuUtama  mu = new MenuUtama();

    public String getidsupplier() {
        return idsupplier;
    }
    public String getnopes() {
        return nopes;
    }

    /**
     * Creates new form PesananPembelian
     */
    public PesananPembelian() {
        initComponents();
        isidata();
    }

    private void setJTable() {
    String [] JudulKolom={"No","No Pesanan","Tgl Pesan","Tgl Kirim","User","ID Supplier","Kode Material","Nama Item","Jumlah","Harga","Total","Sub Total","Keterangan"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTpesanbeli.setModel(tabModel);
    jTpesanbeli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTpesanbeli.getColumnModel().getColumn(0).setPreferredWidth(35);
    jTpesanbeli.getColumnModel().getColumn(1).setMinWidth(0);
    jTpesanbeli.getColumnModel().getColumn(1).setMaxWidth(0);
    jTpesanbeli.getColumnModel().getColumn(1).setWidth(0);
    jTpesanbeli.getColumnModel().getColumn(2).setMinWidth(0);
    jTpesanbeli.getColumnModel().getColumn(2).setMaxWidth(0);
    jTpesanbeli.getColumnModel().getColumn(2).setWidth(0);
    jTpesanbeli.getColumnModel().getColumn(3).setMinWidth(0);
    jTpesanbeli.getColumnModel().getColumn(3).setMaxWidth(0);
    jTpesanbeli.getColumnModel().getColumn(3).setWidth(0);
    jTpesanbeli.getColumnModel().getColumn(4).setMinWidth(0);
    jTpesanbeli.getColumnModel().getColumn(4).setMaxWidth(0);
    jTpesanbeli.getColumnModel().getColumn(4).setWidth(0);
    jTpesanbeli.getColumnModel().getColumn(5).setMinWidth(0);
    jTpesanbeli.getColumnModel().getColumn(5).setMaxWidth(0);
    jTpesanbeli.getColumnModel().getColumn(5).setWidth(0);
    jTpesanbeli.getColumnModel().getColumn(6).setPreferredWidth(180);
    jTpesanbeli.getColumnModel().getColumn(7).setPreferredWidth(240);
    jTpesanbeli.getColumnModel().getColumn(8).setPreferredWidth(60);
    jTpesanbeli.getColumnModel().getColumn(9).setPreferredWidth(150);
    jTpesanbeli.getColumnModel().getColumn(10).setPreferredWidth(150);
    jTpesanbeli.getColumnModel().getColumn(11).setMinWidth(0);
    jTpesanbeli.getColumnModel().getColumn(11).setMaxWidth(0);
    jTpesanbeli.getColumnModel().getColumn(11).setWidth(0);
    jTpesanbeli.getColumnModel().getColumn(12).setMinWidth(0);
    jTpesanbeli.getColumnModel().getColumn(12).setMaxWidth(0);
    jTpesanbeli.getColumnModel().getColumn(12).setWidth(0);
        
   //jTpesanbeli.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getPercentRenderer());
   //jTpesanbeli.getColumnModel().getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());
   //jTpesanbeli.getColumnModel().getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
       
    //getDatapesanan();
    } 
    
   
   public void isidata(){
        notransaksi();
        //getDatasupplier();
        //getNamaSupplier();
        setJTable();
        
        jmldatatable();
        hitungsubtotal();
        hitungjum();
   }
    private void getDatapesanan() {
        // import java.sql.connection
        String nopesan = jTnopesan.getText();
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemppesananbeli WHERE No_Pesanan ='"+nopesan+"'";
            ResultSet rs=st.executeQuery(sql);

                // Menampilkan ke JTable  melalui tabModel
                     String Nopesanan,TglKirim,User,IDSupplier,KodeItem,NamaItem,Jumlah,Harga,Total,subtotal,keterangan;
                     int no=0;
                        while(rs.next()){
                        no=no+1;
                       Nopesanan=rs.getString("No_Pesanan");
                       Date TglPesan=rs.getDate("Tgl_Pesan");
                       TglKirim=rs.getString("Tgl_Kirim");
                       User=rs.getString("User");
                       IDSupplier=rs.getString("ID_Supplier");
                       KodeItem=rs.getString("Kode_Item");
                       NamaItem=rs.getString("Nama");
                       Jumlah=rs.getString("Jumlah");
                       Harga=rs.getString("Harga");
                       Total=rs.getString("Total");
                       subtotal=rs.getString("Sub_Total");
                       keterangan=rs.getString("Keterangan");
                       
         Object Data[]={no,Nopesanan,TglPesan,TglKirim,User,IDSupplier,KodeItem,NamaItem,Jumlah,Harga,Total,subtotal,keterangan};
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
    String nopes,user,idsupplier,kodeitem,nama,jumlah,harga,total,subtotal,keterangan;
    SimpleDateFormat sdf = new SimpleDateFormat(tampilan);                 
    try {
            hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemppesananbeli WHERE No_Pesanan ='"+nopesan+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       nopes=rs.getString("No_Pesanan");
                       Date tglpesan=rs.getDate("Tgl_Pesan");
                       Date tglkirim=rs.getDate("Tgl_Kirim");
                       user=rs.getString("User");
                       idsupplier=rs.getString("ID_Supplier");
                       kodeitem=rs.getString("Kode_Item");
                       nama=rs.getString("Nama");
                       jumlah=rs.getString("Jumlah");
                       harga=rs.getString("Harga");
                       total=rs.getString("Total");
                       subtotal=rs.getString("Sub_Total");
                       keterangan=rs.getString("Keterangan");
        
                       jDtglkirim.setDate(tglkirim);
                       jDtglpesan.setDate(tglpesan);
                       jTuser.setText(user);
                       jCsupplier.setText(idsupplier);
                       jTketerangan.setText(keterangan);
         
                       
         Object Data[]={no,nopes,tglpesan,tglkirim,user,idsupplier,kodeitem,nama,jumlah,harga,total,subtotal,keterangan};
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
        String sql= "select right(No_Pesanan,4) as kd from ttemppesananbeli where No_Pesanan Like '%" +time+ "%' order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTnopesan.setText("NOPB"+time+"000"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTnopesan.setText("NOPB"+time+"00"+Integer.toString(kode));   
                else if(kode>=1000&&kode<=9999) 
                jTnopesan.setText("NOPB"+time+"0"+Integer.toString(kode));
                else
                jTnopesan.setText("NOPB"+time+"0000"+Integer.toString(kode));

            }else{

                int kode = 1;

                jTnopesan.setText("NOPB"+time+"0000"+Integer.toString(kode));

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
    
   private void getDatasupplier() {
        // import java.sql.connection
            String id = jCsupplier.getText();
           try{
                       
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tsupplier";
            ResultSet rs=st.executeQuery(sql);
            
            
           
                        while(rs.next()){
                        String idsupplier = rs.getString("ID_Supplier"); 
                        jCsupplier.setText(idsupplier);
                        
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
    
   private void getNamaSupplier() {
        // import java.sql.connection
       String id = jCsupplier.getText();
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tsupplier where ID_Supplier='"+id+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama");
                       jTnamasupplier.setText(nama);
                       
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
   
   public void tampildata(){
       jCsupplier.setEnabled(true);
       jCsupplier.setText(idsupplier);
       
       getNamaSupplier();
   }
  public void tampildatapesanan(){
       
       jTnopesan.setText(nopes);
      
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
                       String hpokok =rs.getString("Harga_Pokok");
                      
                       jTnamaitem.setText(nama);
                       hargapokok = Integer.parseInt(hpokok);
                       
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
   
   public void simpankepesanbeli(){
      hitungtotal();
        
       try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into tpesananbeli values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);

                SimpleDateFormat format = new SimpleDateFormat(tampilan);
                String tglpesan = String.valueOf(format.format(jDtglpesan.getDate()));
                String tglkirim = String.valueOf(format.format(jDtglkirim.getDate()));    
                
                st.setString(1, jTnopesan.getText());
                st.setString(2, (String)tglpesan);
                st.setString(3, (String)tglkirim);
                st.setString(4, jCsupplier.getText());
                st.setString(5, jTnamasupplier.getText());
                st.setString(6, jTjmlpesan.getText());
                st.setString(7, jTsubtotal.getText());
                st.setString(8, jTketerangan.getText());
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
   
    public void simpanData(){
//Connection conn;
        hitungtotal();
        
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttemppesananbeli values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
                
                SimpleDateFormat format = new SimpleDateFormat(tampilan);
                String tglpesan = String.valueOf(format.format(jDtglpesan.getDate()));
                String tglkirim = String.valueOf(format.format(jDtglkirim.getDate()));
                
                st.setString(1, jTnopesan.getText());
                st.setString(2, (String)tglpesan);
                st.setString(3, (String)tglkirim);
                st.setString(4, jTuser.getText());
                st.setString(5, jCsupplier.getText());
                st.setString(6, jTkodeitem.getText());
                st.setString(7, jTnamaitem.getText());
                st.setString(8, jTjumlah.getText());
                st.setString(9, harga);
                st.setString(10, stotal);
                st.setString(11, "0");
                st.setString(12, jTketerangan.getText());
                
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
   

   public void jmldatatable(){
         int rows = jTpesanbeli.getRowCount();
         String jmls = String.valueOf(rows);
         jmlData.setText(jmls);
         
             
   } 
    
public void hitungtotal(){
            String sjml = jTjumlah.getText();
            int jml = Integer.parseInt(sjml);
            harga = String.valueOf(hargapokok);
            int total = hargapokok*jml;
            stotal = String.valueOf(total);
}
   public void hitungsubtotal(){
    int jumlahBaris = jTpesanbeli.getRowCount();
    int totalBiaya = 0;
    int jumlah, harga;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTpesanbeli.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 8).toString());
        harga = Integer.parseInt(tabelModel.getValueAt(i, 9).toString());
        total = jumlah*harga;
        totalBiaya = totalBiaya + total;
        
    }
    jTsubtotal.setText(String.valueOf(totalBiaya));
   }
   
    public void hitungjum(){
    int jumlahBaris = jTpesanbeli.getRowCount();
    
    int jumlah;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTpesanbeli.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 8).toString());
        
        total = total+jumlah;
    }
    
    jTjmlpesan.setText(String.valueOf(total));
   }
    
    public void cekkode(){
    int jumlahBaris = jTpesanbeli.getRowCount();
    String kodeitem = jTkodeitem.getText();
   
    String kode=null;
    TableModel tabelModel;
    
    tabelModel = jTpesanbeli.getModel();
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
    val=0; 
    
   }
    
    
     public void hapus_Data() {
    // Konfirmasi sebelum melakukan penghapusan data
    int row = jTpesanbeli.getSelectedRow();
    kodebarang = tabModel.getValueAt(row, 6).toString();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus = '" + kodebarang +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttemppesananbeli WHERE Kode_Item = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, kodebarang);
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
     public void hariini(){
           DateFormat dateFormat = new SimpleDateFormat(tampilan);
	   //get current date time with Date()
	   Date date = new Date();
           jDtglpesan.setDate(new Date());
     }
    
     public void setenable(){
         
                jCsupplier.setEnabled(true);
                jBsupplier.setEnabled(true);
                        
                                       // jBcetak.setEnabled(true);
                                        jTpesanbeli.setEnabled(true);
                                        jBtambah.setEnabled(false);
                                        jBsimpan.setEnabled(true);
                                        jBbatal.setEnabled(true);
                                        jTketerangan.setEnabled(true);
     }
     
          public void setdisable(){
         notransaksi();
         jDtglpesan.setDate(null);
         jDtglkirim.setDate(null);
         
         jBsupplier.setEnabled(false);
         jTjumlah.setEnabled(false);
         jTsubtotal.setEnabled(false);
         
         jTnopesan.setEnabled(false);
         jDtglpesan.setEnabled(false);
         jDtglkirim.setEnabled(false);
                jCsupplier.setEnabled(false);
                jBsupplier.setEnabled(false);
                        jTkodeitem.setEnabled(false);
                        jBkodeitem.setEnabled(false);
                                jBjumlah.setEnabled(false);
                                jBcari.setEnabled(false);
                                        jTpesanbeli.setEnabled(false);
                                        jBtambah.setEnabled(true);
                                        jBsimpan.setEnabled(false);
                                        jBbatal.setEnabled(false);
                                        jTketerangan.setEnabled(false);
     }
          
          public void kosongheader(){
              jTnamasupplier.setText("");
              jCsupplier.setText("");
              
                      jTkodeitem.setText("");
          }
          
    public void cetakpesanan(){
         String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("nopesan", jTnopesan.getText());
        try {

            reportSource = PathReport + "BuktiPesananPembelian(PO).jrxml";
            reportDest = PathReport + "BuktiPesananPembelian(PO).html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }      
    
    private void cekstatuspesan() {
              String nopes = jTnopesan.getText();
             
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String pesan="Select No_Pesanan from tpesananbeli where No_Pesanan ='"+nopes+"'";
            ResultSet rs=st.executeQuery(pesan);
                
            while(rs.next()){
            String nopesan = rs.getString(1);
            nopesandb = nopesan;
        }}catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
        }catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);}    

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
        String sql = "DELETE FROM ttemppesananbeli WHERE No_Pesanan = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, notransaksi);
        int rs=st.executeUpdate();
       }catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTpesanbeli = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jBbatal = new javax.swing.JButton();
        jBhapus = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTsubtotal = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jBsupplier = new javax.swing.JButton();
        jTnopesan = new javax.swing.JTextField();
        jTuser = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTnamasupplier = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBkodeitem = new javax.swing.JButton();
        jTnamaitem = new javax.swing.JTextField();
        jTjumlah = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jBjumlah = new javax.swing.JButton();
        jTkodeitem = new javax.swing.JTextField();
        jDtglpesan = new com.toedter.calendar.JDateChooser();
        jDtglkirim = new com.toedter.calendar.JDateChooser();
        jCsupplier = new javax.swing.JTextField();
        jBsimpan = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jBedit = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTjmlpesan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jmlData = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jBtambah = new javax.swing.JButton();
        jTketerangan = new javax.swing.JTextField();
        jBcari = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Order Pembelian");
        setToolTipText("");

        jTpesanbeli.setAutoCreateRowSorter(true);
        jTpesanbeli.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTpesanbeli.setModel(new javax.swing.table.DefaultTableModel(
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
        jTpesanbeli.setFocusable(false);
        jTpesanbeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTpesanbeliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTpesanbeliMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTpesanbeli);

        jLabel23.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel23.setText("Sub-Total");

        jBbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Undo.png"))); // NOI18N
        jBbatal.setText("Batal");
        jBbatal.setEnabled(false);
        jBbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbatalActionPerformed(evt);
            }
        });

        jBhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bin_Full.png"))); // NOI18N
        jBhapus.setText("Hapus");
        jBhapus.setEnabled(false);
        jBhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhapusActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText(":");

        jTsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Order Pembelian", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("No Pesan");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("Supplier");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText(":");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel7.setText("Tgl Pesan");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText(":");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel8.setText("Tgl Kirim ");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText(":");

        jBsupplier.setText("jButton1");
        jBsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsupplierActionPerformed(evt);
            }
        });

        jTnopesan.setEnabled(false);
        jTnopesan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTnopesanFocusGained(evt);
            }
        });
        jTnopesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnopesanKeyPressed(evt);
            }
        });

        jTuser.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel12.setText("User");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText(":");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel14.setText("Nama Supplier");

        jTnamasupplier.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText(":");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText(":");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel3.setText("Kode Material");

        jBkodeitem.setText("jButton1");
        jBkodeitem.setEnabled(false);
        jBkodeitem.setNextFocusableComponent(jTjumlah);
        jBkodeitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkodeitemActionPerformed(evt);
            }
        });

        jTnamaitem.setEnabled(false);

        jTjumlah.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTjumlah.setText("1");
        jTjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTjumlahKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTjumlahKeyTyped(evt);
            }
        });

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

        jTkodeitem.setEnabled(false);
        jTkodeitem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTkodeitemKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTkodeitemKeyTyped(evt);
            }
        });

        jDtglpesan.setDateFormatString("dd-MM-yyyy");
        jDtglpesan.setEnabled(false);

        jDtglkirim.setDateFormatString("dd-MM-yyyy");
        jDtglkirim.setEnabled(false);

        jCsupplier.setEnabled(false);
        jCsupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCsupplierFocusGained(evt);
            }
        });
        jCsupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCsupplierKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(34, 34, 34)
                            .addComponent(jLabel11)
                            .addGap(167, 167, 167))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(34, 34, 34)
                                    .addComponent(jLabel10)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jDtglpesan, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addComponent(jTnopesan))
                            .addGap(12, 12, 12)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDtglkirim, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jBkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(jLabel16)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBjumlah))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTnamasupplier, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jCsupplier)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jBsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jTnopesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel7))
                            .addComponent(jDtglpesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jDtglkirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jBsupplier)
                            .addComponent(jCsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(jBkodeitem)
                        .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBjumlah))))
        );

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

        jTjmlpesan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTjmlpesan.setEnabled(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        jButton1.setText("Keluar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel18.setText("Keterangan");

        jLabel36.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel36.setText("Jumlah Data : ");

        jBtambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jBtambah.setText("Tambah");
        jBtambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtambahActionPerformed(evt);
            }
        });

        jTketerangan.setEnabled(false);

        jBcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.gif"))); // NOI18N
        jBcari.setText("Cari");
        jBcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcariActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBsimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBbatal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBcari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19))
                                    .addComponent(jBedit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jBhapus)
                                        .addGap(120, 120, 120)
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTjmlpesan, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel23)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTketerangan))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jmlData)
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jmlData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBedit)
                    .addComponent(jBhapus)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jTjmlpesan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtambah)
                    .addComponent(jBbatal)
                    .addComponent(jBcari)
                    .addComponent(jBsimpan)
                    .addComponent(jButton1))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTpesanbeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTpesanbeliMouseClicked
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();

        if (evt.getClickCount() == 2) {
            Point pnt = evt.getPoint();
            int row = jTpesanbeli.rowAtPoint(pnt);
            //do something

            //fDB.fAB = this;
            int tabel = jTpesanbeli.getSelectedRow();

            fDB.kodeitem = jTpesanbeli.getValueAt(tabel, 6).toString();
            fDB.namaitem = jTpesanbeli.getValueAt(tabel, 7).toString();
            fDB.jumlah = jTpesanbeli.getValueAt(tabel, 8).toString();
            fDB.harga = jTpesanbeli.getValueAt(tabel, 9).toString();
            fDB.total= jTpesanbeli.getValueAt(tabel, 10).toString();

            fDB.tampildata();
            fDB.setVisible(true);

        }
    }//GEN-LAST:event_jTpesanbeliMouseClicked

    private void jTpesanbeliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTpesanbeliMouseEntered
        // TODO add your handling code here:
        String nopesan = jTnopesan.getText();
        if(nopesan.equals("")){

        }else{
            setJTable();
            tampilDataKeJTable();
            hitungsubtotal();
            hitungjum();
            jBedit.setEnabled(false);
            jBhapus.setEnabled(false);
        }
    }//GEN-LAST:event_jTpesanbeliMouseEntered

    private void jBbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbatalActionPerformed
        // TODO add your handling code here:
        Transaksibatal();
        setdisable();
        kosongheader();
        isidata();
        
    }//GEN-LAST:event_jBbatalActionPerformed

    private void jBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
            jmldatatable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
            tampilDataKeJTable();
        }
        jBhapus.setEnabled(false);
    }//GEN-LAST:event_jBhapusActionPerformed

    private void jBsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsupplierActionPerformed
        // TODO add your handling code here:
        MasterSupplier dp=new MasterSupplier();
        dp.jBedit.setVisible(false);
        dp.jBhapus.setVisible(false);
        dp.jBtambah.setVisible(false);
        dp.jButton5.setVisible(false);
        dp.setTitle("");
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        
        panelPop.add(dp).setVisible(true);
        
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Supplier", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))
                getDatas(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }
        jTnopesan.setEnabled(true);
        jDtglpesan.setEnabled(true);
        jDtglkirim.setEnabled(true);
                jCsupplier.setEnabled(true);
                jBsupplier.setEnabled(true);
                        jTkodeitem.setEnabled(true);
                        jBkodeitem.setEnabled(true);
                                jBjumlah.setEnabled(true);
                                jBcari.setEnabled(true);
     
    }                                         
                                        
private void getDatas(String kode){
            
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tsupplier where ID_Supplier='"+kode+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama");
                       jCsupplier.setText(kode);
                       jTnamasupplier.setText(nama);
                       
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
       
    }//GEN-LAST:event_jBsupplierActionPerformed

    private void jTnopesanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnopesanFocusGained
        // TODO add your handling code here:
        setJTable();
        tampilDataKeJTable();
        hitungsubtotal();
        hitungjum();
    }//GEN-LAST:event_jTnopesanFocusGained

    private void jTnopesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnopesanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            
            cekstatuspesan();
            if(nopesandb.equals(jTnopesan.getText())){
                jBsimpan.setEnabled(false);
            }
            setJTable();
            tampilDataKeJTable();
            hitungsubtotal();
            hitungjum();

    }//GEN-LAST:event_jTnopesanKeyPressed
    }
    private void jBkodeitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkodeitemActionPerformed
        // TODO add your handling code here:
        MasterMaterial dp=new MasterMaterial();
        
        String kodesupplier = String.valueOf(jCsupplier.getText());
        String namasupplier = String.valueOf(jTnamasupplier.getText());
        
        dp.txtkodesupplier.setText(kodesupplier);
        dp.txtnamasupplier.setText(namasupplier);
        dp.carisupplier();
        dp.jmldatatable();
        
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
                       String hpokok =rs.getString("Harga_Pokok");
                       
                       jTkodeitem.setText(kode);
                       jTnamaitem.setText(nama);
                       hargapokok = Integer.parseInt(hpokok);
                       
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

    private void jBjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBjumlahActionPerformed
        // TODO add your handling code here:

        String nopesan = jTnopesan.getText();
        String tglpesan = jDtglpesan.getDateFormatString();
        String tglkirim = jDtglkirim.getDateFormatString();
        String idsupplier = jCsupplier.getText();
        String kodeitem = jTkodeitem.getText();

        if(tglkirim.equalsIgnoreCase("")||nopesan.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||idsupplier.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            setJTable();
        }else{
            cekkode();
            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jCsupplier.setEnabled(false);
        }

    }//GEN-LAST:event_jBjumlahActionPerformed

    private void jTkodeitemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkodeitemKeyPressed
        // TODO add your handling code here:
        jTnamaitem.setText("");
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){

            ambildata();
            String string = jTkodeitem.getText();
            String upper = string.toUpperCase();
            jTkodeitem.setText(upper);
        }

    }//GEN-LAST:event_jTkodeitemKeyPressed

    private void jTkodeitemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkodeitemKeyTyped
        // TODO add your handling code here:
        jTnamaitem.setText("");
        ambildata();

    }//GEN-LAST:event_jTkodeitemKeyTyped

    private void jCsupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCsupplierFocusGained
        // TODO add your handling code here:
        getNamaSupplier();
    }//GEN-LAST:event_jCsupplierFocusGained

    private void jCsupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCsupplierKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            getNamaSupplier();
            jCsupplier.setEnabled(false);
        }
    }//GEN-LAST:event_jCsupplierKeyPressed

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        // TODO add your handling code here:
        getNamaSupplier();

    }//GEN-LAST:event_jPanel1MouseEntered

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:
        String nopesan = jTnopesan.getText();
        String tglpesan = jDtglpesan.getDateFormatString();
        String tglkirim = jDtglkirim.getDateFormatString();
        String subtotal = jTsubtotal.getText();
        int table = jTpesanbeli.getRowCount();

        if(nopesan.equals("")||tglpesan.equals("")||tglkirim.equals("")||table<1||subtotal.equals("")){
            JOptionPane.showMessageDialog(this, "Data Belum Lengkap");

        }else{
            
        simpankepesanbeli();
           
        int ok = JOptionPane.showConfirmDialog(this,
        "Cetak Pesanan Ini ?", "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
        
        cetakpesanan();
        
    }
          setdisable();   
        }

    }//GEN-LAST:event_jBsimpanActionPerformed

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();
        //do something

        int tabel = jTpesanbeli.getSelectedRow();

        fDB.kodeitem = jTpesanbeli.getValueAt(tabel, 6).toString();
        fDB.namaitem = jTpesanbeli.getValueAt(tabel, 7).toString();
        fDB.jumlah = jTpesanbeli.getValueAt(tabel, 8).toString();
        fDB.harga = jTpesanbeli.getValueAt(tabel, 9).toString();
        fDB.total= jTpesanbeli.getValueAt(tabel, 10).toString();

        fDB.tampildata();
        fDB.setVisible(true);

    }//GEN-LAST:event_jBeditActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //String nopesan = jTnopesan.getText();
        //String tglpesan = jDtglpesan.getDateFormatString();
        //String tglkirim = jDtglkirim.getDateFormatString();
        //String namasupplier = jTnamasupplier.getText();
        //int table = jTpesanbeli.getRowCount();

        //  if(nopesan.equals("")||tglpesan.equals("")||tglkirim.equals("")||table<1){
            //    int ok = JOptionPane.showConfirmDialog(this,
                //       "Anda Akan Membatalkan Transaksi", "Konfirmasi ",JOptionPane.YES_NO_OPTION);
            // if (ok == 0) {     // Apabila tombol OK ditekan
                //dispose();
                //  }
            //}else{

            //  simpankepesanbeli();

            // }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBtambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtambahActionPerformed
        // TODO add your handling code here:

        setenable();
        hariini();
        isidata();
        
    }//GEN-LAST:event_jBtambahActionPerformed

    private void jBcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcariActionPerformed
        // TODO add your handling code here:
        
        DaftarPesananPembelian dpb = new DaftarPesananPembelian();
        this.getParent().add(dpb);
        dpb.setVisible(true);
        dpb.s = "1";
        dpb.setJTable();
        
    }//GEN-LAST:event_jBcariActionPerformed

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
        
        String nopesan = jTnopesan.getText();
        String tglpesan = jDtglpesan.getDateFormatString();
        String tglkirim = jDtglkirim.getDateFormatString();
        String idsupplier = jCsupplier.getText();
        String kodeitem = jTkodeitem.getText();

        if(tglkirim.equalsIgnoreCase("")||nopesan.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||idsupplier.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            setJTable();
        }else{
            cekkode();
            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jCsupplier.setEnabled(false);
        }

        }
        
    }//GEN-LAST:event_jTjumlahKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbatal;
    private javax.swing.JButton jBcari;
    private javax.swing.JButton jBedit;
    private javax.swing.JButton jBhapus;
    private javax.swing.JButton jBjumlah;
    private javax.swing.JButton jBkodeitem;
    private javax.swing.JButton jBsimpan;
    private javax.swing.JButton jBsupplier;
    private javax.swing.JButton jBtambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jCsupplier;
    private com.toedter.calendar.JDateChooser jDtglkirim;
    private com.toedter.calendar.JDateChooser jDtglpesan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTjmlpesan;
    private javax.swing.JTextField jTjumlah;
    private javax.swing.JTextField jTketerangan;
    private javax.swing.JTextField jTkodeitem;
    private javax.swing.JTextField jTnamaitem;
    public javax.swing.JTextField jTnamasupplier;
    public javax.swing.JTextField jTnopesan;
    private javax.swing.JTable jTpesanbeli;
    private javax.swing.JTextField jTsubtotal;
    public javax.swing.JTextField jTuser;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
