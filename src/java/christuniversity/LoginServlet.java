package christuniversity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    // JDBC connection parameters (update these with your MySQL configuration)
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/geetha";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


        // Retrieve user input from the login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // JDBC variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
               
        try {
            // Establish a connection to the MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            
            // Query to check if the username and password exist in the database
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            
            
            
            // Check if a matching user was found
            if (resultSet.next()) {
                // Successful login, redirect to the home servlet
                response.sendRedirect(request.getContextPath() + "/HomeServlet?username="+username);
            } else {
                response.setContentType("text/html");

                // Get the PrintWriter
                PrintWriter out = response.getWriter();

                // Display the error message
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Invalid Login</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Invalid Login</h1>");
                out.println("<p>Your username or password is incorrect. Please try again.</p>");
                out.println("</body>");
                out.println("</html>");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception as needed
        } finally {
            // Close resources in the finally block to ensure they are released
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
