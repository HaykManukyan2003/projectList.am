package manager;

import dbConnection.DatabaseConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private final Connection connection = DatabaseConnectionProvider.getConnector().getConnection();

    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM category WHERE id = ? order by id desc limit 20";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getCategories() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                categoryList.add(Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build());
            }
            return categoryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
