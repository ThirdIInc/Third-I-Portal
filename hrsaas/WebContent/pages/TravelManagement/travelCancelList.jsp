<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>

<s:form action="TravelCancel" method="post" name="TravelCancel"
	id="paraFrm" theme="simple" target="main">

	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">


		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Cancel Form</strong></td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>

			<td>
			<table width="100%%" border="0" align="center" cellpadding="2" class=formbg
				cellspacing="2">
				<tr>
					<td height="27" class="formtxt"><s:hidden name="confStatus" />
					<s:hidden name="cancelStatus" /> <a
						href="TravelCancel_callAdminCancel.action?cancelStatus=X-N">Pending
					List</a> | <a
						href="TravelCancel_callAdminCancel.action?cancelStatus=E-K">Canceled
					List</a></td>
				</tr>
				<s:hidden name="apprflag" />
				<s:hidden name="listLength" />

			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<s:hidden name="cStatus" />
					<s:if test="cStatus">
						<td height="27" class="formtxt"><strong>Pending List</strong>
					</s:if>
					<s:else>
						<td height="27" class="formtxt"><strong>Canceled
						List</strong></td>
					</s:else>

				</tr>
				<tr>
					<td width="100%" colspan="5"><s:hidden name="hdPage"
						id="hdPage" value="%{hdPage}" /> <%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("pageNo");
					%>
					<table width="100%" cellpadding="2" cellspacing="2">
						<tr>
							<td width="98%" align="center">
							<%	if(pageNo != 1)
								{
							%> <a href="#" onclick="callPage('1');"> <img
								src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P')">
							<img src="../pages/common/img/previous.gif" width="10"
								height="10" class="iconImage" /> </a> <%	}
								if(totalPage <= 5)
								{
									if(totalPage == 1)
									{
							%> <b><u><%=totalPage%></u></b> <%		}
									else
									{
										for(int z = 1; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}
									}
								}
								else
								{
									if(pageNo == totalPage - 1 ||pageNo == totalPage)
									{									
										for(int z = pageNo - 2; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}
									}
									else if(pageNo <= 3)
									{
										for(int z = 1; z <= 5; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}						
									}
									else if(pageNo > 3)
									{
										for(int z = pageNo - 2; z <= pageNo + 2; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}					
									}
								}
								if(!(pageNo == totalPage))
								{
									if(totalPage == 0){}
									else
									{
							%> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
								src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>');"> <img
								src="../pages/common/img/last.gif" width="10" height="10"
								class="iconImage" /> </a> <%		}
								}
							%>
							</td>
						</tr>
					</table>
					</td>
				</tr>



				<tr>
					<td class="formtext">
					<table width="100%" class="" border="0" cellpadding="2"
						cellspacing="2">
						<tr>

							<td class="formth">Employee ID</td>
							<td class="formth">Employee Name</td>
							<td class="formth">Application Date</td>
							<td class="formth">Cancel</td>
							<s:iterator value="travelConf.travelConfList">
								<tr>
									<s:hidden name="empId" value="%{empId}" />
									<td width="15%" class="border2"><s:property
										value="tokenNo" /></td>
									<td width="50%" class="border2"><s:property
										value="empName" /></td>
									<td width="15%" class="border2"><s:property
										value="travelDate" /><s:hidden name="travelID" /></td>
									<td width="10%" nowrap="nowrap" class="border2"><input
										type="button" class="token" value=" Cancel "
										onclick="cancel('<s:property value="travelID"/>')" /></td>
								</tr>
							</s:iterator>
						</tr>
						<s:if test="cancelNoData">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
						</s:if>
					</table>
				</td>
				<%}
					catch(Exception e)
					{
				    	e.printStackTrace();
					}%>
				</tr>
			</table>
			</td>
			<s:hidden name="hiddenTravelId" />
		</tr>
	</table>

</s:form>

<script>

function confirm(travelId)
{
	document.getElementById("paraFrm_hiddenTravelId").value=travelId;
	document.getElementById("paraFrm").action="TravelCancel_save.action";
	document.getElementById("paraFrm").submit();	
}
function cancel(travelId)
{
        var statusFlag = "S"; 
		document.getElementById('paraFrm').action = "TravelAppMngt_travelCancel.action?travelAppFlag='false'&travelAppId ="+travelId+"&statusSch="+statusFlag;
		document.getElementById('paraFrm').submit();
}

function callPage(id)
	{

		if(id == 'P')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) - 1;
		}
		if(id == 'N')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) + 1;
		}
		document.getElementById('hdPage').value = id;
		document.getElementById('paraFrm').action = "TravelCancel_callAdminCancel.action";
		document.getElementById('paraFrm').submit();
	}
	
</script>