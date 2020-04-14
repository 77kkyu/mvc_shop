package goodsAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GoodsDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public GoodsDAO() {
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/OracleDB");
			con = ds.getConnection();
			
		} catch(Exception ex) {
			System.out.println("DB 연결 실패 :" + ex);
			return;
		}		
	}
	
	
	
	
	public List item_List(String item, int page) {
		
		List itemList = new ArrayList();
		
		int startnum = page * 12 - 11;
		int endnum = page * 12;
		
		try {
		
			StringBuffer findQuery = new StringBuffer();
			
			findQuery.append("Select * From (Select goods_num,");
			findQuery.append("goods_category, goods_name, ");
			findQuery.append("goods_content, goods_price, goods_image, ");
			findQuery.append("goods_best, goods_date, rownum r From ");
			findQuery.append("goods Where ");
			
			if(item.equals("new_item")) {
				findQuery.append("goods_date >= goods_date -7");
			}else if(item.equals("hit_item")) {
				findQuery.append("goods_best = 1");
			}else {
				findQuery.append("goods_category=?");
			}
			
			findQuery.append("order by goods_num desc) ");
			findQuery.append("Where r >= ? AND r <= ?");
			
			pstmt = con.prepareStatement(findQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );
			if (item.equals("new_item") || item.equals("hit_item")){
				pstmt.setInt(1, startnum);
				pstmt.setInt(2, endnum);
			}else {
				pstmt.setString(1, item);
				pstmt.setInt(2, startnum);
				pstmt.setInt(3, endnum);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsBean goodsbean = new GoodsBean();
				goodsbean.setGOODS_NUM(rs.getInt("goods_num"));
				goodsbean.setGOODS_CATEGORY(
						rs.getString("goods_category"));
				goodsbean.setGOODS_NAME(rs.getString("goods_name"));
				goodsbean.setGOODS_PRICE(rs.getInt("goods_price"));
				
				StringTokenizer st=new StringTokenizer(
						rs.getString("goods_image"),",");
				String firstImg=st.nextToken();					 					
				goodsbean.setGOODS_IMAGE(firstImg);				
				goodsbean.setGOODS_BEST(rs.getInt("goods_best"));
								
				itemList.add(goodsbean);
			} 		
				
			rs.close();			
			pstmt.close();
			
			return itemList;
		} catch(SQLException e) {
			e.printStackTrace(); 
		}

		return itemList;
	}
	
	
	
	
	public List item_List(String item, int page, String searchprice) {
		
		List itemList = new ArrayList();
		int startnum = page * 12 - 11;
		int endnum = page * 12;
		
		int firstprice = 0;
		int secondprice = 0;
		
		if(searchprice.equals("1~3")) {
			firstprice = 1;
			secondprice = 29999;
		}else if(searchprice.equals("3~5")) {
			firstprice=30000;
			secondprice=49999;
		}else if(searchprice.equals("5~7")) {
			firstprice=50000;
			secondprice=69999;
		}else if(searchprice.equals("7~10")) {
			firstprice=70000;
			secondprice=99999;
		}else {
			firstprice=100000;
			secondprice=999999;
		}
		
		try {
			
			StringBuffer findQuery = new StringBuffer();
			
			findQuery.append("Select * From (Select goods_num, ");
			findQuery.append("goods_category, goods_name, ");
			findQuery.append("goods_content, goods_price, goods_image, ");
			findQuery.append("goods_best, rownum r From goods Where ");
			
			if(item.equals("new_item")) {
				findQuery.append("goods_date >= goods_date - 7");
			}else if (item.equals("hit_item")) {
				findQuery.append("goods_best = 1");
			}else {
				findQuery.append("goods_category=?");
			}
			
			findQuery.append(" AND(goods_price Between ? AND ?) ");
			findQuery.append(" order by goods_num desc) ");
			findQuery.append(" Where r>=? AND r <=? ");
			
			pstmt = con.prepareStatement(findQuery.toString(), 
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );
			
			if (item.equals("new_item") || item.equals("hit_item")) {
				pstmt.setInt(1, firstprice);
				pstmt.setInt(2, secondprice);
				pstmt.setInt(3, startnum);
				pstmt.setInt(4, endnum);
			}else {
				pstmt.setString(1, item);
				pstmt.setInt(2, firstprice);
				pstmt.setInt(3, secondprice);
				pstmt.setInt(4, startnum);
				pstmt.setInt(5, endnum);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsBean goodsbean = new GoodsBean();
				goodsbean.setGOODS_NUM(rs.getInt("goods_num"));
				goodsbean.setGOODS_CATEGORY(
						rs.getString("goods_category"));
				goodsbean.setGOODS_NAME(rs.getString("goods_name"));
				goodsbean.setGOODS_PRICE(rs.getInt("goods_price"));
				
				StringTokenizer st=new StringTokenizer(
						rs.getString("goods_image"),",");
				String firstimg=st.nextToken();
				
				goodsbean.setGOODS_IMAGE(firstimg);
				goodsbean.setGOODS_BEST(rs.getInt("goodsbean"));
				
				itemList.add(goodsbean);
			} 		
			
			rs.close();			
			pstmt.close();
				
			return itemList;
		} catch(SQLException e){
			e.printStackTrace();
		}
			
		return itemList;
	}
	
	
	
	
	public GoodsBean findDetail(int goods_num, String item, String price, String direction) {
		
		GoodsBean goods = new GoodsBean();
		
		int firstprice = 0;
		int secondprice = 0;
		
		if(price.equals("1~3")) {
			firstprice=1;
			secondprice=29999;
		} else if (price.equals("3~5")) {
			firstprice=30000;
			secondprice=49999;
			
		} else if (price.equals("5~7")) {
			firstprice=50000;
			secondprice=69999;
		} else if (price.equals("7~10")) {
			firstprice=70000;
			secondprice=99999;
		} else if (price.equals("10")){
			firstprice=100000;
			secondprice=999999;
		}
		
		StringBuffer dQuery = new StringBuffer();
		if(direction.equals("next")){
			dQuery.append("Selete goods_num, goods_category,");
			dQuery.append("goods_image, goods_name From goods ");
			dQuery.append("WHERE goods_num > ? AND ");
			if(item.equals("new_item")) {
				dQuery.append("goods_date >= goods_date -7");
			} else if (item.equals("hit_item")) {
				dQuery.append(" goods_best=1 ");
			} else {
				dQuery.append(" goods_category = ? ");			
			}
			if (!price.equals("no")) {
				dQuery.append(" AND (goods_price BETWEEN ? AND ? ) ");
			}
		}else if(direction.equals("prev")){
			dQuery.append(
			"Selete goods_num, goods_category, goods_image");
			dQuery.append(
			"goods_name FROM goods WHERE goods_num < ? AND ");
			if(item.equals("new_item")) {
				dQuery.append("goods_date >= goods_date-7");
			} else if (item.equals("hit_item")) {
				dQuery.append(" goods_best = 1 ");
			} else {
				dQuery.append(" goods_category = ? ");			
			}
			if (!price.equals("no")) {
				dQuery.append(" AND (goods_price BETWEEN ? AND ? ) ");
			}
			dQuery.append("order by goods_num DESC ");
		}
		
		try {
			pstmt = con.prepareStatement(dQuery.toString(), 
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );
			
			if (item.equals("new_item") || item.equals("hit_item")){
				if (price.equals("no")) {
					pstmt.setInt(1, goods_num);
				} else {
					pstmt.setInt(1, goods_num);
					pstmt.setInt(2, firstprice);
					pstmt.setInt(3, secondprice);
				}
			} else {
				if (price.equals("no")) {
					pstmt.setInt(1, goods_num);
					pstmt.setString(2, item);
				} else{
					pstmt.setInt(1, goods_num);
					pstmt.setString(2, item);
					pstmt.setInt(3, firstprice);
					pstmt.setInt(4, secondprice);
				}
			}
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				goods.setGOODS_NUM(rs.getInt("goods_num"));
				goods.setGOODS_CATEGORY(
						rs.getString("goods_category"));
				goods.setGOODS_NAME(rs.getString("goods_name"));
				StringTokenizer st=new StringTokenizer(
						rs.getString("goods_image"),",");
				goods.setGOODS_IMAGE(st.nextToken());
			}
			
			rs.close();			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return goods;	
	}
	
	
	
	
	public GoodsBean findDetailList(int goods_num, String item) {
		
		GoodsBean goods = new GoodsBean();
		
		try {
			StringBuffer dQuery = new StringBuffer();
		
			dQuery.append("Select *");		
			dQuery.append(" FROM goods WHERE goods_num = ?  AND ");
			
			if(item.equals("new_item")) {
				dQuery.append("goods_date >= goods_date-7");
			} else if (item.equals("hit_item")) {
				dQuery.append("goods_best = 1 ");
			} else {
				dQuery.append("goods_category = ? ");	
			}
			
			pstmt = con.prepareStatement(dQuery.toString(), 
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );
			if (item.equals("new_item") || item.equals("hit_item")){
				pstmt.setInt(1, goods_num);
			} else {
				pstmt.setInt(1, goods_num);
				pstmt.setString(2, item);
			}
			
			rs = pstmt.executeQuery();			
			
			if (rs.next()) {	
				goods.setGOODS_NUM(rs.getInt("goods_num"));
				goods.setGOODS_CATEGORY(
						rs.getString("goods_category"));
				goods.setGOODS_NAME(rs.getString("goods_name"));
				goods.setGOODS_CONTENT(
						rs.getString("goods_content"));
				goods.setGOODS_SIZE(rs.getString("goods_size"));
				goods.setGOODS_COLOR(rs.getString("goods_color"));
				goods.setGOODS_AMOUNT(rs.getInt("goods_amount"));
				goods.setGOODS_PRICE(rs.getInt("goods_price"));
				goods.setGOODS_IMAGE(rs.getString("goods_image"));
				goods.setGOODS_BEST(rs.getInt("goods_best"));					
			}
			rs.close();			
			pstmt.close();

			return goods;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	public int getCount(String item) {
		int count=0;
		
		StringBuffer findQuery = new StringBuffer();
		
		findQuery.append("Select count(goods_num) FROM goods WHERE ");
		
		if (item.equals("new_item")) {
			findQuery.append("goods_date >= goods_date-7");
		} else if (item.equals("hit_item")) { 
			findQuery.append("goods_best = ? ");
		}else {
			findQuery.append("goods_category = ?");
		}
		
		try{
			pstmt=con.prepareStatement(findQuery.toString());
			if (item.equals("new_item")){
			}else if (item.equals("hit_item")) {
				pstmt.setInt(1,1);
			}else{
				pstmt.setString(1, item);
			}
			
			rs=pstmt.executeQuery();
			rs.next();
			
			count=rs.getInt(1);
			
			rs.close();
			pstmt.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	
	
	
	public int getCount(String item, String searchprice) {
		int count=0;
		int firstprice=0;
		int secondprice=0;
		
		if(searchprice.equals("1~3")) {
			firstprice=1;
			secondprice=29999;
		} else if (searchprice.equals("3~5")) {
			firstprice=30000;
			secondprice=49999;
		} else if (searchprice.equals("5~7")) {
			firstprice=50000;
			secondprice=69999;
		} else if (searchprice.equals("7~10")) {
			firstprice=70000;
			secondprice=99999;
		} else {
			firstprice=100000;
			secondprice=999999;
		}
		
		StringBuffer findQuery = new StringBuffer();
		
		findQuery.append("Selcet count(goods_num) From goods WHERE ");
		if (item.equals("new_item")) {
			findQuery.append("goods_date >= goods_date-7");
		} else if (item.equals("hit_item")) { 
			findQuery.append("goods_best = ? ");
		}else {
			findQuery.append("goods_category = ?");
		}
		findQuery.append(" and (goods_price between ? and ?)");
		
		try
		{
			pstmt=con.prepareStatement(findQuery.toString());
			
			if(item.equals("new_item")){
				pstmt.setInt(1, firstprice);
				pstmt.setInt(2, secondprice);
			}else if (item.equals("hit_item")) {
				pstmt.setInt(1, 1);
				pstmt.setInt(2, firstprice);
				pstmt.setInt(3, secondprice);
			}else{
				pstmt.setString(1, item);
				pstmt.setInt(2, firstprice);
				pstmt.setInt(3, secondprice);
			}
			
			rs=pstmt.executeQuery();
			rs.next();
			count=rs.getInt(1);
			
			rs.close();
			pstmt.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return count;
	}
	
}
