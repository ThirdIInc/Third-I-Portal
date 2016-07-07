<script>
//var tabCode=document.getElementById('hdMenuId').value;
</script>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form id="paraFrm" name="ThemeSettings" action="ThemeSettings"
	theme="simple" method="post">
	<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="top" class="txt">
			<table class="formbg" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Theme
					Setting </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" theme="simple"
						value="Apply Settings" class="token" onclick="showList();" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="10%" colspan="1"><label name="font.size"
						id="font.size" ondblclick="callShowDiv(this);"><%=label.get("font.size")%></label>
					:</td>
					<td width="15%" colspan="1"><s:select theme="simple"
						cssStyle="width:100px;" headerKey="11" headerValue="Default"
						list="#{'10':'-1','9':'-2','12':'+1','13':'+2'}" name="fontSize"
						onchange="showFontPreview();" /><label id="previewFontSize"></label></td>
					<td width="8%" colspan="1"><label name="font" id="font"
						ondblclick="callShowDiv(this);"><%=label.get("font")%></label> :</td>
					<td width="35%" colspan="1"><s:select theme="simple"
						list="#{'Arial':'Arial'
						,'Helvetica':'Helvetica'
						,'sans-serif':'sans-serif'
						,'Times New Roman':'Times New Roman'
						,'Georgia':'Georgia'
						,'Verdana':'Verdana'
						,'Geneva':'Geneva'
						,'Tahoma':'Tahoma'
						,'Century Gothic':'Century Gothic'
						,'Comic Sans MS':'Comic Sans MS'
						,'Courier New':'Courier New'
						,'Monotype Corsiva':'Monotype Corsiva'
						}"
						name="fontName" onchange="showFontPreview();" />&nbsp;&nbsp;&nbsp;<label
						style="background-color: FFFFFF; border: solid 1px; height: 20px; width: 100px; text-align: center"
						id="previewFont"></label></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>




			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><label name="themes" id="themes"
						ondblclick="callShowDiv(this);"><%=label.get("themes")%></label><s:hidden
						name="myTheme" id="myTheme" /></td>
				</tr>

				<tr>
					<td colspan="4">
					<div id="tabnav">
					<ul>
						<li><a id="sett_1"
							href="javascript:callDivLoad('sett_1', 'themeSetting_1');">
						<div align="center"><span>Corporate<sup><font
							color="#FF0000">&nbsp;</font></sup></span></div>
						</a></li>
						<li><a id="sett_2"
							href="javascript:callDivLoad('sett_2', 'themeSetting_2');">
						<div align="center"><span>Festival<sup><font
							color="#FF0000">New</font></sup></span></div>
						</a></li>

						<li><a id="sett_3"
							href="javascript:callDivLoad('sett_3', 'themeSetting_3');">
						<div align="center"><span>Places<sup><font
							color="#FF0000">&nbsp;</font></sup></span></div>
						</a></li>

						<li><a id="sett_4"
							href="javascript:callDivLoad('sett_4', 'themeSetting_4');">
						<div align="center"><span>WaterWorld<sup><font
							color="#FF0000">&nbsp;</font></sup></span></div>
						</a></li>

						<li><a id="sett_5"
							href="javascript:callDivLoad('sett_5', 'themeSetting_5');">
						<div align="center"><span> Fun<sup><font
							color="#FF0000">&nbsp;</font></sup> </span></div>
						</a></li>

						<li><a id="sett_6"
							href="javascript:callDivLoad('sett_6', 'themeSetting_6');">
						<div align="center"><span> Sports<sup><font
							color="#FF0000">&nbsp;</font></sup> </span></div>
						</a></li>

					</ul>
					</div>
					</td>
				</tr>

				<!-- Theme Setting _1 CORPORATE****    -->

				<tr height="200px">
					<td colspan="4">

					<div id="themeSetting_1" style="display: none; height: 400px">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="peoplePower" id="peoplePower"
								onclick="selectTheme('peoplePower');" />PeoplePower</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/ppower.jpg" /></td>

							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="globe" id="globe"
								onclick="selectTheme('globe');" />Globe</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/globe.jpg" /></td>
						</tr>

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="corp" id="corp"
								onclick="selectTheme('corp');" />Corporate</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/corp.jpg" /></td>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="jigsaw" id="jigsaw"
								onclick="selectTheme('jigsaw');" />JigSaw</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/jigsaw.jpg" /></td>
						</tr>
						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="cartoon" id="cartoon"
								onclick="selectTheme('cartoon');" />Business</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/cartoon.jpg" /></td>

							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="drop" id="drop"
								onclick="selectTheme('drop');" />Essence</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/drop.jpg" /></td>


						</tr>
					</table>

					</div>
					<!-- Theme Setting _2 FESTIVAL  -->
					<div id="themeSetting_2" style="display: none; height: 330px">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="diwali" id="diwali"
								onclick="selectTheme('diwali');" />Diwali</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/diwali.jpg" /></td>

							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="holi" id="holi"
								onclick="selectTheme('holi');" />Holi</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/holi.jpg" /></td>
						</tr>

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="dussehra" id="dussehra"
								onclick="selectTheme('dussehra');" />Dussehra</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/dussehra.jpg" /></td>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="diwali1" id="diwali1"
								onclick="selectTheme('diwali1');" />Diwali<sup><font
								color="#FF0000">New</font></sup></td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/diwali_icon.jpg" /></td>

						</tr>
						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="dhanteras" id="dhanteras"
								onclick="selectTheme('dhanteras');" />Dhanteras</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/dhanteras_icon.gif" /></td>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="merrychristmas" id="merrychristmas"
								onclick="selectTheme('merrychristmas');" />Merry Christmas</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/merrychristmas.jpg" /></td>


						</tr>
						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="newYear" id="newYear"
								onclick="selectTheme('newYear');" />New Year<sup><font
								color="#FF0000">New</font></sup></td>
							<td width="35%" colspan="3"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/newYearIcon.gif" /></td>
							


						</tr>

					</table>

					</div>



					<!-- Theme Setting _3 -->
					<div id="themeSetting_3" style="display: none; height: 330px">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="china" id="china"
								onclick="selectTheme('china');" />China Wall</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/chinawall.jpg" /></td>

							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="tajmahal" id="tajmahal"
								onclick="selectTheme('tajmahal');" />TajMahal</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/tajmahal.jpg" /></td>
						</tr>

					</table>
					</div>


					<!-- Theme Setting _4  WATERWORLD******** -->
					<div id="themeSetting_4" style="display: none; height: 330px">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="beach" id="beach"
								onclick="selectTheme('beach');" />Beach</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/beach.jpg" /></td>

							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="starfish" id="starfish"
								onclick="selectTheme('starfish');" />StarFish</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/starfish.jpg" /></td>
						</tr>

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="hawaii" id="hawaii"
								onclick="selectTheme('hawaii');" />Hawaii</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/hawaii.jpg" /></td>

							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="sagarkinare" id="sagarkinare"
								onclick="selectTheme('sagarkinare');" />Sunset Beach</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/sagarknare.jpg" /></td>
						</tr>

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="fishtank" id="fishtank"
								onclick="selectTheme('fishtank');" />Aquarium</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/fishtank.jpg" /></td>
							<td colspan="1">&nbsp;</td>
							<td colspan="1">&nbsp;</td>

						</tr>




					</table>
					</div>


					<!-- Theme Setting _5 --FUN-======   -->
					<div id="themeSetting_5" style="display: none; height: 330px">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="solitude" id="solitude"
								onclick="selectTheme('solitude');" />Solitude</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/solitude.jpg" /></td>

							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="insect" id="insect"
								onclick="selectTheme('insect');" />Beetle</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/insect.jpg" /></td>
						</tr>

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="love" id="love"
								onclick="selectTheme('love');" />CrabLove</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/love.jpg" /></td>

							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="mirchi" id="mirchi"
								onclick="selectTheme('mirchi');" />Mirchi</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/mirchi.jpg" /></td>
						</tr>

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="sheep" id="sheep"
								onclick="selectTheme('sheep');" />Sheep</td>
							<td width="35%" colspan="3"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/sheep.jpg" /></td>


						</tr>




					</table>
					</div>

					<!-- Theme Setting _6 SPORTS********* -->
					<div id="themeSetting_6" style="display: none; height: 330px">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">

						<tr>
							<td width="15%" colspan="1"><input type="radio"
								name="themeName" value="skyjumping" id="skyjumping"
								onclick="selectTheme('skyjumping');" />SkyJumping</td>
							<td width="35%" colspan="1"><img
								style="border: 1px solid #CCCCCC;"
								src="../pages/common/css/default/images/skyjumping.jpg" /></td>

							<td colspan="1">&nbsp;</td>
							<td colspan="1">&nbsp;</td>

						</tr>

					</table>
					</div>





					</td>
				</tr>









			</table>
			</td>







		</tr>
	</table>
