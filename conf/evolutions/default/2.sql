# Scores schema

# --- !Ups
insert into `conveyordb`.`scores`(name,scores) values ('a-person', '234');
insert into `conveyordb`.`scores`(name,scores) values('x-person', '0.5');
insert into `conveyordb`.`scores`(name,scores) values('y-person', '199');
insert into `conveyordb`.`scores`(name,scores) values('c-person', '4');

# --- !Downs
delete from `conveyordb`.`scores` WHERE name = 'a-person';
delete from `conveyordb`.`scores` WHERE name = 'x-person';
delete from `conveyordb`.`scores` WHERE name = 'y-person';
delete from `conveyordb`.`scores` WHERE name = 'c-person';
