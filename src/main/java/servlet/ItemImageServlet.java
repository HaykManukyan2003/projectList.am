package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/itemImage")
public class ItemImageServlet extends HttpServlet {

    private static final String IMAGE_PATH = "C:\\Users\\Hayk\\IdeaProjects\\projectList.am\\projectImages\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemImage = req.getParameter("itemImageParameter");
        String filePath = IMAGE_PATH + itemImage;
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) file.length());

                ServletOutputStream outputStream = resp.getOutputStream();

                int reader;
                byte[] buffer = new byte[4096];
                while ((reader = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, reader);
                }
            }
        }
    }
}
