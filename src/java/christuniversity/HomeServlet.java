package christuniversity;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the content type and get the PrintWriter
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();

        // Write HTML content to the response
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Welcome to the Home Page</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Welcome to the Home Page!</h1>");
        out.println("<p>Hello! " + request.getParameter("username") + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
