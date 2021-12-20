package com.example.idz12;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                +dbPort  + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);
        return dbConnection;
    }


    //получение данных с бд
    public ArrayList<Const> getWorkers(String position) throws SQLException, ClassNotFoundException {
        String select = "SELECT Name, Surname, Patronymic, phoneNumber, DateBirth, City, HB, IdPas, PlasePas, DatePas, salary FROM anketa WHERE position = '" + position + "';";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        ResultSet rs =  prSt.executeQuery();
        ArrayList<Const> users = new ArrayList<>();
        while (rs.next()) {

            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String patronymic = rs.getString("Patronymic");
            String phoneNumber = rs.getString("phoneNumber");
            String DateBirth = rs.getString("DateBirth");
            String City  = rs.getString("City");
            String HB = rs.getString("HB");
            String IdPas = rs.getString("IdPas");
            String PlasePas = rs.getString("PlasePas");
            String DatePas = rs.getString("DatePas");
            String salary = rs.getString("salary");
            Const user = new Const(name, surname, patronymic, phoneNumber, DateBirth, City, HB, IdPas, PlasePas, DatePas, position, salary);
            users.add(user);

            System.out.println("Имя: " + name +
                    ", Фамилия: " + surname +
                    ", Отчество: " + patronymic +
                    ", номер телефона: " + phoneNumber +
                    ", дата рождения: " + DateBirth +
                    ", Город: " + City +
                    ", номер пасспорта: " + HB +
                    ", идентификационный номер: " + IdPas +
                    ", место выдачи пасспорта: " + PlasePas +
                    ", дата выдачи пасспорта: " + DatePas +
                    ", предварительная зарплата: " + salary);


        }

        return users;

    }
    public ArrayList<String> getPositions() throws SQLException, ClassNotFoundException {// вывод из бд.
        Statement prSt = getDbConnection().createStatement();
        ArrayList<String> positions = new ArrayList<>();
        ResultSet result =  prSt.executeQuery("select position from anketa ");
        while (result.next()){
            positions.add(result.getString("position"));

        }
        Set<String> _p = new HashSet<>(positions);
        return new ArrayList<String >(_p);
    }

    public void signUpUser(Const user) throws SQLException, ClassNotFoundException { //добавление данных в бд
//        String insert = "INSERT INTO " + "anketa" + "(" +
//                user.USER_NAME + "," +
//                user.USER_SURNAME + "," +
//                user.USER_PATRONYMIC + "," +
//                user.USER_PHONENUMBER + "," +
//                user.USER_DATEBIRTH + "," +
//                user.USER_CITY + "," +
//                user.USER_HB + "," +
//                user.USER_IDPAS + "," +
//                user.USER_PLACEPAS + "," +
//                user.USER_DATEPAS + "," +
//                user.USER_POSITION + "," +
//                user.USER_SALARY + ")"  +
//                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        String insert = "INSERT INTO anketa(Name, Surname, Patronymic, phoneNumber, DateBirth, City, HB, IdPas, PlasePas, DatePas, position, salary) VALUES (" +
                "'" + user.USER_NAME + "'" + "," +
                "'" +  user.USER_SURNAME + "'"+  "," +
                "'" +  user.USER_PATRONYMIC+ "'" + "," +
                "'" + user.USER_PHONENUMBER+ "'" + "," +
                "'" +  user.USER_DATEBIRTH+ "'" + "," +
                "'" + user.USER_CITY+ "'" + "," +
                "'" +  user.USER_HB+ "'" + "," +
                "'" + user.USER_IDPAS+ "'" + "," +
                "'" +  user.USER_PLACEPAS + "'" + "," +
                "'" +  user.USER_DATEPAS+ "'" + "," +
                "'" + user.USER_POSITION+ "'" + "," +
                "'" +  user.USER_SALARY+ "'" + ")";
        Statement prSt = getDbConnection().createStatement();
        prSt.execute(insert);
//        prSt.setString(1, Name);
//        prSt.setString(2, Surname);
//        prSt.setString(3, Patronymic);
//        prSt.setString(4, phoneNubmber);
//        prSt.setString(5, DateBirth);
//        prSt.setString(6, City);
//        prSt.setString(7, HB);
//        prSt.setString(8, IdPas);
//        prSt.setString(9, PlacePas);
//        prSt.setString(10, DatePas);
//        prSt.setString(11, position);
//        prSt.setString(12, salary);

      //  prSt.executeUpdate();



    }
}
