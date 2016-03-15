package com.inteliment.rest.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

@Component
public class CSVConverter extends AbstractHttpMessageConverter<List<?>> {
	public static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));

	public CSVConverter() {
    super(MEDIA_TYPE);
  }

	@Override
	protected boolean supports(Class<?> clazz) {
		return List.class.isAssignableFrom(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void writeInternal(List response, HttpOutputMessage output)
			throws IOException, HttpMessageNotWritableException {
		String [] arr = new String[2];
		OutputStream out = output.getBody();
		CSVWriter writer = new CSVWriter(new OutputStreamWriter(out));
		if(response != null && !response.isEmpty()){
			for(int i = 0; i < response.size(); i++){
				Entry obj = (Entry) response.get(i);
				arr = new String[2];
				arr[0] = (String) obj.getKey();
				arr[1] = ((Integer) obj.getValue())+"";
				writer.writeNext(arr);
			}
		}
		writer.flush();
		writer.close();
	}

	@Override
	protected List<?> readInternal(Class<? extends List<?>> request, HttpInputMessage input)
			throws IOException, HttpMessageNotReadableException {
		InputStream in = input.getBody();
		CSVReader reader = new CSVReader(new InputStreamReader(in));
		List<?> records = reader.readAll();
		reader.close();
		return records;
	}
}