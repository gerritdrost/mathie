package gerritdrost.com.webapp.mathie;

import org.jtwig.web.servlet.JtwigRenderer;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {
    private final JtwigRenderer renderer = JtwigRenderer.defaultRenderer();

    @Inject
    protected MathieSession mathieSession;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");

        response.setContentType("application/json");
        Json.createWriter(response.getOutputStream()).writeObject(mathieSession.calculate(query).toJson());
    }

}
