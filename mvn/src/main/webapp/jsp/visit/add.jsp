<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <TITLE>添加客户拜访</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
    <LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
          rel=stylesheet>

    <script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>

    <script type="text/javascript">

        // 获取要插入访问记录的客户信息
        function getVisitCustomer() {
            $("#visitCustomerTable").css("visibility", "hidden");
            var visitPhone = $("#visitPhone").val();
            if (/^[1][3,4,5,7,8][0-9]{9}$/.test(visitPhone)) {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/visit/getVisitCustomer.action',
                    data: {"visitPhone": visitPhone},
                    // dataType: 'json',
                    // cache: false,         //不设置缓存
                    // processData: true,  // 不处理数据
                    // contentType: false,  // 内容类型
                    success: function (data) {
                        if (data == null || data == '') {
                            // 如何没有数据则查询不到该客户，则该客户是第一次访问，提示是否以该手机号码进行添加客户
                            var con;
                            con = confirm("没有查询到对应的客户，是否以该手机添加新客户?"); //在页面上弹出对话框
                            if (con == true) {
                                window.location.href = "${pageContext.request.contextPath}/customer/add.action?visitPhone=" + visitPhone;
                            }
                            return;
                        }
                        $("#visitCustomerTable").css("visibility", "visible");
                        $("#custId").attr("value", data["custId"]);
                        $("#custName").attr("value", data["custName"]);
                        $("#custSex").attr("value", data["custSex"]);
                        $("#custLevel").attr("value", data["custLevel"]["value"]);
                        $("#custLinkman").attr("value", data["custLinkman"]["linkName"]);
                        $("#custSource").attr("value", data["custSource"]["value"]);
                        $("#custIndustry").attr("value", data["custIndustry"]["value"]);
                    }
                });
            } else {
                $("#phoneMessage").css("visibility", "visible");
            }
        }

        function getCurrentDate() {
            var date = new Date();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentDate = date.getFullYear() + "-" + month + "-" + strDate + " " + date.getHours()
                + ":" + date.getMinutes() + ":" + date.getSeconds();

            $("#visitDate").attr("value", currentDate);
        }
    </script>


    <META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY onload="getCurrentDate()">
<FORM id=form1 name=form1
      action="${pageContext.request.contextPath }/visit/insertVisit.action"
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
                        <TD class=manageHead>当前位置：客户拜访管理 &gt; 添加客户拜访</TD>
                    </TR>
                    <TR>
                        <TD height=2></TD>
                    </TR>
                </TABLE>

                <TABLE cellSpacing=0 cellPadding=5 border=0>
                    <tr>
                        <td>客户拜访手机：</td>
                        <td><input type="text" id="visitPhone" class="textbox" name="visitPhone" placeholder="请输入手机号"/>
                        </td>
                        <td>客户拜访时间：</td>
                        <td><input id="visitDate" class="textbox" name="visitDate" readonly="readonly"
                        /></td>
                        <td id="getVisitCustomer">
                            <INPUT class=button id=sButton1 type=button onclick="getVisitCustomer()"
                                   value=" 查询 " name=sButton2>
                        </td>
                        <td>
                            <span id="phoneMessage" type="text"
                                  style="visibility:hidden;color:darkred">手机格式错误</span>
                        </td>
                    </tr>
                    <table id="visitCustomerTable" cellSpacing=0 cellPadding=5 border=0 style="visibility:hidden">
                        <input type="hidden" id="custId" name="visitCustomer.custId"/>
                        <TR>
                            <td>客户名称：</td>
                            <td>
                                <INPUT class=textbox id="custName" disabled="disabled"
                                       style="WIDTH: 180px" maxLength=50 name="custName">
                            </td>
                            <td>客户性别：</td>
                            <td>
                                <input class=textbox type="text" id="custSex" name="custSex" disabled="disabled"
                                       checked="checked">
                            </td>
                        </TR>

                        <TR>
                            <td>客户级别</td>
                            <td>
                                <INPUT class=textbox id="custLevel" disabled="disabled"
                                       style="WIDTH: 180px" maxLength=50 name="custLevel">
                            </td>
                            <td>客户联系人：</td>
                            <td>
                                <INPUT class=textbox id="custLinkman" disabled="disabled"
                                       style="WIDTH: 180px" maxLength=50 name="custLinkman">
                            </td>
                        </TR>
                        <TR>
                            <td>客户信息来源 ：</td>
                            <td>
                                <INPUT class=textbox id="custSource" disabled="disabled"
                                       style="WIDTH: 180px" maxLength=50 name="custSource">
                            </td>
                            <td>客户所属行业 ：</td>
                            <td>
                                <INPUT class=textbox id="custIndustry" disabled="disabled"
                                       style="WIDTH: 180px" maxLength=50 name="custIndustry">
                            </td>
                        </TR>
                        <tr>
                            <td>
                                <INPUT class=button id=sButton2 type=submit
                                       value=" 添加 " name=sButton2>
                            </td>
                        </tr>
                    </table>
                </TABLE>


            </TD>
            <%--<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">--%>
            <%--<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>--%>
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
