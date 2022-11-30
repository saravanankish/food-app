create table if not exists t_restaurants (
	id varchar(40) not null, 
	city varchar(40) not null, 
	cuisine varchar(50) not null, 
	owner varchar(60), 
	pincode varchar(10), 
	restaurant_name varchar(100) not null, 
	state varchar(100),
	street varchar(100), 
	primary key (id)
);

create table if not exists t_menu (
	id varchar(40) not null, 
	description varchar(255), 
	item_name varchar(50), 
	price double, 
	restaurant_id varchar(40), 
	primary key (id),
	constraint restaurantIdConstraint foreign key (restaurant_id) references t_restaurants(id) on delete cascade
);
