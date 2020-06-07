/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pembelian;

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
public class ReturPembelian extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;
private static TransaksiPembelian obj = null;
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
     * Creates new form ReturPembelian
     */
    public ReturPembelian() {
        initComponents();
        isidata();
    }

    private void setJTable() {
    String [] JudulKolom={"No","No_Retur","No_Transaksi","Tanggal","Tgl Transaksi","User","ID Supplier","Kode Material","Nama Item","Jumlah","Harga","Total","Keterangan","Status"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false,false, false, false, false, false, false, false, false, false, false,false,false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTreturbeli.setModel(tabModel);
    jTreturbeli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTreturbeli.getColumnModel().getColumn(0).setPreferredWidth(35);
    jTreturbeli.getColumnModel().getColumn(1).setMinWidth(0);
    jTreturbeli.getColumnModel().getColumn(1).setMaxWidth(0);
    jTreturbeli.getColumnModel().getColumn(1).setWidth(0);
    jTreturbeli.getColumnModel().getColumn(2).setMinWidth(0);
    jTreturbeli.getColumnModel().getColumn(2).setMaxWidth(0);
    jTreturbeli.getColumnModel().getColumn(2).setWidth(0);
    jTreturbeli.getColumnModel().getColumn(3).setMinWidth(0);
    jTreturbeli.getColumnModel().getColumn(3).setMaxWidth(0);
    jTreturbeli.getColumnModel().getColumn(3).setWidth(0);
    jTreturbeli.getColumnModel().getColumn(4).setMinWidth(0);
    jTreturbeli.getColumnModel().getColumn(4).setMaxWidth(0);
    jTreturbeli.getColumnModel().getColumn(4).setWidth(0);
    jTreturbeli.getColumnModel().getColumn(5).setMinWidth(0);
    jTreturbeli.getColumnModel().getColumn(5).setMaxWidth(0);
    jTreturbeli.getColumnModel().getColumn(5).setWidth(0);
    jTreturbeli.getColumnModel().getColumn(6).setMinWidth(0);
    jTreturbeli.getColumnModel().getColumn(6).setMaxWidth(0);
    jTreturbeli.getColumnModel().getColumn(6).setWidth(0);
    jTreturbeli.getColumnModel().getColumn(7).setPreferredWidth(180);
    jTreturbeli.getColumnModel().getColumn(8).setPreferredWidth(240);
    jTreturbeli.getColumnModel().getColumn(9).setPreferredWidth(60);
    jTreturbeli.getColumnModel().getColumn(10).setPreferredWidth(150);
    jTreturbeli.getColumnModel().getColumn(1).setPreferredWidth(150);
    jTreturbeli.getColumnModel().getColumn(12).setMinWidth(0);
    jTreturbeli.getColumnModel().getColumn(12).setMaxWidth(0);
    jTreturbeli.getColumnModel().getColumn(12).setWidth(0);
    jTreturbeli.getColumnModel().getColumn(13).setMinWidth(0);
    jTreturbeli.getColumnModel().getColumn(13).setMaxWidth(0);
    jTreturbeli.getColumnModel().getColumn(13).setWidth(0);

   //jTtransaksipembelian.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getPercentRenderer());
   //jTtransaksipembelian.getColumnModel().getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());
   //jTtransaksipembelian.getColumnModel().getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
       
   
    }
         
    public void isidata(){
       setJTable();
       notransaksi();
       hariini();
   }
   public void getDatatransaksibeli() {
        // import java.sql.connection
    
    String notransaksi = jTnotransbeli.getText();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");                 
    try {
           
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemptransaksibeli where No_Transaksi ='"+notransaksi+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      String notrans,user,idsupplier,kodeitem,nama,harga;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       notrans=rs.getString("No_Transaksi");
                       Date tglpesan=rs.getDate("Tgl_Pesan");
                       user=rs.getString("User");
                       idsupplier=rs.getString("ID_Supplier");
                       kodeitem=rs.getString("Kode_Item");
                       nama=rs.getString("Nama");
                       harga=rs.getString("Harga");
                       
                       String tgl = sdf.format(tglpesan);
                       jTtgltransbeli.setText(tgl);
                       jTuser.setText(user);
                       jCidsupplier.setText(idsupplier);
                       jCkodeitem.addItem(kodeitem);
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
            String sql="Select * from ttempreturbeli WHERE No_Retur ='"+noret+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                    String notrans,user,idsupplier,kodeitem,nama,jumlah,harga,total,keterangan,status;
                int no=0;
                   while(rs.next()){
                       no=no+1;
                       
                       noret=rs.getString("No_Retur");
                       notrans=rs.getString("No_Transaksi");
                       Date tanggal=rs.getDate("Tanggal");
                       Date tgltransaksi=rs.getDate("Tgl_Transaksi");
                       user=rs.getString("User");
                       idsupplier=rs.getString("ID_Supplier");
                       kodeitem=rs.getString("Kode_Item");
                       nama=rs.getString("Nama");
                       jumlah=rs.getString("Jumlah");
                       harga=rs.getString("Harga");
                       total=rs.getString("Total");
                       keterangan=rs.getString("Keterangan");
                       status=rs.getString("Status");
                       
                        jDtanggal.setDate(tanggal);
                        jTnotransbeli.setText(notrans);
                        jTuser.setText(user);
                        jCidsupplier.setText(idsupplier);
                        jTketerangan.setText(keterangan);
                       
         Object Data[]={no,noret,notrans,tanggal,tgltransaksi,user,idsupplier,kodeitem,nama,jumlah,harga,total,keterangan,status};
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
   
   public void getNamaSupplier() {
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
       
       
   }
  public void tampildatatransaksi(){
       
       jTnotransbeli.setText(no_transaksi);
      
   }
    
  public void tampildataretur(){
       
       jTnoretur.setText(no_retur);
      
   }
  
   public void jmldatatable(){
         int rows = jTreturbeli.getRowCount();
         String jmls = String.valueOf(rows);
         jmlData.setText(jmls);
         
             
   } 
    
public void hitungtotal(){
            String sjml = jTjumlah.getText();
            int jml = Integer.parseInt(sjml);
            harga = String.valueOf(hargajual);
            int total = hargajual*jml;
            stotal = String.valueOf(total);
}
   public void hitungsubtotal(){
    int jumlahBaris = jTreturbeli.getRowCount();
    int totalBiaya = 0;
    int jumlah, harga;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTreturbeli.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 9).toString());
        harga = Integer.parseInt(tabelModel.getValueAt(i, 10).toString());
        total = jumlah*harga;
        totalBiaya = totalBiaya + total;
        
    }
    jTsubtotal.setText(String.valueOf(totalBiaya));
   }
   
    public void hitungjum(){
    int jumlahBaris = jTreturbeli.getRowCount();
    
    int jumlah;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTreturbeli.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 9).toString());
        
        total = total+jumlah;
    }
    
    jTjmltrans.setText(String.valueOf(total));
   }
    
    
  
   
    public void cekkodeitem(){
  
         String notrans = jTnotransbeli.getText();
         String kodeinput = jTnamaitem.getName();
         
         
         try{
        
        Statement st= conn.createStatement();
        String cekkode = "SELECT * FROM ttemptransaksibeli WHERE No_Transaksi='" +notrans+"' and Kode_Item='" +kodeinput+"'"; 
        ResultSet rs=st.executeQuery(cekkode); 
	while(rs.next()){
            kode = rs.getString("Nama");
            
        }
        JOptionPane.showMessageDialog(this, kode);
        
          if(kode.equalsIgnoreCase(kodeinput)){
                JOptionPane.showMessageDialog(this, "Kode Material ini tidak ada dalam transaksi");
            }else{
                JOptionPane.showMessageDialog(this, "Kode Material ada dalam transaksi");
               
            }
            
        
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
          }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }  
      
     
    }
    
    
    public void hariini(){
         DateFormat dateFormat = new SimpleDateFormat("d / MMM / yyyy");
	   //get current date time with Date()
	   Date date = new Date();
           jDtanggal.setDate(new Date());
     }
    
     public void setenable(){
         jTjumlah.setEnabled(true);
        
         jDtanggal.setEnabled(true);
       
         jBcari.setEnabled(false);
         //jBcetak.setEnabled(true);
                   jTnotransbeli.setEnabled(true);
                                        jBtambah.setEnabled(false);
                                        jBsimpan.setEnabled(true);
                                        jBbatal.setEnabled(true);
                                    jBnotransaksibeli.setEnabled(true);
                                        jTketerangan.setEnabled(true);
                                        jTketerangan.setEnabled(true);
                                       jTnoretur.setEnabled(true);
     }
     
          public void setdisable(){
         notransaksi();
          jTjumlah.setEnabled(false);
          jDtanggal.setDate(null);
          jTnoretur.setEnabled(false);  
         jTnoretur.setEnabled(false);
        
                jCidsupplier.setEnabled(false);
                jBnotransaksibeli.setEnabled(false);
                                              
                                jCkodeitem.setEnabled(false);
                                jBcari.setEnabled(true);
                                       
                                        jBtambah.setEnabled(true);
                                        jBsimpan.setEnabled(false);
                                        jBbatal.setEnabled(false);
                                        jTketerangan.setEnabled(false);
                                        jTketerangan.setEnabled(false);
     }
   
   
    public void notransaksi(){
        
        Date sk = new Date();
        SimpleDateFormat format1=new SimpleDateFormat("ddMMyyyy");
        String time = format1.format(sk);
        try{
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sql= "select right(No_Retur,3) as kd from ttempreturbeli where No_Retur Like '%" +time+ "%' order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTnoretur.setText("NRPB"+time+"000"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTnoretur.setText("NRPB"+time+"00"+Integer.toString(kode));   
                else if(kode>=1000&&kode<=9999) 
                    jTnoretur.setText("NRPB"+time+"0"+Integer.toString(kode));
                else
                    jTnoretur.setText("NRPB"+time+"0000"+Integer.toString(kode));
            }else{

                int kode = 1;

                jTnoretur.setText("NRPB"+time+"0000"+Integer.toString(kode));

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
   
   
        public void hapus_Data() {
    // Konfirmasi sebelum melakukan penghapusan data
        int row = jTreturbeli.getSelectedRow();
        String kodeitem = tabModel.getValueAt(row, 7).toString();
        String noretur = jTnoretur.getText();
        int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus = '" + kodeitem + "' Retur = '" + noretur + 
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
        if (ok == 0) {     // Apabila tombol OK ditekan
        try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttempreturBeli WHERE No_Retur = ? and Kode_Item = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, noretur);
        st.setString(2, kodeitem);
        int rs=st.executeUpdate();
        
      } catch (Exception se) {  // Silahkan tambahkan catch Exception yang lain
         JOptionPane.showMessageDialog(this,"Gagal Hapus Data.. ");
       }
    
  } 
        }

    
  public void ambildata(){
      
       jTjumlah.setText("");
           try{
                String id = jCkodeitem.getSelectedItem().toString();
       
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttemptransaksibeli where Kode_Item ='"+id+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama");
                       jumlahitem = rs.getString("Jumlah");
                       String hjual =rs.getString("Harga");
                      
                       jTnamaitem.setText(nama);
                       hargajual = Integer.parseInt(hjual);
                       jTjumlah.setText(jumlahitem);
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
          // System.out.println("Koneksi DB Gagal " +e.getMessage());
           //System.exit(0);
    }  
   }
  
   public void cekkode(){
        
    int jumlahBaris = jTreturbeli.getRowCount();
    String kodeitem = jCkodeitem.getSelectedItem().toString();
   
    TableModel tabelModel;
    
    tabelModel = jTreturbeli.getModel();
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
        SimpleDateFormat ff = new SimpleDateFormat("dd-MM-yyyy");
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        
        Date d=null;
    try {
        d = ff.parse(jTtgltransbeli.getText());
    } catch (ParseException ex) {
        Logger.getLogger(ReturPembelian.class.getName()).log(Level.SEVERE, null, ex);
    }
        String tgl = format.format(d);
        
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttempreturbeli values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
             
                st.setString(1, jTnoretur.getText());
                st.setString(2, jTnotransbeli.getText());
                st.setString(3, (String)tanggal);
                st.setString(4, (String)tgl);
                st.setString(5, jTuser.getText());
                st.setString(6, jCidsupplier.getText());
            
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
   
   
  public void updatestatus(){
      String noretur =jTnoretur.getText();
    
      String status ="1";
       try{
           
            conn=koneksidb.getkoneksi();
            String updatestatus ="UPDATE ttempreturbeli SET Status=? WHERE No_Retur = ?";
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
         int baris = jTreturbeli.getRowCount();
         for (int a=0; a<baris; a++)
	{String kodeitem = jTreturbeli.getValueAt(a,7).toString();
         
         String jumlah = jTreturbeli.getValueAt(a,9).toString();
         String notrans = jTnotransbeli.getText();
         String noretur = jTnoretur.getText();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
         
         String stokmasuk = "0" ;
         try{
        conn=koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sqlstok = "SELECT Stok FROM tmaterial WHERE Kode_Item='" +kodeitem+"'"; 
        ResultSet rs=st.executeQuery(sqlstok); 
	while(rs.next()){
            String stokawal = rs.getString(1);
            String insert = "INSERT INTO tstokbarang (No_Transaksi,Tanggal,Kode_Item,Stok_Akhir,Stok_Awal,Stok_Masuk,Stok_Keluar) VALUES( '"+noretur+"','"+tanggal+"','"+kodeitem+"','0','"+stokawal+"','0','0')";
	    
            int stokakhir = Integer.parseInt(stokawal) - Integer.parseInt(jumlah) ;
            String tabelstok ="UPDATE tstokbarang SET Stok_Akhir='" +stokakhir+ "',Stok_Masuk='"+stokmasuk+ "',Stok_Keluar='"+jumlah+ "' WHERE Kode_Item='" + kodeitem + "'";
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
       
     public void simpanketransaksibeli(){
        hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        
       try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into treturbeli values(?,?,?,?,?,?,?,?)";
            
            PreparedStatement st=conn.prepareStatement(sql);
                    
                st.setString(1, jTnoretur.getText());
                st.setString(2, jTnotransbeli.getText());
                st.setString(3, (String)tanggal);
                st.setString(4, jCidsupplier.getText());
                st.setString(5, jTjmltrans.getText());
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
       
    public void hapus() {
    // Konfirmasi sebelum melakukan penghapusan data
    int row = jTreturbeli.getSelectedRow();
   String status = "0";
    String noretur = jTnoretur.getText();
      try {
          //int row = jTreturbeli.getSelectedRow();
          //tabModel.removeRow(row);
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttempreturBeli WHERE No_Retur = ? and Status= ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, noretur);
        st.setString(2, status);
        int rs=st.executeUpdate();
        
      } catch (Exception se) {  // Silahkan tambahkan catch Exception yang lain
         JOptionPane.showMessageDialog(this,"Gagal Hapus Data.. ");
       }
    
  }    
     
    public void cetaktransaksi(){
         String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("noretur", jTnoretur.getText());
        try {

            reportSource = PathReport + "BuktiReturPembelian.jrxml";
            reportDest = PathReport + "BuktiReturPembelian.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }      
    
    public void kosongheader(){
        jTnoretur.setText("");
        jTnotransbeli.setText("");
        jTtgltransbeli.setText("");
        
        jTjumlah.setText("");
        jCidsupplier.setText("");
         jTnamasupplier.setText("");
         jTnamaitem.setText("");
        jCkodeitem.setEnabled(false);
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
        String sql = "DELETE FROM ttempreturbeli WHERE No_Retur = ?";
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
        jButton5 = new javax.swing.JButton();
        jBcari = new javax.swing.JButton();
        jBtambah = new javax.swing.JButton();
        jBhapus = new javax.swing.JButton();
        jBedit = new javax.swing.JButton();
        jTsubtotal = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTketerangan = new javax.swing.JTextField();
        jTjmltrans = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jmlData = new javax.swing.JLabel();
        jBsimpan = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreturbeli = new javax.swing.JTable();
        jBbatal = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTnoretur = new javax.swing.JTextField();
        jTnotransbeli = new javax.swing.JTextField();
        jTuser = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTnamasupplier = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTnamaitem = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jBkodeitem1 = new javax.swing.JButton();
        jBnotransaksibeli = new javax.swing.JButton();
        jDtanggal = new com.toedter.calendar.JDateChooser();
        jCidsupplier = new javax.swing.JTextField();
        jTtgltransbeli = new javax.swing.JTextField();
        jCkodeitem = new javax.swing.JComboBox();
        jTjumlah = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Retur Pembelian Material");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel23.setText("Sub-Total");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        jButton5.setText("Keluar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jBcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Undo.png"))); // NOI18N
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

        jBhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bin_Full.png"))); // NOI18N
        jBhapus.setText("Hapus");
        jBhapus.setEnabled(false);
        jBhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhapusActionPerformed(evt);
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

        jTsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTsubtotal.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText(":");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel18.setText("Keterangan");

        jTketerangan.setEnabled(false);

        jTjmltrans.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTjmltrans.setEnabled(false);

        jLabel20.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel20.setText("Jumlah Retur");

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jBsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jBsimpan.setText("Simpan");
        jBsimpan.setEnabled(false);
        jBsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsimpanActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText(":");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText(":");

        jTreturbeli.setAutoCreateRowSorter(true);
        jTreturbeli.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTreturbeli.setModel(new javax.swing.table.DefaultTableModel(
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
        jTreturbeli.setFocusable(false);
        jTreturbeli.setOpaque(false);
        jTreturbeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreturbeliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTreturbeliMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTreturbeli);

        jBbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Undo.png"))); // NOI18N
        jBbatal.setText("Batal");
        jBbatal.setEnabled(false);
        jBbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbatalActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Retur Pembelian Material", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel24.setText("No Retur");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel25.setText("Supplier");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText(":");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel27.setText("Tanggal");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setText(":");

        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setText(":");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel30.setText("No Trans. Beli");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setText(":");

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

        jTnotransbeli.setEnabled(false);
        jTnotransbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTnotransbeliActionPerformed(evt);
            }
        });
        jTnotransbeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnotransbeliKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTnotransbeliKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnotransbeliKeyTyped(evt);
            }
        });

        jTuser.setEnabled(false);

        jLabel32.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel32.setText("User");

        jLabel33.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel33.setText(":");

        jLabel34.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel34.setText("Nama Supplier");

        jTnamasupplier.setEnabled(false);

        jLabel35.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel35.setText(":");

        jLabel36.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel36.setText(":");

        jLabel38.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel38.setText("Kode Material");

        jTnamaitem.setEnabled(false);

        jLabel39.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel39.setText("Jumlah");

        jLabel40.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel40.setText(":");

        jBkodeitem1.setText(">");
        jBkodeitem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkodeitem1ActionPerformed(evt);
            }
        });

        jBnotransaksibeli.setText("...");
        jBnotransaksibeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnotransaksibeliActionPerformed(evt);
            }
        });

        jDtanggal.setDateFormatString("d-MM-yyyy");
        jDtanggal.setEnabled(false);

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

        jTtgltransbeli.setEnabled(false);

        jCkodeitem.setEnabled(false);
        jCkodeitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCkodeitemActionPerformed(evt);
            }
        });

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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel24)
                            .addComponent(jLabel30))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jDtanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTnoretur))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTnotransbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBnotransaksibeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTtgltransbeli, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel36)
                        .addGap(8, 8, 8)
                        .addComponent(jCkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTnamaitem)))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel33))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel34)
                                .addComponent(jLabel25))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel26)
                                .addComponent(jLabel35))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel40)))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTuser)
                            .addComponent(jCidsupplier)
                            .addComponent(jTnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBkodeitem1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel28)
                            .addComponent(jTnoretur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel27))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jBnotransaksibeli)
                                        .addComponent(jTtgltransbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel30)
                                        .addComponent(jLabel31)
                                        .addComponent(jTnotransbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jDtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jCidsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jTnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(jBkodeitem1)
                        .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel38)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel37.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel37.setText("Jumlah Data : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19))
                            .addComponent(jBedit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jTketerangan))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                .addGap(18, 18, 18)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jmlData)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jTjmltrans, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        hapus();
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jBcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcariActionPerformed
        // TODO add your handling code here:
        DaftarReturPembelian dtb = new DaftarReturPembelian();
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

    private void jBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
            tampilDataKeJTable();
            jmldatatable();
            jBhapus.setEnabled(false);
            hitungsubtotal();
            hitungjum();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
            //          getDatatransaksi();
            //tampilDataKeJTable();
        }
    }//GEN-LAST:event_jBhapusActionPerformed

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();
        try{

           
            //fDB.fAB = this;
            int tabel = jTreturbeli.getSelectedRow();

            fDB.no_retur= jTnoretur.getText();
            fDB.kodeitem= jTreturbeli.getValueAt(tabel, 7).toString();
            fDB.namaitem = jTreturbeli.getValueAt(tabel, 8).toString();
            fDB.jumlah = jTreturbeli.getValueAt(tabel, 9).toString();
            fDB.harga = jTreturbeli.getValueAt(tabel, 10).toString();
            fDB.total= jTreturbeli.getValueAt(tabel, 11).toString();

            fDB.jsimpan.setText("Edit Retur");
            fDB.jTeditkode.setEnabled(false);
            fDB.jTnama.setEnabled(false);
            fDB.jTharga.setEnabled(false);

            fDB.tampildata();
            fDB.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Edit");
            setJTable();
            //       getDatatransaksi();
            //tampilDataKeJTable();
        }
    }//GEN-LAST:event_jBeditActionPerformed

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:

        String noretur = jTnoretur.getText();
        String tanggal = jDtanggal.getDateFormatString();
        String notrans = jTnotransbeli.getText();
        String idsupplier = jCidsupplier.getText();
        String subtotal = jTsubtotal.getText();
        int table = jTreturbeli.getRowCount();

        if(idsupplier.equalsIgnoreCase("")||noretur.equalsIgnoreCase("")||tanggal.equalsIgnoreCase("")||table<1||subtotal.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Data Belum Lengkap");

        }else{

            updatestok();
            updatestatus();
            simpanketransaksibeli();
            
            
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

    private void jTreturbeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreturbeliMouseClicked
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();

        if (evt.getClickCount() == 2) {
            Point pnt = evt.getPoint();
            int row = jTreturbeli.rowAtPoint(pnt);
            //do something

            //fDB.fAB = this;
            int tabel = jTreturbeli.getSelectedRow();

            fDB.no_retur= jTnoretur.getText();
            fDB.kodeitem= jTreturbeli.getValueAt(tabel, 7).toString();
            fDB.namaitem = jTreturbeli.getValueAt(tabel, 8).toString();
            fDB.jumlah = jTreturbeli.getValueAt(tabel, 9).toString();
            fDB.harga = jTreturbeli.getValueAt(tabel, 10).toString();
            fDB.total= jTreturbeli.getValueAt(tabel, 11).toString();

            fDB.jsimpan.setText("Edit Retur");
            fDB.jTeditkode.setEnabled(false);
            fDB.jTnama.setEnabled(false);
            fDB.jTharga.setEnabled(false);

            fDB.tampildata();
            fDB.setVisible(true);

        }
    }//GEN-LAST:event_jTreturbeliMouseClicked

    private void jTreturbeliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreturbeliMouseEntered
        // TODO add your handling code here:
        tampilDataKeJTable();
        //getDatatransaksibeli();
        //getDatareturbeli();
        hitungsubtotal();
        hitungjum();
        jBedit.setEnabled(false);
        jBhapus.setEnabled(false);

    }//GEN-LAST:event_jTreturbeliMouseEntered

    private void jBbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbatalActionPerformed
        // TODO add your handling code here:
        Transaksibatal();
        setdisable();
        kosongheader();
        isidata();
    }//GEN-LAST:event_jBbatalActionPerformed

    private void jTnoreturFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnoreturFocusGained
        // TODO add your handling code here:
        String string = jTnotransbeli.getText();
        jTnotransbeli.setText(string.toUpperCase());

        //setJTable();
        //getNamaSupplier();
        //hitungsubtotal();
        //hitungjum();
        //jmldatatable();
        //jTnoretur.setEnabled(false);
        //jTnotransbeli.setEnabled(false);
    }//GEN-LAST:event_jTnoreturFocusGained

    private void jTnoreturFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnoreturFocusLost
        // TODO add your handling code here:
        String string = jTnoretur.getText();
        String upper = string.toUpperCase();
        jTnoretur.setText(upper);
    }//GEN-LAST:event_jTnoreturFocusLost

    private void jTnoreturKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnoreturKeyPressed
        // TODO add your handling code here:
                if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            setJTable();
        tampilDataKeJTable();
        }
    }//GEN-LAST:event_jTnoreturKeyPressed

    private void jTnoreturKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnoreturKeyTyped
        // TODO add your handling code here:
        int cektabel = jTreturbeli.getRowCount();
        hapusIsiJTable();
        setJTable();
        //        getDatatransaksi();
        getNamaSupplier();
        //tampilDataKeJTable();
        hitungsubtotal();
        hitungjum();

        if(cektabel==0){
            jCidsupplier.setText("");

            jTnamasupplier.setText("");
        }else{
            jTnotransbeli.setEnabled(false);

        }
    }//GEN-LAST:event_jTnoreturKeyTyped

    private void jTnotransbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTnotransbeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTnotransbeliActionPerformed

    private void jTnotransbeliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnotransbeliKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            String cek = jTnotransbeli.getText();
            int table = jTreturbeli.getRowCount();
            if (cek.equals("")){

            }else{

                String string = jTnotransbeli.getText();
                jTnotransbeli.setText(string.toUpperCase());

                getDatatransaksibeli();
                //getDatareturbeli();
                getNamaSupplier();
                //hitungsubtotal();
                //hitungjum();
                //jmldatatable();
                jTnoretur.setEnabled(false);
                jCkodeitem.setEnabled(true);
            }

        }
    }//GEN-LAST:event_jTnotransbeliKeyPressed

    private void jTnotransbeliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnotransbeliKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTnotransbeliKeyReleased

    private void jTnotransbeliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnotransbeliKeyTyped
        // TODO add your handling code here:
        String string = jTnotransbeli.getText();
        jTnotransbeli.setText(string.toUpperCase());
    }//GEN-LAST:event_jTnotransbeliKeyTyped

    private void jBkodeitem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkodeitem1ActionPerformed
        // TODO add your handling code here:

        String notrans = jTnoretur.getText();
        String tglpesan = jDtanggal.getDateFormatString();
        String idsupp = jCidsupplier.getText();
        String jumlah = jTjumlah.getText();
        String kodeitem = jCkodeitem.getSelectedItem().toString();
        int y = Integer.parseInt(jumlahitem);
        int x = Integer.parseInt(jumlah);
        if(kodeitem.equalsIgnoreCase("")||idsupp.equalsIgnoreCase("")||notrans.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
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
            jTnotransbeli.setEnabled(false);
            jTnoretur.setEnabled(false);
            }
        }

    }//GEN-LAST:event_jBkodeitem1ActionPerformed

    private void jBnotransaksibeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnotransaksibeliActionPerformed
        // TODO add your handling code here:
        DaftarTransaksi dp = new DaftarTransaksi();
        dp.jButton5.setVisible(false);
        dp.setTitle("");
        dp.setVisible(true);
        javax.swing.JPanel panelPop = new javax.swing.JPanel();

        panelPop.add(dp).setVisible(true);

        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Transaksi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getData(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }

        }
        private void getData(String notransaksi){
            jTnotransbeli.setText(notransaksi);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                setenable();
                jCkodeitem.setEnabled(true);
                jBnotransaksibeli.setEnabled(false);
                                
            try {
                
                conn =koneksidb.getkoneksi();
                Statement st= conn.createStatement();
                String sql="Select * from ttemptransaksibeli where No_Transaksi ='"+notransaksi+"'";
                ResultSet rs=st.executeQuery(sql);
                // Menampilkan ke JTable  melalui tabModel
                String notrans,user,idsupplier,kodeitem,nama,harga;
                int no=0;
                while(rs.next()){
                    no=no+1;
                    notrans=rs.getString("No_Transaksi");
                    Date tglpesan=rs.getDate("Tgl_Pesan");
                    user=rs.getString("User");
                    idsupplier=rs.getString("ID_Supplier");
                    kodeitem=rs.getString("Kode_Item");
                    nama=rs.getString("Nama");
                    //jumlahitem=rs.getString("Jumlah");
                    harga=rs.getString("Harga");
                    
                    String tgl = sdf.format(tglpesan);
                    jTtgltransbeli.setText(tgl);
                    jTuser.setText(user);
                    jCidsupplier.setText(idsupplier);
                    jCkodeitem.addItem(kodeitem);
                    jBnotransaksibeli.setEnabled(false);
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

            try{
                String id = jCidsupplier.getText();
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
            
    }//GEN-LAST:event_jBnotransaksibeliActionPerformed

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

    private void jCkodeitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCkodeitemActionPerformed
        // TODO add your handling code here:
        ambildata();
        String string = jTnamaitem.getText();
        String upper = string.toUpperCase();
        jTnamaitem.setText(upper);
    }//GEN-LAST:event_jCkodeitemActionPerformed

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
        String idsupp = jCidsupplier.getText();
        String jumlah = jTjumlah.getText();
        String kodeitem = jCkodeitem.getSelectedItem().toString();
        int y = Integer.parseInt(jumlahitem);
        int x = Integer.parseInt(jumlah);
        if(kodeitem.equalsIgnoreCase("")||idsupp.equalsIgnoreCase("")||notrans.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
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
            jTnotransbeli.setEnabled(false);
            jTnoretur.setEnabled(false);
            }
        }
        }
    }//GEN-LAST:event_jTjumlahKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbatal;
    private javax.swing.JButton jBcari;
    private javax.swing.JButton jBedit;
    private javax.swing.JButton jBhapus;
    private javax.swing.JButton jBkodeitem1;
    private javax.swing.JButton jBnotransaksibeli;
    private javax.swing.JButton jBsimpan;
    private javax.swing.JButton jBtambah;
    private javax.swing.JButton jButton5;
    private javax.swing.JTextField jCidsupplier;
    public javax.swing.JComboBox jCkodeitem;
    private com.toedter.calendar.JDateChooser jDtanggal;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTjmltrans;
    private javax.swing.JTextField jTjumlah;
    private javax.swing.JTextField jTketerangan;
    private javax.swing.JTextField jTnamaitem;
    private javax.swing.JTextField jTnamasupplier;
    public javax.swing.JTextField jTnoretur;
    private javax.swing.JTextField jTnotransbeli;
    public javax.swing.JTable jTreturbeli;
    private javax.swing.JTextField jTsubtotal;
    private javax.swing.JTextField jTtgltransbeli;
    public javax.swing.JTextField jTuser;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
