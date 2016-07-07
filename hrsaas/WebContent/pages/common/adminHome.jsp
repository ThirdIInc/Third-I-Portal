<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>


<script type="text/javascript"
	src="../pages/common/include/javascript/yui/glo.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/log.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/dom.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/event.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/yahoo.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/lang.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/connection.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/dragdrop.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/ygddlist.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/glo_3.js"></script>
<script type='text/javascript'
	src="../pages/common/modules/home/home.js"></script>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/mydashlet.js"></script>




<style type="text/css">
#menu1Container {
	visibility: hidden;
	left: 894px;
	top: 118px;
	overflow: hidden;
	z-index: 2
}

#menu1Container,#menu1Content {
	position: absolute;
	width: 86px;
	height: 150px;
	clip: rect(0, 86, 150, 0);
}
</style>

<style type="text/css">

#menu2Container {
	visibility: hidden;
	left: 894px;
	top: 138px;
	overflow: hidden;
	z-index: 2
}

#menu2Container,#menu2Content {
	position: absolute;
	width: 100px;
	height: 150px;
	clip: rect(0, 86, 150, 0);
}
</style>

<style type="text/css">
#menu3Container {
	visibility: hidden;
	left: 894px;
	top: 156px;
	overflow: hidden;
	z-index: 2
}

#menu3Container,#menu3Content {
	position: absolute;
	width: 86px;
	height: 150px;
	clip: rect(0, 86, 150, 0);
}
</style>

<style type="text/css">
#menu4Container {
	visibility: hidden;
	left: 894px;
	top: 195px;
	overflow: hidden;
	z-index: 2
}

#menu4Container,#menu4Content {
	position: absolute;
	width: 86px;
	height: 150px;
	clip: rect(0, 86, 150, 0);
}
</style>

<style type="text/css">
#menu5Container {
	visibility: hidden;
	left: 894px;
	top: 214px;
	overflow: hidden;
	z-index: 2
}

#menu5Container,#menu5Content {
	position: absolute;
	width: 86px;
	height: 150px;
	clip: rect(0, 86, 150, 0);
}
</style>

<style type="text/css">
#menu6Container {
	visibility: hidden;
	left: 894px;
	top: 175px;
	overflow: hidden;
	z-index: 2
}

#menu6Container,#menu6Content {
	position: absolute;
	width: 86px;
	height: 150px;
	clip: rect(0, 86, 150, 0);
}
</style>
<s:form action="HomePage" id="paraFrm" theme="simple">
	<!--	




	<link rel="stylesheet"
	href="../pages/common/themes/sugar/skin.css"
	id="stylesheet" type="text/css">
	
		<link rel="stylesheet"
	href="../pages/common/themes/sugar/style.css"
	id="stylesheet" type="text/css">

-->

	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td width="70%" valign="top">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
			<td colspan="2">
			<script>
			
			dashlet('10','Services','400','1','1');	
			
			dashlet('0','My Mails','150','1','0');			
			
			dashlet('4','HrWork Communication','100','1','0');
			
			dashlet('1','Corporate Communication','100','1','0');
			</script>
			<tr>
			<td colspan="1">
			<script>
			dashlet('6','Corporate Information','140','1','0');
			</script>
			</td>	
			<td colspan="1">
			<script>
			dashlet('7','knowledge Information','140','1','0');
			</script>
			</td>
			</tr>
			</table>
				

			<td width="30%" valign="top">
				<script>
				dashlet('9','Value Services','400','1','0');
			</script>					
			
			</td>

			<td width="5%" valign="top" align="right">
			<s:include value="../pages/common/sideMenu.jsp" ></s:include>
			</td>
		</tr>
	</table>

	<script type="text/javascript">
SUGAR.sugarHome.maxCount = 	15;
dashletIds = ['0','1','2','3','4','5','6','7','8','9','10'];



