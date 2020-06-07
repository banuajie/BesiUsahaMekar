/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MasterData;

import java.awt.event.KeyEvent;
import besiusahamekar.koneksidb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Banuajie
 */
public final class TambahMaterial extends javax.swing.JFrame {
public DefaultTableModel tabModel;

Connection conn;
String formattedNumber;
String hargajual;
    //Fields for data entry
   
    //Formats to format and parse numbers
    
    
    /**
     * Creates new form TambahMaterial
     */
    public TambahMaterial() {
        initComponents();
        jTabinfo.setSelectedIndex(0);
       
        nokodeitem();
        setJTable();
       
        getDatajenis();
        getDatasatuan();
        getNamaSupplier();
        cekdatatabel();
        jmldatatable();
        onsimpan();
        setenable();
        
 }
   
public void setenable(){
               
                jTkodebarang.setEnabled(true);
              jTnamabarang.setEnabled(true);
              jThargajualbarang.setEnabled(true);
              jThargapokokbarang.setEnabled(true);
              jTstokbarang.setEnabled(true);
              jTketeranganbarang.setEnabled(true);
              jCjenisbarang.setEnabled(true);
              jCsatuanbarang.setEnabled(true);
            
              jTketeranganbarang.setEnabled(true);
              jCidsupplier.setEnabled(true);
              jTnamasupplier.setEnabled(true);
            
}

public void setdisable(){
   
    jTnamabarang.setEnabled(false);
              jThargajualbarang.setEnabled(false);
              jThargapokokbarang.setEnabled(false);
              jTstokbarang.setEnabled(false);
              jTketeranganbarang.setEnabled(false);
              
              jTketeranganbarang.setEnabled(false);
    
    
}


