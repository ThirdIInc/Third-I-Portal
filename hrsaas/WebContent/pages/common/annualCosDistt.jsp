
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>

<%
	int nextYear = (Integer) request.getAttribute("nextYear");

	int previousYear = (Integer) request.getAttribute("previousYear");
	
	String str="aaaaaa";
%>
<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2" width="100%" valign="top">

		<table width="100%" border="0" align="left" cellpadding="0"
			cellspacing="0" bgcolor="#FFFFFF">


			<tr>
				<td>
				<div
					style="background-color: white; margin-bottom: 1em; padding: 10px">
				<div id="dept">
				<table bgcolor="#FFFFFF">
					<tr>
						<td><cewolf:chart id="verticalBar3D1" title=""
							type="verticalBar3D" xaxislabel='<%="Financial Year "+previousYear +"-"+nextYear %>' 
							yaxislabel="Net Salary (in Thousands)" showlegend="false">
							<cewolf:gradientpaint>
								<cewolf:point x="0" y="0" color="#FFFFFF" />
							</cewolf:gradientpaint>
							<cewolf:chartpostprocessor id="meterRanges">
							</cewolf:chartpostprocessor>
							<cewolf:data>
								<cewolf:producer id="categoryData1" />
							</cewolf:data>
						</cewolf:chart> <cewolf:img chartid="verticalBar3D1" renderer="/cewolf"
							width="650" height="245" /></td>
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


