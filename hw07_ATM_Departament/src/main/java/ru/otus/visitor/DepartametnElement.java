package ru.otus.visitor;

public interface DepartametnElement {
 //метод который принимает самого visitor
 void accept(Visitor visitor);
}
