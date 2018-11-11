# Advanced Programming 2018 - Solutions to Assignment 1
Andrea Bruno 585457

## Exercise 1
The system is composed by:
* a soil moisture sensor [(MoistureSensor)](https://bit.ly/2FdJv11), reporting on the relative humidity of the soil;
* a [Controller](https://bit.ly/2qF7Oet) that starts and stops the irrigation based on the data from the sensor and on the user input;
* a graphical dashboard [(DashboardFrame)](https://bit.ly/2RPdrSS) that visualises information from the sensor and allows to manually control the irrigation system.

![alt text](https://raw.githubusercontent.com/andybbruno/Advanced-Programming-2018/master/Assignment_1/UML/ex1.png)

The communication between the classes is done through events. In order to be able to exchange messages, we need to have someone who talks and some who is listening. In this project, _Controller_ and _DashboardFrame_ extends [PropertyChangeListener](https://bit.ly/1zKkJb3), an interface who permits to _"listen"_ if any bounded property updates, whereas the _"speaker"_ is implemented through [PropertyChangeSupport](https://bit.ly/2FgPq5I) and its _"firePropertyChange"_ method. Moreover, as the image suggests, the MVC pattern has been respected.

## Exercise 2
* [CheckNPE](https://bit.ly/2JTuy2T) 

![alt text](https://raw.githubusercontent.com/andybbruno/Advanced-Programming-2018/master/Assignment_1/UML/ex2.png)

Given an array of Java classes names, [CheckNPE](https://bit.ly/2z1khxy) check if those classes are NPE sensible i.e., if invoking a constructor or a method with default parameter values, a _NullPointerException_ is thrown. In particular, the default value of a numeric primitive type parameter is _0_, of a boolean parameter is _false_, of a reference type parameter is _null_.

## Exercise 3
```
IMPORTANT MESSAGE: I've added a char '3' both on class names and package names 
in order to distinguish exercise 1 to exercise 3.
```
* [MoistureSensor3](https://github.com/andybbruno/Advanced-Programming-2018/blob/master/Assignment_1/Exercise3/MoistureSensor3/src/moisturesensor/MoistureSensor3.java)
* [Controller3](https://github.com/andybbruno/Advanced-Programming-2018/blob/master/Assignment_1/Exercise3/Controller3/src/controller/Controller3.java)
* [DashboardFrame3](https://github.com/andybbruno/Advanced-Programming-2018/blob/master/Assignment_1/Exercise3/IrrigationDashboard3/src/DashboardFrame3.java)
![alt text](https://raw.githubusercontent.com/andybbruno/Advanced-Programming-2018/master/Assignment_1/UML/ex3.png)

In addiction to all the logic implemented whitin Exercise 1, we were asked to implement constraint properties exploiting _VetoableChangeListener_. In my project, I chose to let _Controller3_ play the role of vetoing, because this class contains all the application logic. Hence, in order to respect MVC pattern, it hasn't been possible to develop required functionalities inside other classes.
