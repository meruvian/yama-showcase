/**
 * 
 */
package org.meruvian.yama.showcase.bbb.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author dianw
 * 
 */
@JacksonXmlRootElement(localName = "response")
public class MeetingResponse {
	private String returncode;
	@JacksonXmlProperty(localName = "meeting")
	@JacksonXmlElementWrapper(localName = "meetings")
	private List<Meeting> meetings = new ArrayList<Meeting>();

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}
}
