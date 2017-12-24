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
	public Map<String, String> getQueryMap(String query) {
		Pattern pattern = Pattern.compile("/\\?.*");

		Matcher matcher = pattern.matcher(query);
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}

	public void handle(HttpExchange t) throws IOException {
		System.out.println("HelloWorldSunServer.handle() ");
		JSONObject json = new JSONObject();
		String query = t.getRequestURI().toString();
		Map<String, String> map = getQueryMap(query);
		String value = map.get("param1");
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

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(4444), 0);
		server.createContext("/", new HelloWorldSunServer());
		server.setExecutor(null); // creates a default executor
		server.start();
	}
}
