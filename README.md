# OOP Concepts

## Topics covered

Classes, objects, properties and methods, encapsulation and inheritance.

## Goal achieved

By the end of the exercise you will have upgraded your query command line tool to have optimized data structures, following OOP principles and perform additional operations.

More specifically, you will make the following changes:

- Nicely Structured project, following OOP concepts.
- Refactorings as per instructions.
- Functionalities as per Employees or guest users.
- Allow users to not continue while entering username and password during ordering.

## Data

This time, the data that you will use to write your query tool will be list of objects.

The exercise includes Repository classes to transform the original json data in the `stock.json` and `personnel.json` files into objects that are stored in memory.

The data will also be structured slightly different from this point. Each warehouse will be an object with a property `stock` that is a list of item objects.

Copy the file [sample/WarehouseRepository.java](sample/WarehouseRepository.java) into `com.dci.java.data` package in your project.

## Description

As usual, you will start with the traditional refactoring, this time into classes and the new stock data structure. This process can be a little more complex than the one you did with functions. It involves various steps:

1. Analyze and decide which classes are required.
2. Implement the classes in Java.
3. Transform the original data set into the new class-based data set.
4. Refactor the original files to accommodate the new class-based data structure.

An experienced developer will require some time to do all of this, and you may chose to do so as well, but the exercise provides you with a solution for the first and third steps that will help you concentrate on the most important parts of the topics covered.

### 1. Classes

You will define classes for the two main data domains of the tool: stock and personnel.

You should certainly consider a class `Item`, but you can also consider a class `Warehouse`. This class can be useful, as it will allow to add methods such as `addItem`, `removeItem` or `search` and properties such as `stock`, `capacity` or `location`. Not all will be implemented now, but this will give you more flexibility in the future.

For the users, you should also consider a class `Employee`, but since the tool can also be used by guests, you may need to define a more general `User` class. They have quite a few differences, but **an employee is a type of user**.

You can use any approach you like, but if you are uncertain on how to do it you may want to read the following guidelines:

#### Stock

##### Warehouse

| Property   | Type | Default |
|------------|------|---------|
| id         | int  | None    |
| stock      | List | []      |

Both properties need to be properties of the object, not just the class. Otherwise, the stock would be shared among all warehouses and the desired solution this time is to have each Warehouse manage its own stock (after all, an item cannot be in two warehouses at the same time).

| Method     | Input                    | Output |
|------------|--------------------------|--------|
| Warehouse | **warehouseId**: int    | None   |
| occupancy  | None                     | int    |
| addItem   | **item**: \<Item>        | None   |
| search     | **searchTerm**: String     | List   |

The constructor method will have an input argument that will be the id of the warehouse as an integer, and it will store this value in the object's `id` property. It does not require a `stock` argument because the stock will be managed with the `addItem` method. Instead, it must simply initialize the `stock` attribute as an empty list.

The `occupancy` method will not take any argument and will return an integer representing the number of items in stock.

The `addItem` method will take an instance of `Item` as an argument. This method should simply add the given object to the `stock` property.

The `search` method will search the items in the current warehouse and will return the list of matching items according to the `searchTerm` passed.

Once this class is done, you should write the `Item` class, which is being used by the `Warehouse` class you just wrote.

##### Item

| Property      | Type         | Default |
|---------------|--------------|---------|
| state         | String       | None    |
| category      | String       | None    |
| dateOfStock   | Date         | None    |
| warehouse     | int          | None    |

Each one of these properties will be supplied to the constructor method below at the moment of instantiating the objects.

The items will be stored in the `stock` property of each `Warehouse` object. Though the item does not need `warehouse` property, we are keeping it to make our continuation easy.

| Method     | Input                                                                                    | Output |
|------------|------------------------------------------------------------------------------------------|--------|
| Item | **state**: String, **category**: String, **warehouse**: int, **dateOfStock**: Date | None   |
| toString  | None                                                                                     | String   |

The constructor method will receive all those arguments, and should store them all as object properties, except the warehouse.

The `toString` method is used to return a string representation of the object. This method is called automatically when we use the object as a string (ex: `System.out.println(item);`).

This method must return the concatenation of the properties `state` and `category`.

Add the getters/setters methods for all the properties, no argument constructor and all argument constructor.

#### Personnel

##### User

| Property         | Type | Default     |
|------------------|------|-------------|
| name            | String | "Anonymous" |
| isAuthenticated | boolean | false       |

The `name` property will be a protected property. If the user provides an empty string as their name, their name should be "Anonymous".

The `isAuthenticated` property will be set to `false` and it will not change for the standard users, but it will still be defined as a property to make the main code simpler (here, you will check `isAuthenticated` without having to check first if it is an employee or a guest).

| Method       | Input              | Output |
|--------------|--------------------|--------|
| User   | **userName**: String| None   |
| authenticate | **password**: String | false  |
| isNamed     | **name**: String     | boolean   |
| greet        | None               | None   |
| bye          | **actions**: List  | None   |

The constructor method takes an argument named `userName` and it will store this value in the `name` object property.

The `authenticate` method will always return `false`. Like `isAuthenticated`, this method is just a placeholder for the feature and to allow us to have a simpler code in `TheWarehouseManager.java`. The `Employee` class will override this method.

The `isNamed` method will return `true` if the name passed to the method equals the `this.name` property. Since `name` is protected we will need a way to check if the user is the one we want.

The `greet` method will print a welcoming message to the user. The message for this user object will be:

```
Hello, {name of the user}!
Welcome to our Warehouse Database.
If you don't find what you are looking for,
please ask one of our staff members to assist you.
```

