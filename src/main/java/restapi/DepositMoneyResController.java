package restapi;

import dal.DAO;
import functionUtils.Function;
import functionUtils.ScanTransaction;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "DepositMoneyResController", urlPatterns = "/api/v1/depositMoney")
public class DepositMoneyResController extends HttpServlet {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Get paymentTransaction
        long paymentId = (long) session.getAttribute("paymentId");
        PaymentTransaction paymentTransaction = DAO.paymentTransactionDAO.getPaymentById(paymentId);

        // Update paymentTransaction status to success
        paymentTransaction.setStatus(true);
        paymentTransaction.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        paymentTransaction.setUpdatedBy(user);
        DAO.paymentTransactionDAO.update(paymentTransaction);

        // Insert new transaction
        Transactions transaction = new Transactions().builder()
                .user(user)
                .money((int) paymentTransaction.getAmount())
                .type(true)
                .status(false)
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .createBy(user)
                .build();
        DAO.transactionsDAO.insert(transaction);

        session.removeAttribute("paymentId");

        executorService.execute(new ScanTransaction());
    }

    @Override
    public void destroy() {
        super.destroy();
        executorService.shutdown();
    }
}