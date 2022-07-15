JC = javac
FILES = *.java
DERIV = ${FILES:.java=.class}

all: Kiosk

Kiosk: 
	$(JC) $(FILES)

clean:
	rm -f $(DERIV) Kiosk
