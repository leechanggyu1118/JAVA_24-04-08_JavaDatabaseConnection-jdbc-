package jdbc;

import java.util.List;
import java.util.Scanner;

public class ProductController {
	/* Controller <-> Service <-> DAO <-> DB
	 * Controller 모든 메뉴에 분기처리
	 */
	private Scanner scan;
	private Service svc; // 아직 안만듦 interface
	private boolean flag; // 종료변수
	
	public ProductController() {
		scan = new Scanner(System.in);
		svc = new ProductServiceImpl(); //service 구현체
		flag = true;
		printMenu();
	}
	private void printMenu() {
		while(flag) {
			System.out.println("--상품관리프로그램--");
			System.out.println("1.상품등록|2.상품목록|3.상품검색(상품상세보기)");
			System.out.println("4.상품수정|5.상품삭제|6.종료");
			System.out.println("메뉴선택 >");
			
			int menu = scan.nextInt();
			
			switch (menu) {
			case 1: register(); break;
			case 2: list(); break;
			case 3: detail(); break;
			case 4: modify(); break;
			case 5: remove(); break;

			default:
				flag=false;
				break;
			}
		}
	}
	private void remove() {
		// 상품삭제 DELETE FROM product WHERE pno=?
		System.out.println("삭제하려는 번호: ");
		int pno = scan.nextInt();
		int isOk = svc.remove(pno);
		//잘 되면 1, 안 되면 0
		System.out.println("상품삭제 "+((isOk >0)? "성공" : "실패 "));
	}
	private void modify() {
		// 상품내용 수정 UPDATE product SET pname=?, price=?, madeby=?, WHERE pno=?
		System.out.println("수정하려는 번호: ");
		int pno = scan.nextInt();
		System.out.println("상품이름: ");
		scan.nextLine();
		String pname = scan.nextLine();
		System.out.println("상품가격: ");
		int price = scan.nextInt();
		System.out.println("상품상세내역: ");
		scan.nextLine();
		String madeby = scan.nextLine();
		
		ProductVO p  = new ProductVO(pno, pname, price, madeby);
		int isOk = svc.modify(p);
		//잘 되면 1, 안 되면 0
		System.out.println("상품등록 "+((isOk >0)? "성공" : "실패 "));
		
		
		
		
	}
	private void detail() {
		// 상품 하나의 상세정보 SELECT * FROM product WHERE pno =?
		System.out.println("상품번호 >");
		int pno = scan.nextInt();
		ProductVO p = svc.getDetail(pno);
		System.out.println(p);
		
	}
	private void list() {
		// 상품리스트 출력 SELECT * FROM product;
		List<ProductVO> list = svc.getList();
		//출력
		for(ProductVO p : list) {
			System.out.println(p);
		}
	}
	private void register() {
		// 상품등록
		System.out.println("상품이름: ");
		scan.nextLine();
		String pname = scan.nextLine();
		System.out.println("상품가격: ");
		int price = scan.nextInt();
		System.out.println("상품상세내역: ");
		scan.nextLine();
		String madeby = scan.nextLine();
		ProductVO p = new ProductVO(pname, price, madeby);
		// service에게 등록을 요청하는 메서드 작성
		int isOk = svc.register(p);
		//잘 되면 1, 안 되면 0
		System.out.println("상품등록 "+((isOk >0)? "성공" : "실패 "));
		
	}
	
	
	
}
