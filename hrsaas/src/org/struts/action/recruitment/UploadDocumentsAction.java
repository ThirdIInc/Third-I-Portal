package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.Recruitment.UploadDocuments;
import org.paradyne.model.recruitment.UploadDocumentsModel;
import org.struts.lib.DyneActionSupport;

public class UploadDocumentsAction extends DyneActionSupport {

	UploadDocuments bean = null;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new UploadDocuments();
		bean.setMenuCode(5019);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public UploadDocuments getBean() {
		return bean;
	}

	public void setBean(UploadDocuments bean) {
		this.bean = bean;
	}

	public String showCheckList() {
		try {
			UploadDocumentsModel model = new UploadDocumentsModel();
			model.initiate(context, session);
			
			String requisitionCode =request.getParameter("requisitionCode");
			
			bean.setRequisitionCode(requisitionCode);
			
			System.out.println("requisitionCode  "+requisitionCode);

			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}

			String fileDataPath = getText("data_path") + "/upload" + poolName
					+ "/Recruitment/";
			bean.setDataPath(fileDataPath);
			
			
			String maxCandCode = "  select CAND_DATABANK_CODE from  HRMS_REC_CAND_LOGIN where CAND_CODE="
				+ bean.getCandidateUserEmpId();
			Object[][]maxCandDatabankCode = model.getSqlModel().getSingleResult(maxCandCode);

		

			String query = " SELECT BG_CODE FROM HRMS_REC_BGCHECK "
					+ "WHERE BG_REQS_CODE="+requisitionCode+" AND BG_CAND_CODE ="+String.valueOf(maxCandDatabankCode[0][0]);

			Object dataobj[][] = model.getSqlModel().getSingleResult(query);

			if (dataobj != null && dataobj.length > 0) {
				bean.setHiddenCode(String.valueOf(dataobj[0][0]));
				model.setSaveDocs(bean);
			} else {
				model.showCheckList(bean);
			}

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String save() {
		try {
			UploadDocumentsModel model = new UploadDocumentsModel();
			model.initiate(context, session);

			Object[] ckcode = request.getParameterValues("checkListitemcode");
			Object[] dtlproof = request.getParameterValues("uploadLocFileName");

			if (bean.getHiddenCode().equals("")) {
				boolean flag = model.save(ckcode, dtlproof, bean);

				if (flag) {
					addActionMessage("Record saved successfully.");
				} else {
					addActionMessage("Record can not saved.");
				}
			} else {
				boolean flag = model.update(ckcode, dtlproof, bean);

				if (flag) {
					addActionMessage("Record updated successfully.");
				} else {
					addActionMessage("Record can not updated.");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return showCheckList();
	}

	public void viewAttachedProof() throws Exception {
		try {
			String uploadFileName = request.getParameter("fileName");
			String dataPath = request.getParameter("dataPath");
			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				fileName = uploadFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} else if (ext.equals("doc")) {
					mimeType = "msword";
				} else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} else if (ext.equals("txt")) {
					mimeType = "txt";
				} else if (ext.equals("gif")) {
					mimeType = "gif";
				}
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					}
				}

				// for getting server path where configuration files are saved.
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					// response.setHeader("Content-type",
					// "application/"+mimeType+"");
				} // end of if
				else {
					response.setHeader("Content-type", "application/"
							+ mimeType + "");
				}
				response.setHeader("Content-disposition", "inline;filename=\""
						+ fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}

			} catch (FileNotFoundException e) {

				addActionMessage("proof document not found");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fsStream != null) {
					fsStream.close();
				}
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
