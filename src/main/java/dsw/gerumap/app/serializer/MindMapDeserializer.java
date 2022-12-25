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
            mindMapModel.addDiagramElements(new ElipsePainter(elipseElement));
        }

        //veze -> NIJE ZAVRSENO
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

            int x1, y1, width1, height1;
            String text1;
            float[] paint1 = new float[3];
            float stroke1;
            x1 = jsonObject.get("diagramDevice").getAsJsonObject().get("device1").getAsJsonObject().get("shape").getAsJsonObject().get("x").getAsInt();
            y1 = jsonObject.get("diagramDevice").getAsJsonObject().get("device1").getAsJsonObject().get("shape").getAsJsonObject().get("y").getAsInt();
            width1 = jsonObject.get("diagramDevice").getAsJsonObject().get("device1").getAsJsonObject().get("shape").getAsJsonObject().get("width").getAsInt();
            height1 = jsonObject.get("diagramDevice").getAsJsonObject().get("device1").getAsJsonObject().get("shape").getAsJsonObject().get("height").getAsInt();
            paint1[0] = (jsonObject.get("diagramDevice").getAsJsonObject().get("device1").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("paint").getAsJsonArray().get(0).getAsFloat());
            paint1[1] = (jsonObject.get("diagramDevice").getAsJsonObject().get("device1").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("paint").getAsJsonArray().get(1).getAsFloat());
            paint1[2] = (jsonObject.get("diagramDevice").getAsJsonObject().get("device1").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("paint").getAsJsonArray().get(2).getAsFloat());
            stroke1 = jsonObject.get("diagramDevice").getAsJsonObject().get("device1").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("stroke").getAsFloat();
            text1 = jsonObject.get("diagramDevice").getAsJsonObject().get("device1").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("name").getAsString();
            ElipseElement elipseElement1 = new ElipseElement(
                    new Point(x1,y1),
                    new Dimension(width1,height1),
                    paint1,
                    stroke1
            );
            ElipsePainter elipsePainter1 = new ElipsePainter(elipseElement1);
            elipseElement1.setName(text1);
            lineElement.setDevice1(elipsePainter1);


            // uzimamo device2


            int x2, y2, width2, height2;
            String text2;
            float[] paint2 = new float[3];
            float stroke2;
            x2 = jsonObject.get("diagramDevice").getAsJsonObject().get("device2").getAsJsonObject().get("shape").getAsJsonObject().get("x").getAsInt();
            y2 = jsonObject.get("diagramDevice").getAsJsonObject().get("device2").getAsJsonObject().get("shape").getAsJsonObject().get("y").getAsInt();
            width2 = jsonObject.get("diagramDevice").getAsJsonObject().get("device2").getAsJsonObject().get("shape").getAsJsonObject().get("width").getAsInt();
            height2 = jsonObject.get("diagramDevice").getAsJsonObject().get("device2").getAsJsonObject().get("shape").getAsJsonObject().get("height").getAsInt();
            paint2[0] = (jsonObject.get("diagramDevice").getAsJsonObject().get("device2").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("paint").getAsJsonArray().get(0).getAsFloat());
            paint2[1] = (jsonObject.get("diagramDevice").getAsJsonObject().get("device2").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("paint").getAsJsonArray().get(1).getAsFloat());
            paint2[2] = (jsonObject.get("diagramDevice").getAsJsonObject().get("device2").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("paint").getAsJsonArray().get(2).getAsFloat());
            stroke2 = jsonObject.get("diagramDevice").getAsJsonObject().get("device2").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("stroke").getAsFloat();
            text2 = jsonObject.get("diagramDevice").getAsJsonObject().get("device2").getAsJsonObject().get("diagramDevice").getAsJsonObject().get("name").getAsString();
            ElipseElement elipseElement2 = new ElipseElement(
                    new Point(x2,y2),
                    new Dimension(width2,height2),
                    paint2,
                    stroke2
            );
            ElipsePainter elipsePainter2 = new ElipsePainter(elipseElement2);
            elipseElement2.setName(text2);
            lineElement.setDevice2(elipsePainter2);





            //lineElement.setDevice1(mindMapModel.getMapElements().get(0));
            //lineElement.setDevice2(mindMapModel.getMapElements().get(1));
            mindMapModel.addVeza(new LinePainter(lineElement, new Point(xFrom,yFrom), new Point(xTo,yTo), lineStroke, oldColor));
        }


        return mindMapModel;
    }
}
