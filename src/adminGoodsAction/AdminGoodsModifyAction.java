package adminGoodsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao_bean.GoodsBean;
import memberAction.Action;
import memberAction.ActionForward;

public class AdminGoodsModifyAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		request.setCharacterEncoding("euc-kr");
		ActionForward forward = new ActionForward();
		AdminGoodsDAO agoodsdao = new AdminGoddsDAO();
		GoodsBean agb = new GoodsBean();
		
		agb.setGOODS_NUM(Integer.parseInt(request.getParameter("goods_num")));
		agb.setGOODS_CATEGORY(request.getParameter("goods_category"));
		agb.setGOODS_NAME(request.getParameter("goods_name"));
		agb.setGOODS_CONTENT(request.getParameter("goods_content"));
		agb.setGOODS_SIZE(request.getParameter("goods_size"));
		agb.setGOODS_COLOR(request.getParameter("goods_color"));
		agb.setGOODS_AMOUNT(Integer.parseInt(request.getParameter("goods_amount")));
		agb.setGOODS_PRICE(Integer.parseInt(request.getParameter("goods_price")));
		agb.setGOODS_BEST(Integer.parseInt(request.getParameter("goods_best")));
		
		int result = agoodsdao.modifyGoods(agb);
		if(result<=0) {
			System.out.println("상품 수정 실패");
			return forward;
		}
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		return forward;
	}

}
