<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<SCRIPT type="text/javascript">
	// 进行页面的跳转
	function to_page(pageNow){
		if(pageNow > ${page.totalPages} || pageNow <= 0){
			return;
		}
		if(pageNow){
			$("#pageJump").val(pageNow);
		}
		document.customerForm.submit();
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
        document.customerForm.submit();
    }


    function deleteCustomer(customerName,customerId) {
        var ret = confirm("确认要删除用户[" + customerName + "]吗?");
        if (ret) {
            window.location.href = "${pageContext.request.contextPath}/customer/deleteCustomer.action?custId=" + customerId;
        }
    }


</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer/list.action"
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
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
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
													<TD><INPUT class=textbox id=customer.customerBasic.custName
														style="IDTH: 80px" maxLength=50 name="customer.customerBasic.custName"></TD>
													<td>客户手机：</td>
													<td>
														<INPUT class=textbox id=customer.customerBasic.custPhone
															   style="WIDTH: 80px" maxLength=50 name="customer.customerBasic.custPhone">
													</td>
                                                    <td>联系人姓名：</td>
                                                    <td>
                                                        <INPUT class=textbox id=customer.customerBasic.custLinkman.linkName
                                                               style="WIDTH: 80px" maxLength=50 name="customer.customerBasic.custLinkman.linkName" value="${selectedLinkName}">
                                                    </td>
                                                    <TD>客户级别：</TD>
                                                    <TD>
                                                        <select name="customer.customerBasic.custLevel['key']">
                                                            <option value=""></option>
                                                            <c:forEach items="${customerLevel}" var="custLevel">
                                                                <option value="${custLevel['key']}" <c:if test="${custLevel['key'] eq selectedLevel}"> selected="selected" </c:if>  >${custLevel['value']}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </TD>
                                                    <TD>客户来源：</TD>
                                                    <TD>
													<select name="customer.customerBasic.custSource['key']">
                                                        <option value=""></option>
                                                        <c:forEach items="${customerSource}" var="custSource">
															<option value="${custSource['key']}" <c:if test="${custSource['key'] eq selectedSource}"> selected="selected" </c:if>  >${custSource['value']}</option>
                                                        </c:forEach>
                                                    </select>
                                                    </TD>
                                                    <TD>客户行业：</TD>
                                                    <TD>
                                                        <select name="customer.customerBasic.custIndustry['key']">
                                                            <option value=""></option>
                                                            <c:forEach items="${customerIndustry}" var="custIndustry">
                                                                <option value="${custIndustry['key']}" <c:if test="${custIndustry['key'] eq selectedIndustry}"> selected="selected" </c:if>  >${custIndustry['value']}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </TD>
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
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none; cursor:default">
													<TD onclick="sortByField('cust_name')">客户姓名</TD>
                                                    <TD onclick="sortByField('cust_sex')">客户性别</TD>
													<TD onclick="sortByField('level_name')">客户级别</TD>
													<TD onclick="sortByField('link_name')">联系人姓名</TD>
                                                    <TD onclick="sortByField('source_name')">客户来源</TD>
													<TD onclick="sortByField('industry_name')">客户行业</TD>
													<TD onclick="sortByField('cust_phone')">客户手机</TD>
													<%--<TD>客户地址</TD>--%>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${ customerBasics }" var="customerBasic">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none"
												>
													<TD>${customerBasic.custName }</TD>
                                                    <TD>${customerBasic.custSex}</TD>
													<TD>${customerBasic.custLevel['value'] }</TD>
													<TD>${customerBasic.custLinkman.linkName }</TD>
                                                    <TD>${customerBasic.custSource['value'] }</TD>
                                                    <TD>${customerBasic.custIndustry['value'] }</TD>
													<TD>${customerBasic.custPhone }</TD>
													<%--<TD>${customerBasic.custAddress }</TD>--%>
													<TD>
													<a href="${pageContext.request.contextPath }/customer/edit.action?custId=${customerBasic.custId}">修改</a>
													&nbsp;&nbsp;
													<%--<a href="${pageContext.request.contextPath }/customer/deleteCustomer.action?custId=${customerBasic.custId}  ">删除</a>--%>
													<a href="#" onclick="deleteCustomer('${customerBasic.custName}','${customerBasic.custId}')">删除</a>
													</TD>
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
</BODY>
</HTML>
