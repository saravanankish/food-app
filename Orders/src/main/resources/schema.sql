create table if not exists t_orders (
	order_id varchar(40) not null, 
	order_status varchar(20), 
	total_price double, 
	primary key (order_id)
);

create table if not exists t_order_line_items (
	id bigint not null auto_increment, 
	item_id varchar(40), 
	quantity integer, 
	order_id varchar(40), 
	primary key (id),
	constraint orderLineItemConstraint foreign key (order_id) references t_orders (order_id) on delete cascade
);