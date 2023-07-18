package restapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dal.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;
import model.Supplier;
import model.User;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductRestController", value = "/api/v1/products")
public class ProductRestController extends HttpServlet {
    static Object lock;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        if ((boolean) session.getAttribute("isAdmin")) {
            // Initialize gson and map
            Gson gson = new Gson();
            JsonObject responseData = new JsonObject();
            JsonParser jsonParser = new JsonParser();
            Map<String, String> map = new HashMap<>();

            // Initialize variable for search product
            int price = -1;
            int supplierId = 0;
            String name = "%";
            int page = 1;
            int supplierIdAddProduct = 1;

            // Get parameter
            String price_raw = request.getParameter("price");
            String searchName = request.getParameter("search");
            String page_raw = request.getParameter("page");
            String supplier_raw = request.getParameter("supplier");
            String supplier_raw_AddProduct = request.getParameter("supplierAddProduct");

            // Set value for variable
            if (price_raw != null && !price_raw.equals("all")) {
                price = Integer.parseInt(price_raw);
            }
            if (supplier_raw != null && !supplier_raw.equals("all")) {
                supplierId = Integer.parseInt(supplier_raw);
            }
            if (searchName != null && !searchName.isEmpty()) {
                name += (searchName + "%");
            }
            if (page_raw != null && !page_raw.equals("1")) {
                page = Integer.parseInt(page_raw);
            }
            if (supplier_raw_AddProduct != null && !supplier_raw_AddProduct.isEmpty() && supplier_raw_AddProduct != "null") {
                supplierIdAddProduct = Integer.parseInt(supplier_raw_AddProduct);
            }

            // Find list product by supplier, name , price; list supplier; list distinct price; total pages
            List<Product> listProduct = DAO.productDAO.searchProduct(supplierId, name, price, (page - 1) * 10);
            List<Supplier> listSupplier = DAO.supplierDAO.getListSupplier();
            List<Integer> listPrice = DAO.productDAO.getListPrice();
            List<Product> listProductBySupplier = DAO.productDAO.getListProductBySupplier(supplierIdAddProduct);
            int totalProduct = DAO.productDAO.getTotalProduct(supplierId, name, price);
            double totalPages = (double) totalProduct / 10;

            // Map lists to a key
            map.put("pageNumber", String.valueOf(page));
            map.put("totalPageNumbers", String.valueOf(Math.ceil(totalPages)));
            responseData.add("listProduct", jsonParser.parseString(gson.toJson(listProduct)));
            responseData.add("pagination", jsonParser.parseString(gson.toJson(map)));
            responseData.add("listPrice", jsonParser.parseString(gson.toJson(listPrice)));
            responseData.add("listSupplier", jsonParser.parseString(gson.toJson(listSupplier)));
            responseData.add("listProductBySubblier", jsonParser.parseString(gson.toJson(listProductBySupplier)));

            // Return a response json
            response.getWriter().write(gson.toJson(responseData));
            response.getWriter().flush();
        } else {
            response.getWriter().write("Error");
            response.getWriter().flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        if ((boolean) session.getAttribute("isAdmin")) {
            // Initialize gson and map
            Gson gson = new Gson();
            Map<String, String> map = new HashMap<>();
            String option = request.getParameter("option");
            User user = (User) session.getAttribute("user");
            try {
                // Get all parameters
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                int price = Integer.parseInt(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                // Set parameters to product with id = id and update
                Product product = DAO.productDAO.findProductById(id);
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                product.setUpdatedBy((User) session.getAttribute("user"));
                DAO.productDAO.update(product, id);

                // Initialize variable for response json
                map.put("message", "Cập nhật thông tin sản phẩm thành công");
            } catch (Exception e) {
                map.put("message", "Cập nhật thông tin sản phẩm thất bại");
            }
            response.getWriter().write(gson.toJson(map));
            response.getWriter().flush();

        } else {
            response.getWriter().write("Error");
            response.getWriter().flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        if ((boolean) session.getAttribute("isAdmin")) {
            // Initialize gson and map
            Gson gson = new Gson();
            Map<String, String> map = new HashMap<>();

            try {

                // Get all parameters
                int id = Integer.parseInt(request.getParameter("id"));

                // Set parameters to product with id = id and update
                Product product = DAO.productDAO.findProductById(id);
                product.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
                product.setDeletedBy((User) session.getAttribute("user"));
                product.setDelete(true);
                DAO.productDAO.delete(product, id);

                // Initialize variable for response json
                map.put("message", "Xóa thành công!");
            } catch (Exception e) {
                map.put("message", "Xóa thất bại!");
            }
            response.getWriter().write(gson.toJson(map));
            response.getWriter().flush();
        } else {
            response.getWriter().write("Error");
            response.getWriter().flush();
        }
    }
}