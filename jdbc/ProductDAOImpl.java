package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements DAO {
	
	//DB연결
	private Connection conn;
	//sql 구문을 실행시키는 기능하는 객체
	private PreparedStatement pst;
	//쿼리구문 저장 스트링
	private String query="";
	
	public ProductDAOImpl() {
		//DBConnection class 생성 (싱글톤)
		DatabaseConnection dbc = DatabaseConnection.getInstance();
		conn = dbc.getConnection();
		
	}
	

	
	@Override
	public int insert(ProductVO p) {
		System.out.println("insert DAOImpl success!!");
		query = "INSERT INTO product(pname, price, madeby) values(?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, p.getPname());
			pst.setInt(2, p.getPrice());
			pst.setString(3, p.getMadeby());
			//insert, update, delete => executeUpdate() return int (처리개수로 리턴)
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert Error!!");
			e.printStackTrace();
		}
		return 0;
	}
	


	@Override
	public List<ProductVO> selectList() {
		System.out.println("list DAOImpl success!!");
		query = "SELECT * FROM product ORDER BY pno DESC";
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			pst = conn.prepareStatement(query);
			//list => executeQuery(); => return ResultSet
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				list.add(new ProductVO(rs.getInt("pno"), 
						rs.getString("pname"), 
						rs.getInt("price")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("list error");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductVO selectList(int pno) {
		System.out.println("list DAOImpl success!!");
		query="SELECT * FROM product WHERE pno=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, pno);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return new ProductVO(
						rs.getInt("pno"),
						rs.getString("pname"),
						rs.getInt("price"),
						rs.getString("regdate"),
						rs.getString("madeby")
						);
			}
		} catch (SQLException e) {
			System.out.println("detail error!!");
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public int update(ProductVO p) {
		System.out.println("UPDATE DAOImpl success!!");
		query="UPDATE product SET pname=?, price=?, madeby=?, regdate=now() WHERE pno=?";
				try {
					pst = conn.prepareStatement(query);
					pst.setString(1, p.getPname());
					pst.setInt(2, p.getPrice());
					pst.setString(3, p.getMadeby());
					pst.setInt(4, p.getPno());
					return pst.executeUpdate();
				} catch (SQLException e) {
					System.out.println("UPDATE Error!!");
					e.printStackTrace();
				}
			return 0;
	}


	@Override
	public int delete(int pno) {
		System.out.println("DELETE DAOImpl success!!");
		query="DELETE FROM product WHERE pno=?";
			try {
				pst = conn.prepareStatement(query);
						pst.setInt(1, pno);
						return pst.executeUpdate();
			} catch (SQLException e) {
				System.out.println("DELETE Error!!");
				e.printStackTrace();
			}
		return 0;
	}






	
	
	
}
