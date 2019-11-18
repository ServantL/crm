<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        function updateSubmit() {
            var phone = $("#linkPhone").val();
            if (/^[1][3,4,5,7,8][0-9]{9}$/.test(phone)) {
                if ('${linkman.linkPhone}' == phone) {
                    updateLinkman();
                    return;
                }
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/linkman/selectLinkmanIdByPhone.action',
                    data: {"phone":phone},
                    // async: false,
                    success: function (data) {
                        if (data == 'exist') {
                            alert("该手机号码已存在");
                        } else {
                            updateLinkman();
                        }
                    }
                });
            }else{
                alert("该手机号码格式不符")
                return false;
            }
        }

        function updateLinkman(){
            var params = $("#form1").serializeArray();
            var jsonData = [];
            for (var i = 0; i < params.length; i++) {
                var json = {};
                json.name = params[i].name;
                json.value = params[i].value;
                jsonData.push(json);
            }
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/linkman/updateLinkman.action',
                data: jsonData,
                success: function (data) {
                    if (data == 'success') {
                        window.location.href = '${pageContext.request.contextPath}/jsp/success.jsp';
                        window.top.top_frame.location.reload();
                    }
                }
            });
        }
    </script>


    <META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<FORM id=form1 name=form1
      action="${pageContext.request.contextPath }/linkman/updateLinkman.action"
      method=post>
    <input type="hidden" name="linkId" value="${linkman.linkId }"/>

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
                        <TD class=manageHead>当前位置：联系人管理 &gt; 修改联系人</TD>
                    </TR>
                    <TR>
                        <TD height=2></TD>
                    </TR>
                </TABLE>
                <TABLE cellSpacing=0 cellPadding=5 border=0>
                    <tr>
                        <td>联系人姓名：</td>
                        <td>
                            <input type="text" id="linkName" name="linkName" style="WIDTH: 180px"
                                   value="${linkman.linkName}"/>  <span style="color: red">*</span>
                        </td>
                        <td>联系人性别：</td>
                        <td>
                            <c:if test="${linkman.linkSex eq '男'}">
                                <input type="radio" name="linkSex" value="男" checked="checked">男
                                <input type="radio" name="linkSex" value="女">女
                            </c:if>
                            <c:if test="${linkman.linkSex eq '女'}">
                                <input type="radio" name="linkSex" value="男">男
                                <input type="radio" name="linkSex" value="女" checked="checked">女
                            </c:if>

                        </td>
                    </tr>
                    <TR>
                        <td>联系人手机：</td>
                        <td>
                            <INPUT class=textbox id="linkPhone"
                                   style="WIDTH: 180px" maxLength=50 name="linkPhone" value="${linkman.linkPhone}">  <span style="color: red">*</span>
                        </td>
                        <td>联系人邮箱：</td>
                        <td>
                            <input type="text" name="linkEmail" style="WIDTH: 180px" value="${linkman.linkEmail}"/>  <span style="color: red">*</span>
                        </td>
                    </TR>
                    <TR>
                        <td>联系人职位：</td>
                        <td>
                            <INPUT class=textbox id="linkPosition"
                                   style="WIDTH: 180px" maxLength=50 name="linkPosition"
                                   value="${linkman.linkPosition}">
                        </td>
                    </TR>
                    <TR>
                        <td>联系人简介 ：</td>
                        <td colspan="4">
									<textarea class=textbox id="linkMemo"
                                              style="OVERFLOW:hidden;width:400px;height:200px"
                                              name="linkMemo">${linkman.linkMemo}</textarea>
                        </td>
                    </TR>
                    <tr>
                        <td rowspan=2>
                            <INPUT class=button id=sButton2 type=button onclick="updateSubmit()"
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
