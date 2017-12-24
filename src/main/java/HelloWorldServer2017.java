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

	// @Path("helloworld")
	@Path("/")
	public static class HelloWorldResource { // Must be public

		@GET
		@Path("json")
		@Produces("application/json")
		public Response json(
		// TODO: @QueryParam("rootId") Integer iRootId
		) throws JSONException {
			System.out.println("1");
			JSONObject json = new JSONObject();
			System.out.println("2");
			json.put("foo", "bar");
			System.out.println("3");
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.entity(json.toString()).type("application/json").build();
		}
	}

	public static void main(String[] args) throws Exception {

		Server server = JettyHttpContainerFactory.createServer(UriBuilder
				.fromUri("http://localhost").port(9099).build(),
				new ResourceConfig(HelloWorldResource.class), false);

		// ContextHandler contextHandler = new ContextHandler("/json");
		// contextHandler.setHandler(server.getHandler());

		System.out.println("HelloWorldServer2017.main() server.getHandler() = "
				+ server.getHandler());

		// ResourceHandler resourceHandler = new ResourceHandler();
		// resourceHandler.setWelcomeFiles(new String[] { "index.html" });
		// resourceHandler.setResourceBase(location.toExternalForm());
		System.out.println(HelloWorldServer2017.class.getProtectionDomain()
				.getCodeSource().getLocation().toExternalForm());
		// HandlerCollection handlerCollection = new HandlerCollection();
		// handlerCollection.setHandlers(new Handler[] {
		// // resourceHandler,
		// contextHandler
		// // , new DefaultHandler()
		// });
		// server.setHandler(handlerCollection);
		server.start();
		server.join();
	}
}