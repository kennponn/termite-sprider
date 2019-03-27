package fun.sprider.core;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import org.apache.http.client.ClientProtocolException;

import lombok.Data;

@Data
public class Processer {
	private String url;
	List<Sprider> spriders;
	public Processer(Sprider [] spriders) {
	this.spriders = Arrays.asList(spriders);
	}
	public void doSprider() throws ClientProtocolException, IOException {
		for (int i = 0; i < spriders.size(); i++) {
			spriders.get(i).doSprider(url);
		}    
	}
}
