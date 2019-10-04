package PureStorage;

import java.util.*;

public class DrawCirclePixel {


  public void midPointCircleDraw(int xcentre, int ycentre, int r) {
    int x = r, y = 0;

    // Printing the initial point on the axes
    // after translation
    printCirclePoint(xcentre + x, ycentre + y);

    // When radius is zero only a single
    // point will be printed
    if (r > 0) {
      printCirclePoint(xcentre + x, ycentre - y);
      printCirclePoint(ycentre + y, xcentre + x);
      printCirclePoint(ycentre - y, xcentre + x);
    }

    // Initialising the value of P
    int P = 1 - r;
    while (x > y) {
      y++;

      // Mid-point is inside or on the perimeter
      if (P <= 0) P = P + 2 * y + 1;
        // Mid-point is outside the perimeter
      else {
        x--;
        P = P + 2 * y - 2 * x + 1;
      }

      // All the perimeter points have already been printed
      if (x < y) break;
      // Printing the generated point and its reflection
      // in the other octants after translation

      printCirclePoint(xcentre + x, ycentre + y);
      printCirclePoint(xcentre - x, ycentre + y);
      printCirclePoint(xcentre + x, ycentre - y);
      printCirclePoint(xcentre - x, ycentre - y);

      if (x != y) {
        printCirclePoint(ycentre + y, xcentre + x);
        printCirclePoint(ycentre - y, xcentre + x);
        printCirclePoint(ycentre + y, xcentre - x);
        printCirclePoint(ycentre - y, xcentre - x);
      }

    }

    }

  // Function for circle-generation
// using Bresenham's algorithm
  void circleBres(int xc, int yc, int r)
  {
    int x = 0, y = r;
    int d = 3 - 2 * r;
    while (y >= x)
    {
      // for each pixel we will
      // draw all eight pixels
      drawCircle(xc, yc, x, y);
      x++;

      // check for decision parameter
      // and correspondingly
      // update d, x, y
      if (d > 0)
      {
        y--;
        d = d + 4 * (x - y) + 10;
      }
      else
        d = d + 4 * x + 6;
      drawCircle(xc, yc, x, y);
    }
  }

  public void printCirclePoint(int x, int y) {
    System.out.println("(" + x + ", " + y + ")");
  }

  public void drawCircle(int xc, int yc, int x, int y) {
    System.out.println("(" + x + ", " + y + ")");
    putpixel(xc+x, yc+y, RED);
    putpixel(xc-x, yc+y, RED);
    putpixel(xc+x, yc-y, RED);
    putpixel(xc-x, yc-y, RED);
    putpixel(xc+y, yc+x, RED);
    putpixel(xc-y, yc+x, RED);
    putpixel(xc+y, yc-x, RED);
    putpixel(xc-y, yc-x, RED);
  }

}
