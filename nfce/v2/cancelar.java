package v2.NFCe;

import java.util.HashMap;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class NFCe_cancelamento {

	public static void main(String[] args){
		
		String login = "Token_enviado_pelo_suporte";

		/* Substituir pela sua identificação interna da nota. */
		String ref = "12345";
		
		/* Para ambiente de produção use a variável abaixo:
		String server = "https://api.focusnfe.com.br/"; */
 		String server = "http://homologacao.acrasnfe.acras.com.br/";
		
		String url = server.concat("v2/nfce/"+ref);
		
		/* Aqui criamos um hashmap para receber a chave "justificativa" e o valor desejado. */		
		HashMap<String, String> justificativa = new HashMap<String, String>();
		justificativa.put("justificativa", "Informe aqui a sua justificativa para realizar o cancelamento da NFCe.");
		
		/* Criamos um objeto JSON para receber a hash com os dados esperado pela API. */
		JSONObject json = new JSONObject(justificativa);
		
		/* Configuração para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		WebResource request = client.resource(url);

		ClientResponse resposta = request.delete(ClientResponse.class, json);

		int httpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);
		
	   /* As três linhas abaixo imprimem as informações retornadas pela API. 
	    * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(httpCode);
		System.out.printf(body);
	}
}