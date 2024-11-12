package org.example.graphics6;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class BezierCurve extends Canvas {

    private List<Point2D> controlPoints = new ArrayList<>();
    private final int POINT_RADIUS = 5;
    private Point2D draggingPoint = null;
    private int draggingIndex = -1;

    public BezierCurve() {
        this.setWidth(800);
        this.setHeight(600);

        // Dodaj przykładowe punkty kontrolne
        controlPoints.add(new Point2D(100, 500));
        //controlPoints.add(new Point2D(200, 100));
        //controlPoints.add(new Point2D(400, 50));
        //controlPoints.add(new Point2D(700, 400));

        draw();


        this.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) { // Lewy przycisk myszy
                for (int i = 0; i < controlPoints.size(); i++) {
                    Point2D point = controlPoints.get(i);
                    if (point.distance(e.getX(), e.getY()) < POINT_RADIUS) {
                        draggingPoint = point;
                        draggingIndex = i;
                        return;
                    }
                }

                // Dodaje nowy punkt, jeśli kliknięcie nie dotyczy istniejącego punktu
                addControlPoint(e.getX(), e.getY());
            }
        });


        this.setOnMouseDragged(e -> {
            if (draggingPoint != null) {
                draggingPoint = new Point2D(e.getX(), e.getY());
                updateControlPoint(draggingIndex, e.getX(), e.getY());
            }
        });

        // (przestaje przeciągać)
        this.setOnMouseReleased(e -> {
            draggingPoint = null;
            draggingIndex = -1;
        });
    }

    // Rysuje krzywą Béziera i punkty kontrolne
    public void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());

        // Rysowanie punktów kontrolnych
        gc.setFill(Color.RED);
        for (Point2D point : controlPoints) {
            gc.fillOval(point.getX() - POINT_RADIUS, point.getY() - POINT_RADIUS, POINT_RADIUS * 2, POINT_RADIUS * 2);
        }

        // Rysowanie krzywej Béziera
        List<Point2D> curvePoints = BezierModel.calculateBezierCurve(controlPoints, 100);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        for (int i = 0; i < curvePoints.size() - 1; i++) {
            gc.strokeLine(curvePoints.get(i).getX(), curvePoints.get(i).getY(),
                    curvePoints.get(i + 1).getX(), curvePoints.get(i + 1).getY());
        }
    }

    // Dodaje nowy punkt kontrolny i odświeża rysowanie
    public void addControlPoint(double x, double y) {
        controlPoints.add(new Point2D(x, y));
        draw();
    }

    // Aktualizuje punkt kontrolny po przeciągnięciu
    public void updateControlPoint(int index, double x, double y) {
        if (index >= 0 && index < controlPoints.size()) {
            controlPoints.set(index, new Point2D(x, y));
            draw();
        }
    }
}
