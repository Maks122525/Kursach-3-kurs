package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class ConnectDB {

    private Connection connection;

    public ConnectDB(String driverClassName, String dbURL, String user, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void avt_pol(String login, String password) {
        String sql = "SELECT * FROM `datebase`.`авторизация`" + "WHERE `логин`=? AND `пароль`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            int kol = 0;
            while (rs.next()) {
                kol++;
            }
            System.out.println(kol);
            if (kol > 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("form2.fxml"));
                Parent root1 = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Учет успеваемости");
                stage.setScene(new Scene(root1,900,680));
                stage.getIcons().add(new Image(new File("C:\\Программы\\Kursach\\Klient\\src\\Image\\unnamed.jpg").toURL().toString()));
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Неправильный логин или пароль!");
                alert.show();
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }


    public void prov_pol(String login,String password) {
        String sql = "SELECT * FROM `datebase`.`авторизация`" + "WHERE `логин`=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();

            int kol = 0;
            while (rs.next()) {
                kol++;
            }
            System.out.println(kol);
            if (kol == 0) {
                String sql_2 = "INSERT INTO `datebase`.`авторизация` (`логин`, `пароль`)" +
                        "VALUES (?, ?)";
                PreparedStatement preparedStatement_1 = connection.prepareStatement(sql_2);
                preparedStatement_1.setString(1, login);
                preparedStatement_1.setString(2, password);
                preparedStatement_1.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Оповещение!");
                alert.setContentText("Пользватель успешно добавлен!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Предупреждение!");
                alert.setContentText("Пользватель с таким именем уже есть!");
                alert.show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ObservableList<Fakultet> getPersonList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from факультет");
        ) {
            ObservableList<Fakultet> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String id = rs.getString("Код факультета");
                String name = rs.getString("Наименование факультета");

                Fakultet tabl = new Fakultet(id, name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<Vid_Rab> getVidList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from `вид работы`");
        ) {
            ObservableList<Vid_Rab> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String id = rs.getString("Код работы");
                String name = rs.getString("Название работы");

                Vid_Rab tabl = new Vid_Rab(id, name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<Disziplin> getDiszList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from `дисциплины`");
        ) {
            ObservableList<Disziplin> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String id = rs.getString("Код дисциплины");
                String name = rs.getString("Наименование дисциплины");

                Disziplin tabl = new Disziplin(id, name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<Dolgnost> getDolgList() throws SQLException {
        float a;
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from должности");
        ) {
            ObservableList<Dolgnost> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String id = rs.getString("Код должности");
                a = Float.parseFloat(id);
                String name = rs.getString("Наименование должности");
                Dolgnost tabl = new Dolgnost(id, name);
                b.add(tabl);
            }
            return b;
        }
        
    }
    public ObservableList<String> getFacList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `факультет`.`Наименование факультета` from факультет");
        ) {
            ObservableList<String> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("Наименование факультета");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<String> getStud_nList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `студент`.`ФИО студента` from студент");
        ) {
            ObservableList<String> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("ФИО студента");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<Reiting> getReitList(String disz,String grup) throws SQLException {
        ObservableList<Reiting> b = FXCollections.observableArrayList();
        String  sql = "select `студент`.`ФИО студента`,`группа`.`Наименование группы`," +
                "`дисциплины`.`Наименование дисциплины`, AVG(`оценки`.`Оценка`) AS `Среднее_1`" +
                "from `оценки`,`студент`,`группа`,`дисциплины`" +
                "WHERE `оценки`.`Код работы`=1  AND `студент`.`Код студента`=`оценки`.`Код студента` " +
                "AND `группа`.`Код группы`=`студент`.`Код группы` " +
                "AND`группа`.`Наименование группы`=?" +
                "AND `дисциплины`.`Код дисциплины`= `оценки`.`Код дисциплины`" +
                "AND `дисциплины`.`Наименование дисциплины`=?" +
                "GROUP BY `студент`.`ФИО студента`,`группа`.`Наименование группы`,`дисциплины`.`Наименование дисциплины`";
        Reiting []reiting = new Reiting[10000];
        for(int j=0;j<10000;j++) reiting[j] = new Reiting();
        Statement stmnt = connection.createStatement();
        ResultSet rs = null;
        PreparedStatement preparedStatement_1 = connection.prepareStatement(sql);
        preparedStatement_1.setString(1, grup);
        preparedStatement_1.setString(2, disz);
        rs = preparedStatement_1.executeQuery();
        int i = 0;
        try {

            while (rs.next()) {
                System.out.println(rs.getString("ФИО студента"));
                reiting[i].setFio(rs.getString("ФИО студента"));
                reiting[i].setName_g(rs.getString("Наименование группы"));
                reiting[i].setName_d(rs.getString("Наименование дисциплины"));
                reiting[i].setSr_lab(rs.getString("Среднее_1"));
                System.out.println(reiting[i].getSr_lab());
                i++;


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String  sql_2 = "select `студент`.`ФИО студента`,`группа`.`Наименование группы`," +
                "`дисциплины`.`Наименование дисциплины`, AVG(`оценки`.`Оценка`) AS `Среднее_1`" +
                "from `оценки`,`студент`,`группа`,`дисциплины`" +
                "WHERE `оценки`.`Код работы`=2  AND `студент`.`Код студента`=`оценки`.`Код студента` " +
                "AND `группа`.`Код группы`=`студент`.`Код группы` " +
                "AND`группа`.`Наименование группы`=?" +
                "AND `дисциплины`.`Код дисциплины`= `оценки`.`Код дисциплины`" +
                "AND `дисциплины`.`Наименование дисциплины`=?" +
                "AND `студент`.`ФИО студента`=?" +
                "GROUP BY `студент`.`ФИО студента`,`группа`.`Наименование группы`,`дисциплины`.`Наименование дисциплины`";

        for(int j=0;j<i;j++) {
            ResultSet resSet_2 = null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, grup);
            preparedStatement.setString(2, disz);
            preparedStatement.setString(3, reiting[j].getFio());
            resSet_2 = preparedStatement.executeQuery();
            try {
                while (resSet_2.next()) {

                        reiting[j].setSr_usr(resSet_2.getString("Среднее_1"));
                        System.out.println(reiting[j].getSr_usr());

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        String  sql_3 = "select `студент`.`ФИО студента`,`группа`.`Наименование группы`," +
                "`дисциплины`.`Наименование дисциплины`, AVG(`оценки`.`Оценка`) AS `Среднее_1`" +
                "from `оценки`,`студент`,`группа`,`дисциплины`" +
                "WHERE `оценки`.`Код работы`=3  AND `студент`.`Код студента`=`оценки`.`Код студента` " +
                "AND `группа`.`Код группы`=`студент`.`Код группы` " +
                "AND`группа`.`Наименование группы`=?" +
                "AND `дисциплины`.`Код дисциплины`= `оценки`.`Код дисциплины`" +
                "AND `дисциплины`.`Наименование дисциплины`=?" +
                "AND `студент`.`ФИО студента`=?" +
                "GROUP BY `студент`.`ФИО студента`,`группа`.`Наименование группы`,`дисциплины`.`Наименование дисциплины`" ;
        ResultSet resSet = null;
        for(int j=0;j<i;j++){
           PreparedStatement preparedStatement = connection.prepareStatement(sql_3);
            preparedStatement.setString(1, grup);
            preparedStatement.setString(2, disz);
            preparedStatement.setString(3, reiting[j].getFio());
            resSet = preparedStatement.executeQuery();
           try{ while (resSet.next()){

                reiting[j].setAtest(resSet.getString("Среднее_1"));}
                System.out.println(reiting[j].getAtest());

        } catch (SQLException throwables) {
               throwables.printStackTrace();
           }
        }

        for(int j=0;j<i;j++){
            reiting[j].reit();
            b.add(reiting[j]);
        }
        return b;
    }
    public ObservableList<String> getRabList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `вид работы`.`Название работы` from `вид работы`");
        ) {
            ObservableList<String> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("Название работы");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<String> getDisz_nList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `дисциплины`.`Наименование дисциплины` from `дисциплины`");
        ) {
            ObservableList<String> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("Наименование дисциплины");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<String> getPrep_nList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `преподаватели`.`ФИО преподавателя` from `преподаватели`");
        ) {
            ObservableList<String> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("ФИО преподавателя");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<String> getGrup_nList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `группа`.`Наименование группы` from группа");
        ) {
            ObservableList<String> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("Наименование группы");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<String> getDolg_nList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `должности`.`Наименование должности` from должности");
        ) {
            ObservableList<String> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("Наименование должности");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<String> getSpec_nList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `специальность`.`Наименование специальность` from `специальность`");
        ) {
            ObservableList<String> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("Наименование специальность");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        }
    }
    public ObservableList<String> getSpec_n_cList(String fac) throws SQLException {
        String sql  = "select `специальность`.`Наименование специальность` " +
                "from `специальность`,`факультет`" +
                "WHERE `специальность`.`Код факультета` = `факультет`.`Код факультета`" +
                "AND `факультет`.`Наименование факультета` = ?";
        ObservableList<String> b = FXCollections.observableArrayList();

        try
        {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fac);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Наименование специальность");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при запросе!");
            alert.showAndWait();
        }
        return b;
    }
    public ObservableList<String> getGrup_n_cList(String fac) throws SQLException {
        String sql  = "select `группа`.`Наименование группы` " +
                "from `группа`,`специальность`" +
                "WHERE `специальность`.`Код специальности` = `группа`.`Код специальности`" +
                "AND `специальность`.`Наименование специальность` = ?";
        ObservableList<String> b = FXCollections.observableArrayList();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fac);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Наименование группы");
                String tabl = new String(name);
                b.add(tabl);
            }
            return b;
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при запросе!");
            alert.showAndWait();
        }
        return b;
    }
    public ObservableList<Special> getSpecList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `специальность`.`Код специальности`," +
                        "`специальность`.`Наименование специальность`,`специальность`.`Шифр специальности`," +
                        "`факультет`.`Наименование факультета` from `специальность`,`факультет`" +
                        "WHERE (`факультет`.`Код факультета` = `специальность`.`Код факультета`)");
        ) {
            ObservableList<Special> b = FXCollections.observableArrayList();
            while (rs.next()) {
                 String id = rs.getString("Код специальности");
                String name_s = rs.getString("Наименование специальность");
                String shifr = rs.getString("Шифр специальности");
                String name_f = rs.getString("Наименование факультета");
                Special tabl_1 = new Special(id,name_s,shifr,name_f);
                b.add(tabl_1);
            }
            return b;
        }
    }
    public ObservableList<Otcen> getOtcenList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `оценки`.`Код оценки`," +
                        "`студент`.`ФИО студента`,`вид работы`.`Название работы`," +
                        "`оценки`.`Оценка`,`дисциплины`.`Наименование дисциплины`,`преподаватели`.`ФИО преподавателя`" +
                        " from `оценки`,`студент`,`вид работы`,`дисциплины`,`преподаватели`" +
                        "WHERE (`студент`.`Код студента` = `оценки`.`Код студента`" +
                        "AND `вид работы`.`Код работы` = `оценки`.`Код работы`" +
                        "AND `дисциплины`.`Код дисциплины` = `оценки`.`Код дисциплины`" +
                        "AND `преподаватели`.`Код преподавателя` = `оценки`.`Код преподавателя`)");
        ) {
            ObservableList<Otcen> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String id = rs.getString("Код оценки");
                String name_s = rs.getString("ФИО студента");
                String name_r = rs.getString("Название работы");
                String otcen = rs.getString("Оценка");
                String name_d = rs.getString("Наименование дисциплины");
                String name_p = rs.getString("ФИО преподавателя");
                Otcen tabl_1 = new Otcen(id,name_s,name_r,otcen,name_d,name_p);
                b.add(tabl_1);
            }
            return b;
        }
    }
    public ObservableList<Prepod> getPrepList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `преподаватели`.`Код преподавателя`," +
                        "`преподаватели`.`ФИО преподавателя`,`должности`.`Наименование должности`" +
                        " from `преподаватели`,`должности`" +
                        "WHERE (`должности`.`Код должности` = `преподаватели`.`Код должности`)");
        ) {
            ObservableList<Prepod> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String id = rs.getString("Код преподавателя");
                String name_s = rs.getString("ФИО преподавателя");
                String shifr = rs.getString("Наименование должности");
                Prepod tabl_1 = new Prepod(id,name_s,shifr);
                b.add(tabl_1);
            }
            return b;
        }
    }
    public ObservableList<Grupa> getGrupList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `группа`.`Код группы`," +
                        "`группа`.`Наименование группы`,`специальность`.`Наименование специальность`," +
                        "`факультет`.`Наименование факультета` from `группа`,`специальность`,`факультет`" +
                        "WHERE `факультет`.`Код факультета` = `группа`.`Код факультета`" +
                        "AND `специальность`.`Код специальности`=`группа`.`Код специальности`");
        ) {
            ObservableList<Grupa> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String id = rs.getString("Код группы");
                String name_g = rs.getString("Наименование группы");
                String name_s = rs.getString("Наименование специальность");
                String name_f = rs.getString("Наименование факультета");
                Grupa tabl_1 = new Grupa(id,name_g,name_s,name_f);
                b.add(tabl_1);
            }
            return b;
        }
    }
    public ObservableList<Student> getStudList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select `студент`.`Код студента`," +
                        "`студент`.`ФИО студента`,`группа`.`Наименование группы`" +
                        " from `студент`,`группа`" +
                        "WHERE `группа`.`Код группы` = `студент`.`Код группы`" );
        ) {
            ObservableList<Student> b = FXCollections.observableArrayList();
            while (rs.next()) {
                String id = rs.getString("Код студента");
                String name_g = rs.getString("ФИО студента");
                String name_s = rs.getString("Наименование группы");
                Student tabl_1 = new Student(id,name_g,name_s);
                b.add(tabl_1);
            }
            return b;
        }
    }
    public void insertData_f(String id, String name) throws SQLException {
        String sql = "INSERT INTO `datebase`.`факультет` (`Код факультета`, `Наименование факультета`)" +
                "VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при дабавлении данных!");
            alert.showAndWait();
        }
    }
    public void insertData_vid(String id, String name) throws SQLException {
        String sql = "INSERT INTO `datebase`.`вид работы` (`Код работы`, `Название работы`)" +
                "VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при дабавлении данных!");
            alert.showAndWait();
        }
    }
    public void insertData_disz(String id, String name) throws SQLException {
        String sql = "INSERT INTO `datebase`.`дисциплины` (`Код дисциплины`, `Наименование дисциплины`)" +
                "VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при дабавлении данных!");
            alert.showAndWait();
        }
    }
    public void insertData_dolg(String id, String name) throws SQLException {
        String sql = "INSERT INTO `datebase`.`должности` (`Код должности`, `Наименование должности`)" +
                "VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при дабавлении данных!");
            alert.showAndWait();
        }
    }
    public void insertData_prep(String id, String name_p,String name_d) throws SQLException {
        String k_d = null;
        String sql = "select `должности`.`Код должности`" +
                " from `должности`" +
                "WHERE    `должности`.`Наименование должности`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_d);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_f = rs.getString("Код должности");
                k_d = new String(kod_f);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_d);
        String sql_2 = "INSERT INTO `datebase`.`преподаватели` (`Код преподавателя`,`ФИО преподавателя`,`Код должности`)" +
                "VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name_p);
            preparedStatement.setString(3, k_d);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при дабавлении данных!");
            alert.showAndWait();
        }
    }
    public void insertData_spec(String id, String name_s,String shifr,String name_f) throws SQLException {
        String k_f = null;
        String sql = "select `факультет`.`Код факультета`" +
                " from `факультет`" +
                "WHERE    `факультет`.`Наименование факультета`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_f);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
               String kod_f = rs.getString("Код факультета");
                k_f = new String(kod_f);
               break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_f);
        String sql_2 = "INSERT INTO `datebase`.`специальность` (`Код специальности`,`Наименование специальность`,`Шифр специальности`,`Код факультета`)" +
                "VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name_s);
            preparedStatement.setString(3, shifr);
            preparedStatement.setString(4, k_f);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при дабавлении данных!");
            alert.showAndWait();
        }
    }
    public void insertData_stud(String id, String name_s,String name_g) throws SQLException {
        String k_f = null;
        String sql = "select `группа`.`Код группы`" +
                " from `группа`" +
                "WHERE    `группа`.`Наименование группы`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_g);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_f = rs.getString("Код группы");
                k_f = new String(kod_f);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_f);
        String sql_2 = "INSERT INTO `datebase`.`студент` (`Код студента`,`ФИО студента`,`Код группы`)" +
                "VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name_s);
            preparedStatement.setString(3, k_f);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при дабавлении данных!");
            alert.showAndWait();
        }
    }
    public void insertData_grup(String id, String name_g,String name_s,String name_f) throws SQLException {
        String k_f = null;
        String sql = "select `факультет`.`Код факультета`" +
                " from `факультет`" +
                "WHERE    `факультет`.`Наименование факультета`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_f);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_f = rs.getString("Код факультета");
                k_f = new String(kod_f);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String k_s = null;
        String sql_1 = "select `специальность`.`Код специальности`" +
                " from `специальность`" +
                "WHERE    `специальность`.`Наименование специальность`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_1);
            preparedStatement.setString(1, name_s);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_s = rs.getString("Код специальности");
                k_s = new String(kod_s);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql_2 = "INSERT INTO `datebase`.`группа` (`Код группы`,`Наименование группы`,`Код специальности`,`Код факультета`)" +
                "VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name_g);
            preparedStatement.setString(3, k_s);
            preparedStatement.setString(4, k_f);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при дабавлении данных!");
            alert.showAndWait();
        }
    }
    public void insertData_otcen(String id, String name_s,String name_r,String otc,String name_d,String name_p) throws SQLException {
        String k_s = null;
        String sql = "select `студент`.`Код студента`" +
                " from `студент`" +
                "WHERE    `студент`.`ФИО студента`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_s);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_f = rs.getString("Код студента");
                k_s = new String(kod_f);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_s);
        String k_r = null;
        String sql_1 = "select `вид работы`.`Код работы`" +
                " from `вид работы`" +
                "WHERE    `вид работы`.`Название работы`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_1);
            preparedStatement.setString(1, name_r);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_s = rs.getString("Код работы");
                k_r = new String(kod_s);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_r);
        String k_d = null;
        String sql_3 = "select `дисциплины`.`Код дисциплины`" +
                " from `дисциплины`" +
                "WHERE    `дисциплины`.`Наименование дисциплины`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_3);
            preparedStatement.setString(1, name_d);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_s = rs.getString("Код дисциплины");
                k_d = new String(kod_s);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_d);
        String k_p = null;
        String sql_4 = "select `преподаватели`.`Код преподавателя`" +
                " from `преподаватели`" +
                "WHERE    `преподаватели`.`ФИО преподавателя`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_4);
            preparedStatement.setString(1, name_p);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_s = rs.getString("Код преподавателя");
                k_p = new String(kod_s);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_p);
        String sql_2 = "INSERT INTO `datebase`.`оценки` (`Код оценки`,`Код студента`,`Код работы`,`Оценка`," +
                "`Код дисциплины`,`Код преподавателя`)" +
                "VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, k_s);
            preparedStatement.setString(3, k_r);
            preparedStatement.setString(4, otc);
            preparedStatement.setString(5, k_d);
            preparedStatement.setString(6, k_p);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при дабавлении данных!");
            alert.showAndWait();
        }
    }
    public void deleteData_otc(String id) {
        String sql = "DELETE FROM `datebase`.`оценки`" + "WHERE (`Код оценки` = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при удалении данных!");
            alert.showAndWait();
        }
    }
    public void deleteData_stud(String id) {
        String sql = "DELETE FROM `datebase`.`студент`" + "WHERE (`Код студента` = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при удалении данных!");
            alert.showAndWait();
        }
    }
    public void deleteData_f(String id) {
        String sql = "DELETE FROM `datebase`.`факультет`" + "WHERE (`Код факультета` = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при удалении данных!");
            alert.showAndWait();
        }
    }
    public void deleteData_p(String id) {
        String sql = "DELETE FROM `datebase`.`преподаватели`" + "WHERE (`Код преподавателя` = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при удалении данных!");
            alert.showAndWait();
        }
    }
    public void deleteData_vid(String id) {
        String sql = "DELETE FROM `datebase`.`вид работы`" + "WHERE (`Код работы` = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при удалении данных!");
            alert.showAndWait();
        }
    }
    public void deleteData_disz(String id) {
        String sql = "DELETE FROM `datebase`.`дисциплины`" + "WHERE (`Код дисциплины` = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при удалении данных!");
            alert.showAndWait();
        }
    }
    public void deleteData_dolg(String id) {
        String sql = "DELETE FROM `datebase`.`должности`" + "WHERE (`Код должности` = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при удалении данных!");
            alert.showAndWait();
        }
    }
    public void deleteData_spec(String id) {
        String sql = "DELETE FROM `datebase`.`специальность`" + "WHERE (`Код специальности` = ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при удалении данных!");
            alert.showAndWait();
        }
    }
    public void deleteData_grup(String id) {
        String sql = "DELETE FROM `datebase`.`группа`" + "WHERE (`Код группы` = ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при удалении данных!");
            alert.showAndWait();
        }
    }
    public void updateData_f(String old_id, String new_id, String name) {
        String sql = "UPDATE `datebase`.`факультет`" +  "SET `Код факультета` = ?, `Наименование факультета` = ? WHERE (`Код факультета` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, new_id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, old_id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при выполнении команды UPDATE!");
            alert.showAndWait();
        }
    }
    public void updateData_vid(String old_id, String new_id, String name) {
        String sql = "UPDATE `datebase`.`вид работы`" +  "SET `Код работы` = ?, `Название работы` = ? WHERE (`Код работы` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, new_id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, old_id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при выполнении команды UPDATE!");
            alert.showAndWait();
        }
    }
    public void updateData_disz(String old_id, String new_id, String name) {
        String sql = "UPDATE `datebase`.`дисциплины`" +  "SET `Код дисциплины` = ?, `Наименование дисциплины` = ? WHERE (`Код дисциплины` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, new_id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, old_id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при выполнении команды UPDATE!");
            alert.showAndWait();
        }
    }
    public void updateData_otc(String old_id, String id, String name_s,String name_r,String otc,String name_d,String name_p) {
        String k_s = null;
        String sql = "select `студент`.`Код студента`" +
                " from `студент`" +
                "WHERE    `студент`.`ФИО студента`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_s);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_f = rs.getString("Код студента");
                k_s = new String(kod_f);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_s);
        String k_r = null;
        String sql_1 = "select `вид работы`.`Код работы`" +
                " from `вид работы`" +
                "WHERE    `вид работы`.`Название работы`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_1);
            preparedStatement.setString(1, name_r);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_s = rs.getString("Код работы");
                k_r = new String(kod_s);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_r);
        String k_d = null;
        String sql_3 = "select `дисциплины`.`Код дисциплины`" +
                " from `дисциплины`" +
                "WHERE    `дисциплины`.`Наименование дисциплины`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_3);
            preparedStatement.setString(1, name_d);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_s = rs.getString("Код дисциплины");
                k_d = new String(kod_s);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_d);
        String k_p = null;
        String sql_4 = "select `преподаватели`.`Код преподавателя`" +
                " from `преподаватели`" +
                "WHERE    `преподаватели`.`ФИО преподавателя`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_4);
            preparedStatement.setString(1, name_p);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_s = rs.getString("Код преподавателя");
                k_p = new String(kod_s);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(k_p);
        System.out.println(old_id);
        String sql_5 = "UPDATE `datebase`.`оценки`" +  "SET `Код оценки` = ?, `Код студента` = ?, " +
                "`Код работы` = ?, `Оценка` = ?, `Код дисциплины` = ?,`Код преподавателя` = ? WHERE (`Код оценки` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_5);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, k_s);
            preparedStatement.setString(3, k_r);
            preparedStatement.setString(4, otc);
            preparedStatement.setString(5, k_d);
            preparedStatement.setString(6, k_p);
            preparedStatement.setString(7, old_id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при выполнении команды UPDATE!");
            alert.showAndWait();
        }
    }
    public void updateData_dolg(String old_id, String new_id, String name) {
        String sql = "UPDATE `datebase`.`должности`" +  "SET `Код должности` = ?, `Наименование должности` = ? WHERE (`Код должности` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, new_id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, old_id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при выполнении команды UPDATE!");
            alert.showAndWait();
        }
    }
    public void updateData_prep(String old_id, String new_id, String name_p,String name_d) {
        String k_f = null;
        String sql = "select `должности`.`Код должности`" +
                " from `должности`" +
                "WHERE    `должности`.`Наименование должности`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_d);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_f = rs.getString("Код должности");
                k_f = new String(kod_f);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql_2= "UPDATE `datebase`.`преподаватели`" +  "SET `Код преподавателя` = ?, " +
                "`ФИО преподавателя` = ?," +
                " `Код должности`=? WHERE (`Код преподавателя` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, new_id);
            preparedStatement.setString(2, name_p);
            preparedStatement.setString(3, k_f);
            preparedStatement.setString(4, old_id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при выполнении команды UPDATE!");
            alert.showAndWait();
        }
    }
    public void updateData_spez(String old_id, String new_id, String shifr,String name_s,String name_f) {
        String k_f = null;
        String sql = "select `факультет`.`Код факультета`" +
                " from `факультет`" +
                "WHERE    `факультет`.`Наименование факультета`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_f);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_f = rs.getString("Код факультета");
                k_f = new String(kod_f);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql_2= "UPDATE `datebase`.`специальность`" +  "SET `Код специальности` = ?, " +
                "`Наименование специальность` = ?,`Шифр специальности`=?," +
                " `Код факультета`=? WHERE (`Код специальности` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, new_id);
            preparedStatement.setString(2, shifr);
            preparedStatement.setString(3, name_s);
            preparedStatement.setString(4, k_f);
            preparedStatement.setString(5, old_id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при выполнении команды UPDATE!");
            alert.showAndWait();
        }
    }
    public void updateData_stud(String old_id, String new_id, String name_s,String name_g) {
        String k_f = null;
        String sql = "select `группа`.`Код группы`" +
                " from `группа`" +
                "WHERE    `группа`.`Наименование группы`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_g);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_f = rs.getString("Код группы");
                k_f = new String(kod_f);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql_2= "UPDATE `datebase`.`студент`" +  "SET `Код студента` = ?, " +
                "`ФИО студента` = ?," +
                " `Код группы`=? WHERE (`Код студента` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, new_id);
            preparedStatement.setString(2, name_s);
            preparedStatement.setString(3, k_f);
            preparedStatement.setString(4, old_id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при выполнении команды UPDATE!");
            alert.showAndWait();
        }
    }
    public void updateData_grup(String old_id, String new_id, String name_g,String name_s,String name_f) {
        String k_f = null;
        String sql = "select `факультет`.`Код факультета`" +
                " from `факультет`" +
                "WHERE    `факультет`.`Наименование факультета`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_f);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_f = rs.getString("Код факультета");
                k_f = new String(kod_f);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String k_s = null;
        String sql_1 = "select `специальность`.`Код специальности`" +
                " from `специальность`" +
                "WHERE    `специальность`.`Наименование специальность`=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_1);
            preparedStatement.setString(1, name_s);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kod_s = rs.getString("Код специальности");
                k_s = new String(kod_s);
                break;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql_2= "UPDATE `datebase`.`группа`" +  "SET `Код группы` = ?, " +
                "`Наименование группы` = ?,`Код специальности`=?," +
                " `Код факультета`=? WHERE (`Код группы` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);
            preparedStatement.setString(1, new_id);
            preparedStatement.setString(2, name_g);
            preparedStatement.setString(3, k_s);
            preparedStatement.setString(4, k_f);
            preparedStatement.setString(5, old_id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setContentText("Ошибка при выполнении команды UPDATE!");
            alert.showAndWait();
        }
    }
}

