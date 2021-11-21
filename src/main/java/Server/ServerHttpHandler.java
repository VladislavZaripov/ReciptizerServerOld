package Server;

import Common.JsonHelper;
import Common.Recipe.Recipe;
import SQL.MySQLHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.stream.Collectors;

public class ServerHttpHandler implements HttpHandler {

    String charsetName = "UTF-8";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        if("GET".equals(httpExchange.getRequestMethod()))
        {
            sendResponse(httpExchange);
        }

        if("POST".equals(httpExchange.getRequestMethod()))
        {
            getResponse(httpExchange);
            sendResponse(httpExchange);
        }
    }

    private void sendResponse(HttpExchange httpExchange) throws IOException {
        String requestParam = httpExchange.getRequestURI().toString().split("\\?")[1];
        String requestParamName = requestParam.split("=")[0];
        String requestParamValue = requestParam.split("=")[1];
        byte[] answer;
        switch (requestParamName) {
            case "getFilter":
                answer = prepareAnswerOnGetFilter();
                break;
            case "getRecipe":
                answer = prepareAnswerOnGetRecipe(requestParamValue);
                break;
            case "getImage":
                answer = prepareAnswerOnGetImage(requestParamValue);
                break;
            case "postRecipe":
                answer = prepareAnswerOnPostRecipe();
                break;
            case "postImage":
                answer = prepareAnswerOnPostImage();
                break;
            default:
                answer = ("None").getBytes();
                break;
        }
        if (answer!=null) {
            System.out.println(httpExchange.getRequestURI() + ": answer not null");
            httpExchange.sendResponseHeaders(200, answer.length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(answer);
            outputStream.flush();
            outputStream.close();
        }
        else {
            System.out.println(httpExchange.getRequestURI() + ": answer null");
            httpExchange.sendResponseHeaders(400, 0);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.close();
        }
    }

    private void getResponse(HttpExchange httpExchange){
        String requestParam = httpExchange.getRequestURI().toString().split("\\?")[1];
        String requestParamName = requestParam.split("=")[0];
        switch (requestParamName) {
            case "postRecipe":
                getAndSavePostRecipeIntoMySQL(httpExchange);
                break;
            case "postImage":
                getAndSavePostImage(httpExchange);
                break;
        }
    }



    private byte[] prepareAnswerOnGetFilter() {
        JsonHelper jsonHelper = new JsonHelper();
        MySQLHelper mySQLHelper = new MySQLHelper();
        mySQLHelper.connectToMySQL();
        byte[] answer = new byte[0];
        try {
            answer = jsonHelper.recipeFilterToJson(mySQLHelper.getRecipesForFilter()).getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return answer;
    }

    private byte[] prepareAnswerOnGetRecipe(String requestParamValue) {
        int idRecipe = Integer.parseInt(requestParamValue);
        JsonHelper jsonHelper = new JsonHelper();
        MySQLHelper mySQLHelper = new MySQLHelper();
        mySQLHelper.connectToMySQL();
        byte[] answer = new byte[0];
        try {
            answer = jsonHelper.objectToJson(mySQLHelper.getRecipeFromMySQL(idRecipe)).getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public byte[] prepareAnswerOnGetImage (String requestParamValue){
        File file = new File("C://Users/zarip/Desktop/Image/" + requestParamValue);

        byte[] imageInByte = null;
        try {
            BufferedImage image = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            return null;
        }
        return imageInByte;
    }

    private byte[] prepareAnswerOnPostRecipe() {
        PostAnswer postAnswer = new PostAnswer("Рецепт сохранён");
        JsonHelper jsonHelper = new JsonHelper();
        return jsonHelper.objectToJson(postAnswer).getBytes();
    }

    private byte[] prepareAnswerOnPostImage() {
        PostAnswer postAnswer = new PostAnswer("Картинка сохранена");
        JsonHelper jsonHelper = new JsonHelper();
        return jsonHelper.objectToJson(postAnswer).getBytes();
    }



    private void getAndSavePostRecipeIntoMySQL(HttpExchange httpExchange){
        InputStream inputStream = httpExchange.getRequestBody();
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream,charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String recipeString = bufferedReader.lines().collect(Collectors.joining("\n"));

        JsonHelper jsonHelper = new JsonHelper();
        Recipe recipe = jsonHelper.jsonStringToRecipe(recipeString);

        MySQLHelper mySQLHelper = new MySQLHelper();
        mySQLHelper.connectToMySQL();
        mySQLHelper.saveRecipeIntoMySQL(recipe);
    }

    private void getAndSavePostImage(HttpExchange httpExchange){
        String requestParam = httpExchange.getRequestURI().toString().split("\\?")[1];
        String requestParamValue = requestParam.split("=")[1];
        File file = new File("C://Users/zarip/Desktop/Image/" + requestParamValue);

        InputStream inputStream = httpExchange.getRequestBody();

        try {
            BufferedImage image = ImageIO.read(inputStream);
            OutputStream outputStream = new FileOutputStream(file);
            ImageIO.write(image,"png",outputStream);

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static class PostAnswer{
        String answer;

        public PostAnswer(String answer) {
            this.answer = answer;
        }
    }
}