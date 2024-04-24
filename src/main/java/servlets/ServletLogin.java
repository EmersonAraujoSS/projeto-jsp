package servlets;


import dao.LoginRepositoryDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

/*Os chamados Controller são as servlets ou ServletLoginController*/
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})  /*Mapeamento de URL que vem da tela*/
public class ServletLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LoginRepositoryDao loginRepositoryDao = new LoginRepositoryDao();

    public ServletLogin() {

    }

    /*Recebe os dados da URL em parametros*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    }

    /*Recebe os dados enviados por um formulario*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");  /*REQUEST = requisição, pedir informação informação*/     /*RESPONSE = resposta do servidor na tela*/
        String senha = request.getParameter("senha");
        String url = request.getParameter("url");

        try {

                if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()){  /* isEmpty = SIGNIFICA DIFERENTE DE VAZIO*/

                    ModelLogin modelLogin = new ModelLogin();
                    modelLogin.setLogin(login);
                    modelLogin.setSenha(senha);

                    if (loginRepositoryDao.validarLogin(modelLogin)){   /*Simulando login*/

                        request.getSession().setAttribute("usuario", modelLogin.getLogin()); /*manter esse usuário logado no sistema*/

                        if (url == null || url.equals("null")){
                            url = "pages/principal.jsp";
                        }

                        RequestDispatcher redirecionar = request.getRequestDispatcher("pages/principal.jsp"); /*redirecionar para uma página*/
                        redirecionar.forward(request, response);

                    }else {
                        RequestDispatcher redirecionar = request.getRequestDispatcher(url);
                        request.setAttribute("msg", "Login ou senha incorretos");
                        redirecionar.forward(request, response);
                    }

                }else {
                    RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
                    request.setAttribute("msg", "Login ou senha incorretos");
                    redirecionar.forward(request, response);  // Encaminhar para a página JSP
                }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}



