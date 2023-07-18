package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dal.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;
import model.Storage;
import model.Supplier;
import model.User;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet(name = "ProductController", value = "/products")
@MultipartConfig
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            request.getRequestDispatcher("/admin/products.jsp").forward(request, response);
        } else {
            response.sendRedirect("/logout");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Initialize gson and map
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        String option = request.getParameter("option");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (option.equals("add")) {
            JsonObject responseData = new JsonObject();
            JsonParser jsonParser = new JsonParser();
            int price = 0;
            int quantity = 0;
            String supplier = "";
            String product = "";
            String image = "";
            Supplier supplier1 = new Supplier();
            Product product1 = new Product();
            LocalDateTime now = LocalDateTime.now();

            String supplierSelect = request.getParameter("supplierSelect");
            String supplierInput = request.getParameter("supplierInput");
            String productSelect = request.getParameter("productSelect");
            String productInput = request.getParameter("productInput");
            String productPrice = request.getParameter("productPrice");
            String supplierImage = request.getParameter("image");
            if (supplierSelect == "" && supplierInput == "") {
                map.put("message", "Vui lòng chọn nhà phát hành hoặc thêm mới");
                response.getWriter().write(gson.toJson(map));
                response.getWriter().flush();
            } else {
                if (productSelect == "" && productInput == "") {
                    map.put("message", "Vui lòng chọn sản phẩm hoặc thêm mới");
                    response.getWriter().write(gson.toJson(map));
                    response.getWriter().flush();
                    return;
                } else {
                    int newSupplierId = 0;
                    int newProductId = 0;
                    if (supplierSelect != "") {
                        newSupplierId = Integer.parseInt(supplierSelect);
                    } else {
                        supplier = supplierInput;
                        image = supplierImage;

                        supplier1.setName(supplier);
                        supplier1.setImage(image);
                        supplier1.setCreatedAt(Timestamp.valueOf(now));
                        supplier1.setCreatedBy((User) session.getAttribute("user"));
                        supplier1.setDelete(false);
                        newSupplierId = DAO.supplierDAO.insert(supplier1);
                    }
                    if (!productPrice.equals("")) {
                        price = Integer.parseInt(productPrice);
                    }
                    if (productSelect != "" && productSelect != null) {
                        newProductId = Integer.parseInt(productSelect);
                    } else if (productInput != "") {
                        if (newSupplierId > 0) {
                            product = productInput;
                            Product newProduct = new Product().builder()
                                    .name(product)
                                    .price(price)
                                    .createdAt(Timestamp.valueOf(now))
                                    .createdBy(user)
                                    .isDelete(false)
                                    .quantity(quantity)
                                    .supplier(DAO.supplierDAO.getSuppierById(newSupplierId))
                                    .build();
                            newProductId = DAO.productDAO.insert(newProduct);
                        } else {
                            map.put("message", "Thêm sản phẩm thất bại vui lòng xem lại nhà phát hành");
                            response.getWriter().write(gson.toJson(map));
                            response.getWriter().flush();
                            return;
                        }
                    }
                    List<Storage> errorStorages = new ArrayList<>();
                    product1 = DAO.productDAO.findProductById(newProductId);

                    Collection<Part> parts = request.getParts();
                    for (Part part : parts) {
                        String fileName = getFileName(part);
                        if (fileName != null && !fileName.isEmpty()) {
                            InputStream fileContent = part.getInputStream();

                            try (Workbook workbook = WorkbookFactory.create(fileContent)) {
                                Sheet sheet = workbook.getSheetAt(0); // Assuming you want to read the first sheet
                                for (Row row : sheet) {
                                    Storage newStorage = new Storage();
                                    for (Cell cell : row) {
                                        if (cell.getRowIndex() > 0) {
                                            if (cell.getColumnIndex() == 1) {
                                                newStorage.setSerialNumber(String.valueOf((long)cell.getNumericCellValue()));
                                            }
                                            if (cell.getColumnIndex() == 2) {
                                                newStorage.setCardNumber(String.valueOf((long)cell.getNumericCellValue()));
                                            }
                                            if (cell.getColumnIndex() == 3) {
                                                newStorage.setExpiredAt(Timestamp.valueOf(cell.getStringCellValue()));
                                            }
                                            newStorage.setCreatedAt(Timestamp.valueOf(now));
                                            newStorage.setCreatedBy((User) session.getAttribute("user"));
                                            newStorage.setUsed(false);
                                            newStorage.setDelete(false);
                                            newStorage.setProduct(product1);
                                            if (DAO.storageDAO.insert(newStorage) < 0) {
                                                errorStorages.add(newStorage);
                                            } else {
                                                quantity += 1;
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    product1.setQuantity(product1.getQuantity() + quantity);
                    product1.setUpdatedBy(user);
                    product1.setUpdatedAt(Timestamp.valueOf(now));
                    DAO.productDAO.update(product1, product1.getId());
                }
            }
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }
}