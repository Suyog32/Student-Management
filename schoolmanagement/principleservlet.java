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

@WebServlet("/rushi")
public class principleservlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("suyog");
		EntityManager em= emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		int id1 = Integer.parseInt(id);
		principle n = em.find(principle.class, id1);
		
		
		if (n==null) {
			
		

		principle p = new principle();
		p.setEmail(email);
		p.setId(id1);

		p.setPassword(password);
		p.setName(name);
		et.begin();
		em.persist(p);
		et.commit();

		PrintWriter pw = resp.getWriter();
		pw.write("registerd succesfully");
		RequestDispatcher rd = req.getRequestDispatcher("login.html");
		rd.include(req, resp);
	resp.setContentType("text/html");
	return;
	
		}
		PrintWriter pw = resp.getWriter();
		pw.write("change id");
		RequestDispatcher rd = req.getRequestDispatcher("register.html");
		rd.include(req, resp);
	resp.setContentType("text/html");

	}
}