The `bye` method will print a *thank you* message. To minimize speculations on how the system handles the log data, it has been decided that the guest user will not be shown the summary of its actions.

##### Employee

The `Employee` class will extend the `User` class, so it will have its same properties and methods, plus the following ones:

| Property   | Type              | Default |
|------------|-------------------|---------|
| password | String              | None    |
| headOf    | List(\<Employee>) | []      |

The `password` property is private and should not be used anywhere other that this class' methods.

The `headOf` property stores a list of `Employee` objects, by default it is an empty list.

| Method       | Input                                                               | Output |
|--------------|---------------------------------------------------------------------|--------|
| Employee   | **userName**: String, **password**: String, **headOf**: List (optional) | None   |
| authenticate | **password**: String                                                  | boolean   |
| order        | **item**: String, **amount**: int                                  | None   |
| greet        | None                                                                | None   |
| bye          | **actions**: List                                                   | None   |

The constructor method will need to be overwritten. This time, you should make sure that the object is instantiated with both a `userName` and `password` arguments. These arguments are compulsory.

The argument `headOf` is a list of employee and is optional. If there is such argument, the constructor should save it in the `headOf` property as a list of `Employee` objects.

The `authenticate` method will also be overwritten, because in this case we need to check if the password is valid. The method will return `true` if the argument `password` matches the property `password` of the object.

The `order` method will print the name of the item and amount ordered by the user when they place an order.

The `greet` method will also print a message, but the message to employees will be different:

```
Hello, {name of the user}!
If you experience a problem with the system,
please contact technical support.
```

The `bye` method will also print a *thank you* message but, additionally, it will print the summary of actions taken during the session. This method should call the parent method to print the message defined there and then print the list of actions. It should not redefine the `Thank you for your visit, {name}!` message.

### 2. Implementing the classes

Inside the package `com.dci.java.data`, define your classes. If you are following these guidelines and you want to use the [Repository classes supplied](), name the classes and methods as indicated on section [1. Classes](#1-classes).

From now on, we recommend you to split the task in two. It may be easier to finish the job if you concentrate first on implementing and refactoring all the classes and features related to the stock, for instance, rather than implementing everything, then changing all the data and then refactoring the main script.

You should start implementing the classes related to the stock: `Warehouse` and `Item`. Before you try them on your query tool, you may want to use a Test class with main method to do explicit tests of your classes with hard-coded data. For instance:

```
public class Test {

	public static void main(String[] args) {		
		Warehouse one = new Warehouse(1);
		Warehouse two = new Warehouse(2);
		one.addItem(new Item("barnd new", "keyboard", 0, new Date()));
		System.out.println("should result 1 : ");
		System.out.println(one.occupancy());
		System.out.println("should result 0 : ");
		System.out.println(two.occupancy());
	}

}
```

Once you have done that, try [loading the data](#3-loading-the-data) and then [refactor your project](#4-refactoring) to use this new data set.

When the stock related features work as they are expected, then come back here and implement the classes `User` and `Employee` and follow the same steps.

### 3. Loading the data

We'll use the new Repository classes i.e WarehouseRepository and UserRepository to load the data.

Just after copying the new provided Repository classes, you can test them from your Test class as :

```
for (Warehouse warehouse : WarehouseRepository.WAREHOUSE_LIST) {
		System.out.println(warehouse.toString());
		}
```


### 4. Refactoring

The first step is to load the new data sets using the [Repository classes]() that you should have copied into your project's `com.dci.java.data` package.
You can create a class named UserRepository referencing the existing PersoneelRepository class. Refactor isUserValid and getAllEmployees method to use the newly created Employee class. Add a method isUserEmployee which takes the employees name and return a boolean value as per the existence of the name in personnel.json.

Optionally you can copy/paste the UserRepository.java file provided in the sample directory and implement the method (isUserEmployee(String name)).

You can delete the classes: Person.java, PersonnelRepository.java, StockRepository.java etc after making sure their functionalities have been transferred to the new classes.

If you are only doing the stock first, load only the stock as indicated previously in the Test class for testing:

```
for (Warehouse warehouse : WarehouseRepository.WAREHOUSE_LIST) {
		System.out.println(warehouse.toString());
		}
```

It is best if, before executing the program to see which error appears, you give first a look at your code and try to identify which changes you need to apply. Some general things we know before-hand are:

- We now have list of warehouses. You will have to iterate first each warehouse and then their respective `stock` list to perform operations such as listing or searching items.

- The warehouses have the methods `occupancy()` and `search()`, use them when you can.


- The users will receive a different greeting depending on whether they are guest users or employees. This means that, once the user enters the name, the program should search into the personnel json file to find if there is such a user. If not, then it should create a new instance of the User class with the given name.

	> It may be more effective to store the user object in the global scope rather than the `userName` and `isAuthenticated` properties separately.

In TheWarehouseManager.java file ->
- Refactor to use WarehouseRepository instead of StockRepository.
- Refactor to use UserRepository instead of PersonnelRepository.
- Refactor to use Employee instead of Person class.
- update the greetUser() method to greet differently for Employees and non-employees.
- update quit() method to use bye method of the Employee or User class instead of listSessionActions method.

> **Hint**   
>Add a static boolean variable IS_EMPLOYEE in TheWarehouseApp.java.
>- change it's state when greeting or when user escalates to employee.  
>- change it's state when userName changed (to a matching employee name) during item order time.  
>- add 'Do you want to continue' while re-asking username or password'
