/**
 * 
 */
package org.meruvian.yama.showcase.bbb.response;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.meruvian.yama.core.commons.Name;
import org.meruvian.yama.core.user.JpaUserRepository;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.showcase.bbb.room.Room;
import org.meruvian.yama.showcase.bbb.room.RoomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * @author dianw
 *
 */
@Service
public class RestBBBService implements BBBService {
	@Inject
	private RoomRepository roomRepository;
	
	@Inject
	private JpaUserRepository userRepository;
	
	@Value("${conference.bbb.api.secret}")
	private String apiSecret;
	
	@Value("${conference.bbb.api.url}")
	private String apiUri;
	
	private RestTemplate restTemplate;
	
	public RestBBBService() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new JacksonXmlMessageConverter());
		
		this.restTemplate = new RestTemplate(messageConverters);
	}
	
	@Override
	public MeetingInfoResponse getMeetingInfoById(String roomId) {
		Room room = roomRepository.findById(roomId);
		
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		valuePairs.add(new BasicNameValuePair("meetingID", room.getId()));
		valuePairs.add(new BasicNameValuePair("password", room.getModeratorPassword()));
		
		String param = addChecksumToParameter("getMeetingInfo", valuePairs);
		String requestUri = StringUtils.join(apiUri, "getMeetingInfo?", param);
		
		return getForObject(requestUri, MeetingInfoResponse.class);
	}

	@Override
	public CreateMeetingResponse createMeeting(String roomId) {
		Room room = roomRepository.findById(roomId);
		
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		valuePairs.add(new BasicNameValuePair("meetingID", room.getId()));
		valuePairs.add(new BasicNameValuePair("name", room.getName()));
		valuePairs.add(new BasicNameValuePair("welcome", room.getWelcome()));
		valuePairs.add(new BasicNameValuePair("attendeePW", room.getAttendeePassword()));
		valuePairs.add(new BasicNameValuePair("moderatorPW", room.getModeratorPassword()));
		valuePairs.add(new BasicNameValuePair("record", Boolean.toString(room.isRecord())));

		String param = addChecksumToParameter("create", valuePairs);
		String requestUri = StringUtils.join(apiUri, "create?", param);
		
		return getForObject(requestUri, CreateMeetingResponse.class);
	}

	@Override
	public EndMeetingResponse endMeeting(String roomId) {
		Room room = roomRepository.findById(roomId);
		
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		valuePairs.add(new BasicNameValuePair("meetingID", room.getId()));
		valuePairs.add(new BasicNameValuePair("password", room.getModeratorPassword()));
		
		String param = addChecksumToParameter("end", valuePairs);
		String requestUri = StringUtils.join(apiUri, "end?", param);
		
		return getForObject(requestUri, EndMeetingResponse.class);
	}

	@Override
	public String joinMeeting(String roomId, String userId, boolean asModerator) {
		createMeeting(roomId);
		
		Room room = roomRepository.findById(roomId);
		User user = userRepository.findById(userId);
		
		Name name = user.getName();
		name = name == null ? new Name() : name;
		String password = asModerator ? room.getModeratorPassword() : room.getAttendeePassword();
		
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		valuePairs.add(new BasicNameValuePair("meetingID", room.getId()));
		valuePairs.add(new BasicNameValuePair("fullName", StringUtils.join(name.getFirst(), " ", name.getLast())));
		valuePairs.add(new BasicNameValuePair("userID", user.getUsername()));
		valuePairs.add(new BasicNameValuePair("password", password));
		
		String param = addChecksumToParameter("join", valuePairs);
		
		return StringUtils.join(apiUri, "join?", param);
	}
	
	private <T> T getForObject(String requestUri, Class<T> responseType) {
		try {
			return restTemplate.getForObject(new URI(requestUri), responseType);
		} catch (Exception e) {
			return restTemplate.getForObject(requestUri, responseType);
		}
	}
	
	private String addChecksumToParameter(String action, List<NameValuePair> pairs) {
		String qParam = URLEncodedUtils.format(pairs, "UTF-8");
		String prechecksum = StringUtils.join(action, qParam, apiSecret);
		
		pairs.add(new BasicNameValuePair("checksum", DigestUtils.shaHex(prechecksum)));
		
		return URLEncodedUtils.format(pairs, "UTF-8");
	}

	public static class JacksonXmlMessageConverter extends AbstractHttpMessageConverter<Object> {

		private XmlMapper xmlMapper;

		public JacksonXmlMessageConverter() {
			super(new MediaType("application", "xml"), 
					new MediaType("text", "xml"));
			
			this.xmlMapper = new XmlMapper();
			this.xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			this.xmlMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		}

		@Override
		protected boolean supports(Class<?> clazz) {
			return !Collection.class.isAssignableFrom(clazz);
		}

		@Override
		protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException,
				HttpMessageNotReadableException {
			return xmlMapper.readValue(inputMessage.getBody(), clazz);
		}

		@Override
		protected void writeInternal(Object t, HttpOutputMessage outputMessage)
				throws IOException, HttpMessageNotWritableException {
			xmlMapper.writeValue(outputMessage.getBody(), t);
		}
	}
	
}
