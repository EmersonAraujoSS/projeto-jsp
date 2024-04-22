package filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/pages/*"}) /*Intercepta todas as requisiçoes que vierem do projeto ou mapeamento */
public class FilterAutenticacao implements Filter {

    public FilterAutenticacao() {  /*construtor padrão */
    }

    public void destroy() {
        /*----------------Encerra os processos quando o servidor é parado----------------*/
        /*----------------Mataria os processos de conexão com o banco----------------*/
    }


    /*----------------Inicia os processos ou recursos quando o servidor sobe o projeto----------------*/
    /*----------------inicia a conexao com o banco----------------*/
    /*----------------Intercepta as requisiçoes e as respostas no sistema----------------*/
    /*----------------Tudo o que a gente fizer no sistema vai passar por ele----------------*/
    /*----------------Validação de autenticaçao----------------*/
    /*----------------Dar commit e rolback de transaçoes do banco----------------*/
    /*----------------Validar e fazer redirecionamento de paginas----------------*/
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        String usuarioLogado = (String) session.getAttribute("usuario");
        String urlParaAuenticacao = req.getServletPath();  /*URL que está sendo acessada*/

        /*----------------Validar se esta logado, se não redireciona para tela de login----------------*/
        if (usuarioLogado == null  &&
        !urlParaAuenticacao.equalsIgnoreCase("/pages/ServletLogin")) {

            RequestDispatcher redirecionar = req.getRequestDispatcher("/index.jsp?url=" + urlParaAuenticacao);
            request.setAttribute("msg", "Por favor, realize o login!");
            redirecionar.forward(request, response);
            return;  /*Para a execucao e redireciona para o login*/

        }else {
            chain.doFilter(request, response);
        }

    }
    /*teste reposritorio github*/

    public void init(FilterConfig fConfig) throws ServletException {

    }
}
