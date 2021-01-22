CREATE TABLE tb_client (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INTEGER NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL,
    sexo VARCHAR(50) NOT NULL
);

INSERT INTO tb_client(
    name,
    age,
    phone,
    email,
    sexo
) VALUES (
    'Josinaldo',
     20,
     '32323233',
     'josinaldo@gmail.com',
     'masculino'
);
