https://refactoring.guru/design-patterns/prototype/go/example

Let’s try to figure out the Prototype pattern using an example based on the operating system’s file system. The OS file system is recursive: the folders contain files and folders, which may also include files and folders, and so on.

Each file and folder can be represented by an inode interface. inode interface also has the clone function.

Both file and folder structs implement the print and clone functions since they are of the inode type. Also, notice the clone function in both file and folder. The clone function in both of them returns a copy of the respective file or folder. During the cloning, we append the suffix “_clone” to the name field.

