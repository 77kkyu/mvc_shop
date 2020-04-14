package adminOrderAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsAction.ActionForward;

public class AdminOrderDeleteAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		
		AdminOrderDAO orderdao = new AdminOrderDAO();
		
		boolean result = false;
		String num = request.getParameter("num");
		result=orderdao.deleteOrder(Integer.parseInt(num));
		
		if(result == false) {
			System.out.println("상품 삭제 실패");
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./AdminOrderList.ao");
		return forward;
		
	}

}
