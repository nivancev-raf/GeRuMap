package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import org.w3c.dom.Node;

import javax.swing.text.AbstractDocument;
import java.io.*;

public class GsonSerializer implements Serializer {

    private Gson gson = new Gson();

    @Override
    public Project loadProject(File file) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            GsonBuilder gsonBilder = new GsonBuilder();
            gsonBilder.registerTypeAdapter(Project.class, new NodeDeserializer());
            gson = gsonBilder.create();
            return gson.fromJson(fileReader, Project.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public void saveProject(Project node) {
        try (FileWriter writer = new FileWriter(node.getFilePath())) {
            gson.toJson(node, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
