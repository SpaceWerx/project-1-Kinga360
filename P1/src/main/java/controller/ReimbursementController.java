package controller;

import java.util.List;

import com.google.gson.Gson;

import DAO.ReimbursementDAO;
import DAO.UserDAO;
import Models.Reimbursement;
import Models.Users;
import Service.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementController {
	static ReimbursementService rs = new ReimbursementService();
	ReimbursementDAO rDAO = new ReimbursementDAO();
	public Handler getReimbursementHandler = (ctx) ->{
		List<Reimbursement>users = ReimbursementService.getALLReimbursement();
		Gson gson = new Gson();
		String JSONObject = gson.toJson(users);
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

}
