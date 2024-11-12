package org.example.graphics6;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;

public class BezierModel {

    // Oblicza punkt na krzywej Béziera dla zadanego parametru t
    public static Point2D calculateBezierPoint(List<Point2D> points, double t) {
        int n = points.size() - 1;
        double x = 0;
        double y = 0;

        // Oblicza współrzędne punktu na krzywej Béziera
        for (int i = 0; i <= n; i++) {
            double binomialCoeff = binomialCoefficient(n, i);
            double pow1 = Math.pow(1 - t, n - i);
            double pow2 = Math.pow(t, i);
            x += binomialCoeff * pow1 * pow2 * points.get(i).getX();
            y += binomialCoeff * pow1 * pow2 * points.get(i).getY();
        }

        return new Point2D(x, y);
    }

    // Oblicza współczynnik Newtona (współczynnik dwumianowy)
    private static int binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
    }

    // Oblicza punkty na krzywej Béziera
    public static List<Point2D> calculateBezierCurve(List<Point2D> points, int steps) {
        List<Point2D> curvePoints = new ArrayList<>();
        for (int i = 0; i <= steps; i++) {
            double t = i / (double) steps;
            curvePoints.add(calculateBezierPoint(points, t));
        }
        return curvePoints;
    }
}
