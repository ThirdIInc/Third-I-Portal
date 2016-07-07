package org.paradyne.bean.TravelManagement.Master;

import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

	public class TravelGrade extends BeanBase implements Serializable {
				
		private String gradeCode="";
		private String gradeName;
		private String description;
		private String Status;
		private String hdomainCode;
		private ArrayList gradeList=null;
		private String confChk;
		private String hdeleteCode;
		private String myPage;
		private String show;
		private String selectname;
		private String hiddencode;
		
		/**
		 * Flags Required  for Travel Purpose
		 */
		
		private boolean onLoadFlag=false;
		private boolean saveFlag=false;
		private boolean flag=false;
		private boolean editFlag=false;
		private boolean pageFlag=false;
		private boolean dblFlag=false;
		
		/**
		 * Flags For Cancel Button
		 */
		
		private boolean loadFlag;
		private boolean addFlag;
		private boolean modFlag;
		private boolean dbFlag;
		public String getGradeCode() {
			return gradeCode;
		}
		public void setGradeCode(String gradeCode) {
			this.gradeCode = gradeCode;
		}
		public String getGradeName() {
			return gradeName;
		}
		public void setGradeName(String gradeName) {
			this.gradeName = gradeName;
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
		public ArrayList getGradeList() {
			return gradeList;
		}
		public void setGradeList(ArrayList gradeList) {
			this.gradeList = gradeList;
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

}
