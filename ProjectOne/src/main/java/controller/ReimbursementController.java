package controller;

import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import Models.Status;
import Models.Reimbursement;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import repositories.ReimbursementDAO;
import services.reimbursementService;
import services.userService;


public class ReimbursementController {
	ObjectMapper objectMapper = new ObjectMapper();
	ReimbursementDAO rDAO = new ReimbursementDAO();
	
	public void handleSubmit(Context ctx) 
	{
		try 
		{
			String input = ctx.body();
			
			Reimbursement reimbursement = objectMapper.readValue(input, Reimbursement.class);
			
			int id = reimbursementService.submitReimbursement(reimbursement);
			
			if(id != 0) 
			{
				ctx.status(HttpCode.CREATED);
				ctx.result(""+id);
			}
			else 
			{
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Reimbursement submission was unsuccessful.");
			}
			
		}
		catch(Exception e) 
		{
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			if(!e.getMessage().isEmpty()) 
			{
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}
	}
	
	public void handleProcess(Context ctx) 
	{
		String authHeader = ctx.header("Current User");
		
		if(authHeader != null) 
		{
			int userId = Integer.parseInt(authHeader);
			
			try 
			{
				String reimbursementIdInput = ctx.pathParam("id");
				
				int id = Integer.parseInt(reimbursementIdInput);
				
				String statusInput = ctx.formParam("status");
				
				Reimbursement reimbursement = reimbursementService.getReimbursementById(id);
				
				if(reimbursement != null) 
				{
					Reimbursement processedReimbursement = reimbursementService.update(reimbursement, userId, Status.valueOf(statusInput));
					
					ctx.status(HttpCode.ACCEPTED);
					ctx.json(processedReimbursement);
				}
				else 
				{
					ctx.status(HttpCode.BAD_REQUEST);
					ctx.result("Reimbursement processing was not successful");
				}
			}
			catch(Exception e)
			{
				ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
				
				if(!e.getMessage().isEmpty()) 
				{
					ctx.result(e.getMessage());
				}
				e.printStackTrace();
			}
		}
		else 
		{
			ctx.status(HttpCode.FORBIDDEN);
			ctx.result("Missing Current User Header with ID");
		}
	}
	
	
	public Handler handleGetReimbursements =(ctx) -> 
	{
		List<Reimbursement> allReim = rDAO.getAllReimbursements();
		
		Gson gson = new Gson();
		String JSONObject = gson.toJson(allReim);
		
		ctx.result(JSONObject);
		ctx.status(200);

//		if(ctx.queryParam("author") != null) 
//		{
//			handleGetReimbursementsByAuthor(ctx);
//		}
//		else if(ctx.queryParam("status") != null) 
//		{
//			handleGetReimbursementByStatus(ctx);
//		}
	};
	
	public Handler handleGetReimbursementByStatus =(ctx) -> 
	{
			String statusParam = ctx.body();
			
			Models.Status status = Models.Status.valueOf(statusParam);
			List<Reimbursement> reim = reimbursementService.getReimbursementByStatus(status);
			Gson gson = new Gson();
			String json = gson.toJson(reim);
			
			if(reim != null) 
			{
				ctx.result(json);
				ctx.status(HttpCode.OK);
//				ctx.json(reimbursementService.getPendingReimbursement());
			}
			else 
			{
				ctx.status(HttpCode.OK);
				ctx.result(json);
			}
		//}
//		catch(Exception e)
//		{
//			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
//			
//			if(!e.getMessage().isEmpty()) 
//			{
//				ctx.result(e.getMessage());
//			}
//			e.printStackTrace();
//		}
	};
	
	public void handleGetReimbursementById(Context ctx) 
	{
		try 
		{
			String idParam = ctx.pathParam("id");
			
			int id = Integer.parseInt(idParam);
			
			Reimbursement reimbursement = reimbursementService.getReimbursementById(id);
			
			if(reimbursement != null) 
			{
				ctx.status(HttpCode.OK);
				ctx.json(reimbursement);
			}
			else 
			{
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Could not retrieve the reimbursement");
			}
		}
		catch(Exception e)
		{
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			if(!e.getMessage().isEmpty()) 
			{
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}
	}
	
	public void handleGetReimbursementsByAuthor(Context ctx) 
	{
		try 
		{
			String idParam = ctx.queryParam("author");
			
			if(idParam != null) 
			{
				int id = Integer.parseInt(idParam);
				
				if(userService.checkUserExistsById(id)) 
				{
					ctx.status(HttpCode.OK);
					
					ctx.json(reimbursementService.getReimbursementByAuthor(id));
				}
				else 
				{
					ctx.status(HttpCode.NOT_FOUND);
					ctx.result("Unable to retrieve reimbursements, curren tuser is not in the database");
				}
			}
			else 
			{
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Missing Current user header");
			}
		}
		catch(Exception e) 
		{
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			if(!e.getMessage().isEmpty()) 
			{
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}
	}
	

}
