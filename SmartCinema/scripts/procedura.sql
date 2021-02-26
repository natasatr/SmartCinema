
delimiter $$
create procedure smanji_kapacitet_sale(in pBroj int, in pRed int, in pSalaID int)
begin

update Sjediste set
Uklonjeno=1 where Broj=pBroj and Red=pRed and SALA_SalaID=pSalaID;
update sala set
Kapacitet=Kapacitet-1 where SalaID=pSalaID;

end $$
delimiter ;

-- call smanji_kapacitet_sale(2, 1, 1);
-- select @status;

delimiter $$
create procedure obrisi_salu(in pSalaID int)
begin
update sala set
Uklonjeno=1 where SalaID=pSalaID;
update sjediste set
Uklonjeno=1 where SALA_SalaID=pSalaID;
end $$
delimiter ;

call obrisi_salu(4);

-- mi postavljeno uklonjeno=1 za sjediste, tada je kapacitet sale manji za 1;