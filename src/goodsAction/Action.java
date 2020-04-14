package goodsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberAction.ActionForward;

public interface Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception;

}
