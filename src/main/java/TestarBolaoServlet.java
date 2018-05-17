import br.ufscar.dc.bolao.beans.Usuario;
import br.ufscar.dc.bolao.beans.dao.UsuarioDAO;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@javax.servlet.annotation.WebServlet(urlPatterns = {"/TestarBolaoServlet"})
public class TestarBolaoServlet extends javax.servlet.http.HttpServlet {

    @Resource(name = "jdbc/BolaoDBLocal")
    DataSource dataSource;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println(dataSource.toString());
        Usuario u = new Usuario();
        u.setNome("Felipe");
        u.setEmail("felipe.pa@hotmail.com");
        u.setTelefone("9999999");
        u.setDataDeNascimento(new Date());

        UsuarioDAO udao = new UsuarioDAO(dataSource);
        Usuario uGravado = null;

        try{
            uGravado = udao.gravarUsuario(u);
            System.out.println(uGravado.getId());
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (NamingException ex){
            ex.printStackTrace();
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }
}
