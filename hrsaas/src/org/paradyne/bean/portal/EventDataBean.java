/**
 * 
 */
package org.paradyne.bean.portal;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0491
 *
 */
public class EventDataBean extends BeanBase {

	private boolean yearFlag  =false;
	private boolean imageDataFlag =false;
	private boolean eventDataFlag =false;
	private boolean videoDataFlag =false;
	private boolean contentDataFlag =false;
	public boolean isYearFlag() {
		return yearFlag;
	}
	public void setYearFlag(boolean yearFlag) {
		this.yearFlag = yearFlag;
	}
	public boolean isImageDataFlag() {
		return imageDataFlag;
	}
	public void setImageDataFlag(boolean imageDataFlag) {
		this.imageDataFlag = imageDataFlag;
	}
	public boolean isEventDataFlag() {
		return eventDataFlag;
	}
	public void setEventDataFlag(boolean eventDataFlag) {
		this.eventDataFlag = eventDataFlag;
	}
	public boolean isVideoDataFlag() {
		return videoDataFlag;
	}
	public void setVideoDataFlag(boolean videoDataFlag) {
		this.videoDataFlag = videoDataFlag;
	}
	public boolean isContentDataFlag() {
		return contentDataFlag;
	}
	public void setContentDataFlag(boolean contentDataFlag) {
		this.contentDataFlag = contentDataFlag;
	}
}
