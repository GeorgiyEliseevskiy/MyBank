package com.company;

import java.sql.*;

public class DB {

    private final static String HOST = "localhost";
    private final static String PORT = "3307";
    private final static String DB_NAME = "java_bank";
    private final static String LOGIN = "root";
    private final static String PASS = "root";
    private static Connection dbConn = null;

    private final Connection getDbConn = null;

    private static Connection getDbConn() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public void isConn() throws SQLException, ClassNotFoundException {
        dbConn = getDbConn();
        System.out.println(dbConn.isValid(1000));
    }

    public static double selectValueInDeposit(int name_deposit) throws SQLException, ClassNotFoundException {
        String sql = "SELECT amount FROM base_accounts_deposits WHERE name_deposit = " + name_deposit;
        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        ResultSet rs = prSt.executeQuery();
        double ar = 0;
        if(rs.next()) {
            ar = rs.getDouble("amount");
        }
        return ar;
    }
    public static double selectValueInBase(int name_account) throws SQLException, ClassNotFoundException {
        String sql = "SELECT bank_account FROM data_bases WHERE name_account = " + name_account;
        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.executeQuery();
        ResultSet rs = prSt.executeQuery(sql);
        double ar = 0;

        if (rs.next()) {
            ar = rs.getDouble(1);
        }
        return ar;
    }

    public static void updateValueInBase(int name_account, double bank_account) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE data_bases SET bank_account = " + bank_account + " WHERE name_account = " + name_account;
        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.executeUpdate();
    }

    public static void updateValueInBaseTopUpAccount(int name_account, double amount) throws SQLException, ClassNotFoundException{
        double sum = selectValueInBase(name_account);
        double end = sum + amount;
        updateValueInBase(name_account, end);
    }

    public static void updateValueInBaseWithdraw_money(int name_account, double amount) throws SQLException, ClassNotFoundException{
        double sum = selectValueInBase(name_account);
        double end = sum - amount;
        updateValueInBase(name_account, end);

    }

    public static int isHasAccountInBase(int name_account) throws SQLException, ClassNotFoundException {
        // String checkSql = "SELECT EXISTS(SELECT 1 FROM data_bases HAVING name_account = 195260);";
        /* PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.executeQuery();
        ResultSet res = prSt.executeQuery(sql); */

        String sql = "SELECT name_account FROM data_bases WHERE name_account = " + name_account;
        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.executeQuery();
        ResultSet rs = prSt.executeQuery(sql);
        int ar = 0;
        if (rs.next()) {
            ar = 1;
        }
        else {
            ar = 0;
        }
        return ar;
    }

    public static void deleteDepositAccount(int name_deposit) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM base_accounts_deposits WHERE name_deposit = " +name_deposit;
        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.executeUpdate();

    }

    public static void updateValueDeposit(int name_deposit, double amount) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE base_accounts_deposits SET amount = " + amount + " WHERE name_deposit = " + name_deposit ;
        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.executeUpdate();
    }

    public static void updateBoolDeposit(int name_deposit) throws SQLException, ClassNotFoundException {
            String sql = "UPDATE data_bases SET deposit = 0 WHERE name_account = " + name_deposit;
            PreparedStatement prSt = getDbConn().prepareStatement(sql);
            prSt.executeUpdate();
    }
    public static boolean isHasDeposit(int name_account) throws SQLException, ClassNotFoundException {
        String sql = "SELECT deposit FROM data_bases WHERE name_account = " + name_account;
        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.executeQuery();
        ResultSet rs = prSt.executeQuery();
        int ar = 0;
        boolean result = false;
        if(rs.next()) {
            ar = rs.getInt("deposit");
        }

        if(ar == 1) {
            result = true;
        }
        return result;
    }

    public static int insertAccountInBase(String name_person, double bank_account) throws SQLException, ClassNotFoundException {

        // int result = jdbcTemplate.queryForInt("Select exists... // SPRING FRAMEWORK

        int hasAccount = 1;
        int name_account = 0;

        while (hasAccount == 1) {
            name_account = (int) (Math.random() * 1000000000);
            hasAccount = isHasAccountInBase(name_account);

        }

        String sql = "INSERT INTO data_bases (name, bank_account, name_account, ) VALUES (?, ?, ?);";

        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.setString(1, name_person);
        prSt.setDouble(2, bank_account);
        prSt.setInt(3, name_account);
        prSt.executeUpdate();

        return name_account;
    }

    public static void insertDepositInBase(int name_account, double amount) throws SQLException, ClassNotFoundException {

        int hasDeposit = 0;

        String sql = "INSERT INTO base_accounts_deposits (name_deposit, amount) VALUES (?,?);";

        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.setInt(1,name_account);
        prSt.setDouble(2, amount);
        prSt.executeUpdate();

        String sqlRet = "UPDATE data_bases SET DEPOSIT = 1 WHERE name_account = " + name_account;
        PreparedStatement prSt1 = getDbConn().prepareStatement(sqlRet);
        prSt1.executeUpdate();

    }

    /* private Connection getDbConn() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public void isConn() throws SQLException, ClassNotFoundException {
        dbConn = getDbConn();
        System.out.println(dbConn.isValid(1000));
    }

    public void createTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName
                + " (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), pass VARCHAR(30))"
                + " ENGINE=MYISAM;";

        Statement statement = getDbConn().createStatement();
        statement.executeUpdate(sql);

    }

    public void insertAlertTest(String title, String text, String date, String avtor) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO article (title, text, date, avtor) VALUES (?,?,?,?);";

        PreparedStatement prSt = getDbConn().prepareStatement(sql);
        prSt.setString(1, title);
        prSt.setString(2, text);
        prSt.setString(3, date);
        prSt.setString(4, avtor);

        prSt.executeUpdate();

    }

    public void getArt(String table) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + table;

        Statement statement = getDbConn().createStatement();
        ResultSet res = statement.executeQuery(sql);
        while(res.next()) {
            System.out.println(res.getString("title"));
        }
    } */
}
