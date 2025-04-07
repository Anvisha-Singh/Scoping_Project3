
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
-   **TestScope.pas**: Test case covering scoping, loops, and error detection.


## Requirements Implemented

### Language Features

-   **While-do and For-do Loops**: Simulated with hardcoded iterations:
    -   for  loops run 10 times (mimicking  1 to 10).
    -   while  loops run 3 times (mimicking  5 to 3  with a break).
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

### Prerequisites

-   **Java**: JDK 23 or compatible version 
-   **ANTLR**: Antlr jar in classpath

### Setup

1.  **Place Files:**
    -   Ensure all files (pascal.g4,  ScopeVisitor.java,  SymbolTable.java,  ScopeChecker.java,  TestScope.pas) are in your project directory (e.g.,  /Users/anvishasingh/IdeaProjects/Project3/).
    -   Place  antlr-4.13.2-complete.jar  in a known location (e.g.,  /Users/anvishasingh/Desktop/).
2.  **Generate Parser:**
    -   Run the ANTLR tool to generate parser and lexer:
        
  
        
        `java -jar /Users/anvishasingh/Desktop/antlr-4.13.2-complete.jar -visitor -package pascal pascal.g4`
        
    -   This generates  pascalLexer.java,  pascalParser.java, etc., in the  pascal  package.
3.  **Compile:**
    -   Compile all Java files with the ANTLR jar in the classpath:
        `javac -cp .:/Users/anvishasingh/Desktop/antlr-4.13.2-complete.jar *.java`
        

### Running the Program

-   Execute  ScopeChecker  with the test case:

    `java -cp .:/Users/anvishasingh/Desktop/antlr-4.13.2-complete.jar ScopeChecker`
    
-   **Input:**  Assumes  TestScope.pas  is in the current directory (modify  ScopeChecker  if the path differs).
-   **Output:**  Prints "hi" for each  writeln  call, scope exit messages, and any errors.

### Example Output



## Current Functionality

-   **Scoping:**  Tracks symbols in a stack of  SymbolTable  objects, detecting undeclared identifiers and scope violations.
-   **Simulation:**  Prints "hi" for  writeln  calls, with hardcoded loop iterations:
    -   for i := 1 to x  → 10 "hi"s.
    -   while x > 0  → 3 "hi"s (mimicking break at  x = 2).
-   **Errors:**  Reports undeclared variables and misplaced  break/continue.



## Test Case Details

-   ** Tests Covered :**
    -   Scoping: Global  i,  x, local  x  in  TestProc function.
    -   Errors: Undeclared  z used outside of scope
 


![image](https://github.com/user-attachments/assets/929a7e9d-b4ed-44c0-a756-393537195946)


Expected output 
<img width="1262" alt="image" src="https://github.com/user-attachments/assets/7d681cef-7ca4-4fb9-bde8-df35c7342c11" />

Using the variable outside of scope

![image](https://github.com/user-attachments/assets/9cdb9a6a-630e-4ffd-9fca-99271bb580a5)

output with undefined error

<img width="490" alt="image" src="https://github.com/user-attachments/assets/2d420831-6659-4e14-9575-63cfe1817081" />

## Test Case Details for break and continue

-   ** Tests Covered :**
    -   test the scope of break/continue keywords
    -   Errors: 'BREAK' used outside of a loop will throw error

<img width="803" alt="image" src="https://github.com/user-attachments/assets/9594db6c-b636-4683-9cf6-8c55be9af8ee" />

- As shown in the above test case the first break statement exits without any error  whereas the second break statement at line 15 will throw an error  beacuse it is outside of the loop
<img width="484" alt="image" src="https://github.com/user-attachments/assets/bbe64f8b-f3ae-49ac-828e-587f7e59474a" />







    
