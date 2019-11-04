<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}

TD {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}
</STYLE>

    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        function refresh_code() {
            $("#identifyCodeImage").attr("src", "${pageContext.request.contextPath}/identifyCode.action?v=" + Math.random());
        }

        function login(){
            $("#messageErrors").css("visibility", "hidden");
            $("#userMessage").css("visibility", "hidden");
            $("#identifyCodeMessage").css("visibility", "hidden");
            if($("#userAccount").val().trim() == '' || $("#userPassword").val().trim() == '' || $("#identifyCode").val().trim() == '' ){
                $("#messageErrors").css("visibility", "visible");
                return false;
            }
            var params = $("#form1").serializeArray();
			var jsonData = [];
            for(var i = 0;i < params.length;i++){
				var json = {};
				json.name = params[i].name;
				json.value = params[i].value;
				jsonData.push(json);
            }
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/login.action',
                data: jsonData,
                success:function(data){
					if (data == 'success') {
						window.location.href='${pageContext.request.contextPath}/welcome.htm';
						window.top.top_frame.location.reload();
					}else if (data == "identifyCodeMessage") {
                        $("#identifyCodeMessage").css("visibility", "visible");
					}else if (data == "userMessage") {
						$("#userMessage").css("visibility", "visible");
					}
                    refresh_code();
					$("#identifyCodeImage").attr("value", "");
                }
            });
        }



    </script>




<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1 method=post action="${pageContext.request.contextPath }/login.action">

		<DIV id=UpdatePanel1>
			<DIV id=div1
				style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
			<DIV id=div2
				style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


			<DIV>&nbsp;&nbsp;</DIV>
			<DIV>
				<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
					<TBODY>
						<TR>
							<TD style="HEIGHT: 105px"><IMG
								src="${pageContext.request.contextPath }/images/login_1.gif"
								border=0></TD>
						</TR>
						<TR>
							<TD
								background="${pageContext.request.contextPath }/images/login_2.jpg"
								height=300>
								<TABLE height=300 cellPadding=0 width=900 border=0>
									<TBODY>
										<TR>
											<TD colSpan=2 height=35></TD>
										</TR>
										<TR>
											<TD width=360></TD>
											<TD>
												<TABLE cellSpacing=0 cellPadding=2 border=0>
													<TBODY>
														<TR>
															<TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
															<TD style="HEIGHT: 28px" width=150>
																<INPUT id=userAccount style="WIDTH: 130px" name="userAccount" value="${user.userAccount}"></TD>
															<TD style="HEIGHT: 28px;color:gold" width=370 >
																<span id="userMessage" style="visibility: hidden;">用户名或密码错误</span>
                                                                <%--<span><c:if test="${loginMessage.userMessage ne null}">${loginMessage.userMessage}</c:if></span>--%>
                                                                <%--<SPAN--%>
																<%--id=RequiredFieldValidator3--%>
																<%--style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN>--%>
																</TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 28px">登录密码：</TD>
															<TD style="HEIGHT: 28px"><INPUT id=userPassword
																style="WIDTH: 130px" type=password name="userPassword"></TD>
															<%--<TD style="HEIGHT: 28px">--%>
																<%--<SPAN--%>
																<%--id=RequiredFieldValidator4--%>
																<%--style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN>--%>
															<%--</TD>--%>
														</TR>
														<TR>
															<TD style="HEIGHT: 28px">验证码：</TD>
															<TD style="HEIGHT: 28px"><INPUT id=identifyCode
																style="WIDTH: 60px" name="identifyCode"> <img
																id="identifyCodeImage" src="${pageContext.request.contextPath}/identifyCode.action"
																alt="" width="56" height="20" align='absMiddle' onclick="refresh_code()" /></TD>
															<TD style="HEIGHT: 28px;color:gold " >
																<span id="identifyCodeMessage" style="visibility:hidden">验证码错误</span>
															<%--<span><c:if test="${loginMessage.identifyCodeMessage ne null}">${loginMessage.identifyCodeMessage}</c:if> </span>--%>
															</TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 18px;color:gold" colspan="3"  >
                                                                <span id="messageErrors" style="visibility: hidden;">
                                                                    用户名，密码，验证码不能为空
                                                                </span>
                                                                <%--<c:if test="${messageErrors ne null}">--%>
                                                                    <%--<c:forEach items="${messageErrors}" var="messageError">--%>
                                                                        <%--<span style="color:gold">${messageError.defaultMessage}</span>--%>
                                                                    <%--</c:forEach>--%>
                                                                <%--</c:if>--%>
                                                            </TD>
															<%--<TD style="HEIGHT: 18px"></TD>--%>
															<%--<TD style="HEIGHT: 18px"></TD>--%>
														</TR>
														<TR>
															<TD></TD>
															<TD><img id=btn
																style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px;cursor: pointer"
																onclick="login()"
                                                                    <%--onclick="top_refresh()"--%>
																<%--type=image--%>
																src="${pageContext.request.contextPath }/images/login_button.gif"
																name=btn /></TD>
														</TR>
													</TBODY>
												</TABLE>
											</TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR>
							<TD><IMG
								src="${pageContext.request.contextPath }/images/login_3.jpg"
								border=0></TD>
						</TR>
					</TBODY>
				</TABLE>
			</DIV>
		</DIV>


	</FORM>
</BODY>
</HTML>
