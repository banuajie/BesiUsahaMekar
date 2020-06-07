/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Penjualan;

import MasterData.MasterMaterial;
import Pembelian.DaftarMaterialMasuk;
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

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import besiusahamekar.koneksidb;

/**
 *
 * @author Banuajie
 */
public class MaterialKeluar extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;

String harga,stotal;
int hargajual;
String tampilan = "dd-MM-yyyy";
public String idsupplier,nopes,no_transaksi;
String notra,kodebarang,status="0";
 int val = 0 ;

    public String getnopes() {
        return nopes;
    }
        public String getnotrans() {
        return no_transaksi;
    }
    /**
     * Creates new form MaterialKeluar2
     */
    public MaterialKeluar() {
        initComponents();
        isidata();
    }
    

    private void setJTable() {
    String [] JudulKolom={"No","No_Transaksi","Tanggal","User","Kode Material","Nama Item","Jumlah","Harga","Total","Keterangan","Status"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTitemkeluar.setModel(tabModel);
    jTitemkeluar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTitemkeluar.getColumnModel().getColumn(0).setPreferredWidth(35);
    jTitemkeluar.getColumnModel().getColumn(1).setMinWidth(0);
    jTitemkeluar.getColumnModel().getColumn(1).setMaxWidth(0);
    jTitemkeluar.getColumnModel().getColumn(1).setWidth(0);
    jTitemkeluar.getColumnModel().getColumn(2).setMinWidth(0);
    jTitemkeluar.getColumnModel().getColumn(2).setMaxWidth(0);
    jTitemkeluar.getColumnModel().getColumn(2).setWidth(0);
    jTitemkeluar.getColumnModel().getColumn(3).setMinWidth(0);
    jTitemkeluar.getColumnModel().getColumn(3).setMaxWidth(0);
    jTitemkeluar.getColumnModel().getColumn(3).setWidth(0); 
    jTitemkeluar.getColumnModel().getColumn(4).setPreferredWidth(140);
    jTitemkeluar.getColumnModel().getColumn(5).setPreferredWidth(215);
    jTitemkeluar.getColumnModel().getColumn(6).setPreferredWidth(60);
    jTitemkeluar.getColumnModel().getColumn(7).setPreferredWidth(110);
    jTitemkeluar.getColumnModel().getColumn(8).setPreferredWidth(110);
    jTitemkeluar.getColumnModel().getColumn(9).setPreferredWidth(110);
    jTitemkeluar.getColumnModel().getColumn(10).setMinWidth(0);
    jTitemkeluar.getColumnModel().getColumn(10).setMaxWidth(0);
    jTitemkeluar.getColumnModel().getColumn(10).setWidth(0);
    

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
   public void getDatamasuk() {
        // import java.sql.connection
        String notrans = jTnotrans.getText();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");                 
    try {
            hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttempmaterialkeluar where No_Transaksi ='"+notrans+"'";
            ResultSet rs=st.executeQuery(sql);
            // Menampilkan ke JTable  melalui tabModel
                      String notransaksi,user,kodeitem,nama,jumlah,harga,total,keterangan,status;
            int no=0;
                   while(rs.next()){
                       no=no+1;
                       notransaksi=rs.getString("No_Transaksi");
                       Date tanggal=rs.getDate("Tanggal");
                       user=rs.getString("User");
                       kodeitem=rs.getString("Kode_Item");
                       nama=rs.getString("Nama");
                       jumlah=rs.getString("Jumlah");
                       harga=rs.getString("Harga");
                       total=rs.getString("Total");
                       keterangan=rs.getString("Keterangan");
                       status=rs.getString("Status");
                      
                       
         Object Data[]={no,notransaksi,tanggal,user,kodeitem,nama,jumlah,harga,total,keterangan,status};
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
    
  
    
 
  public void tampildata(){
       
       jTnotrans.setText(no_transaksi);
      
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
                       String hjual =rs.getString("Harga_Jual");
                      
                       jTnamaitem.setText(nama);
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
   

   
    public void simpanData(){

        hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into ttempmaterialkeluar values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
             
                st.setString(1, jTnotrans.getText());
                st.setString(2, (String)tanggal);
                st.setString(3, jTuser.getText());
                st.setString(4, jTkodeitem.getText());
                st.setString(5, jTnamaitem.getText());
                st.setString(6, jTjml.getText());
                st.setString(7, harga);
                st.setString(8, stotal);
                st.setString(9, jTketerangan.getText());
                st.setString(10, "0");
                
            int rs=st.executeUpdate();
            if(rs>0){
            //JOptionPane.showMessageDialog(this,"Input Berhasil");
      	    
                getDatamasuk();
                       
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
         int rows = jTitemkeluar.getRowCount();
         String jmls = String.valueOf(rows);
         jmlData.setText(jmls);
         
             
   } 
    
public void hitungtotal(){
            String sjml = jTjml.getText();
            int jml = Integer.parseInt(sjml);
            harga = String.valueOf(hargajual);
            int total = hargajual*jml;
            stotal = String.valueOf(total);
}

public void hitungsubtotal(){
    int jumlahBaris = jTitemkeluar.getRowCount();
    int totalBiaya = 0;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTitemkeluar.getModel();
    for (int i=0; i<jumlahBaris; i++){
        int jumlah = Integer.parseInt(tabelModel.getValueAt(i, 6).toString());
        int harga = Integer.parseInt(tabelModel.getValueAt(i, 7).toString());
        total = jumlah*harga;
        totalBiaya = totalBiaya + total;
        
    }
    jTsubtotal.setText(String.valueOf(totalBiaya));
   }
   
    public void hitungjum(){
    int jumlahBaris = jTitemkeluar.getRowCount();
    
    int jumlah;
    int total=0;
    TableModel tabelModel;
    tabelModel = jTitemkeluar.getModel();
    for (int i=0; i<jumlahBaris; i++){
        jumlah = Integer.parseInt(tabelModel.getValueAt(i, 6).toString());
        
        total = total+jumlah;
    }
    
    jTjmltrans.setText(String.valueOf(total));
   }
    
    public void cekkode(){
        
    int jumlahBaris = jTitemkeluar.getRowCount();
    String kodeitem = jTkodeitem.getText();
    
    String kode=null;
    TableModel tabelModel;
    
    tabelModel = jTitemkeluar.getModel();
    for (int i=0; i<jumlahBaris; i++){
        kode = tabelModel.getValueAt(i, 4).toString();
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
    
     
     public void hapus_Data() {
    // Konfirmasi sebelum melakukan penghapusan data
    int row = jTitemkeluar.getSelectedRow();
    kodebarang = tabModel.getValueAt(row, 4).toString();
    notra = tabModel.getValueAt(row, 1).toString();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus = '" + kodebarang + "' Transaksi = '" + notra + 
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttempmaterialkeluar WHERE Kode_Item = ? and No_Transaksi = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, kodebarang);
        st.setString(2, notra);
        int rs=st.executeUpdate();
        if(rs>0){
       // tampilDataKeJTable();
         getDatamasuk();
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
           jDtanggal.setDate(new Date());
     }
    
     public void setenable(){
         jTjml.setEnabled(true);
        
         jDtanggal.setEnabled(true);
         jTnotrans.setEnabled(true);
              
                        jTkodeitem.setEnabled(true);
                        jBkodeitem.setEnabled(true);
                                jBcari.setEnabled(false);
                                        jBjumlah.setEnabled(true);
                                        jTitemkeluar.setEnabled(true);
                                        jBtambah.setEnabled(false);
                                        jBsimpan.setEnabled(true);
                                        jBbatal.setEnabled(true);
                                       
                                       jTketerangan.setEnabled(true);
                                        
     }
     
          public void setdisable(){
         notransaksi();
          jTjml.setEnabled(false);
         jDtanggal.setDate(null);
        jTnotrans.setEnabled(false);  
        
        jDtanggal.setEnabled(false);
        
                        jTkodeitem.setEnabled(false);
                        jBkodeitem.setEnabled(false);
                                
                                jBcari.setEnabled(false);
                                        jBjumlah.setEnabled(false);
                                        jTitemkeluar.setEnabled(false);
                                        jBtambah.setEnabled(true);
                                        jBsimpan.setEnabled(false);
                                        jBbatal.setEnabled(false);
                                        jTketerangan.setEnabled(false);
     }

   
   public void notransaksi(){
        
        Date sk = new Date();
        SimpleDateFormat format1=new SimpleDateFormat("ddMMyyyy");
        String time = format1.format(sk);
        try{
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sql= "select right(No_Transaksi,3) as kd from ttempmaterialkeluar where No_Transaksi Like '%" +time+ "%' order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTnotrans.setText("NMKL"+time+"000"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTnotrans.setText("NMKL"+time+"00"+Integer.toString(kode));   
                else if(kode>=1000&&kode<=9999) 
                    jTnotrans.setText("NMKL"+time+"0"+Integer.toString(kode));
                else
                    jTnotrans.setText("NMKL"+time+"0000"+Integer.toString(kode));
            }else{

                int kode = 1;

                jTnotrans.setText("NMKL"+time+"0000"+Integer.toString(kode));

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

    public void simpanketempmasuk(){
//Connection conn;
      String notrans =jTnotrans.getText();
     try{
            conn=koneksidb.getkoneksi();
            Statement state = conn.createStatement();
            int baris = jTitemkeluar.getRowCount();
            
		for (int a=0; a<baris; a++)
		{
                
                        
		String query = "INSERT INTO ttempmaterialkeluar (No_Transaksi,Tanggal,User,Kode_Item,Nama,Jumlah,Harga,Total,Keterangan,Status) VALUES( '"+jTitemkeluar.getValueAt(a,1)+"','"+jTitemkeluar.getValueAt(a,2)+"','"+jTitemkeluar.getValueAt(a,3)+"','"+jTitemkeluar.getValueAt(a,4)+"','"+jTitemkeluar.getValueAt(a,5)+"','"+jTitemkeluar.getValueAt(a,6)+"','"+jTitemkeluar.getValueAt(a,7)+"','"+jTitemkeluar.getValueAt(a,8)+"','"+jTitemkeluar.getValueAt(a,9)+"','"+jTitemkeluar.getValueAt(a,10)+"')";
		state.executeUpdate(query);
			}
           state.close();
           //JOptionPane.showMessageDialog(null,"Data berhasil disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE);
            //hapusdatadb();
           getDatamasuk();
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }         int col = tabModel.getColumnCount();     
  
  }
   
    public void updatestatus(){
    
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update tmaterialkeluar set Status= ? WHERE No_Transaksi = ?";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
       try {
                     
           p.setString(1, "1");
           p.setString(2, jTnotrans.getText());
           
           
           p.executeUpdate();
           int rs= p.executeUpdate();
          
           if(rs>=0){ }

         // Memanggil Method   tampilDataKeJTable();
        
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
    }catch (SQLException se) {}
    
        
    
    }
    
    
        
        private void cekstatus() {
              String notrans = jTnotrans.getText();
             
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String pesan="Select Status from tmaterialkeluar where No_Transaksi ='"+notrans+"'";
            ResultSet rs=st.executeQuery(pesan);
                
            while(rs.next()){
            String cekstatus = rs.getString(1);
            if(cekstatus.equals(null))
                status="0";
                else
                status = cekstatus;
        }}catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
        }catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);}    

    }
      
     public void kosongheader(){
              
              jTnotrans.setText("");
             
              jTkodeitem.setText("");
              jBcari.setEnabled(true);
          }
   
  
     public void cekkodeitemtabel(){
         int baris = jTitemkeluar.getRowCount();
         for (int a=0; a<baris; a++)
		{String kodeitem = jTitemkeluar.getValueAt(a,4).toString();
                
                }
     }
     
     
     public void updatestok(){
         //String kodeitem = jTkodeitem.getText();
         int baris = jTitemkeluar.getRowCount();
         for (int a=0; a<baris; a++)
	{String kodeitem = jTitemkeluar.getValueAt(a,4).toString();
         
         String jumlah = jTitemkeluar.getValueAt(a,6).toString();
         String notrans = jTnotrans.getText();
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
            String insert = "INSERT INTO tstokbarang (No_Transaksi,Tanggal,Kode_Item,Stok_Akhir,Stok_Awal,Stok_Masuk,Stok_Keluar) VALUES( '"+notrans+"','"+tanggal+"','"+kodeitem+"','0','"+stokawal+"','0','0')";
	    
            int stokakhir =  Integer.parseInt(stokawal) - Integer.parseInt(jumlah);
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
     
     
     public void simpankeitemmasuk(){
        hitungtotal();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(format.format(jDtanggal.getDate()));
        
       try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into tmaterialkeluar values(?,?,?,?,?,?)";
            
            PreparedStatement st=conn.prepareStatement(sql);
                    
                st.setString(1, jTnotrans.getText());
                st.setString(2, (String)tanggal);
                st.setString(3, jTjmltrans.getText());
                st.setString(4, jTsubtotal.getText());
                st.setString(5, jTketerangan.getText());
                st.setString(6, "1");               
            int rs=st.executeUpdate();

            if(rs>0){
            JOptionPane.showMessageDialog(this,"Berhasil disimpan");
      	                           
            }
            st.close();
           
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }
   }
     
     public void Transaksibatal() {
    // Konfirmasi sebelum melakukan penghapusan data
            String notransaksi = jTnotrans.getText();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Membatalkan Transaksi = '" + notransaksi +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttempmaterialkeluar WHERE No_Transaksi = ?";
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
        jLabel18 = new javax.swing.JLabel();
        jBtambah = new javax.swing.JButton();
        jBsimpan = new javax.swing.JButton();
        jTketerangan = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jBbatal = new javax.swing.JButton();
        jmlData = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jBkeluar = new javax.swing.JButton();
        jBhapus = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTnotrans = new javax.swing.JTextField();
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
        jTkodeitem = new javax.swing.JTextField();
        jDtanggal = new com.toedter.calendar.JDateChooser();
        jBcari = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTsubtotal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jBedit = new javax.swing.JButton();
        jTjmltrans = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTitemkeluar = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setTitle("Material Keluar");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel18.setText("Keterangan");

        jBtambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jBtambah.setText("Tambah");
        jBtambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtambahActionPerformed(evt);
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

        jTketerangan.setEnabled(false);

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

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel20.setText("Jumlah Transaksi");

        jBkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        jBkeluar.setText("Keluar");
        jBkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkeluarActionPerformed(evt);
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Material Keluar", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("No Transaksi");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel7.setText("Tanggal");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText(":");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jTnotrans.setEnabled(false);
        jTnotrans.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTnotransFocusLost(evt);
            }
        });
        jTnotrans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnotransKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnotransKeyTyped(evt);
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

        jBkodeitem.setText("jButton1");
        jBkodeitem.setEnabled(false);
        jBkodeitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBkodeitemActionPerformed(evt);
            }
        });

        jTnamaitem.setEnabled(false);

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

        jDtanggal.setDateFormatString("dd-MM-yyyy");
        jDtanggal.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTnamaitem)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTjml)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(jTnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jTuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jBkodeitem)
                    .addComponent(jTnamaitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTkodeitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTjml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBjumlah))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.gif"))); // NOI18N
        jBcari.setText("Cari");
        jBcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcariActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel23.setText("Sub-Total");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText(":");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText(":");

        jTsubtotal.setEnabled(false);

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

        jTjmltrans.setEnabled(false);

        jTitemkeluar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTitemkeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode", "Nama Item", "Jumlah", "Harga", "Total", "Keterangan"
            }
        ));
        jTitemkeluar.setFocusable(false);
        jTitemkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTitemkeluarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTitemkeluarMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTitemkeluar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jmlData))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBedit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBhapus))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBtambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBsimpan)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jBbatal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBcari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBkeluar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jTjmltrans, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(jTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(23, 23, 23)
                        .addComponent(jTketerangan)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBedit)
                    .addComponent(jBhapus)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jTjmltrans, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel23)
                        .addComponent(jTsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtambah)
                    .addComponent(jBbatal)
                    .addComponent(jBcari)
                    .addComponent(jBsimpan)
                    .addComponent(jBkeluar))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtambahActionPerformed
        // TODO add your handling code here:
        setenable();
        hariini();
        isidata();
    }//GEN-LAST:event_jBtambahActionPerformed

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:

        String notrans = jTnotrans.getText();
        String tglpesan = jDtanggal.getDateFormatString();

        int table = jTitemkeluar.getRowCount();

        if(notrans.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")||table<1){
            JOptionPane.showMessageDialog(this, "Data Belum Lengkap");

        }else{
            cekstatus();

            if(status.equals("1")){
                JOptionPane.showMessageDialog(this,"Data Sudah Masuk Transaksi");
                jBsimpan.setEnabled(false);

            }else{
                updatestok();
                simpankeitemmasuk();
                setdisable();
                kosongheader();
                isidata();
            }

        }
    }//GEN-LAST:event_jBsimpanActionPerformed

    private void jBbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbatalActionPerformed
        // TODO add your handling code here:
        Transaksibatal();
        setdisable();
        kosongheader();
        isidata();
    }//GEN-LAST:event_jBbatalActionPerformed

    private void jBkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBkeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBkeluarActionPerformed

    private void jBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
            jmldatatable();
            jBhapus.setEnabled(false);
            //cekkode();
            getDatamasuk();

            hitungsubtotal();
            hitungjum();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
            getDatamasuk();
            //tampilDataKeJTable();
        }
    }//GEN-LAST:event_jBhapusActionPerformed

    private void jTnotransFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnotransFocusLost
        // TODO add your handling code here:
        String string = jTnotrans.getText();
        String upper = string.toUpperCase();
        jTnotrans.setText(upper);
    }//GEN-LAST:event_jTnotransFocusLost

    private void jTnotransKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnotransKeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            int cektabel = jTitemkeluar.getRowCount();
            hapusIsiJTable();
            setJTable();
            getDatamasuk();

            hitungsubtotal();
            hitungjum();

            if(cektabel==0){

                jBsimpan.setEnabled(true);
            }else{

                jBsimpan.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jTnotransKeyPressed

    private void jTnotransKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnotransKeyTyped
        // TODO add your handling code here:
        int cektabel = jTitemkeluar.getRowCount();
        hapusIsiJTable();
        setJTable();
        getDatamasuk();

        hitungsubtotal();
        hitungjum();
    }//GEN-LAST:event_jTnotransKeyTyped

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
        
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Data Material", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getData(dp.getTitle());
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
                       String hjual =rs.getString("Harga_Jual");
                       
                       jTkodeitem.setText(kode);
                       jTnamaitem.setText(nama);
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
    }//GEN-LAST:event_jBkodeitemActionPerformed

    private void jBjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBjumlahActionPerformed
        // TODO add your handling code here:

        String notrans = jTnotrans.getText();
        String tglpesan = jDtanggal.getDateFormatString();

        String jumlah = jTjml.getText();
        String kodeitem = jTkodeitem.getText();

        if(notrans.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            // setJTable();

        }else{

            cekkode();
            getDatamasuk();
            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jTnotrans.setEnabled(false);
        }

    }//GEN-LAST:event_jBjumlahActionPerformed

    private void jTkodeitemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkodeitemKeyPressed
        // TODO add your handling code here:jTnamaitem.setText("");
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

    private void jBcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcariActionPerformed
        // TODO add your handling code here:
         DaftarMaterialKeluar dtb = new DaftarMaterialKeluar();
        this.getParent().add(dtb);
        dtb.setVisible(true);
    }//GEN-LAST:event_jBcariActionPerformed

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:
        try{
            EditDataTabel fDB = new EditDataTabel();
            int tabel = jTitemkeluar.getSelectedRow();

            fDB.no_transaksi = jTitemkeluar.getValueAt(tabel, 1).toString();
            fDB.kodeitem = jTitemkeluar.getValueAt(tabel, 4).toString();
            fDB.namaitem = jTitemkeluar.getValueAt(tabel, 5).toString();
            fDB.jumlah = jTitemkeluar.getValueAt(tabel, 6).toString();
            fDB.harga = jTitemkeluar.getValueAt(tabel, 7).toString();
            fDB.total= jTitemkeluar.getValueAt(tabel, 8).toString();

            fDB.jsimpan.setText("Edit Keluar");
            fDB.tampildata();
            fDB.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Edit");
            setJTable();
            getDatamasuk();
            //tampilDataKeJTable();
        }
    }//GEN-LAST:event_jBeditActionPerformed

    private void jTitemkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTitemkeluarMouseClicked
        // TODO add your handling code here:
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        EditDataTabel fDB = new EditDataTabel();

        if (evt.getClickCount() == 2) {
            Point pnt = evt.getPoint();
            int row = jTitemkeluar.rowAtPoint(pnt);
            //do something

            int tabel = jTitemkeluar.getSelectedRow();

            fDB.no_transaksi = jTitemkeluar.getValueAt(tabel, 1).toString();
            fDB.kodeitem = jTitemkeluar.getValueAt(tabel, 4).toString();
            fDB.namaitem = jTitemkeluar.getValueAt(tabel, 5).toString();
            fDB.jumlah = jTitemkeluar.getValueAt(tabel, 6).toString();
            fDB.harga = jTitemkeluar.getValueAt(tabel, 7).toString();
            fDB.total= jTitemkeluar.getValueAt(tabel, 8).toString();

            fDB.jsimpan.setText("Edit Keluar");
            fDB.tampildata();
            fDB.setVisible(true);

        }
    }//GEN-LAST:event_jTitemkeluarMouseClicked

    private void jTitemkeluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTitemkeluarMouseEntered
        // TODO add your handling code here:
        getDatamasuk();
        hitungsubtotal();
        hitungjum();
        jBedit.setEnabled(false);
        jBhapus.setEnabled(false);
    }//GEN-LAST:event_jTitemkeluarMouseEntered

    private void jTjmlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjmlKeyTyped
        // TODO add your handling code here:
                           char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_jTjmlKeyTyped

    private void jTjmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjmlKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            
        String notrans = jTnotrans.getText();
        String tglpesan = jDtanggal.getDateFormatString();

        String jumlah = jTjml.getText();
        String kodeitem = jTkodeitem.getText();

        if(notrans.equalsIgnoreCase("")||kodeitem.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||tglpesan.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            // setJTable();

        }else{

            cekkode();
            getDatamasuk();
            jmldatatable();
            hitungsubtotal();
            hitungjum();
            jTnotrans.setEnabled(false);
        }

        }
    }//GEN-LAST:event_jTjmlKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbatal;
    private javax.swing.JButton jBcari;
    private javax.swing.JButton jBedit;
    private javax.swing.JButton jBhapus;
    private javax.swing.JButton jBjumlah;
    private javax.swing.JButton jBkeluar;
    private javax.swing.JButton jBkodeitem;
    private javax.swing.JButton jBsimpan;
    private javax.swing.JButton jBtambah;
    private com.toedter.calendar.JDateChooser jDtanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTitemkeluar;
    private javax.swing.JTextField jTjml;
    private javax.swing.JTextField jTjmltrans;
    private javax.swing.JTextField jTketerangan;
    private javax.swing.JTextField jTkodeitem;
    private javax.swing.JTextField jTnamaitem;
    private javax.swing.JTextField jTnotrans;
    private javax.swing.JTextField jTsubtotal;
    public javax.swing.JTextField jTuser;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
