## Before
The interface for "operations" are specified in the Color base class and implemented in the Color derived classes.

## After
The Color hierarchy specifies a single "accept()" method, and then the previous "count()" and "call()" methods are implemented as Visitor derived classes. When accept() is called on a Color object, that is the first dispatch. When visit() is called on a Visitor object, that is the second dispatch; and the "right thing" can be done based on the type of both objects.