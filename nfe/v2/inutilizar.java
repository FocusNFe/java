package v2.NFe;

import java.util.HashMap;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class NFe_inutilizacao {

	public static void main(String[] args) throws JSONException{
		
		String login = "Token_enviado_pelo_suporte";
		
		/* Para ambiente de produ��o use a vari�vel abaixo:
		String server = "https://api.focusnfe.com.br/"; */
 		String server = "http://homologacao.acrasnfe.acras.com.br/";
 		
 		String url = server.concat("v2/nfe/inutilizacao");
 		
 		/* Aqui criamos um hash que ir� receber as chaves e valores esperados para gerar a inutiliza��o. */
		HashMap<String, String> DadosInutilizacao = new HashMap<String, String>();
		DadosInutilizacao.put("cnpj", "51916585009999");
		DadosInutilizacao.put("serie", "9");
		DadosInutilizacao.put("numero_inicial", "7730");
		DadosInutilizacao.put("numero_final", "7732");
		DadosInutilizacao.put("justificativa", "Informe aqui a justificativa para realizar a inutilizacao da numeracao.");
		
		/* Criamos um objeto JSON que ir� receber o input dos dados, para ent�o enviar a requisi��o. */
		JSONObject json = new JSONObject (DadosInutilizacao);
		
		/* Testar se o JSON gerado est� dentro do formato esperado.
		System.out.print(json); */
		
		/* Configura��o para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		WebResource request = client.resource(url);

		ClientResponse resposta = request.post(ClientResponse.class, json);

		int HttpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);
		
		 /* As tr�s linhas abaixo imprimem as informa��es retornadas pela API. 
		  * Aqui o seu sistema dever� interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(HttpCode);
		System.out.printf(body); 
	}
}