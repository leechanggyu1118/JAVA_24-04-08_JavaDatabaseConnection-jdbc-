package jdbc;

import java.util.List;

public interface Service {

	int register(ProductVO p);

	List<ProductVO> getList();

	ProductVO getDetail(int pno);

	int modify(ProductVO p);

	int remove(int pno);
	

}
