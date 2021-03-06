import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Main {

	public static void main(String[] args){

		/* Para ambiente de produ��o use a vari�vel abaixo:
		String server = "https://api.focusnfe.com.br/"; */
 		String server = "http://homologacao.acrasnfe.acras.com.br/";

		String token =  "token_enviado_pelo_suporte ";

		/* Substituir pela sua identifica��o interna da nota. */
		String ref = "12345";

		Client client = Client.create();

		String url = server.concat("nfse/"+ref+"?"+ "token="+ token);

		WebResource request = client.resource(url);

		ClientResponse resposta = request.get(ClientResponse.class);

		int httpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);

		/* As tr�s linhas abaixo imprimem as informa��es retornadas pela API. 
		 * Aqui o seu sistema dever� interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(httpCode);
		System.out.printf(body);
	}
}
