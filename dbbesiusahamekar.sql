-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Waktu pembuatan: 10. April 2016 jam 17:26
-- Versi Server: 5.5.16
-- Versi PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dbbesiusahamekar`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenismaterial`
--

CREATE TABLE IF NOT EXISTS `jenismaterial` (
  `kode` varchar(32) NOT NULL DEFAULT '',
  `keterangan` varchar(100) NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jenismaterial`
--

INSERT INTO `jenismaterial` (`kode`, `keterangan`) VALUES
('AMPLAS', ''),
('BALOK', ''),
('BATU', ''),
('BESI BETON', ''),
('BOR', ''),
('CAT', ''),
('DEMPUL', ''),
('EMBER', ''),
('ENGSEL', ''),
('FIBER', ''),
('GALAR', ''),
('KACA', ''),
('KAPE', ''),
('KASO', ''),
('KAWAT', ''),
('KERAMIK', ''),
('KIKIR', ''),
('KLEM', ''),
('KLOSET', ''),
('KOAS', ''),
('KRAN', ''),
('LAKBAN', ''),
('LAMPU', ''),
('LEM', ''),
('METERAN', ''),
('OBENG', ''),
('PACUL', ''),
('PAKU', ''),
('PAPAN', ''),
('PASIR', ''),
('PINTU', ''),
('PIPA', ''),
('POMPA', ''),
('RENG', ''),
('SAKLAR', ''),
('SARUNG TANGAN', ''),
('SELANG', ''),
('SEMEN', ''),
('SEPATU', ''),
('SHOWER', ''),
('SPLIT', ''),
('TOREN', ''),
('TRIPLEX', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `satuanmaterial`
--

CREATE TABLE IF NOT EXISTS `satuanmaterial` (
  `kode` varchar(32) NOT NULL DEFAULT '',
  `keterangan` varchar(100) NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `satuanmaterial`
--

INSERT INTO `satuanmaterial` (`kode`, `keterangan`) VALUES
('-', ''),
('BATANG', ''),
('CENTIMETER', ''),
('GALON', ''),
('INCI', ''),
('KALENG', ''),
('KILOGRAM', ''),
('LEMBAR', ''),
('LITER', ''),
('M2', ''),
('METER', ''),
('PACK', ''),
('PICK UP', ''),
('ROLL', ''),
('SACK', ''),
('SET', ''),
('UNIT', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `temptmaterial`
--

CREATE TABLE IF NOT EXISTS `temptmaterial` (
  `Kode_Item` varchar(32) NOT NULL,
  `Tipe_Item` varchar(32) NOT NULL,
  `Nama_Item` varchar(100) NOT NULL,
  `Jenis` varchar(32) NOT NULL,
  `Stok` varchar(32) NOT NULL,
  `Satuan` varchar(32) NOT NULL,
  `Harga_Pokok` varchar(32) NOT NULL,
  `Harga_Jual` varchar(32) NOT NULL,
  `Keterangan` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Kode_Item`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tkonsumen`
--

CREATE TABLE IF NOT EXISTS `tkonsumen` (
  `ID_Konsumen` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Alamat` varchar(100) DEFAULT NULL,
  `Kota` varchar(32) DEFAULT NULL,
  `Telepon` varchar(32) DEFAULT NULL,
  `Keterangan` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_Konsumen`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tkonsumen`
--

INSERT INTO `tkonsumen` (`ID_Konsumen`, `Nama`, `Alamat`, `Kota`, `Telepon`, `Keterangan`) VALUES
('KNSM-0000', 'KONSUMEN', '-', '-', '-', ''),
('KNSM-0001', 'NUGRAHA BANU AJIE', 'SARIJADI', 'BANDUNG', '087825555088', ''),
('KNSM-0002', 'INDRA TAUFIKURAHMAN', 'SARIJADI', 'BANDUNG', '0987654321', ''),
('KNSM-0003', 'ACHMAD MUSLIHAT', 'SARIJADI', 'BANDUNG', '081322223955', ''),
('KNSM-0004', 'RANGGA GARNADI', 'SUKATANI', 'BEKASI', '08122100822', ''),
('KNSM-0005', 'ARI ANDRIAYANA', 'RIUNG BANDUNG', 'BANDUNG', '09876543', ''),
('KNSM-0006', 'BURHANUDIN', 'LEUWILIANG', 'BOGOR', '0987654321', ''),
('KNSM-0007', 'TATANG KURNIAWAN', 'KELIPANG', 'SEMARANG', '1234567890', ''),
('KNSM-0008', 'IWAN MULYAWAN', 'CIPOCOK JAYA', 'SERANG', '0876543298', ''),
('KNSM-0009', 'SYAIFUL SYARIF', 'CICERI KPBN', 'SERANG', '456789876', ''),
('KNSM-0010', 'DIAN PURBANINGSIH', 'DERMAGA IPB', 'BOGOR', '076426899654', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tmaterial`
--

CREATE TABLE IF NOT EXISTS `tmaterial` (
  `Kode_Item` varchar(32) NOT NULL,
  `Tipe_Item` varchar(32) NOT NULL,
  `Nama_Item` varchar(100) NOT NULL,
  `Jenis` varchar(32) NOT NULL,
  `Stok` varchar(32) DEFAULT NULL,
  `Satuan` varchar(32) NOT NULL,
  `Harga_Pokok` varchar(32) DEFAULT NULL,
  `Harga_Jual` varchar(32) NOT NULL,
  `Keterangan` varchar(100) DEFAULT NULL,
  `ID_Supplier` varchar(32) NOT NULL,
  `Nama_Supplier` varchar(100) NOT NULL,
  PRIMARY KEY (`Kode_Item`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tmaterial`
--

INSERT INTO `tmaterial` (`Kode_Item`, `Tipe_Item`, `Nama_Item`, `Jenis`, `Stok`, `Satuan`, `Harga_Pokok`, `Harga_Jual`, `Keterangan`, `ID_Supplier`, `Nama_Supplier`) VALUES
('MTRL-00001', 'material', 'BATU BATA MERAH', 'BATU', '1100', 'UNIT', '300', '500', '-', 'SPLR-0003', 'BATU BATA NAGREG'),
('MTRL-00002', 'material', 'SEMEN TIGA RODA (50 KG)', 'SEMEN', '26', 'SACK', '60000', '63000', '-', 'SPLR-0004', 'INDO SEMEN'),
('MTRL-00003', 'material', 'SEMEN TIGA RODA (40KG)', 'SEMEN', '59', 'SACK', '50000', '53000', '-', 'SPLR-0004', 'INDO SEMEN'),
('MTRL-00004', 'material', 'SEMEN GRESIK (50KG)', 'SEMEN', '31', 'SACK', '57000', '61000', '-', 'SPLR-0004', 'INDO SEMEN'),
('MTRL-00005', 'material', 'SEMEN GRESIK (40KG)', 'SEMEN', '64', 'SACK', '47000', '50000', '-', 'SPLR-0004', 'INDO SEMEN'),
('MTRL-00006', 'material', 'KERAMIK KANTAI PLATINUM, 25 (20 X 20)', 'KERAMIK', '27', 'PACK', '35000', '39500', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00007', 'material', 'KACA BENING (3MM)', 'KACA', '19', 'M2', '75000', '80000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00008', 'material', 'KACA RAYBEN (3MM)', 'KACA', '41', 'M2', '60000', '65000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00009', 'material', 'RENG KAMPER (2 X 3 X 400)', 'RENG', '35', 'BATANG', '19000', '22000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00010', 'material', 'RENG MERANTI (2 X 3 X 400)', 'RENG', '26', 'BATANG', '10000', '12000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00011', 'material', 'KASO KAMPER (4 X 6 X 400)', 'KASO', '51', 'BATANG', '63000', '67000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00012', 'material', 'KASO MERANTI (4 X 6 X 400)', 'KASO', '79', 'BATANG', '35000', '38000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00013', 'material', 'GALAR BORNEO (5 X 10 X 400)', 'GALAR', '20', 'BATANG', '55000', '60000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00014', 'material', 'BALOK MERANTI (8 X 12 X 400)', 'BALOK', '50', 'BATANG', '145000', '150000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00015', 'material', 'PAPAN BORNEO (3 X 40 X 400)', 'PAPAN', '74', 'BATANG', '80000', '85000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00016', 'material', 'PAPAN MERANTI (3 X 20 X 400)', 'PAPAN', '10', 'BATANG', '110000', '120000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00017', 'material', 'CAT TEMBOK DULUX PEARL GLO (2,5 LITER)', 'CAT', '14', 'KALENG', '175000', '180000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00018', 'material', 'CAT TEMBOK CATYLAC (5KG)', 'CAT', '49', 'KALENG', '113000', '117500', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00019', 'material', 'TOREN PENGUIN TB25 (225 LITER)', 'TOREN', '16', 'UNIT', '400000', '450000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00020', 'material', 'TOREN PENGUIN TB110 (1050 LITER)', 'TOREN', '30', 'UNIT', '1100000', '1200000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00021', 'material', 'TRIPLEX 3MM (3 X 7)', 'TRIPLEX', '60', 'LEMBAR', '27500', '29500', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00022', 'material', 'TRIPLEX 3MM PALM (4 X 8)', 'TRIPLEX', '30', 'LEMBAR', '39000', '41000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00023', 'material', 'TRIPLEX 3MM TUNAS (4 X 8)', 'TRIPLEX', '40', 'LEMBAR', '37000', '39000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00024', 'material', 'BESI BETON TARIK 4MM (3,8 MM)', 'BESI BETON', '30', 'BATANG', '10000', '11000', '-', 'SPLR-0006', 'CV. READYMIX'),
('MTRL-00025', 'material', 'BESI BETON TARIK 4MM (4MM)', 'BESI BETON', '40', 'BATANG', '11000', '12000', '-', 'SPLR-0006', 'CV. READYMIX'),
('MTRL-00026', 'material', 'BESI BETON D-13 IBD SNI ULIR (12MM)', 'BESI BETON', '20', 'BATANG', '98000', '102000', '-', 'SPLR-0006', 'CV. READYMIX'),
('MTRL-00027', 'material', 'SPLIT', 'SPLIT', '20', 'PICK UP', '230000', '250000', '-', 'SPLR-0006', 'CV. READYMIX'),
('MTRL-00028', 'material', 'PASIR PUTIH', 'PASIR', '40', 'PICK UP', '500000', '550000', '-', 'SPLR-0006', 'CV. READYMIX'),
('MTRL-00029', 'material', 'BATU BATAKO', 'BATU', '90', 'UNIT', '2400', '2500', '-', 'SPLR-0003', 'BATU BATA NAGREG'),
('MTRL-00030', 'material', 'KLOSET DUDUK TOTO C50', 'KLOSET', '12', 'UNIT', '450000', '494000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00031', 'material', 'KLOSET DUDUK TOTO C51/T150NL', 'KLOSET', '12', 'UNIT', '2700000', '2820000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00032', 'material', 'SHOWER TOTO TX469SOBR', 'SHOWER', '37', 'UNIT', '1900000', '1985000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00033', 'material', 'PAKU KAYU (2CM)', 'PAKU', '7', 'KILOGRAM', '16000', '17500', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00034', 'material', 'PAKU KAYU (4CM)', 'PAKU', '10', 'KILOGRAM', '13500', '15000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00038', 'material', 'KAWAT PUTIH', 'KAWAT', '12', 'ROLL', '25000', '27000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00039', 'material', 'KAWAT HITAM', 'KAWAT', '55', 'ROLL', '150000', '160000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00040', 'material', 'AMPLAS DUCO 180 SIKEN LBR', 'AMPLAS', '20', 'LEMBAR', '2300', '2500', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00044', 'material', 'CAT KAYU GLOTEX (1KG)', 'CAT', '16', 'KALENG', '35000', '36000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00046', 'material', 'DEMPUL TEMBOK SANLEX (5KG)', 'DEMPUL', '8', 'GALON', '52000', '55000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00048', 'material', 'DOP DRAT PVC 1/2" TR', 'PIPA', '20', 'UNIT', '1300', '1500', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00051', 'material', 'EMBER HITAM KECIL', 'EMBER', '20', 'UNIT', '3000', '4000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00052', 'material', 'ENGSEL 4" IGM GP', 'ENGSEL', '57', 'UNIT', '18000', '20000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00054', 'material', 'FIBER GELOMBANG 1,8 CLEAR (BCL. ROMA)', 'FIBER', '10', 'LEMBAR', '60000', '65000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00055', 'material', 'FIBER GELOMBANG 2,4 GREYCL. ROMA', 'FIBER', '13', 'LEMBAR', '85000', '90000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00056', 'material', 'KAPE GAGANG KAYU 2" SSSK', 'KAPE', '9', 'UNIT', '2500', '3000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00057', 'material', 'KIKIR 4" STAMVICK', 'KIKIR', '30', 'UNIT', '9000', '10000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00058', 'material', 'KLEM KABEL SUPER KLIP NO. 7', 'KLEM', '19', 'UNIT', '1300', '1500', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00059', 'material', 'KLEM KABEL SUNDEX NO. 8', 'KLEM', '15', 'UNIT', '2200', '2500', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00061', 'material', 'KOAS ROLL STANDARD', 'KOAS', '9', 'UNIT', '12000', '13500', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00062', 'material', 'KRAN AWS 3/4" ONDA', 'KRAN', '12', 'UNIT', '10000', '11000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00068', 'material', 'LAKBAN KAIN HITAM DAICHI', 'LAKBAN', '12', 'UNIT', '4500', '5000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00069', 'material', 'LAMPU PLC INTEGRA ACTIV (10 WATT)', 'LAMPU', '10', 'UNIT', '5500', '6000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00072', 'material', 'LEM FOX PUTIH (350 GRAM)', 'LEM', '6', 'KALENG', '6500', '7000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00074', 'material', 'LEM PVC TUBE TANGIT', 'LEM', '54', 'UNIT', '5500', '6000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00077', 'material', 'MATA BOR BESI (3,5MM)', 'BOR', '36', 'UNIT', '3000', '3500', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00079', 'material', 'METERAN KARET VIPER (5M)', 'METERAN', '91', 'UNIT', '6000', '7000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00081', 'material', 'OBENG +-6IN  WINSTON', 'OBENG', '32', 'UNIT', '8000', '9000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00084', 'material', 'PACUL KAYU (5/8")', 'PACUL', '8', 'UNIT', '8000', '10000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00086', 'material', 'PINTU PVC PLATINUM', 'PINTU', '17', 'UNIT', '150000', '175000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00089', 'material', 'POMPA AIR PS-130 BIT SIMIZU', 'POMPA', '20', 'UNIT', '475000', '500000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00091', 'material', 'SAKLAR ENGKEL OB', 'SAKLAR', '71', 'UNIT', '5000', '6000', '-', 'SPLR-0002', 'PERMATA KARYA'),
('MTRL-00092', 'material', 'SARUNG TANGAN KARET D.D', 'SARUNG TANGAN', '20', 'SET', '7000', '8000', '-', 'SPLR-0005', 'JAYA BARU'),
('MTRL-00093', 'material', 'SELANG BCP ABU', 'SELANG', '12', 'METER', '15000', '16000', '-', 'SPLR-0001', 'KAWI JAYA'),
('MTRL-00097', 'material', 'SEPATU BOOT AP BOOT MOTO 3', 'SEPATU', '10', 'UNIT', '75000', '85000', '-', 'SPLR-0005', 'JAYA BARU');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tmaterialkeluar`
--

CREATE TABLE IF NOT EXISTS `tmaterialkeluar` (
  `No_Transaksi` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `Jumlah_Item` varchar(32) NOT NULL,
  `Total_Harga` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tmaterialmasuk`
--

CREATE TABLE IF NOT EXISTS `tmaterialmasuk` (
  `No_Transaksi` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `Jumlah_Item` varchar(32) NOT NULL,
  `Total_Harga` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tpesananbeli`
--

CREATE TABLE IF NOT EXISTS `tpesananbeli` (
  `No_Pesanan` varchar(32) NOT NULL,
  `Tgl_Pesan` date NOT NULL,
  `Tgl_Kirim` date NOT NULL,
  `ID_Supplier` varchar(32) NOT NULL,
  `Nama_Supplier` varchar(100) NOT NULL,
  `Jml_Pesan` varchar(32) NOT NULL,
  `Total_Pesan` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`No_Pesanan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tpesananbeli`
--

INSERT INTO `tpesananbeli` (`No_Pesanan`, `Tgl_Pesan`, `Tgl_Kirim`, `ID_Supplier`, `Nama_Supplier`, `Jml_Pesan`, `Total_Pesan`, `Keterangan`, `status`) VALUES
('NOPB0604201600001', '2016-04-06', '2016-04-06', 'SPLR-0005', 'JAYA BARU', '5', '45000', '', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tpesananjual`
--

CREATE TABLE IF NOT EXISTS `tpesananjual` (
  `No_Pesanan` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `ID_Konsumen` varchar(32) DEFAULT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `Jml_Pesan` varchar(32) NOT NULL,
  `Total_Pesan` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `DP` varchar(12) NOT NULL,
  `Status` varchar(1) NOT NULL,
  PRIMARY KEY (`No_Pesanan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tpesananjual`
--

INSERT INTO `tpesananjual` (`No_Pesanan`, `Tanggal`, `ID_Konsumen`, `Nama`, `Jml_Pesan`, `Total_Pesan`, `Keterangan`, `DP`, `Status`) VALUES
('NOPJ0604201600001', '2016-04-06', 'KNSM-0008', 'IWAN MULYAWAN', '7', '451000', '', '150000', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `treturbeli`
--

CREATE TABLE IF NOT EXISTS `treturbeli` (
  `No_Retur` varchar(32) NOT NULL,
  `No_Transaksi` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `ID_Supplier` varchar(32) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `treturbeli`
--

INSERT INTO `treturbeli` (`No_Retur`, `No_Transaksi`, `Tanggal`, `ID_Supplier`, `Jumlah`, `Total`, `Keterangan`, `Status`) VALUES
('NRPB0604201600001', 'NTPB0604201600002', '2016-04-06', 'SPLR-0005', '1', '9000', '', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `treturjual`
--

CREATE TABLE IF NOT EXISTS `treturjual` (
  `No_Retur` varchar(32) NOT NULL,
  `No_Transaksi` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `ID_Konsumen` varchar(32) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `treturjual`
--

INSERT INTO `treturjual` (`No_Retur`, `No_Transaksi`, `Tanggal`, `ID_Konsumen`, `Jumlah`, `Total`, `Keterangan`, `Status`) VALUES
('NRPJ0604201600001', 'NTPJ0604201600002', '2016-04-06', 'KNSM-0008', '1', '63000', '', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tstokbarang`
--

CREATE TABLE IF NOT EXISTS `tstokbarang` (
  `No_Transaksi` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `Kode_Item` varchar(32) NOT NULL,
  `Stok_Akhir` varchar(32) NOT NULL,
  `Stok_Awal` varchar(32) NOT NULL,
  `Stok_Masuk` varchar(32) NOT NULL,
  `Stok_Keluar` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tstokbarang`
--

INSERT INTO `tstokbarang` (`No_Transaksi`, `Tanggal`, `Kode_Item`, `Stok_Akhir`, `Stok_Awal`, `Stok_Masuk`, `Stok_Keluar`) VALUES
('NTPB0604201600001', '2016-04-06', 'MTRL-00028', '40', '20', '20', '0'),
('NTPB0604201600002', '2016-04-06', 'MTRL-00057', '30', '26', '0', '1'),
('NRPB0604201600001', '2016-04-06', 'MTRL-00057', '30', '31', '0', '1'),
('NTPJ0604201600001', '2016-04-06', 'MTRL-00022', '30', '50', '0', '20'),
('NTPJ0604201600001', '2016-04-06', 'MTRL-00010', '26', '37', '0', '11'),
('NTPJ0604201600002', '2016-04-06', 'MTRL-00002', '26', '29', '1', '0'),
('NTPJ0604201600002', '2016-04-06', 'MTRL-00008', '41', '46', '0', '5'),
('NRPJ0604201600001', '2016-04-06', 'MTRL-00002', '26', '27', '1', '0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tsupplier`
--

CREATE TABLE IF NOT EXISTS `tsupplier` (
  `ID_Supplier` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Alamat` varchar(100) DEFAULT NULL,
  `Kota` varchar(32) DEFAULT NULL,
  `Telepon` varchar(32) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Keterangan` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_Supplier`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tsupplier`
--

INSERT INTO `tsupplier` (`ID_Supplier`, `Nama`, `Alamat`, `Kota`, `Telepon`, `Email`, `Keterangan`) VALUES
('SPLR-0001', 'KAWI JAYA', 'JALAN SARIMANAH II NO 100', 'BANDUNG', '0987654321', '', ''),
('SPLR-0002', 'PERMATA KARYA', 'SUKAHAJI', 'BANDUNG', '022987654', '', ''),
('SPLR-0003', 'BATU BATA NAGREG', 'NAGREG', 'GARUT', '0987654321', '', ''),
('SPLR-0004', 'INDO SEMEN', 'PASIR KOJA', 'BANDUNG', '1234567890', '', ''),
('SPLR-0005', 'JAYA BARU', 'MAJALAYA', 'BANDUNG', '0987654321', '', ''),
('SPLR-0006', 'CV. READYMIX', 'JL. RAYA SOEKARNO HATTA 758', 'BANDUNG', '081310687533', 'CV.READYMIXPLANT_BANDUNG@YAHOO.CO.ID', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttempmaterialkeluar`
--

CREATE TABLE IF NOT EXISTS `ttempmaterialkeluar` (
  `No_Transaksi` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `User` varchar(32) NOT NULL,
  `Kode_Item` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Harga` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttempmaterialmasuk`
--

CREATE TABLE IF NOT EXISTS `ttempmaterialmasuk` (
  `No_Transaksi` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `User` varchar(32) NOT NULL,
  `Kode_Item` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Harga` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttemppesananbeli`
--

CREATE TABLE IF NOT EXISTS `ttemppesananbeli` (
  `No_Pesanan` varchar(32) NOT NULL,
  `Tgl_Pesan` date NOT NULL,
  `Tgl_Kirim` date NOT NULL,
  `User` varchar(32) NOT NULL,
  `ID_Supplier` varchar(32) NOT NULL,
  `Kode_Item` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Harga` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Sub_Total` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ttemppesananbeli`
--

INSERT INTO `ttemppesananbeli` (`No_Pesanan`, `Tgl_Pesan`, `Tgl_Kirim`, `User`, `ID_Supplier`, `Kode_Item`, `Nama`, `Jumlah`, `Harga`, `Total`, `Sub_Total`, `Keterangan`) VALUES
('NOPB0604201600001', '2016-04-06', '2016-04-06', 'Admin', 'SPLR-0005', 'MTRL-00057', 'KIKIR 4" STAMVICK', '5', '9000', '45000', '0', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttemppesananjual`
--

CREATE TABLE IF NOT EXISTS `ttemppesananjual` (
  `No_Pesanan` varchar(32) NOT NULL,
  `Tgl_Pesan` date NOT NULL,
  `Konsumen` varchar(100) DEFAULT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `User` varchar(32) NOT NULL,
  `Kode_Item` varchar(32) NOT NULL,
  `Nama_Item` varchar(100) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Harga` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ttemppesananjual`
--

INSERT INTO `ttemppesananjual` (`No_Pesanan`, `Tgl_Pesan`, `Konsumen`, `Nama`, `User`, `Kode_Item`, `Nama_Item`, `Jumlah`, `Harga`, `Total`, `Keterangan`, `Status`) VALUES
('NOPJ0604201600001', '2016-04-06', 'KNSM-0008', 'IWAN MULYAWAN', 'Admin', 'MTRL-00002', 'SEMEN TIGA RODA (50 KG)', '2', '63000', '126000', '', '0'),
('NOPJ0604201600001', '2016-04-06', 'KNSM-0008', 'IWAN MULYAWAN', 'Admin', 'MTRL-00008', 'KACA RAYBEN (3MM)', '5', '65000', '325000', '', '0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttempreturbeli`
--

CREATE TABLE IF NOT EXISTS `ttempreturbeli` (
  `No_Retur` varchar(32) NOT NULL,
  `No_Transaksi` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `Tgl_Transaksi` date NOT NULL,
  `User` varchar(32) NOT NULL,
  `ID_Supplier` varchar(32) NOT NULL,
  `Kode_Item` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Harga` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ttempreturbeli`
--

INSERT INTO `ttempreturbeli` (`No_Retur`, `No_Transaksi`, `Tanggal`, `Tgl_Transaksi`, `User`, `ID_Supplier`, `Kode_Item`, `Nama`, `Jumlah`, `Harga`, `Total`, `Keterangan`, `Status`) VALUES
('NRPB0604201600001', 'NTPB0604201600002', '2016-04-06', '2016-04-06', 'Admin', 'SPLR-0005', 'MTRL-00057', 'KIKIR 4" STAMVICK', '1', '9000', '9000', '', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttempreturjual`
--

CREATE TABLE IF NOT EXISTS `ttempreturjual` (
  `No_Retur` varchar(32) NOT NULL,
  `No_Transaksi` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `Tgl_Transaksi` varchar(32) NOT NULL,
  `User` varchar(32) NOT NULL,
  `ID_Konsumen` varchar(32) DEFAULT NULL,
  `Kode_Item` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Harga` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ttempreturjual`
--

INSERT INTO `ttempreturjual` (`No_Retur`, `No_Transaksi`, `Tanggal`, `Tgl_Transaksi`, `User`, `ID_Konsumen`, `Kode_Item`, `Nama`, `Jumlah`, `Harga`, `Total`, `Keterangan`, `Status`) VALUES
('NRPJ0604201600001', 'NTPJ0604201600002', '2016-04-06', '2016-04-06', 'Admin', 'KNSM-0008', 'MTRL-00002', 'SEMEN TIGA RODA (50 KG)', '1', '63000', '63000', '', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttemptransaksibeli`
--

CREATE TABLE IF NOT EXISTS `ttemptransaksibeli` (
  `No_Transaksi` varchar(32) NOT NULL,
  `No_Pesanan` varchar(32) NOT NULL,
  `Tgl_Pesan` date NOT NULL,
  `User` varchar(32) NOT NULL,
  `ID_Supplier` varchar(32) NOT NULL,
  `Kode_Item` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Harga` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Retur` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ttemptransaksibeli`
--

INSERT INTO `ttemptransaksibeli` (`No_Transaksi`, `No_Pesanan`, `Tgl_Pesan`, `User`, `ID_Supplier`, `Kode_Item`, `Nama`, `Jumlah`, `Harga`, `Total`, `Retur`, `Keterangan`) VALUES
('NTPB0604201600001', '', '2016-04-06', 'Admin', 'SPLR-0006', 'MTRL-00028', 'PASIR PUTIH', '20', '500000', '10000000', '0', ''),
('NTPB0604201600002', 'NOPB0604201600001', '2016-04-06', 'Admin', 'SPLR-0005', 'MTRL-00057', 'KIKIR 4" STAMVICK', '5', '9000', '45000', '1', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttemptransaksijual`
--

CREATE TABLE IF NOT EXISTS `ttemptransaksijual` (
  `No_Transaksi` varchar(32) NOT NULL,
  `No_Pesanan` varchar(32) NOT NULL,
  `Tgl_Pesan` date NOT NULL,
  `ID_Konsumen` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `User` varchar(32) NOT NULL,
  `Kode_Item` varchar(32) NOT NULL,
  `Nama_Item` varchar(100) NOT NULL,
  `Jumlah` varchar(32) NOT NULL,
  `Harga` varchar(32) NOT NULL,
  `Total` varchar(32) NOT NULL,
  `Retur` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ttemptransaksijual`
--

INSERT INTO `ttemptransaksijual` (`No_Transaksi`, `No_Pesanan`, `Tgl_Pesan`, `ID_Konsumen`, `Nama`, `User`, `Kode_Item`, `Nama_Item`, `Jumlah`, `Harga`, `Total`, `Retur`, `Keterangan`) VALUES
('NTPJ0604201600001', '', '2016-04-06', 'KNSM-0001', 'NUGRAHA BANU AJIE', 'Admin', 'MTRL-00022', 'TRIPLEX 3MM PALM (4 X 8)', '20', '41000', '820000', '0', ''),
('NTPJ0604201600001', '', '2016-04-06', 'KNSM-0001', 'NUGRAHA BANU AJIE', 'Admin', 'MTRL-00010', 'RENG MERANTI (2 X 3 X 400)', '11', '12000', '132000', '0', ''),
('NTPJ0604201600002', 'NOPJ0604201600001', '2016-04-06', 'KNSM-0008', 'IWAN MULYAWAN', 'Admin', 'MTRL-00002', 'SEMEN TIGA RODA (50 KG)', '2', '63000', '126000', '', ''),
('NTPJ0604201600002', 'NOPJ0604201600001', '2016-04-06', 'KNSM-0008', 'IWAN MULYAWAN', 'Admin', 'MTRL-00008', 'KACA RAYBEN (3MM)', '5', '65000', '325000', '', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttransaksibeli`
--

CREATE TABLE IF NOT EXISTS `ttransaksibeli` (
  `No_Transaksi` varchar(32) NOT NULL,
  `No_Pesanan` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `ID_Supplier` varchar(32) NOT NULL,
  `Nama_Supplier` varchar(100) NOT NULL,
  `Jml_Item` varchar(32) NOT NULL,
  `Total_Transaksi` varchar(32) NOT NULL,
  `Jml_Bayar` varchar(32) NOT NULL,
  `Sisa_Bayar` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ttransaksibeli`
--

INSERT INTO `ttransaksibeli` (`No_Transaksi`, `No_Pesanan`, `Tanggal`, `ID_Supplier`, `Nama_Supplier`, `Jml_Item`, `Total_Transaksi`, `Jml_Bayar`, `Sisa_Bayar`, `Keterangan`, `Status`) VALUES
('NTPB0604201600001', '', '2016-04-06', 'SPLR-0006', 'CV. READYMIX', '20', '10000000', '100000000', '0', '', '1'),
('NTPB0604201600002', 'NOPB0604201600001', '2016-04-06', 'SPLR-0005', 'JAYA BARU', '5', '45000', '45000', '0', '', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ttransaksijual`
--

CREATE TABLE IF NOT EXISTS `ttransaksijual` (
  `No_Transaksi` varchar(32) NOT NULL,
  `No_Pesanan` varchar(32) NOT NULL,
  `Tanggal` date NOT NULL,
  `ID_Konsumen` varchar(32) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Jml_Item` varchar(32) NOT NULL,
  `Total_Transaksi` varchar(32) NOT NULL,
  `DP` varchar(32) NOT NULL,
  `Jml_Bayar` varchar(32) NOT NULL,
  `Sisa_Bayar` varchar(32) NOT NULL,
  `Keterangan` varchar(100) NOT NULL,
  `Status` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ttransaksijual`
--

INSERT INTO `ttransaksijual` (`No_Transaksi`, `No_Pesanan`, `Tanggal`, `ID_Konsumen`, `Nama`, `Jml_Item`, `Total_Transaksi`, `DP`, `Jml_Bayar`, `Sisa_Bayar`, `Keterangan`, `Status`) VALUES
('NTPJ0604201600001', '', '2016-04-06', 'KNSM-0001', 'NUGRAHA BANU AJIE', '31', '952000', '0', '1000000', '48000', '', '1'),
('NTPJ0604201600002', 'NOPJ0604201600001', '2016-04-06', 'KNSM-0008', 'IWAN MULYAWAN', '7', '451000', '150000', '350000', '49000', '', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tuser`
--

CREATE TABLE IF NOT EXISTS `tuser` (
  `ID_User` varchar(8) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Bagian` varchar(32) NOT NULL,
  `Password` varchar(150) NOT NULL,
  `Konfirmasi` varchar(150) NOT NULL,
  PRIMARY KEY (`ID_User`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tuser`
--

INSERT INTO `tuser` (`ID_User`, `Nama`, `Bagian`, `Password`, `Konfirmasi`) VALUES
('admin', 'nugraha', 'Admin', '$2a$10$mbN.1fdufG1pJD40LlzscOtvNtO3dQIbP7qI3txK08dZT20VnkoGe', '$2a$10$674GWxlsIjUYS0majg1Va.g00/toOl6ZMpJ6AChkXy0DWoKupiGUO'),
('gudang', 'ajie', 'Bagian Gudang', '$2a$10$YXxWLrZHuB0BAhlLERXcE.pdBOIY//fpqiyyXCgFdWublGYVZl.j.', '$2a$10$bqvTwjubGNHZhqD0DJ16ROoacc8lkMME.YZo5N2Wvl59KzB7SMeYm'),
('kasir', 'banu', 'Bagian Kasir', '$2a$10$xpoZM7kxFr9TbP5dyyX/AO5oF2Cz9BOg3T5Jzf8PhASnhDclhz38i', '$2a$10$yBg.LDhI61W/.Q3EtSv63OV.H9TxuLprChOocmRC4YIyGScwA5Edq');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
