package gerritdrost.com.webapp.mathie;

import gerritdrost.com.libs.mathie.ExpressionEnvironment;
import gerritdrost.com.libs.mathie.defaults.config.DefaultConfiguration;
import gerritdrost.com.libs.mathie.expression.Expression;
import lombok.Getter;
import lombok.Value;

import javax.enterprise.context.SessionScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class MathieSession implements Serializable {

    private List<QueryOutcomePair> queryOutcomes = new ArrayList<>();

    public List<QueryOutcomePair> getQueryOutcomes() {
        return new ArrayList<>(queryOutcomes);
    }

    private ExpressionEnvironment mathieEnv = new ExpressionEnvironment(new DefaultConfiguration());

    public QueryOutcomePair calculate(String query) {
        Expression expression = mathieEnv.getExpression(query);
        QueryOutcomePair queryOutcomePair = new QueryOutcomePair(query, expression.getValue());
        queryOutcomes.add(queryOutcomePair);
        return queryOutcomePair;
    }

    @Value
    public static class QueryOutcomePair {
        private String query;
        private double outcome;

        public JsonObjectBuilder toJsonBuilder() {
            return Json.createObjectBuilder().add("query", query).add("outcome", outcome);
        }

        public JsonObject toJson() {
            return toJsonBuilder().build();
        }
    }
}
