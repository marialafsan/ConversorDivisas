# Currency Converter

This project implements two independent Java applications: a child application that converts an amount between currencies, and a parent application that launches it as a separate process using **ProcessBuilder**, passes it the parameters, and collects and displays the result through the console.

## Why

> Java project developed to practice and demonstrate:

>- Launching a **child process** from a Java application with **ProcessBuilder**
>- Passing **command-line arguments** to a process
>- Reading a process's **standard output** and **error output**
>- Using **exit codes** to communicate success or failure between processes
>- **Error handling** across process boundaries
>- **Version control** using Git and GitHub

## How it works

>### **ConversorDivisas** *child*
>
>Receives three arguments: amount, original currency and target currency.
>
>It converts the amount to the target currency using fixed rates (1 EUR = 1.00 EUR / 1.10 USD / 0.85 GBP). It prints only the result with two decimals to standard output.
>
 >- Exit code **1**: Wrong number of arguments, or the amount is not a number
 >- Exit code **2**: One or both currencies are invalid or not supported

>### **LanzadorConversor**-*parent* 
>
>Builds the command `java -cp <classpath> <package.ConversorDivisas> <arguments>`, launches the child, reads its output and error streams, and waits for it with `waitFor()`. 
>
>It then displays either the result (e.g. `100 EUR -> USD: 110.00`) or an error message with the exit code and the child's error text.
