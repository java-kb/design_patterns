# Families of cross-platform GUI components and their production

***Source: https://refactoring.guru/design-patterns/abstract-factory/java/example***

In this example, buttons and checkboxes will act as products. They have two variants: macOS and Windows.

The abstract factory defines an interface for creating buttons and checkboxes. There are two concrete factories, which return both products in a single variant.

Client code works with factories and products using abstract interfaces. It makes the same client code working with many product variants, depending on the type of factory object.

