# Project-Graph-Algorithms
## Micaela Landauro
### UTD CS 3345 Spring 2022
This project was one of my final projects in my Data Structures and Algorithms class

### DESCRIPTION
  The main focus of this project was to find all possible flight plans between two cities.
  We would then calculate the time and cost for for all possible flight plans and then return
  the top three, based on either cost or time (selected by user).
  
### HOW IT WORKS
  Given an input files containing the given flight paths (routesA.txt or routesB.txt) the program
  builds an unidrected graph using a Linked List. Then, given another input file (pathsA.txt or pathsB.txt)
  containing the desired flight path, an iterative backtracking algorithm (Dijksta's Algorithm) performs an 
  exhaustive search of all possible flight paths. Finally the program returns an output file (outputFile.txt)
  containing the top three paths based on either cost or time decided by the user in the path file.
  
### PROGRAM INPUT
  The program will first ask for a Graph file, that would be either routesA.txt or routesB.txt
  Then the program will ask for a Path file, that would be either pathsA.txt or pathsB.txt
  routesA.txt corresponds to pathsA.txt and same for B
  
