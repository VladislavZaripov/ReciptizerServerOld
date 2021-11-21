package SQL;

import Common.Recipe.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MySQLHelper {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/sql";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";
    Statement statement;

    public static final String TABLE1_NAME = "Recipe";
    public static final String TABLE1_COLUMN_ID = "_id";
    public static final String TABLE1_COLUMN_RECIPE = "Recipe";
    public static final String TABLE1_COLUMN_CATEGORY = "Category";
    public static final String TABLE1_COLUMN_KITCHEN = "Kitchen";
    public static final String TABLE1_COLUMN_PREFERENCES = "Preferences";
    public static final String TABLE1_COLUMN_TIME = "Time";
    public static final String TABLE1_COLUMN_PORTION = "Portion";
    public static final String TABLE1_COLUMN_IMG_FULL = "MainImgFull";
    public static final String TABLE1_COLUMN_IMG_TITLE = "MainImgTitle";

    public static final String TABLE2_NAME = "Ingredients";
    public static final String TABLE2_COLUMN_ID = "_id";
    public static final String TABLE2_COLUMN_ID_RECIPE = "Ingredients_id";
    public static final String TABLE2_COLUMN_INGREDIENTS = "Ingredients";
    public static final String TABLE2_COLUMN_QUANTITY = "Quantity";
    public static final String TABLE2_COLUMN_MEASURE = "Measure";

    public static final String TABLE3_NAME = "Steps";
    public static final String TABLE3_COLUMN_ID = "_id";
    public static final String TABLE3_COLUMN_ID_RECIPE = "Steps_id";
    public static final String TABLE3_COLUMN_NUMBER = "Number";
    public static final String TABLE3_COLUMN_TEXT = "Text";
    public static final String TABLE3_COLUMN_IMG_FULL = "ImgFull";
    public static final String TABLE3_COLUMN_IMG_TITLE = "ImgTitle";

    public void connectToMySQL() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user",USER);
            properties.setProperty("password",PASSWORD);
            properties.setProperty("verifyServerCertificate","false");
            properties.setProperty("useSSL","false");
            properties.setProperty("requireSSL","false");
            properties.setProperty("useLegacyDatetimeCode","amp");
            properties.setProperty("serverTimezone","Europe/Moscow");

            Connection connection = DriverManager.getConnection(URL,properties);
            statement = connection.createStatement();
        }
        catch (SQLException e)
        {
            System.err.println("MySQLHelper: connectSQL");
            e.printStackTrace();
        }
    }


    public void createTablesInMySQL() {
        try {
            createTable1 ();
            createTable2 ();
            createTable3 ();
        }
        catch (SQLException e)
        {
            System.err.println("MySQLHelper: createTables");
            e.printStackTrace();
        }
    }

    private void createTable1 () throws SQLException{
        statement.execute("create table if not exists " + TABLE1_NAME +
                "(" +
                TABLE1_COLUMN_ID + " integer primary key AUTO_INCREMENT, " +
                TABLE1_COLUMN_RECIPE + " text, " +
                TABLE1_COLUMN_CATEGORY + " text, " +
                TABLE1_COLUMN_KITCHEN + " text, " +
                TABLE1_COLUMN_PREFERENCES + " text, " +
                TABLE1_COLUMN_TIME + " integer, " +
                TABLE1_COLUMN_PORTION + " integer, " +
                TABLE1_COLUMN_IMG_FULL + " text," +
                TABLE1_COLUMN_IMG_TITLE + " text" +
                ");");
    }
    private void createTable2 () throws SQLException{
        statement.execute("create table if not exists " + TABLE2_NAME +
                "(" +
                TABLE2_COLUMN_ID + " integer primary key AUTO_INCREMENT, " +
                TABLE2_COLUMN_ID_RECIPE + " integer, " +
                TABLE2_COLUMN_INGREDIENTS + " text, " +
                TABLE2_COLUMN_QUANTITY + " integer, " +
                TABLE2_COLUMN_MEASURE + " text" +
                ");");
    }
    private void createTable3 () throws SQLException{
        statement.execute("create table if not exists " + TABLE3_NAME +
                "(" +
                TABLE3_COLUMN_ID + " integer primary key AUTO_INCREMENT, " +
                TABLE3_COLUMN_ID_RECIPE + " integer, " +
                TABLE3_COLUMN_NUMBER + " integer, " +
                TABLE3_COLUMN_TEXT + " text, " +
                TABLE3_COLUMN_IMG_FULL + " text, " +
                TABLE3_COLUMN_IMG_TITLE + " text" +
                ");");
    }


    public void saveRecipeIntoMySQL(Recipe recipe) {

        convertPathToName(recipe);
        try {
            long idRecipe = saveRecipeIntoTable1(recipe);
            saveRecipeIntoTable2(recipe,idRecipe);
            saveRecipeIntoTable3(recipe,idRecipe);

        } catch (SQLException e) {
            System.err.println("MySQLHelper: saveRecipe");
            e.printStackTrace();
        }
    }

    private void convertPathToName (Recipe recipe){
        String [] path;

        if(recipe.table1.imageTitle!=null){
            path = recipe.table1.imageTitle.split("/");
            recipe.table1.imageTitle = path[path.length-1];}

        if(recipe.table1.imageFull!=null){
            path = recipe.table1.imageFull.split("/");
            recipe.table1.imageFull = path[path.length-1];}

        for (Table3Row table3Row : recipe.rowsTable3) {
            if(table3Row.imageTitle!=null){
                path = table3Row.imageTitle.split("/");
                table3Row.imageTitle = path[path.length-1];}

            if(table3Row.imageFull!=null){
                path = table3Row.imageFull.split("/");
                table3Row.imageFull = path[path.length-1];}
        }
    }
    private long saveRecipeIntoTable1(Recipe recipe) throws SQLException{
        statement.executeUpdate("INSERT " + TABLE1_NAME +
                " (" +
                TABLE1_COLUMN_RECIPE + ", " +
                TABLE1_COLUMN_CATEGORY + ", " +
                TABLE1_COLUMN_KITCHEN + ", " +
                TABLE1_COLUMN_PREFERENCES + ", " +
                TABLE1_COLUMN_TIME + ", " +
                TABLE1_COLUMN_PORTION + ", " +
                TABLE1_COLUMN_IMG_FULL + ", " +
                TABLE1_COLUMN_IMG_TITLE +
                ") " +
                "values " +
                "(" +
                "\"" + recipe.table1.recipe + "\", " +
                "\"" + recipe.table1.category + "\", " +
                "\"" + recipe.table1.kitchen + "\", " +
                "\"" + recipe.table1.preferences + "\", " +
                recipe.table1.time + ", " +
                recipe.table1.portion + ", " +
                "\"" + recipe.table1.imageFull + "\", " +
                "\"" + recipe.table1.imageTitle + "\"" +
                ");", Statement.RETURN_GENERATED_KEYS);

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            else {
                System.err.println("Creating failed, no ID obtained.");
                return -1;
            }
        }
    }
    private void saveRecipeIntoTable2(Recipe recipe, long idRecipe) throws SQLException{
        List<Table2Row> rowsTable2 = recipe.rowsTable2;

        for (Table2Row table2Row : rowsTable2) {
            statement.executeUpdate("INSERT " + TABLE2_NAME +
                    " (" +
                    TABLE2_COLUMN_ID_RECIPE + ", " +
                    TABLE2_COLUMN_INGREDIENTS + ", " +
                    TABLE2_COLUMN_QUANTITY + ", " +
                    TABLE2_COLUMN_MEASURE +
                    ") " +
                    "values " +
                    "(" +
                    idRecipe + ", " +
                    "\"" + table2Row.ingredient + "\", " +
                    table2Row.quantity + ", " +
                    "\"" + table2Row.measure + "\"" +
                    ");");
        }
    }
    private void saveRecipeIntoTable3(Recipe recipe, long idRecipe) throws SQLException{
        List<Table3Row> rowsTable3 = recipe.rowsTable3;

        for (Table3Row table3Row : rowsTable3) {
            statement.executeUpdate("INSERT " + TABLE3_NAME +
                    " (" +
                    TABLE3_COLUMN_ID_RECIPE + ", " +
                    TABLE3_COLUMN_NUMBER + ", " +
                    TABLE3_COLUMN_TEXT + ", " +
                    TABLE3_COLUMN_IMG_FULL + ", " +
                    TABLE3_COLUMN_IMG_TITLE +
                    ") " +
                    "values " +
                    "(" +
                    idRecipe + ", " +
                    table3Row.number + ", " +
                    "\"" + table3Row.text + "\", " +
                    "\"" + table3Row.imageFull + "\", " +
                    "\"" + table3Row.imageTitle + "\"" +
                    ");");
        }
    }


    public Recipe getRecipeFromMySQL (int idRecipe) {
        Recipe recipe = null;
        try {
            Table1 table1 = getRecipeTable1(idRecipe);
            List <Table2Row> rowsTable2 = getRecipeTable2(idRecipe);
            List <Table3Row> rowsTable3 = getRecipeTable3(idRecipe);
            recipe = new Recipe(table1,rowsTable2,rowsTable3);
        } catch (SQLException e) {
            System.err.println("MySQLHelper: getRecipeFromMySQL");
            e.printStackTrace();
        }
        return recipe;
    }

    private Table1 getRecipeTable1 (int idRecipe) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from " + TABLE1_NAME + " where " + TABLE1_COLUMN_ID + " = " + idRecipe + ";");

        Table1 table1 = null;
        if (resultSet.next()) {
            String recipe = resultSet.getString(TABLE1_COLUMN_RECIPE);
            String category = resultSet.getString(TABLE1_COLUMN_CATEGORY);
            String kitchen = resultSet.getString(TABLE1_COLUMN_KITCHEN);
            String preferences = resultSet.getString(TABLE1_COLUMN_PREFERENCES);
            Integer time = resultSet.getInt(TABLE1_COLUMN_TIME);
            Integer portion = resultSet.getInt(TABLE1_COLUMN_PORTION);
            String img_full = resultSet.getString(TABLE1_COLUMN_IMG_FULL);
            String img_title = resultSet.getString(TABLE1_COLUMN_IMG_TITLE);
            table1 = new Table1(null,recipe,category,kitchen,preferences,time,portion,img_full,img_title);
        }
        return table1;
    }
    private List <Table2Row> getRecipeTable2 (int idRecipe) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from " + TABLE2_NAME + " where " + TABLE2_COLUMN_ID_RECIPE + " = " + idRecipe + ";");
        List <Table2Row> rowsTable2 = new ArrayList<>();
        while (resultSet.next()) {
            String ingredients = resultSet.getString(TABLE2_COLUMN_INGREDIENTS);
            Integer quantity = resultSet.getInt(TABLE2_COLUMN_QUANTITY);
            String measure = resultSet.getString(TABLE2_COLUMN_MEASURE);
            rowsTable2.add(new Table2Row(null,null,ingredients,quantity,measure));
        }
        return rowsTable2;
    }
    private List <Table3Row> getRecipeTable3 (int idRecipe) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from " + TABLE3_NAME + " where " + TABLE3_COLUMN_ID_RECIPE + " = " + idRecipe + ";");
        List <Table3Row> rowsTable3 = new ArrayList<>();
        while (resultSet.next()) {
            Integer number = resultSet.getInt(TABLE3_COLUMN_NUMBER);
            String text = resultSet.getString(TABLE3_COLUMN_TEXT);
            String img_full = resultSet.getString(TABLE3_COLUMN_IMG_FULL);
            String img_title = resultSet.getString(TABLE3_COLUMN_IMG_TITLE);
            rowsTable3.add(new Table3Row(null,null,number,text,img_full,img_title));
        }
        return rowsTable3;
    }


    public RecipeFilter getRecipesForFilter (){
        List<Table1> table1 = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from " + TABLE1_NAME + ";");
            while (resultSet.next()) {
                Integer id = resultSet.getInt(TABLE1_COLUMN_ID);
                String recipe = resultSet.getString(TABLE1_COLUMN_RECIPE);
                String category = resultSet.getString(TABLE1_COLUMN_CATEGORY);
                String kitchen = resultSet.getString(TABLE1_COLUMN_KITCHEN);
                String preferences = resultSet.getString(TABLE1_COLUMN_PREFERENCES);
                Integer time = resultSet.getInt(TABLE1_COLUMN_TIME);
                Integer portion = resultSet.getInt(TABLE1_COLUMN_PORTION);
                String img_full = resultSet.getString(TABLE1_COLUMN_IMG_FULL);
                String img_title = resultSet.getString(TABLE1_COLUMN_IMG_TITLE);
                table1.add(new Table1(id, recipe, category, kitchen, preferences, time, portion, img_full, img_title));
            }
        }
        catch (SQLException e)
        {
            System.err.println("MySQLHelper: getRecipesForFilter");
            e.printStackTrace();
        }
        return new RecipeFilter(table1);
    }
}