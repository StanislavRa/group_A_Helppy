# group_A_Helppy

Desktop application which simulates a social platform for sharing job advertisements.<br>
Version of the current application is Minimum Viable Product (MVP).
So that some parts of source code have potential to be extended and scaled.  

## Getting Started

To run this application you need
 * Version of JDK 8
 * MySql with settings 
    * hostname: localhost
    * port: 3306
    * username: root
    * password: root
 
App can be launched by calling method in:
```
com.sda.main.App
```
Automatically will be created 4 dummy data accounts.<br> 
These accounts have already some Advertisements.<br> 
Accounts for logging in:

| Login         | Password      |
| ------------- | ------------- |
| Demi          | 0000          |
| Mariam        | 1111          |
| Oleks         | 2222          |
| Stan          | 3333          |

## Application Core Architecture

Main architecture is based on Model-View-Controller Pattern.

UML, ERD, User Story and User Story Views can be found here:
* [drawi.io](https://www.draw.io/#HStanislavRa%2Fgroup_A_Helppy%2Fmaster%2FHelppyDiagrams.drawio) - link

## Running the tests

All tests are unit tests.<br> 
So called ...DaoTests should be run manually one-by-one.<br> 
Reason:<br> 
DaoTest methods work correctly if current database tables are empty. 
So running bulk of methods will lead to collisions between data indexes.
<br> 
The rest of the tests can be run as usual without any restrictions. 

## Extendable parts of code

* Can add new type of users, e.g. manager, special customer etc.
* Can use subcategories.
* Can use HomeView window for some new features, e.g. shortcuts to search with specific criteria.
* Can find all advertisements of particular user

## Bugs

*  MyAdsController.java: 
    * After Customer creates or updates advertisement
     selecting row in the table does not populate datepicker fields anymore.


## Built With

* [Java FX](https://openjfx.io/) - Software platform for creating and delivering desktop applications
* [Maven](https://maven.apache.org/) - Dependency Management
* [Hibernate](https://hibernate.org/) - Object-relational mapping tool
* [JUnit](https://junit.org/) - Unit testing framework


## Authors

* **Demi** - *Front End* - [Demi](https://github.com/demiavalian)
* **Mariam** - *Code* - [Dgebu](https://github.com/Dgebu)
* **Oleksandr** - *Database* - [Oleks Oleks](https://github.com/Shpakovsky94)
* **Stanislav** - *Testing* - [Stanislav](https://github.com/StanislavRa)

## Acknowledgments

* Inspiration of Java advanced by [Hatef Palizgar](https://www.linkedin.com/in/hatefpalizgar/)
* Inspiration of DB by [Zino Adidi](https://github.com/zinoadidi)
* Used some code parts of [Jaret Wright](https://github.com/JaretWright/GUIDemo) in controllers 


