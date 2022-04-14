package no.ntnu.iir.wargames.models;

import com.google.common.primitives.Doubles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Optional;


/**
 * Actor for canvas.
 *
 * @author Henrik Norheim Nysæther
 * @version 14.04.2022
 */
public abstract class Actor {
  private double movementSpeed;
  private double x;
  private double y;
  private Color color;
  private double tokenSize;

  protected Actor() {
    setX(0);
    setY(0);
    setMovementSpeed(1);
    setColor(Color.FORESTGREEN);
    setTokenSize(5);
  }

  protected Actor(double x, double y, double movementSpeed, Color tokenColor) {
    setX(x);
    setY(y);
    setMovementSpeed(movementSpeed);
    setColor(tokenColor);
    setTokenSize(5);
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public Color getColor() {
    return color;
  }

  public double getTokenSize() {
    return tokenSize;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setTokenSize(double tokenSize) {
    this.tokenSize = tokenSize;
  }

  public double getMovementSpeed() {
    return movementSpeed;
  }

  /**
   * Sets the movement speed, if movement speed is less than 0 then the speed wil automatically be set to 1.
   *
   * @param movementSpeed - new movement speed
   */
  public void setMovementSpeed(double movementSpeed) {
    if (movementSpeed >= 0) {
      this.movementSpeed = movementSpeed;
    } else {
      this.movementSpeed = 1;
    }
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  /**
   * Move actor towards target.
   * Uses movement speed to constrain x/y movement to stay within max movement.
   *
   * @param targetPosition - the position to move towards
   */
  public void moveTowardPosition(double[] targetPosition) {
    // current position of actor
    double[] currentPosition = this.getPosition();

    // gets vector to target and constrains it to the movement speed.
    // This way we won't be able to move further away than the max range.
    double[] movementVector = new double[]{
        // x-axis
        Doubles.constrainToRange(
            (currentPosition[0] - targetPosition[0]),
            -this.movementSpeed,
            this.movementSpeed),
        // y-axis
        Doubles.constrainToRange(
            (currentPosition[1] - targetPosition[1]),
            -this.movementSpeed,
            this.movementSpeed)
    };

    // sets the new x and y coordinates
    setX(currentPosition[0] + movementVector[0]);
    setY(currentPosition[0] + movementVector[1]);
  }

  /**
   * Get position of actor. Position will be returned as a list with format [x, y].
   *
   * @return position
   */
  public double[] getPosition() {
    return new double[]{this.x, this.y};
  }

  /**
   * Draw actor's token.
   *
   * @param gc - canvas graphics context
   */
  public void draw(GraphicsContext gc) {
    if (gc != null) {
      gc.setStroke(this.color);
      gc.fillOval(this.x, this.y, this.tokenSize, this.tokenSize);
    }
  }

  // what actor will do when an update occurs.
  // TODO: implement onUpdate with unit classes
  public abstract void onUpdate();
}
