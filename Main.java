import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String chave = "CHAVE";
        Scanner leitura = new Scanner(System.in);
        String busca = "";



        while(!busca.equalsIgnoreCase("Sair")) {

            System.out.println("Seja bem vindo(a) ao Conversor de Moeda:)");

            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileiro");
            System.out.println("4) Real brasileiro => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Sair");

            System.out.println("Escolha uma opção válida:");
            int opcao = leitura.nextInt();
            System.out.println("************************************************");

            System.out.println("Digite o valor que deseja converter:");
            double valor = leitura.nextDouble();

            String moedaDeInteresse = "";
            String moedaParaConverter = "";

            switch (opcao){
                case 1:
                    moedaDeInteresse = "USD";
                    moedaParaConverter = "ARS";
                    break;
                case 2:
                    moedaDeInteresse = "ARS";
                    moedaParaConverter = "USD";
                    break;
                case 3:
                    moedaDeInteresse = "USD";
                    moedaParaConverter = "BRL";
                    break;
                case 4:
                    moedaDeInteresse = "BRL";
                    moedaParaConverter = "USD";
                    break;
                case 5:
                    moedaDeInteresse = "USD";
                    moedaParaConverter = "COP";
                    break;
                case 6:
                    moedaDeInteresse = "COP";
                    moedaParaConverter = "USD";
                    break;
                case 7:
                    busca = "Sair";
                    break;
            }

            String endereco = "https://v6.exchangerate-api.com/v6/" + chave + "/latest/" + moedaDeInteresse;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String resposta = response.body();
            Gson gson = new Gson();
            JsonObject json = JsonParser.parseString(resposta).getAsJsonObject();
            JsonObject moedas = json.getAsJsonObject("conversion_rates");

            double valorFinal = moedas.get(moedaParaConverter).getAsDouble() * valor;
            System.out.println(valorFinal);

            if(busca.equalsIgnoreCase("Sair")) {
                break;
            }


        }

    }
}
