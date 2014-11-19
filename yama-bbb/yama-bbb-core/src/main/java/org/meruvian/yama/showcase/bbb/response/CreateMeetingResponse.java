/**
 * 
 */
package org.meruvian.yama.showcase.bbb.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author dianw
 * 
 */
@JacksonXmlRootElement(localName = "response")
public class CreateMeetingResponse {
	private String returncode;
	private Meeting meeting;

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

}
