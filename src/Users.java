import java.sql.*;

public class Users {
    String userId;
    String userName;
    String emailId;
    String phone;
    String password;


    public boolean checkUser(String userName, String password) throws SQLException, ClassNotFoundException {
        boolean flag = false;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeboxFinal", "root", "Adityaa8@#");
                PreparedStatement ppdsmt = conn.prepareStatement("select username,password from users where username = '"+ userName + "' and password = '" + password + "'");
                ResultSet resultSet = ppdsmt.executeQuery();
                while (resultSet.next()) {
                   flag =true;
                }
            }  catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return flag;
    }
}
