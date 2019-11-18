<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加联系人</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>

	<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>

	<script type="text/javascript">

		function checkPhone(){
			var phone = $("#linkPhone").val();
			if (/^[1][3,4,5,7,8][0-9]{9}$/.test(phone)) {
				var existPhone;
				$.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/linkman/selectLinkmanIdByPhone.action',
					data: {"phone": phone},
					async: false,
					success: function (data) {
						if (data == "exist") {
							alert("该手机号码已存在");
							existPhone = false;
						} else {
							existPhone = true;
						}
					}
				});
				return existPhone;
			}else{
				alert("该手机号码格式不符")
				return false;
			}
		}

	</script>

		<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1 onsubmit="return checkPhone()"
		action="${pageContext.request.contextPath }/linkman/insertLinkman.action"
		method=post>

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						 height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：联系人管理 &gt; 添加联系人</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<tr>
								<td>联系人姓名：</td>
								<td>
									<input type="text" id="linkman.linkName" name="linkman.linkName" style="WIDTH: 180px"/>  <span style="color: red">*</span>
								</td>
								<td>联系人性别：</td>
								<td>
									<input type="radio" name="linkman.linkSex" value="男" checked="checked">男
									<input type="radio" name="linkman.linkSex" value="女">女
								</td>
							</tr>
							<TR>
								<td>联系人账号：</td>
								<td>
									<INPUT class=textbox id="user.userAccount"
										   style="WIDTH: 180px" maxLength=50 name="user.userAccount">  <span style="color: red">*</span>
								</td>
								<td>联系人密码：</td>
								<td>
									<input type="password" id="user.userPassword" name="user.userPassword" style="WIDTH: 180px"/>  <span style="color: red">*</span>
								</td>
							</TR>
							<TR>
								<td>联系人手机：</td>
								<td>
								<INPUT class=textbox id="linkPhone"
														style="WIDTH: 180px" maxLength=50 name="linkman.linkPhone">  <span style="color: red">*</span>
								</td>
								<td>联系人邮箱：</td>
								<td>
									<input type="text" name="linkman.linkEmail" style="WIDTH: 180px"/>  <span style="color: red">*</span>
								</td>
							</TR>
							<TR>
								<td>联系人职位：</td>
								<td>
								<INPUT class=textbox id="linkman.linkPosition"
														style="WIDTH: 180px" maxLength=50 name="linkman.linkPosition">
								</td>
							</TR>
							<TR>
								<td>联系人简介 ：</td>
								<td colspan="4">
									<textarea class=textbox id="linkman.linkMemo"  style="OVERFLOW:hidden;width:400px;height:200px"
											  name="linkman.linkMemo" ></textarea>
								</td>
							</TR>
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value="保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
