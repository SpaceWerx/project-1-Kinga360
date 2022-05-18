package Service;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import Utilities.ConnectionFactoryUtility;
public class RoleDAO {
	
	

	

		public void updateSalary(String roleTitle, int newSalary) throws SQLException {
			try(Connection conn = ConnectionFactoryUtility.getConnection()){
				//This is the start of a prepared statement
				String sql = "update roles set role_salary = ? where role_title = ?;";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(1, newSalary);
				ps.setString(2, roleTitle);
				
				ps.executeUpdate();
				
				System.out.println(roleTitle + " salary has been updated to: " + newSalary);
			}
			catch (SQLException e) {
				System.out.println("An error occured while updating the salary");
				e.printStackTrace(); //What I usually do, is I will only leave these in, while I am testing and building the code, and once everything works and I know it works, I remove them
			}
		}

	
}
