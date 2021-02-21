CREATE TABLE tb_purchase (
    id int8 NOT NULL,
    client_id int8 NOT NULL,
    purchased_books int8 NOT NULL,
    amount_to_pay float8 NOT NULL,
    purchase_status VARCHAR(10) NOT NULL,
    CONSTRAINT purchase_id PRIMARY KEY (id)
);

ALTER TABLE tb_purchase ADD CONSTRAINT client_id_fk FOREIGN KEY (client_id) REFERENCES tb_client(id);

ALTER TABLE tb_purchase ADD CONSTRAINT purchased_books_fk FOREIGN KEY (purchased_books) REFERENCES tb_book(id);