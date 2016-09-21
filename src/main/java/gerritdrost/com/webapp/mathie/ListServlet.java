package gerritdrost.com.webapp.mathie;

import org.jtwig.web.servlet.JtwigRenderer;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private final JtwigRenderer renderer = JtwigRenderer.defaultRenderer();

    @Inject
    protected MathieSession mathieSession;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Calculate stuff
        List<MathieSession.QueryOutcomePair> outcomePairs = mathieSession.getQueryOutcomes();

        // Convert to JSON
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (MathieSession.QueryOutcomePair outcomePair : outcomePairs) {
            arrayBuilder.add(outcomePair.toJsonBuilder());
        }

        JsonArray array = arrayBuilder.build();

        response.setContentType("application/json");
        Json.createWriter(response.getOutputStream()).writeArray(array);
    }

}
