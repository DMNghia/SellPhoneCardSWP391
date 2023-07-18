/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import dal.ProductDAO;
import dal.TransactionsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.Transactions;
import model.User;

/**
 *
 * @author hp
 */
public class TransactionsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TransactionsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TransactionsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("login").forward(request, response);
        } else {
            boolean isAdmin = false;
            if (session.getAttribute("isAdmin") != null) {
                isAdmin = (boolean) session.getAttribute("isAdmin");
            }
            String type = "";
            String search = "";
            int page = 1;
            String status = "";
            if (isAdmin) {
//                ArrayList<Transactions> listT = new ArrayList<>();
//                listT = (new TransactionsDAO()).getListTransactions();
//
//                TransactionsDAO td = new TransactionsDAO();
////                String account =(String) session.getAttribute("account");
//                User u = (User) session.getAttribute("user");
//                List<Transactions> listTr = td.getListTransactionsByUserId(u.getId());
//                int size = listTr.size();
//                int soTrang = (size % 2 == 0) ? (size / 2) : (size / 2 + 1);
//                String xpage = request.getParameter("page");
//                if (xpage == null) {
//                    page = 1;
//                } else {
//                    page = Integer.parseInt(xpage);
//                }
//                int start = (page - 1) * 2;
//                int end = Math.min(page * 2, size);
//                listTr = td.getListTransactionsById(u.getId(), start, end);
//
//                if(request.getParameter("searchSubmit")!=null){
//                    type = request.getParameter("type");
//                    status =request.getParameter("status");
//                    search =request.getParameter("search");
//                    listTr = td.searchTransactions(type, status, search,u.getId());
//                }
//                request.setAttribute("soTrang", soTrang);
//                request.setAttribute("list", listTr);
//                request.setAttribute("transactionsList", listT);
//                request.getRequestDispatcher("transactions.jsp").forward(request, response);
//                String status_raw = request.getParameter("status");
//                String type_raw = request.getParameter("type");
//                String search_raw = request.getParameter("search");
//                if (search_raw != null || type_raw != null || status_raw != null) {
//                    type = "";
//                    status = "";
//                    search = "%";
//                    if (search_raw != null && !search_raw.isEmpty()) {
//                        search += (search_raw + "%");
//                    }
//                    List<Transactions> listTrans;
//                    listTrans = td.searchTransactions(type, status, search, u.getId());
//                    List<Transactions> list;
//                    list = td.getListTransactions();
//                    request.setAttribute("pageNumber", 1);
//                    request.setAttribute("transactionsList", list);
//                    request.setAttribute("searchList", listTrans);
//                    request.getRequestDispatcher("transactions.jsp").forward(request, response);
//                }
            } else {
                List<Transactions> list = new ArrayList<>();
                User u = (User) session.getAttribute("user");
                User user = DAO.userDAO.getUserById(u.getId());
                session.setAttribute("user", user);
                type = request.getParameter("type");
                status = request.getParameter("status");
                search = request.getParameter("search");
                String page_raw = request.getParameter("page");
                if (page_raw != null && !page_raw.equals("1")) {
                    page = Integer.parseInt(page_raw);
                }

                if (type == null) {
                    type = "";
                }
                if (status == null) {
                    status = "";
                }
                if (search == null) {
                    search = "";
                }

                list = DAO.transactionsDAO.searchTransactions(type, status, search, user.getId(), (page - 1) * 10);

                long totalTransactions = DAO.transactionsDAO.getTotalTransactions(type, status, search, user.getId());
                double totalPage = Math.ceil((double) totalTransactions/10);
                request.setAttribute("pageNumber", page);
                request.setAttribute("totalPageNumbers", totalPage);
                request.setAttribute("list", list);
                request.getRequestDispatcher("transactions.jsp").forward(request, response);
            }

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        TransactionsDAO td = new TransactionsDAO();
//        Transactions tran = new Transactions();
//        User u = new User();
//        List<Transactions> listDetail = td.getDetailHistory(tran.getId());
//        request.setAttribute("listDetail", listDetail);
//        request.getRequestDispatcher("transactions").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
