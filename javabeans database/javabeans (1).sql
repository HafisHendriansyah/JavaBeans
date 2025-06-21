-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 21 Jun 2025 pada 08.21
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javabeans`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `javabeans_akun`
--

CREATE TABLE `javabeans_akun` (
  `id_user` int(100) NOT NULL,
  `name_user` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password_user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `javabeans_akun`
--

INSERT INTO `javabeans_akun` (`id_user`, `name_user`, `username`, `email`, `password_user`) VALUES
(1, 'Alfaridzi Dieza', 'z4rshzy', 'alfaridzidieza@gmail.com', '12345678'),
(2, 'Haichal Abbrar', 'XXcal', 'abbrarhaichal@gmail.com', '123456789'),
(3, 'Hafis', 'hiz', 'hafishendryansah@gmail.com', '12345678'),
(4, 'Tajir Yanto', 'Tayato', 'Tajir@gmail.com', '12345678'),
(5, 'Fahriansyah', 'itsrian', 'rian@gmail.com', '528191c31809e8b00e7c26c7542910a70773483dbf235c29cad51a7938c87c09'),
(6, 'Haichal Abbrar', 'XXcal-ui', 'haichalabbrar1@gmail.com', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225');

-- --------------------------------------------------------

--
-- Struktur dari tabel `javabeans_menu`
--

CREATE TABLE `javabeans_menu` (
  `id` int(3) NOT NULL,
  `category_menu` varchar(100) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `harga_menu` decimal(10,2) NOT NULL,
  `gambar_menu` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `javabeans_menu`
--

INSERT INTO `javabeans_menu` (`id`, `category_menu`, `nama_menu`, `harga_menu`, `gambar_menu`) VALUES
(1, 'Coffee', 'Kopi salted caramel', 18000.00, ''),
(2, 'Coffee', 'Amerikano', 17000.00, ''),
(3, 'Non-Coffee', 'Matcha latte', 20000.00, ''),
(4, 'Non-Coffee', 'Red Velvet', 18000.00, ''),
(5, 'Coffee', 'Amerikano', 17000.00, ''),
(6, 'Coffee', 'Amerikano', 17000.00, '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `order_detail`
--

CREATE TABLE `order_detail` (
  `id_order` int(11) NOT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  `id_menu` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `sub_total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `order_detail`
--

INSERT INTO `order_detail` (`id_order`, `id_transaksi`, `id_menu`, `jumlah`, `sub_total`) VALUES
(1, 16, 4, 1, 18000),
(2, 17, 3, 1, 20000),
(3, 18, 4, 1, 18000),
(4, 19, 3, 1, 20000),
(5, 19, 4, 1, 18000),
(6, 20, 4, 1, 18000),
(7, 20, 3, 1, 20000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_menu` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `total_harga` double DEFAULT NULL,
  `tanggal_transaksi` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_menu`, `jumlah`, `total_harga`, `tanggal_transaksi`) VALUES
(1, 3, 1, 18000, '2025-06-16'),
(2, 4, 1, 22000, '2025-06-16'),
(3, 4, 1, 22000, '2025-06-16'),
(4, 3, 1, 18000, '2025-06-16'),
(6, 3, 2, 36000, '2025-06-18'),
(7, 4, 1, 22000, '2025-06-18'),
(8, 2, 1, 20000, '2025-06-18'),
(9, 3, 1, 20000, '2025-06-20'),
(10, 4, 1, 18000, '2025-06-20'),
(11, 3, 1, 20000, '2025-06-20'),
(12, 4, 1, 18000, '2025-06-20'),
(13, 3, 2, 40000, '2025-06-20'),
(14, 3, 1, 20000, '2025-06-20'),
(15, 3, 2, 40000, '2025-06-20'),
(16, 4, 1, 18000, '2025-06-20'),
(17, 3, 1, 20000, '2025-06-20'),
(18, 4, 1, 18000, '2025-06-20'),
(19, NULL, NULL, 38000, '2025-06-20'),
(20, NULL, NULL, 38000, '2025-06-20');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `javabeans_akun`
--
ALTER TABLE `javabeans_akun`
  ADD PRIMARY KEY (`id_user`);

--
-- Indeks untuk tabel `javabeans_menu`
--
ALTER TABLE `javabeans_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_menu` (`id_menu`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_menu` (`id_menu`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `javabeans_akun`
--
ALTER TABLE `javabeans_akun`
  MODIFY `id_user` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `javabeans_menu`
--
ALTER TABLE `javabeans_menu`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `javabeans_menu` (`id`);

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_menu`) REFERENCES `javabeans_menu` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
