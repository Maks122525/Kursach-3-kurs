package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
private  ConnectDB dataCon;
int k_tab;
    private ObservableList<Fakultet> a = FXCollections.observableArrayList();
    @FXML TextField log_k;
@FXML PasswordField pas_k;
@FXML void  avtoriz(){
String log = log_k.getText();
String pas = pas_k.getText();
dataCon.avt_pol(log,pas);
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @FXML TextField log_dob;
    @FXML PasswordField pass_1_dob;
    @FXML PasswordField pass_2_dob;
    @FXML Button but_dob;
    @FXML Menu Rast;
    @FXML TableView<Reiting> tabl_reit;
    @FXML TableColumn<Reiting,String> tabl_reit_fio;
    @FXML TableColumn<Reiting,String> tabl_reit_gr;
    @FXML TableColumn<Reiting,String> tabl_reit_dis;
    @FXML TableColumn<Reiting,Float> tabl_reit_lab;
    @FXML TableColumn<Reiting,Float> tabl_reit_usr;
    @FXML TableColumn<Reiting,Integer> tabl_reit_atest;
    @FXML TableColumn<Reiting,Float> tabl_reit_itog;
    @FXML TableView<Fakultet> tabl;
@FXML TableColumn<Fakultet,Integer> id_t;
    @FXML TableColumn<Fakultet,String> name_t;
    @FXML TableView<Dolgnost> tabl_dolg;
    @FXML TableColumn<Dolgnost,Integer> kod_dolg;
    @FXML TableColumn<Dolgnost,String> name_dolg;
    @FXML TableView<Vid_Rab> tabl_vid;
    @FXML TableColumn<Vid_Rab,Integer> tabl_vid_id;
    @FXML TableColumn<Vid_Rab,String> tabl_vid_name;
    @FXML TableView<Disziplin> tabl_disz;
    @FXML TableColumn<Disziplin,Integer> tabl_disz_kod;
    @FXML TableColumn<Disziplin,String> tabl_disz_name;
    @FXML TableView<Grupa> tabl_grup;
    @FXML TableColumn<Grupa,Integer> tabl_grup_kod;
    @FXML TableColumn<Grupa,String> tabl_grup_name_g;
    @FXML TableColumn<Grupa,String> tabl_grup_name_s;
    @FXML TableColumn<Grupa,String> tabl_grup_name_f;
    @FXML TableView<Prepod> tabl_prep;
    @FXML TableColumn<Prepod,Integer> tabl_prep_kod;
    @FXML TableColumn<Prepod,String> tabl_prep_fio;
    @FXML TableColumn<Prepod,String> tabl_prep_dolg;
    @FXML TableView<Student> tabl_stud;
    @FXML TableColumn<Student,Integer> tabl_stud_kod;
    @FXML TableColumn<Student,String> tabl_stud_fio;
    @FXML TableColumn<Student,String> tabl_stud_grup;
    @FXML TableView<Otcen> tabl_otcen;
    @FXML TableColumn<Otcen,Integer> tabl_otcen_kod;
    @FXML TableColumn<Otcen,String> tabl_otcen_fios;
    @FXML TableColumn<Otcen,String> tabl_otcen_name_r;
    @FXML TableColumn<Otcen,Integer> tabl_otcen_otcen;
    @FXML TableColumn<Otcen,String> tabl_otcen_disz;
    @FXML TableColumn<Otcen,String> tabl_otcen_fiop;
    @FXML TextField kod_f_tekst;
    @FXML TextField name_f_tekst;
    @FXML TextField text_3;
    @FXML TextField text_4;
    @FXML TextField text_5;
    @FXML TextField text_6;
    @FXML Label lab_f_k;
    @FXML Label lab_f_n;
    @FXML Label label_3;
    @FXML Label label_4;
    @FXML Label label_5;
    @FXML Label label_6;
    @FXML Label label_7;
    @FXML Label label_8;
    @FXML Label label_9;
    @FXML Label label_10;
    @FXML Button insert_f;
    @FXML Button updete_f;
    @FXML Button delete_f;
    @FXML ComboBox combo_1;
    @FXML ComboBox combo_2;
    @FXML ComboBox combo_3;
    @FXML ComboBox combo4;
    @FXML ComboBox combo_5;
    @FXML ComboBox combo_6;
    @FXML ComboBox combo_7;
    @FXML ComboBox combo_8;
    @FXML Button but_reit;
    @FXML TableView<Special> tabl_spec;
    @FXML TableColumn<Special,Integer> kod_spec;
    @FXML TableColumn<Special,String> nam_spec;
    @FXML TableColumn<Special,String> colum_shifr;
    @FXML TableColumn<Special,String> colum_fak;
    Reiting []reit = new Reiting[10000];
    public void click_fakul(ActionEvent actionEvent) {
        k_tab=1;
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(false);
        tabl.setVisible(true);
        tabl_vid.setVisible(false);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(true);
        name_f_tekst.setVisible(true);
        text_3.setVisible(false);
        text_4.setVisible(false);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(true);
        lab_f_k.setText("Код факультета");
        lab_f_n.setVisible(true);
        lab_f_n.setText("Наименование факультета");
        label_3.setVisible(false);
        label_4.setVisible(false);
        label_5.setVisible(false);
        label_6.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        insert_f.setVisible(true);
        updete_f.setVisible(true);
        delete_f.setVisible(true);
        combo_1.setVisible(false);
        combo_2.setVisible(false);
        combo_3.setVisible(false);
        combo4.setVisible(false);
        try {
        id_t.setCellValueFactory(new PropertyValueFactory<Fakultet,Integer>("Id"));
        name_t.setCellValueFactory(new PropertyValueFactory<Fakultet,String>("Name"));


            tabl.setItems(dataCon.getPersonList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_spez(ActionEvent actionEvent) {
        k_tab=2;
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(false);
        combo_1.setVisible(true);
        combo_2.setVisible(false);
        combo_3.setVisible(false);
        combo4.setVisible(false);
        tabl_vid.setVisible(false);
        tabl.setVisible(false);
        tabl_spec.setVisible(true);
        kod_f_tekst.setVisible(true);
        name_f_tekst.setVisible(true);
        text_3.setVisible(true);
        text_4.setVisible(true);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(true);
        lab_f_k.setText("Код специальности");
        lab_f_n.setVisible(true);
        lab_f_n.setText("Наименование специальности");
        label_3.setVisible(true);
        label_3.setText("Шифр специальности");
        label_4.setVisible(true);
        label_4.setText("Наменование факультета");
        label_5.setVisible(false);
        label_6.setVisible(false);
        insert_f.setVisible(true);
        updete_f.setVisible(true);
        delete_f.setVisible(true);
        try {
            combo_1.setItems(dataCon.getFacList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            kod_spec.setCellValueFactory(new PropertyValueFactory<Special,Integer>("Id"));
            nam_spec.setCellValueFactory(new PropertyValueFactory<Special,String>("Name_s"));
            colum_shifr.setCellValueFactory(new PropertyValueFactory<Special,String>("Shifr"));
            colum_fak.setCellValueFactory(new PropertyValueFactory<Special,String>("Name_f"));

            tabl_spec.setItems(dataCon.getSpecList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_grup(ActionEvent actionEvent) {
        k_tab=6;
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(true);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(false);
        combo_1.setVisible(true);
        combo_2.setVisible(true);
        combo_3.setVisible(false);
        combo4.setVisible(false);
        tabl_vid.setVisible(false);
        tabl.setVisible(false);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(true);
        name_f_tekst.setVisible(true);
        text_3.setVisible(true);
        text_4.setVisible(true);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(true);
        lab_f_k.setText("Код группы");
        lab_f_n.setVisible(true);
        lab_f_n.setText("Наименование группы");
        label_3.setVisible(true);
        label_3.setText("Наименование специальности");
        label_4.setVisible(true);
        label_4.setText("Наменование факультета");
        label_5.setVisible(false);
        label_6.setVisible(false);
        insert_f.setVisible(true);
        updete_f.setVisible(true);
        delete_f.setVisible(true);
        try {
            combo_2.setItems(dataCon.getSpec_nList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            combo_1.setItems(dataCon.getFacList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            tabl_grup_kod.setCellValueFactory(new PropertyValueFactory<Grupa,Integer>("Id"));
            tabl_grup_name_g.setCellValueFactory(new PropertyValueFactory<Grupa,String>("Name_g"));
            tabl_grup_name_s.setCellValueFactory(new PropertyValueFactory<Grupa,String>("Name_s"));
            tabl_grup_name_f.setCellValueFactory(new PropertyValueFactory<Grupa,String>("Name_f"));

            tabl_grup.setItems(dataCon.getGrupList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_student(ActionEvent actionEvent) {
        k_tab=8;
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(true);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(false);
        combo_1.setVisible(false);
        combo_2.setVisible(true);
        combo_3.setVisible(false);
        combo4.setVisible(false);
        tabl_vid.setVisible(false);
        tabl.setVisible(false);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(true);
        name_f_tekst.setVisible(true);
        text_3.setVisible(true);
        text_4.setVisible(false);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(true);
        lab_f_k.setText("Код студента");
        lab_f_n.setVisible(true);
        lab_f_n.setText("ФИО студента");
        label_3.setVisible(true);
        label_3.setText("Группа");
        label_4.setVisible(false);
        label_5.setVisible(false);
        label_6.setVisible(false);
        insert_f.setVisible(true);
        updete_f.setVisible(true);
        delete_f.setVisible(true);
        try {
            combo_2.setItems(dataCon.getGrup_nList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            tabl_stud_kod.setCellValueFactory(new PropertyValueFactory<Student,Integer>("Id"));
            tabl_stud_fio.setCellValueFactory(new PropertyValueFactory<Student,String>("Name_s"));
            tabl_stud_grup.setCellValueFactory(new PropertyValueFactory<Student,String>("Name_g"));

            tabl_stud.setItems(dataCon.getStudList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_prepod(ActionEvent actionEvent) {
        k_tab=7;
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(true);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(false);
        combo_1.setVisible(false);
        combo_2.setVisible(true);
        combo_3.setVisible(false);
        combo4.setVisible(false);
        tabl_vid.setVisible(false);
        tabl.setVisible(false);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(true);
        name_f_tekst.setVisible(true);
        text_3.setVisible(true);
        text_4.setVisible(false);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(true);
        lab_f_k.setText("Код преподавателя");
        lab_f_n.setVisible(true);
        lab_f_n.setText("ФИО преподавателя");
        label_3.setVisible(true);
        label_3.setText("Наименование должности");
        label_4.setVisible(false);
        label_5.setVisible(false);
        label_6.setVisible(false);
        insert_f.setVisible(true);
        updete_f.setVisible(true);
        delete_f.setVisible(true);
        try {
            combo_2.setItems(dataCon.getDolg_nList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            tabl_prep_kod.setCellValueFactory(new PropertyValueFactory<Prepod,Integer>("Id"));
            tabl_prep_fio.setCellValueFactory(new PropertyValueFactory<Prepod,String>("Name_p"));
            tabl_prep_dolg.setCellValueFactory(new PropertyValueFactory<Prepod,String>("Name_d"));


            tabl_prep.setItems(dataCon.getPrepList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_octen(ActionEvent actionEvent) {
        k_tab=9;
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        tabl_otcen.setVisible(true);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(false);
        combo_1.setVisible(true);
        combo_2.setVisible(true);
        combo_3.setVisible(true);
        combo4.setVisible(true);
        tabl_vid.setVisible(false);
        tabl.setVisible(false);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(true);
        name_f_tekst.setVisible(true);
        text_3.setVisible(true);
        text_4.setVisible(true);
        text_5.setVisible(true);
        text_6.setVisible(true);
        lab_f_k.setVisible(true);
        lab_f_k.setText("Код оценки");
        lab_f_n.setVisible(true);
        lab_f_n.setText("ФИО студента");
        label_3.setVisible(true);
        label_3.setText("Название работы");
        label_4.setVisible(true);
        label_4.setText("Оценка");
        label_5.setVisible(true);
        label_5.setText("Наименование дисциплины");
        label_6.setVisible(true);
        label_6.setText("ФИО преподавателя");
        insert_f.setVisible(true);
        updete_f.setVisible(true);
        delete_f.setVisible(true);
        try {
            combo_2.setItems(dataCon.getStud_nList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            combo_1.setItems(dataCon.getRabList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            combo_3.setItems(dataCon.getDisz_nList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            combo4.setItems(dataCon.getPrep_nList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            tabl_otcen_kod.setCellValueFactory(new PropertyValueFactory<Otcen,Integer>("Id"));
            tabl_otcen_fios.setCellValueFactory(new PropertyValueFactory<Otcen,String>("Name_s"));
            tabl_otcen_name_r.setCellValueFactory(new PropertyValueFactory<Otcen,String>("Name_r"));
            tabl_otcen_otcen.setCellValueFactory(new PropertyValueFactory<Otcen,Integer>("Otc"));
            tabl_otcen_disz.setCellValueFactory(new PropertyValueFactory<Otcen,String>("Name_d"));
            tabl_otcen_fiop.setCellValueFactory(new PropertyValueFactory<Otcen,String>("Name_p"));

            tabl_otcen.setItems(dataCon.getOtcenList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_dolg(ActionEvent actionEvent) {
        k_tab=3;
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        combo_1.setVisible(false);
        combo_2.setVisible(false);
        combo_3.setVisible(false);
        combo4.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(true);
        tabl.setVisible(false);
        tabl_vid.setVisible(false);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(true);
        name_f_tekst.setVisible(true);
        text_3.setVisible(false);
        text_4.setVisible(false);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(true);
        lab_f_k.setText("Код должности");
        lab_f_n.setVisible(true);
        lab_f_n.setText("Название должности");
        label_3.setVisible(false);
        label_4.setVisible(false);
        label_5.setVisible(false);
        label_6.setVisible(false);
        insert_f.setVisible(true);
        updete_f.setVisible(true);
        delete_f.setVisible(true);
        try {
            kod_dolg.setCellValueFactory(new PropertyValueFactory<Dolgnost,Integer>("Id"));
            name_dolg.setCellValueFactory(new PropertyValueFactory<Dolgnost,String>("Name"));
            tabl_dolg.setItems(dataCon.getDolgList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void click_disz(ActionEvent actionEvent) {
        k_tab=5;
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(true);
        tabl_dolg.setVisible(false);
        tabl.setVisible(false);
        tabl_vid.setVisible(false);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(true);
        name_f_tekst.setVisible(true);
        text_3.setVisible(false);
        text_4.setVisible(false);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(true);
        lab_f_k.setText("Код дисциплины");
        lab_f_n.setVisible(true);
        lab_f_n.setText("Наименование дисциплины");
        label_3.setVisible(false);
        label_4.setVisible(false);
        label_5.setVisible(false);
        label_6.setVisible(false);
        insert_f.setVisible(true);
        updete_f.setVisible(true);
        delete_f.setVisible(true);
        combo_1.setVisible(false);
        combo_2.setVisible(false);
        combo_3.setVisible(false);
        combo4.setVisible(false);
        try {
            tabl_disz_kod.setCellValueFactory(new PropertyValueFactory<Disziplin,Integer>("Id"));
            tabl_disz_name.setCellValueFactory(new PropertyValueFactory<Disziplin,String>("Name"));
            tabl_disz.setItems(dataCon.getDiszList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_rab(ActionEvent actionEvent) {
        k_tab=4;
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(false);
        tabl.setVisible(false);
        tabl_vid.setVisible(true);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(true);
        name_f_tekst.setVisible(true);
        text_3.setVisible(false);
        text_4.setVisible(false);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(true);
        lab_f_k.setText("Код работы");
        lab_f_n.setVisible(true);
        lab_f_n.setText("Название работы");
        label_3.setVisible(false);
        label_4.setVisible(false);
        label_5.setVisible(false);
        label_6.setVisible(false);
        insert_f.setVisible(true);
        updete_f.setVisible(true);
        delete_f.setVisible(true);
        combo_1.setVisible(false);
        combo_2.setVisible(false);
        combo_3.setVisible(false);
        combo4.setVisible(false);
        try {
            tabl_vid_id.setCellValueFactory(new PropertyValueFactory<Vid_Rab,Integer>("Id"));
            tabl_vid_name.setCellValueFactory(new PropertyValueFactory<Vid_Rab,String>("Name"));
            tabl_vid.setItems(dataCon.getVidList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void click_insert_f(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(k_tab==1){
        if (kod_f_tekst.equals("") || name_f_tekst.equals("") )
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение!");
            alert.setContentText("Заполните все поля!");
            alert.showAndWait();
        } else {
            String id_field = kod_f_tekst.getText();
            String title_field = name_f_tekst.getText();
            kod_f_tekst.setText("");
            name_f_tekst.setText("");
                dataCon.insertData_f(id_field, title_field);
            restartConnection_f();
        }}
        if(k_tab==2){
            System.out.println(k_tab);
            if (kod_f_tekst.equals("") || name_f_tekst.equals("") )
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            } else {
                String id_field = kod_f_tekst.getText();
                String title_field = name_f_tekst.getText();
                String shifr = text_3.getText();
                String nam = text_4.getText();
                kod_f_tekst.setText("");
                name_f_tekst.setText("");
                text_3.setText("");
                text_4.setText("");
                dataCon.insertData_spec(id_field, title_field,shifr,nam);
                restartConnection_spec();
            }}
        if(k_tab==3){
            if (kod_f_tekst.equals("") || name_f_tekst.equals("") )
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            } else {
                String id_field = kod_f_tekst.getText();
                String title_field = name_f_tekst.getText();
                kod_f_tekst.setText("");
                name_f_tekst.setText("");
                dataCon.insertData_dolg(id_field, title_field);
                restartConnection_dolg();
            }}
        if(k_tab==4){
            if (kod_f_tekst.equals("") || name_f_tekst.equals("") )
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            } else {
                String id_field = kod_f_tekst.getText();
                String title_field = name_f_tekst.getText();
                kod_f_tekst.setText("");
                name_f_tekst.setText("");
                dataCon.insertData_vid(id_field, title_field);
                restartConnection_vid();
            }}
        if(k_tab==5){
            if (kod_f_tekst.equals("") || name_f_tekst.equals("") )
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            } else {
                String id_field = kod_f_tekst.getText();
                String title_field = name_f_tekst.getText();
                kod_f_tekst.setText("");
                name_f_tekst.setText("");
                dataCon.insertData_disz(id_field, title_field);
                restartConnection_disz();
            }}
        if(k_tab==6){
            //System.out.println(k_tab);
            if (kod_f_tekst.equals("") || name_f_tekst.equals("") )
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            } else {
                String id_field = kod_f_tekst.getText();
                String title_field = name_f_tekst.getText();
                String shifr = text_3.getText();
                String nam = text_4.getText();
                kod_f_tekst.setText("");
                name_f_tekst.setText("");
                text_3.setText("");
                text_4.setText("");
                dataCon.insertData_grup(id_field, title_field,shifr,nam);
                restartConnection_grup();
            }}
        if(k_tab==7){
            if (kod_f_tekst.equals("") || name_f_tekst.equals("") )
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            } else {
                String id_field = kod_f_tekst.getText();
                String title_field = name_f_tekst.getText();
                String shifr = text_3.getText();
                kod_f_tekst.setText("");
                name_f_tekst.setText("");
                text_3.setText("");
                dataCon.insertData_prep(id_field, title_field,shifr);
                restartConnection_prep();
            }}
        if(k_tab==8){
            if (kod_f_tekst.equals("") || name_f_tekst.equals("") )
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            } else {
                String id_field = kod_f_tekst.getText();
                String title_field = name_f_tekst.getText();
                String shifr = text_3.getText();
                kod_f_tekst.setText("");
                name_f_tekst.setText("");
                text_3.setText("");
                dataCon.insertData_stud(id_field, title_field,shifr);
                restartConnection_stud();
            }}
        if(k_tab==9){
            if (kod_f_tekst.equals("") || name_f_tekst.equals("") )
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            } else {
                String id_field = kod_f_tekst.getText();
                String title_field = name_f_tekst.getText();
                String shifr = text_3.getText();
                String otc = text_4.getText();
                String name_d = text_5.getText();
                String name_p = text_6.getText();
                kod_f_tekst.setText("");
                name_f_tekst.setText("");
                text_3.setText("");
                text_4.setText("");
                text_5.setText("");
                text_6.setText("");
                dataCon.insertData_otcen(id_field, title_field,shifr,otc,name_d,name_p);
                restartConnection_otc();
            }}
    }

    public void click_update_f(ActionEvent actionEvent) throws ClassNotFoundException {
        if(k_tab==1){
        String old_id = "", new_id, name_f;
        if (tabl.getSelectionModel().getSelectedItem() != null) {
            Fakultet selectedPerson = tabl.getSelectionModel().getSelectedItem();
            old_id = selectedPerson.getId();
        }
        new_id = kod_f_tekst.getText();
        name_f = name_f_tekst.getText();
        dataCon.updateData_f(old_id, new_id, name_f);
        restartConnection_f();}
        if(k_tab==2){
            String old_id = "", new_id, name_s, shifr, name_f;
            if (tabl_spec.getSelectionModel().getSelectedItem() != null) {
                Special selectedPerson = tabl_spec.getSelectionModel().getSelectedItem();
                old_id = selectedPerson.getId();
            }
            new_id = kod_f_tekst.getText();
            name_s = name_f_tekst.getText();
            shifr = text_3.getText();
            name_f = text_4.getText();
            dataCon.updateData_spez(old_id, new_id, name_s,shifr,name_f);
            restartConnection_spec();}
        if(k_tab==3){
            String old_id = "", new_id, name_f;
            if (tabl_dolg.getSelectionModel().getSelectedItem() != null) {
                Dolgnost selectedPerson = tabl_dolg.getSelectionModel().getSelectedItem();
                old_id = selectedPerson.getId();
            }
            new_id = kod_f_tekst.getText();
            name_f = name_f_tekst.getText();
            dataCon.updateData_dolg(old_id, new_id, name_f);
            restartConnection_dolg();}
        if(k_tab==4){
            String old_id = "", new_id, name_f;
            if (tabl_vid.getSelectionModel().getSelectedItem() != null) {
                Vid_Rab selectedPerson = tabl_vid.getSelectionModel().getSelectedItem();
                old_id = selectedPerson.getId();
            }
            new_id = kod_f_tekst.getText();
            name_f = name_f_tekst.getText();
            dataCon.updateData_vid(old_id, new_id, name_f);
            restartConnection_vid();}
        if(k_tab==5){
            String old_id = "", new_id, name_f;
            if (tabl_disz.getSelectionModel().getSelectedItem() != null) {
                Disziplin selectedPerson = tabl_disz.getSelectionModel().getSelectedItem();
                old_id = selectedPerson.getId();
            }
            new_id = kod_f_tekst.getText();
            name_f = name_f_tekst.getText();
            dataCon.updateData_disz(old_id, new_id, name_f);
            restartConnection_disz();}
        if(k_tab==6){
            String old_id = "", new_id, name_s, shifr, name_f;
            if (tabl_grup.getSelectionModel().getSelectedItem() != null) {
                Grupa selectedPerson = tabl_grup.getSelectionModel().getSelectedItem();
                old_id = selectedPerson.getId();
            }
            new_id = kod_f_tekst.getText();
            name_s = name_f_tekst.getText();
            shifr = text_3.getText();
            name_f = text_4.getText();
            dataCon.updateData_grup(old_id, new_id, name_s,shifr,name_f);
            restartConnection_grup();}
        if(k_tab==7){
            String old_id = "", new_id, name_p, name_d;
            if (tabl_prep.getSelectionModel().getSelectedItem() != null) {
                Prepod selectedPerson = tabl_prep.getSelectionModel().getSelectedItem();
                old_id = selectedPerson.getId();
            }
            new_id = kod_f_tekst.getText();
            name_p = name_f_tekst.getText();
            name_d = text_3.getText();
            dataCon.updateData_prep(old_id, new_id, name_p,name_d);
            restartConnection_prep();}
        if(k_tab==8){
            String old_id = "", new_id, name_p, name_d;
            if (tabl_stud.getSelectionModel().getSelectedItem() != null) {
                Student selectedPerson = tabl_stud.getSelectionModel().getSelectedItem();
                old_id = selectedPerson.getId();
            }
            new_id = kod_f_tekst.getText();
            name_p = name_f_tekst.getText();
            name_d = text_3.getText();
            dataCon.updateData_stud(old_id, new_id, name_p,name_d);
            restartConnection_stud();}
        if(k_tab==9){
            String old_id = "", new_id, name_s, name_r,otc,name_d,name_p;
            if (tabl_otcen.getSelectionModel().getSelectedItem() != null) {
                Otcen selectedPerson = tabl_otcen.getSelectionModel().getSelectedItem();
                old_id = selectedPerson.getId();
            }
            new_id = kod_f_tekst.getText();
            name_s = name_f_tekst.getText();
            name_r = text_3.getText();
            otc = text_4.getText();
            name_d = text_5.getText();
            name_p = text_6.getText();
            dataCon.updateData_otc(old_id, new_id, name_s,name_r,otc,name_d,name_p);
            restartConnection_otc();}
    }

    public void click_delete_f(ActionEvent actionEvent) throws ClassNotFoundException {
        if(k_tab==1){
        if (tabl.getSelectionModel().getSelectedItem() != null) {
            Fakultet selectedPerson = tabl.getSelectionModel().getSelectedItem();
            String id = selectedPerson.getId();
            dataCon.deleteData_f(id);
            restartConnection_f();
        }}
        if(k_tab==2){
            if (tabl_spec.getSelectionModel().getSelectedItem() != null) {
                Special selectedPerson = tabl_spec.getSelectionModel().getSelectedItem();
                String id = selectedPerson.getId();
                dataCon.deleteData_spec(id);
                restartConnection_spec();
            }}
        if(k_tab==3){
            if (tabl_dolg.getSelectionModel().getSelectedItem() != null) {
                Dolgnost selectedPerson = tabl_dolg.getSelectionModel().getSelectedItem();
                String id = selectedPerson.getId();
                dataCon.deleteData_dolg(id);
                restartConnection_dolg();
            }}
        if(k_tab==4){
            if (tabl_vid.getSelectionModel().getSelectedItem() != null) {
                Vid_Rab selectedPerson = tabl_vid.getSelectionModel().getSelectedItem();
                String id = selectedPerson.getId();
                dataCon.deleteData_vid(id);
                restartConnection_vid();
            }}
        if(k_tab==5){
            if (tabl_disz.getSelectionModel().getSelectedItem() != null) {
                Disziplin selectedPerson = tabl_disz.getSelectionModel().getSelectedItem();
                String id = selectedPerson.getId();
                dataCon.deleteData_disz(id);
                restartConnection_disz();
            }}
        if(k_tab==6){
            if (tabl_grup.getSelectionModel().getSelectedItem() != null) {
                Grupa selectedPerson = tabl_grup.getSelectionModel().getSelectedItem();
                String id = selectedPerson.getId();
                dataCon.deleteData_grup(id);
                restartConnection_grup();
            }}
        if(k_tab==7){
            if (tabl_prep.getSelectionModel().getSelectedItem() != null) {
                Prepod selectedPerson = tabl_prep.getSelectionModel().getSelectedItem();
                String id = selectedPerson.getId();
                dataCon.deleteData_p(id);
                restartConnection_prep();
            }}
        if(k_tab==8){
            if (tabl_stud.getSelectionModel().getSelectedItem() != null) {
                Student selectedPerson = tabl_stud.getSelectionModel().getSelectedItem();
                String id = selectedPerson.getId();
                dataCon.deleteData_stud(id);
                restartConnection_stud();
            }}
        if(k_tab==9){
            if (tabl_otcen.getSelectionModel().getSelectedItem() != null) {
                Otcen selectedPerson = tabl_otcen.getSelectionModel().getSelectedItem();
                String id = selectedPerson.getId();
                dataCon.deleteData_otc(id);
                restartConnection_otc();
            }}
    }

    public void show_f(MouseEvent mouseEvent) {
        if (tabl.getSelectionModel().getSelectedItem() != null) {
            Fakultet selectedPerson = tabl.getSelectionModel().getSelectedItem();
            String id_field = selectedPerson.getId();
            String name_field = selectedPerson.getName();
            kod_f_tekst.setText(id_field);
            name_f_tekst.setText(name_field);
        }
    }
    private void restartConnection_otc() {
        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
            tabl_otcen_kod.setCellValueFactory(new PropertyValueFactory<Otcen,Integer>("Id"));
            tabl_otcen_fios.setCellValueFactory(new PropertyValueFactory<Otcen,String>("Name_s"));
            tabl_otcen_name_r.setCellValueFactory(new PropertyValueFactory<Otcen,String>("Name_r"));
            tabl_otcen_otcen.setCellValueFactory(new PropertyValueFactory<Otcen,Integer>("Otc"));
            tabl_otcen_disz.setCellValueFactory(new PropertyValueFactory<Otcen,String>("Name_d"));
            tabl_otcen_fiop.setCellValueFactory(new PropertyValueFactory<Otcen,String>("Name_p"));

            tabl_otcen.setItems(dataCon.getOtcenList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void restartConnection_stud() {
        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
            tabl_stud_kod.setCellValueFactory(new PropertyValueFactory<Student,Integer>("Id"));
            tabl_stud_fio.setCellValueFactory(new PropertyValueFactory<Student,String>("Name_s"));
            tabl_stud_grup.setCellValueFactory(new PropertyValueFactory<Student,String>("Name_g"));

            tabl_stud.setItems(dataCon.getStudList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void restartConnection_f() {
        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
            id_t.setCellValueFactory(new PropertyValueFactory<Fakultet, Integer>("id"));
            name_t.setCellValueFactory(new PropertyValueFactory<Fakultet, String>("name"));
            tabl.setItems(dataCon.getPersonList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void restartConnection_prep() {
        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
            tabl_prep_kod.setCellValueFactory(new PropertyValueFactory<Prepod,Integer>("Id"));
            tabl_prep_fio.setCellValueFactory(new PropertyValueFactory<Prepod,String>("Name_p"));
            tabl_prep_dolg.setCellValueFactory(new PropertyValueFactory<Prepod,String>("Name_d"));


            tabl_prep.setItems(dataCon.getPrepList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void restartConnection_vid() {
        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
            tabl_disz_kod.setCellValueFactory(new PropertyValueFactory<Disziplin, Integer>("id"));
            tabl_disz_name.setCellValueFactory(new PropertyValueFactory<Disziplin, String>("name"));
            tabl_vid.setItems(dataCon.getVidList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void restartConnection_disz() {
        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
            tabl_disz_kod.setCellValueFactory(new PropertyValueFactory<Disziplin, Integer>("id"));
            tabl_disz_name.setCellValueFactory(new PropertyValueFactory<Disziplin, String>("name"));
            tabl_disz.setItems(dataCon.getDiszList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void restartConnection_dolg() {
        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
            kod_dolg.setCellValueFactory(new PropertyValueFactory<Dolgnost, Integer>("id"));
            name_dolg.setCellValueFactory(new PropertyValueFactory<Dolgnost, String>("name"));
            tabl_dolg.setItems(dataCon.getDolgList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void restartConnection_spec() throws ClassNotFoundException {
        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
            kod_spec.setCellValueFactory(new PropertyValueFactory<Special,Integer>("Id"));
            nam_spec.setCellValueFactory(new PropertyValueFactory<Special,String>("Name_s"));
            colum_shifr.setCellValueFactory(new PropertyValueFactory<Special,String>("Shifr"));
            colum_fak.setCellValueFactory(new PropertyValueFactory<Special,String>("Name_f"));

            tabl_spec.setItems(dataCon.getSpecList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void stop() throws Exception {
        if (dataCon != null) {
            dataCon.shutdown();
        }
    }
    private void restartConnection_grup() throws ClassNotFoundException {
        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataCon = new ConnectDB("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/datebase?serverTimezone=UTC&useSSL=false", "root", "admin");
            tabl_grup_kod.setCellValueFactory(new PropertyValueFactory<Grupa,Integer>("Id"));
            tabl_grup_name_g.setCellValueFactory(new PropertyValueFactory<Grupa,String>("Name_g"));
            tabl_grup_name_s.setCellValueFactory(new PropertyValueFactory<Grupa,String>("Name_s"));
            tabl_grup_name_f.setCellValueFactory(new PropertyValueFactory<Grupa,String>("Name_f"));

            tabl_grup.setItems(dataCon.getGrupList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void click_comb(ActionEvent actionEvent) {
        if(k_tab==9){
            text_3.setText(String.valueOf(combo_1.getValue()));}
        else text_4.setText(String.valueOf(combo_1.getValue()));
    }

    public void click_tabl_spec(MouseEvent mouseEvent) {
        if (tabl_spec.getSelectionModel().getSelectedItem() != null) {
            Special selectedPerson = tabl_spec.getSelectionModel().getSelectedItem();

            String id_field = selectedPerson.getId();
            String name_spec = selectedPerson.getName_s();
            String shifr = selectedPerson.getShifr();
            String name_f = selectedPerson.getName_f();
            kod_f_tekst.setText(id_field);
            name_f_tekst.setText(name_spec);
text_3.setText(shifr);
text_4.setText(name_f);
        }
    }

    public void click_tabl_dolg(MouseEvent mouseEvent) {
        if (tabl_dolg.getSelectionModel().getSelectedItem() != null) {
            Dolgnost selectedPerson = tabl_dolg.getSelectionModel().getSelectedItem();

            String id_field = selectedPerson.getId();
            String name_field = selectedPerson.getName();
            kod_f_tekst.setText(id_field);
            name_f_tekst.setText(name_field);
        }
    }

    public void click_tabl_vid(MouseEvent mouseEvent) {
        if (tabl_vid.getSelectionModel().getSelectedItem() != null) {
            Vid_Rab selectedPerson = tabl_vid.getSelectionModel().getSelectedItem();

            String id_field = selectedPerson.getId();
            String name_field = selectedPerson.getName();
            kod_f_tekst.setText(id_field);
            name_f_tekst.setText(name_field);
        }
    }

    public void clicl_tabl_disz(MouseEvent mouseEvent) {
        if (tabl_disz.getSelectionModel().getSelectedItem() != null) {
            Disziplin selectedPerson = tabl_disz.getSelectionModel().getSelectedItem();

            String id_field = selectedPerson.getId();
            String name_field = selectedPerson.getName();
            kod_f_tekst.setText(id_field);
            name_f_tekst.setText(name_field);
        }
    }

    public void click_tabl_grup(MouseEvent mouseEvent) {
        if (tabl_grup.getSelectionModel().getSelectedItem() != null) {
            Grupa selectedPerson = tabl_grup.getSelectionModel().getSelectedItem();

            String id_field = selectedPerson.getId();
            String name_spec = selectedPerson.getName_g();
            String name_sp = selectedPerson.getName_s();
            String name_f = selectedPerson.getName_f();
            kod_f_tekst.setText(id_field);
            name_f_tekst.setText(name_spec);
            text_3.setText(name_sp);
            text_4.setText(name_f);
        }
    }

    public void click_comb2(ActionEvent actionEvent) {
        if(k_tab==6){
        text_3.setText(String.valueOf(combo_2.getValue()));}
        if(k_tab==7){
            text_3.setText(String.valueOf(combo_2.getValue()));}
        if(k_tab==8){
            text_3.setText(String.valueOf(combo_2.getValue()));}
        if(k_tab==9){
            name_f_tekst.setText(String.valueOf(combo_2.getValue()));}
    }

    public void click_tabl_prep(MouseEvent mouseEvent) {
        if (tabl_prep.getSelectionModel().getSelectedItem() != null) {
            Prepod selectedPerson = tabl_prep.getSelectionModel().getSelectedItem();

            String id_field = selectedPerson.getId();
            String name_spec = selectedPerson.getName_p();
            String name_sp = selectedPerson.getName_d();
            kod_f_tekst.setText(id_field);
            name_f_tekst.setText(name_spec);
            text_3.setText(name_sp);
        }
    }

    public void click_tabl_stud(MouseEvent mouseEvent) {
        if (tabl_stud.getSelectionModel().getSelectedItem() != null) {
            Student selectedPerson = tabl_stud.getSelectionModel().getSelectedItem();

            String id_field = selectedPerson.getId();
            String name_spec = selectedPerson.getName_s();
            String name_sp = selectedPerson.getName_g();
            kod_f_tekst.setText(id_field);
            name_f_tekst.setText(name_spec);
            text_3.setText(name_sp);
        }
    }

    public void click_tabl_otcen(MouseEvent mouseEvent) {
        if (tabl_otcen.getSelectionModel().getSelectedItem() != null) {
            Otcen selectedPerson = tabl_otcen.getSelectionModel().getSelectedItem();

            String id_field = selectedPerson.getId();
            String name_spec = selectedPerson.getName_s();
            String name_sp = selectedPerson.getName_r();
            String oth = selectedPerson.getOtc();
            String name_dis = selectedPerson.getName_d();
            String name_pr = selectedPerson.getName_p();
            kod_f_tekst.setText(id_field);
            name_f_tekst.setText(name_spec);
            text_3.setText(name_sp);
            text_4.setText(oth);
            text_5.setText(name_dis);
            text_6.setText(name_pr);
        }
    }

    public void click_comb3(ActionEvent actionEvent) {
        if(k_tab==9){
            text_5.setText(String.valueOf(combo_3.getValue()));}
    }

    public void click_comb4(ActionEvent actionEvent) {
        if(k_tab==9){
            text_6.setText(String.valueOf(combo4.getValue()));}
    }

    public void click_rast(ActionEvent actionEvent) {
    }

    public void click_comb5(ActionEvent actionEvent) {
        try {
            combo_6.setItems(dataCon.getSpec_n_cList(String.valueOf(combo_5.getValue())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_comb6(ActionEvent actionEvent) {
        try {
            combo_7.setItems(dataCon.getGrup_n_cList(String.valueOf(combo_6.getValue())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_comb7(ActionEvent actionEvent) {
    }

    public void click_comb8(ActionEvent actionEvent) {
    }

    public void click_but_reit(ActionEvent actionEvent) {
        String dis = String.valueOf(combo_8.getValue());
        String grup = String.valueOf(combo_7.getValue());
tabl_reit.setVisible(true);
tabl_reit.getItems().clear();
        try {
            tabl_reit_fio.setCellValueFactory(new PropertyValueFactory<Reiting,String>("fio"));
            tabl_reit_gr.setCellValueFactory(new PropertyValueFactory<Reiting,String>("name_g"));
            tabl_reit_dis.setCellValueFactory(new PropertyValueFactory<Reiting,String>("name_d"));
            tabl_reit_lab.setCellValueFactory(new PropertyValueFactory<Reiting,Float>("sr_lab"));
            tabl_reit_usr.setCellValueFactory(new PropertyValueFactory<Reiting,Float>("sr_usr"));
            tabl_reit_atest.setCellValueFactory(new PropertyValueFactory<Reiting,Integer>("atest"));
            tabl_reit_itog.setCellValueFactory(new PropertyValueFactory<Reiting,Float>("itog"));
            tabl_reit.setItems(dataCon.getReitList(dis,grup));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
    }

    }

    public void click_reit(ActionEvent actionEvent) {
        log_dob.setVisible(false);
        pass_1_dob.setVisible(false);
        pass_2_dob.setVisible(false);
        but_dob.setVisible(false);
        tabl_reit.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(false);
        tabl.setVisible(false);
        tabl_vid.setVisible(false);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(false);
        name_f_tekst.setVisible(false);
        text_3.setVisible(false);
        text_4.setVisible(false);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(false);
        lab_f_n.setVisible(false);
        label_3.setVisible(false);
        label_4.setVisible(false);
        label_5.setVisible(false);
        label_6.setVisible(false);
        label_7.setVisible(true);
        label_8.setVisible(true);
        label_9.setVisible(true);
        label_10.setVisible(true);
        combo_5.setVisible(true);
        combo_6.setVisible(true);
        combo_7.setVisible(true);
        combo_8.setVisible(true);
        but_reit.setVisible(true);
        insert_f.setVisible(false);
        updete_f.setVisible(false);
        delete_f.setVisible(false);
        combo_1.setVisible(false);
        combo_2.setVisible(false);
        combo_3.setVisible(false);
        combo4.setVisible(false);
        try {
            combo_8.setItems(dataCon.getDisz_nList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            combo_5.setItems(dataCon.getFacList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            combo_6.setItems(dataCon.getSpec_nList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            combo_7.setItems(dataCon.getGrup_nList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void click_polz(ActionEvent actionEvent) {
        log_dob.setVisible(true);
        pass_1_dob.setVisible(true);
        pass_2_dob.setVisible(true);
        but_dob.setVisible(true);
        tabl_reit.setVisible(false);
        tabl_otcen.setVisible(false);
        tabl_stud.setVisible(false);
        tabl_prep.setVisible(false);
        tabl_grup.setVisible(false);
        tabl_disz.setVisible(false);
        tabl_dolg.setVisible(false);
        tabl.setVisible(false);
        tabl_vid.setVisible(false);
        tabl_spec.setVisible(false);
        kod_f_tekst.setVisible(false);
        name_f_tekst.setVisible(false);
        text_3.setVisible(false);
        text_4.setVisible(false);
        text_5.setVisible(false);
        text_6.setVisible(false);
        lab_f_k.setVisible(false);
        lab_f_n.setVisible(false);
        label_3.setVisible(false);
        label_4.setVisible(false);
        label_5.setVisible(false);
        label_6.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);
        label_9.setVisible(false);
        label_10.setVisible(false);
        combo_5.setVisible(false);
        combo_6.setVisible(false);
        combo_7.setVisible(false);
        combo_8.setVisible(false);
        but_reit.setVisible(false);
        insert_f.setVisible(false);
        updete_f.setVisible(false);
        delete_f.setVisible(false);
        combo_1.setVisible(false);
        combo_2.setVisible(false);
        combo_3.setVisible(false);
        combo4.setVisible(false);
    }

    public void click_but_dob(ActionEvent actionEvent) {
        String log = log_dob.getText();
        String pass_1 = pass_1_dob.getText();
        String pass_2 = pass_2_dob.getText();
        if(pass_1.equals(pass_2)){
            dataCon.prov_pol(log,pass_1);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение!");
            alert.setContentText("Пароли не совпадают!");
            alert.show();
        }
    }
}
