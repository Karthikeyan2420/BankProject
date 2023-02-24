

import java.sql.*;

public class ServerConnection {
    public static void getsql() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    public static Connection getConnection(){
        Connection con=null;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/javaBank","root","sHj@6378#jw");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void createcustomer( BankCustomers c) throws SQLException {
        Connection con=getConnection();
        Statement sta=con.createStatement();
        sta.executeUpdate("insert into Customer(CustomerId,customerName,phone,email,aadhar,dob) values ("+c.getcustomerId()+",'"+c.getcustomerName()+"','"+c.getphoneNo()+"','"+c.getemail()+"','"+c.getaadhar()+"','"+c.getdateOfBirth()+"')");
        //sta.executeUpdate("insert into Address(customerId,paddress,raddress) values ("+c.getcustomerId()+",'"+c.getpaddr()+"','"+c.getradd()+"')");
        System.out.println("Successfully Inserted \n Values "+c.getcustomerId()+",'"+c.getcustomerName()+"','"+c.getphoneNo()+"','"+c.getemail()+"','"+c.getaadhar()+"','"+c.getdateOfBirth());
    }
    public static void createPermanentAddress( BankCustomers c) throws SQLException {
        Connection con=getConnection();
        Statement sta=con.createStatement();
        sta.executeUpdate("insert into PermanentAddress(customerId,DoorNo,Area,City,Pincode) values ("+c.getcustomerId()+",'"+c.getDoorNo()+"','"+c.getArea()+"','"+c.getCity()+"','"+c.getPincode()+"')");
        //System.out.println("Successfully Inserted \n Values "+c.getcustomerId()+",'"+c.getpermanentAddressr()+"','"+c.getcurrentAddress());
    }
    public static void createCurrentAddress( BankCustomers c) throws SQLException {
        Connection con=getConnection();
        Statement sta=con.createStatement();
        sta.executeUpdate("insert into CurrentAddress(customerId,DoorNo,Area,City,Pincode) values ("+c.getcustomerId()+",'"+c.getDoorNo1()+"','"+c.getArea1()+"','"+c.getCity1()+"','"+c.getPincode1()+"')");
        //System.out.println("Successfully Inserted \n Values "+c.getcustomerId()+",'"+c.getpermanentAddressr()+"','"+c.getcurrentAddress());
    }
    public static void createAcc( BankCustomers c) throws SQLException {
        Connection con=getConnection();
        Statement sta=con.createStatement();
        sta.executeUpdate("insert into Account(customerId,AccountNo,OpenDate,AccountType,Balance) values ("+c.getcustomerId()+",'"+c.getaccountNo()+"','"+c.getopendate()+"','"+c.getaccountType()+"','"+c.getbalance()+"')");
        //sta.executeUpdate("insert into Address(customerId,accno,opendate,accountType,balance) values ("+c.getcustomerId()+",'"+c.getpaddr()+"','"+c.getradd()+"')");
        System.out.println("Successfully Inserted \n Values "+c.getcustomerId()+",'"+c.getaccountNo()+"','"+c.getopendate()+"','"+c.getaccountType()+"','"+c.getbalance());
    }

   /* public static void createBank( BankCustomers c) throws SQLException {
        Connection con=getConnection();
        Statement sta=con.createStatement();
        //sta.executeUpdate("insert into Customer(customerId,customerName,phone,email,aadhar,dob) values ("+c.getcustomerId()+",'"+c.getcustomerName()+"','"+c.getphno()+"','"+c.getemail()+"','"+c.getaadhar()+"','"+c.getdob()+"')");
        sta.executeUpdate("insert into bank1(n,l) values ('"+c.getn()+"','"+c.getf()+"')");
        System.out.println("Successfully Inserted \n Values "+c.getn()+",'"+c.getf());
    }*/

    public static void printcustomerdata(){
        Connection c=getConnection();
        try {
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer");

           /* System.out.println("***************************************************************CUSTOMER DETAILS*****************************************");
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("customerId") + "|  NAME: " + resultSet.getString("customerName")+ "|  Phone No: " + resultSet.getString("phone"));
                System.out.print("| Email: " + resultSet.getString("email") + "|  AAdhar : " + resultSet.getString("aadhar")+ "|  Date Of Birth: " + resultSet.getString("dob"));
                System.out.println();

            }
            System.out.println("\n**********************************************************************END*************************************************");*/

            resultSet.close();
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void printAddressdata(){
        Connection c=getConnection();
        try {
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Address");
            System.out.println("***************************************************************ADDRESS DETAILS*****************************************");
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("customerId") + "|  Permanent Address : " + resultSet.getString("paddress")+ "|  Residential Address: " + resultSet.getString("raddress"));
                //System.out.print("| Email: " + resultSet.getString("email") + "|  AAdhar : " + resultSet.getString("aadhar")+ "|  Date Of Birth: " + resultSet.getString("dob"));
                System.out.println();

            }
            System.out.println("\n**********************************************************************END*************************************************");
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printaccountdata(){
        Connection c=getConnection();
        try {
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Account");
            System.out.println("***************************************************************ACCOUNT DETAILS*****************************************");
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("customerId") + "|  Account No : " + resultSet.getString("accno")+ "|  Open Date: " + resultSet.getString("opendate"));
                System.out.print("| Account Type: " + resultSet.getString("accountType") + "|  Balance : " + resultSet.getString("balance"));
                System.out.println();

            }
            System.out.println("\n**********************************************************************END*************************************************");
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printalldata(){
        Connection c=getConnection();
        try {
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer , address,account  where customer.id=address.id && customer.id=account.id");
            System.out.println("***************************************************************BANK CUSTOMER DETAILS*****************************************");
            while (resultSet.next()) {
                System.out.println(" Customer ID: " + resultSet.getInt("cid"));
                System.out.println("\tPersonal Details :");
                System.out.print( "\t\tNAME: " + resultSet.getString("cname")+ "|  Phone No: " + resultSet.getString("phone"));
                System.out.println("| Email: " + resultSet.getString("email") + "|  AAdhar : " + resultSet.getString("aadhar")+ "|  Date Of Birth: " + resultSet.getString("dob"));
                System.out.println("\tAddress Details :");
                System.out.println( "\t\tPermanent Address : " + resultSet.getString("paddress")+ "|  Residential Address: " + resultSet.getString("raddress"));
                System.out.println("\tAccount Details : ");
                System.out.print( "\t\tAccount No : " + resultSet.getString("accno")+ "|  Open Date: " + resultSet.getString("opendate"));
                System.out.print("| Account Type: " + resultSet.getString("acctype") + "|  Balance : " + resultSet.getString("balance"));
                System.out.println();

            }
            System.out.println("\n**********************************************************************END*************************************************");
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteAccount(BankCustomers b){
        try{
            Connection con=getConnection();
            Statement statement = con.createStatement();
            statement.execute("delete from customer where id="+b.getcustomerId());
            statement.execute("delete from address where id="+b.getcustomerId());
            statement.execute("delete from account where id="+b.getcustomerId());
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
