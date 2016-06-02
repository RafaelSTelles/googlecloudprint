package br.com.rafaelstelles.gcp.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import br.com.rafaelstelles.gcp.api.exception.CloudPrintException;
import br.com.rafaelstelles.gcp.api.model.PrinterStatus;
import br.com.rafaelstelles.gcp.api.model.SubmitJob;
import br.com.rafaelstelles.gcp.api.model.response.SearchPrinterResponse;
import br.com.rafaelstelles.gcp.api.model.response.SubmitJobResponse;
import br.com.rafaelstelles.gcp.api.util.ResponseUtils;

public abstract class AbstractGoogleCloudPrint {

	private static final String CLOUD_PRINT_URL = "https://www.google.com/cloudprint";
	private static final Gson gson = new Gson();

	private String abrirConexao(String serviceAndParameters) throws CloudPrintException {
		return abrirConexao(serviceAndParameters, null);
	}

	private String abrirConexao(String serviceAndParameters, MultipartEntityBuilder entity) throws CloudPrintException {
        final String accessToken = getAccessToken();

		String response = "";
		HttpPost httpPost = null;
		InputStream inputStream = null;
		try {
			final String request = CLOUD_PRINT_URL + serviceAndParameters;
			final HttpClient httpClient = HttpClientBuilder.create().build();
			httpPost = new HttpPost(request);
			httpPost.setHeader("Authorization", "OAuth " + accessToken);

			if (entity != null) {
				httpPost.setEntity(entity.build());
			}

			final HttpResponse httpResponse = httpClient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();
			response = ResponseUtils.streamToString(inputStream);
		} catch (Exception ex) {
			throw new CloudPrintException(ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					throw new CloudPrintException(ex);
				}
			}

			if (httpPost != null && !httpPost.isAborted()) {
				httpPost.abort();
			}

		}
		return response;
	}

	public SearchPrinterResponse listarTodasImpressoras() throws CloudPrintException {
		String response = abrirConexao("/search?output=json&use_cdd=true", null);
		return gson.fromJson(new StringReader(response), SearchPrinterResponse.class);
	}

	public SearchPrinterResponse buscarImpressora(String nomeImpressora) throws CloudPrintException {
		return buscarImpressora(nomeImpressora, PrinterStatus.ALL);
	}

	public SearchPrinterResponse buscarImpressora(String nomeImpressora, PrinterStatus status) throws CloudPrintException {
		String response = abrirConexao(new StringBuilder().append("/search?output=json")
				.append("&q=").append(nomeImpressora)
				.append("&connection_status=").append(status)
				.toString());

		return gson.fromJson(new StringReader(response), SearchPrinterResponse.class);
	}

	public SubmitJobResponse enviarImpressao(SubmitJob submitJob) throws CloudPrintException {
		String response = "";
		try {
			final byte[] contentBase64 = Base64.getEncoder().encode(submitJob.getContent());

			final MultipartEntityBuilder entity = MultipartEntityBuilder.create()
					.addBinaryBody("content", contentBase64)
					.addTextBody("contentType", submitJob.getContentType())
					.addTextBody("contentTransferEncoding", "base64")
					.addTextBody("title", submitJob.getTitle())
					.addTextBody("ticket", submitJob.getTicketJSON());

			if (submitJob.getTag() != null) {
				for (String tag : submitJob.getTag()) {
					entity.addTextBody("tag", tag);
				}
			}
			response = abrirConexao("/submit?output=json&printerid=" + submitJob.getPrinterId(), entity);
		} catch (Exception ex) {
			throw new CloudPrintException(ex);
		}
		return gson.fromJson(new StringReader(response), SubmitJobResponse.class);
	}

    public abstract String getAccessToken();

}
