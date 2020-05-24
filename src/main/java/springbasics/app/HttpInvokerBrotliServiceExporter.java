package springbasics.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.nixxcode.jvmbrotli.dec.BrotliInputStream;
import com.nixxcode.jvmbrotli.enc.BrotliOutputStream;

public class HttpInvokerBrotliServiceExporter extends HttpInvokerServiceExporter{
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-Encoding", "br");
		super.handleRequest(request, response);
		
	}

	protected InputStream decorateInputStream(HttpServletRequest request, InputStream is) throws IOException {
		return new BrotliInputStream(is);
	}
	
	protected OutputStream decorateOutputStream(
			HttpServletRequest request, HttpServletResponse response, OutputStream os) throws IOException {
		//Encoder.Parameters params = new Encoder.Parameters().setQuality(5);
		return new BrotliOutputStream(os);
	}
}
