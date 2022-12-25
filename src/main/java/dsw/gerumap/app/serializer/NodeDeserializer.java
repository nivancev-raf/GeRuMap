package dsw.gerumap.app.serializer;

import com.google.gson.*;
import com.sun.tools.javac.Main;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.elements.MindMapModel;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.lang.reflect.Type;

public class NodeDeserializer implements JsonDeserializer<Project> {
    MindMapModel mindMapModel;

    @Override
    public Project deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Project p = new Project(jsonObject.get("name").getAsString(), MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode());
        p.setFilePath(jsonObject.get("filePath").getAsString());
        if (jsonObject.get("children").getAsJsonArray().size() == 0) return p;
        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(MindMapModel.class, new MindMapDeserializer());
        Gson gson = gsonBilder.create();


        for (JsonElement element : jsonObject.get("children").getAsJsonArray()){
            JsonObject children = element.getAsJsonObject().get("model").getAsJsonObject();
            mindMapModel = gson.fromJson(children, MindMapModel.class);
            MindMap map = new MindMap(element.getAsJsonObject().get("name").getAsString(), p);
            p.addChild(map);
            map.setModel(mindMapModel);
        }

        return p;
    }
}