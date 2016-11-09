package com.cjon.bank;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.cjon.bank.dto.BankDTO;
import com.cjon.bank.service.BankService;

public class Main {

	public static void main(String[] args) {
		
		
		
		
		String config = "classpath:applicationCtx.xml" ;
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		
		ctx.load(config);
		ctx.refresh();
		
		Scanner s = new Scanner(System.in);
		int menu = 0;

		do {
			System.out.println("---은행시스템---");
			System.out.println("1. 입금");
			System.out.println("2. 출금");
			System.out.println("3. 이체");
			System.out.println("4. 종료");
			System.out.print("사용할 메뉴를 입력하세요==>");
			String menuString = s.nextLine();
			menu = Integer.parseInt(menuString);

			if (menu == 1) {
				
				System.out.println("--입금 업무 --");
				System.out.println("입금할 사람을 입력하시오");
				String id = s.nextLine();
				System.out.println("입금할 금액을 입력하시오");
				String moneyString = s.nextLine();
				int money = Integer.parseInt(moneyString);
				
				BankDTO dto = ctx.getBean("dto1",BankDTO.class);
				dto.setUserid(id);
				dto.setBalance(money);
				
				
				BankService service = ctx.getBean("service",BankService.class);
				dto = service.deposit(dto);
				
				System.out.println("--- 처리 결과 ---");
				System.out.println("--- user id : " + dto.getUserid());
				System.out.println("--- 잔액 : " + dto.getBalance());
				
				
				
				

			}
			if (menu == 2) {
				System.out.println("--출금 업무 --");
				System.out.println("출금할 사람을 입력하시오");
				String id = s.nextLine();
				System.out.println("출금할 금액을 입력하시오");
				String moneyString = s.nextLine();
				int money = Integer.parseInt(moneyString);
				
				BankDTO dto = ctx.getBean("dto1",BankDTO.class);
				dto.setUserid(id);
				dto.setBalance(money);
				
				
				BankService service = ctx.getBean("service",BankService.class);
				dto = service.withdraw(dto);
				
				System.out.println("--- 처리 결과 ---");
				System.out.println("--- user id : " + dto.getUserid());
				System.out.println("--- 잔액 : " + dto.getBalance());
				
				

			}
			if (menu == 3) {
				System.out.println("--이체 업무 --");
				System.out.println("출금할 사람을 입력하시오");
				String id1 = s.nextLine();
				System.out.println("입금할 사람을 입력하시오");
				String id2 = s.nextLine();
				System.out.println("출금할 금액을 입력하시오");
				String moneyString = s.nextLine();
				int money = Integer.parseInt(moneyString);
				
				BankDTO dto1 = ctx.getBean("dto1",BankDTO.class);
				dto1.setUserid(id1);
				dto1.setBalance(money);
				
				BankDTO dto2 = ctx.getBean("dto2",BankDTO.class);
				dto2.setUserid(id2);
				dto2.setBalance(money);
				
				
				BankService service = ctx.getBean("service",BankService.class);
				
				ArrayList<BankDTO> list =  service.transfer(dto1,dto2);
				dto1 = list.get(0);
				dto2 = list.get(1);
				
				System.out.println("--- 처리 결과 ---");
				System.out.println("--- 출금 user id : " + dto1.getUserid());
				System.out.println("--- 출금 계좌 잔액 : " + dto1.getBalance());
				System.out.println("--- 입금 user id : " + dto2.getUserid());
				System.out.println("--- 입금 계좌 잔액 : " + dto2.getBalance());
				

			}
			if (menu == 4) {
				System.out.println("--시스템 종료 --");

			}
		} while (menu != 4);
		
		ctx.close();

	}

}
