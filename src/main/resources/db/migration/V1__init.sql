CREATE TABLE category
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    created_at           datetime              NULL,
    updated_at           datetime              NULL,
    category_name        VARCHAR(255)          NULL,
    category_description VARCHAR(255)          NULL,
    sub_category_id      BIGINT                NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime              NULL,
    updated_at          datetime              NULL,
    product_title       VARCHAR(255)          NULL,
    product_price       DOUBLE                NULL,
    product_description VARCHAR(255)          NULL,
    category_id         BIGINT                NULL,
    product_image       VARCHAR(255)          NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE sub_category
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime              NULL,
    updated_at        datetime              NULL,
    sub_category_name VARCHAR(255)          NULL,
    CONSTRAINT pk_subcategory PRIMARY KEY (id)
);

ALTER TABLE category
    ADD CONSTRAINT FK_CATEGORY_ON_SUBCATEGORY FOREIGN KEY (sub_category_id) REFERENCES sub_category (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);