package org.paradyne.bean.admin.increment;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/*author:Pradeep Kumar Sahoo
 * Date:11.10.2007
 * 
 */

public class SeniorityList extends BeanBase {
	
	private String empId;
	private String empName;
	private String rankId;
	private String rankName;
	private String dateOfJoining;
	private String dateOfBirth;
	private String seniorNo;
	private String tokenNo;
	private String goFlag="false";
	private String display;
	private String tradeId;
	private String tradeName;
	private String gradeId;
	private String gradeName;
	private String empTypeId;
	private String empType;
	private String seniorityDate;
	
	ArrayList seniorList=null;
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRankId() {
		return rankId;
	}
	public void setRankId(String rankId) {
		this.rankId = rankId;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getSeniorNo() {
		return seniorNo;
	}
	public void setSeniorNo(String seniorNo) {
		this.seniorNo = seniorNo;
	}

	public ArrayList getSeniorList() {
		return seniorList;
	}

	public void setSeniorList(ArrayList seniorList) {
		this.seniorList = seniorList;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getGoFlag() {
		return goFlag;
	}

	public void setGoFlag(String goFlag) {
		this.goFlag = goFlag;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(String empTypeId) {
		this.empTypeId = empTypeId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getSeniorityDate() {
		return seniorityDate;
	}

	public void setSeniorityDate(String seniorityDate) {
		this.seniorityDate = seniorityDate;
	}
 }
