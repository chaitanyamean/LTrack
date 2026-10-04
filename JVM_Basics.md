# JVM Basics

## What is JDK?

JDK stands for Java Development Kit.

It is mainly used by developers to create Java applications. It contains the tools required to write, compile and run Java programs.

For example, `javac` is a compiler provided by the JDK. It converts Java source code into bytecode.

---

## What is JRE?

JRE stands for Java Runtime Environment.

It provides the environment required to run a Java application. It includes the JVM and the libraries required by Java applications.

In simple terms:

- JDK is used to develop Java applications.
- JRE is used to run Java applications.
- JVM is the part that actually executes the Java bytecode.

---

## What is JVM?

JVM stands for Java Virtual Machine.

It is responsible for running Java bytecode.

When we compile a Java program, Java does not directly create machine code for a specific operating system. Instead, it creates bytecode. The JVM takes this bytecode and executes it on the machine.

Different operating systems can have different JVM implementations, but the same Java bytecode can run on all of them.

---

## What is Bytecode?

Bytecode is the intermediate code generated after compiling a Java program.

For example, if we have:

```java
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}