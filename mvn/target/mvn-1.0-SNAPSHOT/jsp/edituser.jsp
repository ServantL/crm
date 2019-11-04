<%--
  Created by IntelliJ IDEA.
  User: Servant
  Date: 2019/10/8
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
    <LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
          rel=stylesheet>

    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        function validation() {
            var orderUserPassword = $("#orderUserPassword").attr("value");
            if (orderUserPassword != "${sessionScope.user.userPassword}") {
                $("#orderPasswordMessage").css("visibility", "visible");
                return false;
            }
            $("#orderPasswordMessage").css("visibility", "hidden");
            var newUserPassword = $("#newUserPassword").attr("value");
            if (newUserPassword.trim() == "" || newUserPassword.length > 16) {
                $("#newPasswordMessage").css("visibility", "visible");
                return false;
            }
            $("#newPasswordMessage").css("visibility", "hidden");
            var identifyUserPassword = $("#identifyUserPassword").attr("value");
            if (newUserPassword != identifyUserPassword) {
                $("#identifyPasswordMessage").css("visibility", "visible");
                return false;
            }
            $("#identifyPasswordMessage").css("visibility", "hidden");
            return true;
        }
    </script>

</head>
<BODY>
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
        <td bgColor=#ffffff>
            <table border="0" cellpadding="5"
                   cellspacing="0">
                <form id="form1" method="post"
                      action="${pageContext.request.contextPath}/user/updateUserPassword.action"
                      onsubmit="return validation()">
                    <input type="hidden" name="userId" value="${sessionScope.user.userId}"/>
                    <tr>
                        <td>
                            用户名:
                        </td>
                        <td>
                            <input name="userAccount" type="text" disabled="disabled"
                                   value="${sessionScope.user.userAccount}"/>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            原密码:
                        </td>
                        <td>
                            <input type="password" id="orderUserPassword"/>
                        </td>
                        <td>
                <span id="orderPasswordMessage" type="text" value="原密码错误"
                      style="visibility:hidden;color:darkred">原密码错误</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            新密码:
                        </td>
                        <td>
                            <input type="password" name="userPassword" id="newUserPassword"/>
                        </td>
                        <td>
                <span id="newPasswordMessage" type="text" value="密码不能为空且长度不能大于16"
                      style="visibility:hidden;color:darkred">密码不能为空且长度不能大于16</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            确认密码:
                        </td>
                        <td>
                            <input type="password" id="identifyUserPassword"/>
                        </td>
                        <td>
                            <span id="identifyPasswordMessage" type="text" value="两次密码不一致"
                                  style="visibility:hidden;color:darkred">两次密码不一致</span>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan=2>
                            <INPUT class=button id=sButton2 type=submit
                                   value="保存 " name=sButton2>
                        </td>
                    </tr>

                </form>
            </table>
        </td>
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
</BODY>
</html>
