package schoolmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class loginservlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("suyog");
		EntityManager em =emf.createEntityManager();
		EntityTransaction et=em.getTransaction();

		String email = req.getParameter("name");
		String password = req.getParameter("password");
		Query q = em.createQuery("select p from principle p where p.email=?1 and p.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		// System.out.println(q.g);
		List<principle> u = q.getResultList();
		if (u.size()>0) {

			PrintWriter pw = resp.getWriter();
			RequestDispatcher rd = req.getRequestDispatcher("teacherstudent.html");
			pw.write("succesfulll");
			rd.include(req, resp);
			resp.setContentType("text/html");

		} else {
			PrintWriter pw = resp.getWriter();
			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			pw.write("invalid credensials");
			rd.include(req, resp);
			resp.setContentType("text/html");

		}

	}

}
