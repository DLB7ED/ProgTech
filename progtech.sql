-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2022 at 06:58 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `progtech`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `house_number` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `orders_to_products`
--

CREATE TABLE `orders_to_products` (
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `producer` varchar(255) DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL,
  `display` double DEFAULT NULL,
  `processor` varchar(255) DEFAULT NULL,
  `RAM` int(11) DEFAULT NULL,
  `storage_type` varchar(255) DEFAULT NULL,
  `storage` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `producer`, `series`, `display`, `processor`, `RAM`, `storage_type`, `storage`, `price`, `created_at`) VALUES
(2, 'ASUS', 'ROG', 15.6, 'AMD Ryzen 7 2.9 GHz', 8, 'SSD', 512, 348599, '2022-05-18 12:53:27'),
(3, 'ASUS', 'ROG', 15.6, 'AMD Ryzen 5 3.3 GHz', 16, 'SSD', 512, 349900, '2022-05-18 13:31:31'),
(4, 'ASUS', 'Zenbook 14', 14, 'Intel Core i5 1.6 GHz', 8, 'SSD', 256, 269900, '2022-05-17 10:32:27'),
(5, 'Lenovo', 'Ideapad 3', 15.6, 'Intel DualCore 1.8 GHz', 4, 'SSD', 256, 139980, '2022-05-17 10:32:27'),
(6, 'ASUS', 'Zenbook', 13.3, 'Intel Core i5 1.60 GHz', 8, 'SSD', 512, 282990, '2022-05-17 10:32:27'),
(7, 'Lenovo', 'Ideapad 3', 14, 'AMD Ryzen 3 2.60 GHz', 8, 'SSD', 256, 147199, '2022-05-17 10:32:27'),
(8, 'Acer', 'Nitro 5', 15.6, 'Intel Core i5 2.5 GHz', 8, 'SSD', 512, 289900, '2022-05-17 10:32:27'),
(9, 'ASUS', 'ZenBook 14', 14.1, 'Intel DualCore 1.1 GHz', 4, 'SSD', 128, 137799, '2022-05-17 10:32:52'),
(10, 'Lenovo', 'Ideapad Duet 3', 10.3, 'Intel DualCore 2.8 GHz', 4, 'eMMC', 64, 152290, '2022-05-17 10:32:27'),
(11, 'Lenovo', 'Legion 5', 15.6, 'AMD Ryzen 5 3.3 GHz', 16, 'SSD', 512, 479900, '2022-05-17 10:32:27'),
(13, 'Dell', 'Inspiron 5406', 14, 'Intel Core i3 3 GHz', 4, 'SSD', 256, 221199, '2022-05-17 10:32:27'),
(14, 'Lenovo', 'Ideapad 3', 15.6, 'Intel Core i3 3 GHz', 8, 'SSD', 256, 207299, '2022-05-17 10:32:27'),
(15, 'ASUS', 'VivoBook', 17.3, 'AMD Ryzen 5 2.1 GHz', 8, 'SSD', 256, 209899, '2022-05-17 10:32:27'),
(16, 'ASUS', 'TUF', 15.6, 'AMD Ryzen 7 2.3 GHz', 8, 'SSD', 512, 279990, '2022-05-17 10:32:27'),
(17, 'Apple', 'MacBook', 13.3, 'Apple M1 3.2 GHz', 8, 'SSD', 256, 489900, '2022-05-17 10:32:27'),
(18, 'ASUS', 'Zenbook', 14, 'AMD Ryzen 5 2.30 GHz', 16, 'SSD', 512, 319900, '2022-05-17 10:32:27'),
(19, 'Lenovo', 'Legion 5', 15.6, 'Intel Core i5 2.5 GHz', 8, 'SSD', 256, 388799, '2022-05-17 10:32:27'),
(21, 'Lenovo', 'Béla2', 13, 'Szép', 4, 'Pálinka', 12, 120000, '2022-05-18 13:30:31');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `is_admin` tinyint(4) NOT NULL DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `is_admin`, `created_at`) VALUES
(1, 'tzdjh', 'test@test.com', 'password', 0, '2022-05-15 15:51:05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders_to_products`
--
ALTER TABLE `orders_to_products`
  ADD PRIMARY KEY (`order_id`,`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
