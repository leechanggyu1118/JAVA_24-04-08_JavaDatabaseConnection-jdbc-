package jdbc;

import java.util.List;

public interface DAO {

	int insert(ProductVO p);

	List<ProductVO> selectList();

	ProductVO selectList(int pno);

	int update(ProductVO p);

	int delete(int pno);

}
