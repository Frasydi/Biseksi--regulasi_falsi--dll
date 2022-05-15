import Sydn.Mathf;
import Sydn.Precise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Fungsi {
    /*public static void main(String[] args) {
        Fungsi fg = new Fungsi();
        Variabel vb = new Variabel();

        String nsn = new Transpose().persamaantranspose(vb.persamaann);
        double hasil = fg.persamaan(vb.x[0], vb, nsn);
        System.out.println(hasil);
    }
     */


    Double persamaan(Double x, Variabel vb, String sbn) {
        HashMap<String, Double> tempn = vb.mp;
        tempn.put("x", x);
        String sb = Mathf.ConvertSymboltoDecimal(tempn, sbn);
        System.out.print(sb+"\n");
        if (vb.angkasetelahkoma != 0) {
            return Math.round(Mathf.eval(sb, Precise.High)*vb.jmlhangkstlhkm)/ vb.jmlhangkstlhkm;
        }else return Mathf.eval(sb, Precise.High);
    }

    String mengubahsimbolmenjadiangka2(int i, Variabel vb, String sbn) {
        String[] rms = {"fxd","fxs","txs","xd","xs", "xi", "fxi"};
        HashMap<String, Double> rms2 = new HashMap<>();
        rms2.put(rms[0], vb.hasiln[4][i]);
        rms2.put(rms[1], vb.hasiln[3][i]);
        rms2.put(rms[2], vb.hasiln[6][i]);
        rms2.put(rms[3], vb.hasiln[1][i]);
        rms2.put(rms[4], vb.hasiln[0][i]);
        rms2.put(rms[5], vb.hasiln[8][i]);
        rms2.put(rms[6], vb.hasiln[9][i]);
        ArrayList<String> temn = new ArrayList<>(Arrays.asList(Mathf.splitAnStringtoArrayList(sbn)));
        return Mathf.ConvertSymboltoDecimal(rms2, temn);
    }

    void solusi(double data) {
        System.out.println("Solusinya adalah "+data);
    }
    double merubahmenjadipositif(double data) {
        if (data < 0 ) {
            data = data * -1;
        }
        return data;
    }

    Double[][] Metode(Variabel ms) {

        int z = 0;
        int za = 0;
        double[] temp = {0,0};
        String sni = "";
        if (ms.metode == 0 ) {
            System.out.println("Metode biseksi ");
            do {
                ms.hasiln[3][z] = persamaan(ms.hasiln[0][z], ms, ms.persamaann);
                ms.hasiln[4][z] = persamaan(ms.hasiln[1][z], ms, ms.persamaann);;
                sni = mengubahsimbolmenjadiangka2(z ,ms,"(xd+xs)/2");
                ms.hasiln[2][z] = ms.angkasetelahkoma != 0?Math.round(Mathf.eval(sni, Precise.High)*ms.jmlhangkstlhkm)/ ms.jmlhangkstlhkm : Mathf.eval(sni,Precise.High);
                ms.hasiln[5][z] = persamaan(ms.hasiln[2][z], ms, ms.persamaann);
                ms.solusi = ms.hasiln[2][z];
                sni = mengubahsimbolmenjadiangka2(z, ms, "(xd-xs)/xd");
                ms.hasiln[7][z] = ms.angkasetelahkoma != 0?Math.round(Mathf.eval(sni, Precise.High)*ms.jmlhangkstlhkm)/ ms.jmlhangkstlhkm : Mathf.eval(sni,Precise.High );
                z++;
                mengecektanda(ms, z);
                mengecekerror(ms, temp, z);
            } while (temp[0] >= temp[1]);
        } else if (ms.metode == 1) {
            do {
                ms.hasiln[3][z] = persamaan(ms.hasiln[0][z], ms, ms.persamaann);
                ms.hasiln[4][z] = persamaan(ms.hasiln[1][z], ms, ms.persamaann);
                sni = mengubahsimbolmenjadiangka2(z, ms, "xs-fxs*((xd - xs)/(fxd-fxs))");
                ms.hasiln[2][z] = ms.angkasetelahkoma != 0?Math.round(Mathf.eval(sni, Precise.High)*ms.jmlhangkstlhkm)/ ms.jmlhangkstlhkm : Mathf.eval(sni, Precise.High);
                ms.hasiln[5][z] = persamaan(ms.hasiln[2][z], ms, ms.persamaann);
                ms.solusi = ms.hasiln[2][z];
                sni = mengubahsimbolmenjadiangka2(z, ms, "(xd-xs)/xd");
                ms.hasiln[7][z] = ms.angkasetelahkoma != 0?Math.round(Mathf.eval(sni, Precise.High)*ms.jmlhangkstlhkm)/ ms.jmlhangkstlhkm : Mathf.eval(sni, Precise.High);
                z++;
                mengecektanda(ms, z);
                mengecekerror(ms, temp, z);
            }while (temp[0] >= temp[1]) ;
        } else if (ms.metode == 2) {
            do {
                ms.hasiln[3][z] = persamaan(ms.hasiln[0][z], ms, ms.persamaann);
                ms.hasiln[6][z] = persamaan(ms.hasiln[0][z], ms, Mathf.derivative(ms.persamaann));
                sni = mengubahsimbolmenjadiangka2(z, ms, "xs-(fxs/txs)");
                ms.hasiln[1][z] = ms.angkasetelahkoma != 0?Math.round(Mathf.eval(sni, Precise.High)*ms.jmlhangkstlhkm)/ ms.jmlhangkstlhkm : Mathf.eval(sni, Precise.High);
                ms.hasiln[0][z+1] = ms.hasiln[1][z];
                ms.hasiln[4][z] = persamaan(ms.hasiln[1][z], ms, ms.persamaann);
                ms.solusi = ms.hasiln[1][z];
                sni = mengubahsimbolmenjadiangka2(z, ms, "(xd-xs)/xd");
                ms.hasiln[7][z] = ms.angkasetelahkoma != 0?Math.round(Mathf.eval(sni, Precise.High)*ms.jmlhangkstlhkm)/ ms.jmlhangkstlhkm : Mathf.eval(sni, Precise.High);
                z++;
                mengecekerror(ms, temp, z);
            }while (temp[0] >= temp[1]);
        } else if (ms.metode == 3) {
            do {
                ms.hasiln[9][z] = persamaan(ms.hasiln[8][z], ms, ms.persamaann);
                ms.hasiln[3][z] = persamaan(ms.hasiln[0][z+1], ms, ms.persamaann);
                sni = mengubahsimbolmenjadiangka2(z, ms, "")

            }while(temp[0] >= temp[1]);
        }
        ms.jumlahiterasi = z ;
        return ms.hasiln;
    }
    void mengecektanda(Variabel ms, int z) {
        if ((ms.hasiln[3][z-1] >= 0 && ms.hasiln[5][z-1] >= 0)||(ms.hasiln[3][z-1] < 0 && ms.hasiln[5][z-1] < 0)) {
            ms.hasiln[0][z] = ms.hasiln[2][z-1];
            ms.hasiln[1][z] = ms.hasiln[1][z-1];
            ms.arah[z-1] = "Sama Tanda";
        } else {
            ms.hasiln[1][z] = ms.hasiln[2][z-1];
            ms.hasiln[0][z] = ms.hasiln[0][z-1];
            ms.arah[z-1] = "Beda Tanda";
        }
    }
    void mengecekerror(Variabel ms,double[] temp,int z) {
        if (ms.erroror == 0) {
            if (ms.metode != 2) {
                temp[0] = merubahmenjadipositif(ms.hasiln[5][z - 1]);
            } else {
                temp[0] = merubahmenjadipositif(ms.hasiln[4][z-1]);
            }
            temp[1] = ms.error;
        } else {
            temp[0] = ms.error-1;
            temp[1] = z;
        }
    }
}
