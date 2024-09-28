https://sourcemaking.com/design_patterns/prototype/cpp/1

# Before
The architect has done an admirable job of decoupling the client from Stooge concrete derived classes, and, exercising polymorphism. But there remains coupling where instances are actually created.

# After
A clone() method has been added to the Stooge hierarchy. Each derived class implements that method by returning an instance of itself. A Factory class has been introduced that main- tains a suite of "breeder" objects (aka proto- types), and knows how to delegate to the correct prototype.