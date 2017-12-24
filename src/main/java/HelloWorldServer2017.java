import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class HelloWorldServer2017 {

	private HelloWorldServer2017() {
	}

	@Path("helloworld")
	public static class HelloWorldResource { // Must be public

		@GET
		@Path("json")
		@Produces("application/json")
		public Response json(
		// TODO: @QueryParam("rootId") Integer iRootId
		) throws JSONException {
			JSONObject json = new JSONObject();
			json.put("foo", "bar");
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.entity(json.toString()).type("application/json").build();
		}
	}

	public static void main(String[] args) throws Exception {

		Server server = JettyHttpContainerFactory.createServer(UriBuilder
				.fromUri("http://localhost").port(9099).build(),
				new ResourceConfig(HelloWorldResource.class), false);

		server.start();
		server.join();
	}
}