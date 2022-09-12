package manager;

import dbConnection.DatabaseConnectionProvider;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private final CategoryManager categoryManager = new CategoryManager();
    private final Connection connection = DatabaseConnectionProvider.getConnector().getConnection();

    private final UserManager userManager = new UserManager();

    public void addItem(Item item) {
        String sql = "INSERT INTO item(title, price, category_id, picture_url, user_id) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getPictureUrl());
            ps.setInt(5, item.getUserId().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItems() {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM item order by id desc limit 20";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                itemList.add(getFromResultSet(resultSet));
            }
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getItemsByUserId(int userId) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                itemList.add(getFromResultSet(resultSet));
            }
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getItemsByCategory(int categoryId) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE category_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                itemList.add(getFromResultSet(resultSet));
            }
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeItemById(int id) {
        String sql = "DELETE FROM item WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item getFromResultSet(ResultSet resultSet) throws SQLException {
        return Item.builder()
                .id(resultSet.getInt(1))
                .title(resultSet.getString(2))
                .price(resultSet.getDouble(3))
                .category(categoryManager.getCategoryById(resultSet.getInt(4)))
                .pictureUrl(resultSet.getString(5))
                .userId(userManager.getUserById(resultSet.getInt(6)))
                .build();
    }

}
