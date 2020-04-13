<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>���θ�</title>

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
		alert("�̸��� ����� �Է��� �ּ���.");
		form.member_name.focus();
		return false;
	}
	if(id.length == 0){
		alert("���̵� �Է��ϼ���.");
		joinform.member_id.focus();
		return false;
	}
	if(password1.length == 0){
		alert("��й�ȣ�� �Է��ϼ���.");
		joinform.member_pw.focus();
		return false;
	}
	if(password1 != password2){
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		joinform.member_pw.value = "";
		joinform.member_pw2.value = "";
		joinform.member_pw.focus();
		return false;
	}
	if((forms.member_jumin1.value == "")||(forms.member_jumin1.value.length < 6)){
		alert("�ֹε�Ϲ�ȣ ���� 6�ڸ��� �Է��� �ּ���.");
		forms.member_jumin1.focus();
		return false;
	}
	if((forms.member_jumin2.value == "") || (forms.member_jumin2.value.length < 7)){
		alert("�ֹε�Ϲ�ȣ ���� 7�ڸ��� �Է��� �ּ���.");
		forms.member_jumin2.focus();
		return false;
	}
	if(email1.length == 0 || email2.length==0) {
		alert("�̸����� ����� �Է��ϼ���.");
		joinform.member_email1.focus();
		return false;
	}
	if(phone.length == 0){
		alert("�� ��ȭ�� �Է��ϼ���.");
		joinform.member_phone.focus();
		return false;
	}
	if((forms.member_zipcode1.value == "")||(forms.member_zipcode1.value.length < 3)){
		alert("�����ȣ ���� 3�ڸ��� �Է��� �ּ���.");
		forms.member_zipcode1.focus();
		return false;
	}
	if((forms.member_zipcode2.value == "")||(forms.member_zipcode2.value.length < 3)){
		alert("�����ȣ ���� 3�ڸ��� �Է��� �ּ���.");
		forms.member_zipcode2.focus();
		return false;
	}
	if(addr1.length == 0){
		alert("�� �ּҸ� �Է��ϼ���.");
		joinform.member_addr1.focus();
		return false;
	}
	if(addr2.length == 0){
		alert("�� �ּҸ� �Է��ϼ���.");
		joinform.member_addr2.focus();
		return false;
	}
	if(mobile.length == 0){
		alert("�޴��� ��ȣ�� �Է��ϼ���.");
		joinform.member_mobile.focus();
		return false;
	}
	
	return true;	
}

function openConfirmId(joinform){
	var id = joinform.member_id.value;
	var url = "./MemberIDCheckAction.me?member_id="+joinform.member_id.value;
	
	if(id.length == 0){
		alert("���̵� �Է��ϼ���.");
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
			<!-- ȸ������ -->
			<form name="joinform" action="./MemberJoinAction.me" method="post" onsubmit="return check()">
			<p align="center">
			<table border="1" width="80%" height="80%">
			<tr>
				<td width="17%" bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�̸�</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_name" size="20"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;���̵�</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_id" size="10" maxlength=15/>
					<input type="button" name="confirm_id" value="�ߺ�Ȯ��" onclick="openConfirmId(this.form)" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;��й�ȣ</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="password" name="member_pw" size="15"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;��й�ȣ Ȯ��</font>
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
					(���̵�� ��й�ȣ�� ���ڿͼ��ڸ� �����Ͽ� 2~12 �ڸ��� ����� �ּ���)</font>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;�ֹε�Ϲ�ȣ</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_jumin1" size="12" onkeypress="gNumCheck()" maxlength="6" />-
					<input type="text" name="member_jumin2" size="12" onkeypress="gNumCheck()" maxlength="7" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;�̸��� �ּ�</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_email1" size="13"/>@
					<input type="text" name="member_email2" size="15"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;���� ���� ����</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="radio" name="member_email_get" value="YES" checked/>
					<font size="2">����</font>
						&nbsp;&nbsp;
					<input type="radio" name="member_email_get" value="NO"/>
					<font size="2">���� ����</font>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;����ȭ</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_phone" size="24"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;�����ȣ</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_zipcode1" size="6" onkeypress="gNumCheck()" maxlength="3"/>
					<input type="text" name="member_zipcode2" size="6" onkeyprss="gNumCheck()" maxlength="3"/>&nbsp;&nbsp;
					<input type="button" name="zipcode" value="�����ȣ �˻�" onclick="openZipcode(this.form)" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;���ּ�</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_addr1" size="50" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;���ּ�</font>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
					<input type="text" name="member_addr2" size="50"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5">
					<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;�޴���</font>
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
						<input type="submit" value="Ȯ  ��"/>
					</td>
				</tr>
			</table>
			</form>
		</td>
	</tr>
	
</table>
</body>
</html>

























