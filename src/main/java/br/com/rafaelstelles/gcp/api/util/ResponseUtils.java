package br.com.rafaelstelles.gcp.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class ResponseUtils {

	protected ResponseUtils() {
	}

	public final static String streamToString(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
                builder.append("\r\n");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
		return builder.toString();
    }
}
