CREATE TABLE account_order (
  `id` BIGINT(20) NOT NULL,
  `account_id` BIGINT(20) NOT NULL,
  `product_id` BIGINT(20) NOT NULL,
  `product_count` INT(8),
  `order_amount_total` INT(8),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;