<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <TITLE>客户列表</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
    <LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
          rel=stylesheet>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
    <SCRIPT type="text/javascript">
        // 进行页面的跳转
        function to_page(pageNow){
            if(pageNow > ${page.totalPages} || pageNow <= 0){
                return;
            }
            if(pageNow){
                $("#pageJump").val(pageNow);
            }
            document.visitForm.submit();
        }

        // 根据所点击的字段进行排序
        function sortByField(clickSortField) {
            $("#sortField").attr("value", clickSortField);
            var ascOrDescData = $("#ascOrDesc").val();
            if (ascOrDescData == "asc") {
                $("#ascOrDesc").attr("value", "desc");
            }else{
                $("#ascOrDesc").attr("value", "asc");
            }
            document.visitForm.submit();
        }


    </SCRIPT>

    <META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<FORM id="visitForm" name="visitForm"
      action="${pageContext.request.contextPath }/visit/list.action"
      method=post>
    <input type="hidden" id="ascOrDesc" name="page.ascOrDesc" value="${page.ascOrDesc}">
    <input type="hidden" id="sortField" name="page.sortField" value="${page.sortField}" >
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
                        <TD class=manageHead>当前位置：客户拜访管理 &gt; 客户拜访列表</TD>
                    </TR>
                    <TR>
                        <TD height=2></TD>
                    </TR>
                </TABLE>
                <TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
                       width="100%" align=center border=0>
                    <TBODY>
                    <TR>
                        <TD height=25>
                            <TABLE cellSpacing=0 cellPadding=2 border=0>
                                <TBODY>
                                <TR>
                                    <TD>查询条件：</TD>
                                    <TD>客户名称：</TD>
                                    <TD><INPUT class=textbox id=sChannel2
                                               style="WIDTH: 80px" maxLength=50 name="visit.visitCustomer.custName"></TD>
                                    <TD>客户手机：</TD>
                                    <TD><input class="textbox" name="visit.visitCustomer.custPhone"/> </TD>
                                    <TD>客户访问时间：</TD>
                                    <TD><input class=textbox name="visit.visitDate" id="visitDate" type="text" placeholder="请选择日期" autocomplete="off"/></TD>
                                    <TD>客户访问次数</TD>
                                    <TD><input class="textbox" name="visit.visitCustomer.custVisitTime" /></TD>
                                    <%--<TD>客户来源：</TD>--%>
                                    <%--<TD>--%>
                                        <%--<select name="customerBasic.custSource">--%>
                                            <%--<c:forEach items="${customerSource}" var="custSource">--%>
                                                <%--<option value="${custSource.value}">${custSource.key}</option>--%>
                                            <%--</c:forEach>--%>
                                        <%--</select>--%>
                                    <%--</TD>--%>
                                    <TD><INPUT class=button id=sButton2 type=submit
                                               value=" 筛选 " name=sButton2></TD>
                                </TR>
                                </TBODY>
                            </TABLE>
                        </TD>
                    </TR>

                    <TR>
                        <TD>
                            <TABLE id=grid
                                   style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
                                   cellSpacing=1 cellPadding=2 rules=all border=0>
                                <TBODY>
                                <TR
                                        style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none;cursor:default">
                                    <TD onclick="sortByField('cust_name')">客户姓名</TD>
                                    <TD onclick="sortByField('cust_sex')">客户性别</TD>
                                    <TD onclick="sortByField('cust_level')">客户级别</TD>
                                    <TD onclick="sortByField('link_name')">联系人姓名</TD>
                                    <TD onclick="sortByField('cust_phone')">客户手机</TD>
                                    <TD onclick="sortByField('visit_date')">客户访问时间</TD>
                                    <TD onclick="sortByField('cust_visit_time')">客户访问次数</TD>
                                </TR>
                                <c:forEach items="${ visits }" var="visit">
                                    <TR>
                                        <TD>${visit.visitCustomer.custName }</TD>
                                        <TD>${visit.visitCustomer.custSex}</TD>
                                        <TD>${visit.visitCustomer.custLevel['value'] }</TD>
                                        <TD>${visit.visitCustomer.custLinkman.linkName }</TD>
                                        <TD>${visit.visitCustomer.custPhone }</TD>
                                        <TD><fmt:formatDate value="${visit.visitDate}" pattern="yyyy-MM-dd HH:mm:ss"/></TD>
                                        <TD>${visit.visitCustomer.custVisitTime }</TD>
                                    </TR>
                                </c:forEach>

                                </TBODY>
                            </TABLE>
                        </TD>
                    </TR>

                    <TR>
                        <TD><SPAN id=pagelink>
											<DIV
                                                    style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${page.totalCount}</B>]条记录,[<B>${page.totalPages}</B>]页
												,每页显示
												<select name="page.pageSize" onchange="to_page(${page.pageNow})">

												<option value="5" <c:if test="${page.pageSize==5 }">selected</c:if>>5</option>
												<option value="8" <c:if test="${page.pageSize==8 }">selected</c:if>>8</option>
												</select>
												条
												[<A href="javascript:to_page(${page.pageNow-1})">前一页</A>]
												<B>${page.pageNow}</B>
												[<A href="javascript:to_page(${page.pageNow+1})">后一页</A>]
												到
												<input type="text" size="3" id="pageJump" name="page.pageNow" />
												页

												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
                    </TR>
                    </TBODY>
                </TABLE>
            </TD>
            <TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
                    src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
<SCRIPT type="text/javascript">

    laydate.render({
        elem: '#visitDate' //指定元素
    });

</SCRIPT>
</BODY>
</HTML>
