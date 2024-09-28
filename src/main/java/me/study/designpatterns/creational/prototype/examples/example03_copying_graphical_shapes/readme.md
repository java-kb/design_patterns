# Copying graphical shapes
https://refactoring.guru/design-patterns/prototype/java/example

Let’s take a look at how the Prototype can be implemented without the standard Cloneable interface.

## Prototype registry
You could implement a centralized prototype registry (or factory), which would contain a set of pre-defined prototype objects. This way you could retrieve new objects from the factory by passing its name or other parameters. The factory would search for an appropriate prototype, clone it and return you a copy.