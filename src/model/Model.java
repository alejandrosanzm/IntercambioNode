package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controller.Controller;

public class Model {
	private Controller controller;

	public HashMap<Integer, ShoppingList> getProducts() {
		HashMap<String, String> aux = new HashMap<String, String>();
		return parseToObject(sendPost(aux, "http://127.0.0.1:8989/get"));
	}

	public void deleteProduct(int id) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", id + "");
		sendPost(parameters, "http://127.0.0.1:8989/delete");
	}

	public void insertProduct(String name, float quantity, float amount) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", name);
		parameters.put("quantity", quantity + "");
		parameters.put("amount", amount + "");
		sendPost(parameters, "http://127.0.0.1:8989/add");
	}

	private HashMap<Integer, ShoppingList> parseToObject(String json) {
		JsonParser parser = new JsonParser();
		JsonArray gsonArr = parser.parse(json).getAsJsonArray();
		HashMap<Integer, ShoppingList> list = new HashMap<Integer, ShoppingList>();
		for (JsonElement obj : gsonArr) {
			JsonObject gsonObj = obj.getAsJsonObject();

			int id = gsonObj.get("id").getAsInt();
			String name = gsonObj.get("name").getAsString();
			float quantity = gsonObj.get("quantity").getAsFloat();
			float amount = gsonObj.get("amount").getAsFloat();

			ShoppingList sl = new ShoppingList(id, name, quantity, amount);
			list.put(sl.getId(), sl);
		}
		return list;
	}

	private String sendPost(Map<String, String> parameters, String url) {
		String finalResponse = "";
		try {
			HttpPost post = new HttpPost(url);
			// Add request parameter, form parameters
			List<NameValuePair> urlParameters = new ArrayList<>();
			Iterator it = parameters.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				urlParameters.add(new BasicNameValuePair(key, parameters.get(key)));
			}
			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			try (CloseableHttpClient httpClient = HttpClients.createDefault();
					CloseableHttpResponse response = httpClient.execute(post)) {
				finalResponse = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			finalResponse = "Error in POST";
			e.printStackTrace();
		}
		return finalResponse;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
