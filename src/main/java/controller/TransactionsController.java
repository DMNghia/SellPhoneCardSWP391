/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
            if (isAdmin) {
                ArrayList<Transactions> listT = new ArrayList<>();
                listT = (new TransactionsDAO()).getListTransactions();
                
                TransactionsDAO td = new TransactionsDAO();
//                String account =(String) session.getAttribute("account");
                User u = (User)session.getAttribute("user");
                List<Transactions> listTr = td.getListTransactionsById(u.getId());
                int size = listTr.size();
                int soTrang = (size%1==0)?(size/1):(size/1+1);
                String xpage = request.getParameter("page");
                int page;
                if(xpage==null){
                    page=1;
                }else{
                    page = Integer.parseInt(xpage);
                }
                int start = (page -1 )*1;
                int end = Math.min(page*1,size);
                listTr = td.getListTransactionsById(u.getId(),start,end);
                request.setAttribute("soTrang", soTrang);
                request.setAttribute("list", listTr);
                request.setAttribute("transactionsList", listT);
                request.getRequestDispatcher("transactions.jsp").forward(request, response);
//                TransactionsDAO transactionsDAO = new TransactionsDAO();
//                String page_raw = request.getParameter("page");
//                List<Transactions> listTransactions = transactionsDAO.getListDistinctTransactions();
//                Long totalTransactions = transactionsDAO.getTotalTransactions();
//                double totalPages = (double) totalTransactions / 1;
//                List<Transactions> list;
//                if (page_raw == null) {
//                list = transactionsDAO.getListTransactionsForPage(0);
//                request.setAttribute("transactionsList", listT);
//                request.setAttribute("totalPageNumbers", Math.ceil(totalPages));
//                request.setAttribute("pageNumber", 1);
//                request.setAttribute("listTransactions", listT);
//            } else {
//                list = transactionsDAO.getListTransactionsForPage((Integer.parseInt(page_raw) * 1) - 1);
//                request.setAttribute("totalPageNumbers", Math.ceil(totalPages));
//                request.setAttribute("transactionsList", listT);
//                request.setAttribute("pageNumber", page_raw);
//                request.setAttribute("listTransactions", listT);
//            }
                //String page_raw = request.getParameter("page");
                String status_raw = request.getParameter("status");
                String type_raw = request.getParameter("type");
                String search_raw = request.getParameter("search");
                if (search_raw != null || type_raw != null || status_raw != null) {
                boolean type ;
                boolean status ;
                String search = "%";
                if (search_raw != null && !search_raw.isEmpty()) {
                    search += (search_raw + "%");
                }
                if (type_raw != null && !type_raw.equals("all")) {
                    type = Boolean.parseBoolean(type_raw);
                   // totalPages = (double) totalStorage / 10;
                }
//                if (status_raw != null && !status_raw.equals("all")) {
//                    productId = Integer.parseInt(status_raw);
//                }
                List<Transactions> list;
                list = td.getListTransactions();
//                totalStorage = (long) list.size();
//                totalPages = (double) totalStorage / 10;
                request.setAttribute("pageNumber", 1);
                request.setAttribute("listTransactionSearch", list);
                //request.setAttribute("totalPageNumbers", Math.ceil(totalPages));
                request.getRequestDispatcher("transactions.jsp").forward(request, response);
                }
            } else {
                ArrayList<Transactions> listTr1 = new ArrayList<>();
                User u = (User)session.getAttribute("user");
                listTr1 = (new TransactionsDAO()).getListTransactionsById(u.getId());

                TransactionsDAO td = new TransactionsDAO();
//                String account =(String) session.getAttribute("account");
                List<Transactions> listTr = td.getListTransactionsById(u.getId());
                int size = listTr.size();
                int soTrang = (size%2==0)?(size/1):(size/2+1);
                String xpage = request.getParameter("page");
                int page;
                if(xpage==null){
                    page=1;
                }else{
                    page = Integer.parseInt(xpage);
                }
                int start = (page -1 )*2;
                int end = Math.min(page*2,size);
                listTr = td.getListTransactionsById(u.getId(),start,end);
                request.setAttribute("soTrang", soTrang);
                request.setAttribute("list", listTr);
                request.setAttribute("transactionsList", listTr1);
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
        processRequest(request, response);
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
