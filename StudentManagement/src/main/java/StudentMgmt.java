import java.sql.*;
import java.time.LocalDate;

public class StudentMgmt {
    private int portNumber;

    public StudentMgmt(int port) {
        portNumber = port;
    }

    /**
     * Connect to Postgresql with specific url, user and password.
     * @return A Connection object that represent the connection between JDBC drive and the database.
     * @throws SQLException for any database or drive related errors.
     */
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        String url = "jdbc:postgresql://localhost:" + portNumber + "/StudentManagement";
        String user = "postgres";
        String password = "M99996";

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Retrives all student records from the students table and print them.
     */
    private void getAllStudents() {
        String query = "SELECT * FROM students";

        try(Connection connect = getConnection();
            Statement st = connect.createStatement();
            ResultSet res = st.executeQuery(query);) {

            while (res.next()) {
                System.out.println(res.getInt("student_id") + ", " +
                        res.getString("first_name") + " " +
                        res.getString("last_name") + ", " +
                        res.getString("email") + ", " +
                        res.getDate("enrollment_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a student with specified information.
     * @param fn represents the student's first name.
     * @param ln represents the student's last name.
     * @param email represents the student's email address.
     * @param date represents the student's enrollment date.
     */
    public void addStudent(String fn, String ln, String email, String date) {
        String query = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";

        try(Connection connect = getConnection();
            PreparedStatement ps = connect.prepareStatement(query)) {

            ps.setString(1, fn);
            ps.setString(2, ln);
            ps.setString(3, email);
            ps.setDate(4, Date.valueOf(date));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uodate the email address of the student with specific student_id.
     * @param id represents the student's id.
     * @param new_email represents the new email that to replace the student's old email address.
     *
     * After executing the query, the method will print a notification to indicate whether the student's email
     * is successfully updated, or the student id was not found.
     */
    public void updateStudentEmail(int id, String new_email) {
        String query = "UPDATE students SET email = ? WHERE student_id = ?";

        try(Connection connect = getConnection();
            PreparedStatement ps = connect.prepareStatement(query)) {

            ps.setString(1, new_email);
            ps.setInt(2, id);
            int found = ps.executeUpdate();

            if(found > 0) {
                System.out.println("Student email with ID " + id + " was successfully updated");
            }
            else {
                System.out.println("Student with ID " + id + " was not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a student record with specific student_id.
     * @param id represent the student's id.
     *
     * After executing the query, the method will print a notification to indicate whether the student is deleted
     * successfully, or the student id was not found.
     */
    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE student_id = ?";

        try(Connection connect = getConnection();
            PreparedStatement ps = connect.prepareStatement(query)) {

            ps.setInt(1, id);
            int found = ps.executeUpdate();

            if(found > 0) {
                System.out.println("Student with ID " + id + " was successfully deleted");
            }
            else {
                System.out.println("Student with ID " + id + " was not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentMgmt sm = new StudentMgmt(3005);
        //sm.getAllStudents();
        //sm.addStudent("Mike", "Fang", "mike.fang@example.com", "2024-03-14");
        //sm.updateStudentEmail(1, "updated@comp3005.com");
        //sm.deleteStudent(10);
    }
}
