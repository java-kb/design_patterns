# Before
Node objects interact directly with each other, recursion is required, removing a Node is hard, and it is not possible to remove the first node.
# After
A "mediating" List class focuses and simplifies all the administrative responsibilities, and the recursion (which does not scale up well) has been eliminated.

https://sourcemaking.com/design_patterns/mediator/cpp/2