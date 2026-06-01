import java.sql.*;

public class DatabaseConnector {
    private Connection connection;
    
    public DatabaseConnector(String dbPath) {
        try {
            // Загрузка драйвера SQLite
            Class.forName("org.sqlite.JDBC");
            // Подключение к базе данных
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            System.out.println("✅ Подключение к БД установлено");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Драйвер SQLite не найден!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Ошибка подключения к БД!");
            e.printStackTrace();
        }
    }
    
    public void getAllTasks() {
        String query = "SELECT id, title, status, due_date FROM tasks";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            System.out.println("\n📋 Список задач:");
            System.out.println("=".repeat(50));
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String status = rs.getString("status");
                String dueDate = rs.getString("due_date");
                
                System.out.printf("%d. %s [%s] (до: %s)%n", 
                    id, title, status, dueDate != null ? dueDate : "без срока");
            }
            System.out.println("=".repeat(50));
        } catch (SQLException e) {
            System.out.println("❌ Ошибка при получении задач");
            e.printStackTrace();
        }
    }
    
    public void addTask(String title, String description, String priority, String dueDate) {
        String query = "INSERT INTO tasks (title, description, priority, due_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, priority);
            pstmt.setString(4, dueDate);
            pstmt.executeUpdate();
            System.out.println("✅ Задача добавлена: " + title);
        } catch (SQLException e) {
            System.out.println("❌ Ошибка при добавлении задачи");
            e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("🔌 Соединение с БД закрыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Тестовый метод
    public static void main(String[] args) {
        DatabaseConnector db = new DatabaseConnector("database/tasks.db");
        db.getAllTasks();
        db.close();
    }
}