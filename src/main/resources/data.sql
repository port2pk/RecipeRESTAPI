insert  into  recipe (recipe_id,recipe_cd,recipe_creation_date,recipe_modification_date,recipe_is_active,recipe_for_person) 
VALUES(1,'CRISPY COCONUT OATMEALS',CURDATE(),CURDATE(),true,3);

/*
insert into ingredients (ingredients_id,recipe_cd,items_used) 
VALUES(1,'CRISPY COCONUT OATMEALS','1/2 cup solid vegetable shortening.1/2 cup margarine.1/2 cup packed brown sugar.1/2 cup sugar.2 eggs');
update ingredients set recipe_id=1 where ingredients_id=1;*/