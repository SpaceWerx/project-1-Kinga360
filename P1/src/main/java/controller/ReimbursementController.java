package controller;

import java.util.List;

import com.google.gson.Gson;

import DAO.ReimbursementDAO;
import DAO.UserDAO;
import Models.Reimbursement;
import Models.Status;
import Models.Users;
import Service.AuthService;
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
		System.out.println(body);
		Gson gson = new Gson();
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		System.out.println(reimbursement.getDescription());
		rs.submitReimbursement(reimbursement);
		ctx.result("Reimbursement successfully added.");
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
	public Handler Approve  = (ctx) ->{

			String body = ctx.body();
			Gson gson = new Gson();
			Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
			Reimbursement temp = ReimbursementService.getReimbursementByID(reimbursement.getID());
			temp.setStatus(Status.Approved);
			temp.setResolver(reimbursement.getResolver());
			ReimbursementService.update(temp, temp.getResolver(), temp.getStatus());
			String JSONObject = gson.toJson("Reimbursement processed successfully");
			ctx.result(JSONObject);
			ctx.status(237);
	};
	public Handler Denied  = (ctx) ->{

		String body = ctx.body();
		Gson gson = new Gson();
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		Reimbursement temp = ReimbursementService.getReimbursementByID(reimbursement.getID());
		temp.setStatus(Status.Denied);
		temp.setResolver(reimbursement.getResolver());
		ReimbursementService.update(temp, temp.getResolver(), temp.getStatus());
		String JSONObject = gson.toJson("Reimbursement processed successfully");
		ctx.result(JSONObject);
		ctx.status(238);
};
	public Handler getReimbursementByUsername = (ctx) ->{

		String body = ctx.body();
		Gson gson = new Gson();
		Users  user = gson.fromJson(body, Users.class);
		Users temp = AuthService.login(user.getUserName(), user.getPassword());
		int ID = temp.getID();
		List<Reimbursement> r =rDAO.getReimbursementByUser(ID) ;
		String JSONObject = gson.toJson(r);
		ctx.result(JSONObject);
		ctx.status(240);
	};

}