dashletLoaders = ['<%=request.getContextPath()%>/common/HomePage_mailChkHome.action','<%=request.getContextPath()%>/common/HomePage_getCorpComm.action','<%=request.getContextPath()%>/common/HomePage_getPolls.action','<%=request.getContextPath()%>/common/HomePage_chat.action','<%=request.getContextPath()%>/common/HomePage_getBulletin.action','<%=request.getContextPath()%>/common/HomePage_getQuickSearch.action','<%=request.getContextPath()%>/common/HomePage_getCorpInfo.action','<%=request.getContextPath()%>/common/HomePage_getKnowInfo.action','<%=request.getContextPath()%>/common/HomePage_getGenInfo.action','<%=request.getContextPath()%>/pages/common/profile/admin_service.jsp','<%=request.getContextPath()%>/pages/common/profile/admin_service.jsp'];
</script>


	<script>
	function MM_openBrWindow(theURL,winName,features) { 
  window.open(theURL,winName,features);
}
	

	



var pollValue ;
  
  function callPoll(id){
  	pollValue= id.value;
  }
  
   function callForDetails(){ 
   
   text=document.getElementById('quickSearch').value;
 		   
 		   if(text==""){
		   alert('Please Enter Text and Select any one of the option');
		   return false;    
		   }
		   
			   if(pollValue=='e'){
			   
					   if(!(text==""))
										{ 
										
										var iChars = "`~!@#$%^&*()+=[]\\\';,/{}|\"<>?0123456789";
							  			for (var i = 0; i < text.length; i++)
							  			 {		  	
								  		if (iChars.indexOf(text.charAt(i)) != -1)
								  	 	{
									  	alert ("Please Enter Valid Employee Name ");
									  	return false;				  	
					  					   }
					  					}
									}
						if(!(text==""))
									{ 
									 
									var count =0;
									var iChars =" ";
						  			for (var i = 0; i < text.length; i++)
						  			 {		
						  			 
							  		if (iChars.indexOf(text.charAt(i))!= -1)
							  	 	{
							  	 	count=count+1; 
								  	
				  					   }
				  					}
				  					if(count==text.length){
				  					alert ("Blank Spaces Not Allowed");
				  					return false;	
				  					}
								}
					  		
		   
		  	    document.getElementById('paraId').value=document.getElementById('quickSearch').value;
				document.getElementById('paraFrm').action='../common/MISReport_search.action';	
		        document.getElementById("paraFrm").submit();
		   }
		   
		   if(pollValue=='b' || pollValue=='j'){

		       if(text=='') return true;
			   var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
			
					if(!(text.match(dateFormat)) || text.length<10){
						alert(" Date should be in DD-MM-YYYY format");
						document.getElementById(quickSearch).focus();
						return false;
					}
					var dateArray = text.split("-");
					var day   = dateArray[0];
					var month = dateArray[1];
					var year  = dateArray[2];
					
					if(day<1 || day>31){
						alert("Day "+day+" is not a valid day");
						document.getElementById(quickSearch).focus();
						return false;
					}
					
					if(month<1 || month>12){
						alert("Month "+month+" is not a valid month");
						document.getElementById(quickSearch).focus();
						return false;
					}
					
					if(day>29 && month==2){
						window.alert("Day "+day+" of the month "+month+" is not a valid day");
						document.getElementById(quickSearch).focus();
						return false;
					}
					
					
					if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
						window.alert("29th of February is not a valid date in "+year);
						document.getElementById(quickSearch).focus();
						return false;
					}
					
					if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
						window.alert("Day "+day+" of the month "+month+" is not a valid day");
						document.getElementById(quickSearch).focus();
						return false;
					}
			document.getElementById('paraId').value=document.getElementById('quickSearch').value;
			document.getElementById('paraFrm').action='../common/MISReport_search.action';	
		    document.getElementById("paraFrm").submit();				     
		   }		  
	}
	
	function callForAdvSearch() {		
		document.getElementById('paraFrm').action='../common/MISReport_input.action';
		document.getElementById("paraFrm").submit();
	}
</script>

	<script src="../pages/common/include/javascript/mybotdashlet.js"></script>

</s:form>