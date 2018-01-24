package atest.websocket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component
public class Http extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = "Hello Http";
		resp.setContentType(MimeTypeUtils.TEXT_PLAIN_VALUE);
		resp.getWriter().println(msg);
	}
	
}
