# Algorithms_project
Optimal Portfolio Allocation Problem Statement:
 You work for a prestigious investment firm that manages a large number of clients’ portfolios. Your task is to develop a program that determines the optimal allocation of assets in a given investment portfolio.
The portfolio consists of N different assets, each with an associated expected return and risk level. Your program should take as input the expected return and risk of each asset, along with the total investment amount available. The goal is to find the allocation of assets that maximizes the portfolio’s expected return while keeping the risk within a specified tolerance level.
Your program should solve this problem using two different algorithm paradigms: Brute Force and Dynamic Programming.

Project Part 1: Brute Force Approach
Implement a brute force algorithm to exhaustively search all possible asset allocations within the given investment amount. Evaluate the return and risk of each allocation and find the one that maximizes the expected return while keeping the risk within the specified tolerance level.





Sample run:
Input will be in the form of a text file your program should read. The input has the following format:
ID-of-asset : Expected-return : Risk-level : Quantity(units)
Example.txt:
Example 1:
AAPL : 0.05 : 0.02 : 1000 GOOGL : 0.08 : 0.03 : 500 MSFT : 0.04 : 0.015 : 800 
Total investment is 1000 units
Risk tolerance level is 0.024



Example 2:
AMZN : 0.07 : 0.04 : 600 TSLA : 0.1 : 0.05 : 400
FB : 0.06 : 0.025 : 700
Total Investment is 900 units
Risk Tolerance Level is 0.038




Output Sample:
Example 1: 
Optimal Allocation:
AAPL: 200 units
GOOGL: 300 units
MSFT: 500 units
Expected Portfolio Return: 0.064
Portfolio Risk Level: 0.024

Example 2:
Optimal Allocation: AMZN: 100 units
TSLA: 300 units 
FB: 500 units
Expected Portfolio Return: 0.079 
Portfolio Risk Level: 0.038
