create trigger azuriraj_kapacitet_sale after insert
on sjediste
for each row
update sala set
Kapacitet=Kapacitet+1 
where new.SALA_SalaID=SalaID;