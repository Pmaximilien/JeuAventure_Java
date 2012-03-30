CC=javac
SRC=${wildcard *.java}
OBJ=${SRC:.java=.class}



ALL:	$(OBJ)

%.class:	%.java
	$(CC) $<
clean:	
	rm $(OBJ)
