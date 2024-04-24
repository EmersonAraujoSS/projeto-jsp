package connection;

import jakarta.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.DriverManager;


public class SingleConnectionBanco {

    private static String banco = "jdbc:sqlserver://FNXAMDB01;databaseName=DB_TESTE_DEV?autoReconnect=true;encrypt=true;trustServerCertificate=false";
    private static String username = "dev_teste";
    private static String senha = "dev_teste";
    private static Connection connection = null;


    public static Connection getConnection() {
        return connection;
    }

    static {
        conectar();
    }

    public SingleConnectionBanco(){   /*quando tiver uma instancia vai conectar*/
        conectar();
    }

    private static void conectar(){

        try {

            if(connection == null){
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(banco, username, senha);
                connection.setAutoCommit(false);   /*para não efetuar alteraçoes no banco sem nosso comando*/
            }


        }catch (Exception e) {
            e.printStackTrace();  /*Mostrar qualquer erro no momento de conectar*/
        }
    }
}