    private void setJTable() {
    String [] JudulKolom={"Kode Material","Tipe Item","Nama Item","Jenis","Stok","Satuan","Harga Pokok","Harga Jual","Keterangan","ID Supplier","Nama Supplier"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTdaftarbarang.setModel(tabModel);
   
    jTdaftarbarang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTdaftarbarang.getColumnModel().getColumn(0).setPreferredWidth(120);
    //jTdaftarbarang.getColumnModel().getColumn(1).setPreferredWidth(150);
    jTdaftarbarang.getColumnModel().getColumn(1).setMinWidth(0);
    jTdaftarbarang.getColumnModel().getColumn(1).setMaxWidth(0);
    jTdaftarbarang.getColumnModel().getColumn(1).setWidth(0);
    jTdaftarbarang.getColumnModel().getColumn(2).setPreferredWidth(200);
    jTdaftarbarang.getColumnModel().getColumn(3).setPreferredWidth(150);
    jTdaftarbarang.getColumnModel().getColumn(4).setPreferredWidth(50);
    jTdaftarbarang.getColumnModel().getColumn(5).setPreferredWidth(120);
    jTdaftarbarang.getColumnModel().getColumn(6).setPreferredWidth(100);
    jTdaftarbarang.getColumnModel().getColumn(7).setPreferredWidth(100);    
    jTdaftarbarang.getColumnModel().getColumn(8).setPreferredWidth(200);
    jTdaftarbarang.getColumnModel().getColumn(9).setPreferredWidth(100);    
    jTdaftarbarang.getColumnModel().getColumn(10).setPreferredWidth(200);
    getDatabarang();
       }
   
  
   private void getDatabarang() {
        // import java.sql.connection
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from temptmaterial";
            ResultSet rs=st.executeQuery(sql);

                // Menampilkan ke JTable  melalui tabModel
                     String kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       kodeitem=rs.getString("Kode_Item");
                       tipeitem=rs.getString("Tipe_Item");
                       namaitem=rs.getString("Nama_Item");
                       jenis=rs.getString("Jenis");
                       stok=rs.getString("Stok");
                       satuan=rs.getString("Satuan");
                       hargapokok=rs.getString("Harga_Pokok");
                       hargajual=rs.getString("Harga_Jual");
                       keterangan=rs.getString("Keterangan");
                       idsupplier=rs.getString("ID_Supplier");
                       namasupplier=rs.getString("Nama_Supplier");
        
         Object Data[]={kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier};
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
  
 
   
  private void getDatajenis() {
        // import java.sql.connection
    
           try{
            jCjenisbarang.removeAllItems();
            
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select kode from jenismaterial";
            ResultSet rs=st.executeQuery(sql);

                
                        while(rs.next()){
                        String kodejenis = rs.getString("kode"); 
                        jCjenisbarang.addItem(kodejenis);
                         
      
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
   
 private void getDatasatuan() {
        // import java.sql.connection
            
           try{
            jCsatuanbarang.removeAllItems();
           
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select kode from satuanmaterial";
            ResultSet rs=st.executeQuery(sql);

               
                        while(rs.next()){
                       String kodesatuan = rs.getString("kode"); // Get the Id
                       jCsatuanbarang.addItem(kodesatuan);
                    
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
//Connection conn;
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into temptmaterial values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
                st.setString(1, jTkodebarang.getText());
                st.setString(2, "material");
                st.setString(3, jTnamabarang.getText());
                st.setString(4, jCjenisbarang.getSelectedItem().toString());
                st.setString(5, jTstokbarang.getText());
                st.setString(6, jCsatuanbarang.getSelectedItem().toString());
                st.setString(7, jThargapokokbarang.getText());
                st.setString(8, jThargajualbarang.getText());
                st.setString(9, jTketeranganbarang.getText());
                st.setString(10, jCidsupplier.getText().toString());
                st.setString(11, jTnamasupplier.getText());
                
            int rs=st.executeUpdate();

            if(rs>0){
            //JOptionPane.showMessageDialog(this,"Input Berhasil");
      	    
            tampilDataKeJTable();
            
            kosongkanfield();
            }
            st.close();
           
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }
  }
   
 
 
  private void kosongkanfield(){
  
        //jTkodebarang.setText("");
        jTnamabarang.setText("");
        jThargajualbarang.setText("");
        jThargapokokbarang.setText("");
        jTstokbarang.setText("");
        jTketeranganbarang.setText("");
        jCidsupplier.setText("");
        jTnamasupplier.setText("");
        
        
     
        //jCjenisbarang.getSelectedItem().toString();
        //jCsatuanbarang.getSelectedItem().toString();    
      
  }
   
  private void hapusIsiJTable() {

         int row = tabModel.getRowCount();
         for (int i = 0; i < row; i++) {
         tabModel.removeRow(0);
         }
         
        
  }
  
  public void ambilData_dari_JTable() {
        int row = jTdaftarbarang.getSelectedRow();

         // Mengambil data yang dipilih pada JTable
        String kodebarang = tabModel.getValueAt(row, 0).toString();
        String tipeitem = tabModel.getValueAt(row, 1).toString();
        String namabarang = tabModel.getValueAt(row, 2).toString();
        String jenisbarang = tabModel.getValueAt(row, 3).toString();
        String stokbarang = tabModel.getValueAt(row, 4).toString();
        String satuanbarang = tabModel.getValueAt(row, 5).toString();
        String hargapokok = tabModel.getValueAt(row, 6).toString();
        String hargajual = tabModel.getValueAt(row, 7).toString();
        String keterangan = tabModel.getValueAt(row, 8).toString();
        String idsupplier = tabModel.getValueAt(row, 9).toString();
        String namasupplier = tabModel.getValueAt(row, 10).toString();
        
            jTkodebarang.setText(kodebarang);
            jTnamabarang.setText(namabarang);
            jThargajualbarang.setText(hargajual);
            jThargapokokbarang.setText(hargapokok);
            jTstokbarang.setText(stokbarang);
            jTketeranganbarang.setText(keterangan);
            jCidsupplier.setText(idsupplier);
            jTnamasupplier.setText(namasupplier);
            jCjenisbarang.getEditor().setItem(jenisbarang);
            jCsatuanbarang.getEditor().setItem(satuanbarang);
     
  }
  
  
  
  public void hapus_Data() {
    // Konfirmasi sebelum melakukan penghapusan data
    ambilData_dari_JTable();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus = '" + jTkodebarang.getText() +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM temptmaterial WHERE kode_item = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, jTkodebarang.getText());
        int rs=st.executeUpdate();
        if(rs>0){
        tampilDataKeJTable();
        
        setJTable();
        //JOptionPane.showMessageDialog(this,"Data Sudah dihapus");
        }
        
        kosongkanfield();
        
      } catch (Exception se) {  // Silahkan tambahkan catch Exception yang lain
         JOptionPane.showMessageDialog(this,"Gagal Hapus Data.. ");
       }
    }
  } 
  

  
  public void tampilDataKeJTable() {
    
    try {
         hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from temptmaterial";
            ResultSet rs=st.executeQuery(sql);
         
        
         // Menampilkan ke JTable  melalui tabModel
                     String kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       kodeitem=rs.getString("Kode_Item");
                       tipeitem=rs.getString("Tipe_Item");
                       namaitem=rs.getString("Nama_Item");
                       jenis=rs.getString("Jenis");
                       stok=rs.getString("Stok");
                       satuan=rs.getString("Satuan");
                       hargapokok=rs.getString("Harga_Pokok");
                       hargajual=rs.getString("Harga_Jual");
                       keterangan=rs.getString("Keterangan");
                       idsupplier=rs.getString("ID_Supplier");
                       namasupplier=rs.getString("Nama_Supplier");
        
         Object Data[]={kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier};
         tabModel.addRow(Data);
      }
                        st.close();
          rs.close();
  }
    catch (Exception e) {}
}
  
  
 
  public void rubahData() {
      
      
    // Konfirmasi sebelum melakukan perubahan data
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Mengubah Data Ini \n ", "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update temptmaterial set Nama_Item = ?, Jenis= ?, Stok= ?, Satuan= ?, Harga_Pokok= ?, Harga_Jual= ?, Keterangan= ?, ID_Supplier= ?, Nama_Supplier= ? WHERE Kode_Item = ?";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
       
        
        try {
                     
           p.setString(1, jTnamabarang.getText());
           p.setString(2, jCjenisbarang.getSelectedItem().toString());
           p.setString(3, jTstokbarang.getText());
           p.setString(4, jCsatuanbarang.getSelectedItem().toString());
           p.setString(5, jThargapokokbarang.getText());
           p.setString(6, jThargajualbarang.getText());
           p.setString(7, jTketeranganbarang.getText());
           p.setString(8, jCidsupplier.getText().toString());
           p.setString(9, jTnamasupplier.getText());
           p.setString(10, jTkodebarang.getText());
          
           p.executeUpdate();
           int rs= p.executeUpdate();
          
           if(rs>=0){
                      tampilDataKeJTable();
                      JOptionPane.showMessageDialog(this,"Edit Data Berhasil");
          }

         // Memanggil Method   tampilDataKeJTable();
          tampilDataKeJTable();
          setJTable();
          
          
      
        } catch (SQLException se) { }     // Silahkan tambahkan Sendiri informasi Eksepsi
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
    }
    
        
    
  }
  
  
  
  public void simpankedb(){
//Connection conn;
      
     try{
            conn=koneksidb.getkoneksi();
            Statement state = conn.createStatement();
            int baris = jTdaftarbarang.getRowCount();
            
		for (int a=0; a<baris; a++)
		{
                
		String query = "INSERT INTO tmaterial (Kode_Item, Tipe_Item, Nama_Item, Jenis, Stok, Satuan, Harga_Pokok, Harga_Jual, Keterangan, ID_Supplier, Nama_Supplier) VALUES( '"+jTdaftarbarang.getValueAt(a,0)+"','"+jTdaftarbarang.getValueAt(a,1)+"','"+jTdaftarbarang.getValueAt(a,2)+"','"+jTdaftarbarang.getValueAt(a,3)+"','"+jTdaftarbarang.getValueAt(a,4)+"','"+jTdaftarbarang.getValueAt(a,5)+"','"+jTdaftarbarang.getValueAt(a,6)+"','"+jTdaftarbarang.getValueAt(a,7)+"','"+jTdaftarbarang.getValueAt(a,8)+"','"+jTdaftarbarang.getValueAt(a,9)+"','"+jTdaftarbarang.getValueAt(a,10)+"')";
		state.executeUpdate(query);
			}
           state.close();
           JOptionPane.showMessageDialog(null,"Data berhasil disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE);
            hapusdatadb();			
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }         int col = tabModel.getColumnCount();     
  
        
        updatestok();
  }
  
  public void updatestok(){
      
          try{
            conn=koneksidb.getkoneksi();
            Statement state = conn.createStatement();
            int baris = jTdaftarbarang.getRowCount();
            
		for (int a=0; a<baris; a++)
		{
                
		String query = "INSERT INTO tstokbarang (No_Transaksi, Kode_Item, Stok_Awal, Stok_Masuk, Stok_Keluar) VALUES( '','"+jTdaftarbarang.getValueAt(a,0)+"','"+jTdaftarbarang.getValueAt(a,4)+"','','')";
		state.executeUpdate(query);
                
			}
           state.close();
           		
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }  
      
  }
  
  
  
  public void hapusdatadb(){
//Connection conn;
     try{
            conn=koneksidb.getkoneksi();
            Statement state = conn.createStatement();
            String query = "DELETE FROM temptmaterial";
            int delete = state.executeUpdate(query);
           
            if(delete == 0){
                        System.out.println("All rows are completelly deleted!");
                        //JOptionPane.showMessageDialog(null,"Data Berhasil di Hapus","Pesan",JOptionPane.INFORMATION_MESSAGE);
	
                 }
             state.close();
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }         int col = tabModel.getColumnCount();     
  
  }
  
  
  
  public void cekdatatabel(){
       int rows = tabModel.getRowCount();
       
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= 10; j++) {
                Object ob = tabModel.getValueAt(i, j);
                if (ob == null || ob.toString().isEmpty()) {
                    jBsimpanbarang.setEnabled(false);
                    //jBsimpanjasa.setEnabled(false);
                }else{
                    jBsimpanbarang.setEnabled(true);
                    //jBsimpanjasa.setEnabled(true);
                }
            }
        }
         
  
  }
  
   public void jmldatatable(){
         int rows = jTdaftarbarang.getRowCount();
         String jmls = String.valueOf(rows);
         jmlData.setText(jmls);
         
        
             
   }     
  
 
     
   
  
      
   public void nokodeitem(){
        
        try{
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sql= "select right(Kode_Item,3) as kd from tmaterial where Tipe_Item='material' order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTkodebarang.setText("MTRL-"+"000"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTkodebarang.setText("MTRL-"+"00"+Integer.toString(kode));   
                else if(kode>=1000&&kode<=9999) 
                    jTkodebarang.setText("MTRL-"+"0"+Integer.toString(kode));
                else
                    jTkodebarang.setText("MTRL-"+"0000"+Integer.toString(kode));
            }else{

                int kode = 1;

               jTkodebarang.setText("MTRL-"+"0000"+Integer.toString(kode));

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
   
   public void setkodeitem(){
        
        try{
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sql= "select right(Kode_Item,3) as kd from temptmaterial where Tipe_Item='material' order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTkodebarang.setText("MTRL-"+"000"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTkodebarang.setText("MTRL-"+"00"+Integer.toString(kode));   
                else if(kode>=1000&&kode<=9999) 
                    jTkodebarang.setText("MTRL-"+"0"+Integer.toString(kode));
                else
                    jTkodebarang.setText("MTRL-"+"0000"+Integer.toString(kode));
            }else{

                int kode = 1;

               jTkodebarang.setText("MTRL-"+"0000"+Integer.toString(kode));

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
   
   
   public void onsimpan(){
        int rows = jTdaftarbarang.getRowCount();
        if(rows==0)
                jBsimpanbarang.setEnabled(false);
        else
             jBsimpanbarang.setEnabled(true);
   }
   
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jTabinfo = new javax.swing.JTabbedPane();
        jPanelbarang = new javax.swing.JPanel();
        jBhapusbarang = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTkodebarang = new javax.swing.JTextField();
        jThargajualbarang = new javax.swing.JTextField();
        jCjenisbarang = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCsatuanbarang = new javax.swing.JComboBox();
        jTnamabarang = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jThargapokokbarang = new javax.swing.JTextField();
        jTstokbarang = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTketeranganbarang = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jCidsupplier = new javax.swing.JTextField();
        jTnamasupplier = new javax.swing.JTextField();
        jBsupplier = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTdaftarbarang = new javax.swing.JTable();
        jBsimpanbarang = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jmlData = new javax.swing.JLabel();
        jBeditbarang = new javax.swing.JButton();
        jBtambahbarang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tambah Material");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Data Material");

        jPanelbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelbarangMouseClicked(evt);
            }
        });

        jBhapusbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bin_Full.png"))); // NOI18N
        jBhapusbarang.setText("Hapus");
        jBhapusbarang.setEnabled(false);
        jBhapusbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhapusbarangActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel5.setText("Satuan");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel4.setText("Harga Jual");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText(":");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel9.setText("Keterangan");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText(":");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText(":");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText(":");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText(":");

        jTkodebarang.setEnabled(false);
        jTkodebarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTkodebarangMouseClicked(evt);
            }
        });
        jTkodebarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTkodebarangActionPerformed(evt);
            }
        });
        jTkodebarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTkodebarangKeyPressed(evt);
            }
        });

        jThargajualbarang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jThargajualbarang.setToolTipText("Hanya input Angka");
        jThargajualbarang.setEnabled(false);
        jThargajualbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jThargajualbarangActionPerformed(evt);
            }
        });
        jThargajualbarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jThargajualbarangFocusLost(evt);
            }
        });
        jThargajualbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jThargajualbarangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jThargajualbarangKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jThargajualbarangKeyTyped(evt);
            }
        });

        jCjenisbarang.setEditable(true);
        jCjenisbarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis", "-----------------------------------" }));
        jCjenisbarang.setEnabled(false);
        jCjenisbarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCjenisbarangFocusGained(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("Kode Material");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("Nama Material");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel3.setText("Jenis");

        jCsatuanbarang.setEditable(true);
        jCsatuanbarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Satuan", "-----------------------------------" }));
        jCsatuanbarang.setEnabled(false);

        jTnamabarang.setEnabled(false);
        jTnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTnamabarangActionPerformed(evt);
            }
        });
        jTnamabarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTnamabarangKeyPressed(evt);
            }
        });

        jButton2.setText("jButton1");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText(":");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel7.setText("Harga Pokok");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(":");

        jThargapokokbarang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jThargapokokbarang.setToolTipText("Hanya input Angka");
        jThargapokokbarang.setEnabled(false);
        jThargapokokbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jThargapokokbarangActionPerformed(evt);
            }
        });
        jThargapokokbarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jThargapokokbarangFocusLost(evt);
            }
        });
        jThargapokokbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jThargapokokbarangKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jThargapokokbarangKeyTyped(evt);
            }
        });

        jTstokbarang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTstokbarang.setToolTipText("Hanya input Angka");
        jTstokbarang.setEnabled(false);
        jTstokbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTstokbarangActionPerformed(evt);
            }
        });
        jTstokbarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTstokbarangFocusLost(evt);
            }
        });
        jTstokbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTstokbarangKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTstokbarangKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel8.setText("Stok");

        jTketeranganbarang.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel18.setText("Supplier");

        jLabel19.setText(":");

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

        jTnamasupplier.setEnabled(false);

        jBsupplier.setText("...");
        jBsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCsatuanbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jThargajualbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jCjenisbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(261, 261, 261)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel16)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTstokbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jThargapokokbarang)
                    .addComponent(jTketeranganbarang)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jCidsupplier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTnamasupplier))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11)
                            .addComponent(jTnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jThargajualbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12)
                            .addComponent(jCjenisbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14)
                            .addComponent(jCsatuanbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jThargapokokbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTstokbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTketeranganbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jCidsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBsupplier))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jTnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTdaftarbarang.setAutoCreateRowSorter(true);
        jTdaftarbarang.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTdaftarbarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Material", "Tipe Item", "Nama Item", "Jenis", "Stok", "Satuan", "Harga Pokok", "Harga Jual", "Keterangan", "ID Supplier", "Nama Supplier"
            }
        ));
        jTdaftarbarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTdaftarbarang.setAutoscrolls(false);
        jTdaftarbarang.setColumnSelectionAllowed(true);
        jTdaftarbarang.setFocusable(false);
        jTdaftarbarang.setRowHeight(17);
        jTdaftarbarang.setRowMargin(3);
        jTdaftarbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTdaftarbarangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTdaftarbarang);
        jTdaftarbarang.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jBsimpanbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jBsimpanbarang.setText("Simpan");
        jBsimpanbarang.setEnabled(false);
        jBsimpanbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsimpanbarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jBsimpanbarang)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBsimpanbarang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel36.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel36.setText("Jumlah Data : ");

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jBeditbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tools.png"))); // NOI18N
        jBeditbarang.setText("Edit");
        jBeditbarang.setEnabled(false);
        jBeditbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditbarangActionPerformed(evt);
            }
        });

        jBtambahbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jBtambahbarang.setText("Tambah");
        jBtambahbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtambahbarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelbarangLayout = new javax.swing.GroupLayout(jPanelbarang);
        jPanelbarang.setLayout(jPanelbarangLayout);
        jPanelbarangLayout.setHorizontalGroup(
            jPanelbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelbarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtambahbarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBeditbarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBhapusbarang)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelbarangLayout.setVerticalGroup(
            jPanelbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelbarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBhapusbarang)
                    .addComponent(jLabel36)
                    .addComponent(jmlData)
                    .addComponent(jBeditbarang)
                    .addComponent(jBtambahbarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabinfo.addTab("Data Material", jPanelbarang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabinfo)
                .addContainerGap())
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabinfo)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleParent(jBeditbarang);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void getDataJenisDipilih(String kode){
            
                jCjenisbarang.setSelectedItem(kode);
    }
    
    
    
    
    
    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
         getDatajenis();
         getDatasatuan();
    }//GEN-LAST:event_formMouseEntered
