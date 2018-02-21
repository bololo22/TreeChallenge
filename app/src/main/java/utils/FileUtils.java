package utils;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import model.ProductType;

/**
 * Created by kevin on 8/30/17.
 */

public class FileUtils {
    private static final String PRODUCT_TYPES_FILE_NAME = "product_types.json";
    private static final String RELATIONSHIPS_FILE_NAME = "relationships.json";

    /**
     * Opens and parses the product types file into an array of product types
     *
     * @param assetManager to get the files from corresponding file
     * @return array of ProductType
     */
    public static ProductType[] getProductTypesArray(AssetManager assetManager) {
        InputStream stream = null;
        String json = null;
        ProductType[] productTypes;

        try {
            stream = FileUtils.getFileContents(PRODUCT_TYPES_FILE_NAME, assetManager);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");

            Gson gson = new GsonBuilder().create();
            productTypes = gson.fromJson(json, ProductType[].class);
            return productTypes;
        } catch (IOException e) {
            return null;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * Opens and parses the relationships file into a product type relationships map where the node
     * ID is mapped to its children IDs
     *
     * @param assetManager to get the files from corresponding file
     * @return Map with relationships
     */
    public static Map<String, String[]> getRelationships(AssetManager assetManager) {
        InputStream stream = null;
        String json = null;
        Map<String, String[]> relationships = new LinkedHashMap<>();

        try {
            stream = FileUtils.getFileContents(RELATIONSHIPS_FILE_NAME, assetManager);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");

            relationships = new Gson().fromJson(json, new TypeToken<LinkedHashMap<String, String[]>>() {}.getType());

            return relationships;
        } catch (IOException e) {
            return null;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * Helper function that returns a file descriptor of the given file name
     *
     * @param fileName
     * @param assetManager
     * @return
     */
    private static InputStream getFileContents(String fileName, AssetManager assetManager) throws IOException {
        return assetManager.open(fileName);
    }
}
