/**
 * Copyright 2014 Meruvian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meruvian.yama.showcase.social.connect;

import org.meruvian.yama.repository.user.User;
import org.meruvian.yama.showcase.social.api.Mervpolis;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

/**
 * @author Dian Aditya
 *
 */
public class MervpolisAdapter implements ApiAdapter<Mervpolis>{

	@Override
	public boolean test(Mervpolis api) {
		return false;
	}

	@Override
	public void setConnectionValues(Mervpolis api, ConnectionValues values) {
		System.out.println(api.userOperations());
		User user = api.userOperations().getUser();
		values.setProviderUserId(user.getId());
		values.setDisplayName(user.getUsername());
		values.setProfileUrl("http://id.mervpolis.com/u/" + user.getUsername());
	}

	@Override
	public UserProfile fetchUserProfile(Mervpolis api) {
		User user = api.userOperations().getUser();
		
		return new UserProfileBuilder().setName(user.getUsername())
				.setFirstName(user.getName().getFirst()).setLastName(user.getName().getLast())
				.setEmail(user.getEmail()).setUsername(user.getUsername()).build();
	}

	@Override
	public void updateStatus(Mervpolis api, String message) {
	}

}
