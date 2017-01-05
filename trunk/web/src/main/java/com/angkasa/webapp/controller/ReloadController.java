package com.angkasa.webapp.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.angkasa.Constants;
import com.angkasa.webapp.listener.StartupListener;

/**
 * This class is used to reload the drop-downs initialized in the
 * StartupListener.
 *
 * <p>
 * <a href="ReloadController.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/admin/reload*")
public class ReloadController {
	private transient final Log log = LogFactory.getLog(ReloadController.class);

	@RequestMapping(method = RequestMethod.GET)
	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'execute' method");
		}

		StartupListener.setupContext(request.getSession().getServletContext());

		String referer = request.getHeader("Referer");

		if (referer != null) {
			log.info("reload complete, reloading user back to: " + referer);
			List<String> messages = (List) request.getSession().getAttribute(BaseFormController.MESSAGES_KEY);

			if (messages == null) {
				messages = new ArrayList();
			}

			messages.add("Reloading options completed successfully.");
			request.getSession().setAttribute(BaseFormController.MESSAGES_KEY, messages);

			response.sendRedirect(response.encodeRedirectURL(referer));
			return null;
		} else {
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Context Reloaded</title>");
			out.println("</head>");
			out.println("<body bgcolor=\"white\">");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Context Reload Succeeded! Click OK to continue.');");
			out.println("history.back();");
			out.println("</script>");
			out.println("</body>");
			out.println("</html>");
		}

		return null;
	}

	//TODO remove this method and also the associated constant after we go live
	@RequestMapping(value = "/date/{monthYear}", method = RequestMethod.GET)
	public String setApplicableMonthYear(@PathVariable("monthYear") String monthYear, HttpServletResponse response, HttpServletRequest request)
			throws Exception {

		List<String> messages = (List) request.getSession().getAttribute(BaseFormController.MESSAGES_KEY);

		if (messages == null) {
			messages = new ArrayList();
		}

		messages.add("The applicable date for the system has been set to: " + monthYear);
		request.getSession().setAttribute(BaseFormController.MESSAGES_KEY, messages);

		Constants.APPLICABLE_DATE_MONTH_YEAR = monthYear;

		String referer = request.getHeader("Referer");
		return "redirect:/dashboardIT";

	}

}
