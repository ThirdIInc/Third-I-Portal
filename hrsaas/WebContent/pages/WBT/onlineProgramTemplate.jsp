 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="org.paradyne.lib.Utility"%>

<%
	 
	String programInstructions = "";
	try {

 
		programInstructions = (String) request
		.getAttribute("programInstructions");

	} catch (Exception e) {
		e.printStackTrace();
	}
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>DECISIONONE A GLODYNE COMPANY</title>
<META http-equiv="Page-Enter" CONTENT="RevealTrans(Duration=4,Transition=undefined)">
<link href="images/skin.css" rel="stylesheet" type="text/css" />




<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url();
	background-repeat: repeat-x;
	
}


-->
</style>
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
</head>

<body>
<s:form action="OnlineProgram" validate="true" id="paraFrm"
	theme="simple">
  <table width="909" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
    <tr>
      <td width="301" height="70" valign="middle"><img src="../pages/WBT/images/logo.gif" width="324" height="57" /></td>
      <td width="608" valign="bottom"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="25"><div align="right" class="apphead">Web Based Training Portal</div>
          </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td colspan="2" bgcolor="#EC7521" height="4px"></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><table width="100%" height="320" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="895" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="2">
                <tr>
                  <td width="12%" class="appheader" nowrap="nowrap">Program Name  </td>
                  <td width="60%" class="heading1"><s:property
						value="programName" /><s:hidden name="programCode" /> </td>
				<td width="28%"  nowrap="nowrap" align="right">
				<!--<input type="button" value="Techportal Home" style="cursor: pointer;"
				title="Techportal-Home"
				name="btnTechportal" onclick="callTechPortal();"/>
			--><!-- <font color="red">Please do not press back or refresh button.</font> -->	</td>		
                  </tr>
              </table></td>
            </tr>
            <tr>
               <td colspan="2"><hr /></td>
            </tr>
            
            <tr>
              <td class="apphead"><span class="text"><%=Utility.checkNull(programInstructions)%>
              </span></td>
            </tr>
            <%if(!Utility.checkNull(programInstructions).equals("")){ %>
            <tr>
               <td colspan="2"><hr /></td>
            </tr>
            <%} %>
            <tr>
              <td class="heading">&nbsp;</td>
            </tr>
            
            <tr>
              <td><table width="100%" border="0" cellspacing="2" cellpadding="2" id="tableOrderId">
                <tr>
                  <td width="9%" height="27" bgcolor="#F2f2f2"><span class="text">Sr.No.</span></td>
                  <td width="35%" bgcolor="#F2f2f2"><span class="text">Module Name </span></td>
                  <td width="16%" bgcolor="#F2f2f2"><span class="text">Completion Status</span></td>
                 <!-- <td width="22%" bgcolor="#F2f2f2" class="text" align="center">Marks Obtained(%)  </td>-->
                  <td width="22%" bgcolor="#F2f2f2" class="text" align="center">Attempts Remaining</td>
                  <td width="18%" bgcolor="#F2f2f2" class="text" align="center">Result</td>
                </tr>
                
               
                	<%
                	int count=1;
				 	int i=0;
				%>
				<s:iterator value="moduleList">
                <tr>
                  <td class="text" align="center"><%=count++%></td>
                  <td class="text"><table width="128" height="18" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="174" nowrap="nowrap">
                        <input type="hidden" name="moduleNameItt"  value="<s:property value="moduleNameItt"/>" id="moduleNameItt<%=i%>" />
                        <s:property value="moduleNameItt"/>
                        </td>
                        <td width="26"><img src="../pages/WBT/images/play.png" style="cursor: pointer;"
                        title="<s:property value="moduleNameItt"/>"
                        	onclick="return callModule('<s:property value="moduleCodeItt"/>','<s:property value="completionStatusItt"/>','<%=i%>','<s:property value="attemptsRemaining"/>');"
                        width="16" height="16" /></td>
                      </tr>
                    </table></td>
                  <td class="text" align="center"><s:property value="completionStatusItt"/>
				
				<input type="hidden" name="completionStatusItt" id="completionStatusItt<%=i%>" value="<s:property value="completionStatusItt"/>" />					
									
									</td>
                  <!-- <td class="text" align="center"> <s:property value="moduleMarksItt"/></td>-->
                  
                  <td class="text" align="center"> <s:property value="attemptsRemaining"/></td>
                  
                  <td class="text" align="center"><s:property value="moduleResultItt"/>
							<input type="hidden" name="isOrderRequired"  id="isOrderRequired<%=i%>" value="<s:property value="isOrderRequired"/>"/>
							
									
									</td>
                </tr>
                <%i++; %>
                </s:iterator>
                
             
                
              
              </table></td>
            </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" height="4px"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    
    <tr>
      <td colspan="2"><hr /></td>
    </tr>
    <tr>
      <td height="25" colspan="2"><div align="center" class="text">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><div align="left">&reg;All Rights Reserved &copy;Copyright</div></td>
            <td><div align="right"><a href="javascript:void(0);" class="link" onclick="MM_openBrWindow('http://tpstaging.decisionone.com/techportalgolive/terms.html','Terms','width=700,height=400,scrollbars=yes')">Terms &amp; Conditions</a> | <a href="javascript:void(0);" class="link" onclick="MM_openBrWindow('http://tpstaging.decisionone.com/techportalgolive/privacypolicy.html','Privacy','width=700,height=400,scrollbars=yes')">Privacy Policy</a></div></td>
          </tr></table>
        <div align="right"></div><table width="100%" border="0" cellspacing="0" cellpadding="0">
        </table>
        </div></td>
    </tr>
  </table>
  
  	<s:iterator value="randomQueList">
			<s:hidden name="randomQuesCodes" />
		</s:iterator>
		
		
				<s:hidden name="userCode" />
		<s:hidden name="applicationCode" />
		<s:hidden name="userType" />
		 
		 
