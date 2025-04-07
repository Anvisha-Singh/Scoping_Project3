
# Project 3: Pascal Scope Checker 

## Overview

This project implements a subset of Delphi / Turbo Pascal features using ANTLR 4, focusing on static scoping and basic execution simulation. The implementation uses ANTLR's built-in parse tree generator and a visitor pattern (ScopeVisitor) to analyze and simulate the program. Currently, it supports:

-   Static scoping for blocks (while,  for, procedures,  begin-end).
-   Detection of undeclared identifiers and scope-related errors (e.g.,  break  outside loops).
-   Simulated execution of  while  and  for  loops by printing "hi" for each  writeln  call.

The main goal is to capture symbolic information and simulate execution without full evaluation, as per the project requirements. 

## Files

-   **pascal.g4**: ANTLR grammar file defining the Pascal subset (assumed based on your code).
-   **ScopeVisitor.java**: Visitor class implementing scoping and simulation logic.
-   **SymbolTable.java**: Class for managing symbols in each scope.
-   **ScopeChecker.java**: Main class to parse and run the visitor
-   **test1.pas**: Test case covering scoping, loops, and error detection.


## Requirements Implemented

### Language Features

-   **While-do and For-do Loops**: Simulated with hardcoded iterations:
    -   while  loops run 3 times (mimicking  0 to 3  with a break).
-   **Break and Continue Keywords**: 
-   **User-defined Procedures**: Supported with static scoping (e.g.,  Show  procedure).
-   **Scoping**: Static scoping implemented:
    -   New scopes for  while,  for, procedures, and  begin-end  blocks.
    -   Global variables visible in procedures; local scopes override as needed.
    -   Scope chaining via a  SymbolTable  stack.

### Test Cases

-   **TestScope.pas**: Tests scoping, loops, and error detection:
    -   Global and local variable declarations.
    -   Nested scopes with  for,  while, and procedures.
    -   Errors: undeclared variable (y),  break  outside a loop.


## How to Run


1. Generate ANTLR Lexer and Parser

    java -jar < -path to the antlr jar file > Delphi.g4 -Dlanguage=Java -visitor
   
    java -jar /usr/local/lib/antlr-4.13.2-complete.jar pascal.g4 -Dlanguage=Java -visitor

3. Compile the files
   
     javac -cp ".:/usr/local/lib/antlr-4.13.2-complete.jar" *.java
 
5. Run the interpreter with the testcase file

 java -cp ".:/usr/local/lib/antlr-4.13.2-complete.jar" ScopeChecker < -test case file name>
 
 java -cp ".:/usr/local/lib/antlr-4.13.2-complete.jar" ScopeChecker test2.pas

        

### Running the Program
    
-   **Input:**  Assumes  test1.pas  is in the same directory as all other files

### Example Output

## Current Functionality

-   **Scoping:**  Tracks symbols in a stack of  SymbolTable  objects, detecting undeclared identifiers and scope violations.
-   **Simulation:**  Prints "hi" for  writeln  calls, with hardcoded loop iterations:
    -   for i := 0 to 3  → 3 "hi"s.
    -   while x < 3  → 3 "hi"s (mimicking break at  x = 3).
-   **Errors:**  Reports undeclared variables and misplaced  break/continue.



## Test Case Details

-   ** Tests Covered :**
    -   Scoping: Global  i,  x, local  x  in  TestProc function.
    -   Errors: Undeclared  z used outside of scope
 


<img width="600" alt="image" src="https://github.com/user-attachments/assets/929a7e9d-b4ed-44c0-a756-393537195946" />

Expected output 

<img width="600" alt="image" src="https://github.com/user-attachments/assets/7d681cef-7ca4-4fb9-bde8-df35c7342c11" />

Using the variable outside of scope

<img width="600" alt="image" src="https://github.com/user-attachments/assets/9cdb9a6a-630e-4ffd-9fca-99271bb580a5" />

output with undefined error

<img width="600" alt="image" src="https://github.com/user-attachments/assets/2d420831-6659-4e14-9575-63cfe1817081" />

## Test Case Details for break and continue

-   ** Tests Covered :**
    -   test the scope of break/continue keywords
    -   Errors: 'BREAK' used outside of a loop will throw error

<img width="600" alt="image" src="https://github.com/user-attachments/assets/9594db6c-b636-4683-9cf6-8c55be9af8ee" />

- As shown in the above test case the first break statement exits without any error  whereas the second break statement at line 15 will throw an error  beacuse it is outside of the loop
<img width="600" alt="image" src="https://github.com/user-attachments/assets/bbe64f8b-f3ae-49ac-828e-587f7e59474a" />

## Test Case Details for control flow of break/continue

-   ** Tests Covered :**
    -   tests the control flow for the program when break/continue keywords are used
    -   exits the loop after running specified number of times
    -   
![image](https://github.com/user-attachments/assets/49449a01-1905-4f42-879c-e038fa23692a)

- Hi is printed only 3 times after which it exits the loop

![image](https://github.com/user-attachments/assets/99afaa91-0ddc-4bfa-9d51-f71cf380a012)










    
