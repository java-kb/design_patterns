, network protocols ofte nwork with state expressions. As an example, I have chosen RFC 1939, which describes POP3.
RFC 1939 recognizes three different state specifications: AUTHORIZATION, 
TRANSACTION, and UPDATE. These are actually referred to as states in the RFC. The
POP3 server waits for requests. When a request arrives, a TCP connection is established.
The session is initially in the AUTHORIZATION state, where the user’s name and password are requested. If the name can be identified and the password is correct, the session
enters the TRANSACTION state. In this state, commands can be sent to the server. For
example, a list of stored e-mails can be requested. Furthermore, an e-mail can be marked
for deletion. When the QUIT command is received, the session changes to the UPDATE
state. In this state, the e-mails marked for deletion are actually deleted. Afterwards, the
connection is terminated.
If the QUIT command is issued in the AUTHORIZATION state, the session is terminated without UPDATE. Calling QUIT therefore has two completely different effects
depending on the state. Commands that refer to the stored messages are only permitted in
the TRANSACTION state.

Take a look at the sample POP3 project, which demonstrates the transition of these
three state expressions. I have stored four state classes in the states package: Three for
the states defined in the protocol and one class for the START state, which is needed when
the server is waiting for requests. There is also a class POP3_Session in this package.
This class is the context that the clients access. The client only “sees” the context. It has
no information that a variety of state classes are needed in the background