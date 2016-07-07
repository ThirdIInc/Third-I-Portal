package org.paradyne.bean.TravelManagement.TravelProcess;

import org.paradyne.lib.BeanBase;

/**
 * @author krishna date: 16-JULY-2009
 * 
 */
public class TmsTrvlProcessMngr extends BeanBase {
	private String optionValue;

	private String reqFlag = "";
	private String reqAppFlag = "";
	private String schdFlag = "";
	private String schdAppFlag = "";

	private String apprMainSch = "";
	private String confFlag = "";
	private String claimFlag = "";
	private String claimAppFlag = "";
	private String levelFlag = "";

	private String fromDate = "";
	private String effectDate = "";

	private String hidreqFlag = "";
	private String hidreqAppFlag = "";
	private String hidschdFlag = "";
	private String hidschdAppFlag = "";

	private String hidapprMainSch = "";
	private String hidconfFlag = "";
	private String hidclaimFlag = "";
	private String hidclaimAppFlag = "";
	private String hidlevelFlag = "";
	private String hidfromDate = "";
	private String effectiveDate = "";

	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	private boolean onLoadFlag = false;
	private boolean flag = false;
	private boolean saveFlag = false;

	/**
	 * @return the optionValue
	 */
	public String getOptionValue() {
		return optionValue;
	}

	/**
	 * @param optionValue
	 *            the optionValue to set
	 */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	/**
	 * @return the reqFlag
	 */
	public String getReqFlag() {
		return reqFlag;
	}

	/**
	 * @param reqFlag
	 *            the reqFlag to set
	 */
	public void setReqFlag(String reqFlag) {
		this.reqFlag = reqFlag;
	}

	/**
	 * @return the reqAppFlag
	 */
	public String getReqAppFlag() {
		return reqAppFlag;
	}

	/**
	 * @param reqAppFlag
	 *            the reqAppFlag to set
	 */
	public void setReqAppFlag(String reqAppFlag) {
		this.reqAppFlag = reqAppFlag;
	}

	/**
	 * @return the schdFlag
	 */
	public String getSchdFlag() {
		return schdFlag;
	}

	/**
	 * @param schdFlag
	 *            the schdFlag to set
	 */
	public void setSchdFlag(String schdFlag) {
		this.schdFlag = schdFlag;
	}

	/**
	 * @return the schdAppFlag
	 */
	public String getSchdAppFlag() {
		return schdAppFlag;
	}

	/**
	 * @param schdAppFlag
	 *            the schdAppFlag to set
	 */
	public void setSchdAppFlag(String schdAppFlag) {
		this.schdAppFlag = schdAppFlag;
	}

	/**
	 * @return the apprMainSch
	 */
	public String getApprMainSch() {
		return apprMainSch;
	}

	/**
	 * @param apprMainSch
	 *            the apprMainSch to set
	 */
	public void setApprMainSch(String apprMainSch) {
		this.apprMainSch = apprMainSch;
	}

	/**
	 * @return the confFlag
	 */
	public String getConfFlag() {
		return confFlag;
	}

	/**
	 * @param confFlag
	 *            the confFlag to set
	 */
	public void setConfFlag(String confFlag) {
		this.confFlag = confFlag;
	}

	/**
	 * @return the claimFlag
	 */
	public String getClaimFlag() {
		return claimFlag;
	}

	/**
	 * @param claimFlag
	 *            the claimFlag to set
	 */
	public void setClaimFlag(String claimFlag) {
		this.claimFlag = claimFlag;
	}

	/**
	 * @return the claimAppFlag
	 */
	public String getClaimAppFlag() {
		return claimAppFlag;
	}

	/**
	 * @param claimAppFlag
	 *            the claimAppFlag to set
	 */
	public void setClaimAppFlag(String claimAppFlag) {
		this.claimAppFlag = claimAppFlag;
	}

	/**
	 * @return the levelFlag
	 */
	public String getLevelFlag() {
		return levelFlag;
	}

	/**
	 * @param levelFlag
	 *            the levelFlag to set
	 */
	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the effectDate
	 */
	public String getEffectDate() {
		return effectDate;
	}

	/**
	 * @param effectDate
	 *            the effectDate to set
	 */
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	/**
	 * @return the hidreqFlag
	 */
	public String getHidreqFlag() {
		return hidreqFlag;
	}

	/**
	 * @param hidreqFlag
	 *            the hidreqFlag to set
	 */
	public void setHidreqFlag(String hidreqFlag) {
		this.hidreqFlag = hidreqFlag;
	}

