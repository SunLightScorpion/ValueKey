package de.sunlight.key;

import org.json.JSONObject;

import java.io.*;

public class ValueKey <T extends String>{

    T key;

    private ValueKey(T key){
        this.key = key;
    }

    public static ValueKey<String> getKey(Object key){
        return new ValueKey<>(key.toString());
    }

    public T getKey() {
        return key;
    }

    public void saveAsFile(String path){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", key);

        FileWriter file;
        try {
            file = new FileWriter(path + ".json");
            file.write(jsonObject.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getData(String path) {
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            JSONObject json = new JSONObject(line);
            return json.get("value");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
