CREATE TABLE todo_list (
	id bigserial NOT NULL,
	created_at timestamp NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT todo_list_pkey PRIMARY KEY (id)
);

CREATE TABLE todo_item (
	id bigserial NOT NULL,
	todo_list_id int8 NOT NULL,
	created_at timestamp NOT NULL,
	description varchar(255) NOT NULL,
	done bool NOT NULL,
	done_at timestamp NULL,
	CONSTRAINT todo_item_pkey PRIMARY KEY (id),
	CONSTRAINT todoitem_todolist_fkey FOREIGN KEY (todo_list_id) REFERENCES todo_list(id)
);
