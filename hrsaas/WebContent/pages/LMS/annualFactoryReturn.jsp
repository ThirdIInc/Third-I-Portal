<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<body>
<s:form action="ReturnAct" validate="true" id="paraFrm" validate="true" theme="simple">
<s:hidden name="previousPage" value="childlabour" />
<s:hidden name="pageToShow" value="" />
<s:hidden name="factoryAnnualReturnBean.returnID" value="1"/>
<s:hidden name="orgId" /><s:hidden name="frequency" />
<s:hidden name="fromPeriod" /><s:hidden name="toPeriod" />
<s:hidden name="orgName" />
	<table align="center" width="99%" class="formbg" border="0"
		cellspacing="2" cellpadding="0">
		<tr>
			<td valign="bottom" class="txt" colspan="2">
			<table width="100%" border="0" align="right" class="formbg" >
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Annual
					Factory Return </strong></td>
					
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="formtext" colspan="2">
			<table width="100%">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%">
					<div align="left"> <s:select
						name="actList" list="actMap" size="1" headerKey=""
						headerValue="--Select Act--" cssStyle="width:150" theme="simple" />
						<input type="submit" value="Go To"
						class="token" onclick="return goTo();">
						</div>
					</td>

				</tr>
			</table>
			</td>
		</tr>

		<tr class="clsTRBody">
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="2"
				style="border: 1px solid #565656;" >
				<tr>
					<td colspan="2" align="center">							
						<label class="set"	name="factory.heading.english"
								id="factory.heading.english"
								ondblclick="callShowDiv(this);"><%=label.get("factory.heading.english")%>
						</label><s:property value="fromPeriod" /> - <s:property value="toPeriod" />
						<br />
						<label class="set"	name="factory.heading.marathi1"
								id="factory.heading.marathi1"
								ondblclick="callShowDiv(this);"><%=label.get("factory.heading.marathi1")%>
						</label><s:property value="fromPeriod" /> - <s:property value="toPeriod" />
						<label class="set"	name="factory.heading.marathi2"
								id="factory.heading.marathi2"
								ondblclick="callShowDiv(this);"><%=label.get("factory.heading.marathi2")%>
						</label>
					</td>
				</tr>
			</table>
			 </td>
		</tr>


		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;" 
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="3">
					<label class="set"	name="factory.inspections"
								id="factory.inspections"
								ondblclick="callShowDiv(this);"><%=label.get("factory.inspections")%></label>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td width="3%">23</td>
					<td width="37%">
					<label class="set"	name="factory.date_of_the_last_inspection_by_a_factory_inspector"
								id="factory.date_of_the_last_inspection_by_a_factory_inspector"
								ondblclick="callShowDiv(this);"><%=label.get("factory.date_of_the_last_inspection_by_a_factory_inspector")%>
					</label>
					</td>
					<td width="60%">
						<s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="factoryInspectionsBean.lastInspectionDate" />
						<a href="javascript:NewCal('paraFrm_factoryInspectionsBean_lastInspectionDate','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>24</td>
					<td>
					<label class="set"	name="factory.date_of_the_last_spot_safety_audit_by_a_factory_inspector"
								id="factory.date_of_the_last_spot_safety_audit_by_a_factory_inspector"
								ondblclick="callShowDiv(this);"><%=label.get("factory.date_of_the_last_spot_safety_audit_by_a_factory_inspector")%>
					</label>
					</td>
					<td><s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="factoryInspectionsBean.lastSpotSafetyAuditDate" />
						<a href="javascript:NewCal('paraFrm_factoryInspectionsBean_lastSpotSafetyAuditDate','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a></td>
				</tr>
				<tr class="clsTRBody">
					<td>25</td>
					<td>
					<label class="set"	name="factory.date_of_the_last_safety_audit_conducted_by_an_internal_auditor"
								id="factory.date_of_the_last_safety_audit_conducted_by_an_internal_auditor"
								ondblclick="callShowDiv(this);"><%=label.get("factory.date_of_the_last_safety_audit_conducted_by_an_internal_auditor")%>
					</label>
					</td>
					<td><s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="factoryInspectionsBean.lastSafetyAuditDateInternal" />
						<a href="javascript:NewCal('paraFrm_factoryInspectionsBean_lastSafetyAuditDateInternal','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a></td>
				</tr>
				<tr class="clsTRBody">
					<td>26</td>
					<td>
					<label class="set"	name="factory.date_of_the_last_safety_audit_conducted_by_an_external_auditor"
								id="factory.date_of_the_last_safety_audit_conducted_by_an_external_auditor"
								ondblclick="callShowDiv(this);"><%=label.get("factory.date_of_the_last_safety_audit_conducted_by_an_external_auditor")%>
					</label>
					</td>
					<td><s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="factoryInspectionsBean.lastSafetyAuditDateExternal" />
						<a href="javascript:NewCal('paraFrm_factoryInspectionsBean_lastSafetyAuditDateExternal','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a></td>
				</tr>
				<tr class="clsTRBody">
					<td>27&nbsp;</td>
					<td>
					<label class="set"	name="factory.date_of_the_last_examination_by_a_competent_person"
								id="factory.date_of_the_last_examination_by_a_competent_person"
								ondblclick="callShowDiv(this);"><%=label.get("factory.date_of_the_last_examination_by_a_competent_person")%>
					</label>
					</td>
					<td><s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="factoryInspectionsBean.lastExamDateCompetentPerson" />
						<a href="javascript:NewCal('paraFrm_factoryInspectionsBean_lastExamDateCompetentPerson','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.examined_on_that_date"
								id="factory.examined_on_that_date"
								ondblclick="callShowDiv(this);"><%=label.get("factory.examined_on_that_date")%>
					</label>
					</td>
					<td><s:textarea name="factoryInspectionsBean.lastExamDetailsCompetentPerson" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.equipment_machinery_examined"
								id="factory.equipment_machinery_examined"
								ondblclick="callShowDiv(this);"><%=label.get("factory.equipment_machinery_examined")%>
					</label>
					</td>
					<td><s:textarea name="factoryInspectionsBean.examinedMachineryEquipment" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>28</td>
					<td>
					<label class="set"	name="factory.factory_hold_any_ISO_SA_or_other_similar_certification"
								id="factory.factory_hold_any_ISO_SA_or_other_similar_certification"
								ondblclick="callShowDiv(this);"><%=label.get("factory.factory_hold_any_ISO_SA_or_other_similar_certification")%>
					</label>
					</td>
					<td><s:checkbox name="factoryInspectionsBean.isFactoryCertified" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.last_date_of_certificate_renewal"
								id="factory.last_date_of_certificate_renewal"
								ondblclick="callShowDiv(this);"><%=label.get("factory.last_date_of_certificate_renewal")%>
					</label>
					</td>
					<td><s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="factoryInspectionsBean.lastRenewalDate" />
						<a href="javascript:NewCal('paraFrm_factoryInspectionsBean_lastRenewalDate','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a></td>
				</tr>
				<tr class="clsTRBody">
					<td>29</td>
					<td>
					<label class="set"	name="factory.code_of_conduct_as_required_by_buyers"
								id="factory.code_of_conduct_as_required_by_buyers"
								ondblclick="callShowDiv(this);"><%=label.get("factory.code_of_conduct_as_required_by_buyers")%>
					</label>
					</td>
					<td><s:checkbox name="factoryInspectionsBean.hasCodeOfConduct" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.last_date_of_inspection_by_a_buyer_or_buyers_representative"
								id="factory.last_date_of_inspection_by_a_buyer_or_buyers_representative"
								ondblclick="callShowDiv(this);"><%=label.get("factory.last_date_of_inspection_by_a_buyer_or_buyers_representative")%>
					</label>
					</td>
					<td><s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="factoryInspectionsBean.lastDateOfCOCInspection" />
						<a href="javascript:NewCal('paraFrm_factoryInspectionsBean_lastDateOfCOCInspection','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="3">Dangerous operations and hazardous processes /
					&#2343;&#2379;&#2325;&#2366;&#2342;&#2366;&#2351;&#2325;
					&#2325;&#2381;&#2352;&#2367;&#2351;&#2366; &#2310;&#2339;&#2367;
					&#2361;&#2366;&#2344;&#2367;&#2325;&#2366;&#2352;&#2325;
					&#2346;&#2381;&#2352;&#2325;&#2381;&#2352;&#2367;&#2351;&#2366;</td>
				</tr>
				<tr class="clsTRBody">
					<td>30</td>
					<td> 
					<label class="set"	name="factory.dangerous_operations_conducted_in_the_factory"
								id="factory.dangerous_operations_conducted_in_the_factory"
								ondblclick="callShowDiv(this);"><%=label.get("factory.dangerous_operations_conducted_in_the_factory")%>
					</label>
					</td>
					<td><s:textarea name="factoryAnnualReturnBean.operation" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td colspan=2><em><strong>Note :-</strong></em> D/D&nbsp;
					Dangerous Operations Schedule (Indicate all operations that apply
					if more than one). If none of the operations listed in the schedule
					are conducted, write NIL.<br />
					<em><strong>&#2335;&#2368;&#2346; :-</strong></em>
					&#2343;&#2379;&#2325;&#2366;&#2342;&#2366;&#2351;&#2325;
					&#2325;&#2371;&#2340;&#2368;
					&#2342;&#2352;&#2381;&#2358;&#2357;&#2367;&#2339;&#2366;&#2352;&#2375;
					&#2346;&#2352;&#2367;&#2358;&#2367;&#2359;&#2381;&#2336;
					(&#2319;&#2325;&#2366;&#2346;&#2375;&#2325;&#2381;&#2359;&#2366;
					&#2309;&#2343;&#2367;&#2325; &#2348;&#2366;&#2348;&#2368;
					&#2354;&#2366;&#2327;&#2370; &#2361;&#2379;&#2340;
					&#2309;&#2360;&#2354;&#2381;&#2351;&#2366;&#2360;
					&#2340;&#2360;&#2375; &#2344;&#2350;&#2370;&#2342;
					&#2325;&#2352;&#2366;&#2357;&#2375;).
					&#2346;&#2352;&#2367;&#2358;&#2367;&#2359;&#2381;&#2336;&#2366;&#2340;&#2368;&#2354;
					&#2351;&#2366;&#2342;&#2368;&#2346;&#2376;&#2325;&#2368;
					&#2325;&#2366;&#2361;&#2368;&#2361;&#2368;
					&#2354;&#2366;&#2327;&#2370;
					&#2344;&#2360;&#2354;&#2381;&#2351;&#2366;&#2360;
					&ldquo;&#2344;&#2367;&#2352;&#2306;&#2325;&rdquo;
					&#2309;&#2360;&#2375; &#2354;&#2367;&#2361;&#2366;&#2357;&#2375;.</td>
				</tr>
				<tr class="clsTRBody">
					<td>31</td>
					<td>
					<label class="set"	name="factory.processes_carried_out_in_the_factory"
								id="factory.processes_carried_out_in_the_factory"
								ondblclick="callShowDiv(this);"><%=label.get("factory.processes_carried_out_in_the_factory")%>
					</label>
					</td>
					<td><s:textarea name="factoryAnnualReturnBean.process" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td colspan=2><em><strong>Note :-</strong></em> D/D&nbsp;
					Hazardous processes schedule (Indicate all processes that apply if
					more than one). If none of the processes apply, write NIL.<br />
					<em><strong> &#2335;&#2368;&#2346; :-</strong></em>
					&#2343;&#2379;&#2325;&#2366;&#2342;&#2366;&#2351;&#2325;
					&#2346;&#2381;&#2352;&#2325;&#2381;&#2352;&#2367;&#2351;&#2366;
					&#2342;&#2352;&#2381;&#2358;&#2357;&#2367;&#2339;&#2366;&#2352;&#2375;
					&#2346;&#2352;&#2367;&#2358;&#2367;&#2359;&#2381;&#2336;
					(&#2319;&#2325;&#2366;&#2346;&#2375;&#2325;&#2381;&#2359;&#2366;
					&#2309;&#2343;&#2367;&#2325; &#2348;&#2366;&#2348;&#2368;
					&#2354;&#2366;&#2327;&#2370; &#2361;&#2379;&#2340;
					&#2309;&#2360;&#2354;&#2381;&#2351;&#2366;&#2360;
					&#2340;&#2360;&#2375; &#2344;&#2350;&#2370;&#2342;
					&#2325;&#2352;&#2366;&#2357;&#2375;).
					&#2346;&#2352;&#2367;&#2358;&#2367;&#2359;&#2381;&#2336;&#2366;&#2340;&#2368;&#2354;
					&#2351;&#2366;&#2342;&#2368;&#2346;&#2376;&#2325;&#2368;
					&#2325;&#2366;&#2361;&#2368;&#2361;&#2368;
					&#2354;&#2366;&#2327;&#2370;
					&#2344;&#2360;&#2354;&#2381;&#2351;&#2366;&#2360;
					&ldquo;&#2344;&#2367;&#2352;&#2306;&#2325;&rdquo;
					&#2309;&#2360;&#2375; &#2354;&#2367;&#2361;&#2366;&#2357;&#2375;.</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="3">Safety and health /
					&#2360;&#2369;&#2352;&#2325;&#2381;&#2359;&#2366;
					&#2310;&#2339;&#2367; &#2310;&#2352;&#2379;&#2327;&#2381;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td width="3%">32</td>
					<td width="38%">
					<label class="set"	name="factory.written_safety_and_health_policy"
								id="factory.written_safety_and_health_policy"
								ondblclick="callShowDiv(this);"><%=label.get("factory.written_safety_and_health_policy")%>
					</label>
			</td>
					<td width="59%"><s:checkbox name="factorySafetyHealthBean.hasWrittenPolicy" />
					Yes / &#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.policy_communicated_to_workers"
								id="factory.policy_communicated_to_workers"
								ondblclick="callShowDiv(this);"><%=label.get("factory.policy_communicated_to_workers")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.policyCommunicationMediaIDNotice" /> Notice
					board / &#2360;&#2370;&#2330;&#2344;&#2366; &#2347;&#2354;&#2325; 
					<s:checkbox	name="factorySafetyHealthBean.policyCommunicationMediaIDCircular" /> Circular /
					&#2346;&#2352;&#2367;&#2346;&#2340;&#2381;&#2352;&#2325; 
					<s:checkbox	name="factorySafetyHealthBean.policyCommunicationMediaIDOther" /> Other /
					&#2309;&#2344;&#2381;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.language_used"
								id="factory.language_used"
								ondblclick="callShowDiv(this);"><%=label.get("factory.language_used")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.policyCommunicationLanguageIDMarathi" /> Marathi /
					&#2350;&#2352;&#2366;&#2336;&#2368; 
					<s:checkbox name="factorySafetyHealthBean.policyCommunicationLanguageIDHindi"	/> Hindi /
					&#2361;&#2367;&#2306;&#2342;&#2368; 
					<s:checkbox name="factorySafetyHealthBean.policyCommunicationLanguageIDEnglish" /> English&nbsp; /
					&#2311;&#2306;&#2327;&#2381;&#2352;&#2332;&#2368;</td>
				</tr>
				<tr class="clsTRBody">
					<td>33</td>
					<td>
						<label class="set"	name="factory.written_safety_and_health_rules"
								id="factory.written_safety_and_health_rules"
								ondblclick="callShowDiv(this);"><%=label.get("factory.written_safety_and_health_rules")%>
					</label>
				</td>
					<td><s:checkbox name="factorySafetyHealthBean.hasWrittenRules" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
						<label class="set"	name="factory.safety_rules_communicated_to_workers"
								id="factory.safety_rules_communicated_to_workers"
								ondblclick="callShowDiv(this);"><%=label.get("factory.safety_rules_communicated_to_workers")%>
					</label>
					<td><s:checkbox name="factorySafetyHealthBean.ruleCommunicationMediaIDNotice" /> Notice
					board / &#2360;&#2370;&#2330;&#2344;&#2366; &#2347;&#2354;&#2325;
					<s:checkbox	name="factorySafetyHealthBean.ruleCommunicationMediaIDCircular" /> Circular /
					&#2346;&#2352;&#2367;&#2346;&#2340;&#2381;&#2352;&#2325; 
					<s:checkbox	name="factorySafetyHealthBean.ruleCommunicationMediaIDOther" /> Other /
					&#2309;&#2344;&#2381;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.rules_communication_language_used"
								id="factory.rules_communication_language_used"
								ondblclick="callShowDiv(this);"><%=label.get("factory.rules_communication_language_used")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.ruleCommunicationLanguageIDMarathi" /> Marathi /
					&#2350;&#2352;&#2366;&#2336;&#2368;
					<s:checkbox name="factorySafetyHealthBean.ruleCommunicationLanguageIDHindi"	/> Hindi /
					&#2361;&#2367;&#2306;&#2342;&#2368;
					<s:checkbox name="factorySafetyHealthBean.ruleCommunicationLanguageIDEnglish"	/> English&nbsp; /
					&#2311;&#2306;&#2327;&#2381;&#2352;&#2332;&#2368;</td>
				</tr>
				<tr class="clsTRBody">
					<td>34</td>
					<td>
					<label class="set"	name="factory.emergency_and_evacuation_plan"
								id="factory.emergency_and_evacuation_plan"
								ondblclick="callShowDiv(this);"><%=label.get("factory.emergency_and_evacuation_plan")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.hasEnEplan" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.evacuation_plan_displayed_throughout_the_factory"
								id="factory.evacuation_plan_displayed_throughout_the_factory"
								ondblclick="callShowDiv(this);"><%=label.get("factory.evacuation_plan_displayed_throughout_the_factory")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.enEPlanDisplayed" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.regular_evacuation_drills"
								id="factory.regular_evacuation_drills"
								ondblclick="callShowDiv(this);"><%=label.get("factory.regular_evacuation_drills")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.regularEvacuationDrills" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.date_of_the_last_evacuation_drill"
								id="factory.date_of_the_last_evacuation_drill"
								ondblclick="callShowDiv(this);"><%=label.get("factory.date_of_the_last_evacuation_drill")%>
					</label>
					</td>
					<td><s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="factorySafetyHealthBean.lastDateOfEvacuationDrill" />
						<a href="javascript:NewCal('paraFrm_factorySafetyHealthBean_lastDateOfEvacuationDrill','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a></td>
				</tr>
				<tr class="clsTRBody">
					<td>35</td>
					<td>
						<label class="set"	name="factory.factory_have_safety_officers"
								id="factory.factory_have_safety_officers"
								ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_safety_officers")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.hasSafetyOfficers" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
						<label class="set"	name="factory.how_many_as_at_reporting_date"
								id="factory.how_many_as_at_reporting_date"
								ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_as_at_reporting_date")%>
					</label>
					</td>
					<td><s:textfield name="factorySafetyHealthBean.noOfSO" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.qualified_Safety_Officer_as_per_Rules"
								id="factory.qualified_Safety_Officer_as_per_Rules"
								ondblclick="callShowDiv(this);"><%=label.get("factory.qualified_Safety_Officer_as_per_Rules")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.noOfQualifiedSO" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>36</td>
					<td>
					<label class="set"	name="factory.factory_have_a_safety_committee"
								id="factory.factory_have_a_safety_committee"
								ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_a_safety_committee")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.hasSafetyCommittee" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td colspan=2>If YES :- / &#2313;&#2340;&#2381;&#2340;&#2352;
					&#2361;&#2379;&#2325;&#2366;&#2352;&#2366;&#2352;&#2381;&#2341;&#2368;
					&#2309;&#2360;&#2354;&#2381;&#2351;&#2366;&#2360; :-&nbsp;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.how_many_worker_members"
								id="factory.how_many_worker_members"
								ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_worker_members")%>
					</label>
					</td>
					<td><s:textfield name="factorySafetyHealthBean.sCWorkerMembers"  />
					&nbsp;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.manager_representatives_are_members"
								id="factory.manager_representatives_are_members"
								ondblclick="callShowDiv(this);"><%=label.get("factory.manager_representatives_are_members")%>
					</label>
					</td>
					<td><s:textfield name="factorySafetyHealthBean.sCManagementMembers" />
					&nbsp;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
						<label class="set"	name="factory.how_often_does_it_meet"
								id="factory.how_often_does_it_meet"
								ondblclick="callShowDiv(this);"><%=label.get("factory.how_often_does_it_meet")%>
					</label>
				</td>
					<td><s:textfield name="factorySafetyHealthBean.meetingFrequencyID" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>37</td>
					<td>
					<label class="set"	name="factory.factory_have_at_least_2_exits_on_each_floor"
								id="factory.factory_have_at_least_2_exits_on_each_floor"
								ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_at_least_2_exits_on_each_floor")%>
					</label>
				</td>
					<td><s:checkbox name="factorySafetyHealthBean.hasTwoExitsOnEachFloor" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>38</td>
					<td>
						<label class="set"	name="factory.fire_extinguishers_placed_throughout_the_factory"
								id="factory.fire_extinguishers_placed_throughout_the_factory"
								ondblclick="callShowDiv(this);"><%=label.get("factory.fire_extinguishers_placed_throughout_the_factory")%>
					</label>
				</td>
					<td><s:checkbox name="factorySafetyHealthBean.hasFireExtinguishers" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.how_many_extinguishers_in_total"
								id="factory.how_many_extinguishers_in_total"
								ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_extinguishers_in_total")%>
					</label>
				</td>
					<td><s:textfield name="factorySafetyHealthBean.noOfFE" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
						<label class="set"	name="factory.workers_have_been_trained_to_use_extinguishers"
								id="factory.workers_have_been_trained_to_use_extinguishers"
								ondblclick="callShowDiv(this);"><%=label.get("factory.workers_have_been_trained_to_use_extinguishers")%>
					</label>
					</td>
					<td><s:textfield name="factorySafetyHealthBean.noOfWorkersTrainedFE" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>39&nbsp;</td>
					<td>
						<label class="set"	name="factory.factory_have_first_aid_boxes"
								id="factory.factory_have_first_aid_boxes"
								ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_first_aid_boxes")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.hasFirstAidBoxes" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.how_many_first_aid_boxes_throughout_the_factory"
								id="factory.how_many_first_aid_boxes_throughout_the_factory"
								ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_first_aid_boxes_throughout_the_factory")%>
					</label>
					</td>
					<td><s:textfield name="factorySafetyHealthBean.noOfFirstAidboxes" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.how_often_are_they_checked_for_their_contents"
								id="factory.how_often_are_they_checked_for_their_contents"
								ondblclick="callShowDiv(this);"><%=label.get("factory.how_often_are_they_checked_for_their_contents")%>
					</label>
					</td>
					<td><s:textfield name="factorySafetyHealthBean.fABCheckingFrequencyID" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>40&nbsp;</td>
					<td>
					<label class="set"	name="factory.workers_have_a_first_aid_certificate"
								id="factory.workers_have_a_first_aid_certificate"
								ondblclick="callShowDiv(this);"><%=label.get("factory.workers_have_a_first_aid_certificate")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.hasFACertifiedWorkers" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
						<label class="set"	name="factory.how_many_workers"
								id="factory.how_many_workers"
								ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_workers")%>
					</label>
					</td>
					<td><s:textfield name="factorySafetyHealthBean.noOfFACertifiedWorkers" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>41</td>
					<td>
						<label class="set"	name="factory.factory_have_a_HIV_AIDS_policy"
								id="factory.factory_have_a_HIV_AIDS_policy"
								ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_a_HIV_AIDS_policy")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.hasHIVPolicy" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td colspan="2">
					<table width="100%" cellpadding="1" cellspacing="0" border="1">
						<tr class="clsTRPageCaption">
							<th>&nbsp;</th>
							<th>
							<table width="100%" border="0" cellspacing="0" cellpadding="1">
								<tr>
									<td><s:checkbox name="factorySafetyHealthBean.providesProtectiveClothNEquip" /></td>
									<td style="font-weight: normal">42&nbsp;
									<label class="set"	name="factory.factory_provide_workers_with_protective_clothing_and_equipment"
										id="factory.factory_provide_workers_with_protective_clothing_and_equipment"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_provide_workers_with_protective_clothing_and_equipment")%>
									</label>
									</td>
								</tr>
							</table>
							</th>
							<th width="37%">
							<table width="100%" border="0" cellspacing="0" cellpadding="1">
								<tr>
									<td><s:checkbox name="factorySafetyHealthBean.paidProtectiveClothingNEquip" /></td>
									<td style="font-weight: normal">43&nbsp;
									<label class="set"	name="factory.workers_required_to_pay_for_any_protective_clothing_or_equipment"
										id="factory.workers_required_to_pay_for_any_protective_clothing_or_equipment"
										ondblclick="callShowDiv(this);"><%=label.get("factory.workers_required_to_pay_for_any_protective_clothing_or_equipment")%>
									</label>
									</td>
								</tr>
							</table>
							</th>
						</tr>
						<tr class="clsTROdd">
							<td width="31%">
								<label class="set"	name="factory.foot_protection" id="factory.foot_protection"
										ondblclick="callShowDiv(this);"><%=label.get("factory.foot_protection")%>
									</label>
							</td>
							<td width="32%"><s:checkbox name="factorySafetyHealthBean.hasFootProtection" />
							Yes / &#2361;&#2379;&#2351;</td>
							<td><s:checkbox name="factorySafetyHealthBean.isFootProtectionPaid" /> Yes /
							&#2361;&#2379;&#2351;</td>
						</tr>
						<tr class="clsTROdd">
							<td>
							<label class="set"	name="factory.eye_protection"
										id="factory.eye_protection"
										ondblclick="callShowDiv(this);"><%=label.get("factory.eye_protection")%>
									</label>
							</td>
							<td><s:checkbox name="factorySafetyHealthBean.hasEyeProtection" /> Yes /
							&#2361;&#2379;&#2351;</td>
							<td><s:checkbox name="factorySafetyHealthBean.isEyeProtectionPaid" /> Yes /
							&#2361;&#2379;&#2351;</td>
						</tr>
						<tr class="clsTROdd">
							<td>
							<label class="set"	name="factory.ear_protection"
										id="factory.ear_protection"
										ondblclick="callShowDiv(this);"><%=label.get("factory.ear_protection")%>
							</label>
							</td>
							<td><s:checkbox name="factorySafetyHealthBean.hasEarProtection" /> Yes /
							&#2361;&#2379;&#2351;</td>
							<td><s:checkbox name="factorySafetyHealthBean.isEarProtectionPaid" /> Yes /
							&#2361;&#2379;&#2351;</td>
						</tr>
						<tr class="clsTROdd">
							<td>
							<label class="set"	name="factory.hand_protection"
										id="factory.hand_protection"
										ondblclick="callShowDiv(this);"><%=label.get("factory.hand_protection")%>
							</label>
							</td>
							<td><s:checkbox name="factorySafetyHealthBean.hasHandProtection" /> Yes /
							&#2361;&#2379;&#2351;</td>
							<td><s:checkbox name="factorySafetyHealthBean.isHandProtectionPaid" /> Yes /
							&#2361;&#2379;&#2351;</td>
						</tr>
						<tr class="clsTROdd">
							<td>
							<label class="set"	name="factory.body_protection"
										id="factory.body_protection"
										ondblclick="callShowDiv(this);"><%=label.get("factory.body_protection")%>
							</label>
							</td>
							<td><s:checkbox name="factorySafetyHealthBean.hasBodyProtection" /> Yes /
							&#2361;&#2379;&#2351;</td>
							<td><s:checkbox name="factorySafetyHealthBean.isBodyProtectionPaid" /> Yes /
							&#2361;&#2379;&#2351;</td>
						</tr>
						<tr class="clsTROdd">
							<td>
							<label class="set"	name="factory.respiratory_protection"
										id="factory.respiratory_protection"
										ondblclick="callShowDiv(this);"><%=label.get("factory.respiratory_protection")%>
							</label>
							</td>
							<td><s:checkbox name="factorySafetyHealthBean.hasRespiratoryProtection" /> Yes /
							&#2361;&#2379;&#2351;</td>
							<td><s:checkbox name="factorySafetyHealthBean.isRespiratoryProtectionPaid" /> Yes /
							&#2361;&#2379;&#2351;</td>
						</tr>
						<tr class="clsTROdd">
							<td>
							<label class="set"	name="factory.other_protection"
										id="factory.other_protection"
										ondblclick="callShowDiv(this);"><%=label.get("factory.other_protection")%>
							</label>
							</td>
							<td><s:textfield name="factorySafetyHealthBean.otherProtection" /></td>
							<td><s:checkbox name="factorySafetyHealthBean.isOtherProtectionPaid" /> Yes /
							&#2361;&#2379;&#2351;</td>
						</tr>
						<tr class="clsTROdd">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- <tr class="clsTRBody">
					<td>42</td>
					<td>
					<label class="set"	name="factory.factory_provide_workers_with_protective_clothing_and_equipment1"
										id="factory.factory_provide_workers_with_protective_clothing_and_equipment1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_provide_workers_with_protective_clothing_and_equipment1")%>
							</label>
					</td>
					<td><s:checkbox name="checkbox" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.what_is_provided"
										id="factory.what_is_provided"
										ondblclick="callShowDiv(this);"><%=label.get("factory.what_is_provided")%>
					</label>
					</td>
					<td><s:checkbox name="checkbox5" />
					<label class="set"	name="factory.foot_protection1"
										id="factory.foot_protection1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.foot_protection1")%>
					</label>
					<s:checkbox name="checkbox52" />
					<label class="set"	name="factory.eye_protection1"
										id="factory.eye_protection1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.eye_protection1")%>
					</label>
					<s:checkbox name="checkbox53" />
					<label class="set"	name="factory.ear_protection1"
										id="factory.ear_protection1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.ear_protection1")%>
					</label>
					<s:checkbox name="checkbox54" />
					<label class="set"	name="factory.hand_protection1"
										id="factory.hand_protection1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.hand_protection1")%>
					</label>
					<s:checkbox name="checkbox55" />
					<label class="set"	name="factory.body_protection1"
										id="factory.body_protection1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.body_protection1")%>
					</label>
					<s:checkbox name="checkbox56" />
					<label class="set"	name="factory.respiratory_protection1"
										id="factory.respiratory_protection1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.respiratory_protection1")%>
					</label>
					<s:checkbox name="checkbox57" />
					<label class="set"	name="factory.other_protection1"
										id="factory.other_protection1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.other_protection1")%>
					</label>
				</tr>
				<tr class="clsTRBody">
					<td>43</td>
					<td>
					<label class="set"	name="factory.workers_required_to_pay_for_any_protective_clothing_or_equipment1"
										id="factory.workers_required_to_pay_for_any_protective_clothing_or_equipment1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.workers_required_to_pay_for_any_protective_clothing_or_equipment1")%>
					</label>
					</td>
					<td><s:checkbox name="checkbox" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>  
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.which_items_workers_required_to_pay"
										id="factory.which_items_workers_required_to_pay"
										ondblclick="callShowDiv(this);"><%=label.get("factory.which_items_workers_required_to_pay")%>
					</label>
					</td>
					<td><textarea name="textarea" cols="45" rows="4"></textarea></td>
				</tr> -->
				<tr class="clsTRBody">
					<td>44</td>
					<td>
					<label class="set"	name="factory.factory_reported_any_accidents_to_the_factory_inspector_during_the_reporting_period"
										id="factory.factory_reported_any_accidents_to_the_factory_inspector_during_the_reporting_period"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_reported_any_accidents_to_the_factory_inspector_during_the_reporting_period")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.noOfAccidentsReported" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
						<label class="set"	name="factory.how_many_non-fatal"
										id="factory.how_many_non-fatal"
										ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_non-fatal")%>
					</label>
					</td>
					<td><input name="factorySafetyHealthBean.noOfAccidentsReportedNF" type="text" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
						<label class="set"	name="factory.how_many_fatal"
										id="factory.how_many_fatal"
										ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_fatal")%>
					</label>
					</td>
					<td><input name="factorySafetyHealthBean.noOfAccidentsReportedF" type="text" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>45</td>
					<td>
					<label class="set"	name="factory.factory_reported_any_occupational_diseases_to_the_factory_inspector"
										id="factory.factory_reported_any_occupational_diseases_to_the_factory_inspector"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_reported_any_occupational_diseases_to_the_factory_inspector")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.occupationalDiseasesReported" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
						<label class="set"	name="factory.how_many_fatal1"
										id="factory.how_many_fatal1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_fatal1")%>
					</label>
					</td>
					<td><s:textfield name="factorySafetyHealthBean.fatalDiseases" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.how_many_non-fatal1"
										id="factory.how_many_non-fatal1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_non-fatal1")%>
					</label>
					</td>
					<td><s:textfield name="factorySafetyHealthBean.nonFatalDiseases" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>46</td>
					<td>
						<label class="set"	name="factory.safety_posters_displayed_in_the_factory"
										id="factory.safety_posters_displayed_in_the_factory"
										ondblclick="callShowDiv(this);"><%=label.get("factory.safety_posters_displayed_in_the_factory")%>
					</label>
					</td>
					<td><s:checkbox name="factorySafetyHealthBean.safetyPostersDisplayed" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="3">
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="0" style="border-bottom: none;">
						<tr>
							<td width="4%" style="border: none;"><s:checkbox
								name="factoryWelfareFacilitiesBean.welfareFacilityID" /></td>
							<td width="96%" style="border: none;">
							<label class="set"	name="factory.welfare_facilities"
										id="factory.welfare_facilities"
										ondblclick="callShowDiv(this);"><%=label.get("factory.welfare_facilities")%>
							</label>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td width="3%">47</td>
					<td width="38%">
					<label class="set"	name="factory.factory_provide_drinking_water_for_workers"
										id="factory.factory_provide_drinking_water_for_workers"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_provide_drinking_water_for_workers")%>
					</label>
					</td>
					<td width="59%"><s:checkbox name="factoryWelfareFacilitiesBean.provideDrinkingWater" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>48</td>
					<td>
					<label class="set"	name="factory.factory_have_a_creche"
										id="factory.factory_have_a_creche"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_a_creche")%>
					</label>
				</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasCreche" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>49</td>
					<td>
					<label class="set"	name="factory.factory_have_a_canteen"
										id="factory.factory_have_a_canteen"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_a_canteen")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasCanteen" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>50</td>
					<td>
					<label class="set"	name="factory.factory_provide_a_locker_for_workers"
										id="factory.factory_provide_a_locker_for_workers"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_provide_a_locker_for_workers")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.providesLocker" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>51</td>
					<td>
					<label class="set"	name="factory.changing_room_for_workers"
										id="factory.changing_room_for_workers"
										ondblclick="callShowDiv(this);"><%=label.get("factory.changing_room_for_workers")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasChangingRoom" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>52</td>
					<td>
					<label class="set"	name="factory.rest_room_for_workers"
										id="factory.rest_room_for_workers"
										ondblclick="callShowDiv(this);"><%=label.get("factory.rest_room_for_workers")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasRestRoom" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>53</td>
					<td>
					<label class="set"	name="factory.occupational_health_centre"
										id="factory.occupational_health_centre"
										ondblclick="callShowDiv(this);"><%=label.get("factory.occupational_health_centre")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasClinic" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>54</td>
					<td>
					<label class="set"	name="factory.clinic_open_to_members_of_the_workers_family"
										id="factory.clinic_open_to_members_of_the_workers_family"
										ondblclick="callShowDiv(this);"><%=label.get("factory.clinic_open_to_members_of_the_workers_family")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasClinicForWorkersFamily" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>55</td>
					<td>
						<label class="set"	name="factory.ambulance_room"
										id="factory.ambulance_room"
										ondblclick="callShowDiv(this);"><%=label.get("factory.ambulance_room")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasAmbulanceRoom" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>56</td>
					<td>
					<label class="set"	name="factory.fulltime_Doctor_in_attendance"
										id="factory.fulltime_Doctor_in_attendance"
										ondblclick="callShowDiv(this);"><%=label.get("factory.fulltime_Doctor_in_attendance")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasFullTimeDoctor" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>57</td>
					<td>
					<label class="set"	name="factory.parttime_Doctor"
										id="factory.parttime_Doctor"
										ondblclick="callShowDiv(this);"><%=label.get("factory.parttime_Doctor")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasPartTimeDoctor" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>58</td>
					<td>
						<label class="set"	name="factory.fulltime_nurse_in_attendance"
										id="factory.fulltime_nurse_in_attendance"
										ondblclick="callShowDiv(this);"><%=label.get("factory.fulltime_nurse_in_attendance")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasFullTimeNurse" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>59</td>
					<td>
					<label class="set"	name="factory.parttime_nurse"
										id="factory.parttime_nurse"
										ondblclick="callShowDiv(this);"><%=label.get("factory.parttime_nurse")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasPartTimeNurse" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>60</td>
					<td>
					<label class="set"	name="factory.factory_have_separate_toilets_for_men_and_women"
										id="factory.factory_have_separate_toilets_for_men_and_women"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_separate_toilets_for_men_and_women")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasSeperateToiletForMnF" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>61</td>
					<td>
					<label class="set"	name="factory.how_many_latrines_for_men"
										id="factory.how_many_latrines_for_men"
										ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_latrines_for_men")%>
					</label>
					</td>
					<td>
					<s:textfield name="factoryWelfareFacilitiesBean.noOfLatrinesForMen"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>62</td>
					<td>
					<label class="set"	name="factory.how_many_urinals_for_men"
										id="factory.how_many_urinals_for_men"
										ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_urinals_for_men")%>
					</label>
					</td>
					<td>
					<s:textfield name="factoryWelfareFacilitiesBean.noOfUrinalsForMale"/>
					
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>63</td>
					<td>
					<label class="set"	name="factory.how_many_latrines_for_women"
										id="factory.how_many_latrines_for_women"
										ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_latrines_for_women")%>
					</label>
					</td>
					<td>
					<s:textfield name="factoryWelfareFacilitiesBean.noOfLatrinesForWomen"/>
					
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>64</td>
					<td>
						<label class="set"	name="factory.is_there_a_welfare_officer"
										id="factory.is_there_a_welfare_officer"
										ondblclick="callShowDiv(this);"><%=label.get("factory.is_there_a_welfare_officer")%>
					</label>
					</td>
					<td><s:checkbox name="factoryWelfareFacilitiesBean.hasWelfareOfficer" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.number_of_welfare_officers"
										id="factory.number_of_welfare_officers"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_welfare_officers")%>
					</label>
					</td>
					<td>
					<s:textfield name="factoryWelfareFacilitiesBean.noOfWelfareOfficers"/></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="formtext">  
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="3">Wages and benefits /
					&#2357;&#2375;&#2340;&#2344; &#2357; &#2311;&#2340;&#2352;
					&#2349;&#2340;&#2381;&#2340;&#2375;</td>
				</tr>
				<tr class="clsTRBody">
					<td width="3%">65</td>
					<td width="38%">
					<label class="set"	name="factory.workers_required_to_work_overtime"
										id="factory.workers_required_to_work_overtime"
										ondblclick="callShowDiv(this);"><%=label.get("factory.workers_required_to_work_overtime")%>
					</label>
					</td>
					<td width="59%"><s:checkbox name="factoryWagesBenefitsBean.overtimeRequired" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
						<label class="set"	name="factory.overtime_rate_of_pay"
										id="factory.overtime_rate_of_pay"
										ondblclick="callShowDiv(this);"><%=label.get("factory.overtime_rate_of_pay")%>
					</label>
					</td>
					<td>
					<s:textfield name="factoryWelfareFacilitiesBean.overtimeRate"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.highest_number_of_overtime_hours_worked_by_a_worker_last_month"
										id="factory.highest_number_of_overtime_hours_worked_by_a_worker_last_month"
										ondblclick="callShowDiv(this);"><%=label.get("factory.highest_number_of_overtime_hours_worked_by_a_worker_last_month")%>
					</label>
					</td>
					<td><input type="text" name="factoryWagesBenefitsBean.maxOvertimeHrs" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>66</td>
					<td>
					<label class="set"	name="factory.hours_per_day_do_workers_work"
										id="factory.hours_per_day_do_workers_work"
										ondblclick="callShowDiv(this);"><%=label.get("factory.hours_per_day_do_workers_work")%>
					</label>
					</td>
					<td><input type="text" name="factoryWagesBenefitsBean.workingHrsPerDay" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.how_many_days_per_week"
										id="factory.how_many_days_per_week"
										ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_days_per_week")%>
					</label>
					</td>
					<td><input type="text" name="factoryWagesBenefitsBean.workingDaysPerWeek" class="formelement" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="3">&nbsp;Industrial Relations /
					&#2324;&#2342;&#2381;&#2351;&#2379;&#2327;&#2367;&#2325;
					&#2360;&#2306;&#2348;&#2306;&#2343;</td>
				</tr>
				<tr class="clsTRBody">
					<td width="3%">67</td>
					<td width="38%">
						<label class="set"	name="factory.factory_have_a_written_sexual_harassment_policy"
										id="factory.factory_have_a_written_sexual_harassment_policy"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_a_written_sexual_harassment_policy")%>
					</label>
					</td>
					<td width="59%"><s:checkbox name="factoryIndustrialRelationsBean.hasWrittenSHPolicy" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>68</td>
					<td>
					<label class="set"	name="factory.factory_have_a_sexual_harassment_committee"
										id="factory.factory_have_a_sexual_harassment_committee"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_have_a_sexual_harassment_committee")%>
					</label>
					</td>
					<td><s:checkbox name="factoryIndustrialRelationsBean.hasSHCommittee" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>69</td>
					<td>
					<label class="set"	name="factory.sexual_harassment_complaints_been_lodged_within_the_factory"
										id="factory.sexual_harassment_complaints_been_lodged_within_the_factory"
										ondblclick="callShowDiv(this);"><%=label.get("factory.sexual_harassment_complaints_been_lodged_within_the_factory")%>
					</label>
					</td>
					<td><s:checkbox name="factoryIndustrialRelationsBean.sHComplaintsLodged" />&nbsp;Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>70</td>
					<td>
						<label class="set"	name="factory.factory_operate_a_suggestion_box_scheme"
										id="factory.factory_operate_a_suggestion_box_scheme"
										ondblclick="callShowDiv(this);"><%=label.get("factory.factory_operate_a_suggestion_box_scheme")%>
					</label>
					</td>
					<td><s:checkbox name="factoryIndustrialRelationsBean.hasSuggestionBox" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.how_many_useful_suggestions_received"
										id="factory.how_many_useful_suggestions_received"
										ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_useful_suggestions_received")%>
					</label>
					</td>
					<td><input type="text" name="factoryIndustrialRelationsBean.suggestionsReceived"class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.suggestions_were_acted_upon"
										id="factory.suggestions_were_acted_upon"
										ondblclick="callShowDiv(this);"><%=label.get("factory.suggestions_were_acted_upon")%>
					</label>
					</td>
					<td><input type="text" name="factoryIndustrialRelationsBean.noOfSuggestionsActedUpon" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.were_workers_rewarded_for_suggestions"
										id="factory.were_workers_rewarded_for_suggestions"
										ondblclick="callShowDiv(this);"><%=label.get("factory.were_workers_rewarded_for_suggestions")%>
					</label>
					</td>
					<td><s:checkbox name="factoryIndustrialRelationsBean.suggestionsRewarded" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
			</table>
			</td>
		</tr>

		  
		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="6">71 &nbsp;Employment information /
					&#2325;&#2366;&#2350;&#2327;&#2366;&#2352;&#2366;&#2306;&#2357;&#2367;&#2359;&#2351;&#2368;
					&#2350;&#2366;&#2361;&#2367;&#2340;&#2368;</td>
				</tr>
				<tr class="clsTRBody">
					<td width="22%">
					<label class="set"	name="factory.workers"
										id="factory.workers"
										ondblclick="callShowDiv(this);"><%=label.get("factory.workers")%>
					</label>
					</td>
					<td width="11%">&nbsp;</td>
					<td width="12%">
					<label class="set"	name="factory.number"
										id="factory.number"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number")%>
					</label>
					</td>
					<td width="15%">
					<label class="set"	name="factory.number_of_man_days"
										id="factory.number_of_man_days"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_man_days")%>
					</label>
					</td>
					<td width="19%">
					<label class="set"	name="factory.number_of_man-days_worked_overtime"
										id="factory.number_of_man-days_worked_overtime"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_man-days_worked_overtime")%>
					</label>
					</td>
					<td width="21%">
					<label class="set"	name="factory.number_of_man_hours_worked_per_month"
										id="factory.number_of_man_hours_worked_per_month"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_man_hours_worked_per_month")%>
					</label>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=2>
						<label class="set"	name="factory.adults"
										id="factory.adults"
										ondblclick="callShowDiv(this);"><%=label.get("factory.adults")%>
					</label>
					</td>
					<td>M/&#2346;&#2369;</td>
					<td><input name="text10" type="text" class="formelement" name="workersAdultsMale"
						style="width: 40px" /></td>
					<td><input name="text10" type="text" class="formelement" name="mandaysAdultsMale"
						style="width: 40px" /></td>
					<td><input name="text10" type="text" class="formelement" name="overtimeMandaysAdultsMale"
						style="width: 40px" /></td>
					<td><input name="text10" type="text" class="formelement" name="manHoursPerMonthAdultsMale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>F/&#2360;&#2381;&#2340;&#2381;&#2352;&#2368;</td>
					<td><input name="text22" type="text" class="formelement" name="workersAdultsFemale"
						style="width: 40px" /></td>
					<td><input name="text22" type="text" class="formelement" name="mandaysAdultsFemale"
						style="width: 40px" /></td>
					<td><input name="text22" type="text" class="formelement" name="overtimeMandaysAdultsFemale"
						style="width: 40px" /></td>
					<td><input name="text22" type="text" class="formelement" name="manHoursPerMonthAdultsFemale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=2>
					<label class="set"	name="factory.adolescents"	id="factory.adolescents"
										ondblclick="callShowDiv(this);"><%=label.get("factory.adolescents")%>
					</label>
					</td>
					<td>M/&#2346;&#2369;</td>
					<td><input name="text32" type="text" class="formelement" name="workersAdolescentsMale"
						style="width: 40px" /></td>
					<td><input name="text32" type="text" class="formelement" name="mandaysAdolescentsMale"
						style="width: 40px" /></td>
					<td><input name="text32" type="text" class="formelement" name="overtimeMandaysAdolescentsMale"
						style="width: 40px" /></td>
					<td><input name="text32" type="text" class="formelement" name="manHoursPerMonthAdolescentsMale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>F/&#2360;&#2381;&#2340;&#2381;&#2352;&#2368;</td>
					<td><input name="text42" type="text" class="formelement" name="workersAdolescentsFemale"
						style="width: 40px" /></td>
					<td><input name="text42" type="text" class="formelement" name="mandaysAdolescentsFemale"
						style="width: 40px" /></td>
					<td><input name="text42" type="text" class="formelement" name="overtimeMandaysAdolescentsFemale"
						style="width: 40px" /></td>
					<td><input name="text42" type="text" class="formelement" name="manHoursPerMonthAdolescentsFemale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=2>
					<label class="set"	name="factory.children"	id="factory.children"
										ondblclick="callShowDiv(this);"><%=label.get("factory.children")%>
					</label>
					</td>
					<td>M/&#2346;&#2369;</td>
					<td><input name="text52" type="text" class="formelement" name="workersChildrenMale"
						style="width: 40px" /></td>
					<td><input name="text52" type="text" class="formelement" name="mandaysChildrenMale"
						style="width: 40px" /></td>
					<td><input name="text52" type="text" class="formelement" name="overtimeMandaysChildrenMale"
						style="width: 40px" /></td>
					<td><input name="text52" type="text" class="formelement" name="manHoursPerMonthChildrenMale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>F/&#2360;&#2381;&#2340;&#2381;&#2352;&#2368;</td>
					<td><input name="text62" type="text" class="formelement" name="workersChildrenFemale"
						style="width: 40px" /></td>
					<td><input name="text62" type="text" class="formelement" name="mandaysChildrenFemale"
						style="width: 40px" /></td>
					<td><input name="text62" type="text" class="formelement" name="overtimeMandaysChildrenFemale"
						style="width: 40px" /></td>
					<td><input name="text62" type="text" class="formelement" name="manHoursPerMonthChildrenFemale"
						style="width: 40px" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="8">72&nbsp;&nbsp;Leave with wages record /
					&#2346;&#2327;&#2366;&#2352;&#2368;
					&#2352;&#2332;&#2366;&#2306;&#2330;&#2368;
					&#2344;&#2379;&#2306;&#2342;</td>
				</tr>
				<tr class="clsTRBody">
					<td width="11%">
					<label class="set"	name="factory.wages_workers"	id="factory.wages_workers"
										ondblclick="callShowDiv(this);"><%=label.get("factory.wages_workers")%>
					</label>
					</td>
					<td width="6%">&nbsp;</td>
					<td width="9%">
					<label class="set"	name="factory.number_employed"	id="factory.number_employed"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_employed")%>
					</label>
					</td>
					<td width="13%">
					<label class="set"	name="factory.number_entitled_to_annual_leave"	id="factory.number_entitled_to_annual_leave"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_entitled_to_annual_leave")%>
					</label>
					</td>
					<td width="14%">
					<label class="set"	name="factory.number_who_were_granted_leave"	id="factory.number_who_were_granted_leave"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_who_were_granted_leave")%>
					</label>
					</td>
					<td width="15%">
					<label class="set"	name="factory.number_of_discharged_workers"	id="factory.number_of_discharged_workers"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_discharged_workers")%>
					</label>
					</td>
					<td width="16%">
					<label class="set"	name="factory.number_of_dismissed_workers"	id="factory.number_of_dismissed_workers"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_dismissed_workers")%>
					</label>
					</td>
					<td width="16%">
					<label class="set"	name="factory.number_of_workers_to_whom_wages_in_lieu_of_leave_were_paid"	id="factory.number_of_workers_to_whom_wages_in_lieu_of_leave_were_paid"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_workers_to_whom_wages_in_lieu_of_leave_were_paid")%>
					</label>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=2>
					<label class="set"	name="factory.adults1"	id="factory.adults1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.adults1")%>
					</label>
					</td>
					<td>M/&#2346;&#2369;</td>
					<td><input name="text" type="text" class="formelement" name="workersAdultsMaleLeave"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="entitledToAnnualLeaveAdultsMale"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="leaveGrantedAdultsMale"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="dischargedAdultsMale"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="dismissedAdultsMale"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="leaveEncashedAdultsMale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>F/&#2360;&#2381;&#2340;&#2381;&#2352;&#2368;</td>
					<td><input name="text2" type="text" class="formelement" name="workersAdultsFemaleLeave"
						style="width: 40px" /></td>
					<td><input name="text2" type="text" class="formelement" name="entitledToAnnualLeaveAdultsFemale"
						style="width: 40px" /></td>
					<td><input name="text2" type="text" class="formelement" name="leaveGrantedAdultsFemale"
						style="width: 40px" /></td>
					<td><input name="text2" type="text" class="formelement" name="dischargedAdultsFemale"
						style="width: 40px" /></td>
					<td><input name="text2" type="text" class="formelement" name="dismissedAdultsFemale"
						style="width: 40px" /></td>
					<td><input name="text2" type="text" class="formelement" name="leaveEncashedAdultsFemale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=2>
					<label class="set"	name="factory.adolescents1"	id="factory.adolescents1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.adolescents1")%>
					</label>
					</td>
					<td>M/&#2346;&#2369;</td>
					<td><input name="text3" type="text" class="formelement" name="workersAdolescentsMaleLeave"
						style="width: 40px" /></td>
					<td><input name="text3" type="text" class="formelement" name="entitledToAnnualLeaveAdolescentsMale"
						style="width: 40px" /></td>
					<td><input name="text3" type="text" class="formelement" name="leaveGrantedAdolescentsMale"
						style="width: 40px" /></td>
					<td><input name="text3" type="text" class="formelement" name="dischargedAdolescentsMale"
						style="width: 40px" /></td>
					<td><input name="text3" type="text" class="formelement" name="dismissedAdolescentsMale"
						style="width: 40px" /></td>
					<td><input name="text3" type="text" class="formelement" name="leaveEncashedAdolescentsMale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>F/&#2360;&#2381;&#2340;&#2381;&#2352;&#2368;</td>
					<td><input name="text4" type="text" class="formelement" name="workersAdolescentsFemaleLeave"
						style="width: 40px" /></td>
					<td><input name="text4" type="text" class="formelement" name="entitledToAnnualLeaveAdolescentsFemale"
						style="width: 40px" /></td>
					<td><input name="text4" type="text" class="formelement" name="leaveGrantedAdolescentsFemale"
						style="width: 40px" /></td>
					<td><input name="text4" type="text" class="formelement" name="dischargedAdolescentsFemale"
						style="width: 40px" /></td>
					<td><input name="text4" type="text" class="formelement" name="dismissedAdolescentsFemale"
						style="width: 40px" /></td>
					<td><input name="text4" type="text" class="formelement" name="leaveEncashedAdolescentsFemale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=2>
					<label class="set"	name="factory.children1"	id="factory.children1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.children1")%>
					</label>
					</td>
					<td>M/&#2346;&#2369;</td>
					<td><input name="text5" type="text" class="formelement" name="workersChildrenMaleLeave"
						style="width: 40px" /></td>
					<td><input name="text5" type="text" class="formelement" name="entitledToAnnualLeaveChildrenMale"
						style="width: 40px" /></td>
					<td><input name="text5" type="text" class="formelement" name="leaveGrantedChildrenMale"
						style="width: 40px" /></td>
					<td><input name="text5" type="text" class="formelement" name="dischargedChildrenMale"
						style="width: 40px" /></td>
					<td><input name="text5" type="text" class="formelement" name="dismissedChildrenMale"
						style="width: 40px" /></td>
					<td><input name="text5" type="text" class="formelement" name="leaveEncashedChildrenMale"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>F/&#2360;&#2381;&#2340;&#2381;&#2352;&#2368;</td>
					<td><input name="text6" type="text" class="formelement" name="workersChildrenFemaleLeave"
						style="width: 40px" /></td>
					<td><input name="text6" type="text" class="formelement" name="entitledToAnnualLeaveChildrenFemale"
						style="width: 40px" /></td>
					<td><input name="text6" type="text" class="formelement" name="leaveGrantedChildrenFemale"
						style="width: 40px" /></td>
					<td><input name="text6" type="text" class="formelement" name="dischargedChildrenFemale"
						style="width: 40px" /></td>
					<td><input name="text6" type="text" class="formelement" name="dismissedChildrenFemale"
						style="width: 40px" /></td>
					<td><input name="text6" type="text" class="formelement" name="leaveEncashedChildrenFemale"
						style="width: 40px" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="7">
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="0" style="border-bottom: none;">
						<tr>
							<td width="4%" style="border: none;"><s:checkbox name="asdas" /></td>
							<td width="96%" style="border: none;">73 Accident details /
							&#2309;&#2346;&#2328;&#2366;&#2340;&#2366;&#2306;&#2330;&#2366;
							&#2340;&#2346;&#2358;&#2368;&#2354;</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.number1"	id="factory.number1"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number1")%>
					</label>
					</td>
					<td>
					<label class="set"	name="factory.number_of_workers_injured"	id="factory.number_of_workers_injured"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_workers_injured")%>
					</label>
					</td>
					<td>
					<label class="set"	name="factory.number_of_injured_workers_who_returned_to_work_in_this_year"	id="factory.number_of_injured_workers_who_returned_to_work_in_this_year"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_injured_workers_who_returned_to_work_in_this_year")%>
					</label>
					</td>
					<td>
					<label class="set"	name="factory.number_of_workers_injured_in_previous_year_who_joined_the_work_this_year"	id="factory.number_of_workers_injured_in_previous_year_who_joined_the_work_this_year"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_workers_injured_in_previous_year_who_joined_the_work_this_year")%>
					</label>
					</td>
					<td>
					<label class="set"	name="factory.number_of_man-days_lost"	id="factory.number_of_man-days_lost"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_man-days_lost")%>
					</label>
					</td>
					<td>
					<label class="set"	name="factory.number_of_workers_injured_this_year_but_have_not_joined_during_this_year"	id="factory.number_of_workers_injured_this_year_but_have_not_joined_during_this_year"
										ondblclick="callShowDiv(this);"><%=label.get("factory.number_of_workers_injured_this_year_but_have_not_joined_during_this_year")%>
					</label>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.fatal_accidents"	id="factory.fatal_accidents"
										ondblclick="callShowDiv(this);"><%=label.get("factory.fatal_accidents")%>
					</label>
					</td>
					<td><input name="text" type="text" class="formelement" name="numberFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="workersInjuredFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="injWorkersRetToWorkFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="workersInjPrevYrJoinedThisYrFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="manDaysLostFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="workersInjThisYr_NotJoinedFA"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.non-fatal_accidents"	id="factory.non-fatal_accidents"
										ondblclick="callShowDiv(this);"><%=label.get("factory.non-fatal_accidents")%>
					</label>
					</td>
					<td><input name="text" type="text" class="formelement" name="numberNFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="workersInjuredNFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="injWorkersRetToWorkNFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="workersInjPrevYrJoinedThisYrNFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="manDaysLostNFA"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="workersInjThisYr_NotJoinedNFA"
						style="width: 40px" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.dangerous_occurrences"	id="factory.dangerous_occurrences"
										ondblclick="callShowDiv(this);"><%=label.get("factory.dangerous_occurrences")%>
					</label>
					</td>
					<td><input name="text" type="text" class="formelement" name="numberDO"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="workersInjuredDO"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="injWorkersRetToWorkDO"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="workersInjPrevYrJoinedThisYrDO"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="manDaysLostDO"
						style="width: 40px" /></td>
					<td><input name="text" type="text" class="formelement" name="workersInjThisYr_NotJoinedDO"
						style="width: 40px" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="2" cellpadding="2" id="compensationTable">
				<tr class="clsTRPageCaption">
					<td colspan="5">74&nbsp; Compensation/Ex-gratia details /
					&#2344;&#2369;&#2325;&#2360;&#2366;&#2344;
					&#2349;&#2352;&#2346;&#2366;&#2312; /
					&#2360;&#2366;&#2344;&#2369;&#2327;&#2381;&#2352;&#2361;
					&#2309;&#2344;&#2369;&#2342;&#2366;&#2344;&#2366;&#2330;&#2366;
					&#2340;&#2346;&#2358;&#2368;&#2354;</td>
					<td align="right">
						<input type="button" name="addCompensationBtn"	value="Add More" onclick="callAddCompensationRow();">
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.name_of_worker"	id="factory.name_of_worker"
										ondblclick="callShowDiv(this);"><%=label.get("factory.name_of_worker")%>
					</label>
					</td>
					<td>
					<label class="set"	name="factory.pay_scale"	id="factory.pay_scale"
										ondblclick="callShowDiv(this);"><%=label.get("factory.pay_scale")%>
					</label>
					</td>
					<td>
					<label class="set"	name="factory.compensation_paid"	id="factory.compensation_paid"
										ondblclick="callShowDiv(this);"><%=label.get("factory.compensation_paid")%>
					</label>
					</td>
					<td>
					<label class="set"	name="factory.ex_gratia"	id="factory.ex_gratia"
										ondblclick="callShowDiv(this);"><%=label.get("factory.ex_gratia")%>
					</label>
					</td>
					<td>
						<label class="set"	name="factory.whether_legal_heirs_employed"	id="factory.whether_legal_heirs_employed"
										ondblclick="callShowDiv(this);"><%=label.get("factory.whether_legal_heirs_employed")%>
					</label>
					</td>
					<td>
						<label class="set"	name="factory.injured"	id="factory.injured"
										ondblclick="callShowDiv(this);"><%=label.get("factory.injured")%>
					</label>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="2">
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="0" style="border-bottom: none;">
						<tr>
							<td width="4%" style="border: none;"><s:checkbox name="checkbox6222" /></td>
							<td width="96%" style="border: none;">75&nbsp; 
							<label class="set"	name="factory.closure_information_of_factory_as_per_rule"	id="factory.closure_information_of_factory_as_per_rule"
										ondblclick="callShowDiv(this);"><%=label.get("factory.closure_information_of_factory_as_per_rule")%>
							</label>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td width="39%">
					<label class="set"	name="factory.name_of_the_factory"	id="factory.name_of_the_factory"
										ondblclick="callShowDiv(this);"><%=label.get("factory.name_of_the_factory")%>
					</label>
					</td>
					<td width="61%"><input type="text" name="" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.address_of_the_factory"	id="factory.address_of_the_factory"
										ondblclick="callShowDiv(this);"><%=label.get("factory.address_of_the_factory")%>
					</label>
					</td>
					<td><textarea class="formelement"></textarea></td>
				</tr>
				<tr class="clsTRBody">
					<td>
						<label class="set"	name="factory.name_of_industry"	id="factory.name_of_industry"
										ondblclick="callShowDiv(this);"><%=label.get("factory.name_of_industry")%>
					</label>
					</td>
					<td><input type="text" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>
						<label class="set"	name="factory.date_of_closure"	id="factory.date_of_closure"
										ondblclick="callShowDiv(this);"><%=label.get("factory.date_of_closure")%>
					</label>
					</td>
					<td><s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="closureDate" value="%{closureDate}" />
						<a href="javascript:NewCal('paraFrm_closureDate','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.reasons_for_closure"	id="factory.reasons_for_closure"
										ondblclick="callShowDiv(this);"><%=label.get("factory.reasons_for_closure")%>
					</label>
					</td>
					<td><textarea name="reason" class="formelement"></textarea></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.nature_of_closure"	id="factory.nature_of_closure"
										ondblclick="callShowDiv(this);"><%=label.get("factory.nature_of_closure")%>
					</label>
					</td>
					<td><input name="partialClosure" type="radio"> Entire/
					&#2346;&#2370;&#2352;&#2381;&#2339; <input name="rad1" type="radio">
					Partial / &#2310;&#2306;&#2358;&#2367;&#2325;&nbsp;</td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.section_or_department_closed"	id="factory.section_or_department_closed"
										ondblclick="callShowDiv(this);"><%=label.get("factory.section_or_department_closed")%>
					</label>
					</td>
					<td><textarea name="partialClosureDetails" class="formelement"></textarea></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.workers_on_the_muster_roll_at_the_time_of_closure"	id="factory.workers_on_the_muster_roll_at_the_time_of_closure"
										ondblclick="callShowDiv(this);"><%=label.get("factory.workers_on_the_muster_roll_at_the_time_of_closure")%>
					</label>
					</td>
					<td><input type="text" name="musterCount" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.workers_affected_by_the_closure"	id="factory.workers_affected_by_the_closure"
										ondblclick="callShowDiv(this);"><%=label.get("factory.workers_affected_by_the_closure")%>
					</label>
					</td>
					<td><input type="text" name="workersAffected" class="formelement" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="3">
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="0" style="border-bottom: none;">
						<tr>
							<td width="4%" style="border: none;"><s:checkbox name="checkbox62223" /></td>
							<td width="96%" style="border: none;">76&nbsp;
							<label class="set"	name="factory.closure_information_of_factory_as_per_rule_125"	id="factory.closure_information_of_factory_as_per_rule_125"
								ondblclick="callShowDiv(this);"><%=label.get("factory.closure_information_of_factory_as_per_rule_125")%>
							</label>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td width="39%">
					<label class="set"	name="factory.name_of_the_factory1"	id="factory.name_of_the_factory1"
								ondblclick="callShowDiv(this);"><%=label.get("factory.name_of_the_factory1")%>
					</label>
					</td>
					<td width="61%"><input type="text" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.address_of_the_factory1"	id="factory.address_of_the_factory1"
								ondblclick="callShowDiv(this);"><%=label.get("factory.address_of_the_factory1")%>
					</label>
					</td>
					<td><textarea class="formelement"></textarea></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.name_of_industry1"	id="factory.name_of_industry1"
								ondblclick="callShowDiv(this);"><%=label.get("factory.name_of_industry1")%>
					</label>
					</td>
					<td><input type="text" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.date_of_closure1"	id="factory.date_of_closure1"
								ondblclick="callShowDiv(this);"><%=label.get("factory.date_of_closure1")%>
					</label>
					</td>
					<td><s:textfield maxLength="10" cssStyle="width:130" size="10" theme="simple" name="closureDateReopening" value="%{closureDateReopening}" />
						<a href="javascript:NewCal('paraFrm_closureDateReopening','DDMMYYYY');"> 
						<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"></a></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.workers_affected_at_the_time_of_closure1"	id="factory.workers_affected_at_the_time_of_closure1"
								ondblclick="callShowDiv(this);"><%=label.get("factory.workers_affected_at_the_time_of_closure1")%>
					</label>
					</td>
					<td><input type="text" name="workersAffectedByClosure" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.section_or_department_thereof_reopened"	id="factory.section_or_department_thereof_reopened"
								ondblclick="callShowDiv(this);"><%=label.get("factory.section_or_department_thereof_reopened")%>
					</label>
					</td>
					<td><textarea class="formelement" name="partialReOpen"></textarea></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.workers_on_the_muster_roll_at_the_time_of_reopening"	id="factory.workers_on_the_muster_roll_at_the_time_of_reopening"
								ondblclick="callShowDiv(this);"><%=label.get("factory.workers_on_the_muster_roll_at_the_time_of_reopening")%>
					</label>
					</td>
					<td><input type="text" class="formelement" name="workerCountAtReopening" /></td>
				</tr>
				<tr class="clsTRBody">
					<td colspan=2>
					<label class="set"	name="factory.workers_re_employed_and_newly_employed"	id="factory.workers_re_employed_and_newly_employed"
								ondblclick="callShowDiv(this);"><%=label.get("factory.workers_re_employed_and_newly_employed")%>
					</label>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.re_employed"	id="factory.re_employed"
								ondblclick="callShowDiv(this);"><%=label.get("factory.re_employed")%>
					</label>
					</td>
					<td><input type="text" class="formelement" name="workersReEmployed" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>
					<label class="set"	name="factory.newly_employed"	id="factory.newly_employed"
								ondblclick="callShowDiv(this);"><%=label.get("factory.newly_employed")%>
					</label>
					</td>
					<td><input type="text" class="formelement" name="workersNewlyEmployed"/></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext">
			<table align="center" width="100%" style="border: 1px solid #565656;"
				cellspacing="0" cellpadding="1">
				<tr class="clsTRPageCaption">
					<td colspan="3">&nbsp;&nbsp;Other /
					&#2309;&#2344;&#2381;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td width="3%">77</td>
					<td width="36%">
					<label class="set"	name="factory.factory_a_member_of_the_Mutual_Aid_and_Response_Group"	id="factory.factory_a_member_of_the_Mutual_Aid_and_Response_Group"
								ondblclick="callShowDiv(this);"><%=label.get("factory.factory_a_member_of_the_Mutual_Aid_and_Response_Group")%>
					</label>
					</td>
					<td width="61%"><s:checkbox name="factoryOtherDetailsBean.isMARGMember" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>78</td>
					<td>
					<label class="set"	name="factory.company_engaged_in_any_other_corporate_social_responsibility_activities"	id="factory.company_engaged_in_any_other_corporate_social_responsibility_activities"
								ondblclick="callShowDiv(this);"><%=label.get("factory.company_engaged_in_any_other_corporate_social_responsibility_activities")%>
					</label>
					</td>
					<td><s:checkbox name="factoryOtherDetailsBean.engagedinCSRA" />&nbsp;Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.what_activities"	id="factory.what_activities"
								ondblclick="callShowDiv(this);"><%=label.get("factory.what_activities")%>
					</label>
					</td>
					<td><textarea class="formelement" name="factoryOtherDetailsBean.cSRADetails"></textarea></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.who_benefitted"	id="factory.who_benefitted"
								ondblclick="callShowDiv(this);"><%=label.get("factory.who_benefitted")%>
					</label>
					</td>
					<td><textarea class="formelement" name="factoryOtherDetailsBean.cSRABenefietaryDetails"></textarea></td>
				</tr>
				<tr class="clsTRBody">
					<td>79</td>
					<td>
					<label class="set"	name="factory.factory_employ_any_disabled_workers"	id="factory.factory_employ_any_disabled_workers"
								ondblclick="callShowDiv(this);"><%=label.get("factory.factory_employ_any_disabled_workers")%>
					</label>
					</td>
					<td><s:checkbox name="factoryOtherDetailsBean.disabledEmployed" />&nbsp;Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.what_types_of_disability"	id="factory.what_types_of_disability"
								ondblclick="callShowDiv(this);"><%=label.get("factory.what_types_of_disability")%>
					</label>
					</td>
					<td><textarea class="formelement" name="factoryOtherDetailsBean.disabilityDetails"></textarea></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.how_many_men"	id="factory.how_many_men"
								ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_men")%>
					</label>
					</td>
					<td><input type="text" name="factoryOtherDetailsBean.noOfDisabledMen" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.how_many_women"	id="factory.how_many_women"
								ondblclick="callShowDiv(this);"><%=label.get("factory.how_many_women")%>
					</label>
					</td>
					<td><input type="text" name="factoryOtherDetailsBean.noOfDisabledWomen" class="formelement" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td>
					<label class="set"	name="factory.special_assistance_and_support_provided"	id="factory.special_assistance_and_support_provided"
								ondblclick="callShowDiv(this);"><%=label.get("factory.special_assistance_and_support_provided")%>
					</label>
					</td>
					<td><textarea name="factoryOtherDetailsBean.specialAssistanceDetails" class="formelement"></textarea></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<body>
