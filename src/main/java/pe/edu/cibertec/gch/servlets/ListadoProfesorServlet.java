package pe.edu.cibertec.gch.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.edu.cibertec.gch.enums.ServiceTypes;
import pe.edu.cibertec.gch.factory.ProfesorFactory;
import pe.edu.cibertec.gch.modelo.Profesor;
import pe.edu.cibertec.gch.service.ProfesorService;

/**
 * Servlet para listar profesores
 */
@WebServlet(name = "ListadoProfesorServlet", urlPatterns = {"/listarProfesores"})
public class ListadoProfesorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        
        String
                nombres = req.getParameter("nombres"),
                apellidoPaterno = req.getParameter("apellidoPaterno"),
                apellidoMaterno = req.getParameter("apellidoMaterno");       
       
        // trae los profesores en la fuente de datos    
        ProfesorService serviceProfesorBD = ProfesorFactory.create(ServiceTypes.SERVERBD);
        List<Profesor> profesores = serviceProfesorBD.listarSegun(nombres, apellidoPaterno, apellidoMaterno);        
        // almacena resultado en el request
        req.setAttribute("profesores", profesores);
        // pinta los datos en el listado
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/profesor/index.jsp");
        requestDispatcher.forward(req, resp);
    }

}
