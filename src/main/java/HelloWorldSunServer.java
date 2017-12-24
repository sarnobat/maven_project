import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.apache.commons.io.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HelloWorldSunServer implements HttpHandler {

	public void handle(HttpExchange t) throws IOException {
		System.out.println("HelloWorldSunServer.handle() 1");
		JSONObject json = new JSONObject();
		System.out.println("HelloWorldSunServer.handle() 2");
		String query = t.getRequestURI().toString();
		System.out.println("HelloWorldSunServer.handle() 3");
		Map<String, String> map = getQueryMap(query);
		System.out.println("HelloWorldSunServer.handle() 4");
		String value = map.get("param1");
		System.out.println("HelloWorldSunServer.handle() 5");
		json.put("myKey", value);
		System.out.println("Request headers: " + t.getRequestHeaders());
		System.out.println("Request URI " + t.getRequestURI());
		System.out.println("value: " + value);
		json.put("foo", "bar");
		t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
		t.getResponseHeaders().add("Content-type", "application/json");
		t.sendResponseHeaders(200, json.toString().length());
		OutputStream os = t.getResponseBody();
		os.write(json.toString().getBytes());
		os.close();
	}

	private Map<String, String> getQueryMap(String query) {
		try {
			Pattern pattern = Pattern.compile("/\\?.*");

			Matcher matcher = pattern.matcher(query);
			String[] params = query.split("&");
			Map<String, String> map = new HashMap<String, String>();
			for (String param : params) {
				String[] tokens = param.split("=");
				String name = tokens[0];
				String value;
				if (tokens.length > 1) {
					value = tokens[1];
				} else {
					value = "unspecified";
				}
				map.put(name, value);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(4444), 0);
		server.createContext("/", new HelloWorldSunServer());
		server.setExecutor(null); // creates a default executor
		server.start();
	}
}
