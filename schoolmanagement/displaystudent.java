package schoolmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/displaystudent")
public class displaystudent extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("suyog");
		EntityManager em =emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		String id = req.getParameter("id");
		int id1 = Integer.parseInt(id);
		studentinfo s = em.find(studentinfo.class, id1);
		PrintWriter pw = resp.getWriter();
		if (s == null) {
			pw.write("invalid id");
			RequestDispatcher rd = req.getRequestDispatcher("updatestudent.html");
			rd.include(req, resp);
			resp.setContentType("text/html");

		} else {
			
			HttpSession htp = req.getSession();
			htp.setAttribute("displaystudent", s);

			RequestDispatcher rd = req.getRequestDispatcher("displaystude.jsp");
			rd.forward(req, resp);

		}

		 
		
	}

}
