package org.paradyne.bean.TravelManagement.Master;
import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

	public class HotelType  extends BeanBase implements Serializable {
		
		//added by Debajani
		
		private boolean addnewFlag=false;
		private boolean noflag=true;
		private boolean cancelflag=false;
		private String callpageflag;
		
		//ends
		//for priority
		private String itLevel="";
		private String hidLevel="";
		private String previousLevel="";
		private String currentLevel="";
		private String firstRecordOfPage="";
		private String lastRecordOfPage="";
		
		
		private String hotelCode="";
		private String hotelName;
		private String description;
		private String Status;
		private String hdomainCode;
		private ArrayList hotelList=null;
		private String confChk;
		private String hdeleteCode;
		private String myPage;
		private String show;
		private String selectname;
		private String hiddencode;
		private String level;
		private String levelId;
		private String modeLength;
		private String statusUp;
		private String statusDown;
		private String upId;
		/**
		 * Flags Required  for Travel Purpose
		 */
		
		private boolean onLoadFlag=true;
		private boolean saveFlag=false;
		private boolean flag=false;
		private boolean editFlag=false;
		private boolean pageFlag=false;
		private boolean dblFlag=false;
		private boolean editableflag=false;
		
		
		/**
		 * Flags For Cancel Button
		 */
		
		private boolean loadFlag;
		private boolean addFlag;
		private boolean modFlag;
		private boolean dbFlag;
		
		public String getHotelCode() {
			return hotelCode;
		}
		public void setHotelCode(String hotelCode) {
			this.hotelCode = hotelCode;
		}
		public String getHotelName() {
			return hotelName;
		}
		public void setHotelName(String hotelName) {
			this.hotelName = hotelName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getStatus() {
			return Status;
		}
		public void setStatus(String status) {
			Status = status;
		}
		public String getHdomainCode() {
			return hdomainCode;
		}
		public void setHdomainCode(String hdomainCode) {
			this.hdomainCode = hdomainCode;
		}
		public ArrayList getHotelList() {
			return hotelList;
		}
		public void setHotelList(ArrayList hotelList) {
			this.hotelList = hotelList;
		}
		public String getConfChk() {
			return confChk;
		}
		public void setConfChk(String confChk) {
			this.confChk = confChk;
		}
		public String getHdeleteCode() {
			return hdeleteCode;
		}
		public void setHdeleteCode(String hdeleteCode) {
			this.hdeleteCode = hdeleteCode;
		}
		public String getMyPage() {
			return myPage;
		}
		public void setMyPage(String myPage) {
			this.myPage = myPage;
		}
		public String getShow() {
			return show;
		}
		public void setShow(String show) {
			this.show = show;
		}
		public String getSelectname() {
			return selectname;
		}
		public void setSelectname(String selectname) {
			this.selectname = selectname;
		}
		public String getHiddencode() {
			return hiddencode;
		}
		public void setHiddencode(String hiddencode) {
			this.hiddencode = hiddencode;
		}
		public boolean isOnLoadFlag() {
			return onLoadFlag;
		}
		public void setOnLoadFlag(boolean onLoadFlag) {
			this.onLoadFlag = onLoadFlag;
		}
		public boolean isSaveFlag() {
			return saveFlag;
		}
		public void setSaveFlag(boolean saveFlag) {
			this.saveFlag = saveFlag;
		}
		public boolean isFlag() {
			return flag;
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		public boolean isEditFlag() {
			return editFlag;
		}
		public void setEditFlag(boolean editFlag) {
			this.editFlag = editFlag;
		}
		public boolean isPageFlag() {
			return pageFlag;
		}
		public void setPageFlag(boolean pageFlag) {
			this.pageFlag = pageFlag;
		}
		public boolean isDblFlag() {
			return dblFlag;
		}
		public void setDblFlag(boolean dblFlag) {
			this.dblFlag = dblFlag;
		}
		public boolean isLoadFlag() {
			return loadFlag;
		}
		public void setLoadFlag(boolean loadFlag) {
			this.loadFlag = loadFlag;
		}
		public boolean isAddFlag() {
			return addFlag;
		}
		public void setAddFlag(boolean addFlag) {
			this.addFlag = addFlag;
		}
		public boolean isModFlag() {
			return modFlag;
		}
		public void setModFlag(boolean modFlag) {
			this.modFlag = modFlag;
		}
		public boolean isDbFlag() {
			return dbFlag;
		}
		public void setDbFlag(boolean dbFlag) {
			this.dbFlag = dbFlag;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public String getLevelId() {
			return levelId;
		}
		public void setLevelId(String levelId) {
			this.levelId = levelId;
		}
		public String getModeLength() {
			return modeLength;
		}
		public void setModeLength(String modeLength) {
			this.modeLength = modeLength;
		}
		public String getStatusUp() {
			return statusUp;
		}
		public void setStatusUp(String statusUp) {
			this.statusUp = statusUp;
		}
		public String getStatusDown() {
			return statusDown;
		}
		public void setStatusDown(String statusDown) {
			this.statusDown = statusDown;
		}
		public String getUpId() {
			return upId;
		}
		public void setUpId(String upId) {
			this.upId = upId;
		}
		public boolean isEditableflag() {
			return editableflag;
		}
		public void setEditableflag(boolean editableflag) {
			this.editableflag = editableflag;
		}
		/**
		 * @return the addnewFlag
		 */
		public boolean isAddnewFlag() {
			return addnewFlag;
		}
		/**
		 * @param addnewFlag the addnewFlag to set
		 */
		public void setAddnewFlag(boolean addnewFlag) {
			this.addnewFlag = addnewFlag;
		}
		/**
		 * @return the noflag
		 */
		public boolean isNoflag() {
			return noflag;
		}
		/**
		 * @param noflag the noflag to set
		 */
		public void setNoflag(boolean noflag) {
			this.noflag = noflag;
		}
		/**
		 * @return the cancelflag
		 */
		public boolean isCancelflag() {
			return cancelflag;
		}
		/**
		 * @param cancelflag the cancelflag to set
		 */
		public void setCancelflag(boolean cancelflag) {
			this.cancelflag = cancelflag;
		}
		/**
		 * @return the callpageflag
		 */
		/**
		 * @return the callpageflag
		 */
		public String getCallpageflag() {
			return callpageflag;
		}
		/**
		 * @param callpageflag the callpageflag to set
		 */
		public void setCallpageflag(String callpageflag) {
			this.callpageflag = callpageflag;
		}
		/**
		 * @return the itLevel
		 */
		public String getItLevel() {
			return itLevel;
		}
		/**
		 * @param itLevel the itLevel to set
		 */
		public void setItLevel(String itLevel) {
			this.itLevel = itLevel;
		}
		/**
		 * @return the hidLevel
		 */
		public String getHidLevel() {
			return hidLevel;
		}
		/**
		 * @param hidLevel the hidLevel to set
		 */
		public void setHidLevel(String hidLevel) {
			this.hidLevel = hidLevel;
		}
		/**
		 * @return the previousLevel
		 */
		public String getPreviousLevel() {
			return previousLevel;
		}
		/**
		 * @param previousLevel the previousLevel to set
		 */
		public void setPreviousLevel(String previousLevel) {
			this.previousLevel = previousLevel;
		}
		/**
		 * @return the currentLevel
		 */
		public String getCurrentLevel() {
			return currentLevel;
		}
		/**
		 * @param currentLevel the currentLevel to set
		 */
		public void setCurrentLevel(String currentLevel) {
			this.currentLevel = currentLevel;
		}
		/**
		 * @return the firstRecordOfPage
		 */
		public String getFirstRecordOfPage() {
			return firstRecordOfPage;
		}
		/**
		 * @param firstRecordOfPage the firstRecordOfPage to set
		 */
		public void setFirstRecordOfPage(String firstRecordOfPage) {
			this.firstRecordOfPage = firstRecordOfPage;
		}
		/**
		 * @return the lastRecordOfPage
		 */
		public String getLastRecordOfPage() {
			return lastRecordOfPage;
		}
		/**
		 * @param lastRecordOfPage the lastRecordOfPage to set
		 */
		public void setLastRecordOfPage(String lastRecordOfPage) {
			this.lastRecordOfPage = lastRecordOfPage;
		}
		
		}



