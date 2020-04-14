<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>쇼핑몰</title>

<script>
function check(){
	var id = joinform.member_id.value;
	var password1 = joinform.member_pw.value;
	var password2 = joinform.member_pw2.value;
	var email1 = joinform.member_email1.value;
	var email2 = joinform.member_email2.value;
	var phone = joinform.member_phone.value;
	var addr1 = joinform.member_addr1.value;
	var addr2 = joinform.member_addr2.value;
	var mobile = joinform.member_mobile.value; 
	
	var forms = document.getElementById("joinform");
	
	if((forms.member_name.value == "")||(forms.member_name.value.length <= 1)){
		alert("이름을 제대로 입력해 주세요.");
		form.member_name.focus();
		return false;
	}
	if(id.length == 0){
		alert("아이디를 입력하세요.");
		joinform.member_id.focus();
		return false;
	}
	if(password1.length == 0){
		alert("비밀번호를 입력하세요.");
		joinform.member_pw.focus();
		return false;
	}
	if(password1 != password2){
		alert("비밀번호가 일치하지 않습니다.");
		joinform.member_pw.value = "";
		joinform.member_pw2.value = "";
		joinform.member_pw.focus();
		return false;
	}
	if((forms.member_jumin1.value == "")||(forms.member_jumin1.value.length < 6)){
		alert("주민등록번호 앞의 6자리를 입력해 주세요.");
		forms.member_jumin1.focus();
		return false;
	}
	if((forms.member_jumin2.value == "") || (forms.member_jumin2.value.length < 7)){
		alert("주민등록번호 두의 7자리를 입력해 주세요.");
		forms.member_jumin2.focus();
		return false;
	}
	if(email1.length == 0 || email2.length==0) {
		alert("이메일을 제대로 입력하세요.");
		joinform.member_email1.focus();
		return false;
	}
	if(phone.length == 0){
		alert("집 전화를 입력하세요.");
		joinform.member_phone.focus();
		return false;
	}
	if((forms.member_zipcode1.value == "")||(forms.member_zipcode1.value.length < 3)){
		alert("우편번호 앞의 3자리를 입력해 주세요.");
		forms.member_zipcode1.focus();
		return false;
	}
	if((forms.member_zipcode2.value == "")||(forms.member_zipcode2.value.length < 3)){
		alert("우편번호 뒤의 3자리를 입력해 주세요.");
		forms.member_zipcode2.focus();
		return false;
	}
	if(addr1.length == 0){
		alert("집 주소를 입력하세요.");
		joinform.member_addr1.focus();
		return false;
	}
	if(addr2.length == 0){
		alert("상세 주소를 입력하세요.");
		joinform.member_addr2.focus();
		return false;
	}
	if(mobile.length == 0){
		alert("휴대폰 번호를 입력하세요.");
		joinform.member_mobile.focus();
		return false;
	}
	
	return true;	
}

function openConfirmId(joinform){
	var id = joinform.member_id.value;
	var url = "./MemberIDCheckAction.me?member_id="+joinform.member_id.value;
	
	if(id.length == 0){
		alert("아이디를 입력하세요.");
		joinform.member_id.focus();
		return false;
	}
	open(url,"confirm", "toolbar=no, location=no, status=no, menubar=no, "
			 + "scrollbars=no, resizable=no, width=400, height=200");
}

function openZipcode(joinform){
	var url = "./Zipcode.me"
	open(url,"confirm","toolbar=no, location=no, status=no, menubar=no, "
			 + "scrollbars=yes, resizable=no, width=410, height=400");
}

function gNumCheck(){
	if(event.keyCode >= 48 && event.keyCode <= 57){
		return true;
	}else{
		event.returnValue = false;
	}
}
</script>
</head>
<body>

<table width="960" cellspacing="0" cellpadding="0" border="0" align="center">
	<tr>
		<td colspan=2>
			<!-- 회원가입 -->
			<form name="joinform" action="./MemberJoinAction.me" method="post" onsubmit="return check()">
			<p align="center">
			<table border="1" width="80%" height="80%">
			<tr>
				<td width="17%" bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이름</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_name" size="20"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;아이디</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_id" size="10" maxlength=15/>
					<input type="button" name="confirm_id" value="중복확인" onclick="openConfirmId(this.form)" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="password" name="member_pw" size="15"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호 확인</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="password" name="member_pw2" size="15"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">&nbsp;</td>
				<td>
					<font size="2">&nbsp;&nbsp;&nbsp;
					(아이디와 비밀번호는 문자와숫자를 조합하여 2~12 자리로 만들어 주세요)</font>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;주민등록번호</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_jumin1" size="12" onkeypress="gNumCheck()" maxlength="6" />-
					<input type="text" name="member_jumin2" size="12" onkeypress="gNumCheck()" maxlength="7" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;이메일 주소</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_email1" size="13"/>@
					<input type="text" name="member_email2" size="15"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;메일 수신 여부</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="radio" name="member_email_get" value="YES" checked/>
					<font size="2">수신</font>
						&nbsp;&nbsp;
					<input type="radio" name="member_email_get" value="NO"/>
					<font size="2">수신 안함</font>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;집전화</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_phone" size="24"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;우편번호</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_zipcode1" size="6" onkeypress="gNumCheck()" maxlength="3"/>
					<input type="text" name="member_zipcode2" size="6" onkeyprss="gNumCheck()" maxlength="3"/>&nbsp;&nbsp;
					<input type="button" name="zipcode" value="우편번호 검색" onclick="openZipcode(this.form)" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;집주소</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_addr1" size="50" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;상세주소</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_addr2" size="50"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;휴대폰</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_mobile" size="24" />
				</td>
			</tr>
			</table>
			
			<table width="80%">
				<tr>
					<td align="center">
						<br/>
						<input type="submit" value="확  인"/>
					</td>
				</tr>
			</table>
			</form>
		</td>
	</tr>
	
</table>
</body>
</html>

























