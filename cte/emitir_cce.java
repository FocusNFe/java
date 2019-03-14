import java.util.HashMap;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class EmitirCce {

	public static void main(String[] args){

		String login = "Token_enviado_pelo_suporte";
		
		/* Substituir pela sua identificação interno do CTe. */
		String ref = "12345";
		
		/* Para ambiente de produção use a variável abaixo:
		String server = "https://api.focusnfe.com.br/"; */
		String server = "https://homologacao.focusnfe.com.br/";
		
		String url = server.concat("v2/cte/"+ref+"/carta_correcao");

		/* Aqui criamos um hashmap para receber a chave "correcao" e o valor desejado. */
		HashMap<String, String> correcao = new HashMap<String, String>();
		correcao.put("campo_corrigido", "uf_inicio");
		correcao.put("valor_corrigido", "PR");
		
		/* Criamos um objeto JSON para receber a hash com os dados esperado pela API. */
		JSONObject json = new JSONObject(correcao);
		
		/* Configuração para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		WebResource request = client.resource(url);

		ClientResponse resposta = request.post(ClientResponse.class, json);

		int httpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);

	   /* As três linhas abaixo imprimem as informações retornadas pela API. 
		* Aqui o seu sistema deverá interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(httpCode);
		System.out.printf(body);
	}
}
