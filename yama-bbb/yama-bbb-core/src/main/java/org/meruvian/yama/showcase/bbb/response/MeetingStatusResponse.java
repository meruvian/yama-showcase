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
public class MeetingStatusResponse {
	private String returncode;
	private String running;

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getRunning() {
		return running;
	}

	public void setRunning(String running) {
		this.running = running;
	}

}
