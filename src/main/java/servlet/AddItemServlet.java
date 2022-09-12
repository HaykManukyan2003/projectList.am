package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/item/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddItemServlet extends HttpServlet {

    private final CategoryManager categoryManager = new CategoryManager();
    private final ItemManager itemManager = new ItemManager();
    private final UserManager userManager = new UserManager();
    private static final String IMAGE_PATH = "C:\\Users\\Hayk\\IdeaProjects\\projectList.am\\projectImages\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Part pictureUrl = req.getPart("pictureUrl");
        String file = null;
        if (pictureUrl.getSize() != 0) {
            long nanoTime = System.nanoTime();
            file = nanoTime + "_" + pictureUrl.getSubmittedFileName();
            pictureUrl.write(IMAGE_PATH + file);
        }
        itemManager.addItem(Item.builder()
                .title(title)
                .price(price)
                .category(categoryManager.getCategoryById(categoryId))
                .pictureUrl(file)
                .userId(userManager.getUserById(userId))
                .build());
        req.setAttribute("itemSuccessfullyAdded", "item successfully added");
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
