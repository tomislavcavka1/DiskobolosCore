package hr.diskobolos.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main class for handling errors.
 *
 * @author Tomislav Cavka
 */
public class ErrorHandlerUtils {

    private static final Logger log = Logger.getLogger(ErrorHandlerUtils.class);

    public static String handleAjaxError(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        JSONObject json = new JSONObject();
        log.error("HttpServletResponse error code: " + response.getStatus());

        json.put("errorCode", response.getStatus());

        String time = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy.").format(Calendar.getInstance().getTime());
        log.error("Error occured at time: " + time);
        json.put("errorTime", "Time: " + time + "<br/>");

        return json.toString();
    }

    public static void loginError(HttpServletRequest request, HttpServletResponse response, Map<Integer, String> errorMessageMap) {
        String errorMessage = errorMessageMap.keySet().stream().findFirst().get().toString();
        response.addHeader("errorMessage", errorMessage);
        response.addIntHeader("errorCode", HttpServletResponse.SC_UNAUTHORIZED);
    }
}
