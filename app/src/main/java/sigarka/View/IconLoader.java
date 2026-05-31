package sigarka.View;

import javafx.scene.Node;
import javafx.scene.shape.SVGPath;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Group;

public class IconLoader {
    public static Node loadSvgIcon(String path, String color, double scale) {
        try {
            InputStream is = IconLoader.class.getResourceAsStream(path);
            if (is == null) {
                return new Region();
            }
            
            String content;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                content = reader.lines().collect(Collectors.joining("\n"));
            }
            
            Group group = new Group();

            Pattern pattern = Pattern.compile("d=\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(content);
            
            while (matcher.find()) {
                SVGPath svgPath = new SVGPath();
                svgPath.setContent(matcher.group(1));
                svgPath.setFill(Color.web(color));
                group.getChildren().add(svgPath);
            }
            
            group.setScaleX(scale);
            group.setScaleY(scale);
            
            return group;
        } catch (Exception e) {
            return new Region();
        }
    }
}
