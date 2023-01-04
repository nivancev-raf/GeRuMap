package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;
import dsw.gerumap.app.gui.swing.elements.LineElement;
import dsw.gerumap.app.gui.swing.elements.MindMapModel;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painters.ElipsePainter;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;

import javax.swing.tree.TreePath;
import java.awt.*;
import java.lang.reflect.Type;


public class MindMapDeserializer implements JsonDeserializer<MindMapModel> {
    public MindMapDeserializer(){
    }

    @Override
    public MindMapModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonArray object = json.getAsJsonObject().get("mapElements").getAsJsonArray();
        JsonArray object2 = json.getAsJsonObject().get("veze").getAsJsonArray();
        MindMapModel mindMapModel = new MindMapModel();

        //elipse
        for (JsonElement element : object){
            JsonObject jsonObject = element.getAsJsonObject();
            int x, y, width, height;
            String text;
            float[] paint = new float[3];
            float stroke;
            x = jsonObject.get("shape").getAsJsonObject().get("x").getAsInt();
            y = jsonObject.get("shape").getAsJsonObject().get("y").getAsInt();
            width = jsonObject.get("shape").getAsJsonObject().get("width").getAsInt();
            height = jsonObject.get("shape").getAsJsonObject().get("height").getAsInt();
            paint[0] = (jsonObject.get("diagramDevice").getAsJsonObject().get("paint").getAsJsonArray().get(0).getAsFloat());
            paint[1] = (jsonObject.get("diagramDevice").getAsJsonObject().get("paint").getAsJsonArray().get(1).getAsFloat());
            paint[2] = (jsonObject.get("diagramDevice").getAsJsonObject().get("paint").getAsJsonArray().get(2).getAsFloat());
            stroke = jsonObject.get("diagramDevice").getAsJsonObject().get("stroke").getAsFloat();
            text = jsonObject.get("diagramDevice").getAsJsonObject().get("name").getAsString();
            ElipseElement elipseElement = new ElipseElement(
                    new Point(x,y),
                    new Dimension(width,height),
                    paint,
                    stroke
            );

            elipseElement.setName(text);
            ElipsePainter elipsePainter = new ElipsePainter(elipseElement);

            //setujemo poziciju diagramDevice
            int xdev,ydev;
            xdev = jsonObject.get("diagramDevice").getAsJsonObject().get("position").getAsJsonObject().get("x").getAsInt();
            ydev = jsonObject.get("diagramDevice").getAsJsonObject().get("position").getAsJsonObject().get("y").getAsInt();
            elipsePainter.getDiagramDevice().setPosition(new Point(xdev,ydev));
            mindMapModel.addDiagramElements(elipsePainter);
        }

        //veze
        for (JsonElement element : object2){
            JsonObject jsonObject = element.getAsJsonObject();
            int xFrom,yFrom, xTo, yTo;
            float lineStroke;
            float[] oldColor = new float[3];
            xFrom = jsonObject.get("odPojma").getAsJsonObject().get("x").getAsInt();
            yFrom = jsonObject.get("odPojma").getAsJsonObject().get("y").getAsInt();
            xTo = jsonObject.get("doPojma").getAsJsonObject().get("x").getAsInt();
            yTo = jsonObject.get("doPojma").getAsJsonObject().get("y").getAsInt();
            lineStroke = jsonObject.get("lineStroke").getAsFloat();
            oldColor[0] = jsonObject.get("oldColor").getAsJsonArray().get(0).getAsFloat();
            oldColor[1] = jsonObject.get("oldColor").getAsJsonArray().get(1).getAsFloat();
            oldColor[2] = jsonObject.get("oldColor").getAsJsonArray().get(2).getAsFloat();
            int x,y,width, height;
            width = jsonObject.get("diagramDevice").getAsJsonObject().get("size").getAsJsonObject().get("width").getAsInt();
            height = jsonObject.get("diagramDevice").getAsJsonObject().get("size").getAsJsonObject().get("height").getAsInt();
            x = jsonObject.get("diagramDevice").getAsJsonObject().get("position").getAsJsonObject().get("x").getAsInt();
            y = jsonObject.get("diagramDevice").getAsJsonObject().get("position").getAsJsonObject().get("y").getAsInt();
            LineElement lineElement = new LineElement(
                    new Point(x,y),
                    new Dimension(width,height),
                    oldColor,
                    lineStroke
                );


            // uzimamo device1
            int x1, y1;
            x1 = jsonObject.get("shape").getAsJsonObject().get("x2").getAsInt();
            y1 = jsonObject.get("shape").getAsJsonObject().get("y2").getAsInt();

            // uzimamo device2
            int x2, y2;
            x2 = jsonObject.get("shape").getAsJsonObject().get("x1").getAsInt();
            y2 = jsonObject.get("shape").getAsJsonObject().get("y1").getAsInt();

            for (int i = 0; i < mindMapModel.getMapElements().size(); i++){
                if (mindMapModel.getMapElements().get(i).getDiagramDevice().getPosition().equals(new Point(x1,y1))){
                    lineElement.setDevice1(mindMapModel.getMapElements().get(i));
                }
                if (mindMapModel.getMapElements().get(i).getDiagramDevice().getPosition().equals(new Point(x2,y2))){
                    lineElement.setDevice2(mindMapModel.getMapElements().get(i));
                }
            }

            LinePainter linePainter = new LinePainter(lineElement, new Point(xFrom,yFrom), new Point(xTo,yTo), lineStroke, oldColor);
            mindMapModel.addVeza(linePainter);
        }


        return mindMapModel;
    }
}
