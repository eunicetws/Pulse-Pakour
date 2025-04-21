package utilz;

import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

public class SaveDataHandler {
    // Define the path for the saved file
    static String savePath = "utilz/savedata.txt";
    static Path saveFilePath = Paths.get(savePath);

    // Method to create the save file if it doesn't exist
    public static void createSaveFile() {
        try {
            File saveFile = new File(savePath);
            if (!(saveFile.exists())) {
                if (saveFile.createNewFile()) {
                    System.out.println("File created: " + saveFile.getName());
                    FileWriter write = new FileWriter(saveFile);
                    write.write(Integer.toString(KeyEvent.VK_SPACE)+"\n");
                    write.write(Integer.toString(KeyEvent.VK_CONTROL)+"\n");
                    write.write(Integer.toString(KeyEvent.VK_ESCAPE)+"\n");
                    write.write("0\n");
                    write.write("0\n");
                    write.write("0\n");
                    write.close();
                } else {
                    System.out.println("File already exists.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void overWriteFile(int newData, int line) throws IOException {
        try{
            List<String> fileContent = new ArrayList<>(Files.readAllLines(saveFilePath, StandardCharsets.UTF_8));

            if (line >= 0 && line < fileContent.size()) {
                fileContent.set(line, Integer.toString(newData));
            } else {
                System.out.println("IndexOutOfBound: " + line);
                return;
            }
            Files.write(saveFilePath, fileContent, StandardCharsets.UTF_8);
        }catch(Exception e){
            System.err.println(e);
        }
    }

    public static int ReadFile(int line) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(saveFilePath, StandardCharsets.UTF_8));
    
        String data = fileContent.get(line);
        return Integer.parseInt(data);
        
    }
}
