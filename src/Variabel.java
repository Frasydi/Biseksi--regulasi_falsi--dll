import java.io.Serializable;
import java.util.HashMap;

public class Variabel implements Serializable {
    HashMap<String, Double> mp = new HashMap<>();
    int angkasetelahkoma = 0;
    double jmlhangkstlhkm = 0;
    String persamaann;
    int     metode    = 0;
    int     erroror   = 0;
    int jumlahiterasi = 0  ;
    Double[][] hasiln = new Double[10][100];//baris ke : 0 = x, 1 = xn+1, 2 = xmid, 3 = fx, 4 = fxn+1, 5 =fxmid, 6 = tfx, 7 = presentase error, 8 xn-1, 9. fxn-1
    double  solusi    = 0   ;
    Double  error     = 0.0;
    String[] arah = new String[100];
}