	/**
	 * @return the hidreqAppFlag
	 */
	public String getHidreqAppFlag() {
		return hidreqAppFlag;
	}

	/**
	 * @param hidreqAppFlag
	 *            the hidreqAppFlag to set
	 */
	public void setHidreqAppFlag(String hidreqAppFlag) {
		this.hidreqAppFlag = hidreqAppFlag;
	}

	/**
	 * @return the hidschdFlag
	 */
	public String getHidschdFlag() {
		return hidschdFlag;
	}

	/**
	 * @param hidschdFlag
	 *            the hidschdFlag to set
	 */
	public void setHidschdFlag(String hidschdFlag) {
		this.hidschdFlag = hidschdFlag;
	}

	/**
	 * @return the hidschdAppFlag
	 */
	public String getHidschdAppFlag() {
		return hidschdAppFlag;
	}

	/**
	 * @param hidschdAppFlag
	 *            the hidschdAppFlag to set
	 */
	public void setHidschdAppFlag(String hidschdAppFlag) {
		this.hidschdAppFlag = hidschdAppFlag;
	}

	/**
	 * @return the hidapprMainSch
	 */
	public String getHidapprMainSch() {
		return hidapprMainSch;
	}

	/**
	 * @param hidapprMainSch
	 *            the hidapprMainSch to set
	 */
	public void setHidapprMainSch(String hidapprMainSch) {
		this.hidapprMainSch = hidapprMainSch;
	}

	/**
	 * @return the hidconfFlag
	 */
	public String getHidconfFlag() {
		return hidconfFlag;
	}

	/**
	 * @param hidconfFlag
	 *            the hidconfFlag to set
	 */
	public void setHidconfFlag(String hidconfFlag) {
		this.hidconfFlag = hidconfFlag;
	}

	/**
	 * @return the hidclaimFlag
	 */
	public String getHidclaimFlag() {
		return hidclaimFlag;
	}

	/**
	 * @param hidclaimFlag
	 *            the hidclaimFlag to set
	 */
	public void setHidclaimFlag(String hidclaimFlag) {
		this.hidclaimFlag = hidclaimFlag;
	}

	/**
	 * @return the hidclaimAppFlag
	 */
	public String getHidclaimAppFlag() {
		return hidclaimAppFlag;
	}

	/**
	 * @param hidclaimAppFlag
	 *            the hidclaimAppFlag to set
	 */
	public void setHidclaimAppFlag(String hidclaimAppFlag) {
		this.hidclaimAppFlag = hidclaimAppFlag;
	}

	/**
	 * @return the hidlevelFlag
	 */
	public String getHidlevelFlag() {
		return hidlevelFlag;
	}

	/**
	 * @param hidlevelFlag
	 *            the hidlevelFlag to set
	 */
	public void setHidlevelFlag(String hidlevelFlag) {
		this.hidlevelFlag = hidlevelFlag;
	}

	/**
	 * @return the hidfromDate
	 */
	public String getHidfromDate() {
		return hidfromDate;
	}

	/**
	 * @param hidfromDate
	 *            the hidfromDate to set
	 */
	public void setHidfromDate(String hidfromDate) {
		this.hidfromDate = hidfromDate;
	}

	/**
	 * @return the effectiveDate
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the loadFlag
	 */
	public boolean isLoadFlag() {
		return loadFlag;
	}

	/**
	 * @param loadFlag
	 *            the loadFlag to set
	 */
	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}

	/**
	 * @return the addFlag
	 */
	public boolean isAddFlag() {
		return addFlag;
	}

	/**
	 * @param addFlag
	 *            the addFlag to set
	 */
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	/**
	 * @return the modFlag
	 */
	public boolean isModFlag() {
		return modFlag;
	}

	/**
	 * @param modFlag
	 *            the modFlag to set
	 */
	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}

	/**
	 * @return the dbFlag
	 */
	public boolean isDbFlag() {
		return dbFlag;
	}

	/**
	 * @param dbFlag
	 *            the dbFlag to set
	 */
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}

	/**
	 * @return the onLoadFlag
	 */
	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}

	/**
	 * @param onLoadFlag
	 *            the onLoadFlag to set
	 */
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the saveFlag
	 */
	public boolean isSaveFlag() {
		return saveFlag;
	}

	/**
	 * @param saveFlag the saveFlag to set
	 */
	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}

}
