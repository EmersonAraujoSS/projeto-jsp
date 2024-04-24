package dao;

import connection.SingleConnectionBanco;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryDao {

    private Connection connection;   /*primeiro eu tenho que criar um objeto*/

    public LoginRepositoryDao(){                      /*depois tenho que criar o meu construtor,
        this.connection = SingleConnectionBanco.getConnection();           pra sempre que eu iniciar o meu objeto LoginRepositoryDao ele já ter essa conexao pronta*/
    }

    public boolean validarLogin(ModelLogin modelLogin) throws Exception {

        /*----------PRIMEIRO TENHO QUE CRIAR MEU SQL DE CONSULTA----------*/

        String sql = "select * from model_login_jsp where login = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, modelLogin.getLogin());
        statement.setString(2, modelLogin.getSenha());


        /*----------DEPOIS EU PRECISO CRIAR UM OBJETO DE RESULTADO----------*/

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return true;  /*autenticado*/
        }else{
            return false;  /*não autenticado*/
        }

    }
}
