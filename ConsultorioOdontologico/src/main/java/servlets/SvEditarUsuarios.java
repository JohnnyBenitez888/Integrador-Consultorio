package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;

/**
 *
 * @author Johnny
 */
@WebServlet(name = "SvEditarUsuarios", urlPatterns = {"/SvEditarUsuarios"})
public class SvEditarUsuarios extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Usuario user = control.traerUsuario(id);

        HttpSession miSesion = request.getSession();//Traemos la session del usuario
        miSesion.setAttribute("user", user);
        response.sendRedirect("editarUser.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String name = request.getParameter("user");
        String contra = request.getParameter("contrasenia");
        String rol = user.getRol();
        if (request.getParameter("Rol") != null) {
            rol = request.getParameter("Rol");
        }
        user.setNombre_user(name);
        user.setContrasenia(contra);
        user.setRol(rol);
        control.editarUsuario(user);
        response.sendRedirect("SvUsuario");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
