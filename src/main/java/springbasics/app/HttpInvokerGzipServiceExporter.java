package springbasics.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

public class HttpInvokerGzipServiceExporter extends HttpInvokerServiceExporter{
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-Encoding", "gzip");
		super.handleRequest(request, response);
		
	}

	protected InputStream decorateInputStream(HttpServletRequest request, InputStream is) throws IOException {
		return new GZIPInputStream(is);
	}
	
	protected OutputStream decorateOutputStream(
			HttpServletRequest request, HttpServletResponse response, OutputStream os) throws IOException {
		//Encoder.Parameters params = new Encoder.Parameters().setQuality(5);
		return new GZIPOutputStream(os);
	}
}
