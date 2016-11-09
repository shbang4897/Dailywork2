package com.cjon.bank.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.cjon.bank.dao.BankDAO;
import com.cjon.bank.dto.BankDTO;
import com.cjon.bank.util.DBTemplate;

public class BankService {

	public BankDTO deposit(BankDTO dto) {

		
		String config = "classpath:applicationCtx.xml" ;
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		
		ctx.load(config);
		ctx.refresh();
		
		
		DBTemplate template = ctx.getBean("template",DBTemplate.class);
		BankDAO dao =  ctx.getBean("dao",BankDAO.class);
		dto  = dao.update(dto);
		
		if(dto.isResult()){
			template.commit();
			
		}else {
			template.rollback();
		}

		try {
			template.getCon().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx.close();

		return dto;
	}

	public BankDTO withdraw(BankDTO dto) {

		String config = "classpath:applicationCtx.xml" ;
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		
		ctx.load(config);
		ctx.refresh();
		
		DBTemplate template = ctx.getBean("template",DBTemplate.class);
		BankDAO dao =ctx.getBean("dao",BankDAO.class);
		dto  = dao.withdraw(dto);

		if(dto.isResult()){
			template.commit();
			
		}else {
			template.rollback();
		}
		try {
			template.getCon().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx.close();

		return dto;
	}

	public ArrayList<BankDTO> transfer(BankDTO dto1, BankDTO dto2) {

		String config = "classpath:applicationCtx.xml" ;
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		
		ctx.load(config);
		ctx.refresh();
		
		DBTemplate template = ctx.getBean("template",DBTemplate.class);
		BankDAO dao = ctx.getBean("dao",BankDAO.class);
		dto1  = dao.withdraw(dto1);
		dto2 = dao.update(dto2);
		
		if(dto1.isResult()&&dto2.isResult()){
			template.commit();
			
		}else {
			template.rollback();
		}
		
		ArrayList<BankDTO> list = new ArrayList<BankDTO>();
		list.add(dto1);
		list.add(dto2);
		

		try {
			template.getCon().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ctx.close();

		return list;
	}

}
