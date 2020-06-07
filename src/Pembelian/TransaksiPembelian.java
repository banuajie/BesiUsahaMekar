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
import besiusahamekar.koneksidb;
import static besiusahamekar.koneksidb.PathReport;

/**
 *
 * @author Banuajie
 */
public class TransaksiPembelian extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;
private static TransaksiPembelian obj = null;
String formattedNumber;
String number,harga,stotal;
int hargapokok;
String tampilan = "dd-MM-yyyy";
public String idsupplier,nopes,no_transaksi;
 int val = 0 ;
String notra,kodebarang,statustrans="0",statuspesan="0", nopesandb="";


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
     * Creates new form TransaksiPembelian2
     */
    public TransaksiPembelian() {
        initComponents();
        isidata();
    }

     private void setJTable() {
    String [] JudulKolom={"No","No_Transaksi","No Pesanan","Tgl Pesan","User","ID Supplier","Kode Material","Nama Item","Jumlah","Harga","Total","Sub Total","Keterangan"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false,false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTtransaksipembelian.setModel(tabModel);
    jTtransaksipembelian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTtransaksipembelian.getColumnModel().getColumn(0).setPreferredWidth(35);
    jTtransaksipembelian.getColumnModel().getColumn(1).setMinWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(1).setMaxWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(1).setWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(2).setMinWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(2).setMaxWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(2).setWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(3).setMinWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(3).setMaxWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(3).setWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(4).setMinWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(4).setMaxWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(4).setWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(5).setMinWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(5).setMaxWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(5).setWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(6).setPreferredWidth(180);
    jTtransaksipembelian.getColumnModel().getColumn(7).setPreferredWidth(240);
    jTtransaksipembelian.getColumnModel().getColumn(8).setPreferredWidth(60);
    jTtransaksipembelian.getColumnModel().getColumn(9).setPreferredWidth(150);
    jTtransaksipembelian.getColumnModel().getColumn(10).setPreferredWidth(150);
    jTtransaksipembelian.getColumnModel().getColumn(11).setMinWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(11).setMaxWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(11).setWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(12).setMinWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(12).setMaxWidth(0);
    jTtransaksipembelian.getColumnModel().getColumn(12).setWidth(0);
        
   //jTtransaksipembelian.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getPercentRenderer());
   //jTtransaksipembelian.getColumnModel().getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());
   //jTtransaksipembelian.getColumnModel().getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
       
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
   public void getDatatransaksi() {
        // import java.sql.connection
    String nopesan = jTnopesan.getText();
    String notransaksi = jTnotransaksi.getText();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");                 
    try {
            hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemptransaksibeli where No_Transaksi ='"+notransaksi+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      String notrans,nopes,user,idsupplier,kodeitem,nama,jumlah,harga,total,retur,keterangan;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       notrans=rs.getString("No_Transaksi");
                       nopes=rs.getString("No_Pesanan");
                       Date tglpesan=rs.getDate("Tgl_Pesan");
                       user=rs.getString("User");
                       idsupplier=rs.getString("ID_Supplier");
                       kodeitem=rs.getString("Kode_Item");
                       nama=rs.getString("Nama");
                       jumlah=rs.getString("Jumlah");
                       harga=rs.getString("Harga");
                       total=rs.getString("Total");
                       retur=rs.getString("Retur");
                       keterangan=rs.getString("Keterangan");
                      
                       jDtglpesan.setDate(tglpesan);
                       jCidsupplier.setText(idsupplier);
                       jTnopesan.setText(nopes);
                       jTuser.setText(user);
                       
         Object Data[]={no,notrans,nopes,tglpesan,user,idsupplier,kodeitem,nama,jumlah,harga,total,retur,keterangan};
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
    String notrans = jTnotransaksi.getText();
    String nopes,user,idsupplier,kodeitem,nama,jumlah,harga,total,subtotal,keterangan;
                    
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
                       user=rs.getString("User");
                       idsupplier=rs.getString("ID_Supplier");
                       kodeitem=rs.getString("Kode_Item");
                       nama=rs.getString("Nama");
                       jumlah=rs.getString("Jumlah");
                       harga=rs.getString("Harga");
                       total=rs.getString("Total");
                       subtotal=rs.getString("Sub_Total");
                       keterangan=rs.getString("Keterangan");
        
                        jDtglpesan.setDate(tglpesan);
                        jTuser.setText(user);
                        jCidsupplier.setText(idsupplier);
                        jTketerangan.setText(keterangan);
         
                       
         Object Data[]={no,notrans,nopes,tglpesan,user,idsupplier,kodeitem,nama,jumlah,harga,total,subtotal,keterangan};
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
    
    
  
   private void getDatasupplier() {
        // import java.sql.connection
            String id = jCidsupplier.getText().toString();
           try{
           
           
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tsupplier";
            ResultSet rs=st.executeQuery(sql);
            
            
           
                        while(rs.next()){
                        String idsupplier = rs.getString("ID_Supplier"); 
                        jCidsupplier.setText(idsupplier);
                        
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
       String id = jCidsupplier.getText();
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
       jCidsupplier.setEnabled(true);
       jCidsupplier.setText(idsupplier);
       
       getNamaSupplier();
   }
  public void tampildatatransaksi(){
       
       jTnotransaksi.setText(no_transaksi);
      
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
   
   public void simpantransaksibeli(){
      hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        String tglpesan = String.valueOf(format.format(jDtglpesan.getDate()));
        
       try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into tpesananbeli values(?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
                    
                st.setString(1, jTnopesan.getText());
                //st.setString(2, (String)tglpesan);
                //st.setString(3, (String)tglkirim);
                //st.setString(4, jCsupplier.getSelectedItem().toString());
                st.setString(5, jTnamasupplier.getText());
                st.setString(6, jTjmltrans.getText());
                st.setString(7, jTsubtotal.getText());
                st.setString(8, jTketerangan.getText());
                               
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tglpesan = String.valueOf(format.format(jDtglpesan.getDate()));
        
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttemptransaksibeli values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
             
                st.setString(1, jTnotransaksi.getText());
                st.setString(2, jTnopesan.getText());
                st.setString(3, (String)tglpesan);
                st.setString(4, jTuser.getText());
                st.setString(5, jCidsupplier.getText());
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
         int rows = jTtransaksipembelian.getRowCount();
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
    int jumlahBaris = jTtransaksipembelian.getRowCount();
    int totalBiaya = 0;
    int jumlah, harga;
    int total=0;
    
    TableModel tabelModel;
    tabelModel = jTtransaksipembelian.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 8).toString());
        harga = Integer.parseInt(tabelModel.getValueAt(i, 9).toString());
        total = jumlah*harga;
        totalBiaya = totalBiaya + total;
        
    }
    jTsubtotal.setText(String.valueOf(totalBiaya));
    jTjmlbayar.setText(String.valueOf(totalBiaya));
    hitungsisabayar();
   }
   
    public void hitungjum(){
    int jumlahBaris = jTtransaksipembelian.getRowCount();
    
    int jumlah;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTtransaksipembelian.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 8).toString());
        
        total = total+jumlah;
    }
    
    jTjmltrans.setText(String.valueOf(total));
   }
    
    public void cekkode(){
        
    int jumlahBaris = jTtransaksipembelian.getRowCount();
    String kodeitem = jTkodeitem.getText();
  
    String kode=null;
    TableModel tabelModel;
    
    tabelModel = jTtransaksipembelian.getModel();
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
     int val = 0 ;
    
   }
    
     public void cekkodeganda(){
        
    int jumlahBaris = jTtransaksipembelian.getRowCount();
    String kodeitem = jTkodeitem.getText();
    int val = 0 ;
    String kode=null;
    TableModel tabelModel;
    
    tabelModel = jTtransaksipembelian.getModel();
    for (int i=0; i<jumlahBaris; i++){
        kode = tabelModel.getValueAt(i, 6).toString();
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
     
     public void hapus_Data() {
    // Konfirmasi sebelum melakukan penghapusan data
    int row = jTtransaksipembelian.getSelectedRow();
    kodebarang = tabModel.getValueAt(row, 6).toString();
    notra = tabModel.getValueAt(row, 1).toString();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus = '" + kodebarang + "' Transaksi = '" + notra + 
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttemptransaksibeli WHERE Kode_Item = ? and No_Transaksi = ?";
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
     public void hariini(){
         DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	   //get current date time with Date()
	   Date date = new Date();
           jDtglpesan.setDate(new Date());
     }
    
     public void setenable(){
         jTjumlah.setEnabled(true);
         jTnopesan.setEnabled(true);
         jDtglpesan.setEnabled(true);
         jTnotransaksi.setEnabled(true);
                jCidsupplier.setEnabled(true);
                jBsupplier.setEnabled(true);
                        jTkodeitem.setEnabled(true);
                        jBkodeitem.setEnabled(true);
                                jBcari.setEnabled(false);
                                       // jBcetak.setEnabled(true);
                                        jTtransaksipembelian.setEnabled(true);
                                        jBtambah.setEnabled(false);
                                        jBsimpan.setEnabled(true);
                                        jBbatal.setEnabled(true);
                                        jBnopesan.setEnabled(true);
                                        jTjmlbayar.setEnabled(true);
                                        jTketerangan.setEnabled(true);
     }
     
          public void setdisable(){
         notransaksi();
          jTjumlah.setEnabled(false);
         jDtglpesan.setDate(null);
        jTnotransaksi.setEnabled(false);  
         jTnopesan.setEnabled(false);
        jDtglpesan.setEnabled(false);
        
                jCidsupplier.setEnabled(false);
                jBsupplier.setEnabled(false);
                        jTkodeitem.setEnabled(false);
                        jBkodeitem.setEnabled(false);
                                
                                jBcari.setEnabled(false);
                                        jTtransaksipembelian.setEnabled(false);
                                        jBtambah.setEnabled(true);
                                        jBsimpan.setEnabled(false);
                                        jBbatal.setEnabled(false);
                                        jBnopesan.setEnabled(false);
                                        jTjmlbayar.setEnabled(false);
                                        jTjmlbayar.setText("0");
                                        jTketerangan.setEnabled(false);
     }

   
   public void notransaksi(){
        
        Date sk = new Date();
        SimpleDateFormat format1=new SimpleDateFormat("ddMMyyyy");
        String time = format1.format(sk);
        try{
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sql= "select right(No_Transaksi,3) as kd from ttemptransaksibeli where No_Transaksi Like '%" +time+ "%' order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTnotransaksi.setText("NTPB"+time+"000"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTnotransaksi.setText("NTPB"+time+"00"+Integer.toString(kode));   
                else if(kode>=1000&&kode<=9999) 
                    jTnotransaksi.setText("NTPB"+time+"0"+Integer.toString(kode));
                else
                    jTnotransaksi.setText("NTPB"+time+"0000"+Integer.toString(kode));
            }else{

                int kode = 1;

                jTnotransaksi.setText("NTPB"+time+"0000"+Integer.toString(kode));

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

    public void simpanketemptransaksi(){
//Connection conn;
      String notrans =jTnotransaksi.getText();
      String nopesan =jTnopesan.getText();
     try{
            conn=koneksidb.getkoneksi();
            Statement state = conn.createStatement();
            int baris = jTtransaksipembelian.getRowCount();
            
		for (int a=0; a<baris; a++)
		{
                
                              
		String query = "INSERT INTO ttemptransaksibeli (No_Transaksi,No_Pesanan,Tgl_Pesan,User,ID_Supplier,Kode_Item,Nama,Jumlah,Harga,Total,Retur,Keterangan) VALUES( '"+notrans+"','"+nopesan+"','"+jTtransaksipembelian.getValueAt(a,3)+"','"+jTtransaksipembelian.getValueAt(a,4)+"','"+jTtransaksipembelian.getValueAt(a,5)+"','"+jTtransaksipembelian.getValueAt(a,6)+"','"+jTtransaksipembelian.getValueAt(a,7)+"','"+jTtransaksipembelian.getValueAt(a,8)+"','"+jTtransaksipembelian.getValueAt(a,9)+"','"+jTtransaksipembelian.getValueAt(a,10)+"','"+jTtransaksipembelian.getValueAt(a,11)+"','"+jTtransaksipembelian.getValueAt(a,12)+"')";
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
   
    public void updateketdata(){
    
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update tpesananbeli set Status= ? WHERE No_Pesanan = ?";
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
    
    
        
        private void cekstatuspesan() {
              String nopes = jTnopesan.getText();
             
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String pesan="Select Status from tpesananbeli where No_Pesanan ='"+nopes+"'";
            ResultSet rs=st.executeQuery(pesan);
                
            while(rs.next()){
            String cekstatus = rs.getString(1);
            statuspesan = cekstatus;
        }}catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
        }catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);}    

    }
      
            private void cekstatustrans() {
              String notrans = jTnotransaksi.getText();
              
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String trans="Select Status,No_Transaksi from ttransaksibeli where No_Transaksi ='"+notrans+"'";
            ResultSet rs=st.executeQuery(trans);
                
            while(rs.next()){
            String cekstatus = rs.getString(1);
            String notranss = rs.getString(2);
            if(cekstatus.equals(null)){
                statustrans="0";
                
            }
            else{
            statustrans = cekstatus;
             nopesandb = notranss;
             }
        }}catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
        }catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);}    

    }    
        
        
     public void kosongheader(){
              jTnamasupplier.setText("");
              jTnotransaksi.setText("");
              jTnopesan.setText("");
              jCidsupplier.setText("");
              jTkodeitem.setText("");
              jBcari.setEnabled(true);
          }
   
     public void hitungsisabayar(){
         jTsisabayar.setText("0");
         int total=0;
         int bayar=0;
         
         if(jTjmlbayar.getText().equals("")){
             bayar=0;
         }else{
             bayar = Integer.parseInt(jTjmlbayar.getText());
         }
         int sub = Integer.parseInt(jTsubtotal.getText());
         
         total = bayar-sub;
         String stotal = String.valueOf(total);
        jTsisabayar.setText(stotal);
     }
     
     public void cekkodeitemtabel(){
         int baris = jTtransaksipembelian.getRowCount();
         for (int a=0; a<baris; a++)
		{String kodeitem = jTtransaksipembelian.getValueAt(a,6).toString();
                
                }
     }
     
     
     public void updatestok(){
         //String kodeitem = jTkodeitem.getText();
         int baris = jTtransaksipembelian.getRowCount();
         for (int a=0; a<baris; a++)
	{String kodeitem = jTtransaksipembelian.getValueAt(a,6).toString();
         
         String jumlah = jTtransaksipembelian.getValueAt(a,8).toString();
         String notrans = jTnotransaksi.getText();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         String tanggal = String.valueOf(format.format(jDtglpesan.getDate()));
         
         String stokkeluar = "0" ;
         try{
        conn=koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sqlstok = "SELECT Stok FROM tmaterial WHERE Kode_Item='" +kodeitem+"'"; 
        ResultSet rs=st.executeQuery(sqlstok); 
	while(rs.next()){
            String stokawal = rs.getString(1);
            String insert = "INSERT INTO tstokbarang (No_Transaksi,Tanggal,Kode_Item,Stok_Akhir,Stok_Awal,Stok_Masuk,Stok_Keluar) VALUES( '"+notrans+"','"+tanggal+"','"+kodeitem+"','0','"+stokawal+"','0','0')";
	    
            int stokakhir = Integer.parseInt(jumlah) + Integer.parseInt(stokawal);
            String tabelstok ="UPDATE tstokbarang SET Stok_Akhir='" +stokakhir+ "',Stok_Masuk='"+jumlah+ "',Stok_Keluar='"+stokkeluar+ "' WHERE Kode_Item='" + kodeitem + "'";
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
     
     
     public void simpanketransaksibeli(){
        hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(format.format(jDtglpesan.getDate()));
        
       try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttransaksibeli values(?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement st=conn.prepareStatement(sql);
                    
                st.setString(1, jTnotransaksi.getText());
                st.setString(2, jTnopesan.getText());
                st.setString(3, (String)tanggal);
                st.setString(4, jCidsupplier.getText());
                st.setString(5, jTnamasupplier.getText());
                st.setString(6, jTjmltrans.getText());
                st.setString(7, jTsubtotal.getText());
                st.setString(8, jTjmlbayar.getText());
                st.setString(9, jTsisabayar.getText());
                st.setString(10, jTketerangan.getText());
                st.setString(11, "1");               
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
     
     public void cetaktransaksi(){
         String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("notrans", jTnotransaksi.getText());
        try {

            reportSource = PathReport + "BuktiPembelian.jrxml";
            reportDest = PathReport + "BuktiPembelian.html";

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
            String notransaksi = jTnotransaksi.getText();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Membatalkan Transaksi = '" + notransaksi +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttemptransaksibeli WHERE No_Transaksi = ?";
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
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTtransaksipembelian = new javax.swing.JTable();
        jBedit = new javax.swing.JButton();
        jTjmltrans = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTsubtotal = new javax.swing.JTextField();
        jBhapus = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTjmlbayar = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTketerangan = new javax.swing.JTextField();
        jBcari = new javax.swing.JButton();
        jBtambah = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jBsimpan = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jmlData = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jBsupplier = new javax.swing.JButton();
        jTnotransaksi = new javax.swing.JTextField();
        jTnopesan = new javax.swing.JTextField();
        jTuser = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTnamasupplier = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jTnamaitem = new javax.swing.JTextField();
        jTjumlah = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jBkodeitem = new javax.swing.JButton();
        jBnopesan = new javax.swing.JButton();
        jDtglpesan = new com.toedter.calendar.JDateChooser();
        jTkodeitem = new javax.swing.JTextField();
        jCidsupplier = new javax.swing.JTextField();
        jTsisabayar = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jBbatal = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Transaksi Pembelian Material");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel23.setText("Sub-Total");

        jTtransaksipembelian.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTtransaksipembelian.setModel(new javax.swing.table.DefaultTableModel(
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
        jTtransaksipembelian.setEnabled(false);
        jTtransaksipembelian.setFocusable(false);
        jTtransaksipembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTtransaksipembelianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTtransaksipembelianMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTtransaksipembelian);

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

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText(":");

        jTsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTsubtotal.setText("0");
        jTsubtotal.setEnabled(false);

        jBhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Close.png"))); // NOI18N
        jBhapus.setText("Hapus");
        jBhapus.setEnabled(false);
        jBhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhapusActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel24.setText("Jumlah Bayar");

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

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText(":");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel18.setText("Keterangan");

        jBcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.gif"))); // NOI18N
        jBcari.setText("Cari");
        jBcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcariActionPerformed(evt);
            }
        });

        jBtambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jBtambah.setText("Tambah");
        jBtambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtambahActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel20.setText("Jumlah Transaksi");

        jBsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jBsimpan.setText("Simpan");
        jBsimpan.setEnabled(false);
        jBsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsimpanActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText(":");

        jLabel37.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel37.setText("Jumlah Data : ");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        jButton5.setText("Keluar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Transaksi Pembelian Material", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("No Transaksi");

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
        jLabel8.setText("No Pesan");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText(":");

        jBsupplier.setText("jButton1");
        jBsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsupplierActionPerformed(evt);
            }
        });

        jTnotransaksi.setEnabled(false);
        jTnotransaksi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTnotransaksiFocusLost(evt);
            }
        });
        jTnotransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnotransaksiKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnotransaksiKeyTyped(evt);
            }
        });

        jTnopesan.setEnabled(false);
        jTnopesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnopesanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTnopesanKeyReleased(evt);
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

        jButton7.setText("jButton1");
        jButton7.setNextFocusableComponent(jTjumlah);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTnamaitem.setEnabled(false);

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
        jLabel16.setText("Jumlah");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(":");

        jBkodeitem.setText(">");
        jBkodeitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkodeitemActionPerformed(evt);
            }
        });

        jBnopesan.setText("...");
        jBnopesan.setEnabled(false);
        jBnopesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnopesanActionPerformed(evt);
            }
        });

        jDtglpesan.setDateFormatString("dd-MM-yyyy");
        jDtglpesan.setEnabled(false);

        jTkodeitem.setEnabled(false);
        jTkodeitem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTkodeitemKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTkodeitemKeyTyped(evt);
            }
        });

        jCidsupplier.setEnabled(false);
        jCidsupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCidsupplierFocusGained(evt);
            }
        });
        jCidsupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCidsupplierKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jCidsupplierKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTnopesan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBnopesan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel10)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTnotransaksi)
                                    .addComponent(jDtglpesan, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel15)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel17)))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTnamasupplier)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jCidsupplier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTuser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTjumlah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jTnotransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7))
                                .addGap(21, 21, 21)
                                .addComponent(jLabel8))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jDtglpesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jTnopesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBnopesan)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jBsupplier)
                            .addComponent(jCidsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(jButton7)
                        .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBkodeitem)
                        .addComponent(jLabel16))))
        );

        jTsisabayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTsisabayar.setText("0");
        jTsisabayar.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText(":");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel27.setText("Sisa Bayar");

        jBbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Undo.png"))); // NOI18N
        jBbatal.setText("Batal");
        jBbatal.setEnabled(false);
        jBbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19))
                            .addComponent(jBedit))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jTketerangan))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jBhapus)
                                .addGap(124, 124, 124)
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jTjmltrans, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)
                                .addGap(23, 23, 23)
                                .addComponent(jTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                .addComponent(jButton5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jmlData))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25)
                                .addGap(23, 23, 23)
                                .addComponent(jTjmlbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26)
                                .addGap(23, 23, 23)
                                .addComponent(jTsisabayar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBedit)
                        .addComponent(jBhapus)
                        .addComponent(jLabel21)
                        .addComponent(jLabel20)
                        .addComponent(jTjmltrans, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addComponent(jTsubtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel24))
                    .addComponent(jTjmlbayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jLabel27))
                    .addComponent(jTsisabayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtambah)
                    .addComponent(jBsimpan)
                    .addComponent(jBcari)
                    .addComponent(jBbatal)
                    .addComponent(jButton5))
                .addGap(6, 6, 6))
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

    private void jTtransaksipembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtransaksipembelianMouseClicked
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();

        if (evt.getClickCount() == 2) {
            Point pnt = evt.getPoint();
            int row = jTtransaksipembelian.rowAtPoint(pnt);
            //do something

            //fDB.fAB = this;
            int tabel = jTtransaksipembelian.getSelectedRow();

            fDB.no_transaksi = jTtransaksipembelian.getValueAt(tabel, 1).toString();
            fDB.kodeitem = jTtransaksipembelian.getValueAt(tabel, 6).toString();
            fDB.namaitem = jTtransaksipembelian.getValueAt(tabel, 7).toString();
            fDB.jumlah = jTtransaksipembelian.getValueAt(tabel, 8).toString();
            fDB.harga = jTtransaksipembelian.getValueAt(tabel, 9).toString();
            fDB.total= jTtransaksipembelian.getValueAt(tabel, 10).toString();

            fDB.jsimpan.setText("Edit");
            fDB.tampildata();
            fDB.setVisible(true);

        }
    }//GEN-LAST:event_jTtransaksipembelianMouseClicked

    private void jTtransaksipembelianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtransaksipembelianMouseEntered
        // TODO add your handling code here:
        String nopesan = jTnopesan.getText();
        if(jTnotransaksi.isEnabled()==false&&nopesan.equals("")||jCidsupplier.isEnabled()==false){
            getDatatransaksi();
            hitungsubtotal();
            hitungjum();
            jBedit.setEnabled(false);
            jBhapus.setEnabled(false);
        }else{
            getDatatransaksi();
            //tampilDataKeJTable();
            hitungsubtotal();
            hitungjum();
            jBedit.setEnabled(false);
            jBhapus.setEnabled(false);
        }
    }//GEN-LAST:event_jTtransaksipembelianMouseEntered

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:

        try{
            jBedit.setEnabled(true);
            jBhapus.setEnabled(true);

            //double click handle

            EditDataTabel fDB = new EditDataTabel();

            //do something

            //fDB.fAB = this;
            int tabel = jTtransaksipembelian.getSelectedRow();

            fDB.no_transaksi = jTtransaksipembelian.getValueAt(tabel, 1).toString();
            fDB.kodeitem = jTtransaksipembelian.getValueAt(tabel, 6).toString();
            fDB.namaitem = jTtransaksipembelian.getValueAt(tabel, 7).toString();
            fDB.jumlah = jTtransaksipembelian.getValueAt(tabel, 8).toString();
            fDB.harga = jTtransaksipembelian.getValueAt(tabel, 9).toString();
            fDB.total= jTtransaksipembelian.getValueAt(tabel, 10).toString();

            fDB.jsimpan.setText("Edit");
            fDB.tampildata();
            fDB.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Edit");
            setJTable();
            getDatatransaksi();
            //tampilDataKeJTable();
        }
    }//GEN-LAST:event_jBeditActionPerformed

    private void jBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
            jmldatatable();
            jBhapus.setEnabled(false);
            //cekkode();
            getDatatransaksi();

            hitungsubtotal();
            hitungjum();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
            getDatatransaksi();
            //tampilDataKeJTable();
        }

    }//GEN-LAST:event_jBhapusActionPerformed

    private void jTjmlbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjmlbayarKeyPressed
        // TODO add your handling code here:
        int x=0;
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {               
        if(jTjmlbayar.getText().equals("")){
            x=0;
        }  else{
                    x = Integer.parseInt(jTjmlbayar.getText());
                    }
        
        int y = Integer.parseInt(jTsubtotal.getText());
        if(x<y){
            JOptionPane.showMessageDialog(this, "Jumlah Bayar Kurang");
            jTjmlbayar.setText("0");
        }
        }
    }//GEN-LAST:event_jTjmlbayarKeyPressed

    private void jBcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcariActionPerformed
        // TODO add your handling code here:
        DaftarTransaksiBeli dtb = new DaftarTransaksiBeli();
        this.getParent().add(dtb);
        dtb.setVisible(true);
    }//GEN-LAST:event_jBcariActionPerformed

    private void jBtambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtambahActionPerformed
        // TODO add your handling code here:

        setenable();
        hariini();
        isidata();
    }//GEN-LAST:event_jBtambahActionPerformed

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:

        String notrans = jTnotransaksi.getText();
        String tglpesan = jDtglpesan.getDateFormatString();
        String jmlbayar = jTjmlbayar.getText();
        String subtotal = jCidsupplier.getText();
        String sisabayar = jTsisabayar.getText();
        int x = Integer.parseInt(jTjmlbayar.getText());
        int y = Integer.parseInt(jTsubtotal.getText());
        int table = jTtransaksipembelian.getRowCount();

        if(x<y||sisabayar.equalsIgnoreCase("")||notrans.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")||jmlbayar.equalsIgnoreCase("")||table<1||subtotal.equalsIgnoreCase("")){
             jTjmlbayar.setFocusable(true);
            JOptionPane.showMessageDialog(this, "Data Belum Lengkap");
           
        }else{
     
           cekstatustrans();

            if(statustrans.equals("1")){
                JOptionPane.showMessageDialog(this,"Data Sudah Masuk Transaksi Pembelian");
                jBsimpan.setEnabled(false);
                
            }else{
                updatestok();
                simpanketransaksibeli();
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
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            if(!dp.getTitle().equals(""))getDatas(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }

        }

        private void getDatas(String kode){

            try{
                conn =koneksidb.getkoneksi();
                Statement st= conn.createStatement();
                String sql="Select * from tsupplier where ID_Supplier='"+kode+"'";
                ResultSet rs=st.executeQuery(sql);

                while(rs.next()){

                    String nama =rs.getString("Nama");
                    jCidsupplier.setText(kode);
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

    private void jTnotransaksiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnotransaksiFocusLost
        // TODO add your handling code here:
        String string = jTnotransaksi.getText();
        String upper = string.toUpperCase();
        jTnotransaksi.setText(upper);
    }//GEN-LAST:event_jTnotransaksiFocusLost

    private void jTnotransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnotransaksiKeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            int cektabel = jTtransaksipembelian.getRowCount();
            hapusIsiJTable();
            setJTable();
            
            getDatatransaksi();
            getNamaSupplier();
            
                         
            
            //tampilDataKeJTable();
            hitungsubtotal();
            hitungjum();

            if(cektabel==0){
                jCidsupplier.setText("");
                jCidsupplier.setEnabled(true);
                jTnamasupplier.setText("");
                jBsimpan.setEnabled(true);
            }else{
                jTnopesan.setEnabled(false);
                jCidsupplier.setEnabled(false);
                jBsimpan.setEnabled(false);
            }
            
            cekstatustrans();
            if(nopesandb.equals(jTnotransaksi.getText())){
                    
                    jBsimpan.setEnabled(false);
                    

                }
        }
    }//GEN-LAST:event_jTnotransaksiKeyPressed

    private void jTnotransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnotransaksiKeyTyped
        // TODO add your handling code here:
        int cektabel = jTtransaksipembelian.getRowCount();
        hapusIsiJTable();
        setJTable();
        getDatatransaksi();
        getNamaSupplier();
        //tampilDataKeJTable();
        hitungsubtotal();
        hitungjum();

        if(cektabel==0){
            jCidsupplier.setText("");
            jCidsupplier.setEnabled(true);
            jTnamasupplier.setText("");
        }else{
            jTnopesan.setEnabled(false);
            jCidsupplier.setEnabled(false);

        }
    }//GEN-LAST:event_jTnotransaksiKeyTyped

    private void jTnopesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnopesanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            jCidsupplier.setEnabled(false);
            String cek = jTnopesan.getText();
            if (cek.equals("")){

            }else{

                jTnotransaksi.setEnabled(false);
                jTnopesan.setEnabled(false);
                String string = jTnopesan.getText();
                String upper = string.toUpperCase();
                jTnopesan.setText(upper);
                cekstatuspesan();

                if(statuspesan.equals("1")){
                    JOptionPane.showMessageDialog(this,"Data Sudah Masuk Transaksi ");
                    jTnopesan.setText("");
                    jTnopesan.setEnabled(true);
                    jBsimpan.setEnabled(false);
                    

                }else{
                    tampilDataKeJTable();
                    cekkodeganda();
                    getNamaSupplier();
                    hitungsubtotal();
                    hitungjum();
                    jmldatatable();

                }
            }
        }
    }//GEN-LAST:event_jTnopesanKeyPressed

    private void jTnopesanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnopesanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTnopesanKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        MasterMaterial dp=new MasterMaterial();
        
        String kodesupplier = String.valueOf(jCidsupplier.getText());
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

        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Konsumen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getDatakodeitem(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }

        }
        private void getDatakodeitem(String kode){

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

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jBkodeitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkodeitemActionPerformed
        // TODO add your handling code here:

        String notrans = jTnotransaksi.getText();
        String tglpesan = jDtglpesan.getDateFormatString();
        String idsupp = jCidsupplier.getText();
        String jumlah = jTjumlah.getText();
        String kodeitem = jTkodeitem.getText();

        if(idsupp.equalsIgnoreCase("")||notrans.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            // setJTable();

        }else{

            cekkode();
            getDatatransaksi();
            jmldatatable();
            hitungsubtotal();
            hitungjum();

        }

        jTnopesan.setEnabled(false);
        jTnotransaksi.setEnabled(false);
    }//GEN-LAST:event_jBkodeitemActionPerformed

    private void jBnopesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnopesanActionPerformed
        // TODO add your handling code here:
        DaftarPesananPembelian dp=new DaftarPesananPembelian();
        dp.setTitle("");
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        dp.s = "0";
        dp.setJTable();;
        panelPop.add(dp).setVisible(true);

        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih No Pesanan", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getData(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }

        }
        private void getData(String kode){

            jTnopesan.setText(kode);
            jTnotransaksi.setEnabled(false);
            jTnopesan.setEnabled(false);
            String string = jTnopesan.getText();
            String upper = string.toUpperCase();
            jTnopesan.setText(upper);
            cekstatuspesan();

            if(statuspesan.equals("1")){
                JOptionPane.showMessageDialog(this,"Data Sudah Masuk Transaksi ");
                jTnopesan.setText("");
                jTnopesan.setEnabled(true);

            }else{
                tampilDataKeJTable();
                cekkodeganda();
                getNamaSupplier();
                hitungsubtotal();
                hitungjum();
                jmldatatable();
            }
    }//GEN-LAST:event_jBnopesanActionPerformed

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
        String string = jTkodeitem.getText();
        String upper = string.toUpperCase();
        jTkodeitem.setText(upper);
        ambildata();
    }//GEN-LAST:event_jTkodeitemKeyTyped

    private void jCidsupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCidsupplierFocusGained
        // TODO add your handling code here:
        getNamaSupplier();
    }//GEN-LAST:event_jCidsupplierFocusGained

    private void jCidsupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCidsupplierKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            getNamaSupplier();
            String string = jCidsupplier.getText();
            String upper = string.toUpperCase();
            jCidsupplier.setText(upper);
        }

    }//GEN-LAST:event_jCidsupplierKeyPressed

    private void jCidsupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCidsupplierKeyTyped
        // TODO add your handling code here:
        getNamaSupplier();
        String string = jCidsupplier.getText();
        String upper = string.toUpperCase();
        jCidsupplier.setText(upper);
    }//GEN-LAST:event_jCidsupplierKeyTyped

    private void jBbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbatalActionPerformed
        // TODO add your handling code here:
        Transaksibatal();
        setdisable();
        kosongheader();
        isidata();
    }//GEN-LAST:event_jBbatalActionPerformed

    private void jTjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjumlahKeyTyped
        // TODO add your handling code here:
                     char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_jTjumlahKeyTyped

    private void jTjmlbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjmlbayarKeyTyped
        // TODO add your handling code here:
                      char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
        hitungsisabayar();
    }//GEN-LAST:event_jTjmlbayarKeyTyped

    private void jTjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjumlahKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            
            String notrans = jTnotransaksi.getText();
        String tglpesan = jDtglpesan.getDateFormatString();
        String idsupp = jCidsupplier.getText();
        String jumlah = jTjumlah.getText();
        String kodeitem = jTkodeitem.getText();

        if(idsupp.equalsIgnoreCase("")||notrans.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            // setJTable();

        }else{

            cekkode();
            getDatatransaksi();
            jmldatatable();
            hitungsubtotal();
            hitungjum();

        }

        jTnopesan.setEnabled(false);
        jTnotransaksi.setEnabled(false);
            
        }
    }//GEN-LAST:event_jTjumlahKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbatal;
    private javax.swing.JButton jBcari;
    private javax.swing.JButton jBedit;
    private javax.swing.JButton jBhapus;
    private javax.swing.JButton jBkodeitem;
    private javax.swing.JButton jBnopesan;
    private javax.swing.JButton jBsimpan;
    private javax.swing.JButton jBsupplier;
    private javax.swing.JButton jBtambah;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JTextField jCidsupplier;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTjmlbayar;
    private javax.swing.JTextField jTjmltrans;
    private javax.swing.JTextField jTjumlah;
    private javax.swing.JTextField jTketerangan;
    private javax.swing.JTextField jTkodeitem;
    private javax.swing.JTextField jTnamaitem;
    private javax.swing.JTextField jTnamasupplier;
    private javax.swing.JTextField jTnopesan;
    private javax.swing.JTextField jTnotransaksi;
    private javax.swing.JTextField jTsisabayar;
    private javax.swing.JTextField jTsubtotal;
    private javax.swing.JTable jTtransaksipembelian;
    public javax.swing.JTextField jTuser;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