</s:form>
<script>
var theme=document.getElementById('myTheme').value;
document.getElementById(theme).checked='true';
showFontPreview();
function showList()
{
//alert('theme'+theme);
//var seltheme=document.getElementById('paraFrm_themeName').value;
 //alert(seltheme);
	// alert(1);
	document.getElementById('paraFrm').action= 'ThemeSettings_applyTheme.action';
	// alert(2);
	document.getElementById("paraFrm").target="_parent";
	// alert(3);
	document.getElementById('paraFrm').submit();

//alert('theme'+theme);
}
function callLeftFrame(){

//alert('callLeftFrame');
	document.getElementById("paraFrm").action='<%=request.getContextPath()%>/common/menuAction_createMainMenu.action';
	document.getElementById("paraFrm").target="_parent";
	document.getElementById("paraFrm").submit();	
	
}

function callSelfFrame(){
var menuLink= '<%=request.getContextPath()%>/AppStudio/ThemeSettings_applyTheme.action'

    document.getElementById('hdMenuId').value=menuCode;
	document.getElementById("paraFrm").target="main";
	document.getElementById("paraFrm").action=menuLink;
	document.getElementById("paraFrm").submit();
}

function selectTheme(theme){
document.getElementById('myTheme').value=theme;
}
function showFontPreview()
{
	var fontText = document.getElementById('paraFrm_fontName').value;
	var fontSize = document.getElementById('paraFrm_fontSize').value;
	document.getElementById('previewFont').style.fontFamily=fontText;
	document.getElementById('previewFont').style.fontSize=fontSize;
	document.getElementById('previewFont').innerHTML='AaBbYyZz';
	
	
		document.getElementById('themeSetting_1').style.display = 'none';
		document.getElementById('themeSetting_2').style.display = 'none';
		document.getElementById('themeSetting_3').style.display = 'none';
		document.getElementById('themeSetting_4').style.display = 'none';
		document.getElementById('themeSetting_5').style.display = 'none';
		document.getElementById('themeSetting_6').style.display = 'none';	
	
	var theme=document.getElementById('myTheme').value;	
	if(theme=='peoplePower' || theme=='globe' || theme=='corp' || theme=='jigsaw' || theme=='cartoon' || theme=='drop' ){
	document.getElementById('themeSetting_1').style.display = '';
		document.getElementById('sett_1').className = 'on';
	}
	else if(theme=='diwali' || theme=='holi' || theme=='merrychristmas'||theme=='dussehra'||theme=='diwali1'|| theme=='dhanteras' || theme=='newYear'){
	document.getElementById('themeSetting_2').style.display = '';
	document.getElementById('sett_2').className = 'on';
	}
	
	else if(theme=='china' || theme=='tajmahal' ){
	document.getElementById('themeSetting_3').style.display = '';
	document.getElementById('sett_3').className = 'on';
	}
	else if(theme=='beach' || theme=='starfish' || theme=='hawaii' || theme=='sagarkinare' || theme=='fishtank' ){
	document.getElementById('themeSetting_4').style.display = '';
	document.getElementById('sett_4').className = 'on';
	}
	
	else if(theme=='solitude' || theme=='insect' || theme=='love' || theme=='mirchi' || theme=='sheep' ){
	document.getElementById('themeSetting_5').style.display = '';
	document.getElementById('sett_5').className = 'on';
	}
	else if(theme=='skyjumping'){
	document.getElementById('themeSetting_6').style.display = '';
	document.getElementById('sett_6').className = 'on';
	}
	
	
	
}




	
	function callOnLoad() {
		var divID = document.getElementById('paraFrm_divID').value;
		var aID = document.getElementById('paraFrm_aID').value;
		
		if(divID == '') {
			divID = 'attSettings';
		}
		
		if(aID == '') {
			aID = 'attSett';
		}
		
		document.getElementById('attSettings').style.display = 'none';
		document.getElementById('dailySettings').style.display = 'none';		
		document.getElementById(divID).style.display = '';
		
		document.getElementById('attSett').className = '';
		document.getElementById('dailySett').className = '';
		document.getElementById(aID).className = 'on';
	}

		function callDivLoad(aID, divID) {
		document.getElementById('themeSetting_1').style.display = 'none';
		document.getElementById('themeSetting_2').style.display = 'none';
		document.getElementById('themeSetting_3').style.display = 'none';
		document.getElementById('themeSetting_4').style.display = 'none';
		document.getElementById('themeSetting_5').style.display = 'none';
		document.getElementById('themeSetting_6').style.display = 'none';
		for(i=1;i<=6;i++)
			document.getElementById("sett_"+i).className = '';
		document.getElementById(aID).className = 'on';
		document.getElementById(divID).style.display = '';
	}



</script>
