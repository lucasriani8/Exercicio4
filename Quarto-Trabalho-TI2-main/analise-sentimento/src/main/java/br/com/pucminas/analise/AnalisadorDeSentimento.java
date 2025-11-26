package br.com.pucminas.analise;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.azure.core.credential.AzureKeyCredential;

public class AnalisadorDeSentimento {

    // --- Substitua com os seus dados copiados do Portal Azure ---
    private static final String CHAVE = "COLE_SUA_CHAVE_AQUI";
    private static final String PONTO_DE_EXTREMIDADE = "https://analise-sentimento-henrique.cognitiveservices.azure.com/";

    public static void main(String[] args) {
        // Cria um cliente para se conectar ao serviço
        TextAnalyticsClient client = new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(CHAVE))
                .endpoint(PONTO_DE_EXTREMIDADE)
                .buildClient();

        // Frase de exemplo para analisar
        String textoParaAnalise = "Eu adorei este filme! Os atores foram incríveis e a história me emocionou muito.";
        // String textoParaAnalise = "O serviço de atendimento ao cliente foi péssimo e demorou muito para resolver meu problema.";

        System.out.println("Analisando a frase: \"" + textoParaAnalise + "\"");

        // Chama o serviço da Azure para analisar o sentimento
        DocumentSentiment resultado = client.analyzeSentiment(textoParaAnalise);

        // Imprime o resultado
        System.out.println("------------------------------------");
        System.out.println("Sentimento Geral: " + resultado.getSentiment());
        System.out.println("Pontuações de confiança:");
        System.out.println("  Positivo: " + resultado.getConfidenceScores().getPositive());
        System.out.println("  Neutro: " + resultado.getConfidenceScores().getNeutral());
        System.out.println("  Negativo: " + resultado.getConfidenceScores().getNegative());
        System.out.println("------------------------------------");
    }
}