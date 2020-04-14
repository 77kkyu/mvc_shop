package adminOrderAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsAction.ActionForward;
import memberAction.MemberBean;
import memberAction.MemberDAO;
import orderAction.OrderBean;

public class AdminOrderDetailAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		AdminOrderDAO orderdao = new AdminOrderDAO();
		OrderBean order = new OrderBean();
		
		String num = request.getParameter("num");
		order = orderdao.getOrderDetail(Integer.parseInt(num));
		member = memberdao.getMember(order.getORDER_MEMBER_ID());
		request.setAttribute("order", order);
		request.setAttribute("ordermember", member);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./adminorder/admin_order_modify.jsp");
		return forward;
		
	}

}
