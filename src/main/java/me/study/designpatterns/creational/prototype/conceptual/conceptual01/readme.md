# Prototype design pattern
https://sourcemaking.com/design_patterns/prototype/java/2

1. Create a "contract" with clone() and getName() entries
1. Design a "registry" that maintains a cache of prototypical objects
1. Populate the registry with an initializePrototypes() function
1. The registry has a findAndClone() "virtual constructor" that can transform a String into its correct object (it calls clone() which then calls "new")
1. All classes relate themselves to the clone() contract
1. Client uses the findAndClone() virtual ctor instead of the "new" operator
