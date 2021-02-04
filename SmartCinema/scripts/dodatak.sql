-- korisnicki nalog
create user 'student'@'localhost' identified by 'student';
grant select, insert, update, delete on movie.* to 'student'@'localhost';
grant execute on procedure movie.smanji_kapacitet_sale to 'student'@'localhost';
flush privileges;
