package controller;

import java.util.List;

import com.google.gson.Gson;

import DAO.ReimbursementDAO;
import DAO.UserDAO;
import Models.Reimbursement;
import Models.Status;
import Models.Users;
import Service.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementController {
	static ReimbursementService rs = new ReimbursementService();
	ReimbursementDAO rDAO = new ReimbursementDAO();
	int id;
	public Handler getReimbursementHandler = (ctx) ->{
		List<Reimbursement>reimbursement = ReimbursementService.getALLReimbursement();
		Gson gson = new Gson();
		String JSONObject = gson.toJson(reimbursement);
		ctx.result(JSONObject);
		ctx.status(202);
	};
	public Handler insertReimbursementHandler = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		rDAO.create(reimbursement);
		ctx.result("Employee successfully added.");
		ctx.status(203);
	};
	public Handler getByID = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		int id = gson.fromJson(body, Integer.class);
		Reimbursement r = ReimbursementService.getReimbursementByID(id);
		String JSONObject = gson.toJson(r);
		ctx.result(JSONObject);
		ctx.status(205);
	};
	public Handler getByStatus  = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		List<Reimbursement> rStatus ;
		String status = gson.fromJson(body, String.class);
		if (status.equals("Pending")){
		rStatus	= ReimbursementService.getPendingReimbursement();
		}
		else {
			rStatus	= ReimbursementService.getResolvedReimbursement();	
		}
		String JSONObject = gson.toJson(rStatus);
		ctx.result(JSONObject);
		ctx.status(206);
	};
	public Handler getByAuthor  = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		int ID = gson.fromJson(body, Integer.class);
		List<Reimbursement> rStatus = rDAO.getReimbursementByUser(ID) ;
		String JSONObject = gson.toJson(rStatus);
		ctx.result(JSONObject);
		ctx.status(207);
	};
	public Handler Process  = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		int resolverid  = gson.fromJson(body, Integer.class);
		List<Reimbursement>reimbursement = ReimbursementService.getALLReimbursement();
		String JSONObject = gson.toJson(reimbursement + "Which one would you like to process?");
		ctx.result(JSONObject);
		int id = gson.fromJson(body, Integer.class);
		for (Reimbursement r : reimbursement) {
			if (id == r.getID()) {
				ctx.result("What status do you choose?");
				String status = gson.fromJson(body, String.class);
				if (status.equals("Pending")) {
					ReimbursementService.update(r, id, Status.Pending);
					ctx.result(JSONObject);
				}
				
			}
		}
		//int id = gson.fromJson(body, Integer.class);
		
		
		
		ctx.status(208);
	};
	
}
