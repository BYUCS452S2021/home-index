# Project Schema

## user_account

The `user_account` table stores all the data the application will use about the user that is logged in. This does not store the password or authentication pieces as those will most likely use a 3rd party service. A user owns one or more `property`.

### Schema

```sql
CREATE TABLE user_account (
	username varchar(25) NOT NULL UNIQUE,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	PRIMARY KEY (username)
);
```

## property

The `property` table represents a property that a user owns (e.g. a house or an apartment). A storage `space` is located within exactly one `property`. A `property` can be owned by more than one user, such as the case for a husband and wife, both users need to be able to view their stored data for a given `property`. Properties contain any number `space`'s.

### Schema

```sql
CREATE TABLE property (
	id int NOT NULL AUTO_INCREMENT,
	nickname varchar(100) NOT NULL,
	address text,
	PRIMARY KEY (id)
);
```

## user_property

This table is used to store the many-to-many relationship between `user_account` and `property`. Each row represents that a user is an owner of a given property.

### Schema

```sql
CREATE TABLE user_property (
	username varchar(25) NOT NULL,
	property_id int NOT NULL,
	PRIMARY KEY (username, property_id),
	FOREIGN KEY (username) REFERENCES user_account(username)
);
```

## space

The `space` table represents a storage space in the real world, for example:

- Upstairs Hallway Closet
- Devin's Closet
- Pantry
- Laundry Room Cabinet

A `space` is located within exactly one `property` and it contains any number of `container`'s.

### Schema

```sql
CREATE TABLE space (
	id int NOT NULL AUTO_INCREMENT,
	nickname varchar(100) NOT NULL,
	description text,
	property_id int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (property_id) REFERENCES property(id)
);
```

## container

A `container` represents boxes, baskets, bins, etc. A `container` is located in exactly one `space` and holds any number of `item`'s.

### Schema

```sql
CREATE TABLE container (
	id int NOT NULL AUTO_INCREMENT,
	nickname varchar(100) NOT NULL,
	description text,
	space_id int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (space_id) REFERENCES space(id)
);
```

## item

The `item` table is the most basic form of data in the database. It represents the items that a user will be taking inventory of. For example:

- Old Blue Striped Sweater
- Extra iPhone 8 Charger
- 18th Birthday Card from Grandma

An `item` is located within exactly one `container`.

### Schema

```sql
CREATE TABLE item (
	id int NOT NULL AUTO_INCREMENT,
	nickname varchar(100) NOT NULL,
	description text,
	photo TEXT,
	quantity int NOT NULL,
	monetary_value decimal(15,2),
	container_id int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (container_id) REFERENCES container(id)
);
```