private void getDataJenisjDipilih(String kode){
            
                
    }
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        
        hapusdatadb();
    }//GEN-LAST:event_formWindowClosing

    private void jPanelbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelbarangMouseClicked
        // TODO add your handling code here:

        nokodeitem();
        setJTable();
        jBhapusbarang.setEnabled(false);
        jBeditbarang.setEnabled(false);
        jBtambahbarang.setEnabled(true);
        //kosongkanfield();
        cekdatatabel();
        onsimpan();
    }//GEN-LAST:event_jPanelbarangMouseClicked

    private void jBtambahbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtambahbarangActionPerformed
        // TODO add your handling code here:

        String kodebarang = jTkodebarang.getText();
        String tipeitem = "material";
        String namabarang = jTnamabarang.getText();
        String hargajual = jThargajualbarang.getText();
        String hargapokok = jThargapokokbarang.getText();
        String stokbarang = jTstokbarang.getText();
        String keterangan = jTketeranganbarang.getText();
        String jenisbarang = jCjenisbarang.getSelectedItem().toString();
        String satuanbarang = jCsatuanbarang.getSelectedItem().toString();
        String idsupplier = jCidsupplier.getText().toString();
        String namasupplier = jTnamasupplier.getText();

        int x = Integer.valueOf(jThargapokokbarang.getText());
        int y = Integer.valueOf(jThargajualbarang.getText());

        if(kodebarang.equalsIgnoreCase("")||namabarang.equalsIgnoreCase("")||hargajual.equalsIgnoreCase("")||hargapokok.equalsIgnoreCase("")||stokbarang.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");
            setJTable();
        }
        if(x>y){
            JOptionPane.showMessageDialog(this,"Maaf Kesalahan Input, Harga Pokok Tidak Boleh Lebih Besar Dari Harga Jual");
            setJTable();
        }
        else{
            simpanData();
            cekdatatabel();
            jmldatatable();
            //setkodeitem();

            jBsimpanbarang.setEnabled(true);
        }

        onsimpan();
    }//GEN-LAST:event_jBtambahbarangActionPerformed

    private void jBeditbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditbarangActionPerformed
        // TODO add your handling code here:

        try {

            rubahData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Edit");

        }

        onsimpan();
    }//GEN-LAST:event_jBeditbarangActionPerformed

    private void jBsimpanbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanbarangActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        simpankedb();
        setJTable();
        kosongkanfield();
        jmldatatable();
        onsimpan();
       
        nokodeitem();
    }//GEN-LAST:event_jBsimpanbarangActionPerformed

    private void jTdaftarbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTdaftarbarangMouseClicked
        // TODO add your handling code here:
        ambilData_dari_JTable();
        jBeditbarang.setEnabled(true);
        jBhapusbarang.setEnabled(true);
        jBtambahbarang.setEnabled(false);

    }//GEN-LAST:event_jTdaftarbarangMouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel3MouseEntered

    private void jTstokbarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTstokbarangKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTstokbarangKeyTyped

    private void jTstokbarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTstokbarangKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTstokbarangKeyPressed

    private void jTstokbarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTstokbarangFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_jTstokbarangFocusLost

    private void jTstokbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTstokbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTstokbarangActionPerformed

    private void jThargapokokbarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jThargapokokbarangKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jThargapokokbarangKeyTyped

    private void jThargapokokbarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jThargapokokbarangKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jThargapokokbarangKeyPressed

    private void jThargapokokbarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jThargapokokbarangFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_jThargapokokbarangFocusLost

    private void jThargapokokbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jThargapokokbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jThargapokokbarangActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        PopSatuanMaterial dp=new PopSatuanMaterial();
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        panelPop.add(dp).setVisible(true);
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Satuan Material", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getDataSatuanjDipilih(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }

        }
        private void getDataSatuanjDipilih(String kode){

            jCsatuanbarang.setSelectedItem(kode);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTnamabarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnamabarangKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTnamabarangKeyPressed

    private void jTnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTnamabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTnamabarangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PopJenisMaterial dp=new PopJenisMaterial();
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        panelPop.add(dp).setVisible(true);
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Jenis Material", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getDataJenisDipilih(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCjenisbarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCjenisbarangFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_jCjenisbarangFocusGained

    private void jThargajualbarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jThargajualbarangKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jThargajualbarangKeyTyped

    private void jThargajualbarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jThargajualbarangKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jThargajualbarangKeyReleased

    private void jThargajualbarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jThargajualbarangKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jThargajualbarangKeyPressed

    private void jThargajualbarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jThargajualbarangFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_jThargajualbarangFocusLost

    private void jThargajualbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jThargajualbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jThargajualbarangActionPerformed

    private void jTkodebarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkodebarangKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTkodebarangKeyPressed

    private void jTkodebarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTkodebarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTkodebarangActionPerformed

    private void jTkodebarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTkodebarangMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTkodebarangMouseClicked

    private void jBhapusbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusbarangActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
            jmldatatable();
            jBhapusbarang.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
        }

        onsimpan();
      
        nokodeitem();
    }//GEN-LAST:event_jBhapusbarangActionPerformed

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
    }//GEN-LAST:event_jBsupplierActionPerformed

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
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TambahMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahMaterial().setVisible(true);
            }
        });
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBeditbarang;
    private javax.swing.JButton jBhapusbarang;
    private javax.swing.JButton jBsimpanbarang;
    private javax.swing.JButton jBsupplier;
    private javax.swing.JButton jBtambahbarang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jCidsupplier;
    private javax.swing.JComboBox jCjenisbarang;
    private javax.swing.JComboBox jCsatuanbarang;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelbarang;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabinfo;
    private javax.swing.JTable jTdaftarbarang;
    private javax.swing.JTextField jThargajualbarang;
    private javax.swing.JTextField jThargapokokbarang;
    private javax.swing.JTextField jTketeranganbarang;
    private javax.swing.JTextField jTkodebarang;
    private javax.swing.JTextField jTnamabarang;
    private javax.swing.JTextField jTnamasupplier;
    private javax.swing.JTextField jTstokbarang;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
