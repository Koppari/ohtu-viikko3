package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa implements Komento {

    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
    Button undo;
    int edellinen;
    Sovelluslogiikka sovellus;

    int edellinenTulos;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        this.edellinenTulos = sovellus.tulos();
        sovellus.nollaa();
        int laskunTulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.setTulos(edellinenTulos);
        tuloskentta.setText("" + edellinenTulos);
    }

}