
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] ljono;
    private int alkioidenLkm;

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti väärin");
        }
        ljono = new int[kapasiteetti];
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public void lisaa(int luku) {
        if (alkioidenLkm == 0) {
            ljono[0] = luku;
            alkioidenLkm++;
        } else if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            taysiTaulukkoCheck(alkioidenLkm, ljono.length);
        }
    }

    public void taysiTaulukkoCheck(int alkioidenLkm, int ljonoKoko) {
        if (alkioidenLkm % ljonoKoko == 0) {
            int[] taulukkoOld = new int[ljono.length];
            taulukkoOld = ljono;
            kopioiTaulukko(ljono, taulukkoOld);
            ljono = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(taulukkoOld, ljono);
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public void poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) { // siirretään häntään ja poistetaan
                ljono[i] = ljono[alkioidenLkm - 1];
                alkioidenLkm--;
            }
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + ljono[0] + "}";
        } else {
            return jononJasenet();
        }
    }

    public String jononJasenet() {
        String jononJasenet = "";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            jononJasenet += ljono[i] + ", ";
        }
        jononJasenet += ljono[alkioidenLkm - 1];
        return "{" + jononJasenet + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static void lisaaTaulutJoukkoon(IntJoukko intJoukko, int[] tauluYksi, int[] tauluKaksi) {
        for (int i = 0; i < tauluYksi.length; i++) {
            intJoukko.lisaa(tauluYksi[i]);
        }
        for (int i = 0; i < tauluKaksi.length; i++) {
            intJoukko.lisaa(tauluKaksi[i]);
        }
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        lisaaTaulutJoukkoon(x, aTaulu, bTaulu);
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                lisaaTaulutJoukkoon(y, bTaulu, bTaulu);
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        lisaaTaulutJoukkoon(z, aTaulu, bTaulu);
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }

}