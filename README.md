# Kotlin-NHF

# A program célja
A nagyházi feladatomban egy Btor2Circuit megvalósítását készítettem el, amely a Btor2 nyelvtan egy részhalmazát dolgozza fel. A nyelvtani szabályok összeállításában Ádám Zsófia nyújtott segítséget.
A program célja a Btor2 nyelvtan megismerése és értelmezése, valamint bizonyos nyelvi elemek részletesebb kifejtése volt. Emellett a Control Flow Automata (CFA) fogalmával való ismerkedés és egy demo elkészítése is része volt a projektnek.
A megvalósítás alapját a hivatalos btor2tools repository count2 példája adta, amelyet a Visitor minta segítségével valósítottam meg. Az eredményeket a Theta xcfa könyvtárával dolgoztam fel, amely egy CFA-t generál .dot fájlformátumban.

# Futtatás
A program futtatásához indítsuk el a Main.kt fájlt. Ez egy output.dot fájlt generál, amelyet például a webgraphviz segítségével megnyithatunk, így megtekinthető a CFA vizualizációja.
Az egyszerűség kedvéért a generált CFA-ról készült képet is elhelyeztem a könyvtárban, mivel a jelenlegi demo csupán egy példát tartalmaz.

# Felhasznált eszközök:
ANTLR
MIT Theta program
Ádám Zsófia által készített Btor2-frontend prototípus
Btor2 nyelvtan