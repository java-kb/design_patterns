# Book Title Change

https://sourcemaking.com/design_patterns/state/php

In the State Pattern a class will change it's behavior when circumstances change.

In this example, the BookContext class holds an implementation of the BookTitleStateInterface, starting with BookTitleStateStars. BookTitleStateStars and BookTitleStateExclaim will then replace each other in BookContext depending on how many times they are called.