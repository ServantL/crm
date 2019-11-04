<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>联系人列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<SCRIPT language=javascript>
	function to_page(pageNow){
		if(pageNow > ${page.totalPages} || pageNow <= 0){
			return;
		}
		if(pageNow){
			$("#pageJump").val(pageNow);
		}
		document.linkmanForm.submit();
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
		document.linkmanForm.submit();
	}

    function postRemoveCustomers(linkId) {
        var newLinkName = prompt("该联系人门下具有客户，如果要删除，先输入要将客户迁移到哪个联系人门下");
        if (newLinkName == null) {
            return false;
        }
        var del;
        $.ajax({
            type: 'post',
            url: '${pageContext.request.contextPath }/linkman/removeCustomers.action',
            data: {'linkId': linkId,'newLinkName': newLinkName},
            async:false,
            success: function (data) {
                del = data;
                if (data == "success") {
                    window.location.href = "${pageContext.request.contextPath}/jsp/success.jsp";
                    return false;
                }
            }
        });
        if (del == 'success') {
            window.location.href = "${pageContext.request.contextPath}/jsp/success.jsp";
            return false;
        }else{
            var con =  confirm("不存在联系人[" + newLinkName + "]，是否重新输入？"); //在页面上弹出对话框
            return con;
        }
    }

	function removeCustomers(linkId,linkName) {
		// 点击删除时，使用ajax异步请求
		// 查询该联系人门下有无客户，如果没有则弹窗confirm确定是否删除
		// 如果有客户，在弹窗prompt提示要将该联系人门下的所有客户迁移到哪个联系人门下
		// 点击后再使用ajax，先判断是否有输入的该联系人，再进行迁移，迁移后再删除该联系人

        // 因为传入的id必定能查询到该联系人，所以只需要判断其门下有没有客户
        var formData = new FormData();
        formData.append("linkId", linkId);
        $.ajax({
            type: 'post',
            url: '${pageContext.request.contextPath }/linkman/selectLinkmanById.action',
            data: {"linkId": linkId},
            success: function (data) {
                if (data == "have") {
                    // 如果有客户，则进行客户迁移
                    // 定义resume来确认是否需要重新进行提交
                    var resume = true;
                    // 循环有问题！！！！！！！！！！！！！！！！！！！！！！！！！
                    while (resume) {
                        resume = postRemoveCustomers(linkId);
                    }
                }else if(data == "no"){
                    // 如果没有客户，则进行确认是否删除
                    var con;
                    con = confirm("确认要删除联系人[" + linkName + "]吗?"); //在页面上弹出对话框
                    if (con == true) {
                        $.ajax({
                            type: 'post',
                            url: '${pageContext.request.contextPath }/linkman/deleteLinkman.action',
                            data: {"linkId": linkId},
                            success: function (data) {
                                if (data == 'success')
                                    window.location.href = "${pageContext.request.contextPath}/jsp/success.jsp";
                            }
                        });
                    }
                    return;
                }else{
					window.location.href = "${pageContext.request.contextPath}/jsp/rights.jsp";
				}
            }
        });


	}



</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="linkmanForm" name="linkmanForm"
		action="${pageContext.request.contextPath }/linkman/list.action"
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
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：联系人管理 &gt; 联系人列表</TD>
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
													<TD>联系人名称：</TD>
													<TD><INPUT class=textbox id="linkman.linkName"
															   style="WIDTH: 80px" maxLength=50 name="linkman.linkName"></TD>
													<TD>联系人手机：</TD>
													<TD><INPUT class=textbox id="linkman.linkPhone"
															   style="WIDTH: 80px" maxLength=50 name="linkman.linkPhone"></TD>
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
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none;cursor:default">
													<TD onclick="sortByField('link_name')">联系人姓名</TD>
													<TD onclick="sortByField('link_sex')">联系人性别</TD>
													<TD onclick="sortByField('link_phone')">联系人手机</TD>
													<TD onclick="sortByField('link_email')">联系人邮箱</TD>
													<TD onclick="sortByField('link_position')">联系人职位</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${ linkmans }" var="linkman">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${linkman.linkName }</TD>
													<TD>${linkman.linkSex }</TD>
													<TD>${linkman.linkPhone }</TD>
													<TD>${linkman.linkEmail }</TD>
													<TD>${linkman.linkPosition}</TD>
													
													<TD>
													<a href="${pageContext.request.contextPath }/linkman/edit.action?linkId=${linkman.linkId}">修改</a>
													&nbsp;&nbsp;
													<a href="#" onclick="removeCustomers('${linkman.linkId}','${linkman.linkName}')">删除</a>
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
