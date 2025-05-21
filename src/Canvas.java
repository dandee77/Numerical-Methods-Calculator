package src;
import static com.raylib.Raylib.*;
import static src.Utils.*; 

import java.util.ArrayList;
import java.util.List;

public class Canvas {
    private List<List<StrokePoint>> strokes = new ArrayList<>();
    private List<StrokePoint> currentStroke = new ArrayList<>();
    private boolean showOutput = false;
    
    // Drawing settings
    private final float strokeWidth = 50.0f;
    private final float strokeThreshold = 30.0f;
    private final Color strokeColor = black;
    
    // Stroke point helper class
    private static class StrokePoint {
        public Vector2 position;
        public float radius;
        
        public StrokePoint(Vector2 position, float radius) {
            this.position = position;
            this.radius = radius;
        }
    }

    public void update() {
        // Undo with Ctrl+Z
        if (IsKeyPressed(KEY_Z) && IsKeyDown(KEY_LEFT_CONTROL)) {
            if (!strokes.isEmpty()) {
                strokes.remove(strokes.size() - 1);
            }
        }
        // Clear with R
        else if (IsKeyPressed(KEY_R)) {
            strokes.clear();
        }
        // Start new stroke on mouse press
        else if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            currentStroke.clear();
        }
        // Continue stroke while mouse is down
        else if (IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            Vector2 currentMousePosition = GetMousePosition();
            
            if (currentStroke.isEmpty() || Vector2Distance(currentStroke.get(currentStroke.size() - 1).position, currentMousePosition) > strokeThreshold) {
                currentStroke.add(new StrokePoint(
                    vec2(currentMousePosition.x(), currentMousePosition.y()),
                    strokeWidth / 2
                ));
            }
        }
        // Finish stroke on mouse release
        else if (IsMouseButtonReleased(MOUSE_BUTTON_LEFT) && !currentStroke.isEmpty()) {
            strokes.add(new ArrayList<>(currentStroke));
            currentStroke.clear();
        }
    }

    public void draw() {
        // Render completed strokes
        for (List<StrokePoint> stroke : strokes) {
            for (int i = 1; i < stroke.size(); i++) {
                StrokePoint prev = stroke.get(i - 1);
                StrokePoint current = stroke.get(i);
                
                DrawLineEx(
                    vec2(prev.position.x(), prev.position.y()),
                    vec2(current.position.x(), current.position.y()),
                    strokeWidth,
                    strokeColor
                );
                
                DrawCircleV(
                    vec2(current.position.x(), current.position.y()),
                    current.radius,
                    strokeColor
                );
            }
        }
        
        // Render current stroke in progress
        for (int i = 1; i < currentStroke.size(); i++) {
            StrokePoint prev = currentStroke.get(i - 1);
            StrokePoint current = currentStroke.get(i);
            
            DrawLineEx(
                vec2(prev.position.x(), prev.position.y()),
                vec2(current.position.x(), current.position.y()),
                strokeWidth,
                strokeColor
            );
            
            DrawCircleV(
                vec2(current.position.x(), current.position.y()),
                current.radius,
                strokeColor
            );
        }
    }
    
    // Helper method to calculate distance between two points
    // private float Vector2Distance(Vector2 a, Vector2 b) {
    //     float dx = a.x() - b.x();
    //     float dy = a.y() - b.y();
    //     return (float)Math.sqrt(dx * dx + dy * dy);
    // }
}