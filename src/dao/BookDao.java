package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Book;
import model.BookClass;

public class BookDao {
    //获取图书分类
    public List<BookClass> getBookClass(){
        util.DbUtil dbUtil = new util.DbUtil();
        Connection conn = null;
        List<BookClass> bookClasses = new java.util.ArrayList<>();
        try {
            conn = dbUtil.getCon();
            java.sql.Statement statement = conn.createStatement();
            String sql = "SELECT * FROM tb_booktype;";
            ResultSet resultSet  = statement.executeQuery(sql);

            while(resultSet.next()){
                BookClass bookClass = new BookClass();
                bookClass.setId(resultSet.getInt("id"));
                bookClass.setBookClass(resultSet.getString("type"));
                bookClass.setBookClassParam(resultSet.getString("param"));
                bookClasses.add(bookClass);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bookClasses;
    }
    //��������
   public int insertBookClass(String bookClass){
	   //������ɵ��  �������һ��param�ֶ���map�Ĳ�ѯ����  ��� tape�ֶκ�id�ֶ�Ϊ���ֺ�����   Ҳ������map��key  ����תƴ��ʧ�� �������� �����޸�
    	String sql = "INSERT INTO `db_bookshopping`.`tb_booktype` (type, param) VALUES ('"+bookClass+"','"+bookClass+"')";
    	util.DbUtil dbUtil = new util.DbUtil();
    	Connection connection = null;
    	int count = 0;
    	try {
			connection = dbUtil.getCon();
			java.sql.Statement statement = connection.createStatement();
			 count = statement.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	return count;
    }
  
   public int modifyBookClass(String bookClass,int bookId){
	 String sql = "update tb_booktype set type = '"+bookClass+"' where id = "+bookId+";";
   	util.DbUtil dbUtil = new util.DbUtil();
   	Connection connection = null;
   	int count = 0;
   	try {
			connection = dbUtil.getCon();
			java.sql.Statement statement = connection.createStatement();
			 count = statement.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
   	   return count;
   }
   
   public int deleteBookClass(int bookId){
	   String sql = "DELETE FROM tb_booktype where  id = "+bookId+"";
	   	util.DbUtil dbUtil = new util.DbUtil();
	   	Connection connection = null;
	   	int count = 0;
	   	try {
				connection = dbUtil.getCon();
				java.sql.Statement statement = connection.createStatement();
				 count = statement.executeUpdate(sql);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					dbUtil.closeCon(connection);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	   	   return count;
   }

 //��ȡ���ݿ���ÿ�����͵�ǰ����ͼ�����
   public List<model.Book> getBookListById(int id){
   	String sql = "SELECT * FROM db_bookshopping.tb_book where book_typeid = "+id+" limit 0,5 ;";
   	util.DbUtil dbUtil = new util.DbUtil();
   	Connection connection = null;
   	List<model.Book> list = new java.util.ArrayList<model.Book>();
   	try {
			connection = dbUtil.getCon();
			java.sql.Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			while(set.next()){
				Book book = new model.Book();
				book.setAuthor(set.getString("book_author"));
				book.setDescrible(set.getString("book_describe"));
				book.setDiscount(set.getFloat("book_discount"));
				book.setId(set.getInt("book_id"));
				book.setName(set.getString("book_name"));
				book.setType(set.getInt("book_typeid"));
				book.setPrice(set.getFloat("book_price"));
				book.setQuantity(set.getInt("book_quantity"));
				book.setUrl(set.getString("book_url"));
				list.add(book);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	return list;

   }
	public List<model.Book> getBookListByType(int typeId){
		String sql = "SELECT * FROM db_bookshopping.tb_book where book_typeid = " + typeId;
		util.DbUtil dbUtil = new util.DbUtil();
		Connection connection = null;
		List<model.Book> list = new java.util.ArrayList<model.Book>();
		try {
			connection = dbUtil.getCon();
			java.sql.Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			while(set.next()){
				Book book = new model.Book();
				book.setAuthor(set.getString("book_author"));
				book.setDescrible(set.getString("book_describe"));
				book.setDiscount(set.getFloat("book_discount"));
				book.setId(set.getInt("book_id"));
				book.setName(set.getString("book_name"));
				book.setType(set.getInt("book_typeid"));
				book.setPrice(set.getFloat("book_price"));
				book.setQuantity(set.getInt("book_quantity"));
				book.setUrl(set.getString("book_url"));
				list.add(book);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
   //for testing
    public static void main(String[] args) {
        BookDao dao = new BookDao();
        List<BookClass> a = dao.getBookClass();
        System.out.println(a.get(0).getBookClass());
    }
}
