alter table notes
add column category varchar(20);

update notes
set category = 'Default';