#------------------------------------------------------------------------------
# Makefile
# Erica Tom edtom
# pa1
#------------------------------------------------------------------------------ 

MAINCLASS = Lex
JAVAC   = javac
JAVASRC = $(wildcard *.java)
SOURCES = README Makefile $(JAVASRC)
CLASSES = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE = $(MAINCLASS)
SUBMIT = submit cmps101-pt.w17 pa1
CHECK = ls  /afs/cats.ucsc.edu/class/cmps101-pt.w17/pa1/edtom

all: $(JARFILE)
$(JARFILE): $(CLASSES)
        echo Main-class: $(MAINCLASS) > Manifest
        jar cvfm $(JARFILE) Manifest $(CLASSES)
        rm Manifest
        chmod +x $(JARFILE)
$(CLASSES): $(JAVASRC)
        javac -Xlint $(JAVASRC)
clean:
        rm $(CLASSES) $(JARFILE)
submit: $(SOURCES)
        $(SUBMIT) $(SOURCES)
check: 
        $(CHECK)
