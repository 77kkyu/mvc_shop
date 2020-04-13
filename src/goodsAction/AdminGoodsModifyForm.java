package goodsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao_bean.GoodsBean;
import memberAction.Action;
import memberAction.ActionForward;

public class AdminGoodsModifyForm implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward forward = new ActionForward();
		AdminGoodsDAO agoodsdao = new AdminGoodsDAO();
		GoodsBean agb = new GoodsBean();
		
		String num = request.getParameter("goods_num");
		
		agb = agoodsdao.getGoods(Integer.parseInt(num));
		
		request.setAttribute("agb", agb);
		
		forward.setPath("./admingoods/admin_goods_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
