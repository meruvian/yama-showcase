package org.meruvian.yama.example.api.action;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

@Path("/")
@Service
public class IndexApi {
	@GET
	public Response index() {
		return Response.temporaryRedirect(URI.create("/index.html")).build();
	}
}
