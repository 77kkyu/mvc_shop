package adminGoodsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao_bean.AdminGoodsDAO;
import dao_bean.AdminGoodsBean;
import memberAction.Action;
import memberAction.ActionForward;

public class AdminGoodsDeleteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		ActionForward forward = new ActionForward();
		AdminGoodsDAO agoodsdao = new AdminGoodsDAO();
		AdminGoodsBean agb = new AdminGoodsBean();
		
		agb.setGOODS_NUM(Integer.parseInt(request.getParameter("goods_num")));
		
		int check=agoodsdao.deleteGoods(agb);
		if(check>0) {
			forward.setRedirect(true);
			forward.setPath("GoodsList.ag");
		}
		
		return forward;
	}

}
