package repositories;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Status;
import Models.reimbursementType;
import utilities.ConnectionFactoryUtility;

public class ReimbursementDAO {
	public void update(Reimbursement unprocessedReimbursement) 
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			String sql = "UPDATE ers_reimbursements SET resolver = ?, status = ?::status WHERE id = ?";
			
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			
			prepStatement.setInt(1, unprocessedReimbursement.getResolver());
			prepStatement.setObject(2, unprocessedReimbursement.getStatus().name());
			prepStatement.setInt(3, unprocessedReimbursement.getId());
			
			prepStatement.executeUpdate();
			
			System.out.println("Reimbursement Sucessfully Updated! ");
			
			
		}
		catch(SQLException e) 
		{
			System.out.println("Update failed!");
			e.printStackTrace();
		}
	}
	public List<Reimbursement> getReimbursementsByUser(int userID)
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			String sql = "SELECT * FROM ers_reimbursements WHERE author = ?";
			
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			
			prepStatement.setInt(1, userID);
			
			ResultSet rs = prepStatement.executeQuery();
			
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			
			while(rs.next()) 
			{
				reimbursements.add(new Reimbursement(
						rs.getInt("id"),
						rs.getInt("author"),
						rs.getInt("resolver"),
						rs.getString("description"),
						reimbursementType.valueOf(rs.getString("type")),
						Status.valueOf(rs.getString("status")),
						rs.getDouble("amount")
						));
			}
			return reimbursements;
		}
		catch(SQLException e) 
		{
			System.out.println("Something went wrong obtaining the list");
			e.printStackTrace();
		}
		return null;
	}
	
	public Reimbursement getReimbursementById(int selection) 
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			String sql = "SELECT * FROM ers_reimbursements WHERE id = ?";
			
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			
			prepStatement.setInt(1, selection);
			
			ResultSet rs = prepStatement.executeQuery();
			
			if(rs.next()) 
			{
				return new Reimbursement(
						rs.getInt("id"),
						rs.getInt("author"),
						rs.getInt("resolver"),
						rs.getString("description"),
						reimbursementType.valueOf(rs.getString("type")),
						Status.valueOf(rs.getString("status")),
						rs.getDouble("amount")
						);
			}
		}
		catch(SQLException e) 
		{
			System.out.println("Something went wrong with the database");
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Reimbursement> getByStatus(Status status)
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			String sql = "SELECT * FROM ers_reimbursements WHERE status = ?";
			
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			
			prepStatement.setString(1, status.toString());
			
			ResultSet rs = prepStatement.executeQuery();
			
			List<Reimbursement> reimbursements = new ArrayList<>();
			
			while(rs.next()) 
			{
				reimbursements.add(new Reimbursement(
						rs.getInt("id"),
						rs.getInt("author"),
						rs.getInt("resolver"),
						rs.getString("description"),
						reimbursementType.valueOf(rs.getString("type")),
						Status.valueOf(rs.getString("status")),
						rs.getDouble("amount")
						));
			}
			return reimbursements;
		}
		catch(SQLException e) 
		{
			System.out.println("Something went wrong obtaining the reimbursements");
			e.printStackTrace();
		}
		return null;

	}
	
	public List<Reimbursement> getAllReimbursements()
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			List<Reimbursement> reimbursements = new ArrayList<>();
			
			String sql = "SELECT * from ers_reimbursements WHERE author = ?";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) 
			{
				reimbursements.add(new Reimbursement(rs.getInt("id"),
						rs.getInt("author"),
						rs.getInt("resolver"),
						rs.getString("description"),
						reimbursementType.valueOf(rs.getString("type")),
						Status.valueOf(rs.getString("status")),
						rs.getDouble("amount")
						));
			}
			return reimbursements;
			
		}
		catch(SQLException e) 
		{
			System.out.println("Something went wrong with the database");
			e.printStackTrace();
		}
		return null;

	}
	
	public int create(Reimbursement reimbursementToBeSubmitted) 
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			String sql = "INSERT INTO ers_reimbursements(author,description,type,status,amount)"
			+ "VALUES (?,?,?::type,?::status,?)"
			+ "RETURNING ers_reimbursements.id";
			
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			
			prepStatement.setInt(1, reimbursementToBeSubmitted.getAuthor());
			prepStatement.setString(2, reimbursementToBeSubmitted.getDescription());
			prepStatement.setObject(3, reimbursementToBeSubmitted.getType().name());
			prepStatement.setObject(4, reimbursementToBeSubmitted.getStatus().name());
			prepStatement.setDouble(5, reimbursementToBeSubmitted.getAmount());
			
			ResultSet rs;
			if((rs = prepStatement.executeQuery()) != null) 
			{
				rs.next();
				
				return rs.getInt(1);
			}
			
			
		}
		catch(SQLException e) 
		{
			System.out.println("Creating reimbursement has failed");
			e.printStackTrace();
		}
		return 0;
	}
	

}
