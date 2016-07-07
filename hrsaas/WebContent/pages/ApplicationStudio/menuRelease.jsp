<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" type="text/cs href=" ../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript" src="../pages/common/profileTree.js"></script>
<script type="text/javascript">
	var Tree = new Array;
	var mainMenu=0;
	</script>


<%
	String[][] temp = null;
	String[][] menuItems = null;
	try {
		menuItems = (String[][]) request.getAttribute("menuItems");
	} catch (Exception e) {
		System.out.println("Exception ---0");
	}

	try {
		int i;
		if (menuItems != null && menuItems.length > 0) {
			for (i = 0; i < menuItems.length; i++) {
%>
<script type="text/javascript">
					// nodeId | parentNodeId | nodeName | nodeUrl
		var value= '<%=menuItems[i][0] %>'+'|'+'<%=menuItems[i][1] %>'+'|'+'<%=menuItems[i][2] %>'+'|#';
		Tree[<%=i%>]  = value;
	</script>
<%
		}
		}
	} catch (Exception e) {
		System.out.println("Exception ---1");
	}
%>


<s:form action="MenuRelease" theme="simple" method="post" id="paraFrm">

	<s:hidden name="check" value="%{check}" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%"></td>
		</tr>
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Menu
					Release </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">



				<tr>
					<td>


					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="3"><img
								src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
						</tr>

						<tr valign="top">
							<td colspan="3" valign="top">
							<table width="97%" align="center" border="0" cellpadding="0"
								cellspacing="0" height="18">


								<s:hidden name="MenuRelease.profileId"
									value="%{MenuRelease.profileId}" />
								<tr>
									<td width="100%">
									<div id="tabnav" style="vertical-align: bottom">
									<ul>

										<%
												try {
												String[][] menuName1 = (String[][]) request
												.getAttribute("menuName1");
												for (int i = 0; i < menuName1.length; i++) {
										%>


										<li><a href="#" onclick="menuFun('<%=menuName1[i][0]%>')" id="<%=menuName1[i][0]%>">
										<span><%=menuName1[i][1]%> </span></a></li>
										<%
										}
										%>
									</ul>
									</div>
									<%
											} catch (Exception e) {
											System.out.println("Exception ---2");
										}
									%>
									
							</table>
							<label></label></td>
						</tr>



						<tr valign="top">
							<td colspan="3" valign="top">
							<table width="97%" align="center" border="0" cellpadding="0"
								cellspacing="0" class="formbg">

								<tr>
									<td>&nbsp;</td>
									<td width="100%">

									<div>

									<table width="100%">
										<tr>

											<td width="100%">
											<div>
											<table width="100%">
												<tr>
													<td colspan="4" height="22">
													<table width="100%" class="border1">

														<tr>
															<td width="30%" class="formth" align="center"><b>Menu
															List</b></td>
															<td class="formth" width="75" align="center"><b>Is
															Release</b></td>

														</tr>

													</table>
													<div
														style="height: 275px; width: 100%; overflow-y: auto; position: relative"
														bgcolor="#FFFFFF" align="center"><s:hidden
														name="selMenuCode" value="%{selMenuCode}" id="selMenuCode"></s:hidden>
													<div class="tree"><script type="text/javascript">
								createTree(Tree);
								try{
								var menuCode=document.getElementById('selMenuCode').value;
								//alert('menuCode----'+menuCode);
								}catch(e){
								alert(e);
								}
								oc(menuCode,'1');
							</script></div>
											</table>
											</div>
											<s:if test="isTotal">
										<tr>
											<td width="100%" align="center" class="border1"><s:submit
												cssClass="save" action="MenuRelease_create"
												value="      Save" onclick="return callSave();" /></td>
											<td>&nbsp;</td>
										</tr>
                                    </s:if>



										<%
												try {
												String[][] menuFlags = (String[][]) request
												.getAttribute("menuFlags");
												System.out.println("Menu flags *******************"
												+ menuFlags.length);
												int j = 0;
												for (j = 0; j < menuItems.length; j++) {
													int k = 0;
													for (k = 0; k < menuFlags.length; k++) {

												if (menuItems[j][0].equals(menuFlags[k][0])) {
										%>
										<%
										if (String.valueOf(menuFlags[k][1]).equals("Y")) {
										%>
										<script>
				document.getElementById('insert<%=String.valueOf(menuItems[j][0])%>').checked =true;
		</script>
										<%
										}
										%>



										<%
													}
													}
												}
											} catch (Exception e) {
												System.out.println("Exception ---3");
												e.printStackTrace();
											}
										%>



									</table>
									</div>
									</td>
								</tr>
							</table>
							<label></label></td>
						</tr>





					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>

<script>
if(document.getElementById('selMenuCode').value!="")
	document.getElementById(document.getElementById('selMenuCode').value).className="on";

function retData(){

document.getElementById('paraFrm').action=MenuRelease_retData;	
//document.getElementById('paraFrm').submit();		
return true;
}

function hasParentNode(chilNode,flagType) {

	
	for (i=0; i<nodes.length; i++) {
		var nodChild = nodes[i].split("|");
		 if (nodChild[0] == chilNode) {
		//	alert(chilNode);
			if( nodChild[1] > 0){
			//	alert(nodChild[1]);
				if(document.getElementById(flagType+chilNode).checked){
					document.getElementById(flagType+nodChild[1]).checked = true;
				}else{
					//document.getElementById('insert'+nodChild[1]).checked = false;
				}
				hasParentNode(nodChild[1],flagType) ;
			}  //END OF 2ND IF
		
		} 
	}
	
//	return false;
}


function checkNode(parent,flagType) {


	for (var i = 0; i < nodes.length; i++) {
		
		var nodeVal= nodes[i].split("|");
		if (nodeVal[1] == parent) {
			if(document.getElementById(flagType+parent).checked){
				document.getElementById(flagType+nodeVal[0]).checked=true;
				var hcn	= hasChild(nodeVal[0],flagType);
				if(hcn){
					checkNode(nodeVal[0],flagType);
				}
			}else{
				document.getElementById(flagType+nodeVal[0]).checked=false;
				var hcn	= hasChildFalse(nodeVal[0],flagType);
				if(hcn){
					checkNode(nodeVal[0],flagType);
				}
			}
		}
		
	}
}

function hasChild(newNode,flagType) {

	for (i=0; i< nodes.length; i++) {
		var nodeVal = nodes[i].split("|");
		if (nodeVal[1] == newNode){ 
			document.getElementById(flagType+newNode).checked = true;
			return true;
		}
	}
	return false;
}

function hasChildFalse(newNode,flagType) {

	for (i=0; i< nodes.length; i++) {
		var nodeVal = nodes[i].split("|");
		if (nodeVal[1] == newNode){ 
			document.getElementById(flagType+newNode).checked = false;
			return true;
		}
	}
	return false;
}

function callReport(name) {
			/*var profil= document.getElementById('profileBean.profileId').value ;
			if(profil==""){
					alert('Select profile for report');
					return false;
			}*/
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();							
			document.getElementById('paraFrm').target="main";
	}
	
	
	
	function menuFun(id){
		
		mainMenu=id;
		document.getElementById('selMenuCode').value=id;
		document.getElementById('paraFrm').action='MenuRelease_getMenuReleaseItems.action';	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main";
				
		return true;
	}
	
	
	
	



</script>