</s:form>
</body>
</html>


<script>


function callTechPortal()
{

	document.getElementById('paraFrm').action='OnlineProgram_bactTotechportal.action';
 			document.getElementById('paraFrm').submit();

}

function callModule(moduleCode,testStatus,orderCode,attemptCount)
{
	
	 try{
	var table = document.getElementById('tableOrderId'); 
				var rowCount = table.rows.length; 	
				 
				var count=0;
				var isOrderRequired=document.getElementById('isOrderRequired'+orderCode).value;
				 
				if(isOrderRequired=='Y')
				{
				for(var i=0;i<rowCount-1;i++){
				var isOrderRequiredLoop=document.getElementById('isOrderRequired'+i).value;	
			  
					 if(isOrderRequiredLoop=='Y')
					 {
				 
					   moduleNameItt=document.getElementById('moduleNameItt'+i).value;
					 var completionStatusItt =document.getElementById('completionStatusItt'+i).value;
				 
					if(i==orderCode)
					{
					if(isOrderRequiredLoop=='Y'){
					break;
						}
					}
					else{
			 		if(isOrderRequiredLoop=='Y'){
					
					if( completionStatusItt=='Incompleted'||completionStatusItt=='-' ){
						alert("Please finish the "+moduleNameItt+" first then proceed" );
						return false;
					}
					
						
					}
				 
					 }
				 
					 }
					
					 
					
									
				}
				
				}
		 	
	
	}catch(e){ alert("Error "+e);}
	 
	try{
 
	var programCode =document.getElementById('paraFrm_programCode').value;
	document.getElementById('paraFrm').target='_parent';
			document.getElementById('paraFrm').action='OnlineProgram_callModule.action?moduleCodeStr='+moduleCode+'&programCodeStr='+programCode+'&sectionCodeStr=null&from=programPage&testStatus='+testStatus+'&attemptCountStr='+attemptCount;
 			document.getElementById('paraFrm').submit();
	}catch(e)
	{
		alert("Error----"+e);
	}
	
}


 
</script>
 