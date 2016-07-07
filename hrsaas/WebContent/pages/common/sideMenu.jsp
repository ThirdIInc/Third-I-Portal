
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>

<div style="visibility: hidden;" id="menu1Container">
<div style="left: 86px;" id="menu1Content">
<div class="menu" height="17" width="50%" valign="center"
	onmouseout="ypSlideOutMenu.hideMenu('menu1')"
	onmouseover="ypSlideOutMenu.showMenu('menu1')" align="left"><script>
					dashlet('3','Chat Window ','160','1','1');
			</script></div>
</div>
</div>


<!-- End SubMenu1 -->
<!-- Begin Submenu3 -->
<div style="visibility: hidden;" id="menu2Container">
<div style="left: 86px;" id="menu2Content">
<div class="menu" valign="center"
	onmouseout="ypSlideOutMenu.hideMenu('menu2');"
	onmouseover="ypSlideOutMenu.showMenu('menu2');" align="left"><script>
					dashlet('8','General Information','160','1','1');
			</script></div>
</div>
</div>

<!-- End Submenu3 -->
<!-- Begin Submenu3 -->
<!-- End SubMenu1 -->
<!-- Begin Submenu3 -->
<div style="visibility: hidden;" id="menu3Container">
<div style="left: 86px;" id="menu3Content">
<div class="menu" height="17" width="106" valign="center"
	onmouseout="ypSlideOutMenu.hideMenu('menu3');"
	onmouseover="ypSlideOutMenu.showMenu('menu3');" align="left"><script>
			dashlet('5','Quick Search','160','1','1');
			</script></div>
</div>
</div>
<!-- End Submenu3 -->
<!-- Begin Submenu3 -->
<div style="visibility: hidden;" id="menu4Container">
<div style="left: 86px;" id="menu4Content">
<div class="menu" height="17" width="106" valign="center"
	onmouseout="ypSlideOutMenu.hideMenu('menu4');"
	onmouseover="ypSlideOutMenu.showMenu('menu4');" align="left"><script>
			dashlet('2','Opinion Poll','160','1','1');
			</script></div>
</div>
</div>
<!-- End Submenu3 -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

		<tr>
			<script language="javascript"
				src="../pages/common/include/javascript/ypSlideOutMenus.js"></script>
			<script language="javascript">
	 
	<!--
	  var yPosition = 5;
	  //alert(screen.width+","+document.body.clientWidth)
      if(screen.width==800)
	  { 	  	  
		var xposition=664;
		xposition = Math.round(xposition)			
      }
	  else
	  {	  
		//var xposition=890;
	  	var scrwdt;
	  //	alert(document.body.clientWidth)
	  	scrwdt = document.body.clientWidth;
		var xposition = 0;
		xposition = scrwdt - 450;		
		xposition = Math.round(xposition)
		//xposition=884;
		//alert(xposition)			
	  }
	  //new ypSlideOutMenu("number menu", "slide position", left, top, width, height)
		new ypSlideOutMenu("menu1", "left", xposition, yPosition, 400, 300)
		new ypSlideOutMenu("menu2", "left", xposition, yPosition , 400, 300)
		new ypSlideOutMenu("menu3", "left", xposition, yPosition , 400, 300)
		new ypSlideOutMenu("menu4", "left", xposition, yPosition , 400, 300)
		

	//-->
</script>



			<td valign="middle"><img src="../pages/common/t_files/chat.gif"
				onmouseover="ypSlideOutMenu.showMenu('menu1');"
				onmouseout="ypSlideOutMenu.hideMenu('menu1');"></td>
		<tr>
			<td><img src="../pages/common/t_files/general.gif"
				onmouseover="ypSlideOutMenu.showMenu('menu2');"
				onmouseout="ypSlideOutMenu.hideMenu('menu2');"></td>
		<tr>
			<td><img src="../pages/common/t_files/Search.gif"
				onmouseover="ypSlideOutMenu.showMenu('menu3');"
				onmouseout="ypSlideOutMenu.hideMenu('menu3');"></td>
		<tr>
			<td><img src="../pages/common/t_files/poll.gif"
				onmouseover="ypSlideOutMenu.showMenu('menu4');"
				onmouseout="ypSlideOutMenu.hideMenu('menu4');"></td>



		</tr>
	</tbody>
</table>