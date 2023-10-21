CREATE TABLE IF NOT EXISTS tb_product (
	id varchar(255) not null PRIMARY KEY,
    name varchar(255) not null,
    description varchar(255) not null,
    product_size int default 0,
    creation_at timestamp default null
);