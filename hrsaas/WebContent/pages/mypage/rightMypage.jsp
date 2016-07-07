
<!--  style="background-color:#C2C2C2;-->
 <div id="mainTable" style="display: none;">
<table width="170" height="100%" border="0" cellspacing="0"
	cellpadding="0" bgcolor="#ffffff">


	<iframe id="rightframe" frameborder="0"
		style="vertical-align: top; float: left; border: 0px solid; color: red;"
		allowtransparency="yes"
		src="<%=request.getContextPath()%>/mypage/RightTileAction_input.action"
		scrolling="no" marginwidth="0" marginheight="0" vspace="0"
		name="rightframe" width="170px" height="350px"> </iframe>


</table>
</div>
 
<script>

 

if(navigator.appName!='Netscape')
 {
 frames[0].location =frames[0].location;
 }

function resizeRightPage(pixels){
	
	try{
	 
    pixels+=30;
 	// alert('before'+ document.getElementById('rightframe').style.height);
    document.getElementById('rightframe').style.height=pixels+"px";
    // alert('after'+ document.getElementById('rightframe').style.height);
    
    document.body.style.offsetHeight="0px";
    }
    catch(e)
    {
    	//alert("Error   "+e);
    }
}


if(window.screen.width>1024)
{
  
try{
document.getElementById('mainTable').style.display = '';
 //	alert(window.screen.width);
	}
	catch(e)
	{
		alert("Error---"+e);
	}
}
else{
	document.getElementById('mainTable').style.display='none';
}
</script>
