# EulerTask
This repository is made for the Coding Task of Project Euler #54 Poker hands

## Before running the project, please update the File path in the environment variables (depending on your machine), so the code can work properly!

## How the solution works

![how the solution works at a hight level](https://raw.githubusercontent.com/kimmorais/EulerTask/master/eulertask/images/flowchart_poker_drawio.jpg)

## Which object-oriented programming ideas did you apply to solve the problem

Having the code organized around data/objects, getting together related data to create a common object to be (re)used across the application while using a handful of features that object-oriented programming languages provide to make use of Encapsulation and Inheritance, for example.

## What you like and do not like about your solution

I like the way it is implemented, I believe it is implemented in an easy-to-understand way. Though I believe that it being a linear solution, there is room for improvement if parallelism is implemented (for larger inputs, it may be a faster solution).

## Which of the technologies or approaches used were new to you

I tried to have a good grasp on SOLID principles while implementing this solution.

### S.olid (Single responsibility principle)
#### "A class should have only one responsibility." Eg.: if you want to change something regarding the Parsing processing, you should only change the Parser class.

### s.O.lid (Open-closed principle)
#### "Software entities should be open for extension, but closed for modification." Eg.: if you want to add a new validation for a Ranking, you can extend from the RankingValidator interface.

### sol.I.d (Interface segregation principle)
#### "No code should be forced to depend upon interfaces that they do not use." All classes relying on interfaces only have implemented the method it really needs to.

### soli.D. (Dependency inversion principle)
#### "Depend upon abstractions, not concretions." The classes that are used to do the processing of each hand rely only on abstraction (interfaces) of other classes, not on their implementation.
