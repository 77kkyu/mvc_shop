package memberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLoginAction implements Action {//로그인 시도

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		MemberDAO memberdao = new MemberDAO();
		
		String id = request.getParameter("member_id");
		String pass = request.getParameter("member_pw");
		
		int check = memberdao.userCheck(id, pass);
		if(check == 1) { // 1 로그인 성공
			
			session.setAttribute("id", id);
			
			if(memberdao.isAdmin(id)) {
				forward.setRedirect(true);
				forward.setPath("./GoodsList.ag");
				return forward;
			}else {
				forward.setRedirect(true);
				forward.setPath("./GoodsList.go?item=new_item");
				return forward;
			}
			
		}else if(check == 0) {//로그인 실패
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
		}else {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
		}
		
		return null;
	}

}
