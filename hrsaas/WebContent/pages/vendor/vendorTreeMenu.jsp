 
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../pages/common/dtree/dtree.js"></script>
<s:form name="paraFrm" id="paraFrm">


	<%
		String[][] twoDimObjArr = null;
		 
		try {
			twoDimObjArr = (String[][]) request
			.getAttribute("twoDimObjArr");
		 
		} catch (Exception e) {

		}
	%>


	<table border="0" width="150" height="100%" cellpadding="0"
		cellspacing="0">

		<tr>
			<td valign="top">


			<table border="0" width="100%" cellpadding="0" cellspacing="0"
				class="dashborder">
				<tr>
					<td width="100%" valign="top" align="left">

					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" valign="top" align="left">

							<table width="189" border="0" cellpadding="0" cellspacing="0">


								<tr>
									<td height="25" bgcolor="#f2f2f2">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img
												src="../pages/mypage/images/icons/34.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;Menu</td>
											<td width="22%">
											<div align="right">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="67%">
													<div align="right"></div>
													</td>
													<td width="33%">
													<div align="right"></div>
													</td>
												</tr>
											</table>
											<a href="javascript:void(0);" class="link"></a></div>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>

											</table>
					</td>
				</tr>

				<!--  -->
				<tr>
					<td valign="top">
					<%
					if (twoDimObjArr != null && twoDimObjArr.length > 1) {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<!--<tr>
							<td><strong class="dasheader">Menu List</strong></td>
						</tr>




						-->
						<tr>
							<td>
							<div style="width: 100%;">


							<div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td align="center" width="100%">
									<table width="97%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>


											<div>
											<div class='left_menu_bg' align="left">
											<table border="0" width="100%" height="100%">
												<tr>
													<td valign="top">

													

													<script type="text/javascript">
											d = new dTree('d');
											</script> <%
 if (twoDimObjArr != null && twoDimObjArr.length > 0) {
 %> <script>
											d.add('<%=twoDimObjArr[0][0]%>','-1','<%=twoDimObjArr[0][2]%>');
										</script> <%
 			int i = 0;
 			for (i = 1; i < twoDimObjArr.length; i++) {
 		Object test = twoDimObjArr[i][3];
 		if (twoDimObjArr[i][3].equals("")
 				|| twoDimObjArr[i][3].equals("null")) {
 %> <script type="text/javascript">
												d.add('<%=twoDimObjArr[i][0] %>','<%=twoDimObjArr[i][1] %>','<%=twoDimObjArr[i][2] %>','','','','../pages/common/dtree/img/folder.png','../pages/common/dtree/img/folder_open.png');
											</script> <%
 } else {
 %> <script type="text/javascript">
											d.add('<%=twoDimObjArr[i][0] %>','<%=twoDimObjArr[i][1] %>','<%=twoDimObjArr[i][2] %>','<%=twoDimObjArr[i][3] %>','<%=twoDimObjArr[i][4] %>','<%=String.valueOf(twoDimObjArr[i][5]) %>');
										</script> <%
 			}
 			}
 		}
 %> <script type="text/javascript">
											document.write(d);
										</script></td>
												</tr>
											</table>
										</div>
										</div>
										</td>
										</tr>

									</table>

									</td>
								</tr>
							</table>
							</div>
							<!--

							<div  style="width: 100%">&nbsp;</div>

						<div class="r4"></div>
						<div class="r3"></div>
						<div class="r2"></div>
						<div class="r1"></div></div>

							-->
							</td>
						</tr>
					</table>
					
					<%} %>

					</td>
				</tr>
				<!--  -->


			</table>
			</td>
		</tr>
	</table>
</s:form>