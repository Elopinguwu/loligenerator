package me.elo.lolidump.utils;

import javafx.scene.image.Image;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scrapper {
    public static String getRandomLink() throws IOException {
        Random rdm = new Random();
        int page = rdm.nextInt(200);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://gelbooru.com/index.php?page=dapi&q=index&s=post&tags=loli&pid=" + page)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        //System.out.println(body);

        String[] lines = body.split("\n");
        List<String> penis = new ArrayList<>();
        for (String line : lines){
            try {
                boolean blacklisted = false;
                for (String blackList : TagUtils.blacklist)
                    if (line.contains(blackList))
                        blacklisted = true;
                if (!blacklisted) {
                    String[] line2 = line.split("file_url=\"");
                    String[] line3 = line2[1].split("\" parent_id=");
                    penis.add(line3[0]);
                }
            } catch (Exception jambon){}
        }
        return  penis.get(rdm.nextInt(penis.size()));
    }

    public static Image getImage() throws IOException {
        String link = getRandomLink();
        //System.out.println("Actual mimage !!!!!" + link);
        saveImage(link);
        return new Image("file:temp/t.png");
    }

    private static void saveImage(String imageUrl) throws IOException {
        final URL url = new URL(imageUrl);
        final HttpURLConnection connection = (HttpURLConnection) url
                .openConnection();
        connection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
        final BufferedImage img = ImageIO.read(connection.getInputStream());
        File file = new File("temp/t.png");
        ImageIO.write(img, "png", file);
    }
}
