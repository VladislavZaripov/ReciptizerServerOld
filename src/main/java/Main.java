import SQL.MySQLHelper;
import Server.Server;

public class Main {

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.runServer();

        MySQLHelper mySQLHelper = new MySQLHelper();
        mySQLHelper.connectToMySQL();
        mySQLHelper.createTablesInMySQL();
    }
}