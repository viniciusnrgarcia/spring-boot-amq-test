select
max(creation_at) - min(creation_at)  as t,
min(creation_at) ,
max(creation_at) ,
count(1)
from tb_product tp ;


select count(1)
from tb_product tp ;

delete from tb_product tp ;