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
public class EndMeetingResponse {
	private String returncode;
	private String messageKey;
	private String message;

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
