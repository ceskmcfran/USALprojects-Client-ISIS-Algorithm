import java.net.URI;


//TODO hacer que conecte con uribuilder y webtarget
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ipServer[] = new String[3];
		int isISIS = 0;
		final int numParams = 5;

		//TODO Coger los parametros ipServer y isISIS

		//Enviar
		for(int i = 0 ; i < 3; i++){
			String requested = i+";"+ipServer[0]+";"+ipServer[1]+";"+ipServer[2]+";"+isISIS;

			Client client = ClientBuilder.newClient();
			URI uri=UriBuilder.fromUri("http://" + ipServer[i] + ":8080/PracticaIsis/").build();
			WebTarget target = client.target(uri);

			target.path("rest").path("server/create")
			.queryParam("params", requested)
			.queryParam("numParams", numParams)
			.request(MediaType.TEXT_PLAIN).get(String.class);
		}
	}

}