<script>

	onload();
	function onload(){
		callAddCompensationRow();
	}
	function saveFun(){
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="ReturnAct_saveFactoryReturnDetails.action";
		document.getElementById("paraFrm").submit();
	}
	function previousFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callAddCompensationRow(){
		try{
			  var tbl = document.getElementById('compensationTable');
			  var lastRow = tbl.rows.length;
			  var iteration = lastRow-1;
			  var row = tbl.insertRow(lastRow);
			  var yesText = document.createTextNode(" Yes");
				
	   		  var cell0 = row.insertCell(0);
			  var column0 = document.createElement('input');
	  		  column0.type = 'text';
			  column0.name = 'workernameItt';
			  column0.id = 'paraFrm_workernameItt'+iteration;
			  column0.size='15';
			  column0.maxLength='50';
			  cell0.align='left';
			  cell0.appendChild(column0);
			  
	   		  var cell1 = row.insertCell(1);
			  var column1 = document.createElement('input');
	  		  column1.type = 'text';
			  column1.name = 'payscaleItt';
			  column1.id = 'paraFrm_payscaleItt'+iteration;
			  column1.size='15';
			  column1.maxLength='10';
			  cell1.align='left';
			  cell1.appendChild(column1);
	   		 
	   		  var cell2 = row.insertCell(2);
			  var column2 = document.createElement('input');
	  		  column2.type = 'text';
			  column2.name = 'compensationPaidItt';
			  column2.id = 'paraFrm_compensationPaidItt'+iteration;
			  column2.size='15';
			  column2.maxLength='10';
			  cell2.align='left';
			  cell2.appendChild(column2);
	   		  
	   		  var cell3 = row.insertCell(3);
			  var column3 = document.createElement('input');
	  		  column3.type = 'text';
			  column3.name = 'exGratiaItt';
			  column3.id = 'paraFrm_exGratiaItt'+iteration;
			  column3.size='15';
			  column3.maxLength='10';
			  cell3.align='left';
			  cell3.appendChild(column3);
	   		  
	   		  var cell4 = row.insertCell(4);
			  var column4 = document.createElement('input');
	  		  column4.type = 'checkbox';
			  column4.name = 'legalHeirEmployedItt';
			  column4.id = 'paraFrm_legalHeirEmployedItt'+iteration;
			  cell4.align='left';
			  cell4.appendChild(column4);
			  cell4.appendChild(yesText);
			  
			  var cell5 = row.insertCell(5);
			  var column5 = document.createElement('SELECT');
			  column5.name = 'diedItt';
	  		  column5.id = 'paraFrm_diedItt'+iteration;
			  cell5.appendChild(column5);
			  
			  var option = document.createElement('option');
			  option.value = '1';
			  option.appendChild(document.createTextNode('Injured'));
   			  column5.appendChild(option);
   			  
   			  option = document.createElement('option');
			  option.value = '2';
			  option.appendChild(document.createTextNode('Died'));
   			  column5.appendChild(option);
				
			}catch(e){
		}
	}
	function returntolistFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_callReturnList.action';
		document.getElementById('paraFrm').submit();
	}
	function goTo() {
		var combo = trim(document.getElementById('paraFrm_actList').value);
		var pageValue='';
		if(combo==''){
			alert('Please select act');
			return false;
		}
		if(combo=='POG'){
			pageValue='gratuityrules';
		}
		else if(combo=='MHRA'){
			pageValue='rentallowance';
		}
		else if(combo=='POB'){
			pageValue='bonusrules';
		}
		else if(combo=='CL'){
			pageValue='childlabour';
		}
		else if(combo=='ER'){
			pageValue='equalrenumeration';
		}else if(combo=='MB'){
			pageValue='maternitybenefits';
		}else if(combo=='DISH'){
			pageValue='annualfactory';
		}
		document.getElementById('paraFrm_pageToShow').value = pageValue;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	}

</script>