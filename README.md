# Advanced Programming 2018

Solutions to Assignment 1
## Exercise 1
The system is composed by:
* a soil moisture sensor [(MoistureSensor)](https://bit.ly/2zHJooL), reporting on the relative humidity of the soil;
* a [Controller](https://bit.ly/2PpzAdG) that starts and stops the irrigation based on the data from the sensor and on the user input;
* a graphical dashboard [(DashboardFrame)](https://bit.ly/2QyKVVh) that visualises information from the sensor and allows to manually control the irrigation system.

![alt text](https://raw.githubusercontent.com/andybbruno/Advanced-Programming-2018/master/UML/ex1.png)

The communication between the classes is done through events. In order to be able to exchange messages, we need to have someone who talks and some who is listening. In this project, _Controller_ and _DashboardFrame_ extends [PropertyChangeListener](https://bit.ly/1zKkJb3), an interface who permits to _"listen"_ if any bounded property updates, whereas the _"speaker"_ is implemented through [PropertyChangeSupport](https://bit.ly/2FgPq5I) and its _"firePropertyChange"_ method.

## Exercise 2
* [CheckNPE](https://bit.ly/2z1khxy)
* [TestClass](https://bit.ly/2QybxWj) - test class #1
* [SimpleClass](https://bit.ly/2ROX9cy) - test class #2
![alt text](https://raw.githubusercontent.com/andybbruno/Advanced-Programming-2018/master/UML/ex2.png)

## Exercise 3
```
IMPORTANT MESSAGE: I've added a char '3' both on class names and package names 
in order to distinguish exercise 1 to exercise 3.
```
* [MoistureSensor](https://github.com/andybbruno/Advanced-Programming-2018/blob/master/Exercise3/MoistureSensor3/src/moisturesensor/MoistureSensor3.java)
* [Controller](https://github.com/andybbruno/Advanced-Programming-2018/blob/master/Exercise3/Controller3/src/controller/Controller3.java)
* [DashboardFrame](https://github.com/andybbruno/Advanced-Programming-2018/blob/master/Exercise3/IrrigationDashboard3/src/DashboardFrame3.java)
![alt text](https://raw.githubusercontent.com/andybbruno/Advanced-Programming-2018/master/UML/ex3.png)

