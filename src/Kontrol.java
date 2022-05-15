import Sydn.Mathf;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Kontrol implements Initializable {

    public Button btnLoad2;
    public TextField txt45;
    public TextField txt342;
    public Label lbl312;
    @FXML
    private Label persamaanke;
    @FXML
    private Line Animationline;
    @FXML
    private Button btnLoad;
    @FXML
    private Label Texthasil2;
    @FXML
    private TextField Derivative;
    @FXML
    private Label labelsot;
    @FXML
    private Button btn31;
    @FXML
    private TextField rms1;
    @FXML
    private Button btnrms;
    @FXML
    private TextField inputx1;
    @FXML
    private Button downbtn;
    @FXML
    private Button up;
    @FXML
    private TextField txt4;
    @FXML
    private Button btnfin;
    @FXML
    private TextField txt3;
    @FXML
    private ComboBox<String> cbx3;
    @FXML
    private Button btn1;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private ComboBox<String> cb1;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private ChoiceBox<String> cbx1;
    @FXML
    private ListView<String> lV;
    @FXML
    private ComboBox<Double> cbx2;
    Variabel mts = new Variabel();
    Fungsi fgs = new Fungsi();
    int itr = 1;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] metode = {"Biseksi", "Regulasi Falsi", "Newton Raphsan", "Secant" };
        cb1.setItems(FXCollections.observableArrayList(metode));
        cb1.getSelectionModel().selectFirst();
        String[] errit = {"Error","Iterasi"};
        cbx3.setItems(FXCollections.observableArrayList(errit));
        cbx3.getSelectionModel().selectFirst();
    }
    public void melakukanpersamaan(KeyEvent kv) {
        if (kv.getCode() == KeyCode.ENTER) {
            mts.persamaann = txt1.getText();
            System.out.println("Memasukkan rumus : "+mts.persamaann);
        }
    }
    public void menggantiMethod() {
        mts.metode = cb1.getSelectionModel().getSelectedIndex();
        System.out.println("Metode "+mts.metode);
        if (mts.metode == 2) {
            txt4.setEditable(false);
            txt4.setDisable(true);
            lbl312.setDisable(true);
        } else {
            txt4.setEditable(true);
            txt4.setDisable(false);
            lbl312.setDisable(false);
        }
    }

    public void Iterasi(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                itr = Integer.parseInt(txt2.getText());
            }catch (NumberFormatException e) {
                System.out.println("Error");
            }

        }

    }
    void listtest() {
        String[] Liststr = {
                "\t\t\t\t\tIterasi ke "+(itr),
                "Nilai Xn\t\t\t=\t"+mts.hasiln[0][itr-1],
                "Nilai Xn+1\t\t=\t"+mts.hasiln[1][itr-1],
                "",
                "Nilai FXn\t\t\t=\t"+mts.hasiln[3][itr-1],
                "Nilai FXn+1\t\t=\t"+mts.hasiln[4][itr-1],
                "",
                "",
                "",
                "", "Presentase error\t=\t"+mts.hasiln[7][itr-1],"Solusinya adalah\t=\t"+mts.solusi
        };
        Liststr[3] = "Nilai Xmid\t\t=\t";
        Liststr[6] = "Nilai FXMid\t\t=\t";
        Liststr[7] = "Nilai FX`\t\t\t=\t";
        if (mts.metode <= 1)  {
            Liststr[6] += mts.hasiln[5][itr-1];
            Liststr[3] += mts.hasiln[2][itr-1];
            Liststr[8] = mts.arah[itr-1];
            Liststr[7] = "Fx` tidak digunakan di metode ini";
        } else if (mts.metode == 2){
            Liststr[6] = "Fxmid tidak digunakan di metode ini";
            Liststr[3] = "Xmid tidak digunakan di metode ini";
            Liststr[7] += mts.hasiln[6][itr-1];
        } else {
            Liststr[6] = "Fxmid tidak digunakan di metode ini";
            Liststr[3] = "Xmid tidak digunakan di metode ini";
            Liststr[7] = "Fx` tidak digunakan di metode ini";
            Liststr[8] = "";
        }
        lV.setItems(FXCollections.observableArrayList(Liststr));
    }
    public void menambahkannilaix(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                mts.hasiln[0][0] = cbx2.getItems().get(0);
                mts.hasiln[1][0] = cbx2.getItems().get(1);
            }catch (NumberFormatException e) {
                System.out.println("Error");
            }
        }
    }

    public void persamaanfin() {
        itr = 1;
        mts.erroror = cbx3.getSelectionModel().getSelectedIndex();
        try {
            if (!(txt342.getText().trim().equals("")||txt342.getText().equals("0"))) {
                mts.angkasetelahkoma = Integer.parseInt(txt342.getText());
            } else {
                mts.angkasetelahkoma = 0;
            }
            mts.persamaann = txt1.getText();
            System.out.println("Memasukkan rumus : "+mts.persamaann);
            if (mts.erroror == 0) mts.error = Double.parseDouble(txt3.getText());
            else mts.error = Double.parseDouble(txt3.getText());
            System.out.println(txt3.getText());
            mts.hasiln[0][0] = Double.parseDouble(inputx1.getText());
            System.out.println(inputx1.getText());
            if (mts.metode != 2) {
                mts.hasiln[1][0] = Double.parseDouble(txt4.getText());
            }
        } catch (NumberFormatException | InputMismatchException e) {
            Texthasil2.setText("Error pada penginputan");
        }
        Derivative.setText(Mathf.derivative(mts.persamaann));
        System.out.println(txt4.getText());
        try {
            if (mts.angkasetelahkoma > 0) {
                mts.jmlhangkstlhkm = Math.pow(10, mts.angkasetelahkoma);
            } else {
                mts.jmlhangkstlhkm = 0;
            }
            mts.hasiln = fgs.Metode(mts);
            Texthasil2.setText("Berhasil");
            listtest();
            saveFile();
        }catch (ArrayIndexOutOfBoundsException e) {
            Texthasil2.setText("Iterasi yang diberikan lebih dari 100 dan ini sudah melebihi batas");
        } catch (NumberFormatException e) {
            Texthasil2.setText("Anda belum memasukkan nilai X-nya");
        } catch (IndexOutOfBoundsException e) {
            Texthasil2.setText("Error");
        }

    }
    public void Loadmenu() {
        try {
            FileInputStream flp = new FileInputStream("Saveterakhit.ser");
            ObjectInputStream flps = new ObjectInputStream(flp);
            mts = (Variabel) flps.readObject();
            flps.close();
            txt342.setText(String.valueOf(mts.angkasetelahkoma));
            cb1.getSelectionModel().select(mts.metode);
            cbx3.getSelectionModel().select(mts.erroror);
            txt1.setText(mts.persamaann);
            txt3.setText(String.valueOf(mts.error));
            inputx1.setText(String.valueOf(mts.hasiln[0][0]));
            txt4.setText(String.valueOf(mts.hasiln[1][0]));
            persamaanfin();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void saveFile() {
        try {
            FileOutputStream wrt = new FileOutputStream("Saveterakhit.ser");
            ObjectOutputStream orts = new ObjectOutputStream(wrt);
            orts.writeObject(mts);
            orts.flush();
            orts.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Inputx2(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            mts.hasiln[1][0] = Double.parseDouble(txt4.getText());
            System.out.println(txt4.getText());
        }
    }

    public void inputx1(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            mts.hasiln[0][0] = Double.parseDouble(inputx1.getText());
            System.out.println(inputx1.getText());
        }
    }

    public void upact(ActionEvent actionEvent) {
        if (itr < mts.jumlahiterasi) {
            try {
                itr++;
            } catch (NumberFormatException e) {
                System.out.println("Error");
            }
            listtest();
        }
    }

    public void downac(ActionEvent actionEvent) {
        if (itr > 1) {
            try {
                itr--;
            } catch (NumberFormatException e) {
                System.out.println("Error");
            }
            listtest();
        }
    }

    public void rumus12(MouseEvent actionEvent) {
        try {
            HashMap<String, Double> anstemp = new HashMap<>();
            int in = lV.getSelectionModel().getSelectedIndex();
            String temn = "";
            String trans = mts.persamaann;

            if (in == 4) {
                anstemp.put("x", mts.hasiln[0][itr - 1]);
                temn = mts.persamaann;
            } else if (in == 5) {
                anstemp.put("x", mts.hasiln[1][itr - 1]);
                temn = mts.persamaann;
            } else if (in == 6) {
                if (mts.metode <= 1) {
                    anstemp.put("x", mts.hasiln[2][itr - 1]);
                    temn = mts.persamaann;
                } else temn = "Fxmid tidak digunakan di metode ini";
            } else if (in == 7) {
                 if (mts.metode == 2) {
                     trans = Mathf.derivative(trans);
                     anstemp.put("x", mts.hasiln[0][itr - 1]);
                     temn = trans;
                 } else temn = "Fx` tidak digunakan di metode ini";
            } else if (in == 3) {
                ArrayList<Double> temns = new ArrayList<>();
                if (mts.metode == 0) {
                    temns.add(mts.hasiln[0][itr - 1]);
                    temns.add(mts.hasiln[1][itr - 1]);
                    temn = fgs.mengubahsimbolmenjadiangka2(itr - 1, mts, "(xd+xs)/2");
                } else if (mts.metode == 1) {
                    temns.add(mts.hasiln[0][itr - 1]);
                    temns.add(mts.hasiln[1][itr - 1]);
                    temn = fgs.mengubahsimbolmenjadiangka2(itr - 1, mts, "xs-fxs*((xd - xs)/(fxd-fxs))");
                } else {
                    temn = "Xmid tidak digunakan di metode ini";
                }
            } else if (in == 2) {
                if (mts.metode >= 0 && mts.metode <= 1) {
                    temn = String.valueOf(mts.hasiln[in - 1][itr - 1]);
                } else {
                    temn = fgs.mengubahsimbolmenjadiangka2(itr-1, mts, "xs-(fxs/txs)");
                }
            } else if (in == 1 || in == 0) {
                if (in > 0) temn = String.valueOf(mts.hasiln[in - 1][itr - 1]);
                else temn = null;
            } else if (in == 8 && mts.metode <= 1) {
                if (mts.arah[itr-1].equals("Sama Tanda")) {
                    temn = "Fx  dan Fxmid sama-sama bernilai negatif/positif";
                } else {
                    temn = "Fx dan Fxmid berlawanan tanda";
                }
            } else if (in == 10) {
                temn = fgs.mengubahsimbolmenjadiangka2(itr-1, mts, "(xd-xs)/xd");
            }
            if (in != 8 && in != 0 ) {
                rms1.setText(Mathf.ConvertSymboltoDecimal(anstemp, temn));
            } else rms1.setText(temn);
        }catch (NullPointerException e) {
            System.out.println("error");
        }
    }
    int i = 0;
    public void btn31f(ActionEvent actionEvent) {
        String[] tmst = {"Catatan : Taruhkan huruf x di persamaan untuk nilai xnya","Catatan : Anda bisa memasukkan huruf p sebagai pi","Catatan : Jangan lupa memasukkan pangkat 1\n          jika mereka tidak memiliki pangkat"};
        if (i == tmst.length-1) {
            i = 0;
        } else i++;
        labelsot.setText(tmst[i]);
    }



}
