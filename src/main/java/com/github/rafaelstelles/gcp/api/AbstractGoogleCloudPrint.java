package com.github.rafaelstelles.gcp.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.github.rafaelstelles.gcp.api.exception.CloudPrintException;
import com.github.rafaelstelles.gcp.api.model.CopiesTicket;
import com.github.rafaelstelles.gcp.api.model.Document;
import com.github.rafaelstelles.gcp.api.model.PrintTicket;
import com.github.rafaelstelles.gcp.api.model.PrinterStatus;
import com.github.rafaelstelles.gcp.api.model.SearchPrinterResponse;
import com.github.rafaelstelles.gcp.api.model.SubmitJob;
import com.github.rafaelstelles.gcp.api.model.SubmitJobResponse;
import com.github.rafaelstelles.gcp.api.model.Ticket;
import com.github.rafaelstelles.gcp.api.util.ResponseUtils;
import com.google.gson.Gson;

import lombok.extern.java.Log;

@Log
public abstract class AbstractGoogleCloudPrint {

	private static final String CLOUD_PRINT_URL = "https://www.google.com/cloudprint";
	private static final Gson gson = new Gson();

	private String openConnection(final String serviceAndParameters) throws CloudPrintException {
		return openConnection(serviceAndParameters, null);
	}

	private String openConnection(final String serviceAndParameters, final MultipartEntityBuilder entity) throws CloudPrintException {
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

	public SearchPrinterResponse findAllPrinters() throws CloudPrintException {
		String response = openConnection("/search?output=json&use_cdd=true", null);
		return gson.fromJson(new StringReader(response), SearchPrinterResponse.class);
	}

	public SearchPrinterResponse findPrinter(final String printerName) throws CloudPrintException {
		return findPrinter(printerName, PrinterStatus.ALL);
	}

	public SearchPrinterResponse findPrinter(final String printerName, final PrinterStatus status) throws CloudPrintException {
		String response = openConnection("/search?output=json" +
				"&q=" + printerName +
				"&connection_status=" + status);

		return gson.fromJson(new StringReader(response), SearchPrinterResponse.class);
	}

	public void sendDocumentToPrint(final Document document, final String printerId) throws CloudPrintException {
		sendDocumentToPrint(document.getFileName(), document.getContentType(), printerId,
				document.getFile(), document.getCopies());
	}

	public void sendDocumentToPrint(final String title, final String contentType, final String printerId,
									final byte[] content, final int copias) throws CloudPrintException {
		try {
			final Ticket ticket = new Ticket();
			final PrintTicket print = new PrintTicket();
			final CopiesTicket copiesTicket = new CopiesTicket(copias);
			print.setCopies(copiesTicket);
			ticket.setPrint(print);

			SubmitJob submitJob = new SubmitJob();
			submitJob.setContent(content);
			submitJob.setContentType(contentType);
			submitJob.setPrinterId(printerId);
			submitJob.setTicket(ticket);
			submitJob.setTitle(title);
			final SubmitJobResponse response = sendDocumentToPrint(submitJob);
			if (response != null) {
				log.info(String.format("googlecloudprint response %s", response));
			} else {
				log.info("googlecloudprint response is null");
			}
		} catch (Exception ex) {
			throw new CloudPrintException(ex);
		}
	}

	private SubmitJobResponse sendDocumentToPrint(final SubmitJob submitJob) throws CloudPrintException {
		String response = "";
		try {
			final byte[] contentBase64 = Base64.getEncoder().encode(submitJob.getContent());

			final MultipartEntityBuilder entity = MultipartEntityBuilder.create()
					.addBinaryBody("content", contentBase64)
					.addTextBody("contentType", submitJob.getContentType())
					.addTextBody("contentTransferEncoding", "base64")
					.addTextBody("title", submitJob.getTitle())
					.addTextBody("ticket", submitJob.toJson());

			if (submitJob.getTag() != null) {
				for (String tag : submitJob.getTag()) {
					entity.addTextBody("tag", tag);
				}
			}
			response = openConnection("/submit?output=json&printerid=" + submitJob.getPrinterId(), entity);
		} catch (Exception ex) {
			throw new CloudPrintException(ex);
		}
		return gson.fromJson(new StringReader(response), SubmitJobResponse.class);
	}

	public abstract String getAccessToken();

}
