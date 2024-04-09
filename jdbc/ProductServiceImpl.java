package jdbc;


import java.util.List;

public class ProductServiceImpl implements Service {
	private DAO dao; //인터페이스
	public ProductServiceImpl() {
		dao = new ProductDAOImpl(); //구현체
	}
	@Override
	public int register(ProductVO p) {
		// 실제 구현 영역
		System.out.println("register serviceImpl success!!");
		// 각 DAO에게 저장 요청
		// DAO 요청시 SQL구문과 비슷하게 메서드명을 작성하는 것이 일반적
		return dao.insert(p);
	}
	
	
	
	
	@Override
	public List<ProductVO> getList() {
		System.out.println("list serviceImpl success!!");
		return dao.selectList();
	}
	@Override
	public ProductVO getDetail(int pno) {
		System.out.println("detail serviceImpl success!!");
		return dao.selectList(pno);
	}
	
	
	@Override
	public int modify(ProductVO p) {
		System.out.println("modify serviceImpl success!!");
		return dao.update(p);
	}
	@Override
	public int remove(int pno) {
		System.out.println("remove serviceImpl success!!");
		return dao.delete(pno);
	}
	
	


	
	
	
}
