CREATE SCHEMA security AUTHORIZATION postgres;

CREATE table
    security.tbl_authuser (
        id VARCHAR(255) PRIMARY KEY,
        user_name VARCHAR(255),
        password VARCHAR(255)
    );

CREATE SCHEMA products AUTHORIZATION postgres;

CREATE table
    products.tbl_regions (
        id VARCHAR(255) PRIMARY KEY,
        name VARCHAR(255)
    );

CREATE table
    products.tbl_customers (
        id VARCHAR(255) PRIMARY KEY,
        number_id VARCHAR(255),
        first_name VARCHAR(255),
        last_name VARCHAR(255),
        email VARCHAR(255),
        photo_url VARCHAR(255),
        region_id VARCHAR(255),
        state VARCHAR(255),
        CONSTRAINT fk_customer_region FOREIGN KEY (region_id) REFERENCES products.tbl_regions(id)
    );

INSERT INTO products.tbl_regions (id, name) VALUES (1, 'Sudamérica');

INSERT INTO
    products.tbl_regions (id, name)
VALUES (2, 'Centroamérica');

INSERT INTO
    products.tbl_regions (id, name)
VALUES (3, 'Norteamérica');

INSERT INTO products.tbl_regions (id, name) VALUES (4, 'Europa');

INSERT INTO products.tbl_regions (id, name) VALUES (5, 'Asia');

INSERT INTO products.tbl_regions (id, name) VALUES (6, 'Africa');

INSERT INTO products.tbl_regions (id, name) VALUES (7, 'Oceanía');

INSERT INTO products.tbl_regions (id, name) VALUES (8, 'Antártida');

INSERT INTO
    products.tbl_customers (
        id,
        number_id,
        first_name,
        last_name,
        email,
        photo_url,
        region_id,
        state
    )
VALUES (
        1,
        '32404580',
        'Andrés',
        'Guzmán',
        'profesor@bolsadeideas.com',
        '',
        1,
        'CREATED'
    );

CREATE table
    products.tbl_categories (
        id VARCHAR(255) PRIMARY KEY,
        name VARCHAR(255)
    );

CREATE TABLE
    products.tbl_products (
        id VARCHAR(255) PRIMARY KEY,
        name VARCHAR(255),
        description TEXT,
        stock INT,
        price DECIMAL(10, 2),
        status VARCHAR(20),
        create_at DATE,
        category_id VARCHAR(255),
        CONSTRAINT fk_produc_categori FOREIGN KEY (category_id) REFERENCES products.tbl_categories(id)
    );

INSERT INTO products.tbl_categories (id, name) VALUES ('1', 'shoes');

INSERT INTO products.tbl_categories (id, name) VALUES ('2', 'books');

INSERT INTO
    products.tbl_categories (id, name)
VALUES ('3', 'electronics');

INSERT INTO
    products.tbl_products (
        id,
        name,
        description,
        stock,
        price,
        status,
        create_at,
        category_id
    )
VALUES (
        '1',
        'adidas Cloudfoam Ultimate',
        'Walk in the air in the black / black CLOUDFOAM ULTIMATE running shoe from ADIDAS',
        5,
        178.89,
        'CREATED',
        '2018-09-05',
        '1'
    );

INSERT INTO
    products.tbl_products (
        id,
        name,
        description,
        stock,
        price,
        status,
        create_at,
        category_id
    )
VALUES (
        '2',
        'under armour Men ''s Micro G Assert – 7',
        'under armour Men ''Lightweight mesh upper delivers complete breathability . Durable leather overlays for stability ',
        4,
        12.5,
        'CREATED',
        '2018-09-05',
        '1'
    );

INSERT INTO
    products.tbl_products (
        id,
        name,
        description,
        stock,
        price,
        status,
        create_at,
        category_id
    )
VALUES (
        '3',
        'Spring Boot in Action',
        'under armour Men '' Craig Walls is a software developer at Pivotal and is the author of Spring in Action',
        12,
        40.06,
        'CREATED',
        '2018-09-05',
        '2'
    );

CREATE table
    products.tlb_invoices (
        id VARCHAR(255) PRIMARY KEY,
        number_invoice VARCHAR(255),
        description VARCHAR(255),
        customer_id VARCHAR(255),
        create_at DATE,
        state VARCHAR(255)
    );

INSERT INTO
    products.tlb_invoices (
        id,
        number_invoice,
        description,
        customer_id,
        create_at,
        state
    )
VALUES (
        1,
        '0001',
        'invoice office items',
        1,
        NOW(),
        'CREATED'
    );

CREATE table
    products.tbl_invoice_items (
        id VARCHAR(255) PRIMARY KEY,
        invoice_id VARCHAR(255),
        product_id VARCHAR(255),
        quantity INT,
        price DECIMAL(10, 2),
        CONSTRAINT fk_item_invoice FOREIGN KEY (invoice_id) REFERENCES products.tlb_invoices(id),
        CONSTRAINT fk_item_product FOREIGN KEY (product_id) REFERENCES products.tbl_products(id)

);

INSERT INTO
    products.tbl_invoice_items (
        id,
        invoice_id,
        product_id,
        quantity,
        price
    )
VALUES ('affdokk', 1, 1, 1, 178.89);

INSERT INTO
    products.tbl_invoice_items (
        id,
        invoice_id,
        product_id,
        quantity,
        price
    )
VALUES ('fcapok', 1, 2, 2, 12.5);

INSERT INTO
    products.tbl_invoice_items (
        id,
        invoice_id,
        product_id,
        quantity,
        price
    )
VALUES ('pdlafs', 1, 3, 1, 40.06);