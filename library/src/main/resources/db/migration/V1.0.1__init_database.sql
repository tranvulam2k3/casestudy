use manager_library_n1224c1;

create table book
(
    id Integer auto_increment primary key,
    title nvarchar(50),
    author nvarchar(50),
    genre nvarchar(50),
    quantity int,
    price DECIMAL,
    status nvarchar(50),
    image nvarchar(225)
);
