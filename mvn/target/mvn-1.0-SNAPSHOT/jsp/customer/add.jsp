<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <TITLE>添加客户</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
    <LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
          rel=stylesheet>

    <script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>

    <script type="text/javascript">

        // 刷新图片
        function refresh_picture() {
            var formData = new FormData();
            formData.append("pictureFile", $("#pictureFile")[0].files[0]);
            if ($("#custPic").attr("src") != null) {
                formData.append("imgPath", $("#custPic").attr("src"));
            }
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/customer/refreshPicture.action',
                data: formData,
                cache: false,         //不设置缓存
                processData: false,  // 不处理数据
                contentType: false,  // 不设置内容类型
                success: function (data) {
                    console.log("/pic/${pageContext.request.contextPath}" + data);
                    $("#custPic").attr("src", "/pic/${pageContext.request.contextPath}/" + data);
                }
            });
        }

        function checkPhone() {
            var phone = $("#custPhone").val();
            if (/^[1][3,4,5,7,8][0-9]{9}$/.test(phone)) {
                var existPhone;
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/customer/selectCustomerIdByPhone.action',
                    data: {"phone": $("#custPhone").val()},
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
<c:if test="${messageErrors ne null}">
    <c:forEach items="${messageErrors}" var="messageError">
        <span style="color:gold">${messageError.defaultMessage}</span>
    </c:forEach>
</c:if>
<%--
    当改成用js提交表单之后，将错误信息显示在文本框中
    用返回的错误类看其发生错误的字段与文本框的id作比较，
    之后用js的技巧将其错误写到该文本框中
 --%>

<FORM enctype="multipart/form-data" id=form1 name=form1 onsubmit="return checkPhone()"
      action="${pageContext.request.contextPath }/customer/insertCustomer.action"
      method=post>
    <input type="hidden" name="customerBasic.custVisitTime" value="0"/>

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
                        <TD class=manageHead>当前位置：客户管理 &gt; 添加客户</TD>
                    </TR>
                    <TR>
                        <TD height=2></TD>
                    </TR>
                </TABLE>

                <div style="float:right;text-align: center;margin-right: 50px">
                    <div style="align-content:space-around;">
                        <div>
                            <img id="custPic" height="200px" width="200px"> </img>
                            <%--<input type="hidden" name="customerDetail.custPic" value="${customer.customerDetail.custPic}">--%>
                        </div>
                        <div style="padding-top:10px"><input id="pictureFile" type="file" name="pictureFile"
                                                             onchange="refresh_picture()"/></div>
                    </div>
                    <div style="padding-top:10px;font-size:18px ">客户资质图片</div>
                </div>
                <TABLE cellSpacing=0 cellPadding=5 border=0>
                    <TR>
                        <td>客户名称：</td>
                        <td>
                            <INPUT class=textbox id="customerBasic.custName"
                                   style="WIDTH: 180px" maxLength=50 name="customerBasic.custName">  <span style="color: red">*</span>
                        </td>
                        <td>客户性别：</td>
                        <td>
                            <input type="radio" id="customerBasic.custSex" name="customerBasic.custSex" value="男"
                                   checked="checked">男
                            <input type="radio" id="customerBasic.custSex" name="customerBasic.custSex" value="女">女

                            <%--<select id="customerBasic.custSex" name="customerBasic.custSex">--%>
                            <%--<option value="男" selected="selected">男</option>--%>
                            <%--<option value="女">女</option>--%>
                            <%--</select>--%>
                            <%--<INPUT class=textbox id=sChannel2--%>
                            <%--style="WIDTH: 180px" maxLength=50 name="customerBasic.custSex">--%>
                        </td>
                    </TR>

                    <TR>
                        <td>客户级别：</td>
                        <td>
                            <%--<INPUT class=textbox id="customerBasic.custLevel"--%>
                            <%--style="WIDTH: 180px" maxLength=50 name="customerBasic.custLevel" >--%>
                            <select name="customerBasic.custLevel['key']">
                                <c:forEach items="${customerLevel}" var="custLevel">
                                    <option value="${custLevel['key']}">${custLevel['value']}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>客户联系人：</td>
                        <td>
                            <select id="customerBasic.custLinkman.linkId" name="customerBasic.custLinkman.linkId" <c:if
                                    test="${sessionScope.user.userLinkman ne null}"> onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;" </c:if>>
                                <c:forEach items="${linkmanNameId}" var="linkman">
                                    <c:choose>
                                        <c:when test="${linkman.value eq sessionScope.user.userLinkman.linkId}">
                                            <option value="${linkman.value}" selected="selected">${linkman.key}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${linkman.value}">${linkman.key}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <%--<INPUT class=textbox id=sChannel2--%>
                            <%--style="WIDTH: 180px" maxLength=50 name="customerBasic.custLinkman.linkName">--%>
                        </td>
                    </TR>
                    <TR>


                        <td>客户手机：</td>
                        <td>
                            <INPUT class=textbox id="custPhone"
                                   style="WIDTH: 180px" maxLength=50 name="customerBasic.custPhone"
                                   value="${visitPhone}" <c:if
                                    test="${visitPhone ne null && visitPhone ne ''}"> readonly="readonly" </c:if> />  <span style="color: red">*</span>
                        </td>
                        <td>客户信息来源 ：</td>
                        <td>
                            <%--<INPUT class=textbox id="customerBasic.custSource"--%>
                            <%--style="WIDTH: 180px" maxLength=50 name="customerBasic.custSource" >--%>
                            <select name="customerBasic.custSource['key']">
                                <c:forEach items="${customerSource}" var="custSource">
                                    <option value="${custSource['key']}">${custSource['value']}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </TR>

                    <TR>
                        <td>客户联系地址 ：</td>
                        <td>
                            <INPUT class=textbox id="customerDetail.custAddress"
                                   style="WIDTH: 180px" maxLength=50 name="customerDetail.custAddress">  <span style="color: red">*</span>

                        </td>
                        <td>客户所属行业 ：</td>
                        <td>
                            <%--<INPUT class=textbox id="customerBasic.custIndustry"--%>
                            <%--style="WIDTH: 180px" maxLength=50 name="customerBasic.custIndustry" >--%>
                            <select name="customerBasic.custIndustry['key']">
                                <c:forEach items="${customerIndustry}" var="custIndustry">
                                    <option value="${custIndustry['key']}">${custIndustry['value']}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </TR>
                    <TR>
                        <td>客户地区 ：</td>
                        <td>
                            <INPUT class=textbox id="customerDetail.custRegion"
                                   style="WIDTH: 180px" maxLength=50 name="customerDetail.custRegion">
                        </td>
                        <td>客户邮政编码 ：</td>
                        <td>
                            <INPUT class=textbox id="customerDetail.custZip"
                                   style="WIDTH: 180px" maxLength=50 name="customerDetail.custZip">
                        </td>
                    </TR>

                    <TR>
                        <td>客户网址 ：</td>
                        <td>
                            <INPUT class=textbox id="customerDetail.custWebsite"
                                   style="WIDTH: 180px" maxLength=50 name="customerDetail.custWebsite">
                        </td>
                        <td>客户营业执照注册号 ：</td>
                        <td>
                            <INPUT class=textbox id="customerDetail.custLicence"
                                   style="WIDTH: 180px" maxLength=50 name="customerDetail.custLicence">
                        </td>
                    </TR>

                    <TR>
                        <td>企业法人 ：</td>
                        <td>
                            <INPUT class=textbox id="customerDetail.custCorporation"
                                   style="WIDTH: 180px" maxLength=50 name="customerDetail.custCorporation">
                        </td>
                        <td>客户注册资金 ：</td>
                        <td>
                            <INPUT class=textbox id="customerDetail.custCapital"
                                   style="WIDTH: 180px" maxLength=50 name="customerDetail.custCapital">
                        </td>
                    </TR>

                    <TR>
                        <td>开户银行即账号 ：</td>
                        <td>
                            <INPUT class=textbox id="customerDetail.custBank"
                                   style="WIDTH: 180px" maxLength=50 name="customerDetail.custBank">
                        </td>
                    </TR>

                    <TR>
                        <td>客户简介 ：</td>
                        <td colspan="4">
									<textarea class=textbox id="customerDetail.custMemo"
                                              style="OVERFLOW:hidden;width:400px;height:200px"
                                              name="customerDetail.custMemo"></textarea>
                        </td>
                    </TR>
                    <tr>
                        <td>
                            <INPUT class=button id=sButton2 type=submit
                                   value=" 添加 " name=sButton2>
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
