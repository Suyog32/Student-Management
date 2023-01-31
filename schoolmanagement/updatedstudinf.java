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
@WebServlet("/updat")
public class updatedstudinf  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("suyog");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
		
	String id=req.getParameter("id");
String name=	req.getParameter("name");
String age=	req.getParameter("age");
String cno=	req.getParameter("cno");
String email=	req.getParameter("email");
int id1=Integer.parseInt(id);
int age1=Integer.parseInt(age);
long cno1=Long.parseLong(cno);

studentinfo s=new studentinfo();
s.setId(id1);
s.setAge(age1);
s.setConno(cno1);
s.setEmail(email);
s.setName(name);
et.begin();
em.merge(s);
et.commit();
HttpSession ht=req.getSession();
ht.setAttribute("updatedstudent1", s);
PrintWriter pw=resp.getWriter();
pw.write("updated successfully");
RequestDispatcher rd=req.getRequestDispatcher("updatestudentdisplay.jsp");
rd.include(req, resp);
resp.setContentType("text/html");
	}

}
