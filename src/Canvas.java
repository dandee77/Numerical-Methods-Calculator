package src;
import static com.raylib.Raylib.*;
import static src.Utils.*; 

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Canvas {
    private Stack<List<StrokePoint>> strokeStack = new Stack<>();
    private Stack<List<StrokePoint>> redoStack = new Stack<>();
    private List<StrokePoint> currentStroke = new ArrayList<>();
    // private boolean showOutput = false;
    
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
            if (!strokeStack.isEmpty()) {
                // Move from stroke stack to redo stack
                redoStack.push(strokeStack.pop());
            }
        }
        // Redo with Y
        else if (IsKeyPressed(KEY_Y)) {
            if (!redoStack.isEmpty()) {
                // Move from redo stack back to stroke stack
                strokeStack.push(redoStack.pop());
            }
        }
        // Clear with R
        else if (IsKeyPressed(KEY_R)) {
            strokeStack.clear();
            redoStack.clear(); // Clear both stacks when clearing canvas
        }
        // Start new stroke on mouse press
        else if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            currentStroke.clear();
            redoStack.clear(); // Clear redo stack when starting new stroke
        }
        // Continue stroke while mouse is down
        else if (IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            Vector2 currentMousePosition = GetMousePosition();
            
            if (currentStroke.isEmpty() || 
                Vector2Distance(currentStroke.get(currentStroke.size() - 1).position, currentMousePosition) > strokeThreshold) {
                currentStroke.add(new StrokePoint(
                    vec2(currentMousePosition.x(), currentMousePosition.y()),
                    strokeWidth / 2
                ));
            }
        }
        // Finish stroke on mouse release
        else if (IsMouseButtonReleased(MOUSE_BUTTON_LEFT) && !currentStroke.isEmpty()) {
            strokeStack.push(new ArrayList<>(currentStroke));
            currentStroke.clear();
        }
    }

    public void draw() {
        // Render completed strokes from the stack
        for (List<StrokePoint> stroke : strokeStack) {
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
